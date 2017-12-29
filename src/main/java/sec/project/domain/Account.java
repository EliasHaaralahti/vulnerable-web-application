package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    private String name;
    private String password;

    public Account() {
        super();
    }

    public Account(String name, String password) {
        this();
        this.name = name;
        this.password = password;
        System.out.println("Created account with name: " + this.getName() + " password: " + this.getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }   
    
    public void setPassword(String password) {
        this.password = password;
    }
}
