package org.codehaus.stax2.validation;

import java.util.HashMap;

/* JADX INFO: loaded from: classes7.dex */
public abstract class XMLValidationSchemaFactory {
    static final HashMap<String, String> sSchemaIds;
    protected final String mSchemaType;

    static {
        HashMap<String, String> map = new HashMap<>();
        sSchemaIds = map;
        map.put("http://www.w3.org/XML/1998/namespace", "dtd");
        map.put("http://relaxng.org/ns/structure/0.9", "relaxng");
        map.put("http://www.w3.org/2001/XMLSchema", "w3c");
        map.put("http://www.thaiopensource.com/trex", "trex");
    }

    protected XMLValidationSchemaFactory(String str) {
        this.mSchemaType = str;
    }
}
