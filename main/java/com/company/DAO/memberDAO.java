package com.company.DAO;

import java.util.List;
import javax.inject.Inject;
import com.company.first.memberVO;

public interface memberDAO {
	public List<memberVO> selectAll();
}
