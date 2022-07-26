package com.company.first;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.apache.commons.dbcp2.BasicDataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.company.*;
import com.company.DAO.MultiImgDAO;
import com.company.DAO.imgDAO;
import com.company.DAO.memberDAO;
import com.company.DAO.studyDAO;
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
	@Inject
	private studyDAO sDao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		memberVO mVo = new memberVO();
		List<memberVO> mlist = new ArrayList<memberVO>();
		mlist = mDao.selectAll();
		
		studyVO sVo = new studyVO();
		List<studyVO> slist = new ArrayList<studyVO>();
		slist = sDao.selectAll();
		
		model.addAttribute("list",mlist);
		model.addAttribute("slist",slist);
		
		return "home";
	}
	
	@RequestMapping(value = "/img", method = RequestMethod.GET)
	public String img() {
		
		return "img";
	}
	
	/**
	 * ����ó�� ��Ʈ�ѷ�
	 * @param vo
	 * @return
	 */
	
	//이미지 저장하기
	@RequestMapping(value="/saveImage", method=RequestMethod.POST)
	public String saveImage(HttpServletRequest request, String title, String content){
		MultipartHttpServletRequest mhsr=(MultipartHttpServletRequest) request;
		imgVO vo = new imgVO();
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
		/* 로컬에 파일로 저장
		try {
		    OutputStream output = new FileOutputStream("C:\\Users\\NCL-NT-0163\\Desktop\\Output.png");
		    byte[] by = file;
		    output.write(by);
				
		} catch (Exception e) {
	            e.getStackTrace();
		}*/
		try {
			
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
	
	//이미지 페이지(<img>)에서 불러오기
	@RequestMapping(value="/getByteImage", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getByteImage(HttpServletRequest request) {//ResponseEntity�� HttpEntity�� ��ӹ������ν� HttpHeader�� body�� ���� �� ����
		imgVO vo = new imgVO();
		String a = request.getParameter("number");
		int temp = Integer.parseInt(a) ;
		
		//List<imgVO> list = 
		//System.out.println(list.size());
		
		vo = iDao.getByteImage(temp);
	    byte[] imageContent = vo.img;
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);  //�̵�� Ÿ���� ��Ÿ���� ���� ���(����� Ŭ���̾�Ʈ�� ������ ��û �Ǵ� �������� �ΰ����� ������ ������ �� �ְ� ����)
	    return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	
	//이미지 불러오기
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		/*
		List<imgVO> list = iDao.getByteImage();
		System.out.println(list.size());

	    byte[] imageContent = list.get(10).img;
	    request.setAttribute("bydata", imageContent);*/
		
		String num =(request.getParameter("num"));
		
		imgVO vo = new imgVO();
		vo = iDao.getByteImage(Integer.parseInt(num));
		
		String imgUrl = "/getByteImage?number="+num;
		
		request.setAttribute("board",vo);
		request.setAttribute("imgSrc", imgUrl);
		return "imgView";
	}
	
	@RequestMapping(value="/multi", method = RequestMethod.GET)
	public String multiImgEnroll() {
		return "multiimg";
	
	}
	
	//다중 사진 업로드
	@RequestMapping(value="/multi-file", method = RequestMethod.POST)
	public String multiImg(@RequestParam("multiFile") List<MultipartFile> multiFileList) {
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
	
	//지도 띄우기 및 공공데이터 API 연동 밍 xml 파싱
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String Map(Locale locale, Model model) throws IOException, ParserConfigurationException, SAXException{
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/3160000/guroPm25DvcInfoSvc/getGuroComSvCtPm25Dvc"); /*URL*/
        urlBuilder.append("?serviceKey=oR3vMDMK0NkbPa3T72RkCwTELh9R1cOXts2rpMELU4sMNcURyBxNvHgrTgKjNk3farBcSmtVUM8kJ1zvNgJVEQ%3D%3D&numOfRows=10&pageNo=1&returnType=xml"); /*Service Key*/
       
        
        String urlString = urlBuilder.toString();
        System.out.println(urlString);
        urlString.replaceAll("\n", "");
        urlString.replaceAll(" ", "");
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String s = sb.toString();
        System.out.println(sb.toString());
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(urlString);
        
        // root tag 
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        // Root element: response
		
        // 파싱할 tag
        NodeList nList = doc.getElementsByTagName("item");
        System.out.println("파싱할 리스트 수 : "+ nList.getLength());  // 파싱할 리스트 수 
        
        String lat="";
        String lon="";
        Node nNode = nList.item(0);
        if(nNode.getNodeType() == Node.ELEMENT_NODE){
        	Element eElement = (Element) nNode;
        	lat = getTagValue("lat",eElement);
        	lon = getTagValue("lon",eElement);
        	System.out.println(lat+"  "+lon);
        }
        
        model.addAttribute("lat",lat);
        model.addAttribute("lon",lon);
		return "map";
		
	}
	
	//공공데이터 연결 및 xml 파싱
	@RequestMapping(value = "/ApiTest", method = RequestMethod.GET)
	public String ApiTest(Locale locale, Model model) throws IOException, ParserConfigurationException, SAXException {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/3160000/guroPm25DvcInfoSvc/getGuroComSvCtPm25Dvc"); /*URL*/
        urlBuilder.append("?serviceKey=oR3vMDMK0NkbPa3T72RkCwTELh9R1cOXts2rpMELU4sMNcURyBxNvHgrTgKjNk3farBcSmtVUM8kJ1zvNgJVEQ%3D%3D&numOfRows=10&pageNo=1&returnType=xml"); /*Service Key*/
       
        
        String urlString = urlBuilder.toString();
        System.out.println(urlString);
        urlString.replaceAll("\n", "");
        urlString.replaceAll(" ", "");
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String s = sb.toString();
        System.out.println(sb.toString());
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(urlString);
        
        // root tag 
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        // Root element: response
		
        // 파싱할 tag
        NodeList nList = doc.getElementsByTagName("item");
        System.out.println("파싱할 리스트 수 : "+ nList.getLength());  // 파싱할 리스트 수 
        
        for(int i =0; i<nList.getLength();i++) {
        	Node nNode = nList.item(i);
        	if(nNode.getNodeType() == Node.ELEMENT_NODE){
        		Element eElement = (Element) nNode;
        		System.out.println(getTagValue("lon",eElement));
        	}
        }
        return "ApiTest";
	}
	
	
	private static String getTagValue(String tag, Element eElement) {
		    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		    Node nValue = (Node) nlList.item(0);
		    if(nValue == null) 
		        return null;
		    return nValue.getNodeValue();
	}
	
	//공공데이터 연결 및 json 파싱
		@RequestMapping(value = "/Api", method = RequestMethod.GET)
		public String Api(Locale locale, Model model) throws IOException, ParserConfigurationException, SAXException, ParseException{
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty"); /*URL*/
     
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=oR3vMDMK0NkbPa3T72RkCwTELh9R1cOXts2rpMELU4sMNcURyBxNvHgrTgKjNk3farBcSmtVUM8kJ1zvNgJVEQ%3D%3D"); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("stationName","UTF-8")+ "="+ URLEncoder.encode("종로구", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
	        urlBuilder.append("&" + URLEncoder.encode("dataTerm","UTF-8") + "=" + URLEncoder.encode("daily", "UTF-8")); /*한 페이지 결과 수(조회 날짜로 검색 시 사용 안함)*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호(조회 날짜로 검색 시 사용 안함)*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*통보시간 검색(조회 날짜 입력이 없을 경우 한달동안 예보통보 발령 날짜의 리스트 정보를 확인)*/
	        //urlBuilder.append("&" + URLEncoder.encode("InformCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*통보코드검색(PM10, PM25, O3)*/
	        URL url = new URL(urlBuilder.toString());
	        System.out.println(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        
	        
	        String jsonApi = (sb.toString());
	        System.out.println("jsonApi의 값 : "+jsonApi);
	        
	        
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonApi);
	        System.out.println(jsonObject);
	        
	        JSONObject response = (JSONObject) jsonObject.get("response");
	        System.out.println(response);
	        
	        JSONObject body = (JSONObject) response.get("body");
	        System.out.println(body);

	        JSONArray bodyArray = (JSONArray) body.get("items");
	        
	        
	        System.out.println(bodyArray.size());
	        for(int i=0; i<bodyArray.size();i++) {
	        	System.out.println("======== person : " + i + " ========");
                JSONObject personObject = (JSONObject) bodyArray.get(i);
                
                System.out.println(personObject.get("so2Grade"));
                System.out.println(personObject.get("coFlag"));
                System.out.println(personObject.get("khaiValue"));
                System.out.println(personObject.get("so2Value"));
                System.out.println(personObject.get("coValue"));
            

	        }
	        return "ApiTest";
		}
	
	
}
