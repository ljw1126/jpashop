package com.example.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id @GeneratedValue
    @Column(name = "parent_id")
    private Long id;

    private String name;

    // child의 parent 에 맵핑, Child에 fk가 있으므로 연관관계 주인은 Child
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childs = new ArrayList<>();

    public void addChild(Child child) {
        childs.add(child);
        child.setParent(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Child> getChilds() {
        return childs;
    }
}
