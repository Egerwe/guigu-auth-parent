package com.atguigu.system;

import com.atguigu.model.system.SysRole;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author Yjw
 * 2022/10/27 21:24
 */
@SpringBootTest
public class SysRoleServiceTest {

    @Resource
    private SysRoleService sysRoleService;

    @Test
    public void finaAll() {
        List<SysRole> list = sysRoleService.list();
        System.out.println("list = " + list);
    }
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");
        boolean result = sysRoleService.save(sysRole);
        System.out.println("result = " + result);
    }
    @Test
    public void update() {
        SysRole role = sysRoleService.getById(1);
        role.setDescription("系统管理员");
        boolean result = sysRoleService.updateById(role);
        System.out.println("result = " + result);
    }
    @Test
    public void remove() {
        sysRoleService.removeById(1);
    }
    @Test
    public void select() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_code", "SYSTEM");
        List<SysRole> list = sysRoleService.list(wrapper);
        System.out.println("list = " + list);
    }
}
