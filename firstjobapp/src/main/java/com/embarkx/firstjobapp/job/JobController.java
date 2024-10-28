package com.embarkx.firstjobapp.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class JobController {
    @Autowired
    private JobService jobService;

//    public JobController(JobService jobService) {
//        this.jobService = jobService;
//    }

    @GetMapping("/jobs")
public ResponseEntity<List<Job>> findAll()
{
    return ResponseEntity.ok(jobService.findAll());
}

@PostMapping("/jobs")
public ResponseEntity<String> createJobs(@RequestBody Job job)
{
    jobService.createJob(job);
    return new ResponseEntity<>("Job Added Succesfully",HttpStatus.OK);
}

@GetMapping("/jobs/{id}")
public ResponseEntity<Job> getJobById(@PathVariable Long id)
{
    Job job = jobService.getJobById(id);
    if(job !=null)
    {
        return new ResponseEntity<>(job, HttpStatus.OK);
    }
    else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
@DeleteMapping("/jobs/{id}")
public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
             return new ResponseEntity<>("Job deleted successfully ",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
@PutMapping("/jobs/{id}")
public ResponseEntity<String> updateJobById(@PathVariable Long id,@RequestBody Job job)
{
    boolean updated = jobService.updateJobById(id,job);
    if(updated)
       return new ResponseEntity<>("Job updated Successfully...",HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}
