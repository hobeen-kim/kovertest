package com.hobeen.kovertest.user

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {

    @Autowired lateinit var userService: UserService

    @Test
    fun test() {
        //given
        val a = "a"

        //when
        val result = userService.test(a)

        //then
        assert(result == "hello a!!")
    }
}