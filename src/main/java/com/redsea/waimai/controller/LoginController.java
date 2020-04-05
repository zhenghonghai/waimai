package com.redsea.waimai.controller;

import com.redsea.waimai.dto.ResultDTO;
import com.redsea.waimai.model.User;
import com.redsea.waimai.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    // 1. 注册
    @PostMapping("/register")
    @ResponseBody
    public ResultDTO<User> register(@RequestBody User user) {
        ResultDTO resultDTO = loginService.register(user);
        return resultDTO;
    }
    // 2. 登录
    @PostMapping("/login")
    @ResponseBody
    public ResultDTO login(@RequestBody User user) {
        ResultDTO resultDTO = loginService.login(user);
        return resultDTO;
    }
    // 3. 修改密码
    @PostMapping("/changePassword")
    @ResponseBody
    public ResultDTO changePassword(@RequestBody User user){
        ResultDTO resultDTO = loginService.changePassword(user);
        return resultDTO;
    }
    // 4. 忘记密码
    @PostMapping("/forgetPassword")
    @ResponseBody
    public ResultDTO forgetPassword(@RequestBody User user) {
        ResultDTO resultDTO = loginService.forgetPassword(user);
        return resultDTO;
    }

}
