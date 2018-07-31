package com.bdqn.service;

import com.bdqn.entity.Smbms_Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Smbms_RoleService {
    //查询
    List<Smbms_Role> querySR(Smbms_Role role);

    //添加
    int addRole(Smbms_Role role);

    //修改
    int updateRole(Smbms_Role role);

    //删除
    int deleteRole(@Param("id")Integer id);

    //查询受影响的行数
    int count(Smbms_Role role);
}
