package com.api;

import com.model.Company;
import com.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/companies")

public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.service = companyService;
    }

    @GetMapping()
    private String getAllCompanies(Model model,Long id) {
        List<Company> companies = service.getAllCompanies();
        model.addAttribute( "companies", companies);
        return "company/companies";
    }


    @GetMapping("/addCompany")
    private String addCompany(Model model) {
        model.addAttribute( "company", new Company());
        return "company/addCompany";
    }

    @PostMapping("/saveCompany")
    private String saveCompany(@ModelAttribute("company") Company company) {
        service.saveCompany(company);
        return "redirect:/companies";
    }


    @GetMapping("/getCompany/{companyId}")
    private String getCompanyById(@PathVariable("companyId") Long id, Model model) {
        model.addAttribute("company", service.getCompanyById(id));
        System.out.println(service.getCompanyById(id));
        return "/innerPage";
    }


    @GetMapping("/edit/{id}")
    private String updateCompany(@PathVariable("id") Long id, Model model) {
        model.addAttribute("company", service.getCompanyById(id));
        return "company/updateCompany";
    }


    @RequestMapping("/{id}/update")
    private String saveUpdateCompany(@PathVariable Long id ,
                                     @ModelAttribute("company") Company company) {
        service.updateCompany(id, company);
        return "redirect:/companies";
    }


    @RequestMapping("/{id}")
    private String deleteCompany(@PathVariable("id") Long id) {
        service.deleteCompany(id);
        return "redirect:/companies";
    }
}
