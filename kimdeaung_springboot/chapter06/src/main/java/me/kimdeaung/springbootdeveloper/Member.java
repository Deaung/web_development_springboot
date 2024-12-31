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
    @Column(name = "id", nullable = false)      // 테이블 상에서의 컬럼 명이 id라는 의미
    private Long id;    // DB 테이블의 'id' 컬럼과 매칭

    @Column(name = "name", nullable = false)   // 테이블 상에서의 컬럼 명이 name이라는 의미
    private String name;    // DB 테이블의 'name' 컬럼과 매칭

    public void changeName(String name){
        this.name = name;
    }
    /*
        name 의 필드값 바꾸는 단순한 메서드로 setName 과 동일// 수정에 사용할 예정이기 때문에
            메서드 명 직관적으로 지음

        만약 이 메서드가 @Transactional 애너테이션 포함된 메서드에서 호출되면 JPA 에서는
        변경감지(dirty checking) 기능을 통해 엔티티의 필드 값이 변경될 때 해당 변경사항을 JPA 에 자동 반영
     */
}