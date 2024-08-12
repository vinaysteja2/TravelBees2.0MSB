package com.review.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.review.clients.BookingClient;
import com.review.entity.Review;
import com.review.exception.ReviewException;
import com.review.repository.ReviewRepository;

class ReviewServiceImplTest {

	 @Mock
	    private ReviewRepository reviewRepository;

	    @Mock
	    private BookingClient bookingClient;

	    @InjectMocks
	    private ReviewServiceImpl reviewService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetAllReviews_Success() {
	      
	        when(reviewRepository.findAll()).thenReturn(Collections.singletonList(new Review()));

	        List<Review> result = reviewService.getAllReviews();

	        assertFalse(result.isEmpty());
	        assertEquals(1, result.size());
	    }

	    @Test
	    public void testGetAllReviews_EmptyList() {
	       
	        when(reviewRepository.findAll()).thenReturn(Collections.emptyList());

	        ReviewException exception = assertThrows(ReviewException.class, () -> reviewService.getAllReviews());

	        assertEquals("There are no reviews Present", exception.getMessage());
	    }

	    @Test
	    public void testGetReviewById_Success() {
	        Long reviewId = 1L;
	        Review review = new Review(reviewId, 1L, null, null, "Good review", 4);

	       
	        when(reviewRepository.existsById(reviewId)).thenReturn(true);
	        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

	        Review result = reviewService.getReviewById(reviewId);

	        assertNotNull(result);
	        assertEquals(reviewId, result.getId());
	    }

	    @Test
	    public void testGetReviewById_NotFound() {
	        Long reviewId = 1L;

	        
	        when(reviewRepository.existsById(reviewId)).thenReturn(false);

	        ReviewException exception = assertThrows(ReviewException.class, () -> reviewService.getReviewById(reviewId));

	        assertEquals("Provided review is not present", exception.getMessage());
	    }

	    @Test
	    public void testDeleteReview_Success() {
	        Long reviewId = 1L;

	       
	        when(reviewRepository.existsById(reviewId)).thenReturn(true);

	        assertDoesNotThrow(() -> reviewService.deleteReview(reviewId));

	        verify(reviewRepository, times(1)).deleteById(reviewId);
	    }

	    @Test
	    public void testDeleteReview_NotFound() {
	        Long reviewId = 1L;

	        
	        when(reviewRepository.existsById(reviewId)).thenReturn(false);

	        ReviewException exception = assertThrows(ReviewException.class, () -> reviewService.deleteReview(reviewId));

	        assertEquals("Provided review is not present", exception.getMessage());
	        verify(reviewRepository, never()).deleteById(anyLong());
	    }

	    @Test
	    public void testAddReviewToTour_Success() {
	        Long tourId = 1L;
	        Review review = new Review(null, 1L, null, null, "Great tour", 5);

	      
	        when(reviewRepository.save(any(Review.class))).thenReturn(review);

	        Review result = reviewService.addReviewToTour(tourId, review);

	        assertNotNull(result);
	        assertEquals(tourId, result.getTourId());
	    }

	    @Test
	    public void testAddReviewToTouristGuide_Success() {
	        Long touristGuideId = 1L;
	        Review review = new Review(null, 1L, null, null, "Excellent guide", 5);

	      
	        when(reviewRepository.existsByTouristGuideId(touristGuideId)).thenReturn(true);
	        when(reviewRepository.save(any(Review.class))).thenReturn(review);

	        Review result = reviewService.addReviewToTouristGuide(touristGuideId, review);

	        assertNotNull(result);
	        assertEquals(touristGuideId, result.getTouristGuideId());
	    }

	    @Test
	    public void testAddReviewToTouristGuide_NotPresent() {
	        Long touristGuideId = 1L;
	        Review review = new Review(null, 1L, null, null, "Excellent guide", 5);

	      
	        when(reviewRepository.existsByTouristGuideId(touristGuideId)).thenReturn(false);

	        ReviewException exception = assertThrows(ReviewException.class, () -> reviewService.addReviewToTouristGuide(touristGuideId, review));

	        assertEquals("Provided touristGuideId is not present", exception.getMessage());
	    }

	    @Test
	    public void testGetReviewsByTourId_Success() {
	        Long tourId = 1L;
	        List<Review> reviews = Collections.singletonList(new Review(null, 1L, tourId, null, "Good tour", 4));

	     
	        when(reviewRepository.existsByTourId(tourId)).thenReturn(true);
	        when(reviewRepository.findByTourId(tourId)).thenReturn(reviews);

	        List<Review> result = reviewService.getReviewsByTourId(tourId);

	        assertFalse(result.isEmpty());
	        assertEquals(1, result.size());
	    }

	    @Test
	    public void testGetReviewsByTourId_NotPresent() {
	        Long tourId = 1L;

	        
	        when(reviewRepository.existsByTourId(tourId)).thenReturn(false);

	        ReviewException exception = assertThrows(ReviewException.class, () -> reviewService.getReviewsByTourId(tourId));

	        assertEquals("Provided tourId is not present", exception.getMessage());
	    }

	    @Test
	    public void testGetReviewsByTouristGuideId_Success() {
	        Long touristGuideId = 1L;
	        List<Review> reviews = Collections.singletonList(new Review(null, 1L, null, touristGuideId, "Helpful guide", 5));

	      
	        when(reviewRepository.existsByTouristGuideId(touristGuideId)).thenReturn(true);
	        when(reviewRepository.findByTouristGuideId(touristGuideId)).thenReturn(reviews);

	        List<Review> result = reviewService.getReviewsByTouristGuideId(touristGuideId);

	        assertFalse(result.isEmpty());
	        assertEquals(1, result.size());
	    }

	    @Test
	    public void testGetReviewsByTouristGuideId_NotPresent() {
	        Long touristGuideId = 1L;

	  
	        when(reviewRepository.existsByTouristGuideId(touristGuideId)).thenReturn(false);

	        ReviewException exception = assertThrows(ReviewException.class, () -> reviewService.getReviewsByTouristGuideId(touristGuideId));

	        assertEquals("Provided touristGuideId is not present", exception.getMessage());
	    }
}
