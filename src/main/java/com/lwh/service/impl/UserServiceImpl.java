package com.lwh.service.impl;

import com.lwh.mapper.UserMapper;
import com.lwh.param.LoginParam;
import com.lwh.pojo.User;
import com.lwh.result.CodeMsg;
import com.lwh.result.Result;
import com.lwh.service.UserService;
import com.lwh.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Result<User> login(LoginParam loginParam) {
        User user = userMapper.checkPhone(loginParam.getMobile());
        if (user == null){
            return Result.error(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPwd = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDbPass(loginParam.getPassword(),saltDB);
        if (!StringUtils.equals(dbPwd,calcPass)){
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
        user.setPassword(StringUtils.EMPTY);
        return Result.success(user);
    }
}
