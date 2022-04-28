package com.bms.authserver.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customer_credential")
public class CustomerCredentials{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(strategy = "native",name = "native")
    @Column(name = "c_id",updatable = false)
    private Long id;

    @Column(name = "user_name",unique = true,nullable = false, length = 25)
    @Size(max = 25,message = "Username size must not be exceed by 25 char!")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "logged_in_key")
    private String loggedInKey;

    public CustomerCredentials(){}

    public CustomerCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public CustomerCredentials(String username, String password, String loggedInKey) {
        this(username,password);
        this.loggedInKey = loggedInKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoggedInKey() {
        return loggedInKey;
    }

    public void setLoggedInKey(String loggedInKey) {
        this.loggedInKey = loggedInKey;
    }

    @Override
    public String toString() {
        return "CustomerCredentials{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedInKey='" + loggedInKey + '\'' +
                '}';
    }
}
