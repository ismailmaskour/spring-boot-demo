package com.example.demo.utils;

import java.io.Serializable;

public class Constant implements Serializable {

    public static final String FRONT_URL = "http://localhost:4200/,http://localhost:63032/";

    private static final long serialVersionUID = 3588599899157982056L;
    public static final String SCHEMA = "public";
    public static final String SCHEMA_PARAM = "web_inter_gi_param";
    public static final String SIGNING_KEY = "acapsctrl";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";

    public static final String EMPTY = "";
    public static final String CODE_MESSAGE_EREUR = "R";
    public static final String CODE_MESSAGE_OK = "OK";
    public static final String MESSAGE_EREUR = "Erreur , Veuillez contacter le responsable technique";
    public static final String OUI = "O";
    public static final String NON = "N";
    public static final String OUVERT = "O";
    public static final String FERME = "F";
    // public static final Long ENTITE_CONTROLE = new Long(3);
    // public static final Long DELAI_LETTRE = new Long(15);
    // public static final Long DELAI_MISE_DEMEURE = new Long(30);

}
