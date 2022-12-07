package com.postsurvey.demo.repositories;

import com.postsurvey.demo.model.survyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface questionRepo extends JpaRepository<survyQuestion,Long> {


}
