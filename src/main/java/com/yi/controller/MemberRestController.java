package com.yi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yi.service.MemberService;

@RestController
@RequestMapping("/auth/*")
public class MemberRestController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private MemberService service;

	// 중복체크용
	@RequestMapping(value = "join/id", method = RequestMethod.POST)
	public ResponseEntity<Integer> id(@RequestParam("userid") String userid) {
		logger.info("----------------idPOST id=" + userid);
		ResponseEntity<Integer> entity = null;

		try {
			int res = service.selectIdCheck(userid);
			logger.info("res=" + res);
			entity = new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// 중복체크용
	@RequestMapping(value = "join/email", method = RequestMethod.POST)
	public ResponseEntity<Integer> email(@RequestParam("email") String email) {
		logger.info("----------------gEmailPOST email=" + email);
		ResponseEntity<Integer> entity = null;

		try {
			int res = service.selectEmailCheck(email);
			logger.info("res=" + res);
			entity = new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}


}
