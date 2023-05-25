package com.example.demo.utils;

import java.io.Serializable;

public class SecurityConstant implements Serializable {

    private static final long serialVersionUID = 3588599899157982056L;
    
    public static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 24;
    public static final String SIGNING_KEY = "79244226452948404D6351655468576D5A7134743777217A25432A462D4A614E";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";

}
