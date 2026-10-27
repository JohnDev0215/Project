package q6;

import kotlin.jvm.internal.l;
import p6.e;
import p6.h;

/* JADX INFO: compiled from: ByteString.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f9170a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x006b, code lost:
    
        return -1;
     */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int c(byte[] r19, int r20) {
        /*
            Method dump skipped, instruction units count: 495
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q6.b.c(byte[], int):int");
    }

    public static final void d(h commonWrite, e buffer, int i7, int i8) {
        l.e(commonWrite, "$this$commonWrite");
        l.e(buffer, "buffer");
        buffer.e(commonWrite.e(), i7, i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int e(char c7) {
        if ('0' <= c7 && '9' >= c7) {
            return c7 - '0';
        }
        char c8 = 'a';
        if ('a' > c7 || 'f' < c7) {
            c8 = 'A';
            if ('A' > c7 || 'F' < c7) {
                throw new IllegalArgumentException("Unexpected hex digit: " + c7);
            }
        }
        return (c7 - c8) + 10;
    }

    public static final char[] f() {
        return f9170a;
    }
}
