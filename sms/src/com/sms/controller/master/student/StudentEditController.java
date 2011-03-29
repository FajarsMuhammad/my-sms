package com.sms.controller.master.student;

import java.sql.Timestamp;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.sms.bean.master.Student;
import com.sms.common.util.ContexHelper;
import com.sms.controller.CommonComposer;
import com.sms.service.master.StudentService;

public class StudentEditController extends CommonComposer {

	/**
	 * Fajar Podolski
	 */
	private static final long serialVersionUID = 2987372181757715044L;

	private Window winStudentEdit;
	private Textbox txtId;
	private Textbox txtName;
	private Textbox txtAddress;

	private static final StudentService studentService = (StudentService) ContexHelper
			.getBean("studentService");

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	public void onClick$btnCancel(Event event) {
		navigateTo("WEB-INF/zul/master/studentList.zul", null, winStudentEdit);
	}

	private Student getStudent() {
		Student student = studentService.getStudentById(Long.parseLong(txtId.getValue()));
		student.setName(txtName.getValue());
		student.setAddress(txtAddress.getValue());
		student.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		student.setDeleted(new Integer(0));

		return student;
	}

	private boolean doEdit() {
		Student student = getStudent();
		boolean flag = true;
		if (flag)
			studentService.saveOrUpdate(student);

		return flag;
	}

	public void onClick$btnSubmit(Event event) {
		if (doEdit())
			navigateTo("WEB-INF/zul/master/studentList.zul", null, winStudentEdit);
		else
			alert("update gagal");

	}
	

}
