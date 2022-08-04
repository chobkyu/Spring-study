package com.company.first;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class imgVO {
	int seq;
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	String title;
	String content;
	byte[] img;
	
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	private Map<String, Object> hmap;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, Object> getHmap() {
		return hmap;
	}

	public String setHmap(Map<String, Object> hmap) {
		this.hmap = hmap;
		String img = "img";
		return img;
	}

	private MultipartFile imgFile;

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
}
