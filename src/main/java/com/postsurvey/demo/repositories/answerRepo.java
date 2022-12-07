package com.postsurvey.demo.repositories;

import com.postsurvey.demo.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface answerRepo extends JpaRepository<Answers,Long> {


}
