package com.google.protobuf;

import com.google.protobuf.a;
import com.google.protobuf.n;
import com.google.protobuf.q0;
import com.google.protobuf.r0;
import defpackage.nu;
import defpackage.wv1;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: MapEntry.java */
/* JADX INFO: loaded from: classes.dex */
public final class j0<K, V> extends com.google.protobuf.a {
    public final K f;
    public final V g;
    public volatile int h;

    public /* synthetic */ j0() {
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public j0(Object obj, Object obj2) {
        this.h = -1;
        this.f = obj;
        this.g = obj2;
    }

    @Override // defpackage.bh1, com.google.protobuf.t0
    public final q0 b() {
        throw null;
    }

    @Override // com.google.protobuf.t0
    public final n.a c() {
        throw null;
    }

    @Override // com.google.protobuf.r0, com.google.protobuf.q0
    public final q0.a e() {
        throw null;
    }

    @Override // com.google.protobuf.r0
    public final r0.a h() {
        return new a(this.f, this.g, true, true);
    }

    @Override // defpackage.bh1
    public final boolean isInitialized() {
        throw null;
    }

    @Override // com.google.protobuf.t0
    public final boolean k(n.f fVar) {
        n.a aVar = fVar.l;
        throw null;
    }

    @Override // com.google.protobuf.r0
    public final int m() {
        if (this.h != -1) {
            return this.h;
        }
        int iA = k0.a(null, this.f, this.g);
        this.h = iA;
        return iA;
    }

    @Override // com.google.protobuf.t0
    public final j1 n() {
        return j1.f;
    }

    @Override // com.google.protobuf.t0
    public final Object o(n.f fVar) {
        n.a aVar = fVar.l;
        throw null;
    }

    @Override // com.google.protobuf.r0
    public final void p(nu nuVar) {
        k0.b(nuVar, null, this.f, this.g);
    }

    @Override // com.google.protobuf.t0
    public final Map<n.f, Object> s() {
        new TreeMap();
        throw null;
    }

    @Override // com.google.protobuf.r0
    public final wv1<j0<K, V>> u() {
        throw null;
    }

    /* JADX INFO: compiled from: MapEntry.java */
    public static class a<K, V> extends a.AbstractC0042a<a<K, V>> {
        public K e;
        public V f;
        public boolean g;
        public boolean h;

        /* JADX WARN: Multi-variable type inference failed */
        public a(Object obj, Object obj2, boolean z, boolean z2) {
            this.e = obj;
            this.f = obj2;
            this.g = z;
            this.h = z2;
        }

        @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public final j0<K, V> d() {
            return new j0<>(this.e, this.f);
        }

        @Override // com.google.protobuf.q0.a
        public final q0.a a(n.f fVar, Object obj) {
            n.a aVar = fVar.l;
            throw null;
        }

        @Override // defpackage.bh1, com.google.protobuf.t0
        public final r0 b() {
            throw null;
        }

        @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
        public final q0 build() {
            d();
            throw null;
        }

        @Override // com.google.protobuf.q0.a, com.google.protobuf.t0
        public final n.a c() {
            throw null;
        }

        public final Object clone() {
            return new a(this.e, this.f, this.g, this.h);
        }

        @Override // com.google.protobuf.q0.a
        public final q0.a f(n.f fVar, Object obj) {
            throw new RuntimeException("There is no repeated field in a map entry message.");
        }

        @Override // com.google.protobuf.t0
        public final boolean k(n.f fVar) {
            n.a aVar = fVar.l;
            throw null;
        }

        @Override // com.google.protobuf.t0
        public final j1 n() {
            return j1.f;
        }

        @Override // com.google.protobuf.t0
        public final Object o(n.f fVar) {
            n.a aVar = fVar.l;
            throw null;
        }

        @Override // com.google.protobuf.t0
        public final Map<n.f, Object> s() {
            new TreeMap();
            throw null;
        }

        @Override // com.google.protobuf.q0.a
        public final q0.a w(n.f fVar) {
            n.a aVar = fVar.l;
            throw null;
        }

        @Override // com.google.protobuf.r0.a, com.google.protobuf.q0.a
        public final r0 build() {
            d();
            throw null;
        }

        @Override // com.google.protobuf.q0.a
        public final q0.a j0(j1 j1Var) {
            return this;
        }
    }

    @Override // defpackage.bh1, com.google.protobuf.t0
    public final r0 b() {
        throw null;
    }

    @Override // com.google.protobuf.r0, com.google.protobuf.q0
    public final r0.a e() {
        throw null;
    }
}
