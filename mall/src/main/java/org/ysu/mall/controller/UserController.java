package org.ysu.mall.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ysu.mall.common.ApiResponse;
import org.ysu.mall.domain.dto.UserDto;
import org.ysu.mall.domain.entity.User;
import org.ysu.mall.enums.ResultEnum;
import org.ysu.mall.exception.BusinessException;
import org.ysu.mall.service.UserService;
import org.ysu.mall.util.JwtUtil;
import org.ysu.mall.validationGroups.ResetPasswoedGroup;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody UserDto userDto) {
        try{
            if(userService.register(userDto)){
                return ApiResponse.success(ResultEnum.SUCCESS);
            }else{
                return ApiResponse.error(ResultEnum.USER_ADD_ERROR);
            }
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());

        }
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@Valid @RequestBody UserDto userDto) {
        try{
            User user = userService.login(userDto.getUsername(), userDto.getPassword());
            user.setToken(JwtUtil.generateToken(user.getUserId(),user.getUsername()));
            return ApiResponse.success();
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    @PutMapping("/update")
    public ApiResponse<?> update(@Valid @RequestBody UserDto userDto) {
        try{
            User user = userService.update(userDto);
            return ApiResponse.success(user);
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }


    @PutMapping("/resetPassword")
    public ApiResponse<?> resetPassword(@Validated({ResetPasswoedGroup.class}) @RequestBody UserDto userDto) {
        try{
            if(userService.resetPassword(userDto.getUserId(), userDto.getPassword())){
                return ApiResponse.success(ResultEnum.SUCCESS);
            }else{
                return ApiResponse.error(ResultEnum.USER_PASSWORD_RESET_FAILED);
            }
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }

    @DeleteMapping("/delete")
    public ApiResponse<?> delete(@RequestParam Integer userId) {
        try{
            if(userService.delete(userId)){
                return ApiResponse.success(ResultEnum.SUCCESS);
            }else{
                return ApiResponse.error(ResultEnum.USER_DELETE_ERROR);
            }
        }catch (BusinessException e){
            return ApiResponse.error(e.getCode());
        }
    }
}
