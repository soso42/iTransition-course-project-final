package com.example.courseproject.service;

import com.example.courseproject.entity.Comment;
import com.example.courseproject.dto.CommentCreateRequest;

import java.util.List;

public interface CommentService {
    Comment saveComment(CommentCreateRequest commentCreateRequest);
    List<Comment> getAllCommentsByItemId(Long id);
}
