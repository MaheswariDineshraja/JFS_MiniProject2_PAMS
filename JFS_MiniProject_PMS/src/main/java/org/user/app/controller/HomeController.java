package org.user.app.controller;

import org.user.app.model.Contact;
import org.user.app.service.ContactService;
import org.user.app.service.RoleService;
import org.user.app.service.UserService;
import org.user.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Home")
public class HomeController
{
    private ContactService contactService;
    private UserService userService;
    private RoleService roleService;
    public HomeController(RoleService roleService, ContactService contactService, UserService userService)
    {
        this.contactService = contactService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getHomePage(Model model)
    {
        return "Home/home";
    }
    @GetMapping("/home-page")
    public String sayHello(Model model)
    {
        model.addAttribute("date",new java.util.Date());
        return "Home/home";
    }
    @GetMapping("/login")
    public String login()
    {
        return "Login/Login";
    }
    @GetMapping("/signUP")
    public String signUP(Model model)
    {
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("roleList",roleService.getRoles());
        return "Register/sign-up";
    }

    @PostMapping("/register-user")
    public String registerNewUser(@ModelAttribute("user") User user)
    {
        userService.save(user);
        return "redirect:/Home/home-page";
    }
    @GetMapping("/contact-US")
    public String getContactPage(Model model)
    {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "contact-US/contact-US";
    }

    @PostMapping("/save-contact")
    public String reciveContact(@ModelAttribute("contact") Contact contact)
    {
        try
        {
            contactService.save(contact);
            return "redirect:/Home/home-page";
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/Home/home-page";
    }
    
    @PostMapping("/login")
    public String handlePostRequest() {
        // Process the POST request
    	return "redirect:/Home/home-page";
    }
}
