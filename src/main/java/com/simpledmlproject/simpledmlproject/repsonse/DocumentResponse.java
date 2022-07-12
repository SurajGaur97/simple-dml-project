package com.simpledmlproject.simpledmlproject.repsonse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponse {
	private String channelType;
	private String processorName;
	private String fileName;
	private String date;
	private String status;
	private Integer successTransactionCounts;
	private Integer failTransactionCounts;
	private Integer totalCount;
}
