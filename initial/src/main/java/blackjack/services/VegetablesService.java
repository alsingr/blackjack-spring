package blackjack.services;

import blackjack.model.Vegetable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * VegetablesService
 * Resource controller
 *
 */

@RestController
public class VegetablesService {

    private final AtomicLong counter = new AtomicLong();

    //The @RequestMapping annotation ensures that HTTP requests to /animal are mapped to the animal() method.
    @RequestMapping("/animal")
    public Vegetable animal(@RequestParam(value="name", defaultValue="carrot") String vegetableName) {

        //Returning a new instance of the Vegetable class
        //The object data will be written directly to the HTTP response as JSON.
        //you don’t need to do this conversion manually
        return new Vegetable(counter.incrementAndGet(),
               vegetableName);
    }
}
