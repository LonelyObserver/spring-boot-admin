package com.expect.admin.data.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expect.admin.data.dataobject.Department;

public interface DepartmentRepository extends JpaRepository<Department, String> {

	/**
	 * 根据部门代码获取部门
	 */
	public Department findByCode(String code);

	/**
	 * 根据parentId获取所有的子department
	 */
	public List<Department> findByParentDepartmentId(String parentId);

	/**
	 * 根据parentId获取所有的子department
	 */
	public List<Department> findByParentDepartmentIdIn(String[] parentIds);

	/**
	 * 根据ids获取部门
	 * 
	 * @param ids
	 *            用,分隔的id
	 */
	public Set<Department> findByIdIn(String[] ids);
	
	/**
	 * 获取最底层部门
	 */
	@Query("from Department dept where not exists (select 1 from Department oDept where oDept.parentDepartment.id = dept.id)")
	public List<Department> findByLastDepartment();

}
