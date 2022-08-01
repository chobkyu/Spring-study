package com.company.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.company.first.LoginVO;

@Repository
public class LoginDAOImp implements LoginDAO{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.company.mapper.login_sql";
	
	@Override
	public LoginVO getInfo() {
		return sqlSession.selectOne(namespace+".getInfo");
	}
}
