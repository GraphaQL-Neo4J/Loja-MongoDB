package com.booksgames.loja.services;

import java.util.List;

import com.booksgames.loja.documents.Cliente;

public interface ClienteService {

    List<Cliente> listarTodos();

    List<Cliente> listarPorId(String id);

    /*Cliente cadastrar(Cliente cliente);

    Cliente atualizar(Cliente cliente);

     void remover(Integer id);*/
}