package com.bdqn.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.entity.Smbms_Provider;
import com.bdqn.service.Smbms_ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class Smbms_ProviderController {

    @Resource
    private Smbms_ProviderService smbms_providerService;


    ModelAndView mv = new ModelAndView();

    //查询所有信息
    @RequestMapping("/queryProvider")
    public ModelAndView queryProvider(Smbms_Provider p,@RequestParam(required = false)Integer pid,@RequestParam(required = false)String proCode,@RequestParam(required = false)String proName){
        p.setId(pid);
        p.setProCode(proCode);
        p.setProName(proName);
        mv.addObject("proCode",proCode);
        mv.addObject("proName",proName);
        List<Smbms_Provider> smbms_providers = smbms_providerService.querySP(p);
        mv.addObject("providerList",smbms_providers);
        mv.setViewName("providerlist");
        if(pid!=null){
            for (Smbms_Provider smbms_provider:smbms_providers) {
                mv.addObject("provider",smbms_provider);
                mv.setViewName("providermodify");
            }
        }
        return mv;
    }

    //删除
    @RequestMapping("/deleteProvider")
    public ModelAndView deleteProvider(@RequestParam("pid") int pid, Map<String,Object> data, HttpServletResponse response){
        int a = smbms_providerService.count(pid);
        try {
            PrintWriter out = response.getWriter();
            data = new HashMap<String, Object>();
            if(a>0){
                data.put("delResult",a);
            }else{
                int i = smbms_providerService.deleteProvider(pid);
                if(i>0){
                    data.put("delResult","true");
                }else if(i==0){
                    data.put("delResult","notexist");
                }else{
                    data.put("delResult","false");
                }
            }
            out.write(JSON.toJSONString(data));
            out.flush();
            out.close();
            return new ModelAndView("redirect:/user/queryProvider");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mv;
    }

    //查看个人信息
    @RequestMapping("/viewProvider/{id}")
    public ModelAndView SeachThere(@PathVariable("id")int pid, Smbms_Provider p){
        p.setId(pid);
        List<Smbms_Provider> smbms_providers = smbms_providerService.querySP(p);
        for (Smbms_Provider smbms_provider:smbms_providers) {
            mv.addObject("provider",smbms_provider);
        }
        mv.setViewName("providerview");
        return mv;
    }

    //修改
    @RequestMapping("/updateProvider")
    public ModelAndView updateProvider(Smbms_Provider p,@RequestParam(required = true)int pid){
        p.setId(pid);
        int i = smbms_providerService.updateProvider(p);
        if(i>0){
            return new ModelAndView("redirect:/user/queryProvider");
        }else{
            mv.addObject("providermodify");
        }
        return mv;
    }

    //中转站
    @RequestMapping("/Provider")
    public ModelAndView Provider(){
        mv.setViewName("provideradd");
        return mv;
    }

    //添加
    @RequestMapping("/AddProvider")
    public ModelAndView AddProvider(Smbms_Provider p){
        int i = smbms_providerService.addProvider(p);
        if(i>0){
            return new ModelAndView("redirect:/user/queryProvider");
        }else{
            mv.setViewName("provideradd");
        }
        return mv;
    }
}
