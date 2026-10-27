package c;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final byte[] f639a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f640b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f641c;
    boolean d;
    boolean e;
    h f;
    h g;

    h() {
        this.f639a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    h(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f639a = bArr;
        this.f640b = i;
        this.f641c = i2;
        this.d = z;
        this.e = z2;
    }

    public final void a() {
        h hVar = this.g;
        if (hVar == this) {
            throw new IllegalStateException();
        }
        if (hVar.e) {
            int i = this.f641c - this.f640b;
            if (i > (8192 - hVar.f641c) + (hVar.d ? 0 : hVar.f640b)) {
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
        this.d = true;
        return new h(this.f639a, this.f640b, this.f641c, true, false);
    }

    public final h e(int i) {
        h hVarB;
        if (i <= 0 || i > this.f641c - this.f640b) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            hVarB = d();
        } else {
            hVarB = i.b();
            System.arraycopy(this.f639a, this.f640b, hVarB.f639a, 0, i);
        }
        hVarB.f641c = hVarB.f640b + i;
        this.f640b += i;
        this.g.c(hVarB);
        return hVarB;
    }

    public final void f(h hVar, int i) {
        if (!hVar.e) {
            throw new IllegalArgumentException();
        }
        int i2 = hVar.f641c;
        if (i2 + i > 8192) {
            if (hVar.d) {
                throw new IllegalArgumentException();
            }
            int i3 = hVar.f640b;
            if ((i2 + i) - i3 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = hVar.f639a;
            System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
            hVar.f641c -= hVar.f640b;
            hVar.f640b = 0;
        }
        System.arraycopy(this.f639a, this.f640b, hVar.f639a, hVar.f641c, i);
        hVar.f641c += i;
        this.f640b += i;
    }
}
