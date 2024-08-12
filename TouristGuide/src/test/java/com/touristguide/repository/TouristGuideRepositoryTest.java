package com.touristguide.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.touristguide.entity.TouristGuide;
import com.touristguide.serviceimpl.TouristGuideServiceImpl;

class TouristGuideRepositoryTest {
	
	 @Mock
	    private TouristGuideRepository touristGuideRepository;

	    @InjectMocks
	    private TouristGuideServiceImpl touristGuideServiceImpl;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testExistsByEmail() {
	        String email = "john.doe@example.com";

	        when(touristGuideRepository.existsByEmail(email)).thenReturn(true);

	        boolean exists = touristGuideRepository.existsByEmail(email);

	        assertTrue(exists);
	        verify(touristGuideRepository, times(1)).existsByEmail(email);
	    }

	    @Test
	    public void testFindByLocationContainingIgnoreCase() {
	        String location = "New York";

	        TouristGuide guide1 = new TouristGuide(1L, "John Doe", "New York", null, null, null, null, null, null, null);
	        TouristGuide guide2 = new TouristGuide(2L, "Jane Smith", "New York", null, null, null, null, null, null, null);
	        List<TouristGuide> guides = List.of(guide1, guide2);

	        when(touristGuideRepository.findByLocationContainingIgnoreCase(location)).thenReturn(guides);

	        List<TouristGuide> foundGuides = touristGuideRepository.findByLocationContainingIgnoreCase(location);

	        assertEquals(2, foundGuides.size());
	        assertEquals("New York", foundGuides.get(0).getLocation());
	        assertEquals("New York", foundGuides.get(1).getLocation());
	        verify(touristGuideRepository, times(1)).findByLocationContainingIgnoreCase(location);
	    }

	    @Test
	    public void testExistsByLocation() {
	        String location = "Los Angeles";

	        when(touristGuideRepository.existsByLocation(location)).thenReturn(true);

	        boolean exists = touristGuideRepository.existsByLocation(location);

	        assertTrue(exists);
	        verify(touristGuideRepository, times(1)).existsByLocation(location);
	    }

}
