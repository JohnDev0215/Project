package com.google.protobuf;

import com.google.protobuf.n1;
import defpackage.nu;
import defpackage.pt0;

/* JADX INFO: compiled from: MapEntryLite.java */
/* JADX INFO: loaded from: classes.dex */
public final class k0<K, V> {
    public final a<K, V> a;
    public final K b;
    public final V c;

    /* JADX INFO: compiled from: MapEntryLite.java */
    public static class a<K, V> {
        public final n1.a a;
        public final K b;
        public final n1.a c;
        public final V d;

        /* JADX WARN: Multi-variable type inference failed */
        public a(n1.a aVar, Integer num, n1.a.c cVar, pt0 pt0Var) {
            this.a = aVar;
            this.b = num;
            this.c = cVar;
            this.d = pt0Var;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public k0(n1.a aVar, Integer num, n1.a.c cVar, pt0 pt0Var) {
        this.a = new a<>(aVar, num, cVar, pt0Var);
        this.b = num;
        this.c = pt0Var;
    }

    public static <K, V> int a(a<K, V> aVar, K k, V v) {
        return x.f(aVar.c, 2, v) + x.f(aVar.a, 1, k);
    }

    public static <K, V> void b(nu nuVar, a<K, V> aVar, K k, V v) {
        x.y(nuVar, aVar.a, 1, k);
        x.y(nuVar, aVar.c, 2, v);
    }
}
