package com.enset.studentcrud.Web;

import com.enset.studentcrud.Entities.Student;
import com.enset.studentcrud.Repositories.studentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class studentController {

    studentRepository studentRepo ;

    public studentController(studentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping("")
    public String StudentList(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size,
                              @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Student> students = studentRepo.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("students", students.getContent());
        model.addAttribute("pages", new int[students.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "Home";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @GetMapping("/save")
    public String save(Model model, Student student){
        studentRepo.save(student);
        System.out.println("The student is " + student);
        return "redirect:/";
    }

}
