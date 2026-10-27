package com.google.gson;

import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
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

/* JADX INFO: compiled from: Gson.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.google.gson.s.a<?> f3976a = com.google.gson.s.a.a(Object.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ThreadLocal<Map<com.google.gson.s.a<?>, f<?>>> f3977b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<com.google.gson.s.a<?>, p<?>> f3978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.google.gson.internal.b f3979d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.google.gson.internal.k.e f3980e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final List<q> f3981f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final com.google.gson.internal.c f3982g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final com.google.gson.c f3983h;
    final Map<Type, com.google.gson.e<?>> i;
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
    final LongSerializationPolicy t;
    final List<q> u;
    final List<q> v;
    final o w;
    final o x;

    /* JADX INFO: compiled from: Gson.java */
    class a extends p<Number> {
        a() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Double b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return Double.valueOf(aVar.o());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.m();
            } else {
                d.d(number.doubleValue());
                bVar.y(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    class b extends p<Number> {
        b() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Float b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.o());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.m();
            } else {
                d.d(number.floatValue());
                bVar.y(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    class c extends p<Number> {
        c() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return Long.valueOf(aVar.q());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.m();
            } else {
                bVar.z(number.toString());
            }
        }
    }

    /* JADX INFO: renamed from: com.google.gson.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Gson.java */
    class C0073d extends p<AtomicLong> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p f3986a;

        C0073d(p pVar) {
            this.f3986a = pVar;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLong b(com.google.gson.stream.a aVar) throws IOException {
            return new AtomicLong(((Number) this.f3986a.b(aVar)).longValue());
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, AtomicLong atomicLong) throws IOException {
            this.f3986a.d(bVar, Long.valueOf(atomicLong.get()));
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    class e extends p<AtomicLongArray> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p f3987a;

        e(p pVar) {
            this.f3987a = pVar;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLongArray b(com.google.gson.stream.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.j()) {
                arrayList.add(Long.valueOf(((Number) this.f3987a.b(aVar)).longValue()));
            }
            aVar.f();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i = 0; i < size; i++) {
                atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
            }
            return atomicLongArray;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, AtomicLongArray atomicLongArray) throws IOException {
            bVar.c();
            int length = atomicLongArray.length();
            for (int i = 0; i < length; i++) {
                this.f3987a.d(bVar, Long.valueOf(atomicLongArray.get(i)));
            }
            bVar.f();
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    static class f<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private p<T> f3988a;

        f() {
        }

        @Override // com.google.gson.p
        public T b(com.google.gson.stream.a aVar) throws IOException {
            p<T> pVar = this.f3988a;
            if (pVar != null) {
                return pVar.b(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // com.google.gson.p
        public void d(com.google.gson.stream.b bVar, T t) throws IOException {
            p<T> pVar = this.f3988a;
            if (pVar == null) {
                throw new IllegalStateException();
            }
            pVar.d(bVar, t);
        }

        public void e(p<T> pVar) {
            if (this.f3988a != null) {
                throw new AssertionError();
            }
            this.f3988a = pVar;
        }
    }

    public d() {
        this(com.google.gson.internal.c.f4032b, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), ToNumberPolicy.DOUBLE, ToNumberPolicy.LAZILY_PARSED_NUMBER);
    }

    private static void a(Object obj, com.google.gson.stream.a aVar) {
        if (obj != null) {
            try {
                if (aVar.x() == JsonToken.END_DOCUMENT) {
                } else {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e2) {
                throw new JsonSyntaxException(e2);
            } catch (IOException e3) {
                throw new JsonIOException(e3);
            }
        }
    }

    private static p<AtomicLong> b(p<Number> pVar) {
        return new C0073d(pVar).a();
    }

    private static p<AtomicLongArray> c(p<Number> pVar) {
        return new e(pVar).a();
    }

    static void d(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private p<Number> e(boolean z) {
        return z ? com.google.gson.internal.k.n.v : new a();
    }

    private p<Number> f(boolean z) {
        return z ? com.google.gson.internal.k.n.u : new b();
    }

    private static p<Number> n(LongSerializationPolicy longSerializationPolicy) {
        return longSerializationPolicy == LongSerializationPolicy.DEFAULT ? com.google.gson.internal.k.n.t : new c();
    }

    public <T> T g(com.google.gson.stream.a aVar, Type type) throws JsonSyntaxException, JsonIOException {
        boolean zK = aVar.k();
        boolean z = true;
        aVar.C(true);
        try {
            try {
                try {
                    aVar.x();
                    z = false;
                    T tB = k(com.google.gson.s.a.b(type)).b(aVar);
                    aVar.C(zK);
                    return tB;
                } catch (EOFException e2) {
                    if (!z) {
                        throw new JsonSyntaxException(e2);
                    }
                    aVar.C(zK);
                    return null;
                } catch (IOException e3) {
                    throw new JsonSyntaxException(e3);
                }
            } catch (AssertionError e4) {
                AssertionError assertionError = new AssertionError("AssertionError (GSON 2.8.9): " + e4.getMessage());
                assertionError.initCause(e4);
                throw assertionError;
            } catch (IllegalStateException e5) {
                throw new JsonSyntaxException(e5);
            }
        } catch (Throwable th) {
            aVar.C(zK);
            throw th;
        }
    }

    public <T> T h(Reader reader, Type type) throws JsonSyntaxException, JsonIOException {
        com.google.gson.stream.a aVarO = o(reader);
        T t = (T) g(aVarO, type);
        a(t, aVarO);
        return t;
    }

    public <T> T i(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) com.google.gson.internal.h.b(cls).cast(j(str, cls));
    }

    public <T> T j(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) h(new StringReader(str), type);
    }

    public <T> p<T> k(com.google.gson.s.a<T> aVar) {
        p<T> pVar = (p) this.f3978c.get(aVar == null ? f3976a : aVar);
        if (pVar != null) {
            return pVar;
        }
        Map<com.google.gson.s.a<?>, f<?>> map = this.f3977b.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f3977b.set(map);
            z = true;
        }
        f<?> fVar = map.get(aVar);
        if (fVar != null) {
            return fVar;
        }
        try {
            f<?> fVar2 = new f<>();
            map.put(aVar, fVar2);
            Iterator<q> it = this.f3981f.iterator();
            while (it.hasNext()) {
                p<T> pVarA = it.next().a(this, aVar);
                if (pVarA != null) {
                    fVar2.e(pVarA);
                    this.f3978c.put(aVar, pVarA);
                    return pVarA;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.9) cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z) {
                this.f3977b.remove();
            }
        }
    }

    public <T> p<T> l(Class<T> cls) {
        return k(com.google.gson.s.a.a(cls));
    }

    public <T> p<T> m(q qVar, com.google.gson.s.a<T> aVar) {
        if (!this.f3981f.contains(qVar)) {
            qVar = this.f3980e;
        }
        boolean z = false;
        for (q qVar2 : this.f3981f) {
            if (z) {
                p<T> pVarA = qVar2.a(this, aVar);
                if (pVarA != null) {
                    return pVarA;
                }
            } else if (qVar2 == qVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public com.google.gson.stream.a o(Reader reader) {
        com.google.gson.stream.a aVar = new com.google.gson.stream.a(reader);
        aVar.C(this.o);
        return aVar;
    }

    public com.google.gson.stream.b p(Writer writer) throws IOException {
        if (this.l) {
            writer.write(")]}'\n");
        }
        com.google.gson.stream.b bVar = new com.google.gson.stream.b(writer);
        if (this.n) {
            bVar.s("  ");
        }
        bVar.u(this.j);
        return bVar;
    }

    public String q(i iVar) {
        StringWriter stringWriter = new StringWriter();
        u(iVar, stringWriter);
        return stringWriter.toString();
    }

    public String r(Object obj) {
        return obj == null ? q(j.f4149a) : s(obj, obj.getClass());
    }

    public String s(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        w(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void t(i iVar, com.google.gson.stream.b bVar) throws JsonIOException {
        boolean zJ = bVar.j();
        bVar.t(true);
        boolean zI = bVar.i();
        bVar.r(this.m);
        boolean zH = bVar.h();
        bVar.u(this.j);
        try {
            try {
                com.google.gson.internal.i.b(iVar, bVar);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            } catch (AssertionError e3) {
                AssertionError assertionError = new AssertionError("AssertionError (GSON 2.8.9): " + e3.getMessage());
                assertionError.initCause(e3);
                throw assertionError;
            }
        } finally {
            bVar.t(zJ);
            bVar.r(zI);
            bVar.u(zH);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.j + ",factories:" + this.f3981f + ",instanceCreators:" + this.f3979d + "}";
    }

    public void u(i iVar, Appendable appendable) throws JsonIOException {
        try {
            t(iVar, p(com.google.gson.internal.i.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public void v(Object obj, Type type, com.google.gson.stream.b bVar) throws JsonIOException {
        p pVarK = k(com.google.gson.s.a.b(type));
        boolean zJ = bVar.j();
        bVar.t(true);
        boolean zI = bVar.i();
        bVar.r(this.m);
        boolean zH = bVar.h();
        bVar.u(this.j);
        try {
            try {
                pVarK.d(bVar, obj);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            } catch (AssertionError e3) {
                AssertionError assertionError = new AssertionError("AssertionError (GSON 2.8.9): " + e3.getMessage());
                assertionError.initCause(e3);
                throw assertionError;
            }
        } finally {
            bVar.t(zJ);
            bVar.r(zI);
            bVar.u(zH);
        }
    }

    public void w(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            v(obj, type, p(com.google.gson.internal.i.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    d(com.google.gson.internal.c cVar, com.google.gson.c cVar2, Map<Type, com.google.gson.e<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy, String str, int i, int i2, List<q> list, List<q> list2, List<q> list3, o oVar, o oVar2) {
        this.f3977b = new ThreadLocal<>();
        this.f3978c = new ConcurrentHashMap();
        this.f3982g = cVar;
        this.f3983h = cVar2;
        this.i = map;
        com.google.gson.internal.b bVar = new com.google.gson.internal.b(map);
        this.f3979d = bVar;
        this.j = z;
        this.k = z2;
        this.l = z3;
        this.m = z4;
        this.n = z5;
        this.o = z6;
        this.p = z7;
        this.t = longSerializationPolicy;
        this.q = str;
        this.r = i;
        this.s = i2;
        this.u = list;
        this.v = list2;
        this.w = oVar;
        this.x = oVar2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(com.google.gson.internal.k.n.V);
        arrayList.add(com.google.gson.internal.k.j.e(oVar));
        arrayList.add(cVar);
        arrayList.addAll(list3);
        arrayList.add(com.google.gson.internal.k.n.B);
        arrayList.add(com.google.gson.internal.k.n.m);
        arrayList.add(com.google.gson.internal.k.n.f4113g);
        arrayList.add(com.google.gson.internal.k.n.i);
        arrayList.add(com.google.gson.internal.k.n.k);
        p<Number> pVarN = n(longSerializationPolicy);
        arrayList.add(com.google.gson.internal.k.n.b(Long.TYPE, Long.class, pVarN));
        arrayList.add(com.google.gson.internal.k.n.b(Double.TYPE, Double.class, e(z7)));
        arrayList.add(com.google.gson.internal.k.n.b(Float.TYPE, Float.class, f(z7)));
        arrayList.add(com.google.gson.internal.k.i.e(oVar2));
        arrayList.add(com.google.gson.internal.k.n.o);
        arrayList.add(com.google.gson.internal.k.n.q);
        arrayList.add(com.google.gson.internal.k.n.a(AtomicLong.class, b(pVarN)));
        arrayList.add(com.google.gson.internal.k.n.a(AtomicLongArray.class, c(pVarN)));
        arrayList.add(com.google.gson.internal.k.n.s);
        arrayList.add(com.google.gson.internal.k.n.x);
        arrayList.add(com.google.gson.internal.k.n.D);
        arrayList.add(com.google.gson.internal.k.n.F);
        arrayList.add(com.google.gson.internal.k.n.a(BigDecimal.class, com.google.gson.internal.k.n.z));
        arrayList.add(com.google.gson.internal.k.n.a(BigInteger.class, com.google.gson.internal.k.n.A));
        arrayList.add(com.google.gson.internal.k.n.H);
        arrayList.add(com.google.gson.internal.k.n.J);
        arrayList.add(com.google.gson.internal.k.n.N);
        arrayList.add(com.google.gson.internal.k.n.P);
        arrayList.add(com.google.gson.internal.k.n.T);
        arrayList.add(com.google.gson.internal.k.n.L);
        arrayList.add(com.google.gson.internal.k.n.f4110d);
        arrayList.add(com.google.gson.internal.k.c.f4061a);
        arrayList.add(com.google.gson.internal.k.n.R);
        if (com.google.gson.internal.m.d.f4143a) {
            arrayList.add(com.google.gson.internal.m.d.f4147e);
            arrayList.add(com.google.gson.internal.m.d.f4146d);
            arrayList.add(com.google.gson.internal.m.d.f4148f);
        }
        arrayList.add(com.google.gson.internal.k.a.f4055a);
        arrayList.add(com.google.gson.internal.k.n.f4108b);
        arrayList.add(new com.google.gson.internal.k.b(bVar));
        arrayList.add(new com.google.gson.internal.k.h(bVar, z2));
        com.google.gson.internal.k.e eVar = new com.google.gson.internal.k.e(bVar);
        this.f3980e = eVar;
        arrayList.add(eVar);
        arrayList.add(com.google.gson.internal.k.n.W);
        arrayList.add(new com.google.gson.internal.k.k(bVar, cVar2, cVar, eVar));
        this.f3981f = Collections.unmodifiableList(arrayList);
    }
}
