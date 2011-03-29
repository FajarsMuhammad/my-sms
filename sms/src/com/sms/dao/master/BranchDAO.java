package com.sms.dao.master;

import java.util.List;

import com.sms.bean.master.Branch;


public interface BranchDAO{
	
	public List<Branch> getAllBranchs();
	
	public void saveOrUpdate(Branch branch);

	public void delete(Branch branch);

	public void save(Branch branch);

	public void refresh(Branch branch);

	public void initialize(Branch branch);

}
