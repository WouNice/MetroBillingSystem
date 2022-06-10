package org.example.mapper;

import org.example.model.SubwayStation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubwayStationMapperTest
{
    @Resource
    private SubwayStationMapper subwayStationMapper;

    @Test
    public void add_new_station()
    {
        SubwayStation subwayStation = new SubwayStation(1012, "bj", 3);
        subwayStationMapper.add(subwayStation);
    }

}
