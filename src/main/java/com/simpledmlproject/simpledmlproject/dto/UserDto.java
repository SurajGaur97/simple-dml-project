package com.simpledmlproject.simpledmlproject.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDto {
	private String channelType;
	private String processorName;
	private String fileName;
	private String date;
	private String status;
	private Integer successTransactionCounts;
	private Integer failTransactionCounts;
	private Integer totalCount;
	private MultipartFile photo;
	
}
