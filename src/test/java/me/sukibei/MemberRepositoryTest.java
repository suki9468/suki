package me.sukibei;


import org.apache.catalina.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
    @Sql("/intsert-members.sql")
    @DisplayName("MemberRepository를 통해 이름이 'C'인 레코드 가져오기")
    @Test
    public void findByName(){
        Optional<Member> optMember = memberRepository.findByName("C");
        Member member = optMember.get();


        assertThat(member.getId()).isEqualTo(3);



    }

        @Sql("/intsert-members.sql")
        @DisplayName("MemberRepository를 통해 Id가 2인 레코드 가져오기")
        @Test

        public void getMemberById(){
           Member member = memberRepository.findById(2L).get();

           assertThat(member.getName()).isEqualTo("B");
        }


        @DisplayName("MemberRepository를 통해 이름이 'hjj'인 레코드 추가하기")
        @Test
        public void saveMember(){
           // Member member = new Member(1L,"hjj");
            Member member = new Member("hjj");

            memberRepository.save(member);

            List<Member> result = memberRepository.findAll();
            assertThat(result.size()).isEqualTo(1);
            assertThat(result.get(0).getName()).isEqualTo("hjj");
        }
    }
