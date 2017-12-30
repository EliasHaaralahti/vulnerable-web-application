package sec.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Note extends AbstractPersistable<Long> {

    @Id @GeneratedValue long id;
    private String content;
    private String creator;
    private String title;

    public Note() {
        super();
    }

    public Note(String title, String content, String creator) {
        this();
        this.content = content;
        this.creator = creator;
        this.title = title;
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
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getID() {
        return Long.toString(this.id);
    }
}
