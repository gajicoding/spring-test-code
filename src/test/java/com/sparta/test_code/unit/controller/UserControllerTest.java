package com.sparta.test_code.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.test_code.controller.UserController;
import com.sparta.test_code.dto.UserCreationDto;
import com.sparta.test_code.dto.UserResponseDto;
import com.sparta.test_code.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockitoBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void 유저_저장_컨트롤러_테스트() throws Exception {
        //given
        UserCreationDto userCreationDto = new UserCreationDto("name", "email", "password");

        UserResponseDto userResponseDto = new UserResponseDto(1L, "name", "email");
        when(userService.saveUser(any(UserCreationDto.class))).thenReturn(userResponseDto);

        //when
        ResultActions result = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreationDto))
        );

        //then
        String contentAsString = result.andReturn().getResponse().getContentAsString();
        UserResponseDto actualResult = objectMapper.readValue(contentAsString, UserResponseDto.class);

        result.andExpect(status().isOk()); // Http status가 200인지 검사
        assertEquals("email", actualResult.getEmail());
        assertEquals("name", actualResult.getName());
    }

}
