package com.bdqn.service;

import com.bdqn.entity.Smbms_Provider;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface Smbms_ProviderService {
    List<Smbms_Provider> querySP(Smbms_Provider p);

    //添加
    int addProvider(Smbms_Provider p);

    //修改
    int updateProvider(Smbms_Provider p);

    //删除
    int deleteProvider(@RequestParam("id")Integer id);

    //查询供应商下是否有订单
    int count(@RequestParam("id")Integer id);
}
