package spi;

import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: compiled from: Gson.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j4 {
    public static final vb<?> v = new vb<>(Object.class);
    public final ThreadLocal<Map<vb<?>, a<?>>> a;
    public final Map<vb<?>, nb<?>> b;
    public final o2 c;
    public final f5 d;
    public final List<ob> e;
    public final s3 f;
    public final x3 g;
    public final Map<Type, b5<?>> h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;
    public final String p;
    public final int q;
    public final int r;
    public final k6 s;
    public final List<ob> t;
    public final List<ob> u;

    /* JADX INFO: compiled from: Gson.java */
    public static class a<T> extends nb<T> {
        public nb<T> a;

        @Override // spi.nb
        public final T a(o5 o5Var) {
            nb<T> nbVar = this.a;
            if (nbVar != null) {
                return nbVar.a(o5Var);
            }
            throw new IllegalStateException();
        }

        @Override // spi.nb
        public final void b(v5 v5Var, T t) {
            nb<T> nbVar = this.a;
            if (nbVar == null) {
                throw new IllegalStateException();
            }
            nbVar.b(v5Var, t);
        }
    }

    public j4() {
        this(s3.f, w3.a, Collections.emptyMap(), false, false, false, true, false, false, false, k6.a, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    public static void a(Object obj, o5 o5Var) {
        if (obj != null) {
            try {
                if (o5Var.v() == s5.END_DOCUMENT) {
                } else {
                    throw new j5("JSON document was not fully consumed.");
                }
            } catch (m6 e) {
                throw new r5(e);
            } catch (IOException e2) {
                throw new j5(e2);
            }
        }
    }

    public static void b(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public final <T> T c(String str, Type type) {
        if (str == null) {
            return null;
        }
        o5 o5Var = new o5(new StringReader(str));
        o5Var.b = this.n;
        T t = (T) d(o5Var, type);
        a(t, o5Var);
        return t;
    }

    public final <T> T d(o5 o5Var, Type type) {
        boolean z = o5Var.b;
        boolean z2 = true;
        o5Var.b = true;
        try {
            try {
                try {
                    o5Var.v();
                    z2 = false;
                    T tA = e(new vb<>(type)).a(o5Var);
                    o5Var.b = z;
                    return tA;
                } catch (EOFException e) {
                    if (!z2) {
                        throw new r5(e);
                    }
                    o5Var.b = z;
                    return null;
                } catch (IOException e2) {
                    throw new r5(e2);
                }
            } catch (AssertionError e3) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e3.getMessage(), e3);
            } catch (IllegalStateException e4) {
                throw new r5(e4);
            }
        } catch (Throwable th) {
            o5Var.b = z;
            throw th;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map<spi.vb<?>, spi.nb<?>>, java.util.concurrent.ConcurrentHashMap] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.util.Map<spi.vb<?>, spi.nb<?>>, java.util.concurrent.ConcurrentHashMap] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final <T> nb<T> e(vb<T> vbVar) {
        nb<T> nbVar = (nb) this.b.get(vbVar);
        if (nbVar != null) {
            return nbVar;
        }
        Map<vb<?>, a<?>> map = this.a.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.a.set(map);
            z = true;
        }
        a<?> aVar = map.get(vbVar);
        if (aVar != null) {
            return aVar;
        }
        try {
            a<?> aVar2 = new a<>();
            map.put(vbVar, aVar2);
            Iterator<ob> it = this.e.iterator();
            while (it.hasNext()) {
                nb<T> nbVarA = it.next().a(this, vbVar);
                if (nbVarA != null) {
                    if (aVar2.a != null) {
                        throw new AssertionError();
                    }
                    aVar2.a = nbVarA;
                    this.b.put((vb<?>) vbVar, (nb<?>) nbVarA);
                    return nbVarA;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.5) cannot handle " + vbVar);
        } finally {
            map.remove(vbVar);
            if (z) {
                this.a.remove();
            }
        }
    }

    public final <T> nb<T> f(ob obVar, vb<T> vbVar) {
        if (!this.e.contains(obVar)) {
            obVar = this.d;
        }
        boolean z = false;
        for (ob obVar2 : this.e) {
            if (z) {
                nb<T> nbVarA = obVar2.a(this, vbVar);
                if (nbVarA != null) {
                    return nbVarA;
                }
            } else if (obVar2 == obVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + vbVar);
    }

    public final v5 g(Writer writer) throws IOException {
        if (this.k) {
            writer.write(")]}'\n");
        }
        v5 v5Var = new v5(writer);
        if (this.m) {
            v5Var.d = "  ";
            v5Var.e = ": ";
        }
        v5Var.i = this.i;
        return v5Var;
    }

    public final String h(Object obj) {
        if (obj != null) {
            return i(obj, obj.getClass());
        }
        i5 i5Var = k5.a;
        StringWriter stringWriter = new StringWriter();
        try {
            k(i5Var, g(stringWriter));
            return stringWriter.toString();
        } catch (IOException e) {
            throw new j5(e);
        }
    }

    public final String i(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        try {
            j(obj, type, g(stringWriter));
            return stringWriter.toString();
        } catch (IOException e) {
            throw new j5(e);
        }
    }

    public final void j(Object obj, Type type, v5 v5Var) {
        nb nbVarE = e(new vb(type));
        boolean z = v5Var.f;
        v5Var.f = true;
        boolean z2 = v5Var.g;
        v5Var.g = this.l;
        boolean z3 = v5Var.i;
        v5Var.i = this.i;
        try {
            try {
                try {
                    nbVarE.b(v5Var, obj);
                } catch (AssertionError e) {
                    throw new AssertionError("AssertionError (GSON 2.8.5): " + e.getMessage(), e);
                }
            } catch (IOException e2) {
                throw new j5(e2);
            }
        } finally {
            v5Var.f = z;
            v5Var.g = z2;
            v5Var.i = z3;
        }
    }

    public final void k(i5 i5Var, v5 v5Var) {
        boolean z = v5Var.f;
        v5Var.f = true;
        boolean z2 = v5Var.g;
        v5Var.g = this.l;
        boolean z3 = v5Var.i;
        v5Var.i = this.i;
        try {
            try {
                ia.a(i5Var, v5Var);
            } catch (IOException e) {
                throw new j5(e);
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e2.getMessage(), e2);
            }
        } finally {
            v5Var.f = z;
            v5Var.g = z2;
            v5Var.i = z3;
        }
    }

    public final String toString() {
        return "{serializeNulls:" + this.i + ",factories:" + this.e + ",instanceCreators:" + this.c + "}";
    }

    public j4(s3 s3Var, x3 x3Var, Map<Type, b5<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, k6 k6Var, String str, int i, int i2, List<ob> list, List<ob> list2, List<ob> list3) {
        nb g4Var;
        nb e4Var;
        nb f4Var;
        this.a = new ThreadLocal<>();
        this.b = new ConcurrentHashMap();
        this.f = s3Var;
        this.g = x3Var;
        this.h = map;
        o2 o2Var = new o2(map);
        this.c = o2Var;
        this.i = z;
        this.j = z2;
        this.k = z3;
        this.l = z4;
        this.m = z5;
        this.n = z6;
        this.o = z7;
        this.s = k6Var;
        this.p = str;
        this.q = i;
        this.r = i2;
        this.t = list;
        this.u = list2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(qb.Y);
        arrayList.add(c8.b);
        arrayList.add(s3Var);
        arrayList.addAll(list3);
        arrayList.add(qb.D);
        arrayList.add(qb.m);
        arrayList.add(qb.g);
        arrayList.add(qb.i);
        arrayList.add(qb.k);
        if (k6Var == k6.a) {
            g4Var = qb.t;
        } else {
            g4Var = new g4();
        }
        arrayList.add(new sb(Long.TYPE, Long.class, g4Var));
        Class cls = Double.TYPE;
        if (z7) {
            e4Var = qb.v;
        } else {
            e4Var = new e4(this);
        }
        arrayList.add(new sb(cls, Double.class, e4Var));
        Class cls2 = Float.TYPE;
        if (z7) {
            f4Var = qb.u;
        } else {
            f4Var = new f4(this);
        }
        arrayList.add(new sb(cls2, Float.class, f4Var));
        arrayList.add(qb.x);
        arrayList.add(qb.o);
        arrayList.add(qb.q);
        arrayList.add(new rb(AtomicLong.class, new mb(new h4(g4Var))));
        arrayList.add(new rb(AtomicLongArray.class, new mb(new i4(g4Var))));
        arrayList.add(qb.s);
        arrayList.add(qb.z);
        arrayList.add(qb.F);
        arrayList.add(qb.H);
        arrayList.add(new rb(BigDecimal.class, qb.B));
        arrayList.add(new rb(BigInteger.class, qb.C));
        arrayList.add(qb.J);
        arrayList.add(qb.L);
        arrayList.add(qb.P);
        arrayList.add(qb.R);
        arrayList.add(qb.W);
        arrayList.add(qb.N);
        arrayList.add(qb.d);
        arrayList.add(e3.b);
        arrayList.add(qb.U);
        arrayList.add(xa.b);
        arrayList.add(ha.b);
        arrayList.add(qb.S);
        arrayList.add(r.c);
        arrayList.add(qb.b);
        arrayList.add(new g2(o2Var));
        arrayList.add(new n6(o2Var, z2));
        f5 f5Var = new f5(o2Var);
        this.d = f5Var;
        arrayList.add(f5Var);
        arrayList.add(qb.Z);
        arrayList.add(new s9(o2Var, x3Var, s3Var, f5Var));
        this.e = Collections.unmodifiableList(arrayList);
    }
}
