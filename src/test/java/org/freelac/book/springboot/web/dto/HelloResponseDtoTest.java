package org.freelac.book.springboot.web.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDtoTest {


    @Test
    @DisplayName("속성 값을 꺼내오기")
    void getValue() {
        //given
        String name = "test";
        int amount = 141;
        //when
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);
        //then
        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }

    @Test
    void getAmount() {
        //given

        //when

        //then
    }
}