package com.xlw.springbootdatajpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person")
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 5348518128183726236L;

    @Id
    @GenericGenerator(name = "uuid", strategy = "assigned")
    @GeneratedValue(generator = "uuid")
    private String id;

    private String parentId;

    @Column(name = "name", columnDefinition = "varchar(50) comment '名字'")
    private String name;

    private Integer age;

}
