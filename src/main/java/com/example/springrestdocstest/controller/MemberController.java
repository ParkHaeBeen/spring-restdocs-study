package com.example.springrestdocstest.controller;

import com.example.springrestdocstest.dto.MemberDto;
import com.example.springrestdocstest.dto.MemberResponse;
import com.example.springrestdocstest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/member/{id}")
  public ResponseEntity<?> checkMember(@PathVariable final Long id ,@RequestBody final MemberDto memberDto){
    MemberResponse member = memberService.checkMember(memberDto);
    return ResponseEntity.ok(member);
  }
}
