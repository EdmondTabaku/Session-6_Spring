package com.example.session6;

import com.example.session6.dto.BookingDTO;
import com.example.session6.dto.UserDTO;
import com.example.session6.model.Booking;
import com.example.session6.model.Flight;
import com.example.session6.model.User;
import com.example.session6.model.UserDetails;
import com.example.session6.repository.FlightRepository;
import com.example.session6.repository.UserRepository;
import com.example.session6.service.impl.BookingServiceImpl;
import com.example.session6.service.impl.FlightServiceImpl;
import com.example.session6.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class Session6SpringApplication {

	UserServiceImpl userService;
	FlightServiceImpl flightService;
	BookingServiceImpl bookingService;
	UserRepository userRepository;
	FlightRepository flightRepository;


	public Session6SpringApplication(UserServiceImpl userService, FlightServiceImpl flightService, BookingServiceImpl bookingService, UserRepository userRepository, FlightRepository flightRepository) {
		this.userService = userService;
		this.flightService = flightService;
		this.bookingService = bookingService;
		this.userRepository = userRepository;
		this.flightRepository = flightRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Session6SpringApplication.class, args);
	}


	@PostConstruct
	public void run(){
		String[] options = {"1- Save User",
				"2- Find All Users",
				"3- Find User by Id",
				"4- Delete User",
				"5- Save Booking",
				"6- Save Flight",
				"7- Exit",

		};
		Scanner scanner = new Scanner(System.in);
		int option = 1;
		while (option!=7){
			printMenu(options);
			try {
				option = scanner.nextInt();
				switch (option){
					case 1: option1(); break;
					case 2: option2(); break;
					case 3: option3(); break;
					case 4: option4(); break;
					case 5: option5(); break;
					case 6: option6(); break;
					case 7: option = 7; break;
				}
			}
			catch (Exception ex){
				System.out.println("Please enter an integer value between 1 and " + options.length);
				scanner.next();
			}
		}
	}


	// Printing the menu
	public static void printMenu(String[] options){
		for (String option : options){
			System.out.println(option);
		}
		System.out.print("Choose your option : ");
	}

	// Options

	// Saving user
	private void option1() {
		User user = new User();
		UserDetails userDetails = new UserDetails();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the username: ");
		String username = scanner.next();
		user.setUsername(username);

		System.out.println("Enter the password: ");
		String password = scanner.next();
		user.setPassword(password);

		System.out.println("Enter the role: ");
		String role = scanner.next();
		user.setRole(role);

		System.out.println("Enter the first name: ");
		String firstName = scanner.next();
		userDetails.setFirstName(firstName);

		System.out.println("Enter the last name: ");
		String lastName = scanner.next();
		userDetails.setLastName(lastName);

		System.out.println("Enter the phone number: ");
		String phone = scanner.next();
		userDetails.setPhoneNumber(phone);

		System.out.println("Enter the email: ");
		String email = scanner.next();
		userDetails.setEmail(email);

		userDetails.setUser(user);

		user.setUserDetails(userDetails);

		userService.save(user);
		System.out.println("Saved user with username: " + user.getUsername());
	}

	// Finding all users
	private void option2() {
		String[] options1 = {"1- Find users without sorting",
				"2- Find users with sorting - ASC",
				"3- Find users with sorting - DESC",
				"4- Find users with pagination",

		};

		Scanner scanner = new Scanner(System.in);
		int i = 0;
		System.out.println("Choose an option: ");

		while (i < 1 || i > 4){
			printMenu(options1);
			try {
				i = scanner.nextInt();
				switch (i) {
					// Finding all the users without order
					case 1:
						List<UserDTO> userList = userService.findAll();

						for (UserDTO user : userList) {
							System.out.println("Username: " + user.getUsername() +
									"  ||  Role: " + user.getRole());
						}
						break;

					// Finding all the users in ascending order by a specified field
					case 2:
						System.out.println("Enter the field you want to sort by: ");
						String field = scanner.next();

						List<UserDTO> userList1 = userService.findAllSortedASC(field);

						for (UserDTO user : userList1) {
							System.out.println("Username: " + user.getUsername() +
									"  ||  Role: " + user.getRole());
						}
						break;

					// Finding all the users in descending order by a specified field
					case 3:
						System.out.println("Enter the field you want to sort by: ");
						String field1 = scanner.next();

						List<UserDTO> userList2 = userService.findAllSortedDESC(field1);

						for (UserDTO user : userList2) {
							System.out.println("Username: " + user.getUsername() +
									"  ||  Role: " + user.getRole());
						}
						break;

					// Finding users with pagination
					case 4:
						System.out.println("Enter the page size: ");
						int pageSize = scanner.nextInt();

						System.out.println("Enter the number of the page: ");
						int next = scanner.nextInt();

						List<UserDTO> userList3 = userService.findAllWithPagination(next, pageSize);

						for (UserDTO user : userList3) {
							System.out.println("Username: " + user.getUsername() +
									"  ||  Role: " + user.getRole());
						}
						break;
				}
			}
			catch (Exception ex){
				System.out.println("Please enter an integer value between 1 and " + options1.length);
				scanner.next();
			}
		}
	}

	// Finding user by id
	private void option3() {

		Scanner scanner = new Scanner(System.in);
		int id;
		System.out.println("Enter the Id of the user you want to find: ");
		id = scanner.nextInt();
		UserDTO user = userService.findById(id);
		System.out.println("Username: " + user.getUsername() + "  ||  Role: " + user.getRole()
				+ " || First Name: " + user.getFirstName()
				+ " || Last Name: " + user.getLastName()
				+ " || Email: " + user.getEmail()
				+ " || Phone: " + user.getPhone());
	}

	// Deleting User
	private void option4() {

		Scanner scanner = new Scanner(System.in);
		int id;
		System.out.println("Enter the Id of the user you want to delete: ");
		id = scanner.nextInt();
		userService.delete(id);
	}

	// Saving Booking
	private void option5() {

		Booking booking = new Booking();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the id of the user: ");
		int userId = scanner.nextInt();
		User user = userRepository.findById(userId);
		System.out.println("Found user");

		Set<Flight> flights = new HashSet<>();

		System.out.println("Enter the number of flights that you want to book: ");
		int numberOfFlights = scanner.nextInt();
		for (int i = 0; i < numberOfFlights; i++){
			System.out.println("Enter the id of the flight: ");
			int flightId = scanner.nextInt();
			Flight flight1 = flightRepository.findById(flightId);
			flights.add(flight1);
		}

		System.out.println("Created and added flights to list");

		booking.setBookingDate(new Date(2022,06,25));
		System.out.println("Enter the status: ");
		String status = scanner.next();
		booking.setStatus(status);

		booking.setUser(user);
		booking.setFlights(flights);
		System.out.println("Added user and flight");

		BookingDTO booking1 = bookingService.save(booking);
		System.out.println("Saved booking with date: " + booking1.getBookingDate());
	}


	// Saving Flight
	public void option6(){

		Flight flight = new Flight();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the airline: ");
		String airline = scanner.next();
		flight.setAirline(airline);

		System.out.println("Enter the origin: ");
		String origin = scanner.next();
		flight.setOrigin(origin);

		System.out.println("Enter the destination: ");
		String destination = scanner.next();
		flight.setDestination(destination);

		System.out.println("Enter the flight number: ");
		int flightNumber = scanner.nextInt();
		flight.setFlightNumber(flightNumber);

		System.out.println("Enter the status: ");
		String status = scanner.next();
		flight.setStatus(status);

		flightService.save(flight);

	}


}

