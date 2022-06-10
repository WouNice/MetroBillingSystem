package org.example.common.dijkstra;

//    A1 A2 A3 M1 A4 A5 A6
//    B1 B2 B3 M1 B4 M2 B5 B6  //M1
//    C1 C2 C3 C4 M2 C5 C6  //M2
//    D1 D2 D3 D4 D5 M1 D6  //M2

import lombok.Getter;
import lombok.Setter;
import org.example.common.data.SubwayLineData;
import org.example.mapper.SubwayLineMapper;
import org.example.mapper.SubwayStationMapper;
import org.example.model.SubwayLine;
import org.example.model.SubwayStation;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class SubwayLineCount
{
    @Resource
    private SubwayStationMapper subwayStationMapper;

    @Resource
    private SubwayLineMapper subwayLineMapper;

    @Getter
    @Setter
    private List<SubwayLineData> multiLine;

    public SubwayLineCount()
    {
        List<SubwayLineData> multiLine = new ArrayList<>();
        List<SubwayLine> subwayLines = subwayLineMapper.queryAll();
        for (SubwayLine subwayLine : subwayLines)
        {
            SubwayLineData subwayLineData = new SubwayLineData();
            subwayLineData.setId(subwayLine.getId());
            subwayLineData.setName(subwayLine.getName());

            String stations = subwayLine.getStations();
            String[] station_ids = stations.split(":");

            List<SubwayStation> subwayStations = new ArrayList<>();
            for (String id_str : station_ids)
            {
                int station_id = Integer.parseInt(id_str);
                subwayStations.add(subwayStationMapper.query(station_id));
            }
            subwayLineData.setStations(subwayStations);
            multiLine.add(subwayLineData);  //已加载所有的路线
        }

    }

    /**
     * 按路线计费规则9选取价格最少的一条路线计费
     */
    public static int getPrice(SubwayStation start, SubwayStation end)
    {
        return 0;
    }

    /**
     * 计算路线起点位置
     */
    public int seek(List<SubwayStation> subwayLineData, SubwayStation ium)
    {

        int index = 0;
        for (SubwayStation station : subwayLineData)
        {
            if (ium.equals(station))
            {
                return index;
            }
            index = index + 1;
        }
        return -1;
    }
}
