package com.amazonaws.mobileconnectors.remoteconfiguration;

import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public interface Attributes extends Cloneable {
    public static final String PREDEFINED_ATTRIBUTE_PREFIX = "_";

    void addAttribute(String str, Boolean bool);

    void addAttribute(String str, Double d);

    void addAttribute(String str, Integer num);

    void addAttribute(String str, Long l);

    void addAttribute(String str, String str2);

    Attributes clone();

    Map<String, Object> getAllAttributes();

    Boolean getBoolean(String str);

    Double getDouble(String str);

    Integer getInt(String str);

    Long getLong(String str);

    String getString(String str);

    Object remove(String str);
}
