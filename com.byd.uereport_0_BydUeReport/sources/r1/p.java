package r1;

import java.util.Collections;

/* JADX INFO: compiled from: ValueCallbackKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class p<K, A> extends a<K, A> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b2.b<A> f7786i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final A f7787j;

    public p(b2.c<A> cVar) {
        this(cVar, null);
    }

    @Override // r1.a
    public float c() {
        return 1.0f;
    }

    @Override // r1.a
    public A h() {
        b2.c<A> cVar = this.f7745e;
        A a8 = this.f7787j;
        return cVar.b(0.0f, 0.0f, a8, a8, f(), f(), f());
    }

    @Override // r1.a
    public A i(b2.a<K> aVar, float f8) {
        return h();
    }

    @Override // r1.a
    public void j() {
        if (this.f7745e != null) {
            super.j();
        }
    }

    @Override // r1.a
    public void l(float f8) {
        this.f7744d = f8;
    }

    public p(b2.c<A> cVar, A a8) {
        super(Collections.emptyList());
        this.f7786i = new b2.b<>();
        m(cVar);
        this.f7787j = a8;
    }
}
