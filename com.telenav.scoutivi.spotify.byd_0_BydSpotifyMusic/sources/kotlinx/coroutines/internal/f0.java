package kotlinx.coroutines.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: SystemProps.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f0 {
    public static final int a(String str, int i7, int i8, int i9) {
        return (int) d0.c(str, i7, i8, i9);
    }

    public static final long b(String str, long j7, long j8, long j9) {
        String strD = d0.d(str);
        if (strD == null) {
            return j7;
        }
        Long lK = y5.o.k(strD);
        if (lK == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + strD + '\'').toString());
        }
        long jLongValue = lK.longValue();
        if (j8 <= jLongValue && j9 >= jLongValue) {
            return jLongValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j8 + ".." + j9 + ", but is '" + jLongValue + '\'').toString());
    }

    public static final boolean c(String str, boolean z6) {
        String strD = d0.d(str);
        return strD != null ? Boolean.parseBoolean(strD) : z6;
    }

    public static /* synthetic */ int d(String str, int i7, int i8, int i9, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            i8 = 1;
        }
        if ((i10 & 8) != 0) {
            i9 = Integer.MAX_VALUE;
        }
        return d0.b(str, i7, i8, i9);
    }

    public static /* synthetic */ long e(String str, long j7, long j8, long j9, int i7, Object obj) {
        if ((i7 & 4) != 0) {
            j8 = 1;
        }
        long j10 = j8;
        if ((i7 & 8) != 0) {
            j9 = Long.MAX_VALUE;
        }
        return d0.c(str, j7, j10, j9);
    }
}
