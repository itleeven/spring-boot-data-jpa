package com.xlw.springbootdatajpa.controller;

import com.xlw.springbootdatajpa.entity.Person;
import com.xlw.springbootdatajpa.pojo.A;
import com.xlw.springbootdatajpa.repository.PersonRepository;
import com.xlw.springbootdatajpa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(value = "aa")
    private A a;

    @RequestMapping("/findByNameLike")
    public List<Person> findByNameLike(String name) {
        return personRepository.findByNameLike(name);
    }

    @RequestMapping("/get/{id}")
    public Person get(@PathVariable String id) {
        System.out.println(id);
        Optional<Person> o = personRepository.findById(id);
        if (o.isPresent()) {
            Person person = o.get();
            System.out.println(person);
            return person;
        }
        return null;
    }

    @RequestMapping("/jdbc")
    public List<Person> jdbc() {
        System.out.println(a);

        String sql = "select * from person";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Person person = new Person();
            person.setId(resultSet.getString("id"));
            person.setName(resultSet.getString("name"));
            person.setParentId(resultSet.getString("parentId"));
            person.setAge(resultSet.getInt("age"));
            return person;
        });
    }

    @RequestMapping("/save")
    public void save() {
        Person p = new Person();
        p.setId("");
        p.setName("asdfasf");
        personRepository.save(p);

        personService.updateDepartment();
    }

    @RequestMapping("/update")
    @Transactional()
    public void update() {
        personService.updatePerson();
        personService.updateMongo();
        System.out.println(1/0);
        personService.updateDepartment();
    }

    @RequestMapping("/distinct")
    public List<Person> distinct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Person> list = personRepository.distinct(pageable);
        for (Person person : list) {
            System.out.println(person);
        }
        return list;
    }

    @RequestMapping("/all")
    public List<Object[]> getList() {
        List<Object[]> list = personRepository.getList();
        for (Object[] objects : list) {
            for (Object object : objects) {
                System.out.print(object + "\t");
            }
            System.out.println();
        }
        return list;
    }

    @RequestMapping("/get2")
    public List<Person> get2() {
        List<Person> all = personRepository.findAll();
        for (Person person : all) {
            System.out.println(person);
        }
        return all;
    }

    @RequestMapping("/list")
    public List<Person> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        System.out.println(sort.toString());
        List<Person> all = personRepository.findAll();
        return all;
    }

    @RequestMapping("/delete/{name}")
    public boolean delete(@PathVariable String name) {
        personRepository.deleteByName(name);
        return true;
    }
}
