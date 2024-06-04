package com.yaksha.training.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 40)
    private String title;

    @NotBlank
    @Size(min = 2, max = 200)
    private String description;

    @NotBlank
    private String ingredients;

    @NotNull
    private Integer cookTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private RecipeStatus status = RecipeStatus.PENDING;

    public Recipe() {
    }

    public Recipe(Long id, String title, String description, String ingredients, Integer cookTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.cookTime = cookTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public RecipeStatus getStatus() {
        return status;
    }

    public void setStatus(RecipeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", cookTime='" + cookTime + '\'' +
                '}';
    }
}
