package org.example.mapper;

import org.example.model.User;

import java.util.List;

public interface UserMapper
{
    int add(User user); //增加乘客

    int delete(int id); //删除乘客

    int update(User user); //更新乘客

    User query(int id);  //查询乘客

    List<User> queryBlack();  //查询失信黑名单

}