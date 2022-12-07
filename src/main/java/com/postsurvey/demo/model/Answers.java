package com.postsurvey.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Answers {
    @Id
    @Column(name = "answer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answer_id;
    private String question_id;
    private String answer;
    private String choice;
    private LocalDateTime answerdate;

    @OneToOne(mappedBy = "answ")
    private survyQuestion survyQuestion;

    public Long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }
}
