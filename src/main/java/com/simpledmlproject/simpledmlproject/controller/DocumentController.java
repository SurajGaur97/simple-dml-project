package com.simpledmlproject.simpledmlproject.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.simpledmlproject.simpledmlproject.repsonse.DocumentResponse;
import com.simpledmlproject.simpledmlproject.request.DocumentRequest;
import com.simpledmlproject.simpledmlproject.services.DocumentService;

@RestController
@RequestMapping("/api/v1/docs")
public class DocumentController {

	@Qualifier("DocumentService")
	@Autowired
	private DocumentService documentService;

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public DocumentResponse insertDocumnet(@ModelAttribute DocumentRequest documentRequest)
			throws SAXException, IOException, ParserConfigurationException {
		return this.documentService.insertDocument(documentRequest);
	}

	@GetMapping(produces = "application/vnd.ms-excel")
	public byte[] getDocument(@RequestParam String fileName, @RequestParam String status,
			HttpServletResponse httpServletResponse) throws IOException {
		int dotIndex = fileName.indexOf(".");
		String fileNameResponse = fileName.substring(0, dotIndex) + "_" + status + ".xls";
		httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileNameResponse);
		return documentService.getDocument(fileName, status).toByteArray();
	}
}
