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
public class UserTest
{
    @Resource
    private MockMvc mockMvc;

    @Test
    public void user_query() throws Exception
    {
        String responseString = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/v1/user/data")
                .contentType("application/json")
                .param("id", "574535"))
            //.andExpect(MockMvcResultMatchers.status().isOk())
            //.andExpect(MockMvcResultMatchers.content().string("ok"))
            .andReturn().getResponse().getContentAsString();
        System.out.println("--------返回的json = " + responseString);
    }

}
