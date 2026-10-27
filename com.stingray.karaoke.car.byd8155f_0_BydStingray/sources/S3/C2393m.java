package s3;

import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: s3.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2393m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f22320a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22321b;
    public int c;
    public int d;

    public C2393m() {
        int iHighestOneBit = Integer.bitCount(8) != 1 ? Integer.highestOneBit(7) << 1 : 8;
        this.d = iHighestOneBit - 1;
        this.f22320a = new int[iHighestOneBit];
    }

    public void a(int i7) {
        int[] iArr = this.f22320a;
        int i10 = this.c;
        iArr[i10] = i7;
        int i11 = this.d & (i10 + 1);
        this.c = i11;
        int i12 = this.f22321b;
        if (i11 == i12) {
            int length = iArr.length;
            int i13 = length - i12;
            int i14 = length << 1;
            if (i14 < 0) {
                throw new RuntimeException("Max array capacity exceeded");
            }
            int[] iArr2 = new int[i14];
            AbstractC1996k.C(0, i12, length, iArr, iArr2);
            AbstractC1996k.C(i13, 0, this.f22321b, this.f22320a, iArr2);
            this.f22320a = iArr2;
            this.f22321b = 0;
            this.c = length;
            this.d = i14 - 1;
        }
    }

    public void b(int i7, int i10) {
        if (i7 < 0) {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        }
        if (i10 < 0) {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
        int i11 = this.d;
        int i12 = i11 * 2;
        int[] iArr = this.f22320a;
        if (iArr == null) {
            int[] iArr2 = new int[4];
            this.f22320a = iArr2;
            Arrays.fill(iArr2, -1);
        } else if (i12 >= iArr.length) {
            int[] iArr3 = new int[i11 * 4];
            this.f22320a = iArr3;
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        }
        int[] iArr4 = this.f22320a;
        iArr4[i12] = i7;
        iArr4[i12 + 1] = i10;
        this.d++;
    }

    public void c(RecyclerView recyclerView, boolean z6) {
        this.d = 0;
        int[] iArr = this.f22320a;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        AbstractC2367F abstractC2367F = recyclerView.f12157x;
        if (recyclerView.f12155w == null || abstractC2367F == null || !abstractC2367F.f22185i) {
            return;
        }
        if (z6) {
            if (!recyclerView.f12141p.E()) {
                abstractC2367F.i(recyclerView.f12155w.a(), this);
            }
        } else if (!recyclerView.I()) {
            abstractC2367F.h(this.f22321b, this.c, recyclerView.f12140o0, this);
        }
        int i7 = this.d;
        if (i7 > abstractC2367F.f22186j) {
            abstractC2367F.f22186j = i7;
            abstractC2367F.f22187k = z6;
            recyclerView.f12138n.m();
        }
    }
}
