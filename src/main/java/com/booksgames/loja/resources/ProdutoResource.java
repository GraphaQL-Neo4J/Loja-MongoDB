package com.booksgames.loja.resources;

import com.booksgames.loja.domain.Produto;
import com.booksgames.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.bson.types.ObjectId;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
  @Autowired
  private ProdutoRepository repository;
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<Produto> getAllProduto() {
    return repository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Produto getPetById(@PathVariable("id") ObjectId id) {
    return repository.findBy_id(id);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Produto produto) {
    produto.set_id(id);
    repository.save(produto);
  }
  
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Produto createPet(@Valid @RequestBody Produto produto) {
    produto.set_id(ObjectId.get());
    repository.save(produto);
    return produto;
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deletePet(@PathVariable ObjectId id) {
    repository.delete(repository.findBy_id(id));
  }
}