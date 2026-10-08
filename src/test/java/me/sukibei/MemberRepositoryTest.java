package me.sukibei;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Sql("/intsert-members.sql")
    @DisplayName("MemberRepository를 통해 member 태이블의 모든 래코드(3개) 가지오기")
    @Test

    public void getAllMembers(){

       List<Member> members = memberRepository.findAll();


       assertThat(members.size()).isEqualTo(3);

    }
}