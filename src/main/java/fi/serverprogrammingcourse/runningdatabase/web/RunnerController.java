package fi.serverprogrammingcourse.runningdatabase.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.serverprogrammingcourse.runningdatabase.domain.Runner;
import fi.serverprogrammingcourse.runningdatabase.domain.RunnerRepository;
import fi.serverprogrammingcourse.runningdatabase.domain.SexRepository;

@Controller
public class RunnerController {
	@Autowired
	private RunnerRepository rrepository;
	
	@Autowired
	private SexRepository srepository;
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	@GetMapping(value= {"/runnerlist"})
	public String runnerList(Model model) {
		System.out.println("controller.runnerList");
		model.addAttribute("runners", rrepository.findAll());
		return "runnerlist";
	}
	
	@GetMapping(value = "/add")
	public String addRunner(Model model){
		model.addAttribute("runner", new Runner());
		model.addAttribute("sexes", srepository.findAll());
		
		return "addrunner";
	}     
		    
	
	@PostMapping("/save")
	public String saveRunner(@Valid Runner runner, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			model.addAttribute("sexes", srepository.findAll());
			return "addrunner";
	        }
		rrepository.save(runner);
	 	return "redirect:/runnerlist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/delete/{id}")
	public String deleteRunner(@PathVariable("id") Long runnerId, Model model) {
		rrepository.deleteById(runnerId);
		return "redirect:/runnerlist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/edit/{id}")
	public String editRunner(@PathVariable("id") Long id, Model model) {
		model.addAttribute("runner", rrepository.findById(id));
		model.addAttribute("sexes", srepository.findAll());
		return "editRunner";
	}
}
