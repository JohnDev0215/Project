package c.b.b.r.i;

import c.b.b.o;
import c.b.b.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: MapTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.b.r.b f4232b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4233c;

    /* JADX INFO: compiled from: MapTypeAdapterFactory.java */
    public final class a<K, V> extends o<Map<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o<K> f4234a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final o<V> f4235b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c.b.b.r.e<? extends Map<K, V>> f4236c;

        public a(c.b.b.d dVar, Type type, o<K> oVar, Type type2, o<V> oVar2, c.b.b.r.e<? extends Map<K, V>> eVar) {
            this.f4234a = new m(dVar, oVar, type);
            this.f4235b = new m(dVar, oVar2, type2);
            this.f4236c = eVar;
        }

        public final String e(c.b.b.i iVar) {
            if (!iVar.g()) {
                if (iVar.e()) {
                    return "null";
                }
                throw new AssertionError();
            }
            c.b.b.l lVarC = iVar.c();
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

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Map<K, V> b(c.b.b.t.a aVar) throws IOException {
            JsonToken jsonTokenK0 = aVar.k0();
            if (jsonTokenK0 == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            Map<K, V> mapA = this.f4236c.a();
            if (jsonTokenK0 == JsonToken.BEGIN_ARRAY) {
                aVar.z();
                while (aVar.W()) {
                    aVar.z();
                    K kB = this.f4234a.b(aVar);
                    if (mapA.put(kB, this.f4235b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB);
                    }
                    aVar.S();
                }
                aVar.S();
            } else {
                aVar.O();
                while (aVar.W()) {
                    c.b.b.r.d.f4211a.a(aVar);
                    K kB2 = this.f4234a.b(aVar);
                    if (mapA.put(kB2, this.f4235b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB2);
                    }
                }
                aVar.T();
            }
            return mapA;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Map<K, V> map) throws IOException {
            if (map == null) {
                bVar.Z();
                return;
            }
            if (!g.this.f4233c) {
                bVar.P();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    bVar.X(String.valueOf(entry.getKey()));
                    this.f4235b.d(bVar, entry.getValue());
                }
                bVar.S();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i2 = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                c.b.b.i iVarC = this.f4234a.c(entry2.getKey());
                arrayList.add(iVarC);
                arrayList2.add(entry2.getValue());
                z |= iVarC.d() || iVarC.f();
            }
            if (!z) {
                bVar.P();
                while (i2 < arrayList.size()) {
                    bVar.X(e((c.b.b.i) arrayList.get(i2)));
                    this.f4235b.d(bVar, (V) arrayList2.get(i2));
                    i2++;
                }
                bVar.S();
                return;
            }
            bVar.O();
            while (i2 < arrayList.size()) {
                bVar.O();
                c.b.b.r.g.b((c.b.b.i) arrayList.get(i2), bVar);
                this.f4235b.d(bVar, (V) arrayList2.get(i2));
                bVar.R();
                i2++;
            }
            bVar.R();
        }
    }

    public g(c.b.b.r.b bVar, boolean z) {
        this.f4232b = bVar;
        this.f4233c = z;
    }

    @Override // c.b.b.p
    public <T> o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
        Type typeF = aVar.f();
        if (!Map.class.isAssignableFrom(aVar.d())) {
            return null;
        }
        Type[] typeArrJ = C$Gson$Types.j(typeF, C$Gson$Types.k(typeF));
        return new a(dVar, typeArrJ[0], b(dVar, typeArrJ[0]), typeArrJ[1], dVar.k(c.b.b.s.a.b(typeArrJ[1])), this.f4232b.a(aVar));
    }

    public final o<?> b(c.b.b.d dVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? n.f4276f : dVar.k(c.b.b.s.a.b(type));
    }
}
