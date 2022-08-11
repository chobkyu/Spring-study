package com.company.DAO;

import java.util.HashMap;
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
	
	@Override
	public List<studyVO> listPage(int displayPost, int postNum) throws Exception{
		HashMap data = new HashMap();
		
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		
		return sqlSession.selectList(namespace+".listPage",data);
	}

	@Override
	public List<studyVO> search(String key){
		return sqlSession.selectList(namespace+".search",key);
	}
	
	@Override
	public List<studyVO> searchPage(int displayPost, int postNum,String key) throws Exception{
		HashMap data = new HashMap();
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		data.put("key",key);

		return sqlSession.selectList(namespace+".searchSelect",data);
	}
}
