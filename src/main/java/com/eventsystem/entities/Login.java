package com.eventsystem.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roleId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public Login() {
    }

    public Login(Role roleId, String firstName, String lastName, String email, String password) {
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Login login)) return false;
        return getId().equals(login.getId()) && getRoleId().equals(login.getRoleId()) && getFirstName().equals(login.getFirstName()) && getLastName().equals(login.getLastName()) && getEmail().equals(login.getEmail()) && getPassword().equals(login.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleId(), getFirstName(), getLastName(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
