package com.enset.studentcrud.Web;

import com.enset.studentcrud.Entities.Student;
import com.enset.studentcrud.Repositories.studentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//
//@Controller
//@RequestMapping("/")
//public class studentController {
//
//    studentRepository studentRepo ;
//
//    public studentController(studentRepository studentRepo) {
//        this.studentRepo = studentRepo;
//    }
//
//    @GetMapping("")
//    public String StudentList(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
//                              @RequestParam(name = "size", defaultValue = "5") int size,
//                              @RequestParam(name = "keyword", defaultValue = "") String keyword){
//        Page<Student> students = studentRepo.findByNomContains(keyword, PageRequest.of(page, size));
//        model.addAttribute("students", students.getContent());
//        model.addAttribute("pages", new int[students.getTotalPages()]);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("keyword", keyword);
//        return "Home";
//    }
//
//    @GetMapping("/addStudent")
//    public String addStudent(Model model) {
//        model.addAttribute("student", new Student());
//        return "addStudent";
//    }
//
//    @GetMapping("/save")
//    public String save(Model model, Student student){
//        studentRepo.save(student);
//        System.out.println("The student is " + student);
//        return "redirect:/";
//    }
//
//    @GetMapping("/edit")
//    public String edit(Model model, Student student){
//        studentRepo.findById(student.getId());
//        return "EditStudent";
//    }
//
//
//}



@Controller
@Data
@AllArgsConstructor
public class studentController {
    private studentRepository etudiantRepository;

    @GetMapping(path = "/user/index")
    public String etudiants(Model model,
                            //param
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "5") int size,
                            @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Student> pageEtudiants = etudiantRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listetudiants", pageEtudiants.getContent());
        model.addAttribute("pages", new int[pageEtudiants.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "etudiants";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page) {
        etudiantRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/user/etudiants")
    @ResponseBody
    public List<Student> etudiantList() {
        return etudiantRepository.findAll();
    }

    @GetMapping("/admin/formEtudiants")
    public String formEtudiants(Model model) {
        model.addAttribute("Student", new Student());
        return "formEtudiants";
    }

    @PostMapping("/admin/save")
    public String save(Model model, @Valid Student etudiant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors())
            return "formEtudiants";
        etudiantRepository.save(etudiant);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/admin/editStudent")
    public String editEtudiant(Model model, Long id,
                               @RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page) {
        Student etudiant = etudiantRepository.findById(id).orElse(null);
        if (etudiant == null) throw new RuntimeException("Etudiant introuvable!!!");
        model.addAttribute("Student", etudiant);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "editEtudiant";
    }

    @GetMapping("/user/listStudent")
    public String listEtudiant(Model model, Long id,
                               @RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page) {
        Student etudiant = etudiantRepository.findById(id).get();
        model.addAttribute("Student", etudiant);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "listEtudiant";
    }
}

