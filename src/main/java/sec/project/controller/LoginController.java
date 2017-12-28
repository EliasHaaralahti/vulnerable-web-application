package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class LoginController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        return "loginForm";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String password) {
        
        String sql = "INSERT INTO account (name) VALUES ('" + name + "')";
        jdbcTemplate.execute(sql);
        
        Account account = accountRepository.findByName(name);
        System.out.println("account name: " + account.getName() );
        
        return "redirect:/home";
  }
}