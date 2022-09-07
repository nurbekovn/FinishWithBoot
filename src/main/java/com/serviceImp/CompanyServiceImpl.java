package com.serviceImp;

import com.model.Company;
import com.repository.CompanyRepository;
import com.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Long id, Company company) {
        Company company1 = companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found "));
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        companyRepository.save(company1);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.delete(companyRepository.findById(id).get());
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("not found")
        );
    }
}
