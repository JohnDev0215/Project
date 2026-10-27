package f1;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: f1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1249d extends com.bumptech.glide.c {
    public final AtomicReferenceFieldUpdater d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f14534e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f14535f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f14536g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f14537h;

    public C1249d(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.d = atomicReferenceFieldUpdater;
        this.f14534e = atomicReferenceFieldUpdater2;
        this.f14535f = atomicReferenceFieldUpdater3;
        this.f14536g = atomicReferenceFieldUpdater4;
        this.f14537h = atomicReferenceFieldUpdater5;
    }

    @Override // com.bumptech.glide.c
    public final boolean I(AbstractC1252g abstractC1252g, C1248c c1248c, C1248c c1248c2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f14536g;
            if (atomicReferenceFieldUpdater.compareAndSet(abstractC1252g, c1248c, c1248c2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(abstractC1252g) == c1248c);
        return false;
    }

    @Override // com.bumptech.glide.c
    public final boolean J(AbstractC1252g abstractC1252g, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f14537h;
            if (atomicReferenceFieldUpdater.compareAndSet(abstractC1252g, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(abstractC1252g) == obj);
        return false;
    }

    @Override // com.bumptech.glide.c
    public final boolean K(AbstractC1252g abstractC1252g, C1251f c1251f, C1251f c1251f2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f14535f;
            if (atomicReferenceFieldUpdater.compareAndSet(abstractC1252g, c1251f, c1251f2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(abstractC1252g) == c1251f);
        return false;
    }

    @Override // com.bumptech.glide.c
    public final void w0(C1251f c1251f, C1251f c1251f2) {
        this.f14534e.lazySet(c1251f, c1251f2);
    }

    @Override // com.bumptech.glide.c
    public final void x0(C1251f c1251f, Thread thread) {
        this.d.lazySet(c1251f, thread);
    }
}
