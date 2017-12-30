package sec.project.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Note;
import sec.project.repository.NoteRepository;

@Controller
public class HomeController {
    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String loadForm(Model model, HttpSession session) {
        String username = session.getAttribute("loggedUserName").toString();
        if (username == null) {
            return "redirect:login";
        }
        
        List<Note> notes = noteRepository.findAll();
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (!username.equals(note.getCreator()) && note.isHidden()) {
                notes.remove(i);
            }
        }
        
        model.addAttribute("userName", username);
        model.addAttribute("notes", notes );
        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String submitForm(HttpSession session, @RequestParam String title,
                    @RequestParam String content,
                    @RequestParam(value = "hidden", required = false) boolean hidden) {
        
        String userName = session.getAttribute("loggedUserName").toString();
        noteRepository.save( new Note(title, content, userName, hidden) );
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
    public String getNote(Model model, @PathVariable(value="id") String id) {
        model.addAttribute("note", noteRepository.findOne(Long.parseLong(id)) );
        return "note";
    }
}
