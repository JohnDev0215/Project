package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.c;
import com.google.gson.internal.e;
import com.google.gson.internal.h;
import com.google.gson.internal.k;
import com.google.gson.j;
import com.google.gson.o;
import com.google.gson.r;
import com.google.gson.t;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class MapTypeAdapterFactory implements t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f4463b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f4464c;

    private final class Adapter<K, V> extends TypeAdapter<Map<K, V>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TypeAdapter<K> f4465a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TypeAdapter<V> f4466b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final h<? extends Map<K, V>> f4467c;

        public Adapter(Gson gson, Type type, TypeAdapter<K> typeAdapter, Type type2, TypeAdapter<V> typeAdapter2, h<? extends Map<K, V>> hVar) {
            this.f4465a = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, type);
            this.f4466b = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter2, type2);
            this.f4467c = hVar;
        }

        private String a(j jVar) {
            if (!jVar.l()) {
                if (jVar.j()) {
                    return "null";
                }
                throw new AssertionError();
            }
            o oVarF = jVar.f();
            if (oVarF.q()) {
                return String.valueOf(oVarF.n());
            }
            if (oVarF.o()) {
                return Boolean.toString(oVarF.a());
            }
            if (oVarF.s()) {
                return oVarF.h();
            }
            throw new AssertionError();
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map<K, V> read(com.google.gson.w.a aVar) throws IOException {
            com.google.gson.w.b bVarJ0 = aVar.j0();
            if (bVarJ0 == com.google.gson.w.b.NULL) {
                aVar.f0();
                return null;
            }
            Map<K, V> mapA = this.f4467c.a();
            if (bVarJ0 == com.google.gson.w.b.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.V()) {
                    aVar.a();
                    K k = this.f4465a.read(aVar);
                    if (mapA.put(k, this.f4466b.read(aVar)) != null) {
                        throw new r("duplicate key: " + k);
                    }
                    aVar.R();
                }
                aVar.R();
            } else {
                aVar.d();
                while (aVar.V()) {
                    e.f4563a.a(aVar);
                    K k2 = this.f4465a.read(aVar);
                    if (mapA.put(k2, this.f4466b.read(aVar)) != null) {
                        throw new r("duplicate key: " + k2);
                    }
                }
                aVar.S();
            }
            return mapA;
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void write(com.google.gson.w.c cVar, Map<K, V> map) throws IOException {
            if (map == null) {
                cVar.Y();
                return;
            }
            if (!MapTypeAdapterFactory.this.f4464c) {
                cVar.y();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    cVar.W(String.valueOf(entry.getKey()));
                    this.f4466b.write(cVar, entry.getValue());
                }
                cVar.S();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i2 = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                j jsonTree = this.f4465a.toJsonTree(entry2.getKey());
                arrayList.add(jsonTree);
                arrayList2.add(entry2.getValue());
                z |= jsonTree.i() || jsonTree.k();
            }
            if (!z) {
                cVar.y();
                int size = arrayList.size();
                while (i2 < size) {
                    cVar.W(a((j) arrayList.get(i2)));
                    this.f4466b.write(cVar, (V) arrayList2.get(i2));
                    i2++;
                }
                cVar.S();
                return;
            }
            cVar.j();
            int size2 = arrayList.size();
            while (i2 < size2) {
                cVar.j();
                k.b((j) arrayList.get(i2), cVar);
                this.f4466b.write(cVar, (V) arrayList2.get(i2));
                cVar.R();
                i2++;
            }
            cVar.R();
        }
    }

    public MapTypeAdapterFactory(c cVar, boolean z) {
        this.f4463b = cVar;
        this.f4464c = z;
    }

    private TypeAdapter<?> a(Gson gson, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? TypeAdapters.f4513f : gson.k(com.google.gson.v.a.b(type));
    }

    @Override // com.google.gson.t
    public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
        Type typeE = aVar.e();
        if (!Map.class.isAssignableFrom(aVar.c())) {
            return null;
        }
        Type[] typeArrJ = com.google.gson.internal.b.j(typeE, com.google.gson.internal.b.k(typeE));
        return new Adapter(gson, typeArrJ[0], a(gson, typeArrJ[0]), typeArrJ[1], gson.k(com.google.gson.v.a.b(typeArrJ[1])), this.f4463b.a(aVar));
    }
}
