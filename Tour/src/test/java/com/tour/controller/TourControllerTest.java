package com.tour.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tour.entity.Tour;
import com.tour.serviceimpl.TourServiceImpl;

class TourControllerTest {

	   @Mock
	    private TourServiceImpl tourServiceImpl;

	    @InjectMocks
	    private TourController tourController;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

 @Test
 void testAddTourWithTouristGuideId() {
	        Long touristGuideId = 1L;
	Tour tour = new Tour(1L,"Historical Tour","landmarks.",99.99,8,2L,"photo".getBytes(),"Brief tour.",4.5);

	        when(tourServiceImpl.addTourWithTouristGuideId(touristGuideId, tour)).thenReturn(tour);

	        ResponseEntity<?> response = tourController.addTourWithTouristGuideId(touristGuideId, tour);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(tour, response.getBody());
	    }

 
 @Test
 void testDeleteTour() {
     Long tourId = 1L;

     doNothing().when(tourServiceImpl).deleteTour(tourId);

     ResponseEntity<Void> response = tourController.deleteTour(tourId);

     assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
     verify(tourServiceImpl, times(1)).deleteTour(tourId);
 }
	
 @Test
 void testDeleteTourByTouristGuideId() {
     Long touristGuideId = 1L;

     doNothing().when(tourServiceImpl).deleteTourByTouristGuideId(touristGuideId);

     ResponseEntity<Void> response = tourController.deleteTourByTouristGuideId(touristGuideId);

     assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
     verify(tourServiceImpl, times(1)).deleteTourByTouristGuideId(touristGuideId);
 }	

 @Test
 void testUpdateTour() {
     Long tourId = 1L;
     Tour updatedTour = new Tour(1L,"Historical Tour","landmarks.",99.99,8,2L,"photo".getBytes(),"Brief tour.",4.5);

     doNothing().when(tourServiceImpl).updateTour(tourId, updatedTour);

     ResponseEntity<Void> response = tourController.updateTour(tourId, updatedTour);

     assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
 }

 @Test
 void testGetAllTours() {
    
	 Tour tour1 = new Tour(1L,"Historical Tour","landmarks.",99.99,8,2L,"photo".getBytes(),"Brief tour.",4.5);
 
	 Tour tour2 = new Tour(2L,"Historical Tour","landmarks.",98.99,8,2L,"photo".getBytes(),"Brief tour.",3.5);

     List<Tour> tours = Arrays.asList(tour1, tour2);

     when(tourServiceImpl.getAllTours()).thenReturn(tours);

     ResponseEntity<List<Tour>> response = tourController.getAllTours();

     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(tours, response.getBody());
 }

 
 @Test
 void testGetTourById() {
     Long tourId = 1L;
  
     Tour tour = new Tour(2L,"Historical Tour","landmarks.",98.99,8,2L,"photo".getBytes(),"Brief tour.",3.5);

     when(tourServiceImpl.getTourById(tourId)).thenReturn(tour);

     ResponseEntity<?> response = tourController.getTourById(tourId);

     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(tour, response.getBody());
 }
 
 @Test
 void testGetToursByTouristGuideId() {
     Long touristGuideId = 1L;
     
     Tour tour1 = new Tour(1L,"Historical Tour","landmarks.",99.99,8,2L,"photo".getBytes(),"Brief tour.",4.5);
     
	 Tour tour2 = new Tour(2L,"Historical Tour","landmarks.",98.99,8,2L,"photo".getBytes(),"Brief tour.",3.5);


     List<Tour> tours = Arrays.asList(tour1, tour2);

     when(tourServiceImpl.getToursByTouristGuideId(touristGuideId)).thenReturn(tours);

     ResponseEntity<?> response = tourController.getToursByTouristGuideId(touristGuideId);

     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(tours, response.getBody());
 }

}
