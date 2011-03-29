package com.sms.dao.master;

import java.util.List;

import com.sms.bean.master.Student;

public interface StudentDAO {
	
	public List<Student> getListStudent(List<Object> columList, List<Object> valueList, int firstResult, int maxResult);
	
	public void saveOrUpdate(Student student);

	public void delete(Student student);

	public void save(Student student);
	
	public int getCountAllStudent();
	
	public Long getCountStudentByCode(String nis);
	
	public Student getStudentById(long id);

}
