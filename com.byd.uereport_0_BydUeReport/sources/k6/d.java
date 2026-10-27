package k6;

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
    public static final p6.a<?> f5903a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadLocal<Map<p6.a<?>, g<?>>> f5904b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<p6.a<?>, o<?>> f5905c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<p> f5906d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m6.b f5907e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m6.c f5908f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final k6.c f5909g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f5910h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f5911i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f5912j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f5913k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f5914l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final n6.d f5915m;

    /* JADX INFO: compiled from: Gson.java */
    public static class a extends p6.a<Object> {
    }

    /* JADX INFO: compiled from: Gson.java */
    public class b extends o<Number> {
        public b() {
        }

        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Double b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return Double.valueOf(aVar.C());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.B();
            } else {
                d.d(number.doubleValue());
                bVar.V(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public class c extends o<Number> {
        public c() {
        }

        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Float b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.C());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.B();
            } else {
                d.d(number.floatValue());
                bVar.V(number);
            }
        }
    }

    /* JADX INFO: renamed from: k6.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Gson.java */
    public static class C0075d extends o<Number> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return Long.valueOf(aVar.F());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.B();
            } else {
                bVar.X(number.toString());
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class e extends o<AtomicLong> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f5918a;

        public e(o oVar) {
            this.f5918a = oVar;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLong b(q6.a aVar) throws IOException {
            return new AtomicLong(((Number) this.f5918a.b(aVar)).longValue());
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, AtomicLong atomicLong) throws IOException {
            this.f5918a.d(bVar, Long.valueOf(atomicLong.get()));
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class f extends o<AtomicLongArray> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f5919a;

        public f(o oVar) {
            this.f5919a = oVar;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLongArray b(q6.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.g();
            while (aVar.t()) {
                arrayList.add(Long.valueOf(((Number) this.f5919a.b(aVar)).longValue()));
            }
            aVar.o();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i8 = 0; i8 < size; i8++) {
                atomicLongArray.set(i8, ((Long) arrayList.get(i8)).longValue());
            }
            return atomicLongArray;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, AtomicLongArray atomicLongArray) throws IOException {
            bVar.i();
            int length = atomicLongArray.length();
            for (int i8 = 0; i8 < length; i8++) {
                this.f5919a.d(bVar, Long.valueOf(atomicLongArray.get(i8)));
            }
            bVar.n();
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class g<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o<T> f5920a;

        @Override // k6.o
        public T b(q6.a aVar) throws IOException {
            o<T> oVar = this.f5920a;
            if (oVar != null) {
                return oVar.b(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // k6.o
        public void d(q6.b bVar, T t7) throws IOException {
            o<T> oVar = this.f5920a;
            if (oVar == null) {
                throw new IllegalStateException();
            }
            oVar.d(bVar, t7);
        }

        public void e(o<T> oVar) {
            if (this.f5920a != null) {
                throw new AssertionError();
            }
            this.f5920a = oVar;
        }
    }

    public d() {
        this(m6.c.f6409d, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    public static void a(Object obj, q6.a aVar) {
        if (obj != null) {
            try {
                if (aVar.R() == JsonToken.END_DOCUMENT) {
                } else {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e8) {
                throw new JsonSyntaxException(e8);
            } catch (IOException e9) {
                throw new JsonIOException(e9);
            }
        }
    }

    public static o<AtomicLong> b(o<Number> oVar) {
        return new e(oVar).a();
    }

    public static o<AtomicLongArray> c(o<Number> oVar) {
        return new f(oVar).a();
    }

    public static void d(double d8) {
        if (Double.isNaN(d8) || Double.isInfinite(d8)) {
            throw new IllegalArgumentException(d8 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public static o<Number> n(LongSerializationPolicy longSerializationPolicy) {
        return longSerializationPolicy == LongSerializationPolicy.DEFAULT ? n6.n.f6632t : new C0075d();
    }

    public final o<Number> e(boolean z7) {
        return z7 ? n6.n.f6634v : new b();
    }

    public final o<Number> f(boolean z7) {
        return z7 ? n6.n.f6633u : new c();
    }

    public <T> T g(q6.a aVar, Type type) throws JsonSyntaxException, JsonIOException {
        boolean zV = aVar.v();
        boolean z7 = true;
        aVar.Z(true);
        try {
            try {
                try {
                    aVar.R();
                    z7 = false;
                    T tB = k(p6.a.b(type)).b(aVar);
                    aVar.Z(zV);
                    return tB;
                } catch (EOFException e8) {
                    if (!z7) {
                        throw new JsonSyntaxException(e8);
                    }
                    aVar.Z(zV);
                    return null;
                } catch (IllegalStateException e9) {
                    throw new JsonSyntaxException(e9);
                }
            } catch (IOException e10) {
                throw new JsonSyntaxException(e10);
            }
        } catch (Throwable th) {
            aVar.Z(zV);
            throw th;
        }
    }

    public <T> T h(Reader reader, Type type) throws JsonSyntaxException, JsonIOException {
        q6.a aVarO = o(reader);
        T t7 = (T) g(aVarO, type);
        a(t7, aVarO);
        return t7;
    }

    public <T> T i(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) m6.f.c(cls).cast(j(str, cls));
    }

    public <T> T j(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) h(new StringReader(str), type);
    }

    public <T> o<T> k(p6.a<T> aVar) {
        o<T> oVar = (o) this.f5905c.get(aVar == null ? f5903a : aVar);
        if (oVar != null) {
            return oVar;
        }
        Map<p6.a<?>, g<?>> map = this.f5904b.get();
        boolean z7 = false;
        if (map == null) {
            map = new HashMap<>();
            this.f5904b.set(map);
            z7 = true;
        }
        g<?> gVar = map.get(aVar);
        if (gVar != null) {
            return gVar;
        }
        try {
            g<?> gVar2 = new g<>();
            map.put(aVar, gVar2);
            Iterator<p> it = this.f5906d.iterator();
            while (it.hasNext()) {
                o<T> oVarA = it.next().a(this, aVar);
                if (oVarA != null) {
                    gVar2.e(oVarA);
                    this.f5905c.put(aVar, oVarA);
                    return oVarA;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z7) {
                this.f5904b.remove();
            }
        }
    }

    public <T> o<T> l(Class<T> cls) {
        return k(p6.a.a(cls));
    }

    public <T> o<T> m(p pVar, p6.a<T> aVar) {
        if (!this.f5906d.contains(pVar)) {
            pVar = this.f5915m;
        }
        boolean z7 = false;
        for (p pVar2 : this.f5906d) {
            if (z7) {
                o<T> oVarA = pVar2.a(this, aVar);
                if (oVarA != null) {
                    return oVarA;
                }
            } else if (pVar2 == pVar) {
                z7 = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public q6.a o(Reader reader) {
        q6.a aVar = new q6.a(reader);
        aVar.Z(this.f5914l);
        return aVar;
    }

    public q6.b p(Writer writer) throws IOException {
        if (this.f5912j) {
            writer.write(")]}'\n");
        }
        q6.b bVar = new q6.b(writer);
        if (this.f5913k) {
            bVar.M("  ");
        }
        bVar.O(this.f5910h);
        return bVar;
    }

    public String q(i iVar) {
        StringWriter stringWriter = new StringWriter();
        u(iVar, stringWriter);
        return stringWriter.toString();
    }

    public String r(Object obj) {
        return obj == null ? q(j.f5922a) : s(obj, obj.getClass());
    }

    public String s(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        w(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void t(i iVar, q6.b bVar) throws JsonIOException {
        boolean zT = bVar.t();
        bVar.N(true);
        boolean zR = bVar.r();
        bVar.L(this.f5911i);
        boolean zQ = bVar.q();
        bVar.O(this.f5910h);
        try {
            try {
                m6.g.b(iVar, bVar);
            } catch (IOException e8) {
                throw new JsonIOException(e8);
            }
        } finally {
            bVar.N(zT);
            bVar.L(zR);
            bVar.O(zQ);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.f5910h + "factories:" + this.f5906d + ",instanceCreators:" + this.f5907e + "}";
    }

    public void u(i iVar, Appendable appendable) throws JsonIOException {
        try {
            t(iVar, p(m6.g.c(appendable)));
        } catch (IOException e8) {
            throw new JsonIOException(e8);
        }
    }

    public void v(Object obj, Type type, q6.b bVar) throws JsonIOException {
        o oVarK = k(p6.a.b(type));
        boolean zT = bVar.t();
        bVar.N(true);
        boolean zR = bVar.r();
        bVar.L(this.f5911i);
        boolean zQ = bVar.q();
        bVar.O(this.f5910h);
        try {
            try {
                oVarK.d(bVar, obj);
            } catch (IOException e8) {
                throw new JsonIOException(e8);
            }
        } finally {
            bVar.N(zT);
            bVar.L(zR);
            bVar.O(zQ);
        }
    }

    public void w(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            v(obj, type, p(m6.g.c(appendable)));
        } catch (IOException e8) {
            throw new JsonIOException(e8);
        }
    }

    public d(m6.c cVar, k6.c cVar2, Map<Type, k6.e<?>> map, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, LongSerializationPolicy longSerializationPolicy, List<p> list) {
        this.f5904b = new ThreadLocal<>();
        this.f5905c = new ConcurrentHashMap();
        m6.b bVar = new m6.b(map);
        this.f5907e = bVar;
        this.f5908f = cVar;
        this.f5909g = cVar2;
        this.f5910h = z7;
        this.f5912j = z9;
        this.f5911i = z10;
        this.f5913k = z11;
        this.f5914l = z12;
        ArrayList arrayList = new ArrayList();
        arrayList.add(n6.n.Y);
        arrayList.add(n6.h.f6579a);
        arrayList.add(cVar);
        arrayList.addAll(list);
        arrayList.add(n6.n.D);
        arrayList.add(n6.n.f6625m);
        arrayList.add(n6.n.f6619g);
        arrayList.add(n6.n.f6621i);
        arrayList.add(n6.n.f6623k);
        o<Number> oVarN = n(longSerializationPolicy);
        arrayList.add(n6.n.b(Long.TYPE, Long.class, oVarN));
        arrayList.add(n6.n.b(Double.TYPE, Double.class, e(z13)));
        arrayList.add(n6.n.b(Float.TYPE, Float.class, f(z13)));
        arrayList.add(n6.n.f6636x);
        arrayList.add(n6.n.f6627o);
        arrayList.add(n6.n.f6629q);
        arrayList.add(n6.n.a(AtomicLong.class, b(oVarN)));
        arrayList.add(n6.n.a(AtomicLongArray.class, c(oVarN)));
        arrayList.add(n6.n.f6631s);
        arrayList.add(n6.n.f6638z);
        arrayList.add(n6.n.F);
        arrayList.add(n6.n.H);
        arrayList.add(n6.n.a(BigDecimal.class, n6.n.B));
        arrayList.add(n6.n.a(BigInteger.class, n6.n.C));
        arrayList.add(n6.n.J);
        arrayList.add(n6.n.L);
        arrayList.add(n6.n.P);
        arrayList.add(n6.n.R);
        arrayList.add(n6.n.W);
        arrayList.add(n6.n.N);
        arrayList.add(n6.n.f6616d);
        arrayList.add(n6.c.f6558a);
        arrayList.add(n6.n.U);
        arrayList.add(n6.k.f6600a);
        arrayList.add(n6.j.f6598a);
        arrayList.add(n6.n.S);
        arrayList.add(n6.a.f6552a);
        arrayList.add(n6.n.f6614b);
        arrayList.add(new n6.b(bVar));
        arrayList.add(new n6.g(bVar, z8));
        n6.d dVar = new n6.d(bVar);
        this.f5915m = dVar;
        arrayList.add(dVar);
        arrayList.add(n6.n.Z);
        arrayList.add(new n6.i(bVar, cVar2, cVar, dVar));
        this.f5906d = Collections.unmodifiableList(arrayList);
    }
}
