package com.lwh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private int id;

    private String userName;

    private String phone;

    private String password;

    private String salt;

    private String head;

    private int loginCount;

    private Date registerDate;

    private Date lastLoginDate;

}
