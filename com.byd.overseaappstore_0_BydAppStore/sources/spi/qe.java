package spi;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class qe {
    public static final String[] d;
    public static final qe e = new qe();

    @JvmField
    @NotNull
    public static final ByteString a = ByteString.INSTANCE.d("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    public static final String[] b = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    public static final String[] c = new String[64];

    static {
        String[] strArr = new String[256];
        for (int i = 0; i < 256; i++) {
            String binaryString = Integer.toBinaryString(i);
            Intrinsics.checkNotNullExpressionValue(binaryString, "Integer.toBinaryString(it)");
            strArr[i] = StringsKt__StringsJVMKt.replace$default(uu.h("%8s", binaryString), ' ', '0', false, 4, (Object) null);
        }
        d = strArr;
        String[] strArr2 = c;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i2 = 0; i2 < 1; i2++) {
            int i3 = iArr[i2];
            String[] strArr3 = c;
            strArr3[i3 | 8] = Intrinsics.stringPlus(strArr3[i3], "|PADDED");
        }
        String[] strArr4 = c;
        strArr4[4] = "END_HEADERS";
        strArr4[32] = "PRIORITY";
        strArr4[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i4 = 0; i4 < 3; i4++) {
            int i5 = iArr2[i4];
            for (int i6 = 0; i6 < 1; i6++) {
                int i7 = iArr[i6];
                String[] strArr5 = c;
                int i8 = i7 | i5;
                strArr5[i8] = strArr5[i7] + "|" + strArr5[i5];
                StringBuilder sb = new StringBuilder();
                sb.append(strArr5[i7]);
                sb.append("|");
                strArr5[i8 | 8] = hi.f(sb, strArr5[i5], "|PADDED");
            }
        }
        int length = c.length;
        for (int i9 = 0; i9 < length; i9++) {
            String[] strArr6 = c;
            if (strArr6[i9] == null) {
                strArr6[i9] = d[i9];
            }
        }
    }

    private qe() {
    }

    @NotNull
    public final String a(int i) {
        String[] strArr = b;
        return i < strArr.length ? strArr[i] : uu.h("0x%02x", Integer.valueOf(i));
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0053  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String b(boolean r9, int r10, int r11, int r12, int r13) {
        /*
            r8 = this;
            java.lang.String r0 = r8.a(r12)
            r1 = 4
            r2 = 5
            r3 = 1
            r4 = 3
            r5 = 2
            if (r13 != 0) goto Le
            java.lang.String r12 = ""
            goto L57
        Le:
            if (r12 == r5) goto L53
            if (r12 == r4) goto L53
            if (r12 == r1) goto L49
            r6 = 6
            if (r12 == r6) goto L49
            r6 = 7
            if (r12 == r6) goto L53
            r6 = 8
            if (r12 == r6) goto L53
            java.lang.String[] r6 = spi.qe.c
            int r7 = r6.length
            if (r13 >= r7) goto L29
            r6 = r6[r13]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            goto L2d
        L29:
            java.lang.String[] r6 = spi.qe.d
            r6 = r6[r13]
        L2d:
            if (r12 != r2) goto L38
            r7 = r13 & 4
            if (r7 == 0) goto L38
            java.lang.String r12 = "HEADERS"
            java.lang.String r13 = "PUSH_PROMISE"
            goto L42
        L38:
            if (r12 != 0) goto L47
            r12 = r13 & 32
            if (r12 == 0) goto L47
            java.lang.String r12 = "PRIORITY"
            java.lang.String r13 = "COMPRESSED"
        L42:
            java.lang.String r12 = kotlin.text.StringsKt.s(r6, r12, r13)
            goto L57
        L47:
            r12 = r6
            goto L57
        L49:
            if (r13 != r3) goto L4e
            java.lang.String r12 = "ACK"
            goto L57
        L4e:
            java.lang.String[] r12 = spi.qe.d
            r12 = r12[r13]
            goto L57
        L53:
            java.lang.String[] r12 = spi.qe.d
            r12 = r12[r13]
        L57:
            if (r9 == 0) goto L5c
            java.lang.String r9 = "<<"
            goto L5e
        L5c:
            java.lang.String r9 = ">>"
        L5e:
            java.lang.Object[] r13 = new java.lang.Object[r2]
            r2 = 0
            r13[r2] = r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r10)
            r13[r3] = r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r11)
            r13[r5] = r9
            r13[r4] = r0
            r13[r1] = r12
            java.lang.String r9 = "%s 0x%08x %5d %-13s %s"
            java.lang.String r9 = spi.uu.h(r9, r13)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.qe.b(boolean, int, int, int, int):java.lang.String");
    }
}
