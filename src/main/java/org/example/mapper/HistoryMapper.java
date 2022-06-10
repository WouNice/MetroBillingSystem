package org.example.mapper;

import org.example.model.History;

import java.util.List;

public interface HistoryMapper
{
    int add(History history);  //增加乘坐历史记录信息

    List<History> query(int id);  //查询乘坐历史记录信息

    List<History> blackQuery(int id, int black); //返回乘客对应的失信记录
}