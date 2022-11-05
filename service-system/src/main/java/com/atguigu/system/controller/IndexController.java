package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.common.utils.JwtHelper;
import com.atguigu.common.utils.MD5;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.LoginVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Author Yjw
 * 2022/10/29 21:35
 */
/**
 * <p>
 * 后台登录登出
 * </p>
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {

        //根据用户名称查询数据
        SysUser sysUser = sysUserService.getUserInfoByUserName(loginVo.getUsername());

        //如果查询为空
        if (sysUser == null) {
            throw new GuiguException(20001, "用户不存在");
        }

        //判断密码是否一致
        String password = loginVo.getPassword();
        String md5Password = MD5.encrypt(password);
        if (sysUser.getPassword().equals(md5Password)) {
            throw new GuiguException(20001, "密码不正确");
        }

        //判断用户是否可用
        if (sysUser.getStatus().intValue() == 0) {
            throw new GuiguException(20001, "用户已经被禁用");
        }
        //根据 userId 和 userName 生成 token 字符串，通过 map 返回
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.ok(map);

    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public Result info(HttpServletRequest request) {

        //获取请求头 token 字符串
        String token = request.getHeader("token");

        //从 token 字符串获取用户名称（id）
        String username = JwtHelper.getUsername(token);

        //根据用户名称获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }
}
