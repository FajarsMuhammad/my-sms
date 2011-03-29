package com.sms.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sms.bean.master.Branch;
import com.sms.service.master.BranchService;

public class Test {

	public static void main(String[] args) {

		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customize-applicationContext.xml");

		BranchService branchService = (BranchService) ctx.getBean("branchService");

		Branch branch = new Branch();
		branch.setId("126");
		branch.setBranchName("Bandung");
		
		branchService.save(branch);
		List<Branch> branchs = branchService.getAllBranchs();
		for(Branch bra : branchs)
			System.out.println("masuuk=="+bra.getBranchName());
	}

}
