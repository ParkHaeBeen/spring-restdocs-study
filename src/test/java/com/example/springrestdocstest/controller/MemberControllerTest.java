package com.example.springrestdocstest.controller;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.example.springrestdocstest.ControllerTestConfig;
import com.example.springrestdocstest.dto.MemberDto;
import com.example.springrestdocstest.dto.MemberResponse;
import com.example.springrestdocstest.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureRestDocs
@AutoConfigureMockMvc
@WebMvcTest(MemberController.class)
class MemberControllerTest  {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private MemberService memberService;

  @DisplayName("멤버 테스트")
  @Test
  void existMemeber() throws Exception {
    //given
    MemberDto dto=MemberDto.builder()
        .age(10)
        .name("hello")
        .build();
    MemberResponse response=MemberResponse.builder()
        .name("hello")
        .build();

    given(memberService.checkMember(any()))
        .willReturn(response);

    mockMvc.perform(get("/member/{id}",1)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andDo(print())
        .andDo(MockMvcRestDocumentationWrapper.document("members/checkMember",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            pathParameters(
                parameterWithName("id").description("id에 대한 설명")
            ),
            requestFields(
                fieldWithPath("name").type(JsonFieldType.STRING).description("MemberDto name 설명"),
                fieldWithPath("age").type(JsonFieldType.NUMBER).description("MemberDto age 설명")
            ),
            responseFields(
                fieldWithPath("name").type(JsonFieldType.STRING).description("MemberResponse name 설명")
            )))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}