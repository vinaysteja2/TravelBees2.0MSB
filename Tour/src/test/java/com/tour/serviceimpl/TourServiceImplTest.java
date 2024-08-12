package com.tour.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tour.entity.Tour;
import com.tour.exception.TourException;
import com.tour.repository.TourRepository;


public class TourServiceImplTest {

    @Mock
    private TourRepository tourRepository;

    @InjectMocks
    private TourServiceImpl tourService;

    private Tour testTour;

    @BeforeEach
    void setUp() {
       
    	 MockitoAnnotations.openMocks(this);
        testTour = new Tour(1L, "Test Tour", "Test description", 100.0, 2, 1L, null, "Short description", 4.5);
    }

    @Test
    void testAddTourWithTouristGuideId() {
        when(tourRepository.save(any(Tour.class))).thenReturn(testTour);

        Tour addedTour = tourService.addTourWithTouristGuideId(1L, testTour);

        assertNotNull(addedTour);
        assertEquals(testTour.getId(), addedTour.getId());
        assertEquals(testTour.getName(), addedTour.getName());
    }
    
    @Test
    void testDeleteTour() {
        
        when(tourRepository.existsById(1L)).thenReturn(true);

        //assertDoesNotThrow(() -> tourService.deleteTour(1L));
        tourService.deleteTour(1L);
        verify(tourRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetToursByTouristGuideId() {
        
        when(tourRepository.existsByTouristGuideId(1L)).thenReturn(true);

        
        List<Tour> mockTours = new ArrayList<>();
        mockTours.add(new Tour(1L, "Tour 1", "Description 1", 100.0, 2, 1L, null, "Short Description 1", 4.5));
        mockTours.add(new Tour(2L, "Tour 2", "Description 2", 150.0, 3, 1L, null, "Short Description 2", 4.7));

      
        when(tourRepository.findByTouristGuideId(1L)).thenReturn(mockTours);

      
        List<Tour> retrievedTours = tourService.getToursByTouristGuideId(1L);

        
        assertNotNull(retrievedTours);
        assertEquals(2, retrievedTours.size());
        assertEquals("Tour 1", retrievedTours.get(0).getName());
        assertEquals("Tour 2", retrievedTours.get(1).getName());
    }
    
    @Test
    void testUpdateTour() {
     
        when(tourRepository.existsById(1L)).thenReturn(true);
        when(tourRepository.findById(1L)).thenReturn(Optional.of(testTour));
        when(tourRepository.save(any(Tour.class))).thenReturn(testTour);

        Tour updatedTour = new Tour(1L, "Updated Tour", "Updated description", 150.0, 3, 1L, null, "Updated Short description", 4.7);

        assertDoesNotThrow(() -> tourService.updateTour(1L, updatedTour));

        assertEquals(updatedTour.getName(), testTour.getName());
        assertEquals(updatedTour.getDescription(), testTour.getDescription());
        assertEquals(updatedTour.getPrice(), testTour.getPrice());
    }
    
    @Test
    void testGetAllTours() {
       
        List<Tour> tourList = new ArrayList<>();
        tourList.add(testTour);

        when(tourRepository.findAll()).thenReturn(tourList);

        List<Tour> retrievedTours = tourService.getAllTours();

        assertFalse(retrievedTours.isEmpty());
        assertEquals(1, retrievedTours.size());
        assertEquals(testTour.getName(), retrievedTours.get(0).getName());
    }
    
    @Test
    void testGetTourById() {
        // Mock behavior of tourRepository.existsById() and tourRepository.findById()
        when(tourRepository.existsById(1L)).thenReturn(true);
        when(tourRepository.findById(1L)).thenReturn(Optional.of(testTour));

        Tour retrievedTour = tourService.getTourById(1L);

        assertNotNull(retrievedTour);
        assertEquals(testTour.getName(), retrievedTour.getName());
    }
   
    @Test
    void testDeleteTourByTouristGuideId() {
      
        Long touristGuideId = 1L;

        
 
        List<Tour> mockTours = new ArrayList<>();
        mockTours.add(new Tour(1L, "Tour 1", "Description 1", 100.0, 2, touristGuideId, null, "Short Description 1", 4.5));
        mockTours.add(new Tour(2L, "Tour 2", "Description 2", 150.0, 3, touristGuideId, null, "Short Description 2", 4.7));

       
        when(tourRepository.existsByTouristGuideId(touristGuideId)).thenReturn(true);
        
        
        when(tourRepository.findByTouristGuideId(touristGuideId)).thenReturn(mockTours);

        
        assertDoesNotThrow(() -> tourService.deleteTourByTouristGuideId(touristGuideId));

     
        verify(tourRepository, times(mockTours.size())).delete(any());
    }
   

}
