package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile multipartFile) {
      log.debug("Saved image");

      try {
          Recipe recipe = recipeRepository.findById(recipeId).get();

          Byte[] byteObjects= new Byte[multipartFile.getBytes().length];

          int i=0;
          for (byte b: multipartFile.getBytes()){
              byteObjects[i++]=b;
          }

          recipe.setImage(byteObjects);
          recipeRepository.save(recipe);

      }
      catch (IOException e){
          log.error("error Occurred");
          e.printStackTrace();
      }


    }
}
