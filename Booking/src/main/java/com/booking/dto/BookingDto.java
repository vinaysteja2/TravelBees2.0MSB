package com.booking.dto;

import java.time.LocalDate;

import com.booking.external.Tour;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class BookingDto {

	    private Long id;

	    private Long userId;  // The user who made the booking
	    
	    private LocalDate bookingDate;  // Date and time when the booking was made

	    private boolean paymentStatus;
	    
	    private Long paymentId;
	    
	    
	    private int numberOfPersons; // Number of persons booked for the tour

	    private Tour tour;  // The tour that was booked

		public BookingDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		
		public BookingDto(Long id, Long userId, LocalDate bookingDate, boolean paymentStatus, Long paymentId,
				int numberOfPersons, Tour tour) {
			super();
			this.id = id;
			this.userId = userId;
			this.bookingDate = bookingDate;
			this.paymentStatus = paymentStatus;
			this.paymentId = paymentId;
			this.numberOfPersons = numberOfPersons;
			this.tour = tour;
		}


		public Long getUserId() {
			return userId;
		}


		public void setUserId(Long userId) {
			this.userId = userId;
		}


		public boolean getPaymentStatus() {
			return paymentStatus;
		}


		public void setPaymentStatus(boolean paymentStatus) {
			this.paymentStatus = paymentStatus;
		}


		public Long getPaymentId() {
			return paymentId;
		}


		public void setPaymentId(Long paymentId) {
			this.paymentId = paymentId;
		}


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDate getBookingDate() {
			return bookingDate;
		}

		public void setBookingDate(LocalDate bookingDate) {
			this.bookingDate = bookingDate;
		}

		public int getNumberOfPersons() {
			return numberOfPersons;
		}

		public void setNumberOfPersons(int numberOfPersons) {
			this.numberOfPersons = numberOfPersons;
		}

		public Tour getTour() {
			return tour;
		}

		public void setTour(Tour tour) {
			this.tour = tour;
		}
	    
	    
}
