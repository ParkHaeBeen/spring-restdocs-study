package com.example.springrestdocstest.service;

import com.example.springrestdocstest.dto.MemberDto;
import com.example.springrestdocstest.dto.MemberResponse;
import com.example.springrestdocstest.exception.CustomeException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private Map <String,Object> repository=new HashMap <>();

  public MemberResponse checkMember(final MemberDto dto){
    if(!repository.containsKey(dto.getName())){
      throw new CustomeException("멤버 없다");
    }

    return MemberResponse.builder()
        .name(dto.getName())
        .build();
  }
}
