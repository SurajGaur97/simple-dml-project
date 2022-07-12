package com.simpledmlproject.simpledmlproject.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.simpledmlproject.simpledmlproject.dao.DocumentDao;
import com.simpledmlproject.simpledmlproject.entity.Document;
import com.simpledmlproject.simpledmlproject.entity.User;
import com.simpledmlproject.simpledmlproject.repsonse.DocumentResponse;
import com.simpledmlproject.simpledmlproject.request.DocumentRequest;
import com.simpledmlproject.simpledmlproject.services.DocumentService;

@Service("DocumentService")
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;

	@Override
	public DocumentResponse insertDocument(DocumentRequest documentRequest)
			throws SAXException, IOException, ParserConfigurationException {
		if (documentRequest.getFile() == null) {
			return null;
		}
		InputStream inputStream = new ByteArrayInputStream(documentRequest.getFile().getBytes());
		// creating Workbook instance that refers to .xlsx file
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
		Iterator<Row> rowItr = sheet.iterator(); // iterating over excel file
		DocumentResponse documentResponse = new DocumentResponse();
		documentResponse.setChannelType(documentRequest.getChannelType());
		documentResponse.setProcessorName(documentRequest.getProcessorName());
		documentResponse.setFileName(documentRequest.getFile().getOriginalFilename());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		documentResponse.setDate(formatter.format(date));
		int failCount = 0, successCount = 0;
		
		List<User> users = new ArrayList<>();
		
		boolean isFirstRow = true;
		while (rowItr.hasNext()) {
			Row row = rowItr.next();
			if (isFirstRow) {
				isFirstRow = false;
				continue;
			}
			User user = User.builder().name(row.getCell(1).getStringCellValue())
					.amount(row.getCell(2).getNumericCellValue())
					.status(row.getCell(3).getStringCellValue())
					.build();
			users.add(user);
			String status = row.getCell(3).getStringCellValue();
			if (status.equalsIgnoreCase("Success")) {
				successCount++;
			} else if (status.equalsIgnoreCase("Fail")) {
				failCount++;
			}
		}
		documentResponse.setFailTransactionCounts(failCount);
		documentResponse.setSuccessTransactionCounts(successCount);
		documentResponse.setTotalCount(failCount + successCount);

		// Saving Data:
		Document document = Document.builder().processorName(documentResponse.getProcessorName())
				.channelType(documentResponse.getChannelType()).fileName(documentResponse.getFileName())
				.date(documentResponse.getDate())
				.successTransactionCounts(documentResponse.getSuccessTransactionCounts())
				.failTransactionCounts(documentResponse.getFailTransactionCounts())
				.totalCount(documentResponse.getTotalCount())
				.users(users)
				.build();
		for(User user : users) {
			user.setDocument(document);
		}
		this.documentDao.save(document);
		return documentResponse;
	}

	@Override
	public ByteArrayOutputStream getDocument(String fileName, String status) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();  
		//invoking creatSheet() method and passing the name of the sheet to be created   
		HSSFSheet sheet = workbook.createSheet(fileName);   
		//creating the 0th row using the createRow() method  
		HSSFRow rowhead = sheet.createRow((short)0);  
		//creating header 
		rowhead.createCell(0).setCellValue("Sr");  
		rowhead.createCell(1).setCellValue("Name");  
		rowhead.createCell(2).setCellValue("Amount");  
		rowhead.createCell(3).setCellValue("Status"); 
		List<Document> documents = documentDao.findByFileName(fileName);
		List<User> users = documents.get(0).getUsers();
		for(int i = 1, j =1; i < users.size(); i++) {
			if (users.get(i-1).getStatus().equalsIgnoreCase(status) || status.equalsIgnoreCase("All")) {
				HSSFRow row = sheet.createRow((short)j);  
				row.createCell(0).setCellValue(j);  
				row.createCell(1).setCellValue(users.get(i-1).getName());  
				row.createCell(2).setCellValue(users.get(i-1).getAmount()); 
				row.createCell(3).setCellValue(users.get(i-1).getStatus());	
				j++;
			}
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
		return outputStream;
	}

}
