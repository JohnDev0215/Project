package defpackage;

import defpackage.rv1;
import java.util.ArrayList;
import java.util.Comparator;

/* JADX INFO: compiled from: ColorCutQuantizer.java */
/* JADX INFO: loaded from: classes.dex */
public final class uu {
    public static final a f = new a();
    public final int[] a;
    public final int[] b;
    public final ArrayList c;
    public final rv1.c[] d;
    public final float[] e = new float[3];

    /* JADX INFO: compiled from: ColorCutQuantizer.java */
    public class a implements Comparator<b> {
        @Override // java.util.Comparator
        public final int compare(b bVar, b bVar2) {
            b bVar3 = bVar;
            b bVar4 = bVar2;
            return (((bVar4.i - bVar4.h) + 1) * (((bVar4.g - bVar4.f) + 1) * ((bVar4.e - bVar4.d) + 1))) - (((bVar3.i - bVar3.h) + 1) * (((bVar3.g - bVar3.f) + 1) * ((bVar3.e - bVar3.d) + 1)));
        }
    }

    /* JADX INFO: compiled from: ColorCutQuantizer.java */
    public class b {
        public final int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;

        public b(int i, int i2) {
            this.a = i;
            this.b = i2;
            a();
        }

        public final void a() {
            uu uuVar = uu.this;
            int[] iArr = uuVar.a;
            int[] iArr2 = uuVar.b;
            int i = Integer.MAX_VALUE;
            int i2 = Integer.MIN_VALUE;
            int i3 = Integer.MIN_VALUE;
            int i4 = 0;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MIN_VALUE;
            for (int i8 = this.a; i8 <= this.b; i8++) {
                int i9 = iArr[i8];
                i4 += iArr2[i9];
                int i10 = (i9 >> 10) & 31;
                int i11 = (i9 >> 5) & 31;
                int i12 = i9 & 31;
                if (i10 > i7) {
                    i7 = i10;
                }
                if (i10 < i) {
                    i = i10;
                }
                if (i11 > i2) {
                    i2 = i11;
                }
                if (i11 < i5) {
                    i5 = i11;
                }
                if (i12 > i3) {
                    i3 = i12;
                }
                if (i12 < i6) {
                    i6 = i12;
                }
            }
            this.d = i;
            this.e = i7;
            this.f = i5;
            this.g = i2;
            this.h = i6;
            this.i = i3;
            this.c = i4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public uu(int[] r18, int r19, rv1.c[] r20) {
        /*
            Method dump skipped, instruction units count: 554
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uu.<init>(int[], int, rv1$c[]):void");
    }

    public static void a(int[] iArr, int i, int i2, int i3) {
        if (i == -2) {
            while (i2 <= i3) {
                int i4 = iArr[i2];
                iArr[i2] = (i4 & 31) | (((i4 >> 5) & 31) << 10) | (((i4 >> 10) & 31) << 5);
                i2++;
            }
            return;
        }
        if (i != -1) {
            return;
        }
        while (i2 <= i3) {
            int i5 = iArr[i2];
            iArr[i2] = ((i5 >> 10) & 31) | ((i5 & 31) << 10) | (((i5 >> 5) & 31) << 5);
            i2++;
        }
    }

    public static int b(int i, int i2, int i3) {
        return (i3 > i2 ? i << (i3 - i2) : i >> (i2 - i3)) & ((1 << i3) - 1);
    }
}
