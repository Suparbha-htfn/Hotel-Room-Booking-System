
public class Room {

    // Data members
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isBooked;

    // Constructor
    public Room(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isBooked = false;
    }

    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isBooked() {
        return isBooked;
    }

    // Method to display room details
    public void displayRoom() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Price Per Night: Rs. " + pricePerNight);
        System.out.println("Status: " + (isBooked ? "Booked" : "Available"));
        System.out.println("--------------------------------");
    }

    // Method to update booking status
    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }
}
