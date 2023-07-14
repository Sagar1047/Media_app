package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.MediaDao;
import com.example.app.dto.Media;

@Service
public class MediaService {

	@Autowired
	private MediaDao mediaDao;

	public Media saveMediaData(Media media) {

		return mediaDao.saveMediaData(media);

	}

	public List<Media> fetchData() {
		return mediaDao.fetchData();
	}

	public void deleteData(int id) {

		mediaDao.deleteData(id);

	}

	public Optional<Media> findById(int id) {
		
		return mediaDao.findById(id);
		
	}

}
