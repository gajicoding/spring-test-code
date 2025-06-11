package com.sparta.test_code.unit.utils;

import com.sparta.test_code.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordEncoderTest {

    @Test
    void 비밀번호_검사_성공() {
        //given
        String rawPassword = "password";
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        //when
        boolean isMatched = PasswordEncoder.matches(rawPassword, encodedPassword);

        //then
        assertTrue(isMatched);
    }

    @Test
    void 비밀번호_검사_실패() {
        //given
        String rawPassword = "password";
        String encodedPassword = PasswordEncoder.encode("password2");

        //when
        boolean isMatched = PasswordEncoder.matches(rawPassword, encodedPassword);

        //then
        assertFalse(isMatched);
    }
}
