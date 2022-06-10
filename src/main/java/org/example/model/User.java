package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User
{
    private Integer id;

    private String name;

    private Integer money;

    private String cardType;

    private Integer cardMoney;

    private Integer lossTimes;

    private Integer isBlack;

}