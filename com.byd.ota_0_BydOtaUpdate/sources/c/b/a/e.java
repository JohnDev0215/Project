package c.b.a;

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
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.b.a.u.a<?> f2887a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ThreadLocal<Map<c.b.a.u.a<?>, g<?>>> f2888b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<c.b.a.u.a<?>, q<?>> f2889c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<r> f2890d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c.b.a.t.b f2891e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c.b.a.t.c f2892f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c.b.a.d f2893g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f2894h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f2895i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final c.b.a.t.i.d m;

    /* JADX INFO: compiled from: Gson.java */
    public static class a extends c.b.a.u.a<Object> {
    }

    /* JADX INFO: compiled from: Gson.java */
    public class b extends q<Number> {
        public b() {
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Double b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return Double.valueOf(aVar.S());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.P();
            } else {
                e.d(number.doubleValue());
                bVar.b0(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public class c extends q<Number> {
        public c() {
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Float b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.S());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.P();
            } else {
                e.d(number.floatValue());
                bVar.b0(number);
            }
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class d extends q<Number> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return Long.valueOf(aVar.U());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            if (number == null) {
                bVar.P();
            } else {
                bVar.c0(number.toString());
            }
        }
    }

    /* JADX INFO: renamed from: c.b.a.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Gson.java */
    public static class C0066e extends q<AtomicLong> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ q f2898a;

        public C0066e(q qVar) {
            this.f2898a = qVar;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLong b(c.b.a.v.a aVar) throws IOException {
            return new AtomicLong(((Number) this.f2898a.b(aVar)).longValue());
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, AtomicLong atomicLong) throws IOException {
            this.f2898a.d(bVar, Long.valueOf(atomicLong.get()));
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class f extends q<AtomicLongArray> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ q f2899a;

        public f(q qVar) {
            this.f2899a = qVar;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLongArray b(c.b.a.v.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.d();
            while (aVar.N()) {
                arrayList.add(Long.valueOf(((Number) this.f2899a.b(aVar)).longValue()));
            }
            aVar.J();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i2 = 0; i2 < size; i2++) {
                atomicLongArray.set(i2, ((Long) arrayList.get(i2)).longValue());
            }
            return atomicLongArray;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, AtomicLongArray atomicLongArray) throws IOException {
            bVar.s();
            int length = atomicLongArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                this.f2899a.d(bVar, Long.valueOf(atomicLongArray.get(i2)));
            }
            bVar.I();
        }
    }

    /* JADX INFO: compiled from: Gson.java */
    public static class g<T> extends q<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public q<T> f2900a;

        @Override // c.b.a.q
        public T b(c.b.a.v.a aVar) throws IOException {
            q<T> qVar = this.f2900a;
            if (qVar != null) {
                return qVar.b(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // c.b.a.q
        public void d(c.b.a.v.b bVar, T t) throws IOException {
            q<T> qVar = this.f2900a;
            if (qVar == null) {
                throw new IllegalStateException();
            }
            qVar.d(bVar, t);
        }

        public void e(q<T> qVar) {
            if (this.f2900a != null) {
                throw new AssertionError();
            }
            this.f2900a = qVar;
        }
    }

    public e(c.b.a.t.c cVar, c.b.a.d dVar, Map<Type, c.b.a.g<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy, List<r> list) {
        c.b.a.t.b bVar = new c.b.a.t.b(map);
        this.f2891e = bVar;
        this.f2892f = cVar;
        this.f2893g = dVar;
        this.f2894h = z;
        this.j = z3;
        this.f2895i = z4;
        this.k = z5;
        this.l = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(c.b.a.t.i.n.Y);
        arrayList.add(c.b.a.t.i.h.f2975a);
        arrayList.add(cVar);
        arrayList.addAll(list);
        arrayList.add(c.b.a.t.i.n.D);
        arrayList.add(c.b.a.t.i.n.m);
        arrayList.add(c.b.a.t.i.n.f3014g);
        arrayList.add(c.b.a.t.i.n.f3016i);
        arrayList.add(c.b.a.t.i.n.k);
        q<Number> qVarP = p(longSerializationPolicy);
        arrayList.add(c.b.a.t.i.n.b(Long.TYPE, Long.class, qVarP));
        arrayList.add(c.b.a.t.i.n.b(Double.TYPE, Double.class, e(z7)));
        arrayList.add(c.b.a.t.i.n.b(Float.TYPE, Float.class, f(z7)));
        arrayList.add(c.b.a.t.i.n.x);
        arrayList.add(c.b.a.t.i.n.o);
        arrayList.add(c.b.a.t.i.n.q);
        arrayList.add(c.b.a.t.i.n.a(AtomicLong.class, b(qVarP)));
        arrayList.add(c.b.a.t.i.n.a(AtomicLongArray.class, c(qVarP)));
        arrayList.add(c.b.a.t.i.n.s);
        arrayList.add(c.b.a.t.i.n.z);
        arrayList.add(c.b.a.t.i.n.F);
        arrayList.add(c.b.a.t.i.n.H);
        arrayList.add(c.b.a.t.i.n.a(BigDecimal.class, c.b.a.t.i.n.B));
        arrayList.add(c.b.a.t.i.n.a(BigInteger.class, c.b.a.t.i.n.C));
        arrayList.add(c.b.a.t.i.n.J);
        arrayList.add(c.b.a.t.i.n.L);
        arrayList.add(c.b.a.t.i.n.P);
        arrayList.add(c.b.a.t.i.n.R);
        arrayList.add(c.b.a.t.i.n.W);
        arrayList.add(c.b.a.t.i.n.N);
        arrayList.add(c.b.a.t.i.n.f3011d);
        arrayList.add(c.b.a.t.i.c.f2965a);
        arrayList.add(c.b.a.t.i.n.U);
        arrayList.add(c.b.a.t.i.k.f2993a);
        arrayList.add(c.b.a.t.i.j.f2991a);
        arrayList.add(c.b.a.t.i.n.S);
        arrayList.add(c.b.a.t.i.a.f2959a);
        arrayList.add(c.b.a.t.i.n.f3009b);
        arrayList.add(new c.b.a.t.i.b(bVar));
        arrayList.add(new c.b.a.t.i.g(bVar, z2));
        c.b.a.t.i.d dVar2 = new c.b.a.t.i.d(bVar);
        this.m = dVar2;
        arrayList.add(dVar2);
        arrayList.add(c.b.a.t.i.n.Z);
        arrayList.add(new c.b.a.t.i.i(bVar, dVar, cVar, dVar2));
        this.f2890d = Collections.unmodifiableList(arrayList);
    }

    public static void a(Object obj, c.b.a.v.a aVar) {
        if (obj != null) {
            try {
                if (aVar.b0() == JsonToken.END_DOCUMENT) {
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

    public static q<AtomicLong> b(q<Number> qVar) {
        return new C0066e(qVar).a();
    }

    public static q<AtomicLongArray> c(q<Number> qVar) {
        return new f(qVar).a();
    }

    public static void d(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public static q<Number> p(LongSerializationPolicy longSerializationPolicy) {
        return longSerializationPolicy == LongSerializationPolicy.DEFAULT ? c.b.a.t.i.n.t : new d();
    }

    public final q<Number> e(boolean z) {
        return z ? c.b.a.t.i.n.v : new b();
    }

    public final q<Number> f(boolean z) {
        return z ? c.b.a.t.i.n.u : new c();
    }

    public <T> T g(k kVar, Class<T> cls) throws JsonSyntaxException {
        return (T) c.b.a.t.f.c(cls).cast(h(kVar, cls));
    }

    public <T> T h(k kVar, Type type) throws JsonSyntaxException {
        if (kVar == null) {
            return null;
        }
        return (T) i(new c.b.a.t.i.e(kVar), type);
    }

    public <T> T i(c.b.a.v.a aVar, Type type) throws JsonSyntaxException, JsonIOException {
        boolean zO = aVar.O();
        boolean z = true;
        aVar.g0(true);
        try {
            try {
                try {
                    aVar.b0();
                    z = false;
                    T tB = m(c.b.a.u.a.b(type)).b(aVar);
                    aVar.g0(zO);
                    return tB;
                } catch (IOException e2) {
                    throw new JsonSyntaxException(e2);
                }
            } catch (EOFException e3) {
                if (!z) {
                    throw new JsonSyntaxException(e3);
                }
                aVar.g0(zO);
                return null;
            } catch (IllegalStateException e4) {
                throw new JsonSyntaxException(e4);
            }
        } catch (Throwable th) {
            aVar.g0(zO);
            throw th;
        }
    }

    public <T> T j(Reader reader, Type type) throws JsonSyntaxException, JsonIOException {
        c.b.a.v.a aVarQ = q(reader);
        T t = (T) i(aVarQ, type);
        a(t, aVarQ);
        return t;
    }

    public <T> T k(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) c.b.a.t.f.c(cls).cast(l(str, cls));
    }

    public <T> T l(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) j(new StringReader(str), type);
    }

    public <T> q<T> m(c.b.a.u.a<T> aVar) {
        q<T> qVar = (q) this.f2889c.get(aVar == null ? f2887a : aVar);
        if (qVar != null) {
            return qVar;
        }
        Map<c.b.a.u.a<?>, g<?>> map = this.f2888b.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f2888b.set(map);
            z = true;
        }
        g<?> gVar = map.get(aVar);
        if (gVar != null) {
            return gVar;
        }
        try {
            g<?> gVar2 = new g<>();
            map.put(aVar, gVar2);
            Iterator<r> it = this.f2890d.iterator();
            while (it.hasNext()) {
                q<T> qVarA = it.next().a(this, aVar);
                if (qVarA != null) {
                    gVar2.e(qVarA);
                    this.f2889c.put(aVar, qVarA);
                    return qVarA;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z) {
                this.f2888b.remove();
            }
        }
    }

    public <T> q<T> n(Class<T> cls) {
        return m(c.b.a.u.a.a(cls));
    }

    public <T> q<T> o(r rVar, c.b.a.u.a<T> aVar) {
        if (!this.f2890d.contains(rVar)) {
            rVar = this.m;
        }
        boolean z = false;
        for (r rVar2 : this.f2890d) {
            if (z) {
                q<T> qVarA = rVar2.a(this, aVar);
                if (qVarA != null) {
                    return qVarA;
                }
            } else if (rVar2 == rVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public c.b.a.v.a q(Reader reader) {
        c.b.a.v.a aVar = new c.b.a.v.a(reader);
        aVar.g0(this.l);
        return aVar;
    }

    public c.b.a.v.b r(Writer writer) throws IOException {
        if (this.j) {
            writer.write(")]}'\n");
        }
        c.b.a.v.b bVar = new c.b.a.v.b(writer);
        if (this.k) {
            bVar.V("  ");
        }
        bVar.X(this.f2894h);
        return bVar;
    }

    public String s(k kVar) {
        StringWriter stringWriter = new StringWriter();
        w(kVar, stringWriter);
        return stringWriter.toString();
    }

    public String t(Object obj) {
        return obj == null ? s(l.f2911a) : u(obj, obj.getClass());
    }

    public String toString() {
        return "{serializeNulls:" + this.f2894h + "factories:" + this.f2890d + ",instanceCreators:" + this.f2891e + "}";
    }

    public String u(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        y(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void v(k kVar, c.b.a.v.b bVar) throws JsonIOException {
        boolean zM = bVar.M();
        bVar.W(true);
        boolean zL = bVar.L();
        bVar.U(this.f2895i);
        boolean zK = bVar.K();
        bVar.X(this.f2894h);
        try {
            try {
                c.b.a.t.g.b(kVar, bVar);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        } finally {
            bVar.W(zM);
            bVar.U(zL);
            bVar.X(zK);
        }
    }

    public void w(k kVar, Appendable appendable) throws JsonIOException {
        try {
            v(kVar, r(c.b.a.t.g.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }

    public void x(Object obj, Type type, c.b.a.v.b bVar) throws JsonIOException {
        q qVarM = m(c.b.a.u.a.b(type));
        boolean zM = bVar.M();
        bVar.W(true);
        boolean zL = bVar.L();
        bVar.U(this.f2895i);
        boolean zK = bVar.K();
        bVar.X(this.f2894h);
        try {
            try {
                qVarM.d(bVar, obj);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        } finally {
            bVar.W(zM);
            bVar.U(zL);
            bVar.X(zK);
        }
    }

    public void y(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            x(obj, type, r(c.b.a.t.g.c(appendable)));
        } catch (IOException e2) {
            throw new JsonIOException(e2);
        }
    }
}
