package com.embarkx.firstjobapp.company;

import com.embarkx.firstjobapp.company.Impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies()
    {
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company)
    {
        companyService.updateCompany(id, company);
       return new ResponseEntity<>("Company Updated successfully", HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company)
    {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company Added Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id)
    {
        boolean isDeleted = companyService.deleteCompanyById(id);
        if(isDeleted)
        {
            return new ResponseEntity<>("Company deleted Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Company not Found",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id)
    {
        Company company = companyService.getCompanyById(id);
        if (company != null)
        {
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
