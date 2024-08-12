package com.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

	//  List<Booking> findByTourIdAndBookingDate(Long tourId, LocalDate bookingDate);
	
	 List<Booking> findByTourIdAndBookingDateAndPaymentStatusTrue(Long tourId, LocalDate bookingDate);
	 List<Booking> findByTourIdAndBookingDateAndPaymentStatusFalse(Long tourId, LocalDate bookingDate);
		
	List<Booking> findByUserId(Long userId);
	    
	   Boolean existsByUserIdAndTourId(Long userId, Long tourId);
	   
	   Boolean existsByPaymentId(Long paymentId);
	   
	   Booking findByPaymentId(Long paymentId);
	    
}
