package org.example.controller;

import org.example.common.data.UserData;
import org.example.common.enums.CardType;
import org.example.mapper.HistoryMapper;
import org.example.mapper.UserMapper;
import org.example.model.History;
import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 乘客数据
 */
@Controller
@ResponseBody
@RequestMapping(value = "/v1/user/data")
public class UserController
{
    @Resource
    private UserMapper userMapper;

    @Resource
    private HistoryMapper historyMapper;

    /**
     * 乘客数据录入
     */
    @RequestMapping(method = RequestMethod.POST)
    public Integer addData(
        @RequestParam("id")
        int id,
        @RequestParam("name")
        String name,
        @RequestParam("money")
        int money)
    {
        User user = new User(id, name, money, CardType.NONE.name(), 0, 0, 0);
        return userMapper.update(user);
    }

    /**
     * 乘客数据删除
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public int delData(
        @RequestParam("id")
        int id)
    {
        return userMapper.delete(id);
    }

    /**
     * 乘客数据修改
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public int updateData(
        @RequestParam("id")
        int id,
        @RequestParam("name")
        String name,
        @RequestParam("money")
        int money,
        @RequestParam("cardType")
        String cardType,
        @RequestParam("cardMoney")
        int cardMoney,
        @RequestParam("lossTimes")
        int lossTimes,
        @RequestParam("isBlack")
        Integer isBlack)
    {
        User user = userMapper.query(id);
        user.setName(name);
        user.setMoney(money);
        user.setCardType(cardType);
        user.setCardMoney(cardMoney);
        user.setLossTimes(lossTimes);
        user.setIsBlack(isBlack);
        return userMapper.update(user);
    }

    /**
     * 乘客数据查询
     */
    @RequestMapping(method = RequestMethod.GET)
    public UserData queryData(
        @RequestParam("id")
        int id)
    {
        User user = userMapper.query(id);
        List<History> history = historyMapper.query(id);
        UserData userData = new UserData();
        userData.setUser(user);
        userData.setHistory(history);
        return userData;
    }

}