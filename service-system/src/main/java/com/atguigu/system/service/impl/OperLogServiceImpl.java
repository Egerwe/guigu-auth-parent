package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.vo.SysOperLogQueryVo;
import com.atguigu.system.mapper.OperLogMapper;
import com.atguigu.system.service.OperLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Author Yjw
 * 2022/11/9 21:47
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @Resource
    private OperLogMapper operLogMapper;

    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        operLogMapper.insert(sysOperLog);
    }

    @Override
    public IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo) {

        Page<SysOperLog> pageParam = new Page<>(page, limit);
        String title = sysOperLogQueryVo.getTitle();
        String operName = sysOperLogQueryVo.getOperName();
        String createTimeBegin = sysOperLogQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysOperLogQueryVo.getCreateTimeEnd();

        QueryWrapper<SysOperLog> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(operName)) {
            wrapper.like("oper_name", operName);
        }
        if (!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time", createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le("create_time", createTimeEnd);
        }
        IPage<SysOperLog> sysOperLogPage = operLogMapper.selectPage(pageParam, wrapper);
        return sysOperLogPage;
    }
}
