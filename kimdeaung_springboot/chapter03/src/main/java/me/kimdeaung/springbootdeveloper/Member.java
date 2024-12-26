package me.kimdeaung.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false) //테이블 상에서의 컬럼 명이 id
    private Long id;

    @Column(name = "name", nullable = false)//테이블 상에서의 컬럼 명이 name
    private String name;

    /*

     */


}
