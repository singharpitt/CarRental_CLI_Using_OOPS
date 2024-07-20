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
            rentals.add(new Rental(car, customer, days));
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
                // Rent a Car functionality (same as before)
                // ...
            } else if (choice == 2) {
                // Return a Car functionality (same as before)
                // ...
            } else if (choice == 3) {
                // Leave Feedback functionality (same as before)
                // ...
            } else if (choice == 4) {
                // View Feedback functionality (same as before)
                // ...
            } else if (choice == 5) {
                // Display Inventory functionality
                displayInventory();
            } else if (choice == 6) {
                // Filter Cars by Brand functionality
                System.out.print("Enter brand to filter: ");
                String brand = scanner.nextLine();
                List<Car> filteredCars = filterByBrand(brand);
                if (!filteredCars.isEmpty()) {
                    System.out.println("Filtered Cars by Brand: " + brand);
                    for (Car car : filteredCars) {
                        System.out.println("Car ID: " + car.getCarId() + ", Model: " + car.getModel());
                    }
                } else {
                    System.out.println("No cars found for brand: " + brand);
                }
            } else if (choice == 7) {
                // Filter Cars by Model functionality
                System.out.print("Enter model to filter: ");
                String model = scanner.nextLine();
                List<Car> filteredCars = filterByModel(model);
                if (!filteredCars.isEmpty()) {
                    System.out.println("Filtered Cars by Model: " + model);
                    for (Car car : filteredCars) {
                        System.out.println("Car ID: " + car.getCarId() + ", Brand: " + car.getBrand());
                    }
                } else {
                    System.out.println("No cars found for model: " + model);
                }
            } else if (choice == 8) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("\nThank you for using the Car Rental System!");
    }
}
public class Main {
    public static void main(String[] args) {
        // Create a new instance of CarRentalSystem
        CarRentalSystem rentalSystem = new CarRentalSystem();

        // Add 20 cars to the rental system with additional details
        Car car1 = new Car("C001", "Toyota", "Camry", 2020, "Black", 60.0, 15000);
        Car car2 = new Car("C002", "Honda", "Accord", 2019, "Silver", 70.0, 18000);
        Car car3 = new Car("C003", "Ford", "Mustang", 2021, "Red", 100.0, 12000);
        Car car4 = new Car("C004", "BMW", "X5", 2022, "White", 120.0, 10000);
        Car car5 = new Car("C005", "Mercedes-Benz", "C-Class", 2020, "Blue", 80.0, 20000);
        Car car6 = new Car("C006", "Audi", "A4", 2021, "Gray", 90.0, 16000);
        Car car7 = new Car("C007", "Volkswagen", "Golf", 2018, "Silver", 50.0, 25000);
        Car car8 = new Car("C008", "Hyundai", "Elantra", 2019, "Black", 55.0, 18000);
        Car car9 = new Car("C009", "Kia", "Sorento", 2020, "Red", 70.0, 22000);
        Car car10 = new Car("C010", "Chevrolet", "Camaro", 2022, "Yellow", 110.0, 14000);
        Car car11 = new Car("C011", "Subaru", "Outback", 2021, "Green", 85.0, 19000);
        Car car12 = new Car("C012", "Tesla", "Model S", 2023, "Silver", 200.0, 8000);
        Car car13 = new Car("C013", "Lexus", "RX", 2020, "White", 95.0, 17000);
        Car car14 = new Car("C014", "Mazda", "CX-5", 2021, "Blue", 65.0, 21000);
        Car car15 = new Car("C015", "Jeep", "Wrangler", 2022, "Gray", 110.0, 15000);
        Car car16 = new Car("C016", "Nissan", "Altima", 2019, "Black", 60.0, 20000);
        Car car17 = new Car("C017", "Ford", "Explorer", 2020, "Red", 80.0, 18000);
        Car car18 = new Car("C018", "Chevrolet", "Equinox", 2021, "Silver", 70.0, 22000);
        Car car19 = new Car("C019", "Toyota", "RAV4", 2018, "Blue", 55.0, 25000);
        Car car20 = new Car("C020", "Honda", "CR-V", 2019, "White", 65.0, 23000);

        // Add cars to the rental system
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);
        rentalSystem.addCar(car6);
        rentalSystem.addCar(car7);
        rentalSystem.addCar(car8);
        rentalSystem.addCar(car9);
        rentalSystem.addCar(car10);
        rentalSystem.addCar(car11);
        rentalSystem.addCar(car12);
        rentalSystem.addCar(car13);
        rentalSystem.addCar(car14);
        rentalSystem.addCar(car15);
        rentalSystem.addCar(car16);
        rentalSystem.addCar(car17);
        rentalSystem.addCar(car18);
        rentalSystem.addCar(car19);
        rentalSystem.addCar(car20);

        // Start the car rental system menu
        rentalSystem.menu();
    }
}

