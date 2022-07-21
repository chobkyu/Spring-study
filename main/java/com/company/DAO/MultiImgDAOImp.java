package com.company.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.company.first.MultiImgVO;

@Repository
public class MultiImgDAOImp implements MultiImgDAO{
	
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.company.mapper.MultiImgMapper";

	@Override
	public void saveImage(MultiImgVO vo) {
		sqlSession.insert(namespace+".enroll",vo);
	}
}
