package com.embarkx.firstjobapp.review;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyRepository;
import com.embarkx.firstjobapp.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviewByCompanyId(@PathVariable Long companyId){
       return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String> addReviews(@PathVariable Long companyId, @RequestBody Review review)
    {
        boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved)
            return  new ResponseEntity<>("Review Added Successfully",HttpStatus.OK);
        else
            return  new ResponseEntity<>("Review Not Saved",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId)
    {
        return new ResponseEntity<>(reviewService.getReview(companyId,reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review)
    {
        boolean isUpdatedReview = reviewService.updateReview(companyId,reviewId,review);
        if(isUpdatedReview)
        {
            return new ResponseEntity<>("Review Updated Successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review Not Updated Successfully",HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId)
    {
        boolean isDeletedReview = reviewService.deleteReview(companyId,reviewId);
        if(isDeletedReview)
        {
            return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review Not Deleted Successfully",HttpStatus.NOT_FOUND);
        }
    }
}
