package com.example.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.app.dto.Media;
import com.example.app.repo.MediaRepo;

@Repository
public class MediaDao {

	@Autowired
	private MediaRepo mediaRepo;

	public Media saveMediaData(Media media) {

		return mediaRepo.save(media);

	}

	public List<Media> fetchData() {
		List<Media> findAll = mediaRepo.findAll();
		return findAll;
	}

	public void deleteData(int id) {
		mediaRepo.deleteById(id);
	}

	public Optional<Media> findById(int id) {
		return mediaRepo.findById(id);
	}

}
