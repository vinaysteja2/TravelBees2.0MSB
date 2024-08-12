package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.entity.Payment;
import com.payment.serviceimpl.PaymentServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
    private PaymentServiceImpl paymentService;

    @PostMapping("/bookings/{bookingId}")
    public ResponseEntity<?> addPaymentForBooking(@PathVariable Long bookingId, @RequestBody Payment payment) {
      
            Payment createdPayment = paymentService.addPaymentForBooking(bookingId, payment);
          
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
     
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
       
            Payment payment = paymentService.getPaymentById(id);
            if (payment != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(payment);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
      
    }

//    @DeleteMapping("/{paymentId}")
//    public ResponseEntity<Void> deletePayment(@PathVariable String paymentId) {
//        try {
//            paymentService.deletePayment(paymentId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }}
}