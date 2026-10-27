package c.b.a.t.i;

import c.b.a.q;
import c.b.a.r;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: MapTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.b.a.t.b f2969c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f2970d;

    /* JADX INFO: compiled from: MapTypeAdapterFactory.java */
    public final class a<K, V> extends q<Map<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q<K> f2971a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final q<V> f2972b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c.b.a.t.e<? extends Map<K, V>> f2973c;

        public a(c.b.a.e eVar, Type type, q<K> qVar, Type type2, q<V> qVar2, c.b.a.t.e<? extends Map<K, V>> eVar2) {
            this.f2971a = new m(eVar, qVar, type);
            this.f2972b = new m(eVar, qVar2, type2);
            this.f2973c = eVar2;
        }

        public final String e(c.b.a.k kVar) {
            if (!kVar.h()) {
                if (kVar.f()) {
                    return "null";
                }
                throw new AssertionError();
            }
            c.b.a.n nVarC = kVar.c();
            if (nVarC.q()) {
                return String.valueOf(nVarC.n());
            }
            if (nVarC.o()) {
                return Boolean.toString(nVarC.i());
            }
            if (nVarC.s()) {
                return nVarC.d();
            }
            throw new AssertionError();
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Map<K, V> b(c.b.a.v.a aVar) throws IOException {
            JsonToken jsonTokenB0 = aVar.b0();
            if (jsonTokenB0 == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            Map<K, V> mapA = this.f2973c.a();
            if (jsonTokenB0 == JsonToken.BEGIN_ARRAY) {
                aVar.d();
                while (aVar.N()) {
                    aVar.d();
                    K kB = this.f2971a.b(aVar);
                    if (mapA.put(kB, this.f2972b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB);
                    }
                    aVar.J();
                }
                aVar.J();
            } else {
                aVar.s();
                while (aVar.N()) {
                    c.b.a.t.d.f2948a.a(aVar);
                    K kB2 = this.f2971a.b(aVar);
                    if (mapA.put(kB2, this.f2972b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB2);
                    }
                }
                aVar.K();
            }
            return mapA;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Map<K, V> map) throws IOException {
            if (map == null) {
                bVar.P();
                return;
            }
            if (!g.this.f2970d) {
                bVar.v();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    bVar.N(String.valueOf(entry.getKey()));
                    this.f2972b.d(bVar, entry.getValue());
                }
                bVar.J();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i2 = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                c.b.a.k kVarC = this.f2971a.c(entry2.getKey());
                arrayList.add(kVarC);
                arrayList2.add(entry2.getValue());
                z |= kVarC.e() || kVarC.g();
            }
            if (!z) {
                bVar.v();
                while (i2 < arrayList.size()) {
                    bVar.N(e((c.b.a.k) arrayList.get(i2)));
                    this.f2972b.d(bVar, (V) arrayList2.get(i2));
                    i2++;
                }
                bVar.J();
                return;
            }
            bVar.s();
            while (i2 < arrayList.size()) {
                bVar.s();
                c.b.a.t.g.b((c.b.a.k) arrayList.get(i2), bVar);
                this.f2972b.d(bVar, (V) arrayList2.get(i2));
                bVar.I();
                i2++;
            }
            bVar.I();
        }
    }

    public g(c.b.a.t.b bVar, boolean z) {
        this.f2969c = bVar;
        this.f2970d = z;
    }

    @Override // c.b.a.r
    public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
        Type typeE = aVar.e();
        if (!Map.class.isAssignableFrom(aVar.c())) {
            return null;
        }
        Type[] typeArrJ = C$Gson$Types.j(typeE, C$Gson$Types.k(typeE));
        return new a(eVar, typeArrJ[0], b(eVar, typeArrJ[0]), typeArrJ[1], eVar.m(c.b.a.u.a.b(typeArrJ[1])), this.f2969c.a(aVar));
    }

    public final q<?> b(c.b.a.e eVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? n.f3013f : eVar.m(c.b.a.u.a.b(type));
    }
}
