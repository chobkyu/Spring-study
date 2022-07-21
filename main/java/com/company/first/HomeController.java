package com.company.first;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.company.*;
import com.company.DAO.MultiImgDAO;
import com.company.DAO.imgDAO;
import com.company.DAO.memberDAO;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private DataSource dataSource;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@Inject
	private memberDAO mDao;
	@Inject
	private imgDAO iDao;
	@Inject
	private MultiImgDAO mtDao;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		memberVO mVo = new memberVO();
		
		List<memberVO> mlist = new ArrayList<memberVO>();
		
		mlist = mDao.selectAll();
		
		model.addAttribute("list",mlist);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/img", method = RequestMethod.GET)
	public String img(Locale locale, Model model) {
		
		return "img";
	}
	
	/**
	 * ����ó�� ��Ʈ�ѷ�
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/saveImage", method=RequestMethod.POST)
	public String saveImage(HttpServletRequest request, String title, String content){
		MultipartHttpServletRequest mhsr=(MultipartHttpServletRequest) request;

		System.out.println(title+"  "+content);
		byte[] file= "0".getBytes();
		try {
			file = mhsr.getFile("imgFile").getBytes();
			if(file.length==0) {
				return "img";
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		/* ���� ���ÿ� �����ϱ�
		try {
		    OutputStream output = new FileOutputStream("C:\\Users\\NCL-NT-0163\\Desktop\\Output.png");
		    byte[] by = file;
		    output.write(by);
				
		} catch (Exception e) {
	            e.getStackTrace();
		}*/
		try {
			imgVO vo = new imgVO();
			vo.setContent(content);
			vo.setTitle(title);
			vo.setImg(file);
			
	
			iDao.saveImage(vo);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "redirect:/formFile";
	}
	
	@RequestMapping(value="/formFile")
	public String formFile(Model model) {
		List<imgVO> list = iDao.selectAll();
		model.addAttribute("list",list);
		return "image";
	}
	
	@RequestMapping(value="/getByteImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getByteImage(HttpServletRequest request) {//ResponseEntity�� HttpEntity�� ��ӹ������ν� HttpHeader�� body�� ���� �� ����
		String a = request.getParameter("number");
		int temp = Integer.parseInt(a) ;
		
		//List<imgVO> list = 
		//System.out.println(list.size());
		imgVO vo = new imgVO();
		vo =iDao.getByteImage(temp);
	    byte[] imageContent = vo.img;
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);  //�̵�� Ÿ���� ��Ÿ���� ���� ���(����� Ŭ���̾�Ʈ�� ������ ��û �Ǵ� �������� �ΰ����� ������ ������ �� �ְ� ����)
	    return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {
		/*
		List<imgVO> list = iDao.getByteImage();
		System.out.println(list.size());

	    byte[] imageContent = list.get(10).img;
	    request.setAttribute("bydata", imageContent);*/
		
		String num =(request.getParameter("num"));
		
		imgVO vo = new imgVO();
		vo = iDao.getByteImage(Integer.parseInt(num));
		
		String imgUrl = "/getByteImage?number="+num;
		
		model.addAttribute("board",vo);
		request.setAttribute("imgSrc", imgUrl);
		return "imgView";
	}
	
	@RequestMapping(value="/multi", method = RequestMethod.GET)
	public String multiImgEnroll() {
		return "multiimg";
	
	}
	
	@RequestMapping(value="/multi-file", method = RequestMethod.POST)
	public String multiImg(@RequestParam("multiFile") List<MultipartFile> multiFileList, HttpServletRequest request, Model model) {
		//MultipartFile mhsr=(MultipartFile) request;
		List<byte[]> list = new ArrayList<byte[]>();
		byte[] file= "0".getBytes();
		
		for(int i=0; i<multiFileList.size();i++) {
			
			try {
				file = multiFileList.get(i).getBytes();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			list.add(file);
		}
		System.out.println(list.size());
		
		System.out.println(list.get(0).length);
		
		for(int i=0; i<list.size();i++) {
			MultiImgVO vo = new MultiImgVO();
			byte[] fileEnroll = list.get(i);
			vo.setImg(fileEnroll);
			//System.out.println();
			mtDao.saveImage(vo);
		}
		return "home";
	
	
	}
	
}
