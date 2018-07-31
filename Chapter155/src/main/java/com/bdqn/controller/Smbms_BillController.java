package com.bdqn.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.entity.Smbms_Bill;
import com.bdqn.entity.Smbms_Provider;
import com.bdqn.service.Smbms_BillService;
import com.bdqn.service.Smbms_ProviderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class Smbms_BillController {

    @Resource
    private Smbms_BillService smbms_billService;

    @Resource
    private Smbms_ProviderService smbms_providerService;


    ModelAndView mv = new ModelAndView();

    //查询订单信息
    @RequestMapping("/queryBill")
    public ModelAndView queryBill(Smbms_Bill s,Smbms_Provider p){
        List<Smbms_Bill> smbms_bills = smbms_billService.querySB(s);
        List<Smbms_Provider> smbms_providers = smbms_providerService.querySP(p);
        mv.addObject("BillList",smbms_bills);
        mv.addObject("providerList",smbms_providers);
        mv.setViewName("billlist");
        return mv;
    }


    //查看个人信息
    @RequestMapping("/Seach/{id}")
    public ModelAndView Seach(@PathVariable("id")int uid,Smbms_Bill s){
        s.setId(uid);
        List<Smbms_Bill> smbms_bills = smbms_billService.querySB(s);
        for (Smbms_Bill smbmsbill:smbms_bills) {
            mv.addObject("bill",smbmsbill);
        }
        mv.setViewName("billview");
        return mv;
    }

    //删除
    @RequestMapping("/Delete")
    @ResponseBody
    public Object delete(@RequestParam("uid") int uid, Map<String,Object>data, HttpServletResponse response){
        int i = smbms_billService.delete(uid);
        data = new HashMap<String, Object>();
        if(i>0){
            data.put("delResult","true");
        }else if(i<0){
            data.put("delResult","false");
        }else if(i==0){
            data.put("delResult","notexist");
        }
        return JSON.toJSONString(data);
    }

    //查询列表后跳入修改页面
    @RequestMapping("/Upd/{id}")
    public ModelAndView queryUpd(Smbms_Bill s,@PathVariable("id")int uid,Smbms_Provider p){
        s.setId(uid);
        List<Smbms_Bill> smbms_bills = smbms_billService.querySB(s);
        List<Smbms_Provider> smbms_providers = smbms_providerService.querySP(p);
        for (Smbms_Bill bill:smbms_bills) {
            mv.addObject("bill",bill);
            mv.addObject("providerList",smbms_providers);
        }
        mv.setViewName("billmodify");
        return mv;
    }

    //修改
    @RequestMapping("/updateto")
    public ModelAndView update(Smbms_Bill s,@RequestParam(required = false) int BID){
        s.setId(BID);
        int i = smbms_billService.update(s);
        if(i>0){
            System.out.println("修改成功！");
            return new ModelAndView("redirect:/user/queryBill");
        }else{
            System.out.println("修改失败！");
            mv.setViewName("error");
        }
        return mv;
    }

    //条件查询
    @RequestMapping("/Viewto")
    public ModelAndView Viewto(Smbms_Bill s,@RequestParam(required = false)int queryProviderId,@RequestParam(required = false)int queryIsPayment,String queryProductName){
        s.setProviderId(queryProviderId);
        s.setIsPayment(queryIsPayment);
        s.setProductName(queryProductName);
        mv.addObject("queryIsPayment",queryIsPayment);
        mv.addObject("queryProductName",queryProductName);
        mv.addObject("queryProviderId",queryProviderId);
        List<Smbms_Bill> smbms_bills = smbms_billService.querySB(s);
        mv.addObject("BillList",smbms_bills);
        mv.setViewName("billlist");
        return mv;
    }

    //查询列表后跳入添加页面
    @RequestMapping("/SeachLieto")
    public ModelAndView SeachLieto(Smbms_Provider p){
        List<Smbms_Provider> roleList = smbms_providerService.querySP(p);
        mv.addObject("providerList",roleList);
        mv.setViewName("billadd");
        return mv;
    }

    //添加
    @RequestMapping("/AddBill")
    public ModelAndView AddBill(Smbms_Bill s){
        s.setCreationDate(new Date());
        int i = smbms_billService.add(s);
        if(i>0){
            System.out.println("添加成功");
            return new ModelAndView("redirect:/user/queryBill");
        }else{
            System.out.println("添加失败");
        }
        return mv;
    }


}
