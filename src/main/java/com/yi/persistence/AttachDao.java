package com.yi.persistence;

import java.util.List;

import com.yi.domain.Attach;
import com.yi.domain.Creiteria;
import com.yi.domain.SearchCriteria;

public interface AttachDao {
	public void insertAttach(Attach attach);

	public List<Attach> listCreteria(Creiteria cri) throws Exception;
	public int countPaging() throws Exception;

	public void delete(int bno);
}
