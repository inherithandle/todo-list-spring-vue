package todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by hello on 14/09/2018.
 */
@Controller
public class UserController {

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
