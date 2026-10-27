package org.objectweb.asm.commons;

import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import com.fasterxml.jackson.core.JsonPointer;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import org.objectweb.asm.Type;

/* JADX INFO: loaded from: classes6.dex */
public class Method {
    private static final Map<String, String> PRIMITIVE_TYPE_DESCRIPTORS;
    private final String descriptor;
    private final String name;

    static {
        HashMap map = new HashMap();
        map.put("void", "V");
        map.put("byte", "B");
        map.put("char", PlatformWeblabs.C);
        map.put("double", "D");
        map.put("float", "F");
        map.put("int", "I");
        map.put("long", "J");
        map.put("short", "S");
        map.put("boolean", "Z");
        PRIMITIVE_TYPE_DESCRIPTORS = map;
    }

    public Method(String str, String str2) {
        this.name = str;
        this.descriptor = str2;
    }

    public Method(String str, Type type, Type[] typeArr) {
        this(str, Type.getMethodDescriptor(type, typeArr));
    }

    public static Method getMethod(String str) {
        return getMethod(str, false);
    }

    public static Method getMethod(String str, boolean z) {
        int iIndexOf;
        String descriptorInternal;
        int iIndexOf2 = str.indexOf(32);
        int iIndexOf3 = str.indexOf(40, iIndexOf2) + 1;
        int iIndexOf4 = str.indexOf(41, iIndexOf3);
        if (iIndexOf2 == -1 || iIndexOf3 == 0 || iIndexOf4 == -1) {
            throw new IllegalArgumentException();
        }
        String strSubstring = str.substring(0, iIndexOf2);
        String strTrim = str.substring(iIndexOf2 + 1, iIndexOf3 - 1).trim();
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        do {
            iIndexOf = str.indexOf(44, iIndexOf3);
            if (iIndexOf == -1) {
                descriptorInternal = getDescriptorInternal(str.substring(iIndexOf3, iIndexOf4).trim(), z);
            } else {
                descriptorInternal = getDescriptorInternal(str.substring(iIndexOf3, iIndexOf).trim(), z);
                iIndexOf3 = iIndexOf + 1;
            }
            sb.append(descriptorInternal);
        } while (iIndexOf != -1);
        sb.append(')');
        sb.append(getDescriptorInternal(strSubstring, z));
        return new Method(strTrim, sb.toString());
    }

    private static String getDescriptorInternal(String str, boolean z) {
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
            sb.append('[');
        }
        String strSubstring = str.substring(0, str.length() - (sb.length() * 2));
        String str2 = PRIMITIVE_TYPE_DESCRIPTORS.get(strSubstring);
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
                sb.append(strSubstring.replace('.', JsonPointer.SEPARATOR));
            }
            sb.append(';');
        }
        return sb.toString();
    }

    public String getName() {
        return this.name;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public String toString() {
        return this.name + this.descriptor;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Method)) {
            return false;
        }
        Method method = (Method) obj;
        return this.name.equals(method.name) && this.descriptor.equals(method.descriptor);
    }

    public int hashCode() {
        return this.name.hashCode() ^ this.descriptor.hashCode();
    }
}
