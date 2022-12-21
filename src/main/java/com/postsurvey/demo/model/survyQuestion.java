package com.postsurvey.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date addedDate;
    private int userId;

    @PrePersist
    private void onCreate() {
        addedDate = new Date();
    }

  /*  @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    private Answers answ;
*/

    public Long getQues_id() {
        return ques_id;
    }

    public void setQues_id(Long ques_id) {
        this.ques_id = ques_id;
    }
}
