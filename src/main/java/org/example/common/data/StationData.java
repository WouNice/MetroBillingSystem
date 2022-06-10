package org.example.common.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.common.dijkstra.SubwayLineCount;
import org.example.model.SubwayStation;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationData
{
    private SubwayStation station;

    private List<SubwayLineData> subwayLines;

    @Resource
    private SubwayLineCount subwayLineCount;

    /**
     * 获取换乘站点
     */
    public void getNodes()
    {
        this.subwayLines = new ArrayList<>();
        List<SubwayLineData> allLines = subwayLineCount.getMultiLine();
        for (SubwayLineData line : allLines)
        {
            List<SubwayStation> stations = line.getStations();
            if (stations.contains(station))
            {
                this.subwayLines.add(line);
            }
        }
    }
}
