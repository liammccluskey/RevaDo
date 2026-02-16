package com.example.RevaDo.repositories;

import com.example.RevaDo.entities.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {

}
