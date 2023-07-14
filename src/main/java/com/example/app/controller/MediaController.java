package com.example.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.dto.Media;
import com.example.app.service.MediaService;

@Controller
public class MediaController {

	@Autowired
	private MediaService mediaService;

	@GetMapping(value = "/display")
	public String displayPage(ModelMap map) {
		List<Media> fetchData = mediaService.fetchData();
		map.addAttribute("mediaData", fetchData);
		return "display";
	}

	@GetMapping(value = "/index")
	public String indexPage() {
		return "index";
	}

	@GetMapping(value = "/register")
	public String customerRegister() {
		return "index";
	}

	@PostMapping(value = "/savedata")
	public String saveMediaData(@ModelAttribute Media media, ModelMap map, RedirectAttributes attributes) {

		Media saveMediaData = mediaService.saveMediaData(media);

		if (saveMediaData != null) {
			map.addAttribute("email", saveMediaData.getEmail());
			map.addAttribute("location", saveMediaData.getLocation());
			map.addAttribute("name", saveMediaData.getName());
			map.addAttribute("number", saveMediaData.getNumber());

			attributes.addFlashAttribute("success", "Media Registration Successfully Completed...!!!!");

			return "redirect:/display";
		}

		attributes.addFlashAttribute("failed", "Media Registration not Successfully Completed...!!!!");
		return "redirect:/index";

	}

	@GetMapping(value = "/media/edit/{id}")
	public String showUpdateForm(@PathVariable int id, ModelMap map, RedirectAttributes attributes) {
		Optional<Media> findById = mediaService.findById(id);
		if (findById.isPresent()) {
			Media media = findById.get();
			map.addAttribute("media", media);
			return "updateform";

		}
		attributes.addFlashAttribute("failed", "Unable to open the form...!!!!");
		return "redirect:/display";

	}

	@PostMapping(value = "/media/update/{id}")
	public String saveUpdateData(@PathVariable int id, ModelMap map, RedirectAttributes attributes, Media media) {
		Optional<Media> findById = mediaService.findById(id);
		if (findById.isPresent()) {
			Media media2 = findById.get();
			System.out.println(media2);
			Media saveMediaData = mediaService.saveMediaData(media);
			System.out.println(saveMediaData);
			attributes.addFlashAttribute("success", "Media member data update Successfully...!!!!");
			map.addAttribute("mediaData", saveMediaData);
			return "redirect:/display";

		}
		attributes.addFlashAttribute("failed", "Unable to open the form...!!!!");
		return "redirect:/display";

	}

	@GetMapping(value = "/media/delete/{id}")
	public String deleteData(@PathVariable int id, RedirectAttributes attributes) {
		mediaService.deleteData(id);

		attributes.addFlashAttribute("delete", "Data Deleted Successfully...!!!");
		return "redirect:/display";

	}

}
