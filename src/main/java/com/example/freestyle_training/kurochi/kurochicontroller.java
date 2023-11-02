package com.example.freestyle_training.kurochi;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
public class kurochicontroller {

    @RequestMapping(path = "/addTag")
    public String tagopen(Model model) throws IOException {
        model.addAttribute("tagInput", new tagInput());
        return "kurochi/addTag";
    }

    @RequestMapping(path = "/inputTag")
    public String input(@ModelAttribute tagInput inputting, Model model) {

        model.addAttribute("tag", inputting.getTag());
        return "kurochi/addTag";
    }

}
