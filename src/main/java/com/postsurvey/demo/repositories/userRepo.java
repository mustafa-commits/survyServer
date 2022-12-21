package com.postsurvey.demo.repositories;

import com.postsurvey.demo.model.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<users,Long> {
}
