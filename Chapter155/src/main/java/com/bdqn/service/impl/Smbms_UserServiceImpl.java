package com.bdqn.service.impl;

import com.bdqn.dao.Smbms_UserMapper;
import com.bdqn.entity.Smbms_User;
import com.bdqn.service.Smbms_UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Smbms_UserServiceImpl implements Smbms_UserService {

    @Resource
    private Smbms_UserMapper smbms_userMapper;

    public List<Smbms_User> querySU(Smbms_User s) {
        List<Smbms_User> userList = null;
        try {
            userList = smbms_userMapper.querySU(s);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return userList;
        }
    }

    public int count(Smbms_User s) {
        int i =0;
        try {
            i=smbms_userMapper.count(s);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return i;
        }
    }

    public int Add(Smbms_User s) {
        int i =0;
        try {
            i=smbms_userMapper.Add(s);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return i;
        }
    }

    public int delete(Integer id) {
        int i =0;
        try {
            i=smbms_userMapper.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return i;
        }
    }

    public int update(Smbms_User s) {
        int i =0;
        try {
            i=smbms_userMapper.update(s);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return i;
        }
    }
}
