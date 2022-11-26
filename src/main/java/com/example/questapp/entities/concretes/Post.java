package com.example.questapp.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="id")
	Long id;

	@ManyToOne(fetch = FetchType.LAZY) // her sorguda User objesini çekme
	@JoinColumn(name = "user_id",nullable = false) // veritabanındaki columna bağlanmak için 
	@OnDelete(action = OnDeleteAction.CASCADE) // user objesi silindiğinde tüm postları silmek için kullanılır.
	@JsonIgnore
	User user;
	@Column(name="title")
	String title;

	@Lob
	@Column(columnDefinition = "text",name="text")
	String text;
}
