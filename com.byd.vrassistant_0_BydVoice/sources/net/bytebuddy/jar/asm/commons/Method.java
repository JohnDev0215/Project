package net.bytebuddy.jar.asm.commons;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import com.android.multidex.ClassPathElement;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.pool.TypePool;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class Method {
    private static final Map<String, String> DESCRIPTORS;
    private final String desc;
    private final String name;

    static {
        HashMap map = new HashMap();
        DESCRIPTORS = map;
        map.put("void", "V");
        map.put("byte", "B");
        map.put("char", TemperatureUnitEnum.C);
        map.put("double", "D");
        map.put("float", TemperatureUnitEnum.F);
        map.put("int", "I");
        map.put("long", "J");
        map.put("short", "S");
        map.put("boolean", "Z");
    }

    public Method(String str, String str2) {
        this.name = str;
        this.desc = str2;
    }

    public Method(String str, Type type, Type[] typeArr) {
        this(str, Type.getMethodDescriptor(type, typeArr));
    }

    public static Method getMethod(java.lang.reflect.Method method) {
        return new Method(method.getName(), Type.getMethodDescriptor(method));
    }

    public static Method getMethod(Constructor<?> constructor) {
        return new Method(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, Type.getConstructorDescriptor(constructor));
    }

    public static Method getMethod(String str) throws IllegalArgumentException {
        return getMethod(str, false);
    }

    public static Method getMethod(String str, boolean z) throws IllegalArgumentException {
        int iIndexOf;
        String map;
        int iIndexOf2 = str.indexOf(32);
        int iIndexOf3 = str.indexOf(40, iIndexOf2) + 1;
        int iIndexOf4 = str.indexOf(41, iIndexOf3);
        if (iIndexOf2 == -1 || iIndexOf3 == -1 || iIndexOf4 == -1) {
            throw new IllegalArgumentException();
        }
        String strSubstring = str.substring(0, iIndexOf2);
        String strTrim = str.substring(iIndexOf2 + 1, iIndexOf3 - 1).trim();
        StringBuilder sb = new StringBuilder("(");
        do {
            iIndexOf = str.indexOf(44, iIndexOf3);
            if (iIndexOf == -1) {
                map = map(str.substring(iIndexOf3, iIndexOf4).trim(), z);
            } else {
                map = map(str.substring(iIndexOf3, iIndexOf).trim(), z);
                iIndexOf3 = iIndexOf + 1;
            }
            sb.append(map);
        } while (iIndexOf != -1);
        sb.append(')');
        sb.append(map(strSubstring, z));
        return new Method(strTrim, sb.toString());
    }

    private static String map(String str, boolean z) {
        if ("".equals(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int iIndexOf = 0;
        while (true) {
            iIndexOf = str.indexOf(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, iIndexOf) + 1;
            if (iIndexOf <= 0) {
                break;
            }
            sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        }
        String strSubstring = str.substring(0, str.length() - (sb.length() * 2));
        String str2 = DESCRIPTORS.get(strSubstring);
        if (str2 != null) {
            sb.append(str2);
        } else {
            sb.append('L');
            if (strSubstring.indexOf(46) < 0) {
                if (!z) {
                    sb.append("java/lang/");
                }
                sb.append(strSubstring);
            } else {
                sb.append(strSubstring.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR));
            }
            sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.INDEXED_TYPE_DELIMITER);
        }
        return sb.toString();
    }

    public String getName() {
        return this.name;
    }

    public String getDescriptor() {
        return this.desc;
    }

    public Type getReturnType() {
        return Type.getReturnType(this.desc);
    }

    public Type[] getArgumentTypes() {
        return Type.getArgumentTypes(this.desc);
    }

    public String toString() {
        return this.name + this.desc;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Method)) {
            return false;
        }
        Method method = (Method) obj;
        return this.name.equals(method.name) && this.desc.equals(method.desc);
    }

    public int hashCode() {
        return this.name.hashCode() ^ this.desc.hashCode();
    }
}
