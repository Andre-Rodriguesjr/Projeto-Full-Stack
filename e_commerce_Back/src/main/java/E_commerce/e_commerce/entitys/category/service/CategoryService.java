package E_commerce.e_commerce.entitys.category.service;

import E_commerce.e_commerce.entitys.category.Category;
import E_commerce.e_commerce.entitys.category.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Criar categoria
    public Category createCategory(Category category) {

        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Essa categoria já existe.");
        }

        return categoryRepository.save(category);
    }

    //Listar
    public List<Category> listCategories(){
        return categoryRepository.findAll();
    }

    //Buscar categoria por id
    public Optional<Category> findByID(Long id){
        return categoryRepository.findById(id);
    }

    //Atualizar Categoria
    public Category updateCategory(Long id, Category category){
        Category categoryUpdate = categoryRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("categoria não encontrada."));

        categoryUpdate.setName(category.getName());
        return categoryRepository.save(categoryUpdate);
    }

    //Deletar categoria
    public void deleteCategoryById(Long id){
        if(!categoryRepository.existsById(id)){
            throw new IllegalArgumentException("Categoria não encontrada.");
        }
        categoryRepository.deleteById(id);
    }
}
