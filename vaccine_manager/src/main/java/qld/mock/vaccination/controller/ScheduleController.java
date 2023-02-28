package qld.mock.vaccination.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qld.mock.vaccination.dto.ScheduleDto;
import qld.mock.vaccination.entities.Schedule;
import qld.mock.vaccination.entities.Vaccine;
import qld.mock.vaccination.service.ScheduleService;
import qld.mock.vaccination.service.VaccineService;
import qld.mock.vaccination.utils.ControllerUtils;

@Controller
@RequestMapping("injection-schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private VaccineService vaccineService;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String cheduleList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "5") Integer size) {

		Page<Schedule> schedules = scheduleService.getListSchedules(page - 1, size);

		model.addAttribute("pages", schedules);

		LocalDate nowDate = LocalDate.now();

		model.addAttribute("nowDate", nowDate);

		int[] body = ControllerUtils.GenderPage(schedules);

		model.addAttribute("body", body);

		return "schedule/listSchedule";

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String SearchSchedules(Model model, @RequestParam(value = "keyWord", required = false) String keyWord,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "5") Integer size) {

		Page<Schedule> schedules = scheduleService.getListSchedulesByKeyWord(page - 1, size, keyWord);
		model.addAttribute("pages", schedules);

		model.addAttribute("keyWord", keyWord);

		LocalDate nowDate = LocalDate.now();

		model.addAttribute("nowDate", nowDate);

		int[] body = ControllerUtils.GenderPage(schedules);

		model.addAttribute("body", body);

		return "schedule/listSchedule";

	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createSchedule(Model model) {

		ScheduleDto scheduleDto = new ScheduleDto();
		model.addAttribute("scheduleDto", scheduleDto);

		List<Vaccine> vaccines = vaccineService.getVaccines();
		model.addAttribute("vaccines", vaccines);
		return "schedule/create-schedule";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String submitCreateSchedule(@Valid @ModelAttribute("scheduleDto") ScheduleDto scheduleDto,
			BindingResult bindingResult, RedirectAttributes redirAttrs, Model model) {

		if (bindingResult.hasErrors()) {
			List<Vaccine> vaccines = vaccineService.getVaccines();
			model.addAttribute("vaccines", vaccines);
			return "schedule/create-schedule";
		}

		if (scheduleDto.getStartDate().isAfter(scheduleDto.getEndDate())) {
			ObjectError error = new ObjectError("startDate", "Start date must be less than end date.");
			bindingResult.rejectValue("startDate", "error.scheduleDto", "Start date must be less than end date.");
			List<Vaccine> vaccines = vaccineService.getVaccines();
			model.addAttribute("vaccines", vaccines);
			return "schedule/create-schedule";
		}

		Schedule schedule = modelMapper.map(scheduleDto, Schedule.class);

		if (scheduleService.createSchedule(schedule, scheduleDto.getVaccineName())) {
			redirAttrs.addFlashAttribute("success", "Create successfully!");
			return "redirect:/injection-schedule/new";
		}

		redirAttrs.addFlashAttribute("error", "Create failure!");
		return "redirect:/injection-schedule/new";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateSchedule(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "reset", required = false, defaultValue = "false") Boolean reset, Model model) {

		ScheduleDto scheduleDto = new ScheduleDto();

		if (reset == false) {
			Schedule schedule = scheduleService.getById(Integer.parseInt(id));
			scheduleDto = modelMapper.map(schedule, ScheduleDto.class);
			scheduleDto.setVaccineName(schedule.getVaccine().getVaccineName());
		}

		model.addAttribute("scheduleDto", scheduleDto);
		model.addAttribute("id", id);

		List<Vaccine> vaccines = vaccineService.getVaccines();
		model.addAttribute("vaccines", vaccines);

		return "schedule/update-schedule";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String SubmitUpdateSchedule(@RequestParam(value = "id", required = true) String id,
			@Valid @ModelAttribute("scheduleDto") ScheduleDto scheduleDto, BindingResult bindingResult,
			RedirectAttributes redirAttrs, Model model) {

		if (bindingResult.hasErrors()) {
			List<Vaccine> vaccines = vaccineService.getVaccines();
			model.addAttribute("id", id);
			model.addAttribute("vaccines", vaccines);
			return "schedule/update-schedule";
		}

		if (scheduleDto.getStartDate().isAfter(scheduleDto.getEndDate())) {
			ObjectError error = new ObjectError("startDate", "Start date must be less than end date.");
			bindingResult.rejectValue("startDate", "error.scheduleDto", "Start date must be less than end date.");
			List<Vaccine> vaccines = vaccineService.getVaccines();
			model.addAttribute("vaccines", vaccines);
			model.addAttribute("id", id);
			return "schedule/update-schedule";
		}

		Schedule schedule = modelMapper.map(scheduleDto, Schedule.class);

		Vaccine vaccine = vaccineService.getVaccineByVaccineName(scheduleDto.getVaccineName());

		schedule.setVaccine(vaccine);

		if (scheduleService.updateSchedule(schedule, Integer.parseInt(id))) {
			redirAttrs.addFlashAttribute("success", "Update successfully!");
			return "redirect:/injection-schedule/list";

		}

		redirAttrs.addFlashAttribute("error", "Update failure!");
		return "redirect:/injection-schedule/list";
	}

}
