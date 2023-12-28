package com.example.springrestdocstest.service;

import com.example.springrestdocstest.dto.MemberDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private Map <String,Object> repository=new HashMap <>();

  public boolean checkMember(final MemberDto dto){
    if(repository.containsKey(dto.getName())){
      return true;
    }

    return false;
  }
}
