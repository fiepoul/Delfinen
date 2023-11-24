import java.time.LocalDate;
import java.time.Period;

public class Member {
    private String name;
    private LocalDate birthDate;
    private String address;

    private String phoneNumber;
    private String mail;
    private boolean isActive; //aktiv eller passiv
    private boolean isCompetitive; //motion eller konkurrence
    private LocalDate startDate;
    private String memberNumber;


    public Member(String name, LocalDate birthdate, String address, String phoneNumber, String mail, boolean membershipStatus, boolean swimmerType, LocalDate startDate, String memberNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.isActive = membershipStatus;
        this.isCompetitive = swimmerType;
        this.startDate = startDate;
        this.memberNumber = memberNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isCompetitive() {
        return isCompetitive;
    }

}
