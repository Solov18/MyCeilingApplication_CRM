package ru.gb.diplom.representative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/representative")
public class RepresentativeController {
    static final String REDIRECT_LISTALL_FULLPATH = "redirect:/representative/listall";


    @Autowired
    RepresentativeService rs;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("representative", new Representative());
        return "/representative/addrepresentative";
    }

    @PostMapping("/add")
    public String add(@Valid Representative representative, BindingResult result) {
        if (result.hasErrors()) {
            return "/representative/addrepresentative";
        } else {
            rs.save(representative);
            return "redirect:listall";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("r", rs.findOne(id));
        return "/representative/editrepresentative";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Representative representative, BindingResult result) {
        if (result.hasErrors()) {
            return "/representative/editrepresentative";
        } else {
            rs.save(representative);
            return REDIRECT_LISTALL_FULLPATH;
        }
    }


    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") long id, Model model) {
        model.addAttribute("r", rs.findOne(id));
        return "/representative/viewrepresentative";
    }


    @PostMapping("/view/{id}")
    public String view() {
        return REDIRECT_LISTALL_FULLPATH;
    }


    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") long id) {
        rs.remove(id);
        return REDIRECT_LISTALL_FULLPATH;
    }

    @GetMapping("/listall")
    public String listAll(Model model, @SortDefault("id") Pageable pageable) {
        model.addAttribute("page", rs.findAll(pageable));
        return "representative/listrepresentatives";
    }


    @GetMapping("/import")
    public String getImportFileName(Model model) {
        model.addAttribute("filename", "file.csv");
        return "utils/importfilename";
    }


    @PostMapping("/import")
    public String getImportFileName(@RequestParam(name = "filename", required = true) String filename) {
        rs.importDataFromCSV(filename);
        return REDIRECT_LISTALL_FULLPATH;
    }

    @GetMapping("/export")
    public String getExportFileName(Model model) {
        model.addAttribute("filename", "file.csv");
        return "utils/exportfilename";
    }

    @PostMapping("/export")
    public String getExportFileName(@RequestParam(name = "filename", required = true) String filename) {
        rs.exportDataToCSV(filename);
        return REDIRECT_LISTALL_FULLPATH;
    }






}
