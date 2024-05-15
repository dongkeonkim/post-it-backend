package com.postitbackend.member.repository;

import com.postitbackend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndEnable(String email, int enable);

}
