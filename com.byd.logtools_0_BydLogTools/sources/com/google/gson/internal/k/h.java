package com.google.gson.internal.k;

import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: MapTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.gson.internal.b f4066b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f4067c;

    /* JADX INFO: compiled from: MapTypeAdapterFactory.java */
    private final class a<K, V> extends p<Map<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final p<K> f4068a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final p<V> f4069b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final com.google.gson.internal.f<? extends Map<K, V>> f4070c;

        public a(com.google.gson.d dVar, Type type, p<K> pVar, Type type2, p<V> pVar2, com.google.gson.internal.f<? extends Map<K, V>> fVar) {
            this.f4068a = new m(dVar, pVar, type);
            this.f4069b = new m(dVar, pVar2, type2);
            this.f4070c = fVar;
        }

        private String e(com.google.gson.i iVar) {
            if (!iVar.g()) {
                if (iVar.e()) {
                    return "null";
                }
                throw new AssertionError();
            }
            com.google.gson.l lVarC = iVar.c();
            if (lVarC.p()) {
                return String.valueOf(lVarC.l());
            }
            if (lVarC.n()) {
                return Boolean.toString(lVarC.h());
            }
            if (lVarC.q()) {
                return lVarC.m();
            }
            throw new AssertionError();
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Map<K, V> b(com.google.gson.stream.a aVar) throws IOException {
            JsonToken jsonTokenX = aVar.x();
            if (jsonTokenX == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            Map<K, V> mapA = this.f4070c.a();
            if (jsonTokenX == JsonToken.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.j()) {
                    aVar.a();
                    K kB = this.f4068a.b(aVar);
                    if (mapA.put(kB, this.f4069b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB);
                    }
                    aVar.f();
                }
                aVar.f();
            } else {
                aVar.b();
                while (aVar.j()) {
                    com.google.gson.internal.e.f4046a.a(aVar);
                    K kB2 = this.f4068a.b(aVar);
                    if (mapA.put(kB2, this.f4069b.b(aVar)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + kB2);
                    }
                }
                aVar.g();
            }
            return mapA;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Map<K, V> map) throws IOException {
            if (map == null) {
                bVar.m();
                return;
            }
            if (!h.this.f4067c) {
                bVar.d();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    bVar.k(String.valueOf(entry.getKey()));
                    this.f4069b.d(bVar, entry.getValue());
                }
                bVar.g();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                com.google.gson.i iVarC = this.f4068a.c(entry2.getKey());
                arrayList.add(iVarC);
                arrayList2.add(entry2.getValue());
                z |= iVarC.d() || iVarC.f();
            }
            if (!z) {
                bVar.d();
                int size = arrayList.size();
                while (i < size) {
                    bVar.k(e((com.google.gson.i) arrayList.get(i)));
                    this.f4069b.d(bVar, (V) arrayList2.get(i));
                    i++;
                }
                bVar.g();
                return;
            }
            bVar.c();
            int size2 = arrayList.size();
            while (i < size2) {
                bVar.c();
                com.google.gson.internal.i.b((com.google.gson.i) arrayList.get(i), bVar);
                this.f4069b.d(bVar, (V) arrayList2.get(i));
                bVar.f();
                i++;
            }
            bVar.f();
        }
    }

    public h(com.google.gson.internal.b bVar, boolean z) {
        this.f4066b = bVar;
        this.f4067c = z;
    }

    private p<?> b(com.google.gson.d dVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? n.f4112f : dVar.k(com.google.gson.s.a.b(type));
    }

    @Override // com.google.gson.q
    public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
        Type typeE = aVar.e();
        if (!Map.class.isAssignableFrom(aVar.c())) {
            return null;
        }
        Type[] typeArrJ = C$Gson$Types.j(typeE, C$Gson$Types.k(typeE));
        return new a(dVar, typeArrJ[0], b(dVar, typeArrJ[0]), typeArrJ[1], dVar.k(com.google.gson.s.a.b(typeArrJ[1])), this.f4066b.a(aVar));
    }
}
