package com.sparta.test_code.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.test_code.dto.UserCreationDto;
import com.sparta.test_code.dto.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 유저_저장_통합_테스트() throws Exception {
        //given
        UserCreationDto userCreationDto = new UserCreationDto("name", "email", "password");

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
        assertThat(actualResult.getId()).isGreaterThan(0L);
        assertEquals("email", actualResult.getEmail());
        assertEquals("name", actualResult.getName());
    }
}
