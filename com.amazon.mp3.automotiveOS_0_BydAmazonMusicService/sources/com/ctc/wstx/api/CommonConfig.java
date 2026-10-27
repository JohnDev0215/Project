package com.ctc.wstx.api;

import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
abstract class CommonConfig {
    static final HashMap<String, Integer> sStdProperties;
    protected boolean mReturnNullForDefaultNamespace;

    static {
        HashMap<String, Integer> map = new HashMap<>(16);
        sStdProperties = map;
        map.put("org.codehaus.stax2.implName", 1);
        map.put("org.codehaus.stax2.implVersion", 2);
        map.put("org.codehaus.stax2.supportsXml11", 3);
        map.put("org.codehaus.stax2.supportXmlId", 4);
        map.put("com.ctc.wstx.returnNullForDefaultNamespace", 5);
        map.put("http://java.sun.com/xml/stream/properties/implementation-name", 1);
    }

    protected CommonConfig(CommonConfig commonConfig) {
        this.mReturnNullForDefaultNamespace = commonConfig == null ? Boolean.getBoolean("com.ctc.wstx.returnNullForDefaultNamespace") : commonConfig.mReturnNullForDefaultNamespace;
    }
}
