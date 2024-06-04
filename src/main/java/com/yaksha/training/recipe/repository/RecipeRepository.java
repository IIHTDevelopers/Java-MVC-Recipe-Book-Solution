package com.yaksha.training.recipe.repository;

import com.yaksha.training.recipe.entity.Recipe;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(value = "Select c from Recipe c where lower(title) like %:keyword%")
    List<Recipe> findByTitle(@Param("keyword") String keyword);

    @Query("SELECT r FROM Recipe r")
    Page<Recipe> findAllRecipe(Pageable pageable);

    @Query(value = "Select r from Recipe r where  lower(title) like %:keyword% " +
            "or lower(description) like %:keyword%")
    Page<Recipe> findByRecipeByTitleOrDesc(@Param("keyword") String keyword,
                                        Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE recipe SET status = :status where id = :id", nativeQuery = true)
    void updateRecipeStatus(@Param("id") Long id, @Param("status") String status);

}
