package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Note extends AbstractPersistable<Long> {

    private String content;

    public Note() {
        super();
    }

    public Note(String name) {
        this();
        this.content = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String name) {
        this.content = name;
    }
}
