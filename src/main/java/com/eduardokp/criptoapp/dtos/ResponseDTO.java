package com.eduardokp.criptoapp.dtos;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ResponseDTO<T> {
    private List<String> messages;
    private T data;

    public ResponseDTO(T data, String... messages) {
        this.data = data;
        this.messages = Arrays.asList(messages);
    }
}
