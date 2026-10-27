package c.b.b;

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
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.b.b.s.a<?> f4154a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadLocal<Map<c.b.b.s.a<?>, g<?>>> f4155b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<c.b.b.s.a<?>, o<?>> f4156c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<p> f4157d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c.b.b.r.b f4158e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c.b.b.r.c f4159f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c.b.b.c f4160g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f4161h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f4162i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final c.b.b.r.i.d m;

    /* JADX INFO: compiled from: Gson.java */
    public static class a extends c.b.b.s.a<Object> {
    }

    /* JADX INFO: compiled from: Gson.java */
    public class b extends o<Number> {
        public b() {
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Double b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return Double.valueOf(aVar.b0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.Z();
            } else {
                d.d(number.doubleValue());
                bVar.l0(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public class c extends o<Number> {
        public c() {
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Float b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.b0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.Z();
            } else {
                d.d(number.floatValue());
                bVar.l0(number);
            }
        }
    }

    /* JADX INFO: renamed from: c.b.b.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Gson.java */
    public static class C0094d extends o<Number> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return Long.valueOf(aVar.d0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.Z();
            } else {
                bVar.m0(number.toString());
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class e extends o<AtomicLong> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f4165a;

        public e(o oVar) {
            this.f4165a = oVar;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLong b(c.b.b.t.a aVar) throws IOException {
            return new AtomicLong(((Number) this.f4165a.b(aVar)).longValue());
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, AtomicLong atomicLong) throws IOException {
            this.f4165a.d(bVar, Long.valueOf(atomicLong.get()));
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class f extends o<AtomicLongArray> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f4166a;

        public f(o oVar) {
            this.f4166a = oVar;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLongArray b(c.b.b.t.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.z();
            while (aVar.W()) {
                arrayList.add(Long.valueOf(((Number) this.f4166a.b(aVar)).longValue()));
            }
            aVar.S();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i2 = 0; i2 < size; i2++) {
                atomicLongArray.set(i2, ((Long) arrayList.get(i2)).longValue());
            }
            return atomicLongArray;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, AtomicLongArray atomicLongArray) throws IOException {
            bVar.O();
            int length = atomicLongArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                this.f4166a.d(bVar, Long.valueOf(atomicLongArray.get(i2)));
            }
            bVar.R();
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class g<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o<T> f4167a;

        @Override // c.b.b.o
        public T b(c.b.b.t.a aVar) throws IOException {
            o<T> oVar = this.f4167a;
            if (oVar != null) {
                return oVar.b(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // c.b.b.o
        public void d(c.b.b.t.b bVar, T t) throws IOException {
            o<T> oVar = this.f4167a;
            if (oVar == null) {
                throw new IllegalStateException();
            }
            oVar.d(bVar, t);
        }

        public void e(o<T> oVar) {
            if (this.f4167a != null) {
                throw new AssertionError();
            }
            this.f4167a = oVar;
        }
    }

    public d() {
        this(c.b.b.r.c.f4198b, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    public static void a(Object obj, c.b.b.t.a aVar) {
        if (obj != null) {
            try {
                if (aVar.k0() == JsonToken.END_DOCUMENT) {
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
        return longSerializationPolicy == LongSerializationPolicy.DEFAULT ? c.b.b.r.i.n.t : new C0094d();
    }

    public final o<Number> e(boolean z) {
        return z ? c.b.b.r.i.n.v : new b();
    }

    public final o<Number> f(boolean z) {
        return z ? c.b.b.r.i.n.u : new c();
    }

    public <T> T g(c.b.b.t.a aVar, Type type) throws JsonSyntaxException, JsonIOException {
        boolean zX = aVar.X();
        boolean z = true;
        aVar.p0(true);
        try {
            try {
                try {
                    aVar.k0();
                    z = false;
                    T tB = k(c.b.b.s.a.b(type)).b(aVar);
                    aVar.p0(zX);
                    return tB;
                } catch (EOFException e2) {
                    if (!z) {
                        throw new JsonSyntaxException(e2);
                    }
                    aVar.p0(zX);
                    return null;
                } catch (IllegalStateException e3) {
                    throw new JsonSyntaxException(e3);
                }
            } catch (IOException e4) {
                throw new JsonSyntaxException(e4);
            }
        } catch (Throwable th) {
            aVar.p0(zX);
            throw th;
        }
    }

    public <T> T h(Reader reader, Type type) throws JsonSyntaxException, JsonIOException {
        c.b.b.t.a aVarO = o(reader);
        T t = (T) g(aVarO, type);
        a(t, aVarO);
        return t;
    }

    public <T> T i(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) c.b.b.r.f.c(cls).cast(j(str, cls));
    }

    public <T> T j(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) h(new StringReader(str), type);
    }

    public <T> o<T> k(c.b.b.s.a<T> aVar) {
        o<T> oVar = (o) this.f4156c.get(aVar == null ? f4154a : aVar);
        if (oVar != null) {
            return oVar;
        }
        Map<c.b.b.s.a<?>, g<?>> map = this.f4155b.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f4155b.set(map);
            z = true;
        }
        g<?> gVar = map.get(aVar);
        if (gVar != null) {
            return gVar;
        }
        try {
            g<?> gVar2 = new g<>();
            map.put(aVar, gVar2);
            Iterator<p> it = this.f4157d.iterator();
            while (it.hasNext()) {
                o<T> oVarA = it.next().a(this, aVar);
                if (oVarA != null) {
                    gVar2.e(oVarA);
                    this.f4156c.put(aVar, oVarA);
                    return oVarA;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z) {
                this.f4155b.remove();
            }
        }
    }

    public <T> o<T> l(Class<T> cls) {
        return k(c.b.b.s.a.a(cls));
    }

    public <T> o<T> m(p pVar, c.b.b.s.a<T> aVar) {
        if (!this.f4157d.contains(pVar)) {
            pVar = this.m;
        }
        boolean z = false;
        for (p pVar2 : this.f4157d) {
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

    public c.b.b.t.a o(Reader reader) {
        c.b.b.t.a aVar = new c.b.b.t.a(reader);
        aVar.p0(this.l);
        return aVar;
    }

    public c.b.b.t.b p(Writer writer) throws IOException {
        if (this.j) {
            writer.write(")]}'\n");
        }
        c.b.b.t.b bVar = new c.b.b.t.b(writer);
        if (this.k) {
            bVar.f0("  ");
        }
        bVar.h0(this.f4161h);
        return bVar;
    }

    public String q(i iVar) {
        StringWriter stringWriter = new StringWriter();
        u(iVar, stringWriter);
        return stringWriter.toString();
    }

    public String r(Object obj) {
        return obj == null ? q(j.f4169a) : s(obj, obj.getClass());
    }

    public String s(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        w(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void t(i iVar, c.b.b.t.b bVar) throws JsonIOException {
        boolean zV = bVar.V();
        bVar.g0(true);
        boolean zU = bVar.U();
        bVar.e0(this.f4162i);
        boolean zT = bVar.T();
        bVar.h0(this.f4161h);
        try {
            try {
                c.b.b.r.g.b(iVar, bVar);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        } finally {
            bVar.g0(zV);
            bVar.e0(zU);
            bVar.h0(zT);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.f4161h + "factories:" + this.f4157d + ",instanceCreators:" + this.f4158e + "}";
    }

    public void u(i iVar, Appendable appendable) throws JsonIOException {
        try {
            t(iVar, p(c.b.b.r.g.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public void v(Object obj, Type type, c.b.b.t.b bVar) throws JsonIOException {
        o oVarK = k(c.b.b.s.a.b(type));
        boolean zV = bVar.V();
        bVar.g0(true);
        boolean zU = bVar.U();
        bVar.e0(this.f4162i);
        boolean zT = bVar.T();
        bVar.h0(this.f4161h);
        try {
            try {
                oVarK.d(bVar, obj);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        } finally {
            bVar.g0(zV);
            bVar.e0(zU);
            bVar.h0(zT);
        }
    }

    public void w(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            v(obj, type, p(c.b.b.r.g.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public d(c.b.b.r.c cVar, c.b.b.c cVar2, Map<Type, c.b.b.e<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy, List<p> list) {
        this.f4155b = new ThreadLocal<>();
        this.f4156c = new ConcurrentHashMap();
        c.b.b.r.b bVar = new c.b.b.r.b(map);
        this.f4158e = bVar;
        this.f4159f = cVar;
        this.f4160g = cVar2;
        this.f4161h = z;
        this.j = z3;
        this.f4162i = z4;
        this.k = z5;
        this.l = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(c.b.b.r.i.n.Y);
        arrayList.add(c.b.b.r.i.h.f4238a);
        arrayList.add(cVar);
        arrayList.addAll(list);
        arrayList.add(c.b.b.r.i.n.D);
        arrayList.add(c.b.b.r.i.n.m);
        arrayList.add(c.b.b.r.i.n.f4277g);
        arrayList.add(c.b.b.r.i.n.f4279i);
        arrayList.add(c.b.b.r.i.n.k);
        o<Number> oVarN = n(longSerializationPolicy);
        arrayList.add(c.b.b.r.i.n.b(Long.TYPE, Long.class, oVarN));
        arrayList.add(c.b.b.r.i.n.b(Double.TYPE, Double.class, e(z7)));
        arrayList.add(c.b.b.r.i.n.b(Float.TYPE, Float.class, f(z7)));
        arrayList.add(c.b.b.r.i.n.x);
        arrayList.add(c.b.b.r.i.n.o);
        arrayList.add(c.b.b.r.i.n.q);
        arrayList.add(c.b.b.r.i.n.a(AtomicLong.class, b(oVarN)));
        arrayList.add(c.b.b.r.i.n.a(AtomicLongArray.class, c(oVarN)));
        arrayList.add(c.b.b.r.i.n.s);
        arrayList.add(c.b.b.r.i.n.z);
        arrayList.add(c.b.b.r.i.n.F);
        arrayList.add(c.b.b.r.i.n.H);
        arrayList.add(c.b.b.r.i.n.a(BigDecimal.class, c.b.b.r.i.n.B));
        arrayList.add(c.b.b.r.i.n.a(BigInteger.class, c.b.b.r.i.n.C));
        arrayList.add(c.b.b.r.i.n.J);
        arrayList.add(c.b.b.r.i.n.L);
        arrayList.add(c.b.b.r.i.n.P);
        arrayList.add(c.b.b.r.i.n.R);
        arrayList.add(c.b.b.r.i.n.W);
        arrayList.add(c.b.b.r.i.n.N);
        arrayList.add(c.b.b.r.i.n.f4274d);
        arrayList.add(c.b.b.r.i.c.f4228a);
        arrayList.add(c.b.b.r.i.n.U);
        arrayList.add(c.b.b.r.i.k.f4258a);
        arrayList.add(c.b.b.r.i.j.f4256a);
        arrayList.add(c.b.b.r.i.n.S);
        arrayList.add(c.b.b.r.i.a.f4222a);
        arrayList.add(c.b.b.r.i.n.f4272b);
        arrayList.add(new c.b.b.r.i.b(bVar));
        arrayList.add(new c.b.b.r.i.g(bVar, z2));
        c.b.b.r.i.d dVar = new c.b.b.r.i.d(bVar);
        this.m = dVar;
        arrayList.add(dVar);
        arrayList.add(c.b.b.r.i.n.Z);
        arrayList.add(new c.b.b.r.i.i(bVar, cVar2, cVar, dVar));
        this.f4157d = Collections.unmodifiableList(arrayList);
    }
}
