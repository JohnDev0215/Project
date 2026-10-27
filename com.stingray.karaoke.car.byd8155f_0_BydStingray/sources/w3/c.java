package W3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f9760a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f9761b;

    public c(float[] fArr, int[] iArr) {
        this.f9760a = fArr;
        this.f9761b = iArr;
    }

    public final void a(c cVar) {
        int i7 = 0;
        while (true) {
            int[] iArr = cVar.f9761b;
            if (i7 >= iArr.length) {
                return;
            }
            this.f9760a[i7] = cVar.f9760a[i7];
            this.f9761b[i7] = iArr[i7];
            i7++;
        }
    }

    public final c b(float[] fArr) {
        int iA;
        int[] iArr = new int[fArr.length];
        for (int i7 = 0; i7 < fArr.length; i7++) {
            float f10 = fArr[i7];
            float[] fArr2 = this.f9760a;
            int iBinarySearch = Arrays.binarySearch(fArr2, f10);
            int[] iArr2 = this.f9761b;
            if (iBinarySearch >= 0) {
                iA = iArr2[iBinarySearch];
            } else {
                int i10 = -(iBinarySearch + 1);
                if (i10 == 0) {
                    iA = iArr2[0];
                } else if (i10 == iArr2.length - 1) {
                    iA = iArr2[iArr2.length - 1];
                } else {
                    int i11 = i10 - 1;
                    float f11 = fArr2[i11];
                    iA = com.bumptech.glide.d.A(iArr2[i11], iArr2[i10], (f10 - f11) / (fArr2[i10] - f11));
                }
            }
            iArr[i7] = iA;
        }
        return new c(fArr, iArr);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        return Arrays.equals(this.f9760a, cVar.f9760a) && Arrays.equals(this.f9761b, cVar.f9761b);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f9761b) + (Arrays.hashCode(this.f9760a) * 31);
    }
}
