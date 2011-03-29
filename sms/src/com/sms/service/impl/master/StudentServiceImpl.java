package com.sms.service.impl.master;

import java.util.List;

import com.sms.bean.master.Student;
import com.sms.dao.master.StudentDAO;
import com.sms.service.master.StudentService;

public class StudentServiceImpl implements StudentService{
	
	private StudentDAO studentDAO;
	
	
	public StudentServiceImpl(){}
	

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public void delete(Student student) {
		getStudentDAO().delete(student);
	}

	@Override
	public int getCountAllStudent() {
		return getStudentDAO().getCountAllStudent();
	}

	@Override
	public List<Student> getListStudent(List<Object> columList, List<Object> valueList, int firstResult, int maxResult) {
		return getStudentDAO().getListStudent(columList, valueList, firstResult, maxResult);
	}

	@Override
	public void save(Student student) {
		getStudentDAO().save(student);
	}

	@Override
	public void saveOrUpdate(Student student) {
		getStudentDAO().saveOrUpdate(student);
	}


	@Override
	public Student getStudentById(long id) {
		return getStudentDAO().getStudentById(id);
	}


	@Override
	public long getCountStudentByCode(String nis) {
		return getStudentDAO().getCountStudentByCode(nis);
	}
	

}
