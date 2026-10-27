package B2;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f503a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f504b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f505e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f506f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Serializable f507g;

    public boolean a(int i7) {
        int i10;
        int i11;
        int i12;
        int i13;
        if (!((i7 & (-2097152)) == -2097152) || (i10 = (i7 >>> 19) & 3) == 1 || (i11 = (i7 >>> 17) & 3) == 0 || (i12 = (i7 >>> 12) & 15) == 0 || i12 == 15 || (i13 = (i7 >>> 10) & 3) == 3) {
            return false;
        }
        this.f503a = i10;
        this.f507g = AbstractC0062b.f544s[3 - i11];
        int i14 = AbstractC0062b.f545t[i13];
        this.c = i14;
        if (i10 == 2) {
            this.c = i14 / 2;
        } else if (i10 == 0) {
            this.c = i14 / 4;
        }
        int i15 = (i7 >>> 9) & 1;
        int i16 = 1152;
        if (i11 != 1) {
            if (i11 != 2) {
                if (i11 != 3) {
                    throw new IllegalArgumentException();
                }
                i16 = 384;
            }
        } else if (i10 != 3) {
            i16 = 576;
        }
        this.f506f = i16;
        if (i11 == 3) {
            int i17 = i10 == 3 ? AbstractC0062b.f546u[i12 - 1] : AbstractC0062b.f547v[i12 - 1];
            this.f505e = i17;
            this.f504b = (((i17 * 12) / this.c) + i15) * 4;
        } else {
            if (i10 == 3) {
                int i18 = i11 == 2 ? AbstractC0062b.f548w[i12 - 1] : AbstractC0062b.f549x[i12 - 1];
                this.f505e = i18;
                this.f504b = ((i18 * 144) / this.c) + i15;
            } else {
                int i19 = AbstractC0062b.f550y[i12 - 1];
                this.f505e = i19;
                this.f504b = (((i11 == 1 ? 72 : 144) * i19) / this.c) + i15;
            }
        }
        this.d = ((i7 >> 6) & 3) == 3 ? 1 : 2;
        return true;
    }
}
