package com.example.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.dto.Media;

@Repository
public interface MediaRepo extends JpaRepository<Media, Integer> {

}
