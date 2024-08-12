package com.booking.repository;

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

import com.booking.entity.Booking;
import com.booking.serviceimpl.BookingServiceImpl;

class BookingRepositoryTest {

	@Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByTourIdAndBookingDateTest() {
     
        Booking booking1 = new Booking(1L, 1L, 1L, LocalDate.now(), 2, false,"notBooked",1L);
        Booking booking2 = new Booking(2L, 2L, 1L, LocalDate.now(), 3, false,"notBooked",1L);
        List<Booking> bookings = Arrays.asList(booking1, booking2);

        
        when(bookingRepository.findByTourIdAndBookingDateAndPaymentStatusTrue(anyLong(), any(LocalDate.class))).thenReturn(bookings);

        
        List<Booking> result = bookingRepository.findByTourIdAndBookingDateAndPaymentStatusTrue(1L, LocalDate.now());

     
        assertEquals(2, result.size());
    }

    @Test
    public void findByUserIdTest() {
        
        Booking booking1 = new Booking(1L, 1L, 1L, LocalDate.now(), 2, false,"notBooked",1L);
        Booking booking2 = new Booking(2L, 2L, 1L, LocalDate.now(), 3, false,"notBooked",1L);
        List<Booking> bookings = Arrays.asList(booking1, booking2);

     
        when(bookingRepository.findByUserId(anyLong())).thenReturn(bookings);

        
        List<Booking> result = bookingRepository.findByUserId(1L);

     
        assertEquals(2, result.size());
    }

    @Test
    public void existsByUserIdAndTourIdTest() {
     
        Long userId = 1L;
        Long tourId = 1L;

    
        when(bookingRepository.existsByUserIdAndTourId(userId, tourId)).thenReturn(true);

     
        Boolean result =bookingRepository.existsByUserIdAndTourId(userId, tourId);

        // Assert
        assertEquals(true, result);
    }
}
