package com.autumn.controller;

import com.autumn.dto.LoginDataDto;
import com.autumn.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登陆逻辑
 * @author huangfu
 */
@RestController
@RequestMapping("admin/user")
@CrossOrigin
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
