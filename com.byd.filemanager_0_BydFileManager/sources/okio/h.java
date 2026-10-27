package okio;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: Segment.java */
/* JADX INFO: loaded from: classes.dex */
final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final byte[] f4659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f4660b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f4661c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f4662d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f4663e;
    h f;
    h g;

    h() {
        this.f4659a = new byte[8192];
        this.f4663e = true;
        this.f4662d = false;
    }

    public final void a() {
        h hVar = this.g;
        if (hVar == this) {
            throw new IllegalStateException();
        }
        if (hVar.f4663e) {
            int i = this.f4661c - this.f4660b;
            if (i > (8192 - hVar.f4661c) + (hVar.f4662d ? 0 : hVar.f4660b)) {
                return;
            }
            f(hVar, i);
            b();
            i.a(this);
        }
    }

    @Nullable
    public final h b() {
        h hVar = this.f;
        h hVar2 = hVar != this ? hVar : null;
        h hVar3 = this.g;
        hVar3.f = hVar;
        this.f.g = hVar3;
        this.f = null;
        this.g = null;
        return hVar2;
    }

    public final h c(h hVar) {
        hVar.g = this;
        hVar.f = this.f;
        this.f.g = hVar;
        this.f = hVar;
        return hVar;
    }

    final h d() {
        this.f4662d = true;
        return new h(this.f4659a, this.f4660b, this.f4661c, true, false);
    }

    public final h e(int i) {
        h hVarB;
        if (i <= 0 || i > this.f4661c - this.f4660b) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            hVarB = d();
        } else {
            hVarB = i.b();
            System.arraycopy(this.f4659a, this.f4660b, hVarB.f4659a, 0, i);
        }
        hVarB.f4661c = hVarB.f4660b + i;
        this.f4660b += i;
        this.g.c(hVarB);
        return hVarB;
    }

    public final void f(h hVar, int i) {
        if (!hVar.f4663e) {
            throw new IllegalArgumentException();
        }
        int i2 = hVar.f4661c;
        if (i2 + i > 8192) {
            if (hVar.f4662d) {
                throw new IllegalArgumentException();
            }
            int i3 = hVar.f4660b;
            if ((i2 + i) - i3 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = hVar.f4659a;
            System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
            hVar.f4661c -= hVar.f4660b;
            hVar.f4660b = 0;
        }
        System.arraycopy(this.f4659a, this.f4660b, hVar.f4659a, hVar.f4661c, i);
        hVar.f4661c += i;
        this.f4660b += i;
    }

    h(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f4659a = bArr;
        this.f4660b = i;
        this.f4661c = i2;
        this.f4662d = z;
        this.f4663e = z2;
    }
}
