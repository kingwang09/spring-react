package io.labs.springreact.greet.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.labs.springreact.greet.model.Greet;
import io.labs.springreact.greet.model.QGreet;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GreetQuerySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public GreetQuerySupport(JPAQueryFactory jpaQueryFactory) {
        super(Greet.class);
        this.queryFactory = jpaQueryFactory;
    }

    public Greet findById(Long id){
        QGreet greet = QGreet.greet;
        return (Greet) queryFactory
                .from(greet)
                .where(greet.id.eq(id))
                .fetchOne();
    }

    public List<Greet> findAll(){
        return (List<Greet>) queryFactory.from(QGreet.greet).fetch();
    }
}

