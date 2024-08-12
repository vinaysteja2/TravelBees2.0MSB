package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	void deleteById(Long paymentId);
	
	boolean existsByBookingId(Long bookingId);
	boolean existsByPaymentId(String paymentId);
}
