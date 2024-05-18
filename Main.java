import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bine ati venit! Introduceti numarul maxim de participanti la eveniment:");
        int size = scanner.nextInt();
        scanner.nextLine();

        GuestsList list = new GuestsList(size);

        showCommands();

        boolean running = true;
        while (running) {
            String command = scanner.nextLine();

            switch (command) {
                case "help":
                    showCommands();
                    break;
                case "add":
                    addNewGuest(scanner, list);
                    break;
                case "check":
                    checkGuest(scanner, list);
                    break;
                case "remove":
                    removeGuest(scanner, list);
                    break;
                case "update":
                    updateGuest(scanner, list);
                    break;
                case "guests":
                    list.showGuestsList();
                    break;
                case "waitlist":
                    list.showWaitingList();
                    break;
                case "available":
                    System.out.println("Numarul de locuri ramase: " + list.numberOfAvailableSpots());
                    break;
                case "guests_no":
                    System.out.println("Numarul de participanti: " + list.numberOfGuests());
                    break;
                case "waitlist_no":
                    System.out.println("Dimensiunea listei de asteptare: " + list.numberOfPeopleWaiting());
                    break;
                case "subscribe_no":
                    System.out.println("Numarul total de persoane: " + list.numberOfPeopleTotal());
                    break;
                case "search":
                    searchList(scanner, list);
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    scanner.close();
                    running = false;
                    break;
                default:
                    System.out.println("Comanda introdusa nu este valida.");
                    System.out.println("Incercati inca o data.");

            }
        }
    }

    private static void showCommands() {
        System.out.println("help         - Afiseaza aceasta lista de comenzi");
        System.out.println("add          - Adauga o noua persoana (inscriere)");
        System.out.println("check        - Verifica daca o persoana este inscrisa la eveniment");
        System.out.println("remove       - Sterge o persoana existenta din lista");
        System.out.println("update       - Actualizeaza detaliile unei persoane");
        System.out.println("guests       - Lista de persoane care participa la eveniment");
        System.out.println("waitlist     - Persoanele din lista de asteptare");
        System.out.println("available    - Numarul de locuri libere");
        System.out.println("guests_no    - Numarul de persoane care participa la eveniment");
        System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
        System.out.println("subscribe_no - Numarul total de persoane inscrise");
        System.out.println("search       - Cauta toti invitatii conform sirului de caractere introdus");
        System.out.println("quit         - Inchide aplicatia");
    }

    private static void addNewGuest(Scanner sc, GuestsList list) {
        System.out.println("Se adauga o noua persoana…");
        System.out.println("Introduceti numele de familie:");
        String lastName = sc.nextLine();
        System.out.println("Introduceti prenumele:");
        String firstName = sc.nextLine();
        System.out.println("Introduceti email:");
        String email = sc.nextLine();
        System.out.println("Introduceti numar de telefon (format „+40733386463“):");
        String phoneNumber = sc.nextLine();
        Guest g = new Guest(lastName, firstName, email, phoneNumber);
        list.add(g);
    }

    private static void checkGuest(Scanner sc, GuestsList list) {
        System.out.println("Dupa ce criteriu doriti sa faceti cautarea?");
        System.out.println("\t1. Nume si Prenume\n\t2. Email\n\t3. Telefon");
        String input = sc.nextLine();

        switch (input) {
            case "1": {
                Guest guest = searchByName(sc, list);
                if (null != guest) {
                    System.out.println(guest.toString());
                }
                break;
            }
            case "2": {
                Guest guest = searchByEmail(sc, list);
                if (null != guest) {
                    System.out.println(guest.toString());
                }
                break;
            }
            case "3": {
                Guest guest = searchByPhone(sc, list);
                if (null != guest) {
                    System.out.println(guest.toString());
                }
                break;
            }
            default:
                System.out.println("Input invalid.");
        }
    }

    private static Guest searchByName(Scanner sc, GuestsList list) {
        System.out.println("Introduceti numele: ");
        String lastName = sc.nextLine();
        System.out.println("Introduceti prenumele: ");
        String firstName = sc.nextLine();
        Guest g = list.searchParticipantsList(firstName, lastName);
        if (null == g) {
            g = list.searchWaitingList(firstName, lastName);
            if (null == g) {
                System.out.println("Persoana " + lastName + " " + firstName + " nu este inscrisa la eveniment.");
            }
        }
        return g;
    }

    private static Guest searchByEmail(Scanner sc, GuestsList list) {
        System.out.println("Introduceti email-ul: ");
        String email = sc.nextLine();
        Guest g = list.searchParticipantsList(2, email);
        if (null == g) {
            g = list.searchWaitingList(2, email);
            if (null == g) {
                System.out.println("Persoana nu este inscrisa la eveniment.");
            }
        }
        return g;
    }

    private static Guest searchByPhone(Scanner sc, GuestsList list) {
        System.out.println("Introduceti numarul de telefon: ");
        String phoneNumber = sc.nextLine();
        Guest g = list.searchParticipantsList(3, phoneNumber);
        if (null == g) {
            g = list.searchWaitingList(3, phoneNumber);
            if (null == g) {
                System.out.println("Persoana nu este inscrisa la eveniment.");
            }
        }
        return g;
    }

    private static void removeGuest(Scanner sc, GuestsList list) {
        System.out.println("Dupa ce criteriu doriti sa faceti cautarea?");
        System.out.println("\t1. Nume si Prenume\n\t2. Email\n\t3. Telefon");
        String input = sc.nextLine();

        switch (input) {
            case "1": {
                System.out.println("Introduceti numele: ");
                String lastName = sc.nextLine();
                System.out.println("Introduceti prenumele: ");
                String firstName = sc.nextLine();
                boolean remove = list.remove(firstName, lastName);
                if (remove) {
                    System.out.println("Persoana a fost stearsa cu succes din lista.");
                } else System.out.println("Persoana nu a fost gasita pe nicio lista.");
                break;
            }
            case "2": {
                System.out.println("Introduceti email-ul: ");
                String email = sc.nextLine();
                boolean remove = list.remove(2, email);
                if (remove) {
                    System.out.println("Persoana a fost stearsa cu succes din lista.");
                } else System.out.println("Persoana nu a fost gasita pe nicio lista.");
                break;
            }
            case "3": {
                System.out.println("Introduceti numarul de telefon: ");
                String phoneNumber = sc.nextLine();
                boolean remove = list.remove(3, phoneNumber);
                if (remove) {
                    System.out.println("Persoana a fost stearsa cu succes din lista.");
                } else System.out.println("Persoana nu a fost gasita pe nicio lista.");
                break;
            }
            default:
                System.out.println("Input invalid.");
        }
    }

    private static void updateGuest(Scanner sc, GuestsList list) {
        System.out.println("Dupa ce criteriu doriti sa faceti cautarea?");
        System.out.println("\t1. Nume si Prenume\n\t2. Email\n\t3. Telefon");
        String input = sc.nextLine();

        switch (input) {
            case "1": {
                Guest guest = searchByName(sc, list);
                if (null != guest) {
                    String s = updateCriteria(sc);
                    updateGuestFields(guest, s, sc);
                } else System.out.println("Persoana nu este inscrisa la eveniment.");
                break;
            }
            case "2": {
                Guest guest = searchByEmail(sc, list);
                if (null != guest) {
                    String s = updateCriteria(sc);
                    updateGuestFields(guest, s, sc);
                } else System.out.println("Persoana nu este inscrisa la eveniment.");
                break;
            }
            case "3": {
                Guest guest = searchByPhone(sc, list);
                if (null != guest) {
                    String s = updateCriteria(sc);
                    updateGuestFields(guest, s, sc);
                } else System.out.println("Persoana nu este inscrisa la eveniment.");
                break;
            }
            default:
                System.out.println("Input invalid.");
        }
    }

    private static void updateGuestFields(Guest guest, String input, Scanner sc) {
        switch (input) {
            case "1": {
                System.out.println("Introduceti noul nume: ");
                String s = sc.nextLine();
                guest.updateGuestInfo(1, s);
                System.out.println("Numele a fost updatat. Noul nume este: " + guest.fullName());
                break;
            }
            case "2": {
                System.out.println("Introduceti noul prenume: ");
                String s = sc.nextLine();
                guest.updateGuestInfo(2, s);
                System.out.println("Numele a fost updatat. Noul nume este: " + guest.fullName());
                break;
            }
            case "3": {
                System.out.println("Introduceti noul email: ");
                String s = sc.nextLine();
                guest.updateGuestInfo(3, s);
                System.out.println("Email-ul a fost updatat. Noul email este: " + guest.getEmail());
                break;
            }
            case "4": {
                System.out.println("Introduceti noul numar de telefon: ");
                String s = sc.nextLine();
                guest.updateGuestInfo(4, s);
                System.out.println("Numarul de telefon a fost updatat. Noul numar de telefon este: " + guest.getPhoneNumber());
                break;
            }
            default:
                System.out.println("Input invalid");
        }
    }

    private static String updateCriteria(Scanner sc) {
        System.out.println("Alegeti campul pe care doriti sa il actualizati: ");
        System.out.println("\t1. Nume\n\t2. Prenume\n\t3. Email\n\t4. Numar de telefon");
        String input = sc.nextLine();
        return input;
    }

    private static void searchList(Scanner sc, GuestsList list) {
        System.out.println("Introduceti cuvantul-cheie dupa care doriti sa incepeti cautarea: ");
        String input = sc.nextLine();
        List<Guest> display = list.partialSearch(input);
        if (display.size() == 0) {
            System.out.println("Nothing found");
            return;
        }
        int index = 1;
        for (Guest g : display) {
            System.out.println(index + ". " + g.toString());
            index++;
        }
    }
}