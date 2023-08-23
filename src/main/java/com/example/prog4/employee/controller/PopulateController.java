package com.example.prog4.employee.controller;


import com.example.prog4.employee.config.CompanyConf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PopulateController {


    @ModelAttribute("companyConf")
    private CompanyConf populateCompanyConf(){
        return new CompanyConf();
    }
}