package com.redsea.waimai.service.impl;

import com.redsea.waimai.dto.ResultDTO;
import com.redsea.waimai.exception.CustomizeErrorCode;
import com.redsea.waimai.mapper.UserMapper;
import com.redsea.waimai.model.User;
import com.redsea.waimai.model.UserExample;
import com.redsea.waimai.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public ResultDTO register(User user) {
        // 查找数据库是否存在相同账号
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(user.getAccount());
        long count = userMapper.countByExample(userExample);
        ResultDTO<User> resultDTO = new ResultDTO();
        if (count > 0) {
            // 存在相同账号
            resultDTO = resultDTO.errorOf(CustomizeErrorCode.REGISTER_FAIL);
        } else {
            // 不存在相同账号
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            resultDTO = resultDTO.okOf("注册成功");
            resultDTO.setData(user);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO login(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountEqualTo(user.getAccount())
                .andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        ResultDTO<User> resultDTO = new ResultDTO<>();
        if (users.size() > 0) {
            // 登录成功
            resultDTO = resultDTO.okOf("登录成功");
            resultDTO.setData(users.get(0));
        } else {
            // 登录失败
            resultDTO = resultDTO.errorOf(CustomizeErrorCode.LOGIN_FAIL);
        }
        return resultDTO;
    }

    @Override
    @Transactional
    public ResultDTO changePassword(User user) {
        User userTemp = userMapper.selectByPrimaryKey(user.getId());
        ResultDTO<User> resultDTO = new ResultDTO<>();
        if (userTemp != null) {
            // 修改密码
            User updateUser = new User();
            updateUser.setPassword(user.getPassword());
            updateUser.setGmtModified(System.currentTimeMillis());
            userMapper.updateByPrimaryKeySelective(user);
            resultDTO = resultDTO.okOf("密码修改成功");
            resultDTO.setData(user);
        } else {
            // 用户不存在，无法修改密码
            resultDTO = resultDTO.errorOf(CustomizeErrorCode.CHANGE_PASSWORD_FAIL);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO forgetPassword(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountEqualTo(user.getAccount());
        List<User> users = userMapper.selectByExample(userExample);
        ResultDTO<User> resultDTO = new ResultDTO<>();
        if (users.size() > 0) {
            // 查找成功
            resultDTO = resultDTO.okOf("找回密码成功");
            resultDTO.setData(users.get(0));
        } else {
            // 查找失败
            resultDTO = resultDTO.errorOf(CustomizeErrorCode.FIND_PASSWORD_FAIL);
        }
        return resultDTO;
    }
}
