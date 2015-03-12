package blackjack.controllers;

import blackjack.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;


//GreetingController handles GET requests for /greeting
@Controller
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model)
    {
        User user = new User(counter.incrementAndGet(), name);
        //The value of the name parameter is added to a Model object,
        // ultimately making it accessible to the view template.
        model.addAttribute("user", user);
        //Returning the name of a View
        // A View is responsible for rendering the HTML content:
        return "greeting";
    }

}