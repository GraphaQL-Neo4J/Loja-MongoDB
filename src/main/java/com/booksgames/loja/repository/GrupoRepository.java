package com.booksgames.loja.repository;

import com.booksgames.loja.documents.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GrupoRepository extends MongoRepository<Grupo, String> {
    Grupo findBy_id(String _id);
}