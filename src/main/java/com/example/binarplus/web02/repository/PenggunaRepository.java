package com.example.binarplus.web02.repository;

import com.example.binarplus.web02.domain.Pengguna;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenggunaRepository extends CrudRepository<Pengguna, Long> {
    Pengguna findByUsername(String username);
    Pengguna getById(Long id);
}
