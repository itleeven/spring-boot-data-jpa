package com.xlw.springbootdatajpa.repository;

import com.xlw.springbootdatajpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    @Modifying
    @Query("update Department d set d.name = ?2 where id = ?1")
    public int updateById(String id, String name);

}
