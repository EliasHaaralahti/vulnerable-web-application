package sec.project.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        if (session.getAttribute("loggedUserName") == null) {
            return "redirect:login";
        }
        
        model.addAttribute("userName", session.getAttribute("loggedUserName"));
        model.addAttribute("notes", noteRepository.findAll() );
        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String submitForm(HttpSession session, @RequestParam String content) {
        String userName = session.getAttribute("loggedUserName").toString();
        noteRepository.save( new Note(content, userName) );
        return "redirect:/home";
    }
}
