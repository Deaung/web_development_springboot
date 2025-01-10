package me.kimdeaung.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model){

        Person examplePerson = new Person();
        // id 1L, 이름에 홍길동 나이가 11 에 취미가 운동, 독서 입력
        examplePerson.setId(1L);
        examplePerson.setAge(11);
        examplePerson.setName("홍길동");
        examplePerson.setHobbies(List.of("운동","독서"));

        model.addAttribute("person",examplePerson);
        model.addAttribute("today", LocalDate.now());

        return "example";
    }

    @Setter
    @Getter
    class Person{
        private long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }

}
/*
    Model 객체는 뷰, 즉 HTML 쪽으로 값을 넘겨주는 객체임
    모델 객체는 따로 생성할 필요 없이 코드처럼 argument 로 선언하기만 하면 스프ㅇ링이 알아서 만들어 주므로
    편리하게 사용

    person 이라는 키에 사람 정보를 today 라는 키에 날짜 정보 저장

    thymeleafExample() 이라는 매서드는 "example 이라는 리턴값을 가지는데 이게 @Controller 라는 에너테이션과
    합쳐지면 -> 뷰의 이름이라는 의미
    즉 스프링 부트는 컨트롤러의 @Controller 애너테이션을 보고
    '리턴값의 이름을 가진 뷰의 파일을 찾으라' 고 알아듣고서 resources/templates 디렉토리에서
    example.html 을 찾은 뒤 웹 브라우저에 해당파일 보여줌

    모델 역할 살펴보면
        이제 모델 "person", "today" 두 키를 가진 데이터가 들어가있다
        컨트롤러는 모델을 통해 데이터를 설정하고
        모델은 뷰로 해당 데이터를 전달해서 키에 맞는 데이터를 뷰에서 조회할 수있게 만들어 줌

        즉, 모델은 컨트롤러와 뷰의 중간다리 역할을 해준다고 생각하면 됨
                                                     "person" id: 1
                                                     name: "홍길동              뷰에서 사용할 수 있게
                    컨트롤러에서 데이터 설정             age : 11                      데이터 전달
컨트롤러(Controller)---------------------->           hobbies : ["운동", "독서"] -------------------> 뷰(View)
                                                    모델(Model)
 */
