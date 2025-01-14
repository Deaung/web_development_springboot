package me.kimdeaung.springbootdeveloper.repository;

import me.kimdeaung.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {

}
