package g;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: Segment.java */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f5496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5497b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5498c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5499d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5500e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public m f5501f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m f5502g;

    public m() {
        this.f5496a = new byte[8192];
        this.f5500e = true;
        this.f5499d = false;
    }

    public final void a() {
        m mVar = this.f5502g;
        if (mVar == this) {
            throw new IllegalStateException();
        }
        if (mVar.f5500e) {
            int i2 = this.f5498c - this.f5497b;
            if (i2 > (8192 - mVar.f5498c) + (mVar.f5499d ? 0 : mVar.f5497b)) {
                return;
            }
            f(mVar, i2);
            b();
            n.a(this);
        }
    }

    @Nullable
    public final m b() {
        m mVar = this.f5501f;
        m mVar2 = mVar != this ? mVar : null;
        m mVar3 = this.f5502g;
        mVar3.f5501f = mVar;
        this.f5501f.f5502g = mVar3;
        this.f5501f = null;
        this.f5502g = null;
        return mVar2;
    }

    public final m c(m mVar) {
        mVar.f5502g = this;
        mVar.f5501f = this.f5501f;
        this.f5501f.f5502g = mVar;
        this.f5501f = mVar;
        return mVar;
    }

    public final m d() {
        this.f5499d = true;
        return new m(this.f5496a, this.f5497b, this.f5498c, true, false);
    }

    public final m e(int i2) {
        m mVarB;
        if (i2 <= 0 || i2 > this.f5498c - this.f5497b) {
            throw new IllegalArgumentException();
        }
        if (i2 >= 1024) {
            mVarB = d();
        } else {
            mVarB = n.b();
            System.arraycopy(this.f5496a, this.f5497b, mVarB.f5496a, 0, i2);
        }
        mVarB.f5498c = mVarB.f5497b + i2;
        this.f5497b += i2;
        this.f5502g.c(mVarB);
        return mVarB;
    }

    public final void f(m mVar, int i2) {
        if (!mVar.f5500e) {
            throw new IllegalArgumentException();
        }
        int i3 = mVar.f5498c;
        if (i3 + i2 > 8192) {
            if (mVar.f5499d) {
                throw new IllegalArgumentException();
            }
            int i4 = mVar.f5497b;
            if ((i3 + i2) - i4 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = mVar.f5496a;
            System.arraycopy(bArr, i4, bArr, 0, i3 - i4);
            mVar.f5498c -= mVar.f5497b;
            mVar.f5497b = 0;
        }
        System.arraycopy(this.f5496a, this.f5497b, mVar.f5496a, mVar.f5498c, i2);
        mVar.f5498c += i2;
        this.f5497b += i2;
    }

    public m(byte[] bArr, int i2, int i3, boolean z, boolean z2) {
        this.f5496a = bArr;
        this.f5497b = i2;
        this.f5498c = i3;
        this.f5499d = z;
        this.f5500e = z2;
    }
}
