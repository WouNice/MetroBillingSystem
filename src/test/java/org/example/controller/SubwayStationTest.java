package org.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SubwayStationTest
{
    @Resource
    private MockMvc mockMvc;

    @Test
    public void add_new_station() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.PUT, "/v1/subway/station")
            .contentType("application/json")
            .param("id", "1001")
            .param("name", "mike")
            .param("weight", "100"));
    }
}
