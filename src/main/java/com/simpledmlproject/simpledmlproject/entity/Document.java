package com.simpledmlproject.simpledmlproject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_generator")
	@SequenceGenerator(name="doc_generator", sequenceName = "doc_seq")
	private long id;
	private String channelType;
	private String processorName;
	private String fileName;
	private String date;
	private String status;
	private Integer successTransactionCounts;
	private Integer failTransactionCounts;
	private Integer totalCount;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="document")
	private List<User> users;
}
