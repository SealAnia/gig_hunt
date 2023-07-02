package com.example.gig_hunt.service;

import com.example.gig_hunt.model.entity.Company;

public interface CompanyService extends DefaultService<Company> {

    Company readByName(String name);
    Company readByRegistrationNumber(Long registrationNumber);
    Company findCompanyInfoByUser(String nickname);

}
