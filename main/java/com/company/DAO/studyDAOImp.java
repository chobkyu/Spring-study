package com.company.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.company.first.studyVO;

@Repository
public class studyDAOImp implements studyDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.company.mapper.studyMapper";
	
	@Override
	public List<studyVO> selectAll() {
		return sqlSession.selectList(namespace+".selectAll");
	}
	

}
