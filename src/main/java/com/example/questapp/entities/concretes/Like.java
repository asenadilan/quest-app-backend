package com.example.questapp.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "likes")
@AllArgsConstructor
@NoArgsConstructor
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="id")
	Long id;
	@ManyToOne(fetch = FetchType.LAZY) // her sorguda post objesini çekme
	@JoinColumn(name = "post_id",nullable = false) // veritabanındaki columna bağlanmak için 
	@OnDelete(action = OnDeleteAction.CASCADE) // post objesi silindiğinde tüm postları silmek için kullanılır.
	@JsonIgnore
	Post post;
	@ManyToOne(fetch = FetchType.LAZY) // her sorguda User objesini çekme
	@JoinColumn(name = "user_id",nullable = false) // veritabanındaki columna bağlanmak için 
	@OnDelete(action = OnDeleteAction.CASCADE) // user objesi silindiğinde tüm postları silmek için kullanılır.
	@JsonIgnore
	User user;

}
