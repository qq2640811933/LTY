package com.entor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.entor.entity.Student;
import com.entor.mapper.StudentMapper;
import com.entor.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private StudentMapper studentMapper;
	@Override
	public void add(Student student) {
		studentMapper.add(student);
	}

	@Override
	public void update(Student student) {
		studentMapper.update(student);
	}

	@Override
	@CacheEvict(allEntries=true)//清除该组中的所有缓存
	public void deleteById(int id) {
		studentMapper.deleteById(id);
	}

	@Override
	@Cacheable(key="'student_'+#p0")
	public Student queryById(int id) {
		return studentMapper.queryById();
	}

	@Override
	@Cacheable(key="'student_'+#p0")
	public List<Student> queryByPage(int currentPage, int pageSize) {
		return studentMapper.queryByPage(currentPage, pageSize);
	}

	@Override
	public List<Student> queryAll() {
		return studentMapper.queryAll();
	}

}
