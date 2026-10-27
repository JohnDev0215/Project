package d.a.a.a.a;

import external.org.apache.commons.lang3.JavaVersion;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: ClassUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f4960a = String.valueOf('.');

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f4961b = String.valueOf('$');

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Map<Class<?>, Class<?>> f4962c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map<Class<?>, Class<?>> f4963d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Map<String, String> f4964e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Map<String, String> f4965f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Map<String, String> f4966g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Map<String, String> f4967h;

    static {
        HashMap map = new HashMap();
        f4962c = map;
        map.put(Boolean.TYPE, Boolean.class);
        map.put(Byte.TYPE, Byte.class);
        map.put(Character.TYPE, Character.class);
        map.put(Short.TYPE, Short.class);
        map.put(Integer.TYPE, Integer.class);
        map.put(Long.TYPE, Long.class);
        map.put(Double.TYPE, Double.class);
        map.put(Float.TYPE, Float.class);
        map.put(Void.TYPE, Void.class);
        f4963d = new HashMap();
        for (Class<?> cls : map.keySet()) {
            Class<?> cls2 = f4962c.get(cls);
            if (!cls.equals(cls2)) {
                f4963d.put(cls2, cls);
            }
        }
        f4964e = new HashMap();
        f4965f = new HashMap();
        for (Class<?> cls3 : f4962c.keySet()) {
            Class<?> cls4 = f4962c.get(cls3);
            if (!cls3.equals(cls4)) {
                f4964e.put(cls3.getName(), cls4.getName());
                f4965f.put(cls4.getName(), cls3.getName());
            }
        }
        f4966g = new HashMap();
        f4967h = new HashMap();
        a("int", "I");
        a("boolean", "Z");
        a("float", "F");
        a("long", "J");
        a("short", "S");
        a("byte", "B");
        a("double", "D");
        a("char", "C");
    }

    public static void a(String str, String str2) {
        f4966g.put(str, str2);
        f4967h.put(str2, str);
    }

    public static Class<?> b(ClassLoader classLoader, String str, boolean z) throws ClassNotFoundException {
        try {
            Map<String, String> map = f4966g;
            if (!map.containsKey(str)) {
                return Class.forName(l(str), z, classLoader);
            }
            return Class.forName("[" + map.get(str), z, classLoader).getComponentType();
        } catch (ClassNotFoundException e2) {
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf != -1) {
                try {
                    return b(classLoader, str.substring(0, iLastIndexOf) + '$' + str.substring(iLastIndexOf + 1), z);
                } catch (ClassNotFoundException unused) {
                    throw e2;
                }
            }
            throw e2;
        }
    }

    public static Class<?> c(String str) throws ClassNotFoundException {
        return d(str, true);
    }

    public static Class<?> d(String str, boolean z) throws ClassNotFoundException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            contextClassLoader = b.class.getClassLoader();
        }
        return b(contextClassLoader, str, z);
    }

    public static boolean e(Class<?> cls, Class<?> cls2) {
        return f(cls, cls2, d.e(JavaVersion.JAVA_1_5));
    }

    public static boolean f(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = j(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = m(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (!cls.isPrimitive()) {
            return cls2.isAssignableFrom(cls);
        }
        if (!cls2.isPrimitive()) {
            return false;
        }
        Class cls3 = Integer.TYPE;
        if (cls3.equals(cls)) {
            return Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
        }
        Class cls4 = Long.TYPE;
        if (cls4.equals(cls)) {
            return Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
        }
        if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
            return false;
        }
        Class cls5 = Float.TYPE;
        if (cls5.equals(cls)) {
            return Double.TYPE.equals(cls2);
        }
        if (Character.TYPE.equals(cls)) {
            return cls3.equals(cls2) || cls4.equals(cls2) || cls5.equals(cls2) || Double.TYPE.equals(cls2);
        }
        if (Short.TYPE.equals(cls)) {
            return cls3.equals(cls2) || cls4.equals(cls2) || cls5.equals(cls2) || Double.TYPE.equals(cls2);
        }
        if (Byte.TYPE.equals(cls)) {
            return Short.TYPE.equals(cls2) || cls3.equals(cls2) || cls4.equals(cls2) || cls5.equals(cls2) || Double.TYPE.equals(cls2);
        }
        return false;
    }

    public static boolean g(Class<?>[] clsArr, Class<?>[] clsArr2, boolean z) {
        if (!a.a(clsArr, clsArr2)) {
            return false;
        }
        if (clsArr == null) {
            clsArr = a.f4952b;
        }
        if (clsArr2 == null) {
            clsArr2 = a.f4952b;
        }
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (!f(clsArr[i2], clsArr2[i2], z)) {
                return false;
            }
        }
        return true;
    }

    public static boolean h(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        return cls.isPrimitive() || i(cls);
    }

    public static boolean i(Class<?> cls) {
        return f4963d.containsKey(cls);
    }

    public static Class<?> j(Class<?> cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : f4962c.get(cls);
    }

    public static String k(String str) {
        String str2 = f4964e.get(str);
        return str2 == null ? str : str2;
    }

    public static String l(String str) {
        String strA = c.a(str);
        Objects.requireNonNull(strA, "className must not be null.");
        if (!strA.endsWith("[]")) {
            return strA;
        }
        StringBuilder sb = new StringBuilder();
        while (strA.endsWith("[]")) {
            strA = strA.substring(0, strA.length() - 2);
            sb.append("[");
        }
        String str2 = f4966g.get(strA);
        if (str2 != null) {
            sb.append(str2);
        } else {
            sb.append("L");
            sb.append(strA);
            sb.append(";");
        }
        return sb.toString();
    }

    public static Class<?> m(Class<?> cls) {
        return f4963d.get(cls);
    }
}
