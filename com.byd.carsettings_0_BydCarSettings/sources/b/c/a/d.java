package b.c.a;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.LongSerializationPolicy;
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
/* JADX INFO: loaded from: classes3.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.c.a.s.a<?> f2186a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadLocal<Map<b.c.a.s.a<?>, g<?>>> f2187b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<b.c.a.s.a<?>, o<?>> f2188c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<p> f2189d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b.c.a.r.b f2190e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b.c.a.r.c f2191f;
    public final b.c.a.c g;
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final b.c.a.r.i.d m;

    /* JADX INFO: compiled from: Gson.java */
    public static class a extends b.c.a.s.a<Object> {
    }

    /* JADX INFO: compiled from: Gson.java */
    public class b extends o<Number> {
        public b() {
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Double b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return Double.valueOf(aVar.u());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.s();
            } else {
                d.d(number.doubleValue());
                bVar.F(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public class c extends o<Number> {
        public c() {
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Float b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.u());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.s();
            } else {
                d.d(number.floatValue());
                bVar.F(number);
            }
        }
    }

    /* JADX INFO: renamed from: b.c.a.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Gson.java */
    public static class C0089d extends o<Number> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return Long.valueOf(aVar.w());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.s();
            } else {
                bVar.G(number.toString());
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class e extends o<AtomicLong> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f2194a;

        public e(o oVar) {
            this.f2194a = oVar;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLong b(b.c.a.t.a aVar) throws IOException {
            return new AtomicLong(((Number) this.f2194a.b(aVar)).longValue());
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, AtomicLong atomicLong) throws IOException {
            this.f2194a.d(bVar, Long.valueOf(atomicLong.get()));
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class f extends o<AtomicLongArray> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f2195a;

        public f(o oVar) {
            this.f2195a = oVar;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLongArray b(b.c.a.t.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.c();
            while (aVar.n()) {
                arrayList.add(Long.valueOf(((Number) this.f2195a.b(aVar)).longValue()));
            }
            aVar.i();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i = 0; i < size; i++) {
                atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
            }
            return atomicLongArray;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, AtomicLongArray atomicLongArray) throws IOException {
            bVar.e();
            int length = atomicLongArray.length();
            for (int i = 0; i < length; i++) {
                this.f2195a.d(bVar, Long.valueOf(atomicLongArray.get(i)));
            }
            bVar.h();
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class g<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o<T> f2196a;

        @Override // b.c.a.o
        public T b(b.c.a.t.a aVar) throws IOException {
            o<T> oVar = this.f2196a;
            if (oVar != null) {
                return oVar.b(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // b.c.a.o
        public void d(b.c.a.t.b bVar, T t) throws IOException {
            o<T> oVar = this.f2196a;
            if (oVar == null) {
                throw new IllegalStateException();
            }
            oVar.d(bVar, t);
        }

        public void e(o<T> oVar) {
            if (this.f2196a != null) {
                throw new AssertionError();
            }
            this.f2196a = oVar;
        }
    }

    public d() {
        this(b.c.a.r.c.f2227a, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    public static void a(Object obj, b.c.a.t.a aVar) {
        if (obj != null) {
            try {
                if (aVar.E() == JsonToken.END_DOCUMENT) {
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

    public static o<AtomicLong> b(o<Number> oVar) {
        return new e(oVar).a();
    }

    public static o<AtomicLongArray> c(o<Number> oVar) {
        return new f(oVar).a();
    }

    public static void d(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public static o<Number> n(LongSerializationPolicy longSerializationPolicy) {
        return longSerializationPolicy == LongSerializationPolicy.DEFAULT ? b.c.a.r.i.n.t : new C0089d();
    }

    public final o<Number> e(boolean z) {
        return z ? b.c.a.r.i.n.v : new b();
    }

    public final o<Number> f(boolean z) {
        return z ? b.c.a.r.i.n.u : new c();
    }

    public <T> T g(b.c.a.t.a aVar, Type type) throws JsonSyntaxException, JsonIOException {
        boolean zO = aVar.o();
        boolean z = true;
        aVar.L(true);
        try {
            try {
                try {
                    aVar.E();
                    z = false;
                    T tB = k(b.c.a.s.a.b(type)).b(aVar);
                    aVar.L(zO);
                    return tB;
                } catch (IOException e2) {
                    throw new JsonSyntaxException(e2);
                }
            } catch (EOFException e3) {
                if (!z) {
                    throw new JsonSyntaxException(e3);
                }
                aVar.L(zO);
                return null;
            } catch (IllegalStateException e4) {
                throw new JsonSyntaxException(e4);
            }
        } catch (Throwable th) {
            aVar.L(zO);
            throw th;
        }
    }

    public <T> T h(Reader reader, Type type) throws JsonSyntaxException, JsonIOException {
        b.c.a.t.a aVarO = o(reader);
        T t = (T) g(aVarO, type);
        a(t, aVarO);
        return t;
    }

    public <T> T i(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) b.c.a.r.f.c(cls).cast(j(str, cls));
    }

    public <T> T j(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) h(new StringReader(str), type);
    }

    public <T> o<T> k(b.c.a.s.a<T> aVar) {
        o<T> oVar = (o) this.f2188c.get(aVar == null ? f2186a : aVar);
        if (oVar != null) {
            return oVar;
        }
        Map<b.c.a.s.a<?>, g<?>> map = this.f2187b.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f2187b.set(map);
            z = true;
        }
        g<?> gVar = map.get(aVar);
        if (gVar != null) {
            return gVar;
        }
        try {
            g<?> gVar2 = new g<>();
            map.put(aVar, gVar2);
            Iterator<p> it = this.f2189d.iterator();
            while (it.hasNext()) {
                o<T> oVarA = it.next().a(this, aVar);
                if (oVarA != null) {
                    gVar2.e(oVarA);
                    this.f2188c.put(aVar, oVarA);
                    return oVarA;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z) {
                this.f2187b.remove();
            }
        }
    }

    public <T> o<T> l(Class<T> cls) {
        return k(b.c.a.s.a.a(cls));
    }

    public <T> o<T> m(p pVar, b.c.a.s.a<T> aVar) {
        if (!this.f2189d.contains(pVar)) {
            pVar = this.m;
        }
        boolean z = false;
        for (p pVar2 : this.f2189d) {
            if (z) {
                o<T> oVarA = pVar2.a(this, aVar);
                if (oVarA != null) {
                    return oVarA;
                }
            } else if (pVar2 == pVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public b.c.a.t.a o(Reader reader) {
        b.c.a.t.a aVar = new b.c.a.t.a(reader);
        aVar.L(this.l);
        return aVar;
    }

    public b.c.a.t.b p(Writer writer) throws IOException {
        if (this.j) {
            writer.write(")]}'\n");
        }
        b.c.a.t.b bVar = new b.c.a.t.b(writer);
        if (this.k) {
            bVar.y("  ");
        }
        bVar.A(this.h);
        return bVar;
    }

    public String q(i iVar) {
        StringWriter stringWriter = new StringWriter();
        u(iVar, stringWriter);
        return stringWriter.toString();
    }

    public String r(Object obj) {
        return obj == null ? q(j.f2198a) : s(obj, obj.getClass());
    }

    public String s(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        w(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void t(i iVar, b.c.a.t.b bVar) throws JsonIOException {
        boolean zL = bVar.l();
        bVar.z(true);
        boolean zK = bVar.k();
        bVar.x(this.i);
        boolean zJ = bVar.j();
        bVar.A(this.h);
        try {
            try {
                b.c.a.r.g.b(iVar, bVar);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        } finally {
            bVar.z(zL);
            bVar.x(zK);
            bVar.A(zJ);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.h + "factories:" + this.f2189d + ",instanceCreators:" + this.f2190e + "}";
    }

    public void u(i iVar, Appendable appendable) throws JsonIOException {
        try {
            t(iVar, p(b.c.a.r.g.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public void v(Object obj, Type type, b.c.a.t.b bVar) throws JsonIOException {
        o oVarK = k(b.c.a.s.a.b(type));
        boolean zL = bVar.l();
        bVar.z(true);
        boolean zK = bVar.k();
        bVar.x(this.i);
        boolean zJ = bVar.j();
        bVar.A(this.h);
        try {
            try {
                oVarK.d(bVar, obj);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        } finally {
            bVar.z(zL);
            bVar.x(zK);
            bVar.A(zJ);
        }
    }

    public void w(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            v(obj, type, p(b.c.a.r.g.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public d(b.c.a.r.c cVar, b.c.a.c cVar2, Map<Type, b.c.a.e<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy, List<p> list) {
        this.f2187b = new ThreadLocal<>();
        this.f2188c = new ConcurrentHashMap();
        b.c.a.r.b bVar = new b.c.a.r.b(map);
        this.f2190e = bVar;
        this.f2191f = cVar;
        this.g = cVar2;
        this.h = z;
        this.j = z3;
        this.i = z4;
        this.k = z5;
        this.l = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(b.c.a.r.i.n.Y);
        arrayList.add(b.c.a.r.i.h.f2266a);
        arrayList.add(cVar);
        arrayList.addAll(list);
        arrayList.add(b.c.a.r.i.n.D);
        arrayList.add(b.c.a.r.i.n.m);
        arrayList.add(b.c.a.r.i.n.g);
        arrayList.add(b.c.a.r.i.n.i);
        arrayList.add(b.c.a.r.i.n.k);
        o<Number> oVarN = n(longSerializationPolicy);
        arrayList.add(b.c.a.r.i.n.b(Long.TYPE, Long.class, oVarN));
        arrayList.add(b.c.a.r.i.n.b(Double.TYPE, Double.class, e(z7)));
        arrayList.add(b.c.a.r.i.n.b(Float.TYPE, Float.class, f(z7)));
        arrayList.add(b.c.a.r.i.n.x);
        arrayList.add(b.c.a.r.i.n.o);
        arrayList.add(b.c.a.r.i.n.q);
        arrayList.add(b.c.a.r.i.n.a(AtomicLong.class, b(oVarN)));
        arrayList.add(b.c.a.r.i.n.a(AtomicLongArray.class, c(oVarN)));
        arrayList.add(b.c.a.r.i.n.s);
        arrayList.add(b.c.a.r.i.n.z);
        arrayList.add(b.c.a.r.i.n.F);
        arrayList.add(b.c.a.r.i.n.H);
        arrayList.add(b.c.a.r.i.n.a(BigDecimal.class, b.c.a.r.i.n.B));
        arrayList.add(b.c.a.r.i.n.a(BigInteger.class, b.c.a.r.i.n.C));
        arrayList.add(b.c.a.r.i.n.J);
        arrayList.add(b.c.a.r.i.n.L);
        arrayList.add(b.c.a.r.i.n.P);
        arrayList.add(b.c.a.r.i.n.R);
        arrayList.add(b.c.a.r.i.n.W);
        arrayList.add(b.c.a.r.i.n.N);
        arrayList.add(b.c.a.r.i.n.f2298d);
        arrayList.add(b.c.a.r.i.c.f2256a);
        arrayList.add(b.c.a.r.i.n.U);
        arrayList.add(b.c.a.r.i.k.f2283a);
        arrayList.add(b.c.a.r.i.j.f2281a);
        arrayList.add(b.c.a.r.i.n.S);
        arrayList.add(b.c.a.r.i.a.f2250a);
        arrayList.add(b.c.a.r.i.n.f2296b);
        arrayList.add(new b.c.a.r.i.b(bVar));
        arrayList.add(new b.c.a.r.i.g(bVar, z2));
        b.c.a.r.i.d dVar = new b.c.a.r.i.d(bVar);
        this.m = dVar;
        arrayList.add(dVar);
        arrayList.add(b.c.a.r.i.n.Z);
        arrayList.add(new b.c.a.r.i.i(bVar, cVar2, cVar, dVar));
        this.f2189d = Collections.unmodifiableList(arrayList);
    }
}
