package com.example.demo.utils;

import java.io.Serializable;

public class SecurityConstant implements Serializable {

    private static final long serialVersionUID = 3588599899157982056L;

    public static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 1000 * 60 * 60 * 24; //Millisecondes
    public static final String SIGNING_KEY = "DFQ2SJ3LMP3ZQK0UEYJ7IZ4BGPKW01MZ1Q6G4GLFHNZBND02N7S5JL9MPZ0G26GW";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";

}
