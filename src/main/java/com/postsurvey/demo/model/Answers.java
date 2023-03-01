package com.postsurvey.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    private String UserId;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date answerdate;

    @PrePersist
    private void onCreate() {
        answerdate = new Date();
    }
/*

    @ManyToOne
    @JoinColumn(name = "user_id")
    private users users;


    @ManyToOne
    private users user;
    public Long getAnswer_id() {
        return answer_id;
    }
*/

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }
}
