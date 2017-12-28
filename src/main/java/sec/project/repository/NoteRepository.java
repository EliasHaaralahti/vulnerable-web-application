package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
