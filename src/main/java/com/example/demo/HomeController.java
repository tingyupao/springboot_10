package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    CourseRepository cR;

    @RequestMapping("/")
    public String listJobs(Model model){
        model.addAttribute("courses", cR.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model){
        model.addAttribute("course", new Course());
        return "courseform";
    }

    @PostMapping("/process")
            public String processForm(@Valid Course course, BindingResult result) {
        if (result.hasErrors()) {
            return "courseform";
        }
        cR.save(course);
        return "redirect:/";
    }
        @RequestMapping("/detail/{id}")
                public String showJob(@PathVariable("id") long id, Model model){
            model.addAttribute("course", cR.findOne(id));
            return "show";

        }

        @RequestMapping("/update/{id}")
                public String updateCourse(@PathVariable("id") long id, Model model){
            model.addAttribute("course", cR.findOne(id));
            return "courseform";
        }

        @RequestMapping("/delete/{id}")
                public String delJob(@PathVariable("id")long id){
                    cR.delete(id);
                    return "redirect:/";
        }
    }


