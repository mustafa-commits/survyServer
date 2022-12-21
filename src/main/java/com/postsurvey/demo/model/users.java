package com.postsurvey.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class users {
    @Id
    @Column(name = "userid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;
    private String name;
    private String phone;
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date localDateTime;

    @PrePersist
    private void onCreate() {
        localDateTime = new Date();
    }
    /*@OneToMany
    private List<Answers> answers;*/

}
