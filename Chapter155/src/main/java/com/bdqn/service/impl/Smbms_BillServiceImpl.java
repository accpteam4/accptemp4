package com.bdqn.service.impl;

import com.bdqn.dao.Smbms_BillMapper;
import com.bdqn.entity.Smbms_Bill;
import com.bdqn.service.Smbms_BillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Smbms_BillServiceImpl implements Smbms_BillService {

    @Resource
    private Smbms_BillMapper smbms_billMapper;


    public List<Smbms_Bill> querySB(Smbms_Bill sb) {
        return smbms_billMapper.querySB(sb);
    }

    public int delete(Integer id) {
        return smbms_billMapper.delete(id);
    }

    public int add(Smbms_Bill s) {
        return smbms_billMapper.add(s);
    }

    public int update(Smbms_Bill s) {
        return smbms_billMapper.update(s);
    }
}
