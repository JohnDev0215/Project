package org.apache.commons.lang3;

import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes7.dex */
public class ClassUtils {
    private static final Map<String, String> abbreviationMap;
    private static final Map<String, Class<?>> namePrimitiveMap;
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap;
    private static final Map<String, String> reverseAbbreviationMap;
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;
    public static final String PACKAGE_SEPARATOR = String.valueOf('.');
    public static final String INNER_CLASS_SEPARATOR = String.valueOf(Typography.dollar);

    static {
        HashMap map = new HashMap();
        namePrimitiveMap = map;
        Class cls = Boolean.TYPE;
        map.put("boolean", cls);
        map.put("byte", Byte.TYPE);
        map.put("char", Character.TYPE);
        map.put("short", Short.TYPE);
        Class cls2 = Integer.TYPE;
        map.put("int", cls2);
        Class cls3 = Long.TYPE;
        map.put("long", cls3);
        map.put("double", Double.TYPE);
        Class cls4 = Float.TYPE;
        map.put("float", cls4);
        map.put("void", Void.TYPE);
        HashMap map2 = new HashMap();
        primitiveWrapperMap = map2;
        map2.put(cls, Boolean.class);
        map2.put(Byte.TYPE, Byte.class);
        map2.put(Character.TYPE, Character.class);
        map2.put(Short.TYPE, Short.class);
        map2.put(cls2, Integer.class);
        map2.put(cls3, Long.class);
        map2.put(Double.TYPE, Double.class);
        map2.put(cls4, Float.class);
        Class cls5 = Void.TYPE;
        map2.put(cls5, cls5);
        wrapperPrimitiveMap = new HashMap();
        for (Map.Entry entry : map2.entrySet()) {
            Class<?> cls6 = (Class) entry.getKey();
            Class<?> cls7 = (Class) entry.getValue();
            if (!cls6.equals(cls7)) {
                wrapperPrimitiveMap.put(cls7, cls6);
            }
        }
        HashMap map3 = new HashMap();
        map3.put("int", "I");
        map3.put("boolean", "Z");
        map3.put("float", "F");
        map3.put("long", "J");
        map3.put("short", "S");
        map3.put("byte", "B");
        map3.put("double", "D");
        map3.put("char", PlatformWeblabs.C);
        HashMap map4 = new HashMap();
        for (Map.Entry entry2 : map3.entrySet()) {
            map4.put(entry2.getValue(), entry2.getKey());
        }
        abbreviationMap = Collections.unmodifiableMap(map3);
        reverseAbbreviationMap = Collections.unmodifiableMap(map4);
    }

    public static String getShortClassName(Class<?> cls) {
        return cls == null ? "" : getShortClassName(cls.getName());
    }

    public static String getShortClassName(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.startsWith("[")) {
            while (str.charAt(0) == '[') {
                str = str.substring(1);
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
                str = str.substring(1, str.length() - 1);
            }
            Map<String, String> map = reverseAbbreviationMap;
            if (map.containsKey(str)) {
                str = map.get(str);
            }
        }
        int iLastIndexOf = str.lastIndexOf(46);
        int iIndexOf = str.indexOf(36, iLastIndexOf != -1 ? iLastIndexOf + 1 : 0);
        String strSubstring = str.substring(iLastIndexOf + 1);
        if (iIndexOf != -1) {
            strSubstring = strSubstring.replace(Typography.dollar, '.');
        }
        return strSubstring + ((Object) sb);
    }
}
