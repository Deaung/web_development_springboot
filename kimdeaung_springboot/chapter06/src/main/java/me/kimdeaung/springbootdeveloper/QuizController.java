package me.kimdeaung.springbootdeveloper;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //자주 사용할 예정
public class QuizController {

    @GetMapping("/quiz")     //(1)
    public ResponseEntity<String> quiz(@RequestParam("code")int code){
        switch (code){
            case 1:
                return ResponseEntity.created(null).body("Created!");
            case 2:
                return ResponseEntity.badRequest().body("Bad Request!");
            default:
                return ResponseEntity.ok().body("Ok!");
        }
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> quiz2(@RequestBody Code code){

        switch (code.value()){
            case 1:
                return ResponseEntity.status(403).body("Forbidden!");
            default:
                return ResponseEntity.ok().body("Ok!");
        }
    }


}
record Code(int value) {} // (3)

/*
    QuizControllerTest.java 만들기
 */