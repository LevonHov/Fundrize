package org.example.Models;

import javax.persistence.*;

import org.example.Models.Enums.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Создает отдельные таблицы для подтипов
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;



    // Конструктор
    public User(String firstName, String lastName, String email,
                String passwordHash, String phone, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.role = role;
    }
    public User(){}
    // Геттеры и сеттеры
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getPhone() { return phone; }
    public Role getRole() { return role; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setRole(Role role) { this.role = role; }
}
