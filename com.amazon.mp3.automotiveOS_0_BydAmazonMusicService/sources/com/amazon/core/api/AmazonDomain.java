package com.amazon.core.api;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public enum AmazonDomain {
    US("amazon.com"),
    CA("amazon.ca"),
    MX("amazon.com.mx"),
    BR("amazon.com.br"),
    UK("amazon.co.uk"),
    DE("amazon.de"),
    FR("amazon.fr"),
    IT("amazon.it"),
    ES("amazon.es"),
    IN("amazon.in"),
    AE("amazon.ae"),
    SA("amazon.sa"),
    EG("amazon.eg"),
    JP("amazon.co.jp"),
    AU("amazon.com.au");

    private static final Map<String, AmazonDomain> LOOKUP = new HashMap(values().length);
    private String value;

    static {
        for (AmazonDomain amazonDomain : values()) {
            LOOKUP.put(amazonDomain.getValue(), amazonDomain);
        }
    }

    public String getValue() {
        return this.value;
    }

    AmazonDomain(String str) {
        this.value = str;
    }

    public static final AmazonDomain lookupByValue(String str) {
        return str == null ? US : LOOKUP.get(str);
    }
}
