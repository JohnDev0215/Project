package h4;

import A4.q;
import android.graphics.Bitmap;

/* JADX INFO: renamed from: h4.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1367i implements InterfaceC1365g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1363e f15525a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f15526b;
    public Bitmap.Config c;

    public C1367i(C1363e c1363e) {
        this.f15525a = c1363e;
    }

    @Override // h4.InterfaceC1365g
    public final void a() {
        this.f15525a.S0(this);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1367i)) {
            return false;
        }
        C1367i c1367i = (C1367i) obj;
        return this.f15526b == c1367i.f15526b && q.b(this.c, c1367i.c);
    }

    public final int hashCode() {
        int i7 = this.f15526b * 31;
        Bitmap.Config config = this.c;
        return i7 + (config != null ? config.hashCode() : 0);
    }

    public final String toString() {
        return C1368j.c(this.f15526b, this.c);
    }
}
