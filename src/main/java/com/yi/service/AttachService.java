package com.yi.service;

import java.util.List;

import com.yi.domain.Attach;
import com.yi.domain.Creiteria;

public interface AttachService {
	public void insertAttach(Attach attach);
	public List<Attach> listCreteria(Creiteria cri) throws Exception;
	public int listCountCriteria() throws Exception;
	public void delete(int bno);
}
