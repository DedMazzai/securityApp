package ru.davydenko.springcourse.securityapp.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "password")
    private String password;

    public Member(String username, int yearOfBirth) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
    }
}
