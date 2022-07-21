package com.company.DAO;

import java.util.List;
import java.util.Map;

import com.company.first.imgVO;


public interface imgDAO {
	public void saveImage(imgVO vo);
	public imgVO getByteImage(int a);
	public List<imgVO> selectAll();
}
