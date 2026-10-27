package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;

/* JADX INFO: compiled from: NavType.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o<Integer> f1733a = new c(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final o<Integer> f1734b = new d(false);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o<int[]> f1735c = new e(true);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final o<Long> f1736d = new f(false);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final o<long[]> f1737e = new g(true);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final o<Float> f1738f = new h(false);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final o<float[]> f1739g = new i(true);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final o<Boolean> f1740h = new j(false);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final o<boolean[]> f1741i = new k(true);
    public static final o<String> j = new a(true);
    public static final o<String[]> k = new b(true);
    private final boolean l;

    /* JADX INFO: compiled from: NavType.java */
    class a extends o<String> {
        a(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "string";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public String b(Bundle bundle, String str) {
            return (String) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public String k(String str) {
            return str;
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, String str2) {
            bundle.putString(str, str2);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class b extends o<String[]> {
        b(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "string[]";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public String[] b(Bundle bundle, String str) {
            return (String[]) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public String[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, String[] strArr) {
            bundle.putStringArray(str, strArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class c extends o<Integer> {
        c(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "integer";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Integer b(Bundle bundle, String str) {
            return (Integer) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Integer k(String str) {
            return str.startsWith("0x") ? Integer.valueOf(Integer.parseInt(str.substring(2), 16)) : Integer.valueOf(Integer.parseInt(str));
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Integer num) {
            bundle.putInt(str, num.intValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class d extends o<Integer> {
        d(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "reference";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Integer b(Bundle bundle, String str) {
            return (Integer) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Integer k(String str) {
            return str.startsWith("0x") ? Integer.valueOf(Integer.parseInt(str.substring(2), 16)) : Integer.valueOf(Integer.parseInt(str));
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Integer num) {
            bundle.putInt(str, num.intValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class e extends o<int[]> {
        e(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "integer[]";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public int[] b(Bundle bundle, String str) {
            return (int[]) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public int[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, int[] iArr) {
            bundle.putIntArray(str, iArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class f extends o<Long> {
        f(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "long";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Long b(Bundle bundle, String str) {
            return (Long) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Long k(String str) {
            if (str.endsWith("L")) {
                str = str.substring(0, str.length() - 1);
            }
            return str.startsWith("0x") ? Long.valueOf(Long.parseLong(str.substring(2), 16)) : Long.valueOf(Long.parseLong(str));
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Long l) {
            bundle.putLong(str, l.longValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class g extends o<long[]> {
        g(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "long[]";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public long[] b(Bundle bundle, String str) {
            return (long[]) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public long[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, long[] jArr) {
            bundle.putLongArray(str, jArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class h extends o<Float> {
        h(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "float";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Float b(Bundle bundle, String str) {
            return (Float) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Float k(String str) {
            return Float.valueOf(Float.parseFloat(str));
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Float f2) {
            bundle.putFloat(str, f2.floatValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class i extends o<float[]> {
        i(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "float[]";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public float[] b(Bundle bundle, String str) {
            return (float[]) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public float[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, float[] fArr) {
            bundle.putFloatArray(str, fArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class j extends o<Boolean> {
        j(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "boolean";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Boolean b(Bundle bundle, String str) {
            return (Boolean) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Boolean k(String str) {
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            throw new IllegalArgumentException("A boolean NavType only accepts \"true\" or \"false\" values.");
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Boolean bool) {
            bundle.putBoolean(str, bool.booleanValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class k extends o<boolean[]> {
        k(boolean z) {
            super(z);
        }

        @Override // androidx.navigation.o
        public String c() {
            return "boolean[]";
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public boolean[] b(Bundle bundle, String str) {
            return (boolean[]) bundle.get(str);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, boolean[] zArr) {
            bundle.putBooleanArray(str, zArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    public static final class l<D extends Enum> extends p<D> {
        private final Class<D> n;

        public l(Class<D> cls) {
            super(false, cls);
            if (cls.isEnum()) {
                this.n = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " is not an Enum type.");
        }

        @Override // androidx.navigation.o.p, androidx.navigation.o
        public String c() {
            return this.n.getName();
        }

        @Override // androidx.navigation.o.p
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public D k(String str) {
            for (D d2 : this.n.getEnumConstants()) {
                if (d2.name().equals(str)) {
                    return d2;
                }
            }
            throw new IllegalArgumentException("Enum value " + str + " not found for type " + this.n.getName() + ".");
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    public static final class m<D extends Parcelable> extends o<D[]> {
        private final Class<D[]> m;

        public m(Class<D> cls) {
            super(true);
            if (!Parcelable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Parcelable.");
            }
            try {
                this.m = (Class<D[]>) Class.forName("[L" + cls.getName() + ";");
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // androidx.navigation.o
        public String c() {
            return this.m.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || m.class != obj.getClass()) {
                return false;
            }
            return this.m.equals(((m) obj).m);
        }

        public int hashCode() {
            return this.m.hashCode();
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public D[] b(Bundle bundle, String str) {
            return (D[]) ((Parcelable[]) bundle.get(str));
        }

        @Override // androidx.navigation.o
        public D[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, D[] dArr) {
            this.m.cast(dArr);
            bundle.putParcelableArray(str, dArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    public static final class n<D> extends o<D> {
        private final Class<D> m;

        public n(Class<D> cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls) || Serializable.class.isAssignableFrom(cls)) {
                this.m = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " does not implement Parcelable or Serializable.");
        }

        @Override // androidx.navigation.o
        public D b(Bundle bundle, String str) {
            return (D) bundle.get(str);
        }

        @Override // androidx.navigation.o
        public String c() {
            return this.m.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || n.class != obj.getClass()) {
                return false;
            }
            return this.m.equals(((n) obj).m);
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: h */
        public D k(String str) {
            throw new UnsupportedOperationException("Parcelables don't support default values.");
        }

        public int hashCode() {
            return this.m.hashCode();
        }

        @Override // androidx.navigation.o
        public void i(Bundle bundle, String str, D d2) {
            this.m.cast(d2);
            if (d2 == null || (d2 instanceof Parcelable)) {
                bundle.putParcelable(str, (Parcelable) d2);
            } else if (d2 instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) d2);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.navigation.o$o, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NavType.java */
    public static final class C0025o<D extends Serializable> extends o<D[]> {
        private final Class<D[]> m;

        public C0025o(Class<D> cls) {
            super(true);
            if (!Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            }
            try {
                this.m = (Class<D[]>) Class.forName("[L" + cls.getName() + ";");
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // androidx.navigation.o
        public String c() {
            return this.m.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || C0025o.class != obj.getClass()) {
                return false;
            }
            return this.m.equals(((C0025o) obj).m);
        }

        public int hashCode() {
            return this.m.hashCode();
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public D[] b(Bundle bundle, String str) {
            return (D[]) ((Serializable[]) bundle.get(str));
        }

        @Override // androidx.navigation.o
        public D[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, D[] dArr) {
            this.m.cast(dArr);
            bundle.putSerializable(str, dArr);
        }
    }

    o(boolean z) {
        this.l = z;
    }

    public static o<?> a(String str, String str2) {
        String strSubstring;
        o<Integer> oVar = f1733a;
        if (oVar.c().equals(str)) {
            return oVar;
        }
        o oVar2 = f1735c;
        if (oVar2.c().equals(str)) {
            return oVar2;
        }
        o<Long> oVar3 = f1736d;
        if (oVar3.c().equals(str)) {
            return oVar3;
        }
        o oVar4 = f1737e;
        if (oVar4.c().equals(str)) {
            return oVar4;
        }
        o<Boolean> oVar5 = f1740h;
        if (oVar5.c().equals(str)) {
            return oVar5;
        }
        o oVar6 = f1741i;
        if (oVar6.c().equals(str)) {
            return oVar6;
        }
        o<String> oVar7 = j;
        if (oVar7.c().equals(str)) {
            return oVar7;
        }
        o oVar8 = k;
        if (oVar8.c().equals(str)) {
            return oVar8;
        }
        o<Float> oVar9 = f1738f;
        if (oVar9.c().equals(str)) {
            return oVar9;
        }
        o oVar10 = f1739g;
        if (oVar10.c().equals(str)) {
            return oVar10;
        }
        o<Integer> oVar11 = f1734b;
        if (oVar11.c().equals(str)) {
            return oVar11;
        }
        if (str == null || str.isEmpty()) {
            return oVar7;
        }
        try {
            if (!str.startsWith(".") || str2 == null) {
                strSubstring = str;
            } else {
                strSubstring = str2 + str;
            }
            if (str.endsWith("[]")) {
                strSubstring = strSubstring.substring(0, strSubstring.length() - 2);
                Class<?> cls = Class.forName(strSubstring);
                if (Parcelable.class.isAssignableFrom(cls)) {
                    return new m(cls);
                }
                if (Serializable.class.isAssignableFrom(cls)) {
                    return new C0025o(cls);
                }
            } else {
                Class<?> cls2 = Class.forName(strSubstring);
                if (Parcelable.class.isAssignableFrom(cls2)) {
                    return new n(cls2);
                }
                if (Enum.class.isAssignableFrom(cls2)) {
                    return new l(cls2);
                }
                if (Serializable.class.isAssignableFrom(cls2)) {
                    return new p(cls2);
                }
            }
            throw new IllegalArgumentException(strSubstring + " is not Serializable or Parcelable.");
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(e2);
        }
    }

    static o d(String str) {
        try {
            try {
                try {
                    try {
                        o<Integer> oVar = f1733a;
                        oVar.k(str);
                        return oVar;
                    } catch (IllegalArgumentException unused) {
                        return j;
                    }
                } catch (IllegalArgumentException unused2) {
                    o<Long> oVar2 = f1736d;
                    oVar2.k(str);
                    return oVar2;
                }
            } catch (IllegalArgumentException unused3) {
                o<Float> oVar3 = f1738f;
                oVar3.k(str);
                return oVar3;
            }
        } catch (IllegalArgumentException unused4) {
            o<Boolean> oVar4 = f1740h;
            oVar4.k(str);
            return oVar4;
        }
    }

    static o e(Object obj) {
        if (obj instanceof Integer) {
            return f1733a;
        }
        if (obj instanceof int[]) {
            return f1735c;
        }
        if (obj instanceof Long) {
            return f1736d;
        }
        if (obj instanceof long[]) {
            return f1737e;
        }
        if (obj instanceof Float) {
            return f1738f;
        }
        if (obj instanceof float[]) {
            return f1739g;
        }
        if (obj instanceof Boolean) {
            return f1740h;
        }
        if (obj instanceof boolean[]) {
            return f1741i;
        }
        if ((obj instanceof String) || obj == null) {
            return j;
        }
        if (obj instanceof String[]) {
            return k;
        }
        if (obj.getClass().isArray() && Parcelable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new m(obj.getClass().getComponentType());
        }
        if (obj.getClass().isArray() && Serializable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new C0025o(obj.getClass().getComponentType());
        }
        if (obj instanceof Parcelable) {
            return new n(obj.getClass());
        }
        if (obj instanceof Enum) {
            return new l(obj.getClass());
        }
        if (obj instanceof Serializable) {
            return new p(obj.getClass());
        }
        throw new IllegalArgumentException("Object of type " + obj.getClass().getName() + " is not supported for navigation arguments.");
    }

    public abstract T b(Bundle bundle, String str);

    public abstract String c();

    public boolean f() {
        return this.l;
    }

    T g(Bundle bundle, String str, String str2) {
        T tK = k(str2);
        i(bundle, str, tK);
        return tK;
    }

    /* JADX INFO: renamed from: h */
    public abstract T k(String str);

    public abstract void i(Bundle bundle, String str, T t);

    public String toString() {
        return c();
    }

    /* JADX INFO: compiled from: NavType.java */
    public static class p<D extends Serializable> extends o<D> {
        private final Class<D> m;

        public p(Class<D> cls) {
            super(true);
            if (!Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            }
            if (!cls.isEnum()) {
                this.m = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " is an Enum. You should use EnumType instead.");
        }

        @Override // androidx.navigation.o
        public String c() {
            return this.m.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof p) {
                return this.m.equals(((p) obj).m);
            }
            return false;
        }

        public int hashCode() {
            return this.m.hashCode();
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public D b(Bundle bundle, String str) {
            return (D) bundle.get(str);
        }

        @Override // androidx.navigation.o
        public D k(String str) {
            throw new UnsupportedOperationException("Serializables don't support default values.");
        }

        @Override // androidx.navigation.o
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, D d2) {
            this.m.cast(d2);
            bundle.putSerializable(str, d2);
        }

        p(boolean z, Class<D> cls) {
            super(z);
            if (Serializable.class.isAssignableFrom(cls)) {
                this.m = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " does not implement Serializable.");
        }
    }
}
