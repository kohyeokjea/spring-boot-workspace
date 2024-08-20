package com.example.ex30_jpa_qnaboard_rest_api.answer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ex30_jpa_qnaboard_rest_api.question.Question;
import com.example.ex30_jpa_qnaboard_rest_api.question.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@RestController
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public ResponseEntity<String> createAnswer(@PathVariable("id") Integer id,@Valid @RequestBody AnswerDTO answerDTO, BindingResult bindingResult) {

        // 유효성 검사 실패 시 에러 메시지 변환
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error->errorMsg.append(error.getDefaultMessage()).append("; "));
            return new ResponseEntity<>(errorMsg.toString(), HttpStatus.BAD_REQUEST);
        }
        
        try {
            Question question = this.questionService.getQuestion(id);
            this.answerService.create(question, answerDTO.getContent());
            return new ResponseEntity<>("Answer created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failde to create answer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}