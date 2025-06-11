package com.sparta.test_code.unit.service;

import com.sparta.test_code.dto.UserCreationDto;
import com.sparta.test_code.dto.UserResponseDto;
import com.sparta.test_code.entity.User;
import com.sparta.test_code.respository.UserRepository;
import com.sparta.test_code.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void 유저_저장_서비스_단위_테스트() {

        //given
        UserCreationDto userCreationDto = new UserCreationDto("name", "email", "password");

        User user = new User("name", "email", "password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        //when
        UserResponseDto result = userService.saveUser(userCreationDto);

        //then

        //상태검증 -> 데이터가 정상적으로 출력이 되었는가?
        assertEquals("name", result.getName());
        assertEquals("email", result.getEmail());

        //행위검증
        verify(userRepository, times(1)).save(any(User.class));
    }
}
