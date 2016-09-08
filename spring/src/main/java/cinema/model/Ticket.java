package cinema.model;


import cinema.model.User;

import java.util.Currency;

/**
 * Бронь
 Номер брони: cтрока
 Название фильма: строка
 Время сеанса: время и дата
 Место : число
 Цена билета : деньги
 Заказчик : человек
 * Created by Vitali_Sheuka on 9/7/2016.
 */
public class Ticket {

    private String id;
    private String name;
    private long time;
    private int seat;
    private Amount price;
    private User user;

    public Ticket() {
    }

    public Ticket(String id, String name, long time, int seat, Amount price, User user) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.seat = seat;
        this.price = price;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Amount getPrice() {
        return price;
    }

    public void setPrice(Amount price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
