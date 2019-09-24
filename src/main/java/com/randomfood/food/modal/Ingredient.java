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
@Table(name = "ingredient")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id", length = 60)
    private Long ingredientId;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ingredient_name", length = 100, unique = true, nullable = false)
    private String ingredientName;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<RecipeIngredientMatrix> recipeIngredientMatrix;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return ingredientId.equals(that.ingredientId) &&
                ingredientName.equals(that.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, ingredientName);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", name='" + ingredientName + '\'' +
                '}';
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public static class IngredientBuilder {
        private Long ingredientId;
        private String ingredientName;
        private Set<RecipeIngredientMatrix> recipeIngredientMatrix;

        public IngredientBuilder setIngredientId(final Long ingredientId) {
            this.ingredientId = ingredientId;
            return this;
        }

        public IngredientBuilder setIngredientName(final String ingredientName) {
            this.ingredientName = ingredientName;
            return this;
        }

        public IngredientBuilder setRecipeIngredientMatrix(final Set<RecipeIngredientMatrix> recipeIngredientMatrix) {
            this.recipeIngredientMatrix = recipeIngredientMatrix;
            return this;
        }

        public Ingredient build() {
            return new Ingredient(ingredientId, ingredientName, recipeIngredientMatrix);
        }
    }
}
