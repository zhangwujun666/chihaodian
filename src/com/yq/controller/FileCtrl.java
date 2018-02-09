package com.yq.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.weixin.entity.WxSetting;
import com.weixin.service.WxSettingService;

@Controller
@RequestMapping("/main")
public class FileCtrl {
	@Autowired
	private WxSettingService wxSettingService;
	
	@ResponseBody
	@RequestMapping(value="/upload.html")
	public Object upload(@RequestParam MultipartFile file,HttpServletRequest request){
			String realpath = request.getSession().getServletContext().getRealPath(""); 
			String path = "";
			if(realpath.contains("\\")){
				 path = realpath.substring(0,realpath.lastIndexOf("\\"))+"/upload";
			}else{
				 path = realpath.substring(0,realpath.lastIndexOf("/"))+"/upload";
			}
			System.out.println("path="+path);
//			String fileName = file.getOriginalFilename();  
	        String fileName = new Date().getTime()+".png";  
	        
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	  
	        //保存  
	        try {  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        WxSetting ws = wxSettingService.selectByPrimaryKey(1);
	        String link = ws.getLink();
	        if(StringUtils.isNotEmpty(link)){
	        	link = link.replace("/chihaodian", "");
	        }
	        String url = link+"/upload/"+fileName;
//	        StringBuffer url = request.getRequestURL();  
//			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").append("upload/")
//					.append(fileName).toString();
			System.out.println(url);
			return url;  
	}
}
