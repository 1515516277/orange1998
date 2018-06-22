package com.ming.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ming.entity.EmpEntity;
import com.ming.service.Dempservice;
import com.ming.service.Empservice;
import com.ming.until.SixEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/admin")
@Controller
@SuppressWarnings("all")
public class AdminController {
    @Autowired
    Empservice empservice;
    @Autowired
    Dempservice dempservice;

    @RequestMapping("/emplist")
    public String emplist(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        try {
            //Testclass.addtest(empservice);
            //empservice.insertemp(null);
            PageHelper.startPage(page, 8);
            List<EmpEntity> emps = empservice.selectall();
            for (EmpEntity e : emps) {
                e.setGender(SixEnum.getSix(e.getGender()==null?"":e.getGender()).toString());
            }
            PageInfo pi = new PageInfo(emps, 5);
            System.out.println(pi.toString());
            model.addAttribute("pi", pi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "emplist";
    }
}
