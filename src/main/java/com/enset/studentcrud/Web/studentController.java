package com.enset.studentcrud.Web;

import com.enset.studentcrud.Entities.Student;
import com.enset.studentcrud.Repositories.studentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class studentController {

    studentRepository studentRepo ;

    public studentController(studentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping("")
    public String StudentList(Model model){
        List<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "Home";
    }

}
