package training;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private static final char SEPARATOR = ';';

    private String name;
    private String lastname;
    private String login;
    private String email;

    public User(){
        super();
    }

    public User (String name, String lastname, String login, String email) {
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public java.lang.String toString() {
        return login+ SEPARATOR + name + SEPARATOR + lastname + SEPARATOR + email;
    }

    @Override
    public boolean equals (Object user) {
        return user instanceof User && ((User) user).getLogin().equals(this.getLogin());
    }
}
