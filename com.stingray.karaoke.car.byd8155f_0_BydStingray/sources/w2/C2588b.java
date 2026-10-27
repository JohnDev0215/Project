package w2;

import d5.C1077F;
import e2.C1208p;
import e2.g0;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: renamed from: w2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2588b implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0 f23172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23173b;
    public final int[] c;
    public final C1208p[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f23174e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f23175f;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2588b(g0 g0Var, int[] iArr) {
        this(g0Var, iArr, (byte) 0);
        this.f23175f = 0;
    }

    public static void m(ArrayList arrayList, long[] jArr) {
        long j3 = 0;
        for (long j10 : jArr) {
            j3 += j10;
        }
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            C1077F c1077f = (C1077F) arrayList.get(i7);
            if (c1077f != null) {
                c1077f.a(new C2587a(j3, jArr[i7]));
            }
        }
    }

    private final void n() {
    }

    private final void p() {
    }

    private final void r(float f10) {
    }

    @Override // w2.q
    public final void a(boolean z6) {
    }

    @Override // w2.q
    public final C1208p b(int i7) {
        return this.d[i7];
    }

    @Override // w2.q
    public void c() {
        int i7 = this.f23175f;
    }

    @Override // w2.q
    public final int d(int i7) {
        return this.c[i7];
    }

    @Override // w2.q
    public void e() {
        int i7 = this.f23175f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C2588b c2588b = (C2588b) obj;
        return this.f23172a.equals(c2588b.f23172a) && Arrays.equals(this.c, c2588b.c);
    }

    @Override // w2.q
    public final int f() {
        return this.c[0];
    }

    @Override // w2.q
    public final g0 g() {
        return this.f23172a;
    }

    @Override // w2.q
    public final C1208p h() {
        return this.d[0];
    }

    public final int hashCode() {
        if (this.f23174e == 0) {
            this.f23174e = Arrays.hashCode(this.c) + (System.identityHashCode(this.f23172a) * 31);
        }
        return this.f23174e;
    }

    @Override // w2.q
    public void i(float f10) {
        int i7 = this.f23175f;
    }

    @Override // w2.q
    public final int l(int i7) {
        for (int i10 = 0; i10 < this.f23173b; i10++) {
            if (this.c[i10] == i7) {
                return i10;
            }
        }
        return -1;
    }

    @Override // w2.q
    public final int length() {
        return this.c.length;
    }

    public final void o() {
    }

    public final void q() {
    }

    public final void s(float f10) {
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C2588b(g0 g0Var, int i7) {
        this(g0Var, new int[]{i7}, (byte) 0);
        this.f23175f = 1;
    }

    public C2588b(g0 g0Var, int[] iArr, byte b10) {
        C1208p[] c1208pArr;
        AbstractC1346c.j(iArr.length > 0);
        g0Var.getClass();
        this.f23172a = g0Var;
        int length = iArr.length;
        this.f23173b = length;
        this.d = new C1208p[length];
        int i7 = 0;
        while (true) {
            int length2 = iArr.length;
            c1208pArr = g0Var.d;
            if (i7 >= length2) {
                break;
            }
            this.d[i7] = c1208pArr[iArr[i7]];
            i7++;
        }
        Arrays.sort(this.d, new C1.b(13));
        this.c = new int[this.f23173b];
        int i10 = 0;
        while (true) {
            int i11 = this.f23173b;
            if (i10 < i11) {
                int[] iArr2 = this.c;
                C1208p c1208p = this.d[i10];
                int i12 = 0;
                while (true) {
                    if (i12 >= c1208pArr.length) {
                        i12 = -1;
                        break;
                    } else if (c1208p == c1208pArr[i12]) {
                        break;
                    } else {
                        i12++;
                    }
                }
                iArr2[i10] = i12;
                i10++;
            } else {
                long[] jArr = new long[i11];
                return;
            }
        }
    }
}
