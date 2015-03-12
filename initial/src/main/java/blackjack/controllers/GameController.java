package blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 *
 * */

@Controller
public class GameController {


    @RequestMapping("/play")
    public String home(Model model)
    {
        model.addAttribute("name", "Welcome to the game");
        return "index" ;
    }

    @RequestMapping("/play/{room}")
    public String playInRoom(@PathVariable String room){
        return "game";
    }

}
