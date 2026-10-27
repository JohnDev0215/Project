package v6;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/* JADX INFO: compiled from: MatrixUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[][] f8592a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[][] f8593b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[][] f8594c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[][] f8595d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    public static void a(r6.a aVar, ErrorCorrectionLevel errorCorrectionLevel, u6.a aVar2, int i8, b bVar) throws WriterException {
        c(bVar);
        d(aVar2, bVar);
        l(errorCorrectionLevel, i8, bVar);
        s(aVar2, bVar);
        f(aVar, i8, bVar);
    }

    public static int b(int i8, int i9) {
        if (i9 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int iN = n(i9);
        int iN2 = i8 << (iN - 1);
        while (n(iN2) >= iN) {
            iN2 ^= i9 << (n(iN2) - iN);
        }
        return iN2;
    }

    public static void c(b bVar) {
        bVar.a((byte) -1);
    }

    public static void d(u6.a aVar, b bVar) throws WriterException {
        j(bVar);
        e(bVar);
        r(aVar, bVar);
        k(bVar);
    }

    public static void e(b bVar) throws WriterException {
        if (bVar.b(8, bVar.d() - 8) == 0) {
            throw new WriterException();
        }
        bVar.f(8, bVar.d() - 8, 1);
    }

    public static void f(r6.a aVar, int i8, b bVar) throws WriterException {
        boolean zF;
        int iE = bVar.e() - 1;
        int iD = bVar.d() - 1;
        int i9 = -1;
        int i10 = 0;
        while (iE > 0) {
            if (iE == 6) {
                iE--;
            }
            while (iD >= 0 && iD < bVar.d()) {
                for (int i11 = 0; i11 < 2; i11++) {
                    int i12 = iE - i11;
                    if (o(bVar.b(i12, iD))) {
                        if (i10 < aVar.g()) {
                            zF = aVar.f(i10);
                            i10++;
                        } else {
                            zF = false;
                        }
                        if (i8 != -1 && d.f(i8, i12, iD)) {
                            zF = !zF;
                        }
                        bVar.g(i12, iD, zF);
                    }
                }
                iD += i9;
            }
            i9 = -i9;
            iD += i9;
            iE -= 2;
        }
        if (i10 == aVar.g()) {
            return;
        }
        throw new WriterException("Not all bits consumed: " + i10 + '/' + aVar.g());
    }

    public static void g(int i8, int i9, b bVar) throws WriterException {
        for (int i10 = 0; i10 < 8; i10++) {
            int i11 = i8 + i10;
            if (!o(bVar.b(i11, i9))) {
                throw new WriterException();
            }
            bVar.f(i11, i9, 0);
        }
    }

    public static void h(int i8, int i9, b bVar) {
        for (int i10 = 0; i10 < 5; i10++) {
            int[] iArr = f8593b[i10];
            for (int i11 = 0; i11 < 5; i11++) {
                bVar.f(i8 + i11, i9 + i10, iArr[i11]);
            }
        }
    }

    public static void i(int i8, int i9, b bVar) {
        for (int i10 = 0; i10 < 7; i10++) {
            int[] iArr = f8592a[i10];
            for (int i11 = 0; i11 < 7; i11++) {
                bVar.f(i8 + i11, i9 + i10, iArr[i11]);
            }
        }
    }

    public static void j(b bVar) throws WriterException {
        int length = f8592a[0].length;
        i(0, 0, bVar);
        i(bVar.e() - length, 0, bVar);
        i(0, bVar.e() - length, bVar);
        g(0, 7, bVar);
        g(bVar.e() - 8, 7, bVar);
        g(0, bVar.e() - 8, bVar);
        m(7, 0, bVar);
        m((bVar.d() - 7) - 1, 0, bVar);
        m(7, bVar.d() - 7, bVar);
    }

    public static void k(b bVar) {
        int i8 = 8;
        while (i8 < bVar.e() - 8) {
            int i9 = i8 + 1;
            int i10 = i9 % 2;
            if (o(bVar.b(i8, 6))) {
                bVar.f(i8, 6, i10);
            }
            if (o(bVar.b(6, i8))) {
                bVar.f(6, i8, i10);
            }
            i8 = i9;
        }
    }

    public static void l(ErrorCorrectionLevel errorCorrectionLevel, int i8, b bVar) throws WriterException {
        int iD;
        r6.a aVar = new r6.a();
        p(errorCorrectionLevel, i8, aVar);
        for (int i9 = 0; i9 < aVar.g(); i9++) {
            boolean zF = aVar.f((aVar.g() - 1) - i9);
            int[] iArr = f8595d[i9];
            bVar.g(iArr[0], iArr[1], zF);
            int iE = 8;
            if (i9 < 8) {
                iD = 8;
                iE = (bVar.e() - i9) - 1;
            } else {
                iD = (bVar.d() - 7) + (i9 - 8);
            }
            bVar.g(iE, iD, zF);
        }
    }

    public static void m(int i8, int i9, b bVar) throws WriterException {
        for (int i10 = 0; i10 < 7; i10++) {
            int i11 = i9 + i10;
            if (!o(bVar.b(i8, i11))) {
                throw new WriterException();
            }
            bVar.f(i8, i11, 0);
        }
    }

    public static int n(int i8) {
        return 32 - Integer.numberOfLeadingZeros(i8);
    }

    public static boolean o(int i8) {
        return i8 == -1;
    }

    public static void p(ErrorCorrectionLevel errorCorrectionLevel, int i8, r6.a aVar) throws WriterException {
        if (!f.b(i8)) {
            throw new WriterException("Invalid mask pattern");
        }
        int bits = (errorCorrectionLevel.getBits() << 3) | i8;
        aVar.c(bits, 5);
        aVar.c(b(bits, 1335), 10);
        r6.a aVar2 = new r6.a();
        aVar2.c(21522, 15);
        aVar.k(aVar2);
        if (aVar.g() == 15) {
            return;
        }
        throw new WriterException("should not happen but we got: " + aVar.g());
    }

    public static void q(u6.a aVar, r6.a aVar2) throws WriterException {
        aVar2.c(aVar.f(), 6);
        aVar2.c(b(aVar.f(), 7973), 12);
        if (aVar2.g() == 18) {
            return;
        }
        throw new WriterException("should not happen but we got: " + aVar2.g());
    }

    public static void r(u6.a aVar, b bVar) {
        if (aVar.f() < 2) {
            return;
        }
        int[] iArr = f8594c[aVar.f() - 1];
        for (int i8 : iArr) {
            if (i8 >= 0) {
                for (int i9 : iArr) {
                    if (i9 >= 0 && o(bVar.b(i9, i8))) {
                        h(i9 - 2, i8 - 2, bVar);
                    }
                }
            }
        }
    }

    public static void s(u6.a aVar, b bVar) throws WriterException {
        if (aVar.f() < 7) {
            return;
        }
        r6.a aVar2 = new r6.a();
        q(aVar, aVar2);
        int i8 = 17;
        for (int i9 = 0; i9 < 6; i9++) {
            for (int i10 = 0; i10 < 3; i10++) {
                boolean zF = aVar2.f(i8);
                i8--;
                bVar.g(i9, (bVar.d() - 11) + i10, zF);
                bVar.g((bVar.d() - 11) + i10, i9, zF);
            }
        }
    }
}
