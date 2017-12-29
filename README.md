# Vulnerable-web-application
## Course project for Cyber Security Base with F-Secure by University of Helsinki

- This application allows you to create notes that everyone can see after logging in, it will have at least five vulnerabilities from the [OWASP top ten vulnerabilities list](https://www.owasp.org/index.php/Top_10_2013-Top_10). Each vulnerability will have instructions for identifying and fixing them.

## A1-Injection
### How this vulnerability can be identified?
* This is a SQL injection vulnerability in the login form. To manually exploit this vulnerability, the easiest way is by inputting any name and in the password field typing something like "NotRealPass'OR'1'='1". This will log the user in as whatever is the first queried account. More specific queries can also be made for specific accounts.
### How this vulnerability can be fixed?
* Fixing this problem would be easy using Spring repositories, as used for every other query in this application. Other ways could be parameterized SQL queries or escaping all user input.

## A3-Cross-Site Scripting (XSS)
### How this vulnerability can be identified?
* When creating a note on the home page, JavaScript can be written as a part of the message, it is then sent to the browser of whoever views the note. Example of this is: "MyNote<script>console.log("message");</script>".
### How this vulnerability can be fixed?
* With Thymeleaf to fix this vulnerability you just need to change th:utext to th:text. Other ways of fixing include sanitizing and escaping the input.

## A8-Cross-Site Request Forgery (CSRF) (Not yet implemented, as no sessions exist yet)
### How this vulnerability can be identified?
* While logged in, open the csrf.html file in folder external-resources using the same browser. In the input field write your note and then submit. The note will now be stored and displayed to be posted by the active user.
### How this vulnerability can be fixed?
* A CSRF token has to be added to prevent CSRF attacks. In Spring this can be done automatically using the Spring security framework by adding line "http.csrf();" inside the security configurate function. It will then automatically add a CSRF token when needed.

## Other: Brute-force attacks
### How this vulnerability can be identified?
* As no security exists to prevents trying to login multiple times, any brute-force attack types should work
### How this vulnerability can be fixed?
* There are many methods to block brute-force attacks, perhaps the simplest way would be adding a CAPTCHA after multiple attempts.
