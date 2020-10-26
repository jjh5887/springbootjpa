package com.example.springbootjpa;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;     // -> jpa 핵심 클래스

    //@Transactional     -> 이런식도 가능능
   @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setUsername("dsadsd");
        account.setPassword("1234");

        Study study = new Study();
        study.setName("Spring Data JPA");
        //study.setOwner(account);

       account.getStudies().add(study); //양방향 관계일때 optinal이지만 객체지향적으로는 필수!
       study.setOwner(account); // 양방향 관계일때 주인쪽에 관계설정을 안하면 설정이 안됨!

       // account.addStudy(study); 위에 두줄은 세트임으로 이렇게 함수로 묶어주는게 좋음 이런걸 convenient method 라고함
        Session session =  entityManager.unwrap(Session.class); // 이런 session을 Persistent Context 라고함

        //이 위까지는 Transient 상태
        session.save(account);
        session.save(study);

//        entityManager.persist(account);

       Account kwonho = session.load(Account.class, account.getId()); // 이때 select 쿼리 안함
       kwonho.setUsername("kwonho13");   // -> save를 하지 않아도 자동으로 업데이트 해줌!!
       kwonho.setUsername("dsadsd");   // 이러면 update쿼리 자체가 안일어남
       //이위는 Persistent 상태
       System.out.println("==============");
       System.out.println(kwonho.getUsername());


       Comment comment = session.get(Comment.class, 3l);

   }
}
