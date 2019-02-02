package com.example.binarplus.web02.security;

public class SecurityConstant {

    public static final String SIGN_UP_URL = "/api/users/**";
    public static final String SECRET = "SecretKeyTOGenJWT";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorisation";
    public static final long EXPIRATION_TIME = 30_000;

}
