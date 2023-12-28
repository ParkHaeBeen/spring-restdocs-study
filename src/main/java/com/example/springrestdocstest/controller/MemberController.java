package com.example.springrestdocstest.controller;

import com.example.springrestdocstest.dto.MemberDto;
import com.example.springrestdocstest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/member")
  public ResponseEntity<?> checkMember(@RequestBody final MemberDto memberDto){
    boolean existMember = memberService.checkMember(memberDto);
    if(!existMember){
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().build();
  }
}
