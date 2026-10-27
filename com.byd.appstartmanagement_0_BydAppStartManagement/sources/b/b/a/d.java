package b.b.a;

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
/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.b.a.s.a<?> f377a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadLocal<Map<b.b.a.s.a<?>, g<?>>> f378b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<b.b.a.s.a<?>, o<?>> f379c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<p> f380d;
    public final b.b.a.r.b e;
    public final b.b.a.r.c f;
    public final b.b.a.c g;
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final b.b.a.r.i.d m;

    /* JADX INFO: compiled from: Gson.java */
    public static class a extends b.b.a.s.a<Object> {
    }

    /* JADX INFO: compiled from: Gson.java */
    public class b extends o<Number> {
        public b() {
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Double b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return Double.valueOf(aVar.p());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.n();
            } else {
                d.d(number.doubleValue());
                bVar.z(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public class c extends o<Number> {
        public c() {
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Float b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.p());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.n();
            } else {
                d.d(number.floatValue());
                bVar.z(number);
            }
        }
    }

    /* JADX INFO: renamed from: b.b.a.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Gson.java */
    public static class C0027d extends o<Number> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return Long.valueOf(aVar.r());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.n();
            } else {
                bVar.A(number.toString());
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class e extends o<AtomicLong> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f383a;

        public e(o oVar) {
            this.f383a = oVar;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLong b(b.b.a.t.a aVar) throws IOException {
            return new AtomicLong(((Number) this.f383a.b(aVar)).longValue());
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, AtomicLong atomicLong) throws IOException {
            this.f383a.d(bVar, Long.valueOf(atomicLong.get()));
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class f extends o<AtomicLongArray> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f384a;

        public f(o oVar) {
            this.f384a = oVar;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLongArray b(b.b.a.t.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.b();
            while (aVar.k()) {
                arrayList.add(Long.valueOf(((Number) this.f384a.b(aVar)).longValue()));
            }
            aVar.g();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i = 0; i < size; i++) {
                atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
            }
            return atomicLongArray;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, AtomicLongArray atomicLongArray) throws IOException {
            bVar.c();
            int length = atomicLongArray.length();
            for (int i = 0; i < length; i++) {
                this.f384a.d(bVar, Long.valueOf(atomicLongArray.get(i)));
            }
            bVar.f();
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class g<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o<T> f385a;

        @Override // b.b.a.o
        public T b(b.b.a.t.a aVar) throws IOException {
            o<T> oVar = this.f385a;
            if (oVar != null) {
                return oVar.b(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // b.b.a.o
        public void d(b.b.a.t.b bVar, T t) throws IOException {
            o<T> oVar = this.f385a;
            if (oVar == null) {
                throw new IllegalStateException();
            }
            oVar.d(bVar, t);
        }

        public void e(o<T> oVar) {
            if (this.f385a != null) {
                throw new AssertionError();
            }
            this.f385a = oVar;
        }
    }

    public d() {
        this(b.b.a.r.c.f416b, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    public static void a(Object obj, b.b.a.t.a aVar) {
        if (obj != null) {
            try {
                if (aVar.y() == JsonToken.END_DOCUMENT) {
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
        return longSerializationPolicy == LongSerializationPolicy.DEFAULT ? b.b.a.r.i.n.t : new C0027d();
    }

    public final o<Number> e(boolean z) {
        return z ? b.b.a.r.i.n.v : new b();
    }

    public final o<Number> f(boolean z) {
        return z ? b.b.a.r.i.n.u : new c();
    }

    public <T> T g(b.b.a.t.a aVar, Type type) throws JsonSyntaxException, JsonIOException {
        boolean zL = aVar.l();
        boolean z = true;
        aVar.D(true);
        try {
            try {
                try {
                    aVar.y();
                    z = false;
                    T tB = k(b.b.a.s.a.b(type)).b(aVar);
                    aVar.D(zL);
                    return tB;
                } catch (EOFException e2) {
                    if (!z) {
                        throw new JsonSyntaxException(e2);
                    }
                    aVar.D(zL);
                    return null;
                } catch (IllegalStateException e3) {
                    throw new JsonSyntaxException(e3);
                }
            } catch (IOException e4) {
                throw new JsonSyntaxException(e4);
            }
        } catch (Throwable th) {
            aVar.D(zL);
            throw th;
        }
    }

    public <T> T h(Reader reader, Type type) throws JsonSyntaxException, JsonIOException {
        b.b.a.t.a aVarO = o(reader);
        T t = (T) g(aVarO, type);
        a(t, aVarO);
        return t;
    }

    public <T> T i(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) b.b.a.r.f.c(cls).cast(j(str, cls));
    }

    public <T> T j(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) h(new StringReader(str), type);
    }

    public <T> o<T> k(b.b.a.s.a<T> aVar) {
        o<T> oVar = (o) this.f379c.get(aVar == null ? f377a : aVar);
        if (oVar != null) {
            return oVar;
        }
        Map<b.b.a.s.a<?>, g<?>> map = this.f378b.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f378b.set(map);
            z = true;
        }
        g<?> gVar = map.get(aVar);
        if (gVar != null) {
            return gVar;
        }
        try {
            g<?> gVar2 = new g<>();
            map.put(aVar, gVar2);
            Iterator<p> it = this.f380d.iterator();
            while (it.hasNext()) {
                o<T> oVarA = it.next().a(this, aVar);
                if (oVarA != null) {
                    gVar2.e(oVarA);
                    this.f379c.put(aVar, oVarA);
                    return oVarA;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z) {
                this.f378b.remove();
            }
        }
    }

    public <T> o<T> l(Class<T> cls) {
        return k(b.b.a.s.a.a(cls));
    }

    public <T> o<T> m(p pVar, b.b.a.s.a<T> aVar) {
        if (!this.f380d.contains(pVar)) {
            pVar = this.m;
        }
        boolean z = false;
        for (p pVar2 : this.f380d) {
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

    public b.b.a.t.a o(Reader reader) {
        b.b.a.t.a aVar = new b.b.a.t.a(reader);
        aVar.D(this.l);
        return aVar;
    }

    public b.b.a.t.b p(Writer writer) throws IOException {
        if (this.j) {
            writer.write(")]}'\n");
        }
        b.b.a.t.b bVar = new b.b.a.t.b(writer);
        if (this.k) {
            bVar.t("  ");
        }
        bVar.v(this.h);
        return bVar;
    }

    public String q(i iVar) {
        StringWriter stringWriter = new StringWriter();
        u(iVar, stringWriter);
        return stringWriter.toString();
    }

    public String r(Object obj) {
        return obj == null ? q(j.f387a) : s(obj, obj.getClass());
    }

    public String s(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        w(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void t(i iVar, b.b.a.t.b bVar) throws JsonIOException {
        boolean zJ = bVar.j();
        bVar.u(true);
        boolean zI = bVar.i();
        bVar.s(this.i);
        boolean zH = bVar.h();
        bVar.v(this.h);
        try {
            try {
                b.b.a.r.g.b(iVar, bVar);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        } finally {
            bVar.u(zJ);
            bVar.s(zI);
            bVar.v(zH);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.h + "factories:" + this.f380d + ",instanceCreators:" + this.e + "}";
    }

    public void u(i iVar, Appendable appendable) throws JsonIOException {
        try {
            t(iVar, p(b.b.a.r.g.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public void v(Object obj, Type type, b.b.a.t.b bVar) throws JsonIOException {
        o oVarK = k(b.b.a.s.a.b(type));
        boolean zJ = bVar.j();
        bVar.u(true);
        boolean zI = bVar.i();
        bVar.s(this.i);
        boolean zH = bVar.h();
        bVar.v(this.h);
        try {
            try {
                oVarK.d(bVar, obj);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        } finally {
            bVar.u(zJ);
            bVar.s(zI);
            bVar.v(zH);
        }
    }

    public void w(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            v(obj, type, p(b.b.a.r.g.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public d(b.b.a.r.c cVar, b.b.a.c cVar2, Map<Type, b.b.a.e<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy, List<p> list) {
        this.f378b = new ThreadLocal<>();
        this.f379c = new ConcurrentHashMap();
        b.b.a.r.b bVar = new b.b.a.r.b(map);
        this.e = bVar;
        this.f = cVar;
        this.g = cVar2;
        this.h = z;
        this.j = z3;
        this.i = z4;
        this.k = z5;
        this.l = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(b.b.a.r.i.n.Y);
        arrayList.add(b.b.a.r.i.h.f450a);
        arrayList.add(cVar);
        arrayList.addAll(list);
        arrayList.add(b.b.a.r.i.n.D);
        arrayList.add(b.b.a.r.i.n.m);
        arrayList.add(b.b.a.r.i.n.g);
        arrayList.add(b.b.a.r.i.n.i);
        arrayList.add(b.b.a.r.i.n.k);
        o<Number> oVarN = n(longSerializationPolicy);
        arrayList.add(b.b.a.r.i.n.b(Long.TYPE, Long.class, oVarN));
        arrayList.add(b.b.a.r.i.n.b(Double.TYPE, Double.class, e(z7)));
        arrayList.add(b.b.a.r.i.n.b(Float.TYPE, Float.class, f(z7)));
        arrayList.add(b.b.a.r.i.n.x);
        arrayList.add(b.b.a.r.i.n.o);
        arrayList.add(b.b.a.r.i.n.q);
        arrayList.add(b.b.a.r.i.n.a(AtomicLong.class, b(oVarN)));
        arrayList.add(b.b.a.r.i.n.a(AtomicLongArray.class, c(oVarN)));
        arrayList.add(b.b.a.r.i.n.s);
        arrayList.add(b.b.a.r.i.n.z);
        arrayList.add(b.b.a.r.i.n.F);
        arrayList.add(b.b.a.r.i.n.H);
        arrayList.add(b.b.a.r.i.n.a(BigDecimal.class, b.b.a.r.i.n.B));
        arrayList.add(b.b.a.r.i.n.a(BigInteger.class, b.b.a.r.i.n.C));
        arrayList.add(b.b.a.r.i.n.J);
        arrayList.add(b.b.a.r.i.n.L);
        arrayList.add(b.b.a.r.i.n.P);
        arrayList.add(b.b.a.r.i.n.R);
        arrayList.add(b.b.a.r.i.n.W);
        arrayList.add(b.b.a.r.i.n.N);
        arrayList.add(b.b.a.r.i.n.f477d);
        arrayList.add(b.b.a.r.i.c.f440a);
        arrayList.add(b.b.a.r.i.n.U);
        arrayList.add(b.b.a.r.i.k.f464a);
        arrayList.add(b.b.a.r.i.j.f462a);
        arrayList.add(b.b.a.r.i.n.S);
        arrayList.add(b.b.a.r.i.a.f434a);
        arrayList.add(b.b.a.r.i.n.f475b);
        arrayList.add(new b.b.a.r.i.b(bVar));
        arrayList.add(new b.b.a.r.i.g(bVar, z2));
        b.b.a.r.i.d dVar = new b.b.a.r.i.d(bVar);
        this.m = dVar;
        arrayList.add(dVar);
        arrayList.add(b.b.a.r.i.n.Z);
        arrayList.add(new b.b.a.r.i.i(bVar, cVar2, cVar, dVar));
        this.f380d = Collections.unmodifiableList(arrayList);
    }
}
