import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GuestsList {

    private final int guestsCapacity;
    private ArrayList<Guest> participantsList;
    private LinkedList<Guest> waitingList;

    public GuestsList(int guestsCapacity) {
        this.guestsCapacity = guestsCapacity;
        this.participantsList = new ArrayList<>(guestsCapacity);
        this.waitingList = new LinkedList<>();
    }

    public int add(Guest g) {
        if (!isOnTheListAlready(g)) {
            if (participantsList.size() < guestsCapacity) {
                participantsList.add(g);
                addParticipantConfirmMessage(g);
                return 0;
            } else {
                waitingList.add(g);
                addGuestListConfirmMessage(g, waitingList.size());
                return waitingList.size();
            }
        }
        return -1;
    }

    private void addParticipantConfirmMessage(Guest g) {
        System.out.println(g.fullName() + " Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
    }

    private void addGuestListConfirmMessage(Guest g, int n) {
        System.out.println(g.fullName() + " Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + n + ". Te vom notifica daca un loc devine disponibil.");
    }

    private boolean isOnTheListAlready(Guest g) {
        return isOnWaitingList(g) || isOnParticipantsList(g);
    }

    private boolean isOnParticipantsList(Guest g) {
        for (Guest guest : participantsList) {
            if (guest.equalsFullName(g) || guest.equalsEmail(g) || guest.equalsPhoneNumber(g))
                return true;
        }
        return false;
    }

    private boolean isOnWaitingList(Guest g) {
        for (Guest guest : waitingList) {
            if (guest.equalsFullName(g) || guest.equalsEmail(g) || guest.equalsPhoneNumber(g))
                return true;
        }
        return false;
    }

    public Guest search(String firstName, String lastName) {
        Guest g = searchParticipantsList(firstName, lastName);
        if (null != g) {
            return g;
        }
        g = searchWaitingList(firstName, lastName);
        return g;
    }

    public Guest searchWaitingList(String firstName, String lastName) {
        for (Guest g : waitingList) {
            if (firstName.equals(g.getFirstName()) && lastName.equals(g.getLastName()))
                return g;
        }
        return null;
    }

    public Guest searchParticipantsList(String firstName, String lastName) {
        for (Guest g : participantsList) {
            if (firstName.equals(g.getFirstName()) && lastName.equals(g.getLastName()))
                return g;
        }
        return null;
    }

    public Guest searchWaitingList(int opt, String match) {
        if (opt == 2) {
            for (Guest g : waitingList) {
                if (match.equals(g.getEmail()))
                    return g;
            }
        }
        if (opt == 3) {
            for (Guest g : waitingList) {
                if (match.equals(g.getPhoneNumber()))
                    return g;
            }
        }
        return null;
    }

    public Guest searchParticipantsList(int opt, String match) {
        if (opt == 2) {
            for (Guest g : participantsList) {
                if (match.equals(g.getEmail()))
                    return g;
            }
        }
        if (opt == 3) {
            for (Guest g : participantsList) {
                if (match.equals(g.getPhoneNumber()))
                    return g;
            }
        }
        return null;
    }

    public Guest search(int opt, String match) {
        Guest g = searchParticipantsList(opt, match);
        if (null != g) {
            return g;
        }
        g = searchWaitingList(opt, match);
        return g;
    }

    public boolean remove(String firstName, String lastName) {
        Guest g = searchParticipantsList(firstName, lastName);
        if (null != g) {
            participantsList.remove(g);
            if (numberOfPeopleWaiting() > 0) {
                g = waitingList.removeFirst();
                participantsList.add(g);
                addParticipantConfirmMessage(g);
                return true;
            }
        }
        g = searchWaitingList(firstName, lastName);
        if (null != g) {
            waitingList.remove(g);
            return true;
        }
        return false;
    }

    public boolean remove(int opt, String match) {
        Guest g = searchParticipantsList(opt, match);
        if (null != g) {
            participantsList.remove(g);
            if (numberOfPeopleWaiting() > 0) {
                g = waitingList.removeFirst();
                participantsList.add(g);
                addParticipantConfirmMessage(g);
                return true;
            }
        }
        g = searchWaitingList(opt, match);
        if (null != g) {
            waitingList.remove(g);
            return true;
        }
        return false;
    }

    public void showGuestsList() {
        if (participantsList.size() == 0) {
            System.out.println("Lista de participanti este goala...");
            return;
        }
        int i = 1;
        for (Guest g : participantsList) {
            System.out.println(i + ". " + g.toString());
            i++;
        }
    }

    public void showWaitingList() {
        if (waitingList.size() == 0) {
            System.out.println("Lista de asteptare este goala...");
            return;
        }
        int i = 1;
        for (Guest g : waitingList) {
            System.out.println(i + ". " + g.toString());
            i++;
        }
    }

    public int numberOfAvailableSpots() {
        return guestsCapacity - numberOfGuests();
    }

    public int numberOfGuests() {
        return participantsList.size();
    }

    public int numberOfPeopleWaiting() {
        return waitingList.size();
    }

    public int numberOfPeopleTotal() {
        return numberOfGuests() + numberOfPeopleWaiting();
    }

    public List<Guest> partialSearch(String match) {
        match = match.toLowerCase();
        List<Guest> list = new ArrayList<>();
        for (Guest g : participantsList) {
            if (g.getFirstName().toLowerCase().contains(match) ||
                    g.getLastName().toLowerCase().contains(match) ||
                    g.getEmail().toLowerCase().contains(match) ||
                    g.getPhoneNumber().contains(match))
                list.add(g);
        }
        for (Guest g : waitingList) {
            if (g.getFirstName().toLowerCase().contains(match) ||
                    g.getLastName().toLowerCase().contains(match) ||
                    g.getEmail().toLowerCase().contains(match) ||
                    g.getPhoneNumber().contains(match))
                list.add(g);
        }
        return list;
    }

    @Override
    public String toString() {
        return "GuestsList{" +
                "Guests Capacity=" + guestsCapacity +
                ", Participants=" + numberOfGuests() +
                ", People Waiting=" + numberOfPeopleWaiting() +
                '}';
    }
}