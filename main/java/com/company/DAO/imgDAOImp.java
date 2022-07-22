package com.company.DAO;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.company.first.imgVO;

@Repository
public class imgDAOImp implements imgDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.company.mapper.imgMapper";
	
	@Override
	public void saveImage(imgVO vo) {
		sqlSession.insert(namespace+".insert",vo);
	}
	
	@Override
	public imgVO getByteImage(int a){
		return sqlSession.selectOne(namespace+".select",a);
	}
	
	@Override
	public List<imgVO> selectAll(){
		return sqlSession.selectList(namespace+".selectAll");
	}
}
