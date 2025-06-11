package com.sparta.test_code.unit.repository;

import com.sparta.test_code.entity.User;
import com.sparta.test_code.respository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void 유저_저장_로직_테스트() {

        //given
        User user = new User("test", "email", "password");

        //when
        User savedUser = userRepository.save(user);

        //then
        Assertions.assertThat(savedUser.getName()).isEqualTo("test");
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("email");
        Assertions.assertThat(savedUser.getPassword()).isEqualTo("password");
    }
}
