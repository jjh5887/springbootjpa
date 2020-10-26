package com.example.springbootjpa;

import javax.persistence.*;

@Entity
public class Study {

    @Id @GeneratedValue
    private Long id;

    private String name;

    // 1번
    //@ManyToOne // owner가 한개니까 One
    //private Account owner;

    // Account와 Study엔티티 사이에서 주인은 Study
    // 왜냐? Study가 Account가지고 있고 그래서 주인쪽(Study)에다가 관계를 정의했으니까

    @ManyToOne
    private Account owner;

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
