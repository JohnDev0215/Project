package g.a.e.d.a;

/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static int a(int i2) {
        int i3 = -1;
        while (i2 != 0) {
            i3++;
            i2 >>>= 1;
        }
        return i3;
    }

    public static int b(int i2, int i3) {
        while (true) {
            int i4 = i3;
            int i5 = i2;
            i2 = i4;
            if (i2 == 0) {
                return i5;
            }
            i3 = e(i5, i2);
        }
    }

    public static boolean c(int i2) {
        if (i2 == 0) {
            return false;
        }
        int iA = a(i2) >>> 1;
        int iD = 2;
        for (int i3 = 0; i3 < iA; i3++) {
            iD = d(iD, iD, i2);
            if (b(iD ^ 2, i2) != 1) {
                return false;
            }
        }
        return true;
    }

    public static int d(int i2, int i3, int i4) {
        int iE = e(i2, i4);
        int iE2 = e(i3, i4);
        int i5 = 0;
        if (iE2 != 0) {
            int iA = 1 << a(i4);
            while (iE != 0) {
                if (((byte) (iE & 1)) == 1) {
                    i5 ^= iE2;
                }
                iE >>>= 1;
                iE2 <<= 1;
                if (iE2 >= iA) {
                    iE2 ^= i4;
                }
            }
        }
        return i5;
    }

    public static int e(int i2, int i3) {
        if (i3 == 0) {
            System.err.println("Error: to be divided by 0");
            return 0;
        }
        while (a(i2) >= a(i3)) {
            i2 ^= i3 << (a(i2) - a(i3));
        }
        return i2;
    }
}
