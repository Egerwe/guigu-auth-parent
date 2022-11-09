package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysLoginLog;
import com.atguigu.model.vo.SysLoginLogQueryVo;
import com.atguigu.system.mapper.LoginLogMapper;
import com.atguigu.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Author Yjw
 * 2022/11/9 20:50
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr,
                               String msg) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setStatus(status);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(msg);
        loginLogMapper.insert(sysLoginLog);
    }

    //条件分页查询登录日志
    @Override
    public IPage<SysLoginLog> selectPage(Long page, Long limit,
                                         SysLoginLogQueryVo sysLoginLogQueryVo) {
        Page<SysLoginLog> pageParam = new Page(page, limit);
        String username = sysLoginLogQueryVo.getUsername();
        String createTimeBegin = sysLoginLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysLoginLogQueryVo.getCreateTimeEnd();
        //
        QueryWrapper<SysLoginLog> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            wrapper.like("username", username);
        }
        if (!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time", createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le("create_time", createTimeEnd);
        }
        //
        IPage<SysLoginLog> pageModel = loginLogMapper.selectPage(pageParam, wrapper);
        return pageModel;
    }
}
