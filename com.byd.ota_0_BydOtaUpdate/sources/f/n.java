package f;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: Segment.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f3721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3722b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3723c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3724d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3725e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public n f3726f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public n f3727g;

    public n() {
        this.f3721a = new byte[8192];
        this.f3725e = true;
        this.f3724d = false;
    }

    public void a() {
        n nVar = this.f3727g;
        if (nVar == this) {
            throw new IllegalStateException();
        }
        if (nVar.f3725e) {
            int i2 = this.f3723c - this.f3722b;
            if (i2 > (8192 - nVar.f3723c) + (nVar.f3724d ? 0 : nVar.f3722b)) {
                return;
            }
            e(nVar, i2);
            b();
            o.a(this);
        }
    }

    @Nullable
    public n b() {
        n nVar = this.f3726f;
        n nVar2 = nVar != this ? nVar : null;
        n nVar3 = this.f3727g;
        nVar3.f3726f = nVar;
        this.f3726f.f3727g = nVar3;
        this.f3726f = null;
        this.f3727g = null;
        return nVar2;
    }

    public n c(n nVar) {
        nVar.f3727g = this;
        nVar.f3726f = this.f3726f;
        this.f3726f.f3727g = nVar;
        this.f3726f = nVar;
        return nVar;
    }

    public n d(int i2) {
        n nVarB;
        if (i2 <= 0 || i2 > this.f3723c - this.f3722b) {
            throw new IllegalArgumentException();
        }
        if (i2 >= 1024) {
            nVarB = new n(this);
        } else {
            nVarB = o.b();
            System.arraycopy(this.f3721a, this.f3722b, nVarB.f3721a, 0, i2);
        }
        nVarB.f3723c = nVarB.f3722b + i2;
        this.f3722b += i2;
        this.f3727g.c(nVarB);
        return nVarB;
    }

    public void e(n nVar, int i2) {
        if (!nVar.f3725e) {
            throw new IllegalArgumentException();
        }
        int i3 = nVar.f3723c;
        if (i3 + i2 > 8192) {
            if (nVar.f3724d) {
                throw new IllegalArgumentException();
            }
            int i4 = nVar.f3722b;
            if ((i3 + i2) - i4 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = nVar.f3721a;
            System.arraycopy(bArr, i4, bArr, 0, i3 - i4);
            nVar.f3723c -= nVar.f3722b;
            nVar.f3722b = 0;
        }
        System.arraycopy(this.f3721a, this.f3722b, nVar.f3721a, nVar.f3723c, i2);
        nVar.f3723c += i2;
        this.f3722b += i2;
    }

    public n(n nVar) {
        this(nVar.f3721a, nVar.f3722b, nVar.f3723c);
        nVar.f3724d = true;
    }

    public n(byte[] bArr, int i2, int i3) {
        this.f3721a = bArr;
        this.f3722b = i2;
        this.f3723c = i3;
        this.f3725e = false;
        this.f3724d = true;
    }
}
