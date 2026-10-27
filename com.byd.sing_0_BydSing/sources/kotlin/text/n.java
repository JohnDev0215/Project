package kotlin.text;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: StringNumberConversions.kt */
/* JADX INFO: loaded from: classes.dex */
public class n extends m {
    public static Integer a(String str) {
        kotlin.jvm.internal.h.d(str, "$this$toIntOrNull");
        return b(str, 10);
    }

    public static final Integer b(String str, int i) {
        boolean z;
        int i2;
        kotlin.jvm.internal.h.d(str, "$this$toIntOrNull");
        b.a(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i3 = 0;
        char cCharAt = str.charAt(0);
        int i4 = -2147483647;
        int i5 = 1;
        if (kotlin.jvm.internal.h.e(cCharAt, 48) >= 0) {
            z = false;
            i5 = 0;
        } else {
            if (length == 1) {
                return null;
            }
            if (cCharAt == '-') {
                i4 = Integer.MIN_VALUE;
                z = true;
            } else {
                if (cCharAt != '+') {
                    return null;
                }
                z = false;
            }
        }
        int i6 = -59652323;
        while (i5 < length) {
            int iB = b.b(str.charAt(i5), i);
            if (iB < 0) {
                return null;
            }
            if ((i3 < i6 && (i6 != -59652323 || i3 < (i6 = i4 / i))) || (i2 = i3 * i) < i4 + iB) {
                return null;
            }
            i3 = i2 - iB;
            i5++;
        }
        return z ? Integer.valueOf(i3) : Integer.valueOf(-i3);
    }
}
