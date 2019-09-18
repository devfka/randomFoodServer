package com.randomfood.food.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "recipe")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id",length = 60)
    private Long recipeId;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name="recipe_name", length = 100, nullable = false)
    private String recipeName;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<RecipeIngredientMatrix> recipeIngredientMatrix;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId.equals(recipe.recipeId) &&
                recipeName.equals(recipe.recipeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", name='" + recipeName + '\'' +
                '}';
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
