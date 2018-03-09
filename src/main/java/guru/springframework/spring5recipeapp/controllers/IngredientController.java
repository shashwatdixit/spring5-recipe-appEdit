package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {
    public RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("recipe/{id}/ingredients/show")
    public String showIngredient(@PathVariable String id, Model model){
        RecipeCommand recipeCommand=recipeService.findRecipeCommandById(new Long(id));

        model.addAttribute("ingredients",recipeCommand.getIngredients());
        return "recipe/ingredients/showIngredients";

    }
}
