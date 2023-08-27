package com.example.prog4.controller.view;

import com.example.prog4.config.CompanyConf;
import com.example.prog4.controller.PopulateController;
import com.example.prog4.controller.mapper.EmployeeMapper;
import com.example.prog4.model.Employee;
import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.repository.entity.Phone;
import com.example.prog4.service.EmployeeService;
import com.example.prog4.service.PDFService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeViewController extends PopulateController {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    private CompanyConf companyConf = new CompanyConf();



    @GetMapping("/list")
    public String getAll(
            @ModelAttribute EmployeeFilter filters,
            Model model,
            HttpSession session
    ) {
        model.addAttribute("employees", employeeService.getAll(filters).stream().map(employeeMapper::toView).toList())
                .addAttribute("employeeFilters", filters)
                .addAttribute("directions", Sort.Direction.values());
        session.setAttribute("employeeFiltersSession", filters);

        return "employees";
    }

    @GetMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("employee", Employee.builder().build());

        return "employee_creation";
    }

    @GetMapping("/edit/{eId}")
    public String editEmployee(@PathVariable String eId, Model model) {
        Employee toEdit = employeeMapper.toView(employeeService.getOne(eId));
        model.addAttribute("employee", toEdit);


        return "employee_edition";
    }

    @GetMapping("/show/{eId}")
    public String showEmployee(@PathVariable String eId, Model model) {
        Employee toShow = employeeMapper.toView(employeeService.getOne(eId));
        model.addAttribute("employee", toShow);

        return "employee_show";
    }
    @GetMapping("/payment/{eId}")
    public String EmployeePayment(@PathVariable String eId, Model model) {
        String companyName = companyConf.getName();
        String logo = companyConf.getLogo();
        String address = companyConf.getAddress();
        List<Phone> phones = companyConf.getPhones();
        String  email = companyConf.getEmail();
        String NIF = companyConf.getTaxIdentity().getNif();
        String STAT = companyConf.getTaxIdentity().getStat();

        Employee toShow = employeeMapper.toView(employeeService.getOne(eId));
        model.addAttribute("employee", toShow);
        model.addAttribute("companyName", companyName);
        model.addAttribute("logo", logo);
        model.addAttribute("address", address);
        model.addAttribute("phones", phones);
        model.addAttribute("email", email);
        model.addAttribute("NIF", NIF);
        model.addAttribute("STAT", STAT);


        return "payment";
    }

    @GetMapping("/payment/{eId}/pdf")
    public void generateEmployeePdf(@PathVariable String eId, HttpServletResponse response) throws Exception {
        Employee employee = employeeMapper.toView(employeeService.getOne(eId));
        pdfService.generatePdfForEmployee(employee, response);
    }
    private final PDFService pdfService;


    @GetMapping("/")
    public String home() {
        return "redirect:/employee/list";
    }
}
