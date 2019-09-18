package com.yi.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yi.domain.Attach;
import com.yi.domain.Creiteria;

@Repository
public class AttachDaoImpl implements AttachDao{
	private static final String namespace = "com.yi.mapper.AttachMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertAttach(Attach attach) {
		sqlSession.insert(namespace+".insertAttach",attach);
	}

	
	@Override
	public List<Attach> listCreteria(Creiteria cri) throws Exception {
		return sqlSession.selectList(namespace+".listCreteria",cri);
	}
	@Override
	public int countPaging() throws Exception {
		return sqlSession.selectOne(namespace+".countPaging");
	}


	@Override
	public void delete(int bno) {
		sqlSession.delete(namespace+".delete",bno);
	}
}
