package com.example.gig_hunt.controller;

import com.example.gig_hunt.exception.NumberOfSymbolsDifferentFromRequiredException;
import com.example.gig_hunt.model.entity.Company;
import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.service.impl.CompanyServiceImpl;
import com.example.gig_hunt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyRestController {

    private final CompanyServiceImpl companyService;
    private final UserServiceImpl userService;

    @Autowired
    public CompanyRestController(CompanyServiceImpl companyService, UserServiceImpl userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping(value = "")
    public List<Company> getAllCompanies() {
        return companyService.getAll();
    }

    @GetMapping(value = "/{companyId}")
    public Company getCompanyById(@PathVariable Long companyId) {
        return companyService.readById(companyId);
    }

    //FINDS A COMPANY BY NAME
    @GetMapping(value = "/name={name}")
    public Company findCompanyByName(@PathVariable String name) {
        return companyService.readByName(name);
    }

    //FINDS A COMPANY BY REGISTRATION NUMBER
    @GetMapping(value = "/number={registrationNumber}")
    public Company findCompnyByRegistrationNumber(@PathVariable Long registrationNumber) {
        return companyService.readByRegistrationNumber(registrationNumber);
    }

    //FINDS INFO ABOUT COMPANY OWNER
    @GetMapping(value = "/by_user")
    public Company getCompanyByUser(@RequestParam(value = "nickname") String nickname) {
        return companyService.findCompanyInfoByUser(nickname);
    }

    @PostMapping(value = "/")
    public void createCompany(@RequestBody Company company) throws NumberOfSymbolsDifferentFromRequiredException {
        try {
            companyService.createOrUpdate(company);
        } catch (NullPointerException e) {
            e.printStackTrace();
            e.getMessage();
        } catch(NumberOfSymbolsDifferentFromRequiredException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @PutMapping(value = "/{companyId}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long companyId,
                                                 @RequestBody Company company) throws NumberOfSymbolsDifferentFromRequiredException {
        Company updatedCompany = companyService.readById(companyId);
        try {
            updatedCompany.setName(company.getName());
            updatedCompany.setRegistrationNumber(company.getRegistrationNumber());
            var user = company.getUser();
            updatedCompany.setUser((Master) user);
            companyService.createOrUpdate(updatedCompany);
        } catch(NullPointerException e) {
            e.printStackTrace();
            e.getMessage();
        } catch(NumberOfSymbolsDifferentFromRequiredException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping(value = "/{companyId}")
    public void deleteCompany(@PathVariable Long companyId) {
        companyService.delete(companyId);
    }

}
