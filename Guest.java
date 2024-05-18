import java.util.*;

public class Guest {

    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(lastName, guest.lastName) && Objects.equals(firstName, guest.firstName) && Objects.equals(email, guest.email) && Objects.equals(phoneNumber, guest.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, email, phoneNumber);
    }

    public boolean equalsFullName(Guest g) {
        return Objects.equals(lastName, g.lastName) && Objects.equals(firstName, g.firstName);
    }

    public boolean equalsEmail(Guest g) {
        return Objects.equals(email, g.email);
    }

    public boolean equalsPhoneNumber(Guest g) {
        return Objects.equals(phoneNumber, g.phoneNumber);
    }

    @Override
    public String toString() {
        // TO DO:
        StringBuilder sb = new StringBuilder();
        sb.append("Nume: ");
        sb.append(this.lastName);
        sb.append(' ');
        sb.append(this.firstName);
        sb.append(", Email: ");
        sb.append(this.email);
        sb.append(", Telefon: ");
        sb.append(this.phoneNumber);
        return sb.toString();
    }

    public String fullName() {
        return "[" + this.lastName + " " + this.firstName + "]";
    }

    public void updateGuestInfo(int opt, String update) {
        switch (opt) {
            case 1: {
                this.lastName = update;
                break;
            }
            case 2: {
                this.firstName = update;
                break;
            }
            case 3: {
                this.email = update;
                break;
            }
            case 4: {
                this.phoneNumber = update;
                break;
            }
        }
    }
}