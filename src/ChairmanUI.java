import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChairmanUI {
    private Scanner scanner;
    private List<Member> members;
    private int memberCountThisYear = 0; // Holder styr på antallet af medlemmer indmeldt i år
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public ChairmanUI() {
        this.scanner = new Scanner(System.in);
        this.members = new ArrayList<>();
    }

    public void start() {
        System.out.println("SVØMMEKLUBBEN DELFINENS MANAGEMENT SYSTEM");
        boolean running = true;

        while (running) {
            System.out.println("Menu:");
            System.out.println("1: Tilføj nyt medlem");
            System.out.println("2: Opdater medlemsoplysninger");
            System.out.println("3. Slet medlem");
            System.out.println("4: Vis medlemsliste");
            System.out.println("5: Afslut og gem");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addNewMember();
                case 2 -> updateMember();
                case 3 -> deleteMember();
                case 4 -> showMembers();
                case 5 -> {saveMembers();
                exitMamagement();}
                default -> System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

    private void addNewMember() {
        System.out.println("Indtast navn: ");
        String name = scanner.nextLine();
        System.out.println("Indtast fødselsdato (dd.MM.yyyy):");
        String birthDateString = scanner.next();
        LocalDate birthDate = LocalDate.parse(birthDateString, DATE_FORMATTER);
        System.out.println("Indtast adresse:");
        String address = scanner.nextLine();
        System.out.println("Indtast telefonnummer: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Indtast email:");
        String email = scanner.nextLine();
        System.out.println("Er medlemmet aktivt? (ja/nej):");
        boolean isActive = scanner.nextLine().trim().equalsIgnoreCase("ja");
        System.out.println("Er medlemmet en konkurrencesvømmer? (ja/nej):");
        boolean isCompetitive = scanner.nextLine().trim().equalsIgnoreCase("ja");

        LocalDate startDate = LocalDate.now();
        String memberNumber = generateMemberNumber(startDate);

        Member newMember = new Member(name, birthDate, address, phoneNumber, email, isActive, isCompetitive, startDate, memberNumber);
        members.add(newMember);

        System.out.println(name + " er blevet tilføjet som medlem med medlemsnummer " + memberNumber + ".");
    }

    private String generateMemberNumber(LocalDate startDate) {
        int year = startDate.getYear();
        memberCountThisYear++;
        return String.format("%d%04d", year, memberCountThisYear);
    }

    private void updateMember() {
        System.out.println("Indtast navn på medlem, der skal opdateres:");
        String name = scanner.nextLine();

        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                System.out.println("Indtast typen af information, der skal opdateres:");
                System.out.println("Mulige valg: navn, adresse, telefonnummer, e-mail");
                String infoType = scanner.nextLine().toLowerCase();
                System.out.println("Indtast ny værdi:");
                String newValue = scanner.nextLine();

                switch (infoType) {
                    case "navn" -> member.setName(newValue);
                    case "adresse" -> member.setAddress(newValue);
                    case "telefonnummer" -> member.setPhoneNumber(newValue);
                    case "e-mail" -> member.setMail(newValue);
                    default -> {
                        System.out.println("Ugyldig informationstype. Prøv igen: ");
                        return;
                    }
                }
                System.out.println(member.getName() + "'s oplysninger er blevet opdateret.");
                return;
            }
        }
    }

    private void deleteMember() {
        // Implementer logik for at slette et medlem
    }

    private void showMembers() {
        // Implementer logik for at vise medlemslisten
    }

    private void saveMembers() {
        try {
            FileOutputStream fileOut = new FileOutputStream("members.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(members);
            out.close();
            fileOut.close();
            System.out.println("Medlemsdata er gemt.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private void exitMamagement() {
        // Implementer logik for at gemme data og afslutte programmet
        System.out.println("Gemmer data og afslutter...");
    }


}
