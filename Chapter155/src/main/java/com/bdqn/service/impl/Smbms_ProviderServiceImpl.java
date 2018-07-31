package com.bdqn.service.impl;

import com.bdqn.dao.Smbms_ProviderMapper;
import com.bdqn.entity.Smbms_Provider;
import com.bdqn.service.Smbms_ProviderService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Smbms_ProviderServiceImpl implements Smbms_ProviderService{

    @Resource
    private Smbms_ProviderMapper smbms_providerMapper;

    public List<Smbms_Provider> querySP(Smbms_Provider p) {
        return smbms_providerMapper.querySP(p);
    }

    public int addProvider(Smbms_Provider p) {
        return smbms_providerMapper.addProvider(p);
    }

    public int updateProvider(Smbms_Provider p) {
        return smbms_providerMapper.updateProvider(p);
    }

    public int deleteProvider(Integer id) {
        return smbms_providerMapper.deleteProvider(id);
    }

    public int count(Integer id) {
        return smbms_providerMapper.count(id);
    }
}
