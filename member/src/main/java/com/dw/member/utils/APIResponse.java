package com.dw.member.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

// <> = 제네릭
// T : Type
// APIResponse<Type 를 담는다 , 클래스만 넣을 수 있다>
public class APIResponse<T> {

    int total;
    T list;
}