package com.simpledmlproject.simpledmlproject.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DocumentRequest {
	private String channelType;
	private String processorName;
	private MultipartFile file;
}
