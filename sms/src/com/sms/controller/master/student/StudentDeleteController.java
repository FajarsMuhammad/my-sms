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

public class StudentDeleteController extends CommonComposer {

	/**
	 * Fajar Podolski
	 */
	private static final long serialVersionUID = 2987372181757715044L;

	private Window winStudentDelete;
	private Textbox txtId;
	
	private static final StudentService studentService = (StudentService) ContexHelper
			.getBean("studentService");

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}

	public void onClick$btnCancel(Event event) {
		navigateTo(ResourceHelper.getResources("zul.studentList"), null, winStudentDelete);
	}

	private Student getStudent() {
		Student student = studentService.getStudentById(Long.parseLong(txtId.getValue()));
		student.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		student.setDeleted(new Integer(1));

		return student;
	}

	private boolean doDelete() {
		Student student = getStudent();
		boolean flag = true;
		if (flag)
			studentService.saveOrUpdate(student);

		return flag;
	}

	public void onClick$btnSubmit(Event event) {
		if (doDelete())
			navigateTo(ResourceHelper.getResources("zul.studentList"), null, winStudentDelete);
		else
			alert("delete gagal");

	}

}
