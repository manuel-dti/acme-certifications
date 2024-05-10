/*
 * AnnouncementCustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import services.CustomerService;
import controllers.AbstractController;
import domain.Announcement;

@Controller
@RequestMapping("/announcement/customer")
public class AnnouncementCustomerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AnnouncementService	announcementService;
	@Autowired
	private CustomerService		customerService;


	// Constructors -----------------------------------------------------------

	public AnnouncementCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Announcement> announcements;
		Collection<Announcement> registeredAnnouncements;

		announcements = this.announcementService.findAllActive();
		registeredAnnouncements = this.announcementService.findRegistered();

		result = new ModelAndView("announcement/list");
		result.addObject("requestURI", "announcement/customer/list.do");
		result.addObject("announcements", announcements);
		result.addObject("registeredAnnouncements", registeredAnnouncements);

		return result;
	}

	// Registration -----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam final int announcementId) {
		ModelAndView result;

		try {
			this.customerService.registerPrincipal(announcementId);
			result = this.list();
			result.addObject("message", "announcement.commit.ok");
		} catch (final Throwable oops) {
			result = this.list();
			result.addObject("message", "announcement.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/unregister", method = RequestMethod.GET)
	public ModelAndView unregister(@RequestParam final int announcementId) {
		ModelAndView result;

		try {
			this.customerService.unregisterPrincipal(announcementId);
			;
			result = this.list();
			result.addObject("message", "announcement.commit.ok");
		} catch (final Throwable oops) {
			result = this.list();
			result.addObject("message", "announcement.commit.error");
		}

		return result;
	}

}
