package com.postsurvey.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class survyQuestion {
    @Id
    @Column(name = "ques_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ques_id;
    private String question_l;
    private String title;
    private String type;
    private String group_l;
    private String isactive;
    private LocalDateTime addedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    private Answers answ;


    public Long getQues_id() {
        return ques_id;
    }

    public void setQues_id(Long ques_id) {
        this.ques_id = ques_id;
    }
}
