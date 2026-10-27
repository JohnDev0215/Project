package n6;

import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import k6.o;
import k6.p;

/* JADX INFO: compiled from: MapTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m6.b f6573d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f6574e;

    /* JADX INFO: compiled from: MapTypeAdapterFactory.java */
    public final class a<K, V> extends o<Map<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o<K> f6575a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final o<V> f6576b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final m6.e<? extends Map<K, V>> f6577c;

        public a(k6.d dVar, Type type, o<K> oVar, Type type2, o<V> oVar2, m6.e<? extends Map<K, V>> eVar) {
            this.f6575a = new m(dVar, oVar, type);
            this.f6576b = new m(dVar, oVar2, type2);
            this.f6577c = eVar;
        }

        public final String e(k6.i iVar) {
            if (!iVar.g()) {
                if (iVar.e()) {
                    return "null";
                }
                throw new AssertionError();
            }
            k6.l lVarC = iVar.c();
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

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Map<K, V> b(q6.a aVar) throws IOException {
            JsonToken jsonTokenR = aVar.R();
            if (jsonTokenR == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            Map<K, V> mapA = this.f6577c.a();
            if (jsonTokenR == JsonToken.BEGIN_ARRAY) {
                aVar.g();
                while (aVar.t()) {
                    aVar.g();
                    K kB = this.f6575a.b(aVar);
                    if (mapA.put(kB, this.f6576b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB);
                    }
                    aVar.o();
                }
                aVar.o();
            } else {
                aVar.i();
                while (aVar.t()) {
                    m6.d.f6422a.a(aVar);
                    K kB2 = this.f6575a.b(aVar);
                    if (mapA.put(kB2, this.f6576b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB2);
                    }
                }
                aVar.q();
            }
            return mapA;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Map<K, V> map) throws IOException {
            if (map == null) {
                bVar.B();
                return;
            }
            if (!g.this.f6574e) {
                bVar.j();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    bVar.w(String.valueOf(entry.getKey()));
                    this.f6576b.d(bVar, entry.getValue());
                }
                bVar.o();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i8 = 0;
            boolean z7 = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                k6.i iVarC = this.f6575a.c(entry2.getKey());
                arrayList.add(iVarC);
                arrayList2.add(entry2.getValue());
                z7 |= iVarC.d() || iVarC.f();
            }
            if (!z7) {
                bVar.j();
                while (i8 < arrayList.size()) {
                    bVar.w(e((k6.i) arrayList.get(i8)));
                    this.f6576b.d(bVar, (V) arrayList2.get(i8));
                    i8++;
                }
                bVar.o();
                return;
            }
            bVar.i();
            while (i8 < arrayList.size()) {
                bVar.i();
                m6.g.b((k6.i) arrayList.get(i8), bVar);
                this.f6576b.d(bVar, (V) arrayList2.get(i8));
                bVar.n();
                i8++;
            }
            bVar.n();
        }
    }

    public g(m6.b bVar, boolean z7) {
        this.f6573d = bVar;
        this.f6574e = z7;
    }

    @Override // k6.p
    public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
        Type typeF = aVar.f();
        if (!Map.class.isAssignableFrom(aVar.d())) {
            return null;
        }
        Type[] typeArrJ = C$Gson$Types.j(typeF, C$Gson$Types.k(typeF));
        return new a(dVar, typeArrJ[0], b(dVar, typeArrJ[0]), typeArrJ[1], dVar.k(p6.a.b(typeArrJ[1])), this.f6573d.a(aVar));
    }

    public final o<?> b(k6.d dVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? n.f6618f : dVar.k(p6.a.b(type));
    }
}
