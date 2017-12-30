# Vulnerable-web-application
## Course project for Cyber Security Base with F-Secure by University of Helsinki

- This application allows you to create notes that everyone can see after logging in, it will have at least five vulnerabilities from the [OWASP top ten vulnerabilities list](https://www.owasp.org/index.php/Top_10_2013-Top_10). Each vulnerability will have instructions for identifying and fixing them.

- Some vulnerabilities will also include steps for detecting them using [Owasp Zap](https://www.owasp.org/index.php/Main_Page). Some of these vulnerabilities steps require you to use the FuzzDB add-on installed. To install it, go to the add-ons manager, marketplace and download FuzzDB.

## A1-Injection
### How this vulnerability can be identified?
* This is a SQL injection vulnerability in the login form. To manually exploit this vulnerability, the easiest way is by inputting any name and in the password field typing something like ```NotRealPass'OR'1'='1```. This vulnerability also allows you to create, remove or even get data.
```
Owasp Zap steps:
1. Start both this application and Owasp Zap.
2. In the quick start section, click Launch Browser, go to localhost:8080, add values username and password respectively.
3. In Owasp Zap click the post request, select the password value, which should be "password", right click and select "Fuzz".
4. Note: This part requires you have to have the FuzzDB tools installed. More information at the top of this file.
5. Select payloads, click add, select type "File Fuzzers", from the fuzzdb selection go to attack and select sql-injection. Click add and ok.
6. Start the attack. From the results you can see if the application is vulnerable.
```
### How this vulnerability can be fixed?
* When working with Spring using Spring repositories, as used for every other query in this application. Other ways could be parameterized SQL queries or escaping all user input.

## A2-Broken Authentication and Session Management
### How this vulnerability can be identified?
* This vulnerability is caused by multiple other vulnerabilities, including: User credentials are not protected safely, as passwords are stored in plain text and credentials can be accessed using login form SQL injection vulnerability. 
### How this vulnerability can be fixed?
* For credentials not being protected safely and SQL injection, the way to fix them can be found under A6-Sensitive Data Exposure and A1-Injection.

## A3-Cross-Site Scripting (XSS)
### How this vulnerability can be identified?
* When creating a note on the home page, JavaScript can be written as a part of the message, it is then sent to the browser of whoever views the note. Example of this is: ```MyNote<script>console.log("message");</script>.```
```
Owasp Zap steps:
1. Start both this application and Owasp Zap.
2. In the quick start section, click Launch Browser, go to localhost:8080, login with the default credentials, set note field value to "test" and submit the form.
3. In Owasp Zap click the post request, select the content value, which should be "test", right click and select "Fuzz".
4. Note: This part requires you have to have the FuzzDB tools installed. More information at the top of this file.
5. Select payloads, click add, select type "File Fuzzers", from the fuzzdb selection go to attack and select xss. Click add and ok.
6. Start the attack. From the results you can see if the application is vulnerable.
```
### How this vulnerability can be fixed?
* With Thymeleaf to fix this vulnerability you just need to change ```th:utext``` to ```th:text```. Other ways of fixing include sanitizing and escaping the input.

## A4-Insecure Direct Object References
### How this vulnerability can be identified?
This application allows users to create notes that are hidden or visible to everyone. Hidden notes will only be shown to the user who created it. However notes are accessed using path /note/id and it does not implement any access control checks or other protection, so by guessing the ID number users are able to see hidden notes they are not supposed to. 
### How this vulnerability can be fixed?
To fix this access control should be implemented to the page to check if the user is authenticated to view the note.

## A5-Security Misconfiguration
### How this vulnerability can be identified?
* The first part of this vulnerability if the obvious fact that CSRF is disabled, which makes this application vulnerable to A8-Cross-Site Request Forgery attacks. However the tricky part is that as get request security is not handled using the framework's security, but instead by checking the session attribute, each request has to be verified manually. This will likely cause issues during development.
### How this vulnerability can be fixed?
* By default all requests should require authentication and the pages that do not require it, such as login, should be specifically mentioned. Also it would be best to use the security framework provided by Spring.

## A6-Sensitive Data Exposure
### How this vulnerability can be identified?
* User data is stored as plain text, including passwords. Using the SQL injection vulnerability all data can be retrieved using SQL queries.
### How this vulnerability can be fixed?
* The data should be encrypted, one method could be using the Spring security crypto module.

## A8-Cross-Site Request Forgery (CSRF)
### How this vulnerability can be identified?
* While logged in, open the csrf.html file in folder external-resources using the same browser. In the input field write your note and then submit. The note will now be stored and displayed to be posted by the active user. This vulnerability can also use the XSS vulnerability mentioned above.
```
Owasp Zap steps:
1. Start both this application and Owasp Zap.
2. In the quick start section, click Launch Browser and go to localhost:8080.
3. Click submit in the login form.
4. Inspect the request in Owasp Zap to find that there is no CRSF token included.
```
### How this vulnerability can be fixed?
* A CSRF token has to be added to prevent CSRF attacks. In Spring this can be done automatically using the Spring security framework by adding line "http.csrf();" inside the security configurate function. It will then automatically add a CSRF token when needed.

## Other: Brute-force attacks
### How this vulnerability can be identified?
* As no security exists to prevents trying to login multiple times, any brute-force attack types can be used to get the login credentials.
```
Owasp Zap steps for brute-forcing a password with dictionary attack:
1. Start both this application and Owasp Zap.
2. Set the name field value to the user name you want to get access to, like "User". Set the password field to some value, for an example "test" and click submit.
3. Go to Owasp Zap, open the POST request created by submitting the form, select the password value, right click, select "Fuzz".
4. In the new window, select "Payloads", click add and change type to File. From the external resources folder in this project, select the file passwords.txt. This file includes the correct password for the default user.
5. Add the world list, press okay and start the attack.
6. From the requests find the request that was successful to find the correct password.
```
### How this vulnerability can be fixed?
* There are many methods to block brute-force attacks, perhaps the simplest way would be adding a CAPTCHA after multiple attempts.
