package blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController.
 *
 */


@Controller
public class HomeController {

    @RequestMapping("/home")
    public String homePage(Model model)
    {
        //The value of the name parameter is added to a Model object,
        // ultimately making it accessible to the view template.
        model.addAttribute("name", "Welcome to the homepage");
        //Returning the name of a View
        // A View is responsible for rendering the HTML content:
        return "index";
    }

}
