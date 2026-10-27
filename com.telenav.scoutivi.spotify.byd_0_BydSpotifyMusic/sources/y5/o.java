package y5;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: StringNumberConversions.kt */
/* JADX INFO: loaded from: classes.dex */
public class o extends n {
    public static Integer i(String str) {
        kotlin.jvm.internal.l.e(str, "<this>");
        return j(str, 10);
    }

    public static final Integer j(String str, int i7) {
        boolean z6;
        int i8;
        kotlin.jvm.internal.l.e(str, "<this>");
        b.a(i7);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i9 = 0;
        char cCharAt = str.charAt(0);
        int i10 = -2147483647;
        int i11 = 1;
        if (kotlin.jvm.internal.l.g(cCharAt, 48) >= 0) {
            z6 = false;
            i11 = 0;
        } else {
            if (length == 1) {
                return null;
            }
            if (cCharAt == '-') {
                i10 = Integer.MIN_VALUE;
                z6 = true;
            } else {
                if (cCharAt != '+') {
                    return null;
                }
                z6 = false;
            }
        }
        int i12 = -59652323;
        while (i11 < length) {
            int iB = b.b(str.charAt(i11), i7);
            if (iB < 0) {
                return null;
            }
            if ((i9 < i12 && (i12 != -59652323 || i9 < (i12 = i10 / i7))) || (i8 = i9 * i7) < i10 + iB) {
                return null;
            }
            i9 = i8 - iB;
            i11++;
        }
        return z6 ? Integer.valueOf(i9) : Integer.valueOf(-i9);
    }

    public static Long k(String str) {
        kotlin.jvm.internal.l.e(str, "<this>");
        return l(str, 10);
    }

    public static final Long l(String str, int i7) {
        kotlin.jvm.internal.l.e(str, "<this>");
        b.a(i7);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i8 = 0;
        char cCharAt = str.charAt(0);
        long j7 = -9223372036854775807L;
        boolean z6 = true;
        if (kotlin.jvm.internal.l.g(cCharAt, 48) >= 0) {
            z6 = false;
        } else {
            if (length == 1) {
                return null;
            }
            if (cCharAt == '-') {
                j7 = Long.MIN_VALUE;
                i8 = 1;
            } else {
                if (cCharAt != '+') {
                    return null;
                }
                z6 = false;
                i8 = 1;
            }
        }
        long j8 = -256204778801521550L;
        long j9 = 0;
        long j10 = -256204778801521550L;
        while (i8 < length) {
            int iB = b.b(str.charAt(i8), i7);
            if (iB < 0) {
                return null;
            }
            if (j9 < j10) {
                if (j10 == j8) {
                    j10 = j7 / ((long) i7);
                    if (j9 < j10) {
                    }
                }
                return null;
            }
            long j11 = j9 * ((long) i7);
            long j12 = iB;
            if (j11 < j7 + j12) {
                return null;
            }
            j9 = j11 - j12;
            i8++;
            j8 = -256204778801521550L;
        }
        return z6 ? Long.valueOf(j9) : Long.valueOf(-j9);
    }
}
