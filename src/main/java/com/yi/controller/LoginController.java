package com.yi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yi.domain.Attach;
import com.yi.domain.LoginDTO;
import com.yi.domain.Member;
import com.yi.domain.PageMaker;
import com.yi.domain.SearchCriteria;
import com.yi.service.AttachService;
import com.yi.service.MemberService;

@RequestMapping("/auth/*")
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	MemberService service;
	
	
	
	@RequestMapping(value="join",method=RequestMethod.GET)
	public void joinGET() {
		logger.info("-------------------join GET");
	}
	
	@RequestMapping(value="join",method=RequestMethod.POST)
	public String joinPOST(Member member) {
		logger.info("-------------------joinPost guest="+member);
		
		service.insertJoinDefault(member);
		return "redirect:login";
	}
	
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public void loginGET() {
		logger.info("-------------------login GET");
	}
	
	@RequestMapping(value="loginPost",method=RequestMethod.POST)
	public void loginPOST(Member member, Model model) {
		logger.info("-------------------login POST,"+member);
		
		Member dbMember = service.selectMemberByIdAndPw(member.getUserid(), member.getUserpw());
		
		if(dbMember==null) {
			logger.info("loginPOST...login fail,not member");
			return;
		}
		LoginDTO dto = new LoginDTO();
		dto.setUserid(dbMember.getUserid());
		dto.setUsername(dbMember.getUsername());
		model.addAttribute("loginDTO",dto);
	}
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("logout ......");
		session.invalidate();
		return "redirect:/";
	}
	
	
}



