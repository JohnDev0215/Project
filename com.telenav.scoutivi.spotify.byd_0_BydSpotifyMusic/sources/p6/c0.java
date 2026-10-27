package p6;

/* JADX INFO: compiled from: Utf8.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {
    public static final long a(String utf8Size, int i7, int i8) {
        int i9;
        kotlin.jvm.internal.l.e(utf8Size, "$this$utf8Size");
        if (!(i7 >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i7).toString());
        }
        if (!(i8 >= i7)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i8 + " < " + i7).toString());
        }
        if (!(i8 <= utf8Size.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i8 + " > " + utf8Size.length()).toString());
        }
        long j7 = 0;
        while (i7 < i8) {
            char cCharAt = utf8Size.charAt(i7);
            if (cCharAt < 128) {
                j7++;
            } else {
                if (cCharAt < 2048) {
                    i9 = 2;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    i9 = 3;
                } else {
                    int i10 = i7 + 1;
                    char cCharAt2 = i10 < i8 ? utf8Size.charAt(i10) : (char) 0;
                    if (cCharAt > 56319 || cCharAt2 < 56320 || cCharAt2 > 57343) {
                        j7++;
                        i7 = i10;
                    } else {
                        j7 += (long) 4;
                        i7 += 2;
                    }
                }
                j7 += (long) i9;
            }
            i7++;
        }
        return j7;
    }

    public static /* synthetic */ long b(String str, int i7, int i8, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            i7 = 0;
        }
        if ((i9 & 2) != 0) {
            i8 = str.length();
        }
        return a(str, i7, i8);
    }
}
