package com.company.DAO;

import java.util.List;

import com.company.first.studyVO;

public interface studyDAO {
	public List<studyVO> selectAll(); 
	public List<studyVO> listPage(int displayPost, int postNum) throws Exception;
	public List<studyVO> search(String key);
}
