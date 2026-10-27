package com.fasterxml.jackson.dataformat.xml.util;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;

/* JADX INFO: loaded from: classes6.dex */
public class TypeUtil {
    public static boolean isIndexedType(JavaType javaType) {
        Class<?> rawClass;
        return (!javaType.isContainerType() || (rawClass = javaType.getRawClass()) == byte[].class || rawClass == char[].class || javaType.isMapLikeType()) ? false : true;
    }

    public static boolean isIndexedType(Class<?> cls) {
        return !(!cls.isArray() || cls == byte[].class || cls == char[].class) || Collection.class.isAssignableFrom(cls);
    }
}
