package org.example.controller;

import org.example.mapper.SubwayLineMapper;
import org.example.model.SubwayLine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 地铁线
 */
@Controller
@ResponseBody
@RequestMapping("/v1/subway/line")
public class SubwayLineController
{
    @Resource
    private SubwayLineMapper subwayLineMapper;

    /**
     * 地铁线录入
     */
    @RequestMapping(method = RequestMethod.PUT)
    public int addLine(
        @RequestParam("id")
        int id,
        @RequestParam("name")
        String name,
        @RequestParam("stations")
        String stations)
    {
        SubwayLine subwayLine = new SubwayLine(id, name, stations);
        return subwayLineMapper.add(subwayLine);
    }

    /**
     * 地铁线查询
     */
    @RequestMapping(method = RequestMethod.GET)
    public SubwayLine queryLine(
        @RequestParam("id")
        int id)
    {
        return subwayLineMapper.query(id);
    }
}