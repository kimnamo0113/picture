package com.yi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yi.domain.Attach;
import com.yi.domain.Creiteria;
import com.yi.domain.Member;
import com.yi.domain.PageMaker;
import com.yi.service.AttachService;
import com.yi.util.UploadFileUtils;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
			
	private String innerUploadPath = "resources/upload";
	
	@Autowired
	private AttachService service;
			
	@Resource(name="uploadPath")
	private String outUploadPath;
	
	
	@RequestMapping(value="/displayFile",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> displayFile(String filename){
		logger.info("----------displayFile,filename="+filename);
		
		String formatName = filename.substring(filename.lastIndexOf(".")+1);//확장자만 뽑아냄
		MediaType mType = null;
		ResponseEntity<byte[]> entity;
		
		if(formatName.equalsIgnoreCase("jpg")) {
			mType=MediaType.IMAGE_JPEG;
		}
		else if(formatName.equalsIgnoreCase("gif")) {
			mType=MediaType.IMAGE_GIF;
		}
		else if(formatName.equalsIgnoreCase("png")) {
			mType=MediaType.IMAGE_PNG;
		}
		InputStream in =null;
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(outUploadPath+"/"+filename);
			headers.setContentType(mType);
			
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in),
					headers,
					HttpStatus.CREATED
					);
		}catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			try {
				if(in !=null)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return entity;
	}
	
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public void listPage(Model model,Creiteria cri) throws Exception {
		logger.info("---------- list");
		List<Attach> list = service.listCreteria(cri);
		model.addAttribute("list",list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria());
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	
	@RequestMapping(value="outUploadForm2",method=RequestMethod.POST)
	public ResponseEntity<List<String>> outUpload2POST(List<MultipartFile> files, Member member) {
		logger.info("--------------- outUpload2 POST");
		
		
		List<String> list = new ArrayList<>();
		
		
		ResponseEntity<List<String>> entity = null;
		
		try {
			for(MultipartFile file : files) {
				String savedName = UploadFileUtils.uploadFile(
						outUploadPath, 
						file.getOriginalFilename(), 
						file.getBytes()
						);
				list.add(savedName);
				Attach attach = new Attach();
				attach.setFullName(savedName);
				attach.setUserid(member.getUserid());
				attach.setOrgName(file.getOriginalFilename());
				service.insertAttach(attach);
			}
			entity = new ResponseEntity<List<String>>(list,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
		
		
		return entity;
	}
	
	
	@RequestMapping(value="delteImg",method=RequestMethod.GET)
	public String deleteImg(String imgFile,int bno,int page) {
		logger.info("-----------------delteImg");
		try {
			File file = new File(outUploadPath+"/"+imgFile.substring(0,12)+"s_"+imgFile.substring(12));
			File sfile = new File(outUploadPath+"/"+imgFile);
			logger.info("-----------------file:"+file);
			logger.info("-----------------sfile:"+sfile);
			logger.info("------------------bno:"+bno);
			logger.info("-----------------page:"+page);
			if(file.exists()) {
				logger.info("file exist"+sfile.getPath());
				logger.info("file exist"+file.getPath());
				sfile.delete();
				file.delete();
			}
			service.delete(bno);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:list?page="+page;
	}
}



