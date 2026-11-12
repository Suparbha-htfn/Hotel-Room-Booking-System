
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class HotelBookingSystem {

    // ArrayList to store hotel rooms
    static ArrayList<Room> rooms = new ArrayList<>();

    // Scanner for user input
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        // Load previously saved rooms
        loadRooms();

        do {
            System.out.println("\n===== HOTEL ROOM BOOKING SYSTEM =====");
            System.out.println("1. Add Room");
            System.out.println("2. Display All Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. Check-out Room");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        addRoom();
                        break;

                    case 2:
                        displayRooms();
                        break;

                    case 3:
                        bookRoom();
                        break;

                    case 4:
                        checkOutRoom();
                        break;

                    case 5:
                        saveRooms();
                        System.out.println("Exiting Hotel Booking System...");
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                choice = 0;
            }

        } while (choice != 5);

        sc.close();
    }


    // Method to add a new room
    public static void addRoom() {

        try {
            System.out.print("Enter Room Number: ");
            int number = sc.nextInt();

            sc.nextLine();

            // Check for duplicate room number
            for (Room r : rooms) {
                if (r.getRoomNumber() == number) {
                    System.out.println("Room number already exists!");
                    return;
                }
            }

            System.out.print("Enter Room Type: ");
            String type = sc.nextLine();

            System.out.print("Enter Price Per Night: ");
            double price = sc.nextDouble();

            if (price <= 0) {
                System.out.println("Price must be greater than zero!");
                return;
            }

            Room r = new Room(number, type, price);
            rooms.add(r);

            System.out.println("Room added successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input! Please enter correct details.");
            sc.nextLine();
        }
    }


    // Method to display all rooms
    public static void displayRooms() {

        if (rooms.isEmpty()) {
            System.out.println("No rooms available!");
            return;
        }

        System.out.println("\n===== ROOM DETAILS =====");

        for (Room r : rooms) {
            r.displayRoom();
        }
    }


    // Method to book a room
    public static void bookRoom() {

        try {
            System.out.print("Enter Room Number to Book: ");
            int number = sc.nextInt();

            boolean found = false;

            for (Room r : rooms) {

                if (r.getRoomNumber() == number) {

                    found = true;

                    if (r.isBooked()) {
                        throw new RoomNotAvailableException(
                            "Room is already booked!"
                        );
                    }

                    r.setBooked(true);
                    System.out.println("Room booked successfully!");
                    break;
                }
            }

            if (!found) {
                System.out.println("Room not found!");
            }

        } catch (RoomNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid room number.");
            sc.nextLine();
        }
    }


    // Method to check-out a room
    public static void checkOutRoom() {

        try {
            System.out.print("Enter Room Number for Check-out: ");
            int number = sc.nextInt();

            boolean found = false;

            for (Room r : rooms) {

                if (r.getRoomNumber() == number) {

                    found = true;

                    if (r.isBooked()) {
                        r.setBooked(false);
                        System.out.println("Check-out successful!");
                    } else {
                        System.out.println("Room is not currently booked!");
                    }

                    break;
                }
            }

            if (!found) {
                System.out.println("Room not found!");
            }

        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid room number.");
            sc.nextLine();
        }
    }


    // Method to save room details into a file
    public static void saveRooms() {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("rooms.txt"))) {

            for (Room r : rooms) {

                bw.write(r.getRoomNumber() + "," +
                         r.getRoomType() + "," +
                         r.getPricePerNight() + "," +
                         r.isBooked());

                bw.newLine();
            }

            System.out.println("Room details saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving room details: " + e.getMessage());
        }
    }


    // Method to load room details from a file
    public static void loadRooms() {

        File file = new File("rooms.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length != 4) {
                    continue;
                }

                int number = Integer.parseInt(data[0]);
                String type = data[1];
                double price = Double.parseDouble(data[2]);
                boolean booked = Boolean.parseBoolean(data[3]);

                Room r = new Room(number, type, price);
                r.setBooked(booked);

                rooms.add(r);
            }

            System.out.println("Room details loaded successfully!");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading room details: " + e.getMessage());
        }
    }

}
