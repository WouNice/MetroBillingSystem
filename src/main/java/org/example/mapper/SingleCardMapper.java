package org.example.mapper;

import org.example.model.SingleCard;

public interface SingleCardMapper
{
    int add(SingleCard singleCard); //购买单程卡

    int update(SingleCard singleCard);  //更新单程卡

    SingleCard query(int id); //查询
}