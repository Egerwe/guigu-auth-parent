package com.atguigu.system.service;

import com.atguigu.model.system.SysMenu;
import com.atguigu.model.vo.AssginMenuVo;
import com.atguigu.model.vo.RouterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author yjw
 * @since 2022-11-01
 */
public interface SysMenuService extends IService<SysMenu> {

    //菜单列表
    List<SysMenu> findNodes();

    //删除菜单
    void removeMenuById(String id);

    //根据角色创建菜单
    List<SysMenu> findMenuById(String roleId);

    //给角色分配菜单权限
    void doAssign(AssginMenuVo assginMenuVo);

    //根据 userid 查询菜单权限值
    List<RouterVo> getUserMenuList(String id);

    //根据 userid 查询按钮权限值
    List<String> getUserButtonList(String id);
}
