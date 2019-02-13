package com.booksgames.loja.services.impl;

import java.util.Optional;

import com.booksgames.loja.services.CorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.booksgames.loja.documents.Cor;
import com.booksgames.loja.dto.CorDTO;
import com.booksgames.loja.repository.CorRepository;
import com.booksgames.loja.services.exceptions.DataIntegrityException;
import com.booksgames.loja.services.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CorServiceImpl implements CorService {

    @Autowired
    private CorRepository corRepository;

    public Cor findId(String _id) {
        Optional<Cor> obj = corRepository.findById(_id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + _id + ", Tipo: " + Cor.class.getName()));
    }

    @Transactional
    public Cor insert(Cor obj) {
        obj.set_id(null);
        return corRepository.save(obj);
    }

    public Cor update(String _id) {
        Cor newObj = findId(_id);
        updateData(newObj, newObj);
        return corRepository.save(newObj);
    }

    public void delete(String _id) {
        findId(_id);
        try {
            corRepository.deleteById(_id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cor que possui produtos");
        }
    }

    public Page<Cor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return corRepository.findAll(pageRequest);
    }

    public Cor fromDTO(CorDTO objDto) {
        return new Cor(objDto.get_id(), objDto.getNome(),objDto.getHex());
    }

    private void updateData(Cor newObj, Cor obj) {
        newObj.setNome(obj.getNome());
    }
}