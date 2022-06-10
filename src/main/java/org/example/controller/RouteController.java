package org.example.controller;

import org.example.common.dijkstra.SubwayLineCount;
import org.example.common.enums.CardType;
import org.example.mapper.HistoryMapper;
import org.example.mapper.SingleCardMapper;
import org.example.mapper.SubwayStationMapper;
import org.example.mapper.UserMapper;
import org.example.model.History;
import org.example.model.SingleCard;
import org.example.model.SubwayStation;
import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Controller
@ResponseBody
@RequestMapping(value = "/v1/user/route")
public class RouteController
{
    @Resource
    private UserMapper userMapper;

    @Resource
    private HistoryMapper historyMapper;

    @Resource
    private SubwayStationMapper subwayStationMapper;

    @Resource
    private SingleCardMapper singleCardMapper;

    /**
     * 乘坐
     */
    @RequestMapping(method = RequestMethod.POST)
    public History addData(
        @RequestParam("id")
        int id,
        @RequestParam("start")
        int start,
        @RequestParam("end")
        int end)
    {
        User user = userMapper.query(id);
        int isBlack = user.getIsBlack();
        if (isBlack == 1)
        {
            System.out.println("黑名单用户不允许乘坐地铁");
            return new History();
        }
        String cardType = user.getCardType();
        if (Objects.equals(cardType, CardType.NONE.name()))
        {
            System.out.println("没有乘车卡");
            return new History();
        }
        SubwayStation startNode = subwayStationMapper.query(start);
        SubwayStation endNode = subwayStationMapper.query(end);
        int price = SubwayLineCount.getPrice(startNode, endNode);

        //初始化乘坐记录
        History history = new History();
        history.setId(user.getId());
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        history.setTime(java.sql.Date.valueOf(simpleDateFormat.format(currentDate.getTime())));
        history.setStartNode(startNode.toString());
        history.setEndNode(endNode.toString());

        //交通卡乘坐
        if (Objects.equals(cardType, CardType.M_CARD.name()))
        {
            if (user.getCardMoney() < price)
            {
                if (user.getMoney() < price)
                {
                    user.setIsBlack(1);
                }
                user.setMoney(user.getMoney() - price);
                history.setBlack(1);
                user.setLossTimes(user.getLossTimes() + 1);
            }
            else
            {
                user.setCardMoney(user.getCardMoney() - price);
            }
        }

        //单程卡乘坐
        if (Objects.equals(cardType, CardType.CARD_TYPE.name()))
        {
            SingleCard singleCard = singleCardMapper.query(id);
            SubwayStation reStart = subwayStationMapper.query(singleCard.getStartNode());
            SubwayStation reEnd = subwayStationMapper.query(singleCard.getEndNode());
            if (reStart == startNode && reEnd == endNode)
            {
                history.setBlack(0);
                user.setCardMoney(0);
                user.setCardType(CardType.NONE.name());
            }
            else
            {
                if (user.getCardMoney() < price)
                {
                    user.setLossTimes(user.getLossTimes() + 1);
                    if (user.getLossTimes() >= 3 | user.getMoney() < price)
                    {
                        user.setIsBlack(1);
                    }
                    history.setBlack(1);
                    user.setMoney(user.getMoney() - price);
                }
                else
                {
                    user.setCardMoney(user.getCardMoney() - price);
                    history.setBlack(0);
                }
            }
        }
        //更新用户信息
        userMapper.update(user);
        //生成乘坐记录
        historyMapper.add(history);
        return history;
    }
}