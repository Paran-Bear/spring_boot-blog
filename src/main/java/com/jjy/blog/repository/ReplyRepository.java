package com.jjy.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjy.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	

}
