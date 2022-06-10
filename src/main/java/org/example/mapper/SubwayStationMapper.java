package org.example.mapper;

import org.example.model.SubwayStation;

public interface SubwayStationMapper
{
    int add(SubwayStation subwayStation); //地铁站点录入

    SubwayStation query(int id);  //地铁站点查询 by id

}