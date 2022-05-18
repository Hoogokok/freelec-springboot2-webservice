package org.freelac.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
@ExtendWith(SpringExtension.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void hello() throws Exception {
        //given
        String hello = "hello";
        //when
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
        //then
    }

    @Test
    void helloDto() throws Exception {
        //given
        String name = "haha";
        int amount = 115;
        //when
//        mvc.perform(get("/hello/dtd")
//                .param("name",name)
//                .param("amount",String.valueOf(amount)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name",is(name)))

        mvc.perform(get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.amount").value(amount));
    //then
    }
}