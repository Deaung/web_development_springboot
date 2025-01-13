package me.kimdeaung.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Article {

    @Id // id 필드를 기본키(PK)로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) //
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate //엔티티가 생성될 때ㅣ의 생성 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime updateAt;


    @Builder    // 빌더 패턴으로 객체 생성
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    // 이상의 업데이트 메서드 정의 후에 수정요청 과 관련된 dto 작성

    /*
        @Builder 애너테이션은 롬복에서 지원하는 에너테이션, 생성자 위에 입력하면 빌터 패턴으로 객체
        생성 가능

        객체를 유연하고 직관적으로 생성할 수 있기 때문에 개발자들이 애용하는 '디자인패턴'
        어느 필트에 어떤 값이 들어가는지 명시적으로 파악 할 수있다

        repository package 생성(domain 과 같은 라인)

     */
}
