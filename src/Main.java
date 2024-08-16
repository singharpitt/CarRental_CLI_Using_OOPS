import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carId;
    private String brand;
    private String model;
    private int year; // New: Year of the car
    private String color; // New: Color of the car
    private double basePricePerDay;
    private double mileage; // New: Mileage of the car
    private boolean isAvailable;

    public Car(String carId, String brand, String model, int year, String color, double basePricePerDay, double mileage) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.basePricePerDay = basePricePerDay;
        this.mileage = mileage;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public double getBasePricePerDay() {
        return basePricePerDay;
    }

    public double getMileage() {
        return mileage;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class Customer {
    private String customerId;
    private String name;
    private List<Feedback> feedbacks;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.feedbacks = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void addFeedback(int rating, String comments) {
        Feedback feedback = new Feedback(rating, comments);
        feedbacks.add(feedback);
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}

class Feedback {
    private int rating;
    private String comments;

    public Feedback(int rating, String comments) {
        this.rating = rating;
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public double getTotalCost() {
        return car.calculatePrice(days);
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("===== Rental Receipt =====\n");
        receipt.append("Customer Name: ").append(customer.getName()).append("\n");
        receipt.append("Customer ID: ").append(customer.getCustomerId()).append("\n");
        receipt.append("--------------------------\n");
        receipt.append("Car Details:\n");
        receipt.append("Car ID: ").append(car.getCarId()).append("\n");
        receipt.append("Brand: ").append(car.getBrand()).append("\n");
        receipt.append("Model: ").append(car.getModel()).append("\n");
        receipt.append("Year: ").append(car.getYear()).append("\n");
        receipt.append("Color: ").append(car.getColor()).append("\n");
        receipt.append("Mileage: ").append(car.getMileage()).append(" miles\n");
        receipt.append("--------------------------\n");
        receipt.append("Rental Duration: ").append(days).append(" days\n");
        receipt.append("Base Price per Day: $").append(car.getBasePricePerDay()).append("\n");
        receipt.append("--------------------------\n");
        receipt.append("Total Cost: $").append(getTotalCost()).append("\n");
        receipt.append("==========================\n");
        return receipt.toString();
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            Rental rental = new Rental(car, customer, days);
            rentals.add(rental);
            System.out.println("Car rented successfully!");
            // Generate and print the receipt
            System.out.println(rental.generateReceipt());
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
            System.out.println("Car returned successfully!");
        } else {
            System.out.println("Car was not rented.");
        }
    }

    public void collectFeedback(Customer customer, int rating, String comments) {
        customer.addFeedback(rating, comments);
    }

    public void displayFeedback(Customer customer) {
        List<Feedback> feedbacks = customer.getFeedbacks();
        if (feedbacks.isEmpty()) {
            System.out.println("No feedback available for " + customer.getName());
        } else {
            System.out.println("Feedback for " + customer.getName() + ":");
            for (Feedback feedback : feedbacks) {
                System.out.println("Rating: " + feedback.getRating());
                System.out.println("Comments: " + feedback.getComments());
                System.out.println();
            }
        }
    }

    public void displayInventory() {
        System.out.println("===== Current Inventory =====");
        for (Car car : cars) {
            System.out.println("Car ID: " + car.getCarId());
            System.out.println("Brand: " + car.getBrand());
            System.out.println("Model: " + car.getModel());
            System.out.println("Year: " + car.getYear()); // Display year
            System.out.println("Color: " + car.getColor()); // Display color
            System.out.println("Base Price per Day: $" + car.getBasePricePerDay());
            System.out.println("Mileage: " + car.getMileage() + " miles");
            System.out.println("Availability: " + (car.isAvailable() ? "Available" : "Not Available"));
            System.out.println("--------------------------------");
        }
    }

    public List<Car> filterByBrand(String brand) {
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                filteredCars.add(car);
            }
        }
        return filteredCars;
    }

    public List<Car> filterByModel(String model) {
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model)) {
                filteredCars.add(car);
            }
        }
        return filteredCars;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Leave Feedback");
            System.out.println("4. View Feedback");
            System.out.println("5. Display Inventory");
            System.out.println("6. Filter Cars by Brand");
            System.out.println("7. Filter Cars by Model");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter Customer ID: ");
                String customerId = scanner.nextLine();
                Customer customer = null;
                for (Customer c : customers) {
                    if (c.getCustomerId().equalsIgnoreCase(customerId)) {
                        customer = c;
                        break;
                    }
                }

                if (customer != null) {
                    System.out.print("Enter Car ID: ");
                    String carId = scanner.nextLine();
                    Car car = null;
                    for (Car c : cars) {
                        if (c.getCarId().equalsIgnoreCase(carId)) {
                            car = c;
                            break;
                        }
                    }

                    if (car != null) {
                        System.out.print("Enter rental duration (days): ");
                        int days = scanner.nextInt();
                        rentCar(car, customer, days);
                    } else {
                        System.out.println("Car ID not found.");
                    }
                } else {
                    System.out.println("Customer ID not found.");
                }
            } else if (choice == 2) {
                System.out.print("Enter Car ID to return: ");
                String carId = scanner.nextLine();
                Car car = null;
                for (Car c : cars) {
                    if (c.getCarId().equalsIgnoreCase(carId)) {
                        car = c;
                        break;
                    }
                }

                if (car != null) {
                    returnCar(car);
                } else {
                    System.out.println("Car ID not found.");
                }
            } else if (choice == 3) {
                System.out.print("Enter Customer ID: ");
                String customerId = scanner.nextLine();
                Customer customer = null;
                for (Customer c : customers) {
                    if (c.getCustomerId().equalsIgnoreCase(customerId)) {
                        customer = c;
                        break;
                    }
                }

                if (customer != null) {
                    System.out.print("Enter Rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Comments: ");
                    String comments = scanner.nextLine();
                    collectFeedback(customer, rating, comments);
                } else {
                    System.out.println("Customer ID not found.");
                }
            } else if (choice == 4) {
                System.out.print("Enter Customer ID to view feedback: ");
                String customerId = scanner.nextLine();
                Customer customer = null;
                for (Customer c : customers) {
                    if (c.getCustomerId().equalsIgnoreCase(customerId)) {
                        customer = c;
                        break;
                    }
                }

                if (customer != null) {
                    displayFeedback(customer);
                } else {
                    System.out.println("Customer ID not found.");
                }
            } else if (choice == 5) {
                displayInventory();
            } else if (choice == 6) {
                System.out.print("Enter Brand to filter by: ");
                String brand = scanner.nextLine();
                List<Car> filteredCars = filterByBrand(brand);
                if (filteredCars.isEmpty()) {
                    System.out.println("No cars found for the specified brand.");
                } else {
                    for (Car car : filteredCars) {
                        System.out.println("Car ID: " + car.getCarId());
                        System.out.println("Brand: " + car.getBrand());
                        System.out.println("Model: " + car.getModel());
                        System.out.println("Year: " + car.getYear()); // Display year
                        System.out.println("Color: " + car.getColor()); // Display color
                        System.out.println("Base Price per Day: $" + car.getBasePricePerDay());
                        System.out.println("Mileage: " + car.getMileage() + " miles");
                        System.out.println("Availability: " + (car.isAvailable() ? "Available" : "Not Available"));
                        System.out.println("--------------------------------");
                    }
                }
            } else if (choice == 7) {
                System.out.print("Enter Model to filter by: ");
                String model = scanner.nextLine();
                List<Car> filteredCars = filterByModel(model);
                if (filteredCars.isEmpty()) {
                    System.out.println("No cars found for the specified model.");
                } else {
                    for (Car car : filteredCars) {
                        System.out.println("Car ID: " + car.getCarId());
                        System.out.println("Brand: " + car.getBrand());
                        System.out.println("Model: " + car.getModel());
                        System.out.println("Year: " + car.getYear()); // Display year
                        System.out.println("Color: " + car.getColor()); // Display color
                        System.out.println("Base Price per Day: $" + car.getBasePricePerDay());
                        System.out.println("Mileage: " + car.getMileage() + " miles");
                        System.out.println("Availability: " + (car.isAvailable() ? "Available" : "Not Available"));
                        System.out.println("--------------------------------");
                    }
                }
            } else if (choice == 8) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a new instance of CarRentalSystem
        CarRentalSystem rentalSystem = new CarRentalSystem();

        // Add some cars and customers
        Car car1 = new Car("C001", "Toyota", "Camry", 2020, "Black", 60.0, 15000);
        Car car2 = new Car("C002", "Honda", "Accord", 2019, "Silver", 70.0, 18000);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);

        Customer customer1 = new Customer("CU001", "John Doe");
        rentalSystem.addCustomer(customer1);

        // Rent a car and display the receipt
        rentalSystem.rentCar(car1, customer1, 5);  // Renting car1 to customer1 for 5 days

        // Start the car rental system menu
        rentalSystem.menu();
    }
}
