package org.example.mapper;

import org.example.common.enums.CardType;
import org.example.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest
{
    @Resource
    private UserMapper userMapper;

    @Test
    public void add_new_user()
    {
        User user = new User((int) (Math.random() * 1000000), "user001", 100, CardType.NONE.name(), 0, 0, 1);
        System.out.println(user);
        userMapper.add(user);
    }
}
