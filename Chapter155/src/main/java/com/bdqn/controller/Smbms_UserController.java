package com.bdqn.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.entity.Smbms_Role;
import com.bdqn.entity.Smbms_User;
import com.bdqn.service.Smbms_RoleService;
import com.bdqn.service.Smbms_UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class Smbms_UserController {

    @Resource
    private Smbms_UserService smbms_userService;

    @Resource
    private Smbms_RoleService smbms_roleService;

    ModelAndView mv = new ModelAndView();


    //登陆
    @RequestMapping("/Login")
    public String queryUser(Smbms_User s,String userCode,String userPassword,HttpSession session){
        List<Smbms_User> userList = smbms_userService.querySU(s);
        for (Smbms_User user:userList) {
            if(userCode.equals(user.getUserCode()) && userPassword.equals(user.getUserPassword())){
                session.setAttribute("userEntity",user);
                return "frame";
            }
        }
        return "error";
    }

    //查询用户
    @RequestMapping("/User")
    public ModelAndView querySmbmsUser(Smbms_User s,Smbms_Role role){

        List<Smbms_User> userList = smbms_userService.querySU(s);
        mv.addObject("userList",userList);
        List<Smbms_Role> roleList = smbms_roleService.querySR(role) ;
        mv.addObject("roleList",roleList);

        mv.setViewName("userlist");
        return mv;
    }

    //删除
    @RequestMapping("/Del")
    public ModelAndView delete(@RequestParam(required = false) int uid, Map<String,Object> data, HttpServletResponse response){
        int i = smbms_userService.delete(uid);
        try {
            PrintWriter out = response.getWriter();
            data = new HashMap<String, Object>();
            if(i>0){
                data.put("delResult","true");
            }else if(i==0){
                data.put("delResult","notexist");
            }else{
                data.put("delResult","false");
            }
            out.write(JSON.toJSONString(data));
            out.flush();
            out.close();
            return new ModelAndView("redirect:/user/User");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mv;
    }

    //修改
    @RequestMapping("/update")
    public ModelAndView update(Smbms_User s,@RequestParam(required = false) int uid){
        s.setId(uid);
        int i = smbms_userService.update(s);
        if(i>0){
            System.out.println("修改成功！");
            return new ModelAndView("redirect:/user/User");
        }else{
            System.out.println("修改失败！");
            mv.setViewName("error");
        }
        return mv;
    }

    //查询列表后跳入修改页面
    @RequestMapping("/Upd")
    public ModelAndView queryUpd(Smbms_User s,@RequestParam(required = false) int uid,Smbms_Role role){
        s.setId(uid);
        List<Smbms_User> userList = smbms_userService.querySU(s);
        List<Smbms_Role> roleList = smbms_roleService.querySR(role);
        for (Smbms_User smbms_user:userList) {
            mv.addObject("smbms_user",smbms_user);
            mv.addObject("roleList",roleList);
        }
        mv.setViewName("usermodify");
        return mv;
    }

    //查看个人信息
    @RequestMapping("/Seach")
    public ModelAndView Seach(Smbms_User s,@RequestParam(required = false) int uid){
        s.setId(uid);
        List<Smbms_User> userList = smbms_userService.querySU(s);
        for (Smbms_User users:userList) {
            mv.addObject("smbms_user",users);
        }
        mv.setViewName("userview");
        return mv;
    }

    //查询列表后跳入添加页面
    @RequestMapping("/SeachLie")
    public ModelAndView SeachLie(Smbms_Role role){
        List<Smbms_Role> roleList = smbms_roleService.querySR(role);
        mv.addObject("roleList",roleList);
        mv.setViewName("useradd");
        return mv;
    }

    //判断用户是否存在
    @RequestMapping("/Count")
    public String Count(Smbms_User s,Map<String,Object> data, HttpServletResponse response){
        try {
            int i = smbms_userService.count(s);
            PrintWriter out = response.getWriter();
            data = new HashMap<String, Object>();
            if(i>0){
                data.put("userCode","exist");
            }else{
                data.put("userCode","true");
            }
            out.write(JSON.toJSONString(data));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //添加
    @RequestMapping("/Insert")
    public ModelAndView insert(Smbms_User s){
        int i = smbms_userService.Add(s);
        if(i>0){
            System.out.println("添加成功");
            return new ModelAndView("redirect:/user/User");
        }else{
            System.out.println("添加失败");
        }
        return mv;
    }

    //条件查询
    @RequestMapping("/View")
    public ModelAndView View(Smbms_User s,@RequestParam(required = false)int ProuserRole,
                             @RequestParam(required = false) String userName){
        s.setUserName(userName);
        s.setUserRole(ProuserRole);

        mv.addObject("userName",userName);
        mv.addObject("ProuserRole",ProuserRole);

        List<Smbms_User> userList = smbms_userService.querySU(s);
        mv.addObject("userList",userList);
        mv.setViewName("userlist");
        return mv;
    }

    //修改密码中转站
    @RequestMapping("/UpdPwd")
    public ModelAndView updpwd(){
        mv.setViewName("pwdmodify");
        return mv;
    }

    //查询之前的密码
    @RequestMapping("/updatePwd")
    public ModelAndView updatePwd(Smbms_User u,HttpSession session,Map<String,Object>date,HttpServletResponse response,String oldpassword){
        Smbms_User smbms_user = (Smbms_User)session.getAttribute("userEntity");
        date = new HashMap<String, Object>();
        try {
            PrintWriter out = response.getWriter();
            if(smbms_user==null){
                date.put("result","sessionerror");
            }else if(oldpassword.equals("") || oldpassword == ""){ //判断旧密码框为空
                date.put("result","error");
            }else if(oldpassword.equals(smbms_user.getUserPassword())){//判断密码相同的时候
                date.put("result","true");
            }else {
                date.put("result","false");
            }
            out.print(JSON.toJSONString(date));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mv;
    }

    //修改密码
    @RequestMapping("/updatePassword")
    public ModelAndView updatePassword(Smbms_User u,HttpSession session,String newpassword){
        Smbms_User smbms_user = (Smbms_User)session.getAttribute("userEntity");
        u.setId(smbms_user.getId());
        u.setUserPassword(newpassword);
        int i = smbms_userService.update(u);
        if(i>0){
            return new ModelAndView("redirect:/user/User");
        }else {
            mv.setViewName("pwdmodify");
        }
        return mv;
    }
}
