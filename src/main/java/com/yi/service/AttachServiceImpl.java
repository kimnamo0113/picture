package com.yi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yi.domain.Attach;
import com.yi.domain.Creiteria;
import com.yi.persistence.AttachDao;

@Service
public class AttachServiceImpl implements AttachService{

	@Autowired
	private AttachDao dao;
	
	@Override
	public void insertAttach(Attach attach) {
		dao.insertAttach(attach);
	}
	@Override
	public List<Attach> listCreteria(Creiteria cri) throws Exception {
		return dao.listCreteria(cri);
	}
	@Override
	public int listCountCriteria() throws Exception {
		return dao.countPaging();
	}
	@Override
	public void delete(int bno) {
		dao.delete(bno);
	}
}
