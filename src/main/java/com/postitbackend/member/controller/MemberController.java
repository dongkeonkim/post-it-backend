package com.postitbackend.member.controller;

import com.postitbackend.member.dto.MemberDTO;
import com.postitbackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public MemberDTO createMember(@RequestBody MemberDTO dto) {
        return memberService.createMember(dto);
    }

}
