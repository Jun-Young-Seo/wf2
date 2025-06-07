package kr.ac.hansung.cse.hellospringdatajpa.controller;

import kr.ac.hansung.cse.hellospringdatajpa.dto.UserRegisterDto;
import kr.ac.hansung.cse.hellospringdatajpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/admin/home")
    public String adminHome(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin_home";
    }

    //just render
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    //just render
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new UserRegisterDto());
        return "register";
    }

    @PostMapping("/register")
    public String submitRegisterForm(@ModelAttribute UserRegisterDto user, Model model) {
        logger.info("회원가입 요청: " + user.getEmail());
        if (userService.checkEmailExists(user.getEmail())) {
            logger.info("email alreadyy exists" + user.getEmail());
            model.addAttribute("emailExists", true);
            model.addAttribute("user", user);
            return "register";
        }
        userService.createUser(user);
        logger.info("회원가입 성공 : " + user.getEmail());
        return "redirect:/login";
    }

}
