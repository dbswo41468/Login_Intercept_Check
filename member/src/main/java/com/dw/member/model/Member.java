package com.dw.member.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

// vo 기능
// db에 테이블 생성 기능

// 테이블 이름은 "소"문자
@Getter
@Setter
@Entity
@Table(name = "dw_member")
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	// Column(컬럼)이라고 지정한다
	@Column
	private long id; // pk

	@Column(length = 30)
	private String name; // 멤버이름

	@Column
	private int age; // 나이

	@Column(length = 40)
	private String userId; // 유저 아이디

	@Column
	private String userPassword; // 유저 비밀번호
	
	// 조인
	// One to Many 구분 해야한다
	// One to One 
	// Many to Many 
	@ManyToOne
	// @JoinColumn() -> member 테이블에 dept_id 라는 컬럼(FK)생성
	@JoinColumn(name ="dept_id")
	private Dept dept;
}
