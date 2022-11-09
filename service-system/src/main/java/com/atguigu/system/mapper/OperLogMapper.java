package com.atguigu.system.mapper;

import com.atguigu.model.system.SysLoginLog;
import com.atguigu.model.system.SysOperLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yjw
 * @since 2022-11-9
 */
@Repository
@Mapper
public interface OperLogMapper extends BaseMapper<SysOperLog> {

}
