package c7;

import external.org.apache.commons.lang3.JavaVersion;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: ClassUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2359a = String.valueOf('.');

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2360b = String.valueOf('$');

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Map<Class<?>, Class<?>> f2361c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map<Class<?>, Class<?>> f2362d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Map<String, String> f2363e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Map<String, String> f2364f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Map<String, String> f2365g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Map<String, String> f2366h;

    static {
        HashMap map = new HashMap();
        f2361c = map;
        map.put(Boolean.TYPE, Boolean.class);
        map.put(Byte.TYPE, Byte.class);
        map.put(Character.TYPE, Character.class);
        map.put(Short.TYPE, Short.class);
        map.put(Integer.TYPE, Integer.class);
        map.put(Long.TYPE, Long.class);
        map.put(Double.TYPE, Double.class);
        map.put(Float.TYPE, Float.class);
        map.put(Void.TYPE, Void.class);
        f2362d = new HashMap();
        for (Class<?> cls : map.keySet()) {
            Class<?> cls2 = f2361c.get(cls);
            if (!cls.equals(cls2)) {
                f2362d.put(cls2, cls);
            }
        }
        f2363e = new HashMap();
        f2364f = new HashMap();
        for (Class<?> cls3 : f2361c.keySet()) {
            Class<?> cls4 = f2361c.get(cls3);
            if (!cls3.equals(cls4)) {
                f2363e.put(cls3.getName(), cls4.getName());
                f2364f.put(cls4.getName(), cls3.getName());
            }
        }
        f2365g = new HashMap();
        f2366h = new HashMap();
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
        f2365g.put(str, str2);
        f2366h.put(str2, str);
    }

    public static Class<?> b(ClassLoader classLoader, String str, boolean z7) throws ClassNotFoundException {
        try {
            Map<String, String> map = f2365g;
            if (!map.containsKey(str)) {
                return Class.forName(l(str), z7, classLoader);
            }
            return Class.forName("[" + map.get(str), z7, classLoader).getComponentType();
        } catch (ClassNotFoundException e8) {
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf != -1) {
                try {
                    return b(classLoader, str.substring(0, iLastIndexOf) + '$' + str.substring(iLastIndexOf + 1), z7);
                } catch (ClassNotFoundException unused) {
                    throw e8;
                }
            }
            throw e8;
        }
    }

    public static Class<?> c(String str) throws ClassNotFoundException {
        return d(str, true);
    }

    public static Class<?> d(String str, boolean z7) throws ClassNotFoundException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            contextClassLoader = b.class.getClassLoader();
        }
        return b(contextClassLoader, str, z7);
    }

    public static boolean e(Class<?> cls, Class<?> cls2) {
        return f(cls, cls2, d.e(JavaVersion.JAVA_1_5));
    }

    public static boolean f(Class<?> cls, Class<?> cls2, boolean z7) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z7) {
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

    public static boolean g(Class<?>[] clsArr, Class<?>[] clsArr2, boolean z7) {
        if (!a.a(clsArr, clsArr2)) {
            return false;
        }
        if (clsArr == null) {
            clsArr = a.f2341b;
        }
        if (clsArr2 == null) {
            clsArr2 = a.f2341b;
        }
        for (int i8 = 0; i8 < clsArr.length; i8++) {
            if (!f(clsArr[i8], clsArr2[i8], z7)) {
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
        return f2362d.containsKey(cls);
    }

    public static Class<?> j(Class<?> cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : f2361c.get(cls);
    }

    public static String k(String str) {
        String str2 = f2363e.get(str);
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
        String str2 = f2365g.get(strA);
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
        return f2362d.get(cls);
    }
}
