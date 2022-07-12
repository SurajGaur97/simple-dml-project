package com.simpledmlproject.simpledmlproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpledmlproject.simpledmlproject.entity.Document;

public interface DocumentDao extends JpaRepository<Document, Long> {
	List<Document> findByFileName(String fileName);
}
