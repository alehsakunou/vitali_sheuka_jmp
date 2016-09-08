package cinema.model;

/**
 * Created by Vitali_Sheuka on 9/7/2016.
 */
public class TicketMessage {

    private String name;
    private String surname;

    public TicketMessage() {
    }

    public TicketMessage(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
