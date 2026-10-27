package L8;

import F8.C0273h;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class r extends a {
    public final C0273h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f4094m;

    public r(int i7, C0273h c0273h) {
        this.l = c0273h;
        this.f4094m = i7;
    }

    @Override // L8.a
    public final int d() {
        return 1;
    }

    @Override // L8.a
    public final Object get(int i7) {
        if (i7 == this.f4094m) {
            return this.l;
        }
        return null;
    }

    @Override // L8.a, java.lang.Iterable
    public final Iterator iterator() {
        return new q(this, 0);
    }

    @Override // L8.a
    public final void l(int i7, C0273h c0273h) {
        throw new IllegalStateException();
    }
}
