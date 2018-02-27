package guru.springframework.spring5recipeapp.Bootstrap;


import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes=new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional= unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UoM Does not Exist");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional= unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UoM Does not Exist");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional= unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UoM Does not Exist");
        }

        Optional<UnitOfMeasure> dashUomOptional= unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UoM Does not Exist");
        }

        Optional<UnitOfMeasure> pintUomOptional= unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UoM Does not Exist");
        }

        Optional<UnitOfMeasure> cupUomOptional= unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected UoM Does not Exist");
        }

        //Get Uom's
        UnitOfMeasure eachUom=eachUomOptional.get();
        UnitOfMeasure tableSpoonUom=tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom= teaSpoonUomOptional.get();
        UnitOfMeasure dashUom= dashUomOptional.get();
        UnitOfMeasure pintUom=pintUomOptional.get();
        UnitOfMeasure cupUom=cupUomOptional.get();

        //Get Categories

        Optional<Category> americalCategoryOptional= categoryRepository.findByDescription("American");
        if(!americalCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not found");
        }

        Optional<Category> mexicanCategoryOptional= categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Category americanCategory=americalCategoryOptional.get();
        Category mexicanCategory=mexicanCategoryOptional.get();

        Recipe guacRecipe= new Recipe();

        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(10);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Do this that and bla bla bla!!");

        Notes guacNotes= new Notes();

        guacNotes.setRecipeNotes("Additional Notes Here");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredients(new Ingredient("Ripe Avocados", new BigDecimal(2),eachUom));
        guacRecipe.addIngredients(new Ingredient("Salt",new BigDecimal(4),teaSpoonUom));


        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);
        recipes.add(guacRecipe);

        return recipes;
    }


}
