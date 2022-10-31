package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.AssginRoleVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.exception.GuiguException;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Author Yjw
 * 2022/10/27 22:17
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @ApiOperation("获取用户的角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }
    @ApiOperation("用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
    //7 批量删除---------------------------------------------------------------------
    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids) {
        boolean result = sysRoleService.removeByIds(ids);
        if (result) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
    //6 修改-最终修改-----------------------------------------------------------------
    @ApiOperation("修改")
    @PostMapping("/update")
    public Result update(@RequestBody SysRole sysRole) {
        boolean result = sysRoleService.updateById(sysRole);
        if (result) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
    //5 修改-根据id查询---------------------------------------------------------------
    @ApiOperation("根据id查询")
    @PostMapping("/findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id) {
        SysRole role = sysRoleService.getById(id);
        return Result.ok(role);
    }
    //4 添加------------------------------------------------------------------------
    /*
     * @RequestBody 不能使用 get 提交方式
     * 传递 json 格式数据，把 json 格式数据封装到对象里面
     */
    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result saveRole(@RequestBody SysRole sysRole) {
        boolean result = sysRoleService.save(sysRole);
        if (result) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
    //3 条件分页查询------------------------------------------------------------------
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysRoleQueryVo sysRoleQueryVo) {
        //创建 page 对象
        Page<SysRole> pageParam = new Page<>(page, limit);
        //调用 service 方法
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        return Result.ok(pageModel);
    }
    //2 逻辑删除接口------------------------------------------------------------------
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("/remove/{id}")
    public Result removeRole(@PathVariable Long id) {
        boolean result = sysRoleService.removeById(id);
        if (result) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
    //1 查询所有记录------------------------------------------------------------------
    @ApiOperation("查询所有记录")
    @GetMapping("/findAll")
    public Result findAllRole() {
        try {
            int i = 9/0;
        } catch (Exception e) {
            //手动抛出
            throw new GuiguException(20001, "执行了自定义异常处理");
        }
        //调用service
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

}
