package sec.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        if (accountRepository.count() == 0) {
            accountRepository.save(new Account("Name", "Password"));
            accountRepository.save(new Account("User", "Password"));
        }
        return "loginForm";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitForm(HttpServletRequest request, HttpSession currentSession,
                    @RequestParam String name, @RequestParam String password) {         
        
        currentSession.invalidate();
        HttpSession session = request.getSession();        
            
        // Unsafe SQL query. Example injection for password = Dunno'OR'1'='1'
        String sql = 
            "SELECT * FROM account WHERE name='" + name + "' AND password='" + password + "'";
        
        Account account;
        try {
            account = (Account)jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(Account.class));
        } catch (Exception e) {
            return "loginForm";
        }
        session.setAttribute("loggedUserName", account.getName());
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }
}