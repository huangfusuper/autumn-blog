package com.autumn.controller;

import com.autumn.dto.LoginDataDto;
import com.autumn.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆逻辑
 * @author huangfu
 */
@RestController
@RequestMapping("admin/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public String login(@RequestBody @Validated LoginDataDto loginDataDto){
        return userService.login(loginDataDto);
    }
}
