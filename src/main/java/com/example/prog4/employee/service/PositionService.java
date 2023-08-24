package com.example.prog4.employee.service;

import com.example.prog4.employee.repository.PositionRepository;
import com.example.prog4.employee.entity.Position;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PositionService {
    private PositionRepository repository;
    public List<Position> getAll(){
        return repository.findAll();
    }
    public Position saveOne(Position position){
        return repository.save(position);
    }
}
