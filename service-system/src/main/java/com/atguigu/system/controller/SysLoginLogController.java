package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysLoginLog;
import com.atguigu.model.vo.SysLoginLogQueryVo;
import com.atguigu.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Author Yjw
 * 2022/11/9 22:30
 */
@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping("/admin/system/sysLoginLog")
public class SysLoginLogController {

    @Resource
    private LoginLogService loginLogService;

    //条件分页查询登录日志
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        SysLoginLogQueryVo sysLoginLogQueryVo) {
        IPage<SysLoginLog> pageModel= loginLogService.selectPage(page, limit, sysLoginLogQueryVo);
        return Result.ok(pageModel);
    }
}
