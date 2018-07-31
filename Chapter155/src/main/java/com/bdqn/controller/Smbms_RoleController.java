package com.bdqn.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.entity.Smbms_Role;
import com.bdqn.service.Smbms_RoleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class Smbms_RoleController {

    @Resource
    private Smbms_RoleService smbms_roleService;

    ModelAndView mv = new ModelAndView();

    //查询所有信息
    @RequestMapping("/ShowList")
    public ModelAndView ShowRole(Smbms_Role r,@RequestParam(required = false)Integer rid){
        r.setId(rid);
        List<Smbms_Role> roleList = smbms_roleService.querySR(r);
        mv.addObject("roleList",roleList);
        mv.setViewName("rolelist");
        if(rid!=null){
            for (Smbms_Role role:roleList) {
                mv.addObject("role",role);
                mv.setViewName("rolemodify");
            }
        }
        return mv;
    }

    //添加中转站
    @RequestMapping("/addZhuan")
    public ModelAndView addShow(){
        mv.setViewName("roleadd");
        return mv;
    }

    //添加
    @RequestMapping("/addRole")
    public ModelAndView deleteRole(Smbms_Role role){
        role.getId();
        int i = smbms_roleService.addRole(role);
        if(i>0){
            return new ModelAndView("redirect:/user/ShowList");
        }else{
            mv.setViewName("roleadd");
        }
        return mv;
    }

    //修改
    @RequestMapping("/updateRole")
    public ModelAndView updateRole(Smbms_Role role,@RequestParam(required = false)Integer rid){
        role.setId(rid);
        int i = smbms_roleService.updateRole(role);
        if(i>0){
            System.out.println("修改成功！");
            return new ModelAndView("redirect:/user/ShowList");
        }else{
            System.out.println("修改失败");
            mv.setViewName("rolemodify");
        }
        return mv;
    }

    //判断用户是否存在
    @RequestMapping("/CountRole")
    @ResponseBody
    public Object CountRole(Smbms_Role role){
        Map<String,Object> data = new HashMap<String, Object>();
        int i = smbms_roleService.count(role);
        data = new HashMap<String, Object>();
        if(i>0){
            data.put("role","exist");
        }else{
            data.put("role","true");
        }
        return JSON.toJSONString(data);
    }

    //删除
    @RequestMapping("/delRole")
    @ResponseBody
    public Object deleteRole(@RequestParam(required = false)Integer id, HttpServletResponse response,Smbms_Role role){
        Map<String,Object> data = new HashMap<String, Object>();
        int count = smbms_roleService.count(role);
        if(count>0){
            data.put("delRole",count);
        }else{
            int i = smbms_roleService.deleteRole(id);
            if(i>0){
                System.out.println("删除成功");
                data.put("delRole","true");
            }else if(i==0){
                System.out.println("角色不存在");
                data.put("delRole","notexist");
            }else{
                System.out.println("删除失败");
                data.put("delRole","false");
            }
        }
        return JSON.toJSONString(data);
    }

}
