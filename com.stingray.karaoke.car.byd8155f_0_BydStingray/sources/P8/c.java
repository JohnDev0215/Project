package P8;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class c implements k, d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f5461a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5462b;

    public c(k kVar, int i7) {
        this.f5461a = kVar;
        this.f5462b = i7;
        if (i7 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i7 + '.').toString());
    }

    @Override // P8.d
    public final k a(int i7) {
        int i10 = this.f5462b + i7;
        return i10 < 0 ? new c(this, i7) : new c(this.f5461a, i10);
    }

    @Override // P8.k
    public final Iterator iterator() {
        return new b(this);
    }
}
