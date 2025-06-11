package com.sparta.test_code.service;

import com.sparta.test_code.dto.UserCreationDto;
import com.sparta.test_code.dto.UserResponseDto;
import com.sparta.test_code.entity.User;
import com.sparta.test_code.respository.UserRepository;
import com.sparta.test_code.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public UserResponseDto saveUser(UserCreationDto userCreationDto) {

        String encodedPassword = PasswordEncoder.encode(userCreationDto.getPassword());

        User user = new User(
                userCreationDto.getName(),
                userCreationDto.getEmail(),
                encodedPassword
        );

        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }
}
