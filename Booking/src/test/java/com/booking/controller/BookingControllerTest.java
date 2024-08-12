package com.booking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.booking.dto.BookingDto;
import com.booking.entity.Booking;
import com.booking.serviceimpl.BookingServiceImpl;

class BookingControllerTest {

	 @InjectMocks
	    private BookingController bookingController;

	    @Mock
	    private BookingServiceImpl bookingService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void getAllBookingsTest() {
	        Booking booking1 = new Booking(1L, 1L, 1L, LocalDate.now(), 2, false,"notBooked",1L);
	        Booking booking2 = new Booking(2L, 2L, 2L, LocalDate.now(), 3, false,"notBooked",1L);
	        List<Booking> bookings = Arrays.asList(booking1, booking2);

	        when(bookingService.getAllBookings()).thenReturn(bookings);

	        ResponseEntity<List<Booking>> response = bookingController.getAllBookings();

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(2, response.getBody().size());
	    }

//	    @Test
//	    public void getBookingByIdTest() {
//	        BookingDto bookingDto = new Booking(1L, 1L, 1L, LocalDate.now(), 2, false,"notBooked",1L);
//
//	        when(bookingService.getBookingById(anyLong())).thenReturn(bookingDto);
//
//	        ResponseEntity<BookingDto> response = bookingController.getBookingById(1L);
//
//	        assertEquals(HttpStatus.OK, response.getStatusCode());
//	        assertEquals(1L, response.getBody().getId());
//	    }
	    

	    @Test
	    public void bookTourTest() {
	        Booking booking = new Booking(1L, 1L, 1L, LocalDate.now(), 2, false,"notBooked",1L);

	        when(bookingService.bookTour(anyLong(), anyLong(), any(LocalDate.class), any(Integer.class))).thenReturn(booking);

	        ResponseEntity<?> response = bookingController.bookTour(1L, booking);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(1L, ((Booking) response.getBody()).getId());
	    }

	    @Test
	    public void updatePaymentStatusTest() {
	        Booking booking = new Booking(1L, 1L, 1L, LocalDate.now(), 2, false,"notBooked",1L);

	        when(bookingService.updatePaymentStatus(anyLong(), any(Boolean.class),anyLong())).thenReturn(booking);

	        ResponseEntity<?> response = bookingController.updatePaymentStatus(1L, true,1L);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(false, ((Booking) response.getBody()).getPaymentStatus());
	    }

	    @Test
	    public void deleteBookingTest() {
	        ResponseEntity<Void> response = bookingController.deleteBooking(1L);

	        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	    }

}
