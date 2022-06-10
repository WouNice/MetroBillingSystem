package org.example.controller;

import org.example.common.dijkstra.SubwayLineCount;
import org.example.common.enums.CardType;
import org.example.mapper.SingleCardMapper;
import org.example.mapper.SubwayStationMapper;
import org.example.mapper.UserMapper;
import org.example.model.SingleCard;
import org.example.model.SubwayStation;
import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 卡管理
 */
@Controller
@ResponseBody
@RequestMapping("/v1/card")
public class CardController
{
    @Resource
    private SingleCardMapper singleCardMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private SubwayStationMapper subwayStationMapper;

    /**
     * 交通卡充值
     */
    @RequestMapping(value = "/traffic", method = RequestMethod.POST)
    public boolean addT_card(
        @RequestParam("id")
        int id,
        @RequestParam("money")
        int money)
    {
        User user = userMapper.query(id);
        String cardType = user.getCardType();
        if (Objects.equals(cardType, CardType.CARD_TYPE.name()))
        {
            return false;
        }
        else
        {
            if (Objects.equals(cardType, CardType.NONE.name()))
            {
                user.setCardType(CardType.M_CARD.name());
            }
            if (user.getCardMoney() < money)
            {
                return false;
            }
            user.setMoney(user.getMoney() - money);
            user.setCardMoney(user.getCardMoney() + money);
            userMapper.update(user);
            return true;
        }
    }

    /**
     * 单程卡购买
     */
    @RequestMapping(value = "/oneway", method = RequestMethod.POST)
    public boolean addS_card(
        @RequestParam("id")
        int id,
        @RequestParam("start")
        int startNode,
        @RequestParam("end")
        int endNode)
    {
        User user = userMapper.query(id);
        String cardType = user.getCardType();
        if (Objects.equals(cardType, CardType.M_CARD.name()))
        {
            return false;
        }
        else
        {
            user.setCardType(CardType.CARD_TYPE.name());
            SubwayStation start = subwayStationMapper.query(startNode);
            SubwayStation end = subwayStationMapper.query(startNode);
            int price = SubwayLineCount.getPrice(start, end);
            if (user.getMoney() < price)
            {
                return false;
            }
            user.setMoney(user.getMoney() - price);
            user.setCardMoney(user.getCardMoney() + price);

            userMapper.update(user);
            SingleCard singleCard = new SingleCard(id, startNode, endNode);
            if (Objects.equals(cardType, CardType.NONE.name()))
            {
                singleCardMapper.add(singleCard);
            }
            else
            {
                singleCardMapper.update(singleCard);
            }
            return true;
        }
    }
}