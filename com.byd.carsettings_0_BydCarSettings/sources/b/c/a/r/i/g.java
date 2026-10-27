package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: MapTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class g implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.c.a.r.b f2260a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2261b;

    /* JADX INFO: compiled from: MapTypeAdapterFactory.java */
    public final class a<K, V> extends o<Map<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o<K> f2262a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final o<V> f2263b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b.c.a.r.e<? extends Map<K, V>> f2264c;

        public a(b.c.a.d dVar, Type type, o<K> oVar, Type type2, o<V> oVar2, b.c.a.r.e<? extends Map<K, V>> eVar) {
            this.f2262a = new m(dVar, oVar, type);
            this.f2263b = new m(dVar, oVar2, type2);
            this.f2264c = eVar;
        }

        public final String e(b.c.a.i iVar) {
            if (!iVar.g()) {
                if (iVar.e()) {
                    return "null";
                }
                throw new AssertionError();
            }
            b.c.a.l lVarC = iVar.c();
            if (lVarC.r()) {
                return String.valueOf(lVarC.n());
            }
            if (lVarC.p()) {
                return Boolean.toString(lVarC.i());
            }
            if (lVarC.t()) {
                return lVarC.o();
            }
            throw new AssertionError();
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Map<K, V> b(b.c.a.t.a aVar) throws IOException {
            JsonToken jsonTokenE = aVar.E();
            if (jsonTokenE == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            Map<K, V> mapA = this.f2264c.a();
            if (jsonTokenE == JsonToken.BEGIN_ARRAY) {
                aVar.c();
                while (aVar.n()) {
                    aVar.c();
                    K kB = this.f2262a.b(aVar);
                    if (mapA.put(kB, this.f2263b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB);
                    }
                    aVar.i();
                }
                aVar.i();
            } else {
                aVar.e();
                while (aVar.n()) {
                    b.c.a.r.d.f2239a.a(aVar);
                    K kB2 = this.f2262a.b(aVar);
                    if (mapA.put(kB2, this.f2263b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB2);
                    }
                }
                aVar.j();
            }
            return mapA;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Map<K, V> map) throws IOException {
            if (map == null) {
                bVar.s();
                return;
            }
            if (!g.this.f2261b) {
                bVar.f();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    bVar.o(String.valueOf(entry.getKey()));
                    this.f2263b.d(bVar, entry.getValue());
                }
                bVar.i();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                b.c.a.i iVarC = this.f2262a.c(entry2.getKey());
                arrayList.add(iVarC);
                arrayList2.add(entry2.getValue());
                z |= iVarC.d() || iVarC.f();
            }
            if (!z) {
                bVar.f();
                while (i < arrayList.size()) {
                    bVar.o(e((b.c.a.i) arrayList.get(i)));
                    this.f2263b.d(bVar, (V) arrayList2.get(i));
                    i++;
                }
                bVar.i();
                return;
            }
            bVar.e();
            while (i < arrayList.size()) {
                bVar.e();
                b.c.a.r.g.b((b.c.a.i) arrayList.get(i), bVar);
                this.f2263b.d(bVar, (V) arrayList2.get(i));
                bVar.h();
                i++;
            }
            bVar.h();
        }
    }

    public g(b.c.a.r.b bVar, boolean z) {
        this.f2260a = bVar;
        this.f2261b = z;
    }

    @Override // b.c.a.p
    public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
        Type typeF = aVar.f();
        if (!Map.class.isAssignableFrom(aVar.d())) {
            return null;
        }
        Type[] typeArrJ = C$Gson$Types.j(typeF, C$Gson$Types.k(typeF));
        return new a(dVar, typeArrJ[0], b(dVar, typeArrJ[0]), typeArrJ[1], dVar.k(b.c.a.s.a.b(typeArrJ[1])), this.f2260a.a(aVar));
    }

    public final o<?> b(b.c.a.d dVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? n.f2300f : dVar.k(b.c.a.s.a.b(type));
    }
}
