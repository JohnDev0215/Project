package i7;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: Segment.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f5522a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5523b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5524c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5525d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5526e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public h f5527f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public h f5528g;

    public h() {
        this.f5522a = new byte[8192];
        this.f5526e = true;
        this.f5525d = false;
    }

    public final void a() {
        h hVar = this.f5528g;
        if (hVar == this) {
            throw new IllegalStateException();
        }
        if (hVar.f5526e) {
            int i8 = this.f5524c - this.f5523b;
            if (i8 > (8192 - hVar.f5524c) + (hVar.f5525d ? 0 : hVar.f5523b)) {
                return;
            }
            f(hVar, i8);
            b();
            i.a(this);
        }
    }

    @Nullable
    public final h b() {
        h hVar = this.f5527f;
        h hVar2 = hVar != this ? hVar : null;
        h hVar3 = this.f5528g;
        hVar3.f5527f = hVar;
        this.f5527f.f5528g = hVar3;
        this.f5527f = null;
        this.f5528g = null;
        return hVar2;
    }

    public final h c(h hVar) {
        hVar.f5528g = this;
        hVar.f5527f = this.f5527f;
        this.f5527f.f5528g = hVar;
        this.f5527f = hVar;
        return hVar;
    }

    public final h d() {
        this.f5525d = true;
        return new h(this.f5522a, this.f5523b, this.f5524c, true, false);
    }

    public final h e(int i8) {
        h hVarB;
        if (i8 <= 0 || i8 > this.f5524c - this.f5523b) {
            throw new IllegalArgumentException();
        }
        if (i8 >= 1024) {
            hVarB = d();
        } else {
            hVarB = i.b();
            System.arraycopy(this.f5522a, this.f5523b, hVarB.f5522a, 0, i8);
        }
        hVarB.f5524c = hVarB.f5523b + i8;
        this.f5523b += i8;
        this.f5528g.c(hVarB);
        return hVarB;
    }

    public final void f(h hVar, int i8) {
        if (!hVar.f5526e) {
            throw new IllegalArgumentException();
        }
        int i9 = hVar.f5524c;
        if (i9 + i8 > 8192) {
            if (hVar.f5525d) {
                throw new IllegalArgumentException();
            }
            int i10 = hVar.f5523b;
            if ((i9 + i8) - i10 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = hVar.f5522a;
            System.arraycopy(bArr, i10, bArr, 0, i9 - i10);
            hVar.f5524c -= hVar.f5523b;
            hVar.f5523b = 0;
        }
        System.arraycopy(this.f5522a, this.f5523b, hVar.f5522a, hVar.f5524c, i8);
        hVar.f5524c += i8;
        this.f5523b += i8;
    }

    public h(byte[] bArr, int i8, int i9, boolean z7, boolean z8) {
        this.f5522a = bArr;
        this.f5523b = i8;
        this.f5524c = i9;
        this.f5525d = z7;
        this.f5526e = z8;
    }
}
