package com.stellaweb.webdemo.models.data;

import com.stellaweb.webdemo.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
        Post findByTitle(String title);

}
