package com.xlw.springbootdatajpa.service;

import com.xlw.springbootdatajpa.entity.MongoPerson;
import com.xlw.springbootdatajpa.repository.DepartmentRepository;
import com.xlw.springbootdatajpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public void update() {
        int p = personRepository.updateById("1", "person_1");
        System.out.println(p);
        System.out.println(1/0);
        int d = departmentRepository.updateById("1", "department_1");
        System.out.println(d);
    }

    @Transactional
    public void updatePerson() {
        int p = personRepository.updateById("1", "person_1");
        System.out.println(p);
    }

    @Transactional
    public void updateDepartment() {
        int d = departmentRepository.updateById("1", "department_1");
        System.out.println(d);


    }

    @Transactional
    public void updateMongo() {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("1"));
        Update update = new Update();
        update.set("name", "mongodb_name");
        mongoTemplate.findAndModify(query, update, MongoPerson.class);
    }
}
