package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;

/* JADX INFO: compiled from: NavType.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final p<Integer> f2741b = new c(false);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final p<Integer> f2742c = new d(false);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p<int[]> f2743d = new e(true);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final p<Long> f2744e = new f(false);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final p<long[]> f2745f = new g(true);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final p<Float> f2746g = new h(false);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final p<float[]> f2747h = new i(true);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final p<Boolean> f2748i = new j(false);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final p<boolean[]> f2749j = new k(true);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final p<String> f2750k = new a(true);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final p<String[]> f2751l = new b(true);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f2752a;

    /* JADX INFO: compiled from: NavType.java */
    class a extends p<String> {
        a(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "string";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public String b(Bundle bundle, String str) {
            return (String) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public String k(String str) {
            return str;
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, String str2) {
            bundle.putString(str, str2);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class b extends p<String[]> {
        b(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "string[]";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public String[] b(Bundle bundle, String str) {
            return (String[]) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public String[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, String[] strArr) {
            bundle.putStringArray(str, strArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class c extends p<Integer> {
        c(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "integer";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Integer b(Bundle bundle, String str) {
            return (Integer) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Integer k(String str) {
            return str.startsWith("0x") ? Integer.valueOf(Integer.parseInt(str.substring(2), 16)) : Integer.valueOf(Integer.parseInt(str));
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Integer num) {
            bundle.putInt(str, num.intValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class d extends p<Integer> {
        d(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "reference";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Integer b(Bundle bundle, String str) {
            return (Integer) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Integer k(String str) {
            return str.startsWith("0x") ? Integer.valueOf(Integer.parseInt(str.substring(2), 16)) : Integer.valueOf(Integer.parseInt(str));
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Integer num) {
            bundle.putInt(str, num.intValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class e extends p<int[]> {
        e(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "integer[]";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public int[] b(Bundle bundle, String str) {
            return (int[]) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public int[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, int[] iArr) {
            bundle.putIntArray(str, iArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class f extends p<Long> {
        f(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "long";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Long b(Bundle bundle, String str) {
            return (Long) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Long k(String str) {
            if (str.endsWith("L")) {
                str = str.substring(0, str.length() - 1);
            }
            return str.startsWith("0x") ? Long.valueOf(Long.parseLong(str.substring(2), 16)) : Long.valueOf(Long.parseLong(str));
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Long l7) {
            bundle.putLong(str, l7.longValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class g extends p<long[]> {
        g(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "long[]";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public long[] b(Bundle bundle, String str) {
            return (long[]) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public long[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, long[] jArr) {
            bundle.putLongArray(str, jArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class h extends p<Float> {
        h(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "float";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Float b(Bundle bundle, String str) {
            return (Float) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public Float k(String str) {
            return Float.valueOf(Float.parseFloat(str));
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Float f7) {
            bundle.putFloat(str, f7.floatValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class i extends p<float[]> {
        i(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "float[]";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public float[] b(Bundle bundle, String str) {
            return (float[]) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public float[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, float[] fArr) {
            bundle.putFloatArray(str, fArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class j extends p<Boolean> {
        j(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "boolean";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public Boolean b(Bundle bundle, String str) {
            return (Boolean) bundle.get(str);
        }

        @Override // androidx.navigation.p
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

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, Boolean bool) {
            bundle.putBoolean(str, bool.booleanValue());
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    class k extends p<boolean[]> {
        k(boolean z6) {
            super(z6);
        }

        @Override // androidx.navigation.p
        public String c() {
            return "boolean[]";
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public boolean[] b(Bundle bundle, String str) {
            return (boolean[]) bundle.get(str);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public boolean[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, boolean[] zArr) {
            bundle.putBooleanArray(str, zArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    public static final class l<D extends Enum> extends C0026p<D> {

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private final Class<D> f2753n;

        public l(Class<D> cls) {
            super(false, cls);
            if (cls.isEnum()) {
                this.f2753n = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " is not an Enum type.");
        }

        @Override // androidx.navigation.p.C0026p, androidx.navigation.p
        public String c() {
            return this.f2753n.getName();
        }

        @Override // androidx.navigation.p.C0026p
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public D k(String str) {
            for (D d7 : this.f2753n.getEnumConstants()) {
                if (d7.name().equals(str)) {
                    return d7;
                }
            }
            throw new IllegalArgumentException("Enum value " + str + " not found for type " + this.f2753n.getName() + ".");
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    public static final class m<D extends Parcelable> extends p<D[]> {

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private final Class<D[]> f2754m;

        public m(Class<D> cls) {
            super(true);
            if (!Parcelable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Parcelable.");
            }
            try {
                this.f2754m = (Class<D[]>) Class.forName("[L" + cls.getName() + ";");
            } catch (ClassNotFoundException e7) {
                throw new RuntimeException(e7);
            }
        }

        @Override // androidx.navigation.p
        public String c() {
            return this.f2754m.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || m.class != obj.getClass()) {
                return false;
            }
            return this.f2754m.equals(((m) obj).f2754m);
        }

        public int hashCode() {
            return this.f2754m.hashCode();
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public D[] b(Bundle bundle, String str) {
            return (D[]) ((Parcelable[]) bundle.get(str));
        }

        @Override // androidx.navigation.p
        public D[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, D[] dArr) {
            this.f2754m.cast(dArr);
            bundle.putParcelableArray(str, dArr);
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    public static final class n<D> extends p<D> {

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private final Class<D> f2755m;

        public n(Class<D> cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls) || Serializable.class.isAssignableFrom(cls)) {
                this.f2755m = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " does not implement Parcelable or Serializable.");
        }

        @Override // androidx.navigation.p
        public D b(Bundle bundle, String str) {
            return (D) bundle.get(str);
        }

        @Override // androidx.navigation.p
        public String c() {
            return this.f2755m.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || n.class != obj.getClass()) {
                return false;
            }
            return this.f2755m.equals(((n) obj).f2755m);
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: h */
        public D k(String str) {
            throw new UnsupportedOperationException("Parcelables don't support default values.");
        }

        public int hashCode() {
            return this.f2755m.hashCode();
        }

        @Override // androidx.navigation.p
        public void i(Bundle bundle, String str, D d7) {
            this.f2755m.cast(d7);
            if (d7 == null || (d7 instanceof Parcelable)) {
                bundle.putParcelable(str, (Parcelable) d7);
            } else if (d7 instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) d7);
            }
        }
    }

    /* JADX INFO: compiled from: NavType.java */
    public static final class o<D extends Serializable> extends p<D[]> {

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private final Class<D[]> f2756m;

        public o(Class<D> cls) {
            super(true);
            if (!Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            }
            try {
                this.f2756m = (Class<D[]>) Class.forName("[L" + cls.getName() + ";");
            } catch (ClassNotFoundException e7) {
                throw new RuntimeException(e7);
            }
        }

        @Override // androidx.navigation.p
        public String c() {
            return this.f2756m.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || o.class != obj.getClass()) {
                return false;
            }
            return this.f2756m.equals(((o) obj).f2756m);
        }

        public int hashCode() {
            return this.f2756m.hashCode();
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public D[] b(Bundle bundle, String str) {
            return (D[]) ((Serializable[]) bundle.get(str));
        }

        @Override // androidx.navigation.p
        public D[] k(String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, D[] dArr) {
            this.f2756m.cast(dArr);
            bundle.putSerializable(str, dArr);
        }
    }

    p(boolean z6) {
        this.f2752a = z6;
    }

    public static p<?> a(String str, String str2) {
        String strSubstring;
        p<Integer> pVar = f2741b;
        if (pVar.c().equals(str)) {
            return pVar;
        }
        p pVar2 = f2743d;
        if (pVar2.c().equals(str)) {
            return pVar2;
        }
        p<Long> pVar3 = f2744e;
        if (pVar3.c().equals(str)) {
            return pVar3;
        }
        p pVar4 = f2745f;
        if (pVar4.c().equals(str)) {
            return pVar4;
        }
        p<Boolean> pVar5 = f2748i;
        if (pVar5.c().equals(str)) {
            return pVar5;
        }
        p pVar6 = f2749j;
        if (pVar6.c().equals(str)) {
            return pVar6;
        }
        p<String> pVar7 = f2750k;
        if (pVar7.c().equals(str)) {
            return pVar7;
        }
        p pVar8 = f2751l;
        if (pVar8.c().equals(str)) {
            return pVar8;
        }
        p<Float> pVar9 = f2746g;
        if (pVar9.c().equals(str)) {
            return pVar9;
        }
        p pVar10 = f2747h;
        if (pVar10.c().equals(str)) {
            return pVar10;
        }
        p<Integer> pVar11 = f2742c;
        if (pVar11.c().equals(str)) {
            return pVar11;
        }
        if (str == null || str.isEmpty()) {
            return pVar7;
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
                    return new o(cls);
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
                    return new C0026p(cls2);
                }
            }
            throw new IllegalArgumentException(strSubstring + " is not Serializable or Parcelable.");
        } catch (ClassNotFoundException e7) {
            throw new RuntimeException(e7);
        }
    }

    static p d(String str) {
        try {
            try {
                try {
                    try {
                        p<Integer> pVar = f2741b;
                        pVar.k(str);
                        return pVar;
                    } catch (IllegalArgumentException unused) {
                        return f2750k;
                    }
                } catch (IllegalArgumentException unused2) {
                    p<Long> pVar2 = f2744e;
                    pVar2.k(str);
                    return pVar2;
                }
            } catch (IllegalArgumentException unused3) {
                p<Float> pVar3 = f2746g;
                pVar3.k(str);
                return pVar3;
            }
        } catch (IllegalArgumentException unused4) {
            p<Boolean> pVar4 = f2748i;
            pVar4.k(str);
            return pVar4;
        }
    }

    static p e(Object obj) {
        if (obj instanceof Integer) {
            return f2741b;
        }
        if (obj instanceof int[]) {
            return f2743d;
        }
        if (obj instanceof Long) {
            return f2744e;
        }
        if (obj instanceof long[]) {
            return f2745f;
        }
        if (obj instanceof Float) {
            return f2746g;
        }
        if (obj instanceof float[]) {
            return f2747h;
        }
        if (obj instanceof Boolean) {
            return f2748i;
        }
        if (obj instanceof boolean[]) {
            return f2749j;
        }
        if ((obj instanceof String) || obj == null) {
            return f2750k;
        }
        if (obj instanceof String[]) {
            return f2751l;
        }
        if (obj.getClass().isArray() && Parcelable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new m(obj.getClass().getComponentType());
        }
        if (obj.getClass().isArray() && Serializable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new o(obj.getClass().getComponentType());
        }
        if (obj instanceof Parcelable) {
            return new n(obj.getClass());
        }
        if (obj instanceof Enum) {
            return new l(obj.getClass());
        }
        if (obj instanceof Serializable) {
            return new C0026p(obj.getClass());
        }
        throw new IllegalArgumentException("Object of type " + obj.getClass().getName() + " is not supported for navigation arguments.");
    }

    public abstract T b(Bundle bundle, String str);

    public abstract String c();

    public boolean f() {
        return this.f2752a;
    }

    T g(Bundle bundle, String str, String str2) {
        T tK = k(str2);
        i(bundle, str, tK);
        return tK;
    }

    /* JADX INFO: renamed from: h */
    public abstract T k(String str);

    public abstract void i(Bundle bundle, String str, T t6);

    public String toString() {
        return c();
    }

    /* JADX INFO: renamed from: androidx.navigation.p$p, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NavType.java */
    public static class C0026p<D extends Serializable> extends p<D> {

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private final Class<D> f2757m;

        public C0026p(Class<D> cls) {
            super(true);
            if (!Serializable.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(cls + " does not implement Serializable.");
            }
            if (!cls.isEnum()) {
                this.f2757m = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " is an Enum. You should use EnumType instead.");
        }

        @Override // androidx.navigation.p
        public String c() {
            return this.f2757m.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof C0026p) {
                return this.f2757m.equals(((C0026p) obj).f2757m);
            }
            return false;
        }

        public int hashCode() {
            return this.f2757m.hashCode();
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
        public D b(Bundle bundle, String str) {
            return (D) bundle.get(str);
        }

        @Override // androidx.navigation.p
        public D k(String str) {
            throw new UnsupportedOperationException("Serializables don't support default values.");
        }

        @Override // androidx.navigation.p
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public void i(Bundle bundle, String str, D d7) {
            this.f2757m.cast(d7);
            bundle.putSerializable(str, d7);
        }

        C0026p(boolean z6, Class<D> cls) {
            super(z6);
            if (Serializable.class.isAssignableFrom(cls)) {
                this.f2757m = cls;
                return;
            }
            throw new IllegalArgumentException(cls + " does not implement Serializable.");
        }
    }
}
