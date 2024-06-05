package ru.gb.diplom.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
public class RoleService {

    @Autowired
    RoleRepository repository;


    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }



    Role getRole(Long id) throws RoleNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role with id " + id + " not found"));
    }

    public Role findByName(String name) {
        return repository.findOneByName(name);
    }


}
