package com.touristguide.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TouristGuideTest {

	  @Test
	    public void createTouristGuideTest() {
	        Long id = 1L;
	        String name = "John Doe";
	        String location = "New York";
	        List<String> languages = Arrays.asList("English", "Spanish");
	        String description = "Experienced tourist guide in New York area";
	        String email = "john.doe@example.com";
	        Double averageRating = 4.5;
	        byte[] photo = new byte[] { 0x01, 0x02, 0x03 };
	        String contactNumber = "1234567890";
	        String aadharNumber = "123456789012";

	        TouristGuide touristGuide = new TouristGuide(id, name, location, languages, description, email,
	                averageRating, photo, contactNumber, aadharNumber);

	        assertEquals(id, touristGuide.getId());
	        assertEquals(name, touristGuide.getName());
	        assertEquals(location, touristGuide.getLocation());
	        assertEquals(languages, touristGuide.getLanguages());
	        assertEquals(description, touristGuide.getDescription());
	        assertEquals(email, touristGuide.getEmail());
	        assertEquals(averageRating, touristGuide.getAverageRating());
	        assertEquals(photo, touristGuide.getPhoto());
	        assertEquals(contactNumber, touristGuide.getContactNumber());
	        assertEquals(aadharNumber, touristGuide.getAadharNumber());
	    }

	    @Test
	    public void updateTouristGuideTest() {
	        Long id = 1L;
	        String name = "John Doe";
	        String location = "New York";
	        List<String> languages = Arrays.asList("English", "Spanish");
	        String description = "Experienced tourist guide in New York area";
	        String email = "john.doe@example.com";
	        Double averageRating = 4.5;
	        byte[] photo = new byte[] { 0x01, 0x02, 0x03 };
	        String contactNumber = "1234567890";
	        String aadharNumber = "123456789012";

	        TouristGuide touristGuide = new TouristGuide(id, name, location, languages, description, email,
	                averageRating, photo, contactNumber, aadharNumber);

	        // Update values
	        touristGuide.setName("Jane Doe");
	        touristGuide.setLocation("Los Angeles");
	        touristGuide.setLanguages(Arrays.asList("English", "French"));
	        touristGuide.setDescription("Experienced tourist guide in Los Angeles area");
	        touristGuide.setEmail("jane.doe@example.com");
	        touristGuide.setAverageRating(4.8);
	        touristGuide.setPhoto(null);
	        touristGuide.setContactNumber("9876543210");
	        touristGuide.setAadharNumber("987654321098");

	        // Assert updated values
	        assertEquals("Jane Doe", touristGuide.getName());
	        assertEquals("Los Angeles", touristGuide.getLocation());
	        assertEquals(Arrays.asList("English", "French"), touristGuide.getLanguages());
	        assertEquals("Experienced tourist guide in Los Angeles area", touristGuide.getDescription());
	        assertEquals("jane.doe@example.com", touristGuide.getEmail());
	        assertEquals(4.8, touristGuide.getAverageRating());
	        assertEquals(null, touristGuide.getPhoto());
	        assertEquals("9876543210", touristGuide.getContactNumber());
	        assertEquals("987654321098", touristGuide.getAadharNumber());
	    }

	    @Test
	    public void toStringTest() {
	        Long id = 1L;
	        String name = "John Doe";
	        String location = "New York";
	        List<String> languages = Arrays.asList("English", "Spanish");
	        String description = "Experienced tourist guide in New York area";
	        String email = "john.doe@example.com";
	        Double averageRating = 4.5;
	        byte[] photo = new byte[] { 0x01, 0x02, 0x03 };
	        String contactNumber = "1234567890";
	        String aadharNumber = "123456789012";

	        TouristGuide touristGuide = new TouristGuide(id, name, location, languages, description, email,
	                averageRating, photo, contactNumber, aadharNumber);

	        String expectedToString = "TouristGuide [id=" + id + ", name=" + name + ", location=" + location +
	                ", languages=" + languages + ", description=" + description + ", email=" + email +
	                ", averageRating=" + averageRating + ", photo=" + Arrays.toString(photo) +
	                ", contactNumber=" + contactNumber + ", aadharNumber=" + aadharNumber + "]";

	        assertEquals(expectedToString, touristGuide.toString());
	    }
}
