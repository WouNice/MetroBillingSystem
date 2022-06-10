package org.example.controller;

import org.example.common.data.UserData;
import org.example.mapper.HistoryMapper;
import org.example.mapper.UserMapper;
import org.example.model.History;
import org.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询失信黑名单
 */
@Controller
@ResponseBody
@RequestMapping("/v1/history")
public class HistoryController
{
    @Resource
    private HistoryMapper historyMapper;

    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/black", method = RequestMethod.GET)
    public ArrayList<UserData> queryBlack_history()
    {
        List<User> blackUsers = userMapper.queryBlack();
        ArrayList<UserData> black_list = new ArrayList<>();
        for (User blackUser : blackUsers)
        {
            int user_id = blackUser.getId();
            List<History> blackHistory = historyMapper.blackQuery(user_id, 1);
            UserData userData = new UserData(blackUser, blackHistory);
            black_list.add(userData);
        }
        return black_list;
    }
}