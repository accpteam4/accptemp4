package com.bdqn.service.impl;

import com.bdqn.dao.Smbms_RoleMapper;
import com.bdqn.entity.Smbms_Role;
import com.bdqn.service.Smbms_RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Smbms_RoleServiceImpl implements Smbms_RoleService {

    @Resource
    private Smbms_RoleMapper smbms_roleMapper;

    public List<Smbms_Role> querySR(Smbms_Role role) {
        List<Smbms_Role> roleList = null;
        try {
            roleList = smbms_roleMapper.querySR(role);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return roleList;
        }
    }

    public int addRole(Smbms_Role role) {
        return smbms_roleMapper.addRole(role);
    }

    public int updateRole(Smbms_Role role) {
        return smbms_roleMapper.updateRole(role);
    }

    public int deleteRole(Integer id) {
        return smbms_roleMapper.deleteRole(id);
    }

    public int count(Smbms_Role role) {
        return smbms_roleMapper.count(role);
    }

}
