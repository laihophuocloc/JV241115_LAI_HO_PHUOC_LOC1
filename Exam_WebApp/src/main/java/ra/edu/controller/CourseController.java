package ra.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.edu.Entity.Course;
import ra.edu.Service.course.ICourseService;
import ra.edu.dto.request.CourseAdd;
import ra.edu.dto.request.CourseUpdate;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;
    @GetMapping({"","/list"})
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       @RequestParam(value = "keyword", defaultValue = "") String keyword,
                       Model model, @RequestParam(value = "error", defaultValue = "") String message) {
        model.addAttribute("courses", courseService.paginationList(keyword, page, size));
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages",courseService.countTotalPages(size));
        if(!Objects.equals(message,"")) {
            model.addAttribute("error", message);
        }
        return "course/list";
    }
    @GetMapping("/new")
    public String showAddForm(Model model){
        model.addAttribute("course", new CourseAdd());
        return "course/add";
    }
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("course") CourseAdd request, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("course", request);
            return "course/add";
        }
        courseService.create(request);
        return "redirect:/course";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        Course entity = courseService.findById(id);
        CourseUpdate dto = new CourseUpdate(entity.getCourseName(), entity.getDescription());
        model.addAttribute("course", dto);
        model.addAttribute("id", id);
        return "course/edit";
    }
    @PostMapping("/update")
    public String doUpdate(@Valid @ModelAttribute("course") CourseUpdate request, BindingResult result, Model model, @RequestParam("courseId") Integer id){
        if(result.hasErrors()){
            model.addAttribute("course", request);
            return "course/edit";
        }
        courseService.update(request, id);
        return "redirect:/course";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
//        if (courseService.existProductByCategoryId(id)){
//            return "redirect:/category?error="+"Khong the xoa danh muc nay vi ton tai san pham";
//        }
        courseService.delete(id);
        return "redirect:/course";
    }
}
