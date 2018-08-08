package com.xlw.springbootdatajpa.repository;

import com.xlw.springbootdatajpa.entity.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {

    @Query("select name, age, count(p) from Person p group by p.name, p.age")
    List<Object[]> getList();

    @Query("from Person")
    List<Person> distinct(Pageable pageable);

    @Modifying
    @Query("update Person d set d.name = ?2 where id = ?1")
    public int updateById(String id, String name);

    void deleteByName(String name);

    @Query("from Person where name like ?1%")
    List<Person> findByNameLike(String name);
}
