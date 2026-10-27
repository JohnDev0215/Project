package androidx.datastore.preferences.protobuf;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0955s implements Cloneable {
    public final AbstractC0957u l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public AbstractC0957u f11888m;

    public AbstractC0955s(AbstractC0957u abstractC0957u) {
        this.l = abstractC0957u;
        if (abstractC0957u.i()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.f11888m = abstractC0957u.k();
    }

    public final AbstractC0957u a() {
        AbstractC0957u abstractC0957uB = b();
        abstractC0957uB.getClass();
        if (AbstractC0957u.h(abstractC0957uB, true)) {
            return abstractC0957uB;
        }
        throw new h0();
    }

    public final AbstractC0957u b() {
        if (!this.f11888m.i()) {
            return this.f11888m;
        }
        AbstractC0957u abstractC0957u = this.f11888m;
        abstractC0957u.getClass();
        V v10 = V.c;
        v10.getClass();
        v10.a(abstractC0957u.getClass()).d(abstractC0957u);
        abstractC0957u.j();
        return this.f11888m;
    }

    public final void c() {
        if (this.f11888m.i()) {
            return;
        }
        AbstractC0957u abstractC0957uK = this.l.k();
        AbstractC0957u abstractC0957u = this.f11888m;
        V v10 = V.c;
        v10.getClass();
        v10.a(abstractC0957uK.getClass()).a(abstractC0957uK, abstractC0957u);
        this.f11888m = abstractC0957uK;
    }

    public final Object clone() {
        AbstractC0955s abstractC0955s = (AbstractC0955s) this.l.e(5);
        abstractC0955s.f11888m = b();
        return abstractC0955s;
    }
}
