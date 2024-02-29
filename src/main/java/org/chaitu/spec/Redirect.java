package org.chaitu.spec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class Redirect {

    @RequestMapping(value = {"/*", "/**"})
    public RedirectView redirectToRoot() {
        return new RedirectView("/", true);
    }
}
