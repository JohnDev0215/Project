package com.airbnb.lottie.r.c;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<K, A> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d<K> f2727c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected com.airbnb.lottie.v.c<A> f2729e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final List<b> f2725a = new ArrayList(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2726b = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected float f2728d = 0.0f;
    private A f = null;
    private float g = -1.0f;
    private float h = -1.0f;

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    public interface b {
        void b();
    }

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    private static final class c<T> implements d<T> {
        private c() {
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public float a() {
            return 1.0f;
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public boolean b(float f) {
            throw new IllegalStateException("not implemented");
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public float c() {
            return 0.0f;
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public com.airbnb.lottie.v.a<T> d() {
            throw new IllegalStateException("not implemented");
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public boolean e(float f) {
            return false;
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public boolean isEmpty() {
            return true;
        }
    }

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    private interface d<T> {
        float a();

        boolean b(float f);

        float c();

        com.airbnb.lottie.v.a<T> d();

        boolean e(float f);

        boolean isEmpty();
    }

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    private static final class e<T> implements d<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<? extends com.airbnb.lottie.v.a<T>> f2730a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private com.airbnb.lottie.v.a<T> f2732c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f2733d = -1.0f;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private com.airbnb.lottie.v.a<T> f2731b = f(0.0f);

        e(List<? extends com.airbnb.lottie.v.a<T>> list) {
            this.f2730a = list;
        }

        private com.airbnb.lottie.v.a<T> f(float f) {
            List<? extends com.airbnb.lottie.v.a<T>> list = this.f2730a;
            com.airbnb.lottie.v.a<T> aVar = list.get(list.size() - 1);
            if (f >= aVar.e()) {
                return aVar;
            }
            for (int size = this.f2730a.size() - 2; size >= 1; size--) {
                com.airbnb.lottie.v.a<T> aVar2 = this.f2730a.get(size);
                if (this.f2731b != aVar2 && aVar2.a(f)) {
                    return aVar2;
                }
            }
            return this.f2730a.get(0);
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public float a() {
            return this.f2730a.get(r0.size() - 1).b();
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public boolean b(float f) {
            com.airbnb.lottie.v.a<T> aVar = this.f2732c;
            com.airbnb.lottie.v.a<T> aVar2 = this.f2731b;
            if (aVar == aVar2 && this.f2733d == f) {
                return true;
            }
            this.f2732c = aVar2;
            this.f2733d = f;
            return false;
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public float c() {
            return this.f2730a.get(0).e();
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public com.airbnb.lottie.v.a<T> d() {
            return this.f2731b;
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public boolean e(float f) {
            if (this.f2731b.a(f)) {
                return !this.f2731b.h();
            }
            this.f2731b = f(f);
            return true;
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public boolean isEmpty() {
            return false;
        }
    }

    /* JADX INFO: compiled from: BaseKeyframeAnimation.java */
    private static final class f<T> implements d<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.airbnb.lottie.v.a<T> f2734a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f2735b = -1.0f;

        f(List<? extends com.airbnb.lottie.v.a<T>> list) {
            this.f2734a = list.get(0);
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public float a() {
            return this.f2734a.b();
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public boolean b(float f) {
            if (this.f2735b == f) {
                return true;
            }
            this.f2735b = f;
            return false;
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public float c() {
            return this.f2734a.e();
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public com.airbnb.lottie.v.a<T> d() {
            return this.f2734a;
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public boolean e(float f) {
            return !this.f2734a.h();
        }

        @Override // com.airbnb.lottie.r.c.a.d
        public boolean isEmpty() {
            return false;
        }
    }

    a(List<? extends com.airbnb.lottie.v.a<K>> list) {
        this.f2727c = n(list);
    }

    private float g() {
        if (this.g == -1.0f) {
            this.g = this.f2727c.c();
        }
        return this.g;
    }

    private static <T> d<T> n(List<? extends com.airbnb.lottie.v.a<T>> list) {
        return list.isEmpty() ? new c() : list.size() == 1 ? new f(list) : new e(list);
    }

    public void a(b bVar) {
        this.f2725a.add(bVar);
    }

    protected com.airbnb.lottie.v.a<K> b() {
        com.airbnb.lottie.c.a("BaseKeyframeAnimation#getCurrentKeyframe");
        com.airbnb.lottie.v.a<K> aVarD = this.f2727c.d();
        com.airbnb.lottie.c.b("BaseKeyframeAnimation#getCurrentKeyframe");
        return aVarD;
    }

    float c() {
        if (this.h == -1.0f) {
            this.h = this.f2727c.a();
        }
        return this.h;
    }

    protected float d() {
        com.airbnb.lottie.v.a<K> aVarB = b();
        if (aVarB.h()) {
            return 0.0f;
        }
        return aVarB.f2820d.getInterpolation(e());
    }

    float e() {
        if (this.f2726b) {
            return 0.0f;
        }
        com.airbnb.lottie.v.a<K> aVarB = b();
        if (aVarB.h()) {
            return 0.0f;
        }
        return (this.f2728d - aVarB.e()) / (aVarB.b() - aVarB.e());
    }

    public float f() {
        return this.f2728d;
    }

    public A h() {
        float fD = d();
        if (this.f2729e == null && this.f2727c.b(fD)) {
            return this.f;
        }
        A aI = i(b(), fD);
        this.f = aI;
        return aI;
    }

    abstract A i(com.airbnb.lottie.v.a<K> aVar, float f2);

    public void j() {
        for (int i = 0; i < this.f2725a.size(); i++) {
            this.f2725a.get(i).b();
        }
    }

    public void k() {
        this.f2726b = true;
    }

    public void l(float f2) {
        if (this.f2727c.isEmpty()) {
            return;
        }
        if (f2 < g()) {
            f2 = g();
        } else if (f2 > c()) {
            f2 = c();
        }
        if (f2 == this.f2728d) {
            return;
        }
        this.f2728d = f2;
        if (this.f2727c.e(f2)) {
            j();
        }
    }

    public void m(com.airbnb.lottie.v.c<A> cVar) {
        com.airbnb.lottie.v.c<A> cVar2 = this.f2729e;
        if (cVar2 != null) {
            cVar2.c(null);
        }
        this.f2729e = cVar;
        if (cVar != null) {
            cVar.c(this);
        }
    }
}
