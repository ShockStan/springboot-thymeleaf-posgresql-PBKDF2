package ui.online.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ui.online.Service.peopleService;
import ui.online.entity.peopleEntity;

import java.security.NoSuchAlgorithmException;

@Controller
public class peopleController {
    @Autowired
    private peopleService peopleservice;
    @GetMapping("/people")
    public String getAll(Model model){
        model.addAttribute("people", peopleservice.getALl());
        return "people";
    }
    @GetMapping("/people/new")
    public String createForm(Model model){
        peopleEntity people = new peopleEntity();
        model.addAttribute("data",people);
        return "create_people";
    }
    @PostMapping("/people")
    public String createUser(@ModelAttribute("data") peopleEntity people) throws NoSuchAlgorithmException {
        peopleservice.addPeople(people);
        return "redirect:/people";
    }
    @GetMapping("/people/update/{ID}")
    public String updateForm(@PathVariable Integer ID, Model model) {
        model.addAttribute("data", peopleservice.getByid(ID));
        return "update_people";
    }
    @PatchMapping("/people/{ID}")
    public String updateUser(@PathVariable Integer ID, @ModelAttribute("student") peopleEntity people, Model model) throws NoSuchAlgorithmException {
        peopleservice.updateUser(ID, people);
        return "redirect:/people";
    }
    @GetMapping("/people/{ID}")
    public String deleteUser(@PathVariable Integer ID) {
        peopleservice.deleteUser(ID);
        return "redirect:/people";
    }
}
