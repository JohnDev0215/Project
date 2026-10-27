package T;

import java.util.ArrayList;
import u.C2476p;

/* JADX INFO: renamed from: T.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0742g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f8252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8253b;
    public int c;
    public final ArrayList d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2476p f8254e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m7.n f8255f;

    public C0742g0(int i7, ArrayList arrayList) {
        this.f8252a = arrayList;
        this.f8253b = i7;
        if (!(i7 >= 0)) {
            C0735d.X("Invalid start index");
            throw null;
        }
        this.d = new ArrayList();
        C2476p c2476p = new C2476p();
        int size = arrayList.size();
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            N n10 = (N) this.f8252a.get(i11);
            int i12 = n10.c;
            int i13 = n10.d;
            c2476p.i(i12, new I(i11, i10, i13));
            i10 += i13;
        }
        this.f8254e = c2476p;
        this.f8255f = h6.r.o(new K7.K(this, 17));
    }

    public final boolean a(int i7, int i10) {
        int i11;
        C2476p c2476p = this.f8254e;
        I i12 = (I) c2476p.f(i7);
        if (i12 == null) {
            return false;
        }
        int i13 = i12.f8188b;
        int i14 = i10 - i12.c;
        i12.c = i10;
        if (i14 == 0) {
            return true;
        }
        Object[] objArr = c2476p.c;
        long[] jArr = c2476p.f22615a;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i15 = 0;
        while (true) {
            long j3 = jArr[i15];
            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i16 = 8 - ((~(i15 - length)) >>> 31);
                for (int i17 = 0; i17 < i16; i17++) {
                    if ((255 & j3) < 128) {
                        I i18 = (I) objArr[(i15 << 3) + i17];
                        if (i18.f8188b >= i13 && !i18.equals(i12) && (i11 = i18.f8188b + i14) >= 0) {
                            i18.f8188b = i11;
                        }
                    }
                    j3 >>= 8;
                }
                if (i16 != 8) {
                    return true;
                }
            }
            if (i15 == length) {
                return true;
            }
            i15++;
        }
    }
}
