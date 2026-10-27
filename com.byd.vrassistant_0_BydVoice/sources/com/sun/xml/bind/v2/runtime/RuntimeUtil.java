package com.sun.xml.bind.v2.runtime;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class RuntimeUtil {
    public static final Map<Class, Class> boxToPrimitive;
    public static final Map<Class, Class> primitiveToBox;

    public static final class ToStringAdapter extends XmlAdapter<String, Object> {
        @Override // javax.xml.bind.annotation.adapters.XmlAdapter
        public Object unmarshal(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // javax.xml.bind.annotation.adapters.XmlAdapter
        public String marshal(Object obj) {
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }
    }

    static {
        HashMap map = new HashMap();
        map.put(Byte.TYPE, Byte.class);
        map.put(Short.TYPE, Short.class);
        map.put(Integer.TYPE, Integer.class);
        map.put(Long.TYPE, Long.class);
        map.put(Character.TYPE, Character.class);
        map.put(Boolean.TYPE, Boolean.class);
        map.put(Float.TYPE, Float.class);
        map.put(Double.TYPE, Double.class);
        map.put(Void.TYPE, Void.class);
        primitiveToBox = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            map2.put(entry.getValue(), entry.getKey());
        }
        boxToPrimitive = Collections.unmodifiableMap(map2);
    }

    private static String getTypeName(Object obj) {
        return obj.getClass().getName();
    }
}
