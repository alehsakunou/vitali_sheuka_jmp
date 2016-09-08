package cinema.model;

/**
 * Имя : строка
 Фамилия : строка
 * Created by Vitali_Sheuka on 9/7/2016.
 */
public class User {

    private String name;
    private String surname;

    public User() {
    }

    public User(String name, String surname) {
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
