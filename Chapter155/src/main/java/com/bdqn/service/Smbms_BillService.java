package com.bdqn.service;

import com.bdqn.entity.Smbms_Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface Smbms_BillService {
    List<Smbms_Bill> querySB(Smbms_Bill sb);

    //删除订单
    int delete(@Param("id") Integer id);

    //添加订单
    int add(Smbms_Bill s);

    //修改订单
    int update(Smbms_Bill s);
}
