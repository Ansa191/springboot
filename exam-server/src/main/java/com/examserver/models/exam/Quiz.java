package com.examserver.models.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 private Long qid;
 private String title;
 private String maxMarks;
 private String noOfQuestions;
 private String description;
 private boolean active=false;
 
 @ManyToOne
 private Category category;
 
 @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
 @JsonIgnore
 private Set<Question> questions = new HashSet<>();
 
 
}
