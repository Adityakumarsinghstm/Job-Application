package com.embarkx.reviewms.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviewByCompanyId(@RequestParam Long companyId){
       return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addReviews(@RequestParam Long companyId, @RequestBody Review review)
    {
        boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved)
            return  new ResponseEntity<>("Review Added Successfully",HttpStatus.OK);
        else
            return  new ResponseEntity<>("Review Not Saved",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId)
    {
        return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review)
    {
        boolean isUpdatedReview = reviewService.updateReview(reviewId,review);
        if(isUpdatedReview)
        {
            return new ResponseEntity<>("Review Updated Successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review Not Updated Successfully",HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview( @PathVariable Long reviewId)
    {
        boolean isDeletedReview = reviewService.deleteReview(reviewId);
        if(isDeletedReview)
        {
            return new ResponseEntity<>("Review Deleted Successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Review Not Deleted Successfully",HttpStatus.NOT_FOUND);
        }
    }
}
