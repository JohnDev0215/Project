package com.google.gson;

import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
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

/* JADX INFO: loaded from: classes.dex */
public final class Gson {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.google.gson.v.a<?> f4407a = com.google.gson.v.a.a(Object.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ThreadLocal<Map<com.google.gson.v.a<?>, FutureTypeAdapter<?>>> f4408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<com.google.gson.v.a<?>, TypeAdapter<?>> f4409c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.google.gson.internal.c f4410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final JsonAdapterAnnotationTypeAdapterFactory f4411e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final List<t> f4412f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final Excluder f4413g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final d f4414h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final Map<Type, f<?>> f4415i;
    final boolean j;
    final boolean k;
    final boolean l;
    final boolean m;
    final boolean n;
    final boolean o;
    final boolean p;
    final String q;
    final int r;
    final int s;
    final s t;
    final List<t> u;
    final List<t> v;

    static class FutureTypeAdapter<T> extends TypeAdapter<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private TypeAdapter<T> f4420a;

        FutureTypeAdapter() {
        }

        public void a(TypeAdapter<T> typeAdapter) {
            if (this.f4420a != null) {
                throw new AssertionError();
            }
            this.f4420a = typeAdapter;
        }

        @Override // com.google.gson.TypeAdapter
        public T read(com.google.gson.w.a aVar) {
            TypeAdapter<T> typeAdapter = this.f4420a;
            if (typeAdapter != null) {
                return typeAdapter.read(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // com.google.gson.TypeAdapter
        public void write(com.google.gson.w.c cVar, T t) {
            TypeAdapter<T> typeAdapter = this.f4420a;
            if (typeAdapter == null) {
                throw new IllegalStateException();
            }
            typeAdapter.write(cVar, t);
        }
    }

    public Gson() {
        this(Excluder.f4440b, c.f4423b, Collections.emptyMap(), false, false, false, true, false, false, false, s.f4599b, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    private static void a(Object obj, com.google.gson.w.a aVar) {
        if (obj != null) {
            try {
                if (aVar.j0() == com.google.gson.w.b.END_DOCUMENT) {
                } else {
                    throw new k("JSON document was not fully consumed.");
                }
            } catch (com.google.gson.w.d e2) {
                throw new r(e2);
            } catch (IOException e3) {
                throw new k(e3);
            }
        }
    }

    private static TypeAdapter<AtomicLong> b(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLong>() { // from class: com.google.gson.Gson.4
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public AtomicLong read(com.google.gson.w.a aVar) {
                return new AtomicLong(((Number) typeAdapter.read(aVar)).longValue());
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, AtomicLong atomicLong) {
                typeAdapter.write(cVar, Long.valueOf(atomicLong.get()));
            }
        }.nullSafe();
    }

    private static TypeAdapter<AtomicLongArray> c(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLongArray>() { // from class: com.google.gson.Gson.5
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public AtomicLongArray read(com.google.gson.w.a aVar) throws IOException {
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.V()) {
                    arrayList.add(Long.valueOf(((Number) typeAdapter.read(aVar)).longValue()));
                }
                aVar.R();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i2 = 0; i2 < size; i2++) {
                    atomicLongArray.set(i2, ((Long) arrayList.get(i2)).longValue());
                }
                return atomicLongArray;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, AtomicLongArray atomicLongArray) throws IOException {
                cVar.j();
                int length = atomicLongArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    typeAdapter.write(cVar, Long.valueOf(atomicLongArray.get(i2)));
                }
                cVar.R();
            }
        }.nullSafe();
    }

    static void d(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private TypeAdapter<Number> e(boolean z) {
        return z ? TypeAdapters.v : new TypeAdapter<Number>() { // from class: com.google.gson.Gson.1
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Double read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return Double.valueOf(aVar.a0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                if (number == null) {
                    cVar.Y();
                } else {
                    Gson.d(number.doubleValue());
                    cVar.k0(number);
                }
            }
        };
    }

    private TypeAdapter<Number> f(boolean z) {
        return z ? TypeAdapters.u : new TypeAdapter<Number>() { // from class: com.google.gson.Gson.2
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Float read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return Float.valueOf((float) aVar.a0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                if (number == null) {
                    cVar.Y();
                } else {
                    Gson.d(number.floatValue());
                    cVar.k0(number);
                }
            }
        };
    }

    private static TypeAdapter<Number> n(s sVar) {
        return sVar == s.f4599b ? TypeAdapters.t : new TypeAdapter<Number>() { // from class: com.google.gson.Gson.3
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Number read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return Long.valueOf(aVar.c0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                if (number == null) {
                    cVar.Y();
                } else {
                    cVar.l0(number.toString());
                }
            }
        };
    }

    public <T> T g(com.google.gson.w.a aVar, Type type) {
        boolean zW = aVar.W();
        boolean z = true;
        aVar.o0(true);
        try {
            try {
                try {
                    aVar.j0();
                    z = false;
                    T t = k(com.google.gson.v.a.b(type)).read(aVar);
                    aVar.o0(zW);
                    return t;
                } catch (EOFException e2) {
                    if (!z) {
                        throw new r(e2);
                    }
                    aVar.o0(zW);
                    return null;
                } catch (AssertionError e3) {
                    throw new AssertionError("AssertionError (GSON 2.8.5): " + e3.getMessage(), e3);
                }
            } catch (IOException e4) {
                throw new r(e4);
            } catch (IllegalStateException e5) {
                throw new r(e5);
            }
        } catch (Throwable th) {
            aVar.o0(zW);
            throw th;
        }
    }

    public <T> T h(Reader reader, Type type) {
        com.google.gson.w.a aVarO = o(reader);
        T t = (T) g(aVarO, type);
        a(t, aVarO);
        return t;
    }

    public <T> T i(String str, Class<T> cls) {
        return (T) com.google.gson.internal.j.c(cls).cast(j(str, cls));
    }

    public <T> T j(String str, Type type) {
        if (str == null) {
            return null;
        }
        return (T) h(new StringReader(str), type);
    }

    public <T> TypeAdapter<T> k(com.google.gson.v.a<T> aVar) {
        TypeAdapter<T> typeAdapter = (TypeAdapter) this.f4409c.get(aVar == null ? f4407a : aVar);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        Map<com.google.gson.v.a<?>, FutureTypeAdapter<?>> map = this.f4408b.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f4408b.set(map);
            z = true;
        }
        FutureTypeAdapter<?> futureTypeAdapter = map.get(aVar);
        if (futureTypeAdapter != null) {
            return futureTypeAdapter;
        }
        try {
            FutureTypeAdapter<?> futureTypeAdapter2 = new FutureTypeAdapter<>();
            map.put(aVar, futureTypeAdapter2);
            Iterator<t> it = this.f4412f.iterator();
            while (it.hasNext()) {
                TypeAdapter<T> typeAdapterB = it.next().b(this, aVar);
                if (typeAdapterB != null) {
                    futureTypeAdapter2.a(typeAdapterB);
                    this.f4409c.put(aVar, typeAdapterB);
                    return typeAdapterB;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.5) cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z) {
                this.f4408b.remove();
            }
        }
    }

    public <T> TypeAdapter<T> l(Class<T> cls) {
        return k(com.google.gson.v.a.a(cls));
    }

    public <T> TypeAdapter<T> m(t tVar, com.google.gson.v.a<T> aVar) {
        if (!this.f4412f.contains(tVar)) {
            tVar = this.f4411e;
        }
        boolean z = false;
        for (t tVar2 : this.f4412f) {
            if (z) {
                TypeAdapter<T> typeAdapterB = tVar2.b(this, aVar);
                if (typeAdapterB != null) {
                    return typeAdapterB;
                }
            } else if (tVar2 == tVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public com.google.gson.w.a o(Reader reader) {
        com.google.gson.w.a aVar = new com.google.gson.w.a(reader);
        aVar.o0(this.o);
        return aVar;
    }

    public com.google.gson.w.c p(Writer writer) throws IOException {
        if (this.l) {
            writer.write(")]}'\n");
        }
        com.google.gson.w.c cVar = new com.google.gson.w.c(writer);
        if (this.n) {
            cVar.e0("  ");
        }
        cVar.g0(this.j);
        return cVar;
    }

    public String q(j jVar) {
        StringWriter stringWriter = new StringWriter();
        u(jVar, stringWriter);
        return stringWriter.toString();
    }

    public String r(Object obj) {
        return obj == null ? q(l.f4595a) : s(obj, obj.getClass());
    }

    public String s(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        w(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void t(j jVar, com.google.gson.w.c cVar) {
        boolean zV = cVar.V();
        cVar.f0(true);
        boolean zU = cVar.U();
        cVar.d0(this.m);
        boolean zT = cVar.T();
        cVar.g0(this.j);
        try {
            try {
                com.google.gson.internal.k.b(jVar, cVar);
            } catch (IOException e2) {
                throw new k(e2);
            } catch (AssertionError e3) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e3.getMessage(), e3);
            }
        } finally {
            cVar.f0(zV);
            cVar.d0(zU);
            cVar.g0(zT);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.j + ",factories:" + this.f4412f + ",instanceCreators:" + this.f4410d + "}";
    }

    public void u(j jVar, Appendable appendable) {
        try {
            t(jVar, p(com.google.gson.internal.k.c(appendable)));
        } catch (IOException e2) {
            throw new k(e2);
        }
    }

    public void v(Object obj, Type type, com.google.gson.w.c cVar) {
        TypeAdapter typeAdapterK = k(com.google.gson.v.a.b(type));
        boolean zV = cVar.V();
        cVar.f0(true);
        boolean zU = cVar.U();
        cVar.d0(this.m);
        boolean zT = cVar.T();
        cVar.g0(this.j);
        try {
            try {
                typeAdapterK.write(cVar, obj);
            } catch (IOException e2) {
                throw new k(e2);
            } catch (AssertionError e3) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e3.getMessage(), e3);
            }
        } finally {
            cVar.f0(zV);
            cVar.d0(zU);
            cVar.g0(zT);
        }
    }

    public void w(Object obj, Type type, Appendable appendable) {
        try {
            v(obj, type, p(com.google.gson.internal.k.c(appendable)));
        } catch (IOException e2) {
            throw new k(e2);
        }
    }

    Gson(Excluder excluder, d dVar, Map<Type, f<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, s sVar, String str, int i2, int i3, List<t> list, List<t> list2, List<t> list3) {
        this.f4408b = new ThreadLocal<>();
        this.f4409c = new ConcurrentHashMap();
        this.f4413g = excluder;
        this.f4414h = dVar;
        this.f4415i = map;
        com.google.gson.internal.c cVar = new com.google.gson.internal.c(map);
        this.f4410d = cVar;
        this.j = z;
        this.k = z2;
        this.l = z3;
        this.m = z4;
        this.n = z5;
        this.o = z6;
        this.p = z7;
        this.t = sVar;
        this.q = str;
        this.r = i2;
        this.s = i3;
        this.u = list;
        this.v = list2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(TypeAdapters.Y);
        arrayList.add(ObjectTypeAdapter.f4469a);
        arrayList.add(excluder);
        arrayList.addAll(list3);
        arrayList.add(TypeAdapters.D);
        arrayList.add(TypeAdapters.m);
        arrayList.add(TypeAdapters.f4514g);
        arrayList.add(TypeAdapters.f4516i);
        arrayList.add(TypeAdapters.k);
        TypeAdapter<Number> typeAdapterN = n(sVar);
        arrayList.add(TypeAdapters.c(Long.TYPE, Long.class, typeAdapterN));
        arrayList.add(TypeAdapters.c(Double.TYPE, Double.class, e(z7)));
        arrayList.add(TypeAdapters.c(Float.TYPE, Float.class, f(z7)));
        arrayList.add(TypeAdapters.x);
        arrayList.add(TypeAdapters.o);
        arrayList.add(TypeAdapters.q);
        arrayList.add(TypeAdapters.b(AtomicLong.class, b(typeAdapterN)));
        arrayList.add(TypeAdapters.b(AtomicLongArray.class, c(typeAdapterN)));
        arrayList.add(TypeAdapters.s);
        arrayList.add(TypeAdapters.z);
        arrayList.add(TypeAdapters.F);
        arrayList.add(TypeAdapters.H);
        arrayList.add(TypeAdapters.b(BigDecimal.class, TypeAdapters.B));
        arrayList.add(TypeAdapters.b(BigInteger.class, TypeAdapters.C));
        arrayList.add(TypeAdapters.J);
        arrayList.add(TypeAdapters.L);
        arrayList.add(TypeAdapters.P);
        arrayList.add(TypeAdapters.R);
        arrayList.add(TypeAdapters.W);
        arrayList.add(TypeAdapters.N);
        arrayList.add(TypeAdapters.f4511d);
        arrayList.add(DateTypeAdapter.f4460a);
        arrayList.add(TypeAdapters.U);
        arrayList.add(TimeTypeAdapter.f4490a);
        arrayList.add(SqlDateTypeAdapter.f4488a);
        arrayList.add(TypeAdapters.S);
        arrayList.add(ArrayTypeAdapter.f4454a);
        arrayList.add(TypeAdapters.f4509b);
        arrayList.add(new CollectionTypeAdapterFactory(cVar));
        arrayList.add(new MapTypeAdapterFactory(cVar, z2));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(cVar);
        this.f4411e = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(TypeAdapters.Z);
        arrayList.add(new ReflectiveTypeAdapterFactory(cVar, dVar, excluder, jsonAdapterAnnotationTypeAdapterFactory));
        this.f4412f = Collections.unmodifiableList(arrayList);
    }
}
