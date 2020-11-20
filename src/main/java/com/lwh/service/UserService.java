package com.lwh.service;

import com.lwh.param.LoginParam;
import com.lwh.pojo.User;
import com.lwh.result.Result;


public interface UserService {

    Result<User> login(LoginParam loginParam);

}
