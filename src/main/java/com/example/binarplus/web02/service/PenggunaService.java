package com.example.binarplus.web02.service;

import com.example.binarplus.web02.domain.Pengguna;
import com.example.binarplus.web02.exceptions.UsernameAlreadyExistExceptions;
import com.example.binarplus.web02.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PenggunaService {

    @Autowired
    private PenggunaRepository penggunaRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Pengguna savePengguna(Pengguna newUser) {

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setReTypePass("");

            return penggunaRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistExceptions("Username "+ newUser.getUsername()+" Username Already Exist");
        }

//    }
//        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
//        return penggunaRepository.save(newUser);
//
    }
}
