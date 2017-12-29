package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Note;
import sec.project.repository.AccountRepository;
import sec.project.repository.NoteRepository;

@Controller
public class NoteController {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String loadForm(Model model) {
        System.out.println("Accounts found: " + accountRepository.count() );
        model.addAttribute("notes", noteRepository.findAll() );
        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String submitForm(@RequestParam String content) {
        System.out.println("Home: Post: " + content);
        noteRepository.save( new Note(content) );
        return "redirect:/home";
    }
}
