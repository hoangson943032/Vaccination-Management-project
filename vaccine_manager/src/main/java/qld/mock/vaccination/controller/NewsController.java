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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import qld.mock.vaccination.dto.NewsDto;
import qld.mock.vaccination.entities.News;
import qld.mock.vaccination.service.NewsService;
import qld.mock.vaccination.utils.ControllerUtils;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	NewsService newsService;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(value = "/list-news", method = RequestMethod.GET)
	public String NewsList(Model model,

			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "5") Integer size) {

		Page<News> news = newsService.getListNews(page - 1, size);

		model.addAttribute("pages", news);

		LocalDate nowDate = LocalDate.now();

		model.addAttribute("nowDate", nowDate);

		int[] body = ControllerUtils.GenderPage(news);

		model.addAttribute("body", body);
		return "news/ListNews";
	}

	@RequestMapping(value = "/search-news", method = RequestMethod.GET)
	public String SearchNews(Model model, @RequestParam(value = "keyWord", required = false) String keyWord,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {

		Page<News> news = newsService.getListNewsByKeyWord(page - 1, 5, keyWord);
		model.addAttribute("pages", news);
		int totalPages = news.getTotalPages();
		model.addAttribute("totalPages", totalPages);

		model.addAttribute("keyWord", keyWord);
		return "news/ListNews";

	}

	@RequestMapping(value = "/create-news", method = RequestMethod.GET)
	public String createNews(Model model) {
			
		NewsDto newsDto = new NewsDto();
		model.addAttribute("newsDto", newsDto);

		return "news/create";
	}

	@RequestMapping(value = "/create-news", method = RequestMethod.POST)
	public String submitCreateNews(@Valid @ModelAttribute("newsDto") NewsDto newsDto,BindingResult bindingResult, RedirectAttributes redirAttrs, Model model) {

		News news = modelMapper.map(newsDto, News.class);
		
		
		if (bindingResult.hasErrors()) {
			
			return "news/create";
		}

		if (newsService.createNews(news)) {
			redirAttrs.addFlashAttribute("success", "Create successfully!");
			return "redirect:/news/create-news";
		}

		redirAttrs.addFlashAttribute("error", "Create failure!");
		return "redirect:/news/list-news";
	}

	@RequestMapping(value = "/update-news", method = RequestMethod.GET)
	public String updateNews(@RequestParam(value = "id", required = true) String id, Model model) {

		News news = newsService.getById(Integer.parseInt(id));

		NewsDto newsDto = modelMapper.map(news, NewsDto.class);
		newsDto.setContent(news.getContent());
		newsDto.setTitle(news.getTitle());
		newsDto.setPreview(news.getPreview());
		model.addAttribute("newsDto", newsDto);
		model.addAttribute("id", id);
		return "news/update";
	}

	@RequestMapping(value = "/update-news", method = RequestMethod.POST)
	public String SubmitUpdateNews(@RequestParam(value = "id", required = true) String id,
			@ModelAttribute("newsDto") NewsDto newsDto, RedirectAttributes redirAttrs) {

		News news = modelMapper.map(newsDto, News.class);

		if (newsService.updateNews(news, Integer.parseInt(id))) {
			redirAttrs.addFlashAttribute("success", "Update successfully!");
			return "redirect:/news/list-news";

		}
		redirAttrs.addFlashAttribute("error", "Update failure!");
		return "redirect:news/list-news";
	}
}
