package com.example.springbootjpa;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

//    @Embedded
//    private Address address;


    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    // 2번
    @OneToMany(mappedBy = "owner")                      // Study가 다수니까 Many
    private Set<Study> studies  = new HashSet<>();
    //이번엔 Account가 Study를 가지고 있으니까 Account가 주인
    //db에서는 OneToMany는  기본적으로 조인 talble 생성 -> 관계만 나타나는 table이 따로생김
    // 1번과 2번을 동시에 하면 두개의 단방향 관계이지 양방향관계가 아님




    //    @Temporal(TemporalType.TIMESTAMP)
//    private Date created;
//
//    @Transient  // db에 없는 컬럼
//    private String no;
//
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
//    })
//    private Address address;



    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void addStudy(Study study) {
        this.getStudies().add(study);
        study.setOwner(this);
    }
}
