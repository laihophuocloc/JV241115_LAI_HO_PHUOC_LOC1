package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.edu.Entity.Student;
import ra.edu.Service.course.ICourseService;
import ra.edu.Service.student.IStudentService;
import ra.edu.dto.request.StudentAdd;
import ra.edu.dto.request.StudentUpdate;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IStudentService studentService;
    @GetMapping({"","/list"})
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       @RequestParam(value = "keyword", defaultValue = "") String keyword,
                       Model model) {
        model.addAttribute("students", studentService.paginationList(keyword, page, size));
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages",studentService.countTotalPages(size));
        return "student/list";
    }
    @GetMapping("/new")
    public String showAddForm(Model model){
        model.addAttribute("student", new StudentAdd());
        model.addAttribute("courses", courseService.findAll());
        return "student/add";
    }
    //Xử lý thêm mới
    @PostMapping("/add")
    public String doAdd(@ModelAttribute("student") StudentAdd request, Model model, @RequestParam("avatar") MultipartFile avatar){
        request.setAvatar(avatar);
        studentService.create(request);
        return "redirect:/student";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model){
        Student entity = studentService.findById(id);
        StudentUpdate dto = new StudentUpdate(entity.getStudentId(), entity.getStudentName(),entity.getEmail(),entity.getPhone(),entity.getSex(), entity.getDateOfBirth(), entity.getCourse().getCourseId(), null, entity.getStatus());
        model.addAttribute("student", dto);
        model.addAttribute("id", id);
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("imageUrl", entity.getAvatar());
        return "student/edit";
    }
    @PostMapping("/update")
    public String doUpdate(@ModelAttribute("student") StudentUpdate request, Model model, @RequestParam("studentId") String id){
        studentService.update(request, id);
        return "redirect:/student";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        studentService.delete(id);
        return "redirect:/student";
    }
}
