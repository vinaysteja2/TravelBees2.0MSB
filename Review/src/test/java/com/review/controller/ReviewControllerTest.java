package com.review.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.review.entity.Review;
import com.review.serviceimpl.ReviewServiceImpl;

class ReviewControllerTest {

	   @Mock
	    private ReviewServiceImpl reviewService;

	    @InjectMocks
	    private ReviewController reviewController;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetAllReviews() {
	     
	        when(reviewService.getAllReviews()).thenReturn(Collections.singletonList(new Review()));

	        ResponseEntity<List<Review>> responseEntity = reviewController.getAllReviews();

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(1, responseEntity.getBody().size());
	    }

	    @Test
	    public void testGetReviewById() {
	        Long reviewId = 1L;
	        Review review = new Review(reviewId, 1L, null, null, "Good review", 4);

	       
	        when(reviewService.getReviewById(reviewId)).thenReturn(review);

	        ResponseEntity<Review> responseEntity = reviewController.getReviewById(reviewId);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(reviewId, responseEntity.getBody().getId());
	    }

	 
	    @Test
	    public void testDeleteReview() {
	        Long reviewId = 1L;

	      
	        ResponseEntity<Void> responseEntity = reviewController.deleteReview(reviewId);

	        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	        verify(reviewService, times(1)).deleteReview(reviewId);
	    }

	    @Test
	    public void testAddReviewToTour() {
	        Long tourId = 1L;
	        Review review = new Review(null, 1L, null, null, "Great tour", 5);

	        
	        when(reviewService.addReviewToTour(eq(tourId), any(Review.class))).thenReturn(review);

	        ResponseEntity<?> responseEntity = reviewController.addReviewToTour(tourId, review);

	        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	        assertEquals(review, responseEntity.getBody());
	    }

	    @Test
	    public void testAddReviewToTouristGuide() {
	        Long touristGuideId = 1L;
	        Review review = new Review(null, 1L, null, null, "Excellent guide", 5);

	      
	        when(reviewService.addReviewToTouristGuide(eq(touristGuideId), any(Review.class))).thenReturn(review);

	        ResponseEntity<?> responseEntity = reviewController.addReviewToTouristGuide(touristGuideId, review);

	        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	        assertEquals(review, responseEntity.getBody());
	    }

	    @Test
	    public void testGetReviewsByTourId() {
	        Long tourId = 1L;
	        List<Review> reviews = Collections.singletonList(new Review(null, 1L, tourId, null, "Good tour", 4));

	      
	        when(reviewService.getReviewsByTourId(tourId)).thenReturn(reviews);

	        ResponseEntity<?> responseEntity = reviewController.getReviewsByTourId(tourId);

	        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	        assertEquals(reviews, responseEntity.getBody());
	    }

	    @Test
	    public void testGetReviewsByTouristGuideId() {
	        Long touristGuideId = 1L;
	        List<Review> reviews = Collections.singletonList(new Review(null, 1L, null, touristGuideId, "Helpful guide", 5));

	      
	        when(reviewService.getReviewsByTouristGuideId(touristGuideId)).thenReturn(reviews);

	        ResponseEntity<?> responseEntity = reviewController.getReviewsByTouristGuideId(touristGuideId);

	        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	        assertEquals(reviews, responseEntity.getBody());
	    }
}
