package com.example.binarplus.web02.service;

import com.example.binarplus.web02.domain.Pengguna;
import com.example.binarplus.web02.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUSerDetailsService implements UserDetailsService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pengguna pengguna = penggunaRepository.findByUsername(username);
        if(pengguna==null)
            new UsernameNotFoundException("User Not Found");
        return pengguna;
    }
    @Transactional
    public Pengguna loadUserById(Long id) throws UsernameNotFoundException{
        Pengguna pengguna = penggunaRepository.getById(id);
        if(pengguna==null)
            new UsernameNotFoundException("User Not Found");
        return pengguna;
    }
}
