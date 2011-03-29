package com.sms.controller.master.student;

import java.sql.Timestamp;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.sms.bean.master.Student;
import com.sms.common.util.ContexHelper;
import com.sms.common.util.ResourceHelper;
import com.sms.controller.CommonComposer;
import com.sms.service.master.StudentService;

public class StudentCreateController extends CommonComposer {

	/**
	 * Fajar Podolski
	 */
	private static final long serialVersionUID = 2987372181757715044L;

	private Window winStudentCreate;
	private Textbox txtNis;
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
		navigateTo(ResourceHelper.getResources("zul.studentList"), null, winStudentCreate);
	}

	private Student getStudent() {
		Student student = new Student();
		student.setNis(txtNis.getValue());
		student.setName(txtName.getValue());
		student.setAddress(txtAddress.getValue());
		student.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		student.setDeleted(new Integer(0));

		return student;
	}

	private int doInsert() {
		Student student = getStudent();
		Long flag = new Long(0);
		flag = studentService.getCountStudentByCode(txtNis.getValue());
		if (flag==0)
			studentService.save(student);

		return (int)flag.intValue();
	}

	public void onClick$btnSubmit(Event event) {
		if (doInsert() == new Integer(0))
			navigateTo(ResourceHelper.getResources("zul.studentList"), null, winStudentCreate);
		else
			alert("insert gagal");

	}

}
