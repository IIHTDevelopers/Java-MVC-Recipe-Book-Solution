package com.yaksha.training.recipe.repository;

import com.yaksha.training.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(value = "Select c from Recipe c where lower(title) like %:keyword%")
    List<Recipe> findByTitle(@Param("keyword") String keyword);
}
