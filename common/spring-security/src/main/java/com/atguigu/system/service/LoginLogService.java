package com.atguigu.system.service;

import com.atguigu.model.system.SysLoginLog;
import com.atguigu.model.vo.SysLoginLogQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * Author Yjw
 * 2022/11/9 20:47
 */
public interface LoginLogService {

    public void recordLoginLog(String username, Integer status,
                               String ipaddr, String msg);

    //条件分页查询登录日志
    IPage<SysLoginLog> selectPage(Long page, Long limit, SysLoginLogQueryVo sysLoginLogQueryVo);
}
