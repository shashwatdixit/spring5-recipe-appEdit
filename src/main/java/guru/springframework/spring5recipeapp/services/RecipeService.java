package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    public Recipe findById(Long l);

    public RecipeCommand saveRecipeCommand(RecipeCommand command);
    public RecipeCommand findRecipeCommandById(Long id);

    public void deleteById(Long idToDelete);

}
