package spi;

import java.io.EOFException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class i2 {

    @NotNull
    public static final byte[] a;

    static {
        Intrinsics.checkNotNullParameter("0123456789abcdef", "$this$asUtf8ToByteArray");
        byte[] bytes = "0123456789abcdef".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        a = bytes;
    }

    @NotNull
    public static final String a(@NotNull g2 readUtf8Line, long j) throws EOFException {
        Intrinsics.checkNotNullParameter(readUtf8Line, "$this$readUtf8Line");
        if (j > 0) {
            long j2 = j - 1;
            if (readUtf8Line.F(j2) == ((byte) 13)) {
                String strM = readUtf8Line.M(j2);
                readUtf8Line.skip(2L);
                return strM;
            }
        }
        String strM2 = readUtf8Line.M(j);
        readUtf8Line.skip(1L);
        return strM2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
    
        if (r19 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005d, code lost:
    
        return -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005e, code lost:
    
        return r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int b(@org.jetbrains.annotations.NotNull spi.g2 r17, @org.jetbrains.annotations.NotNull spi.kk r18, boolean r19) {
        /*
            r0 = r17
            r1 = r18
            java.lang.String r2 = "$this$selectPrefix"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            java.lang.String r2 = "options"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            spi.vo r0 = r0.a
            r2 = -1
            r3 = -2
            if (r0 == 0) goto Laa
            byte[] r4 = r0.a
            int r5 = r0.b
            int r6 = r0.c
            int[] r1 = r1.b
            r7 = 0
            r9 = r0
            r10 = r2
            r8 = r7
        L20:
            int r11 = r8 + 1
            r8 = r1[r8]
            int r12 = r11 + 1
            r11 = r1[r11]
            if (r11 == r2) goto L2b
            r10 = r11
        L2b:
            if (r9 != 0) goto L2e
            goto L5b
        L2e:
            r11 = 0
            if (r8 >= 0) goto L7b
            int r8 = r8 * (-1)
            int r13 = r8 + r12
        L35:
            int r8 = r5 + 1
            r5 = r4[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r14 = r12 + 1
            r12 = r1[r12]
            if (r5 == r12) goto L42
            return r10
        L42:
            if (r14 != r13) goto L46
            r5 = 1
            goto L47
        L46:
            r5 = r7
        L47:
            if (r8 != r6) goto L68
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            spi.vo r4 = r9.f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            int r6 = r4.b
            byte[] r8 = r4.a
            int r9 = r4.c
            if (r4 != r0) goto L62
            if (r5 != 0) goto L5f
        L5b:
            if (r19 == 0) goto L5e
            return r3
        L5e:
            return r10
        L5f:
            r4 = r8
            r8 = r11
            goto L6e
        L62:
            r16 = r8
            r8 = r4
            r4 = r16
            goto L6e
        L68:
            r16 = r9
            r9 = r6
            r6 = r8
            r8 = r16
        L6e:
            if (r5 == 0) goto L76
            r5 = r1[r14]
            r13 = r6
            r6 = r9
            r9 = r8
            goto La0
        L76:
            r5 = r6
            r6 = r9
            r12 = r14
            r9 = r8
            goto L35
        L7b:
            int r13 = r5 + 1
            r5 = r4[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r14 = r12 + r8
        L83:
            if (r12 != r14) goto L86
            return r10
        L86:
            r15 = r1[r12]
            if (r5 != r15) goto La7
            int r12 = r12 + r8
            r5 = r1[r12]
            if (r13 != r6) goto La0
            spi.vo r9 = r9.f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            int r4 = r9.b
            byte[] r6 = r9.a
            int r8 = r9.c
            r13 = r4
            r4 = r6
            r6 = r8
            if (r9 != r0) goto La0
            r9 = r11
        La0:
            if (r5 < 0) goto La3
            return r5
        La3:
            int r8 = -r5
            r5 = r13
            goto L20
        La7:
            int r12 = r12 + 1
            goto L83
        Laa:
            if (r19 == 0) goto Lad
            r2 = r3
        Lad:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.i2.b(spi.g2, spi.kk, boolean):int");
    }
}
