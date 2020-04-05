package com.redsea.waimai.service;

import com.redsea.waimai.dto.ResultDTO;
import com.redsea.waimai.model.User;

public interface LoginService {
    ResultDTO register(User user);

    ResultDTO login(User user);

    ResultDTO changePassword(User user);

    ResultDTO forgetPassword(User user);
}
