package okio;

import javax.annotation.Nullable;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: Segment.java */
/* JADX INFO: loaded from: classes.dex */
final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final byte[] f4381a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f4382b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f4383c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f4384d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f4385e;
    h f;
    h g;

    h() {
        this.f4381a = new byte[CarPlayConfig.IapFeature.IAP_FEATURE_OOB_BT_PAIRING];
        this.f4385e = true;
        this.f4384d = false;
    }

    public final void a() {
        h hVar = this.g;
        if (hVar == this) {
            throw new IllegalStateException();
        }
        if (hVar.f4385e) {
            int i = this.f4383c - this.f4382b;
            if (i > (8192 - hVar.f4383c) + (hVar.f4384d ? 0 : hVar.f4382b)) {
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
        this.f4384d = true;
        return new h(this.f4381a, this.f4382b, this.f4383c, true, false);
    }

    public final h e(int i) {
        h hVarB;
        if (i <= 0 || i > this.f4383c - this.f4382b) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            hVarB = d();
        } else {
            hVarB = i.b();
            System.arraycopy(this.f4381a, this.f4382b, hVarB.f4381a, 0, i);
        }
        hVarB.f4383c = hVarB.f4382b + i;
        this.f4382b += i;
        this.g.c(hVarB);
        return hVarB;
    }

    public final void f(h hVar, int i) {
        if (!hVar.f4385e) {
            throw new IllegalArgumentException();
        }
        int i2 = hVar.f4383c;
        if (i2 + i > 8192) {
            if (hVar.f4384d) {
                throw new IllegalArgumentException();
            }
            int i3 = hVar.f4382b;
            if ((i2 + i) - i3 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = hVar.f4381a;
            System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
            hVar.f4383c -= hVar.f4382b;
            hVar.f4382b = 0;
        }
        System.arraycopy(this.f4381a, this.f4382b, hVar.f4381a, hVar.f4383c, i);
        hVar.f4383c += i;
        this.f4382b += i;
    }

    h(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f4381a = bArr;
        this.f4382b = i;
        this.f4383c = i2;
        this.f4384d = z;
        this.f4385e = z2;
    }
}
