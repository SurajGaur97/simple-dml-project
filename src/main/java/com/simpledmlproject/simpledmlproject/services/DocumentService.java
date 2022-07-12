package com.simpledmlproject.simpledmlproject.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.simpledmlproject.simpledmlproject.repsonse.DocumentResponse;
import com.simpledmlproject.simpledmlproject.request.DocumentRequest;

public interface DocumentService {
	ByteArrayOutputStream getDocument(String fileName, String status) throws IOException;
	DocumentResponse insertDocument(DocumentRequest documentRequest) throws SAXException, IOException, ParserConfigurationException;
}
