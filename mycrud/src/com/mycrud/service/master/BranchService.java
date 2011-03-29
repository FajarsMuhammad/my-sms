package com.mycrud.service.master;

import java.util.List;

import com.mycrud.bean.master.Branch;

public interface BranchService {
	
	public List<Branch> getAllBranchs();
	
	void saveOrUpdate(Branch branch);

	void delete(Branch branch);
	
	void save(Branch branch);

}
