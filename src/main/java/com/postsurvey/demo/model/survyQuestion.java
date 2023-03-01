package com.postsurvey.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime addedDate;
    private int userId;

    @PrePersist
    private void onCreate() {
        LocalDateTime myDateObj = LocalDateTime.now().plusHours(3);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = myDateObj.format(myFormatObj);
        addedDate = myDateObj;
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
