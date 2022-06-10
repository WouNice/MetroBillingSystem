package org.example.mapper;

import org.example.model.SubwayLine;

import java.util.List;

public interface SubwayLineMapper
{
    int add(SubwayLine subwayLine); //地铁线录入

    SubwayLine query(int id);  //地铁线查询

    List<SubwayLine> queryAll();  //所有地铁线查询
}