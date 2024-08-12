package com.gateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	public static final List<String> openApiEndPoints = List.of("/auth/register", "/auth/token", "/eureka","/auth/AdminRegister",
			"/auth/send-otp","/auth/validate-otp","/bookings","/auth/forgotPassword","/auth/resetPassword");
//	,"/tourist-guides/locations",
//	"/tours","/bookings","/payments","/reviews"

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndPoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));
}

/*----------ROLES BASED -----------*/
//package com.gateway.filter;
//
//import java.util.List;
//import java.util.function.Predicate;
//
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RouteValidator {
//
//    public static final List<String> openApiEndPoints = List.of("/auth/register", "/auth/token", "/eureka");
//
//    // Define role-specific endpoints
//    public static final List<String> adminEndPoints = List.of("/tourist-guides/**"); // replace with actual admin endpoints
//    public static final List<String> userEndPoints = List.of("/tours/**");   // replace with actual user endpoints
//
//    public Predicate<ServerHttpRequest> isSecured = request -> openApiEndPoints.stream(
//            .noneMatch(uri -> request.getURI().getPath().contains(uri));
//
//    public Predicate<ServerHttpRequest> isAdmin = request -> adminEndPoints.stream()
//            .anyMatch(uri -> request.getURI().getPath().startsWith(uri));
//
//    public Predicate<ServerHttpRequest> isUser = request -> userEndPoints.stream()
//            .anyMatch(uri -> request.getURI().getPath().startsWith(uri));
//}
