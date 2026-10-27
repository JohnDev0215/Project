package u2;

import java.util.Arrays;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Random f22803a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f22804b;
    public final int[] c;

    public c0() {
        this(new Random());
    }

    public final c0 a(int i7, int i10) {
        int[] iArr;
        Random random;
        int[] iArr2 = new int[i10];
        int[] iArr3 = new int[i10];
        int i11 = 0;
        while (true) {
            iArr = this.f22804b;
            random = this.f22803a;
            if (i11 >= i10) {
                break;
            }
            iArr2[i11] = random.nextInt(iArr.length + 1);
            int i12 = i11 + 1;
            int iNextInt = random.nextInt(i12);
            iArr3[i11] = iArr3[iNextInt];
            iArr3[iNextInt] = i11 + i7;
            i11 = i12;
        }
        Arrays.sort(iArr2);
        int[] iArr4 = new int[iArr.length + i10];
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < iArr.length + i10; i15++) {
            if (i13 >= i10 || i14 != iArr2[i13]) {
                int i16 = i14 + 1;
                int i17 = iArr[i14];
                iArr4[i15] = i17;
                if (i17 >= i7) {
                    iArr4[i15] = i17 + i10;
                }
                i14 = i16;
            } else {
                iArr4[i15] = iArr3[i13];
                i13++;
            }
        }
        return new c0(iArr4, new Random(random.nextLong()));
    }

    public c0(int[] iArr, Random random) {
        this.f22804b = iArr;
        this.f22803a = random;
        this.c = new int[iArr.length];
        for (int i7 = 0; i7 < iArr.length; i7++) {
            this.c[iArr[i7]] = i7;
        }
    }

    public c0(Random random) {
        this(new int[0], random);
    }
}
