package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Note extends AbstractPersistable<Long> {

    private String content;
    private String creator;

    public Note() {
        super();
    }

    public Note(String name, String creator) {
        this();
        this.content = name;
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String name) {
        this.content = name;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
}
