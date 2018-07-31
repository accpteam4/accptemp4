package com.bdqn.dao;

import com.bdqn.entity.Smbms_User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Smbms_UserMapper {
    //查询所有数据
    List<Smbms_User> querySU(Smbms_User s);

    //查询受影响的行数
    int count(Smbms_User s);

    //添加
    int Add(Smbms_User s);

    //删除
    int delete(@Param("uid") Integer id);

    //修改
    int update(Smbms_User s);
}
