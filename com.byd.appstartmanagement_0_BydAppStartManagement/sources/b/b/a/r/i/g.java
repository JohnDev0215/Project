package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: MapTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.b.a.r.b f444b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f445c;

    /* JADX INFO: compiled from: MapTypeAdapterFactory.java */
    public final class a<K, V> extends o<Map<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o<K> f446a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final o<V> f447b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final b.b.a.r.e<? extends Map<K, V>> f448c;

        public a(b.b.a.d dVar, Type type, o<K> oVar, Type type2, o<V> oVar2, b.b.a.r.e<? extends Map<K, V>> eVar) {
            this.f446a = new m(dVar, oVar, type);
            this.f447b = new m(dVar, oVar2, type2);
            this.f448c = eVar;
        }

        public final String e(b.b.a.i iVar) {
            if (!iVar.g()) {
                if (iVar.e()) {
                    return "null";
                }
                throw new AssertionError();
            }
            b.b.a.l lVarC = iVar.c();
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

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Map<K, V> b(b.b.a.t.a aVar) throws IOException {
            JsonToken jsonTokenY = aVar.y();
            if (jsonTokenY == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            Map<K, V> mapA = this.f448c.a();
            if (jsonTokenY == JsonToken.BEGIN_ARRAY) {
                aVar.b();
                while (aVar.k()) {
                    aVar.b();
                    K kB = this.f446a.b(aVar);
                    if (mapA.put(kB, this.f447b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB);
                    }
                    aVar.g();
                }
                aVar.g();
            } else {
                aVar.c();
                while (aVar.k()) {
                    b.b.a.r.d.f423a.a(aVar);
                    K kB2 = this.f446a.b(aVar);
                    if (mapA.put(kB2, this.f447b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB2);
                    }
                }
                aVar.h();
            }
            return mapA;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Map<K, V> map) throws IOException {
            if (map == null) {
                bVar.n();
                return;
            }
            if (!g.this.f445c) {
                bVar.d();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    bVar.l(String.valueOf(entry.getKey()));
                    this.f447b.d(bVar, entry.getValue());
                }
                bVar.g();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                b.b.a.i iVarC = this.f446a.c(entry2.getKey());
                arrayList.add(iVarC);
                arrayList2.add(entry2.getValue());
                z |= iVarC.d() || iVarC.f();
            }
            if (!z) {
                bVar.d();
                while (i < arrayList.size()) {
                    bVar.l(e((b.b.a.i) arrayList.get(i)));
                    this.f447b.d(bVar, (V) arrayList2.get(i));
                    i++;
                }
                bVar.g();
                return;
            }
            bVar.c();
            while (i < arrayList.size()) {
                bVar.c();
                b.b.a.r.g.b((b.b.a.i) arrayList.get(i), bVar);
                this.f447b.d(bVar, (V) arrayList2.get(i));
                bVar.f();
                i++;
            }
            bVar.f();
        }
    }

    public g(b.b.a.r.b bVar, boolean z) {
        this.f444b = bVar;
        this.f445c = z;
    }

    @Override // b.b.a.p
    public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
        Type typeF = aVar.f();
        if (!Map.class.isAssignableFrom(aVar.d())) {
            return null;
        }
        Type[] typeArrJ = C$Gson$Types.j(typeF, C$Gson$Types.k(typeF));
        return new a(dVar, typeArrJ[0], b(dVar, typeArrJ[0]), typeArrJ[1], dVar.k(b.b.a.s.a.b(typeArrJ[1])), this.f444b.a(aVar));
    }

    public final o<?> b(b.b.a.d dVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? n.f : dVar.k(b.b.a.s.a.b(type));
    }
}
