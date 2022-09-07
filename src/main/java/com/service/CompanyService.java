package com.service;

import com.model.Company;

import java.util.List;

public interface CompanyService {
    void saveCompany(Company company);
    void updateCompany(Long id , Company company);
    void deleteCompany(Long id);
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);



}
