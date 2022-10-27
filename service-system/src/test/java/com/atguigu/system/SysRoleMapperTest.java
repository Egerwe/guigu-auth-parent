package com.atguigu.system;

import com.atguigu.model.system.SysRole;
import com.atguigu.system.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Author Yjw
 * 2022/10/25 23:56
 */
@SpringBootTest
public class SysRoleMapperTest {

    @Resource
    private SysRoleMapper sysRoleMapper;

    //7 条件删除
    @Test
    public void testDel() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", "用户管理员");
        int result = sysRoleMapper.delete(wrapper);
        System.out.println("result = " + result);
    }
    //6 条件查询
    @Test
    public void testSelect() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置
        wrapper.eq("role_name", "用户管理员");
        //调用
        List<SysRole> list = sysRoleMapper.selectList(wrapper);
        System.out.println("list = " + list);
    }
    //5 批量删除
    @Test
    public void delAll() {
        int result = sysRoleMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println("result = " + result);
    }
    //4 删除
    @Test
    public void deleteId() {
        int result = sysRoleMapper.deleteById(1);
        System.out.println("result = " + result);
    }
    //3 修改
    @Test
    public void update() {
        //根据id修改
        SysRole role = sysRoleMapper.selectById(1);
        //设置修改值
        role.setDescription("系统管理员尚硅谷");
        //调用
        sysRoleMapper.updateById(role);
    }
    //2 添加
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色");
        sysRole.setRoleCode("testManger");
        sysRole.setDescription("测试角色");
        int result = sysRoleMapper.insert(sysRole);
        System.out.println("result = " + result);
    }
    //1 查询所有
    @Test
    public void findAll() {
        List<SysRole> list = sysRoleMapper.selectList(null);
        for (SysRole sysRole : list) {
            System.out.println(sysRole);
        }
    }
}
