package com.example.binarplus.web02.controller;

import com.example.binarplus.web02.domain.Pengguna;
import com.example.binarplus.web02.login.JwtLoginSuccessResponse;
import com.example.binarplus.web02.login.LoginReq;
import com.example.binarplus.web02.security.JwtTokenProvider;
import com.example.binarplus.web02.service.MapValidationErrorService;
import com.example.binarplus.web02.service.PenggunaService;
import com.example.binarplus.web02.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.binarplus.web02.security.SecurityConstant.TOKEN_PREFIX;

@RestController
@RequestMapping("api/users")
public class PenggunaController {

    @Autowired
    private PenggunaService penggunaService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping ("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Pengguna pengguna, BindingResult bindingResult){

        userValidator.validate(pengguna, bindingResult);
        ResponseEntity errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if(errorMap != null)
            return errorMap;
        Pengguna newUser = penggunaService.savePengguna(pengguna);

        return new ResponseEntity<Pengguna>(newUser, HttpStatus.CREATED);
    }

    @PostMapping ("/login")
    public ResponseEntity<?> authtenticateUser(@Valid @RequestBody LoginReq loginReq, BindingResult bindingResult){

        ResponseEntity errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if(errorMap != null)
            return errorMap;
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(),loginReq.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);


        return ResponseEntity.ok(new JwtLoginSuccessResponse(true, jwt));
    }


}
