package com.booking.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bookings1")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @NotNull(message = "User ID is required")
    private Long userId;  // The user who made the booking

  //  @NotNull(message = "Tour ID is required")
    private Long tourId;  // The tour that was booked

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;  // Date and time when the booking was made

    @Min(value = 1, message = "Number of persons must be at least 1")
    private int numberOfPersons; // Number of persons booked for the tour

    private boolean paymentStatus = false;
    
    private String bookingStatus="notBooked";
    
    private Long paymentId;
   
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Booking(Long id, Long userId, Long tourId,
			@NotNull(message = "Booking date is required") LocalDate bookingDate,
			@Min(value = 1, message = "Number of persons must be at least 1") int numberOfPersons,
			boolean paymentStatus, String bookingStatus, Long paymentId) {
		super();
		this.id = id;
		this.userId = userId;
		this.tourId = tourId;
		this.bookingDate = bookingDate;
		this.numberOfPersons = numberOfPersons;
		this.paymentStatus = paymentStatus;
		this.bookingStatus = bookingStatus;
		this.paymentId = paymentId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTourId() {
		return tourId;
	}

	public void setTourId(Long tourId) {
		this.tourId = tourId;
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

	public boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}


	public Long getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}


	@Override
	public String toString() {
		return "Booking [id=" + id + ", userId=" + userId + ", tourId=" + tourId + ", bookingDate=" + bookingDate
				+ ", numberOfPersons=" + numberOfPersons + ", paymentStatus=" + paymentStatus + ", bookingStatus="
				+ bookingStatus + ", paymentId=" + paymentId + "]";
	}


	

	

	
    
}
