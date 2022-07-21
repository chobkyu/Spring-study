package com.company.DAO;
import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.company.first.memberVO;

@Repository
public class memberDAOImp implements memberDAO{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.company.mapper.memberMapper";
	
	@Override
	public List<memberVO> selectAll(){
		return sqlSession.selectList(namespace+".selectAll");
	}
}
