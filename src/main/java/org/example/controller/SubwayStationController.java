package org.example.controller;

import org.example.common.data.StationData;
import org.example.mapper.SubwayStationMapper;
import org.example.model.SubwayStation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 地铁站点
 */
@Controller
@ResponseBody
@RequestMapping("/v1/subway/station")
public class SubwayStationController
{

    @Resource
    private SubwayStationMapper subwayStationMapper;

    /**
     * 地铁站点录入
     */
    @RequestMapping(method = RequestMethod.PUT)
    public int addStation(
        @RequestParam("id")
        int id,
        @RequestParam("name")
        String name,
        @RequestParam("weight")
        int weight)
    {
        SubwayStation subwayStation = new SubwayStation(id, name, weight);
        return subwayStationMapper.add(subwayStation);
    }

    /**
     * 地铁站点查询
     * + 换乘站点
     */
    @RequestMapping(method = RequestMethod.GET)
    public StationData queryStation(
        @RequestParam("id")
        int id)
    {
        SubwayStation station = subwayStationMapper.query(id);
        StationData stationData = new StationData();
        stationData.setStation(station);
        stationData.getNodes();
        return stationData;
    }
}