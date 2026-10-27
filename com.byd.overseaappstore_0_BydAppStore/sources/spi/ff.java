package spi;

import java.io.EOFException;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
@JvmName(name = "HttpHeaders")
public final class ff {
    public static final ByteString a;
    public static final ByteString b;

    static {
        ByteString.Companion companion = ByteString.INSTANCE;
        a = companion.d("\"\\");
        b = companion.d("\t ,=");
    }

    public static final boolean a(@NotNull ln promisesBody) {
        Intrinsics.checkNotNullParameter(promisesBody, "$this$promisesBody");
        if (Intrinsics.areEqual(promisesBody.b.c, "HEAD")) {
            return false;
        }
        int i = promisesBody.e;
        return (((i >= 100 && i < 200) || i == 204 || i == 304) && uu.j(promisesBody) == -1 && !StringsKt.h("chunked", ln.B(promisesBody, "Transfer-Encoding"))) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0086, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0086, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(spi.g2 r18, java.util.List<spi.o3> r19) throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.ff.b(spi.g2, java.util.List):void");
    }

    public static final String c(g2 g2Var) {
        long jH = g2Var.H(b);
        if (jH == -1) {
            jH = g2Var.b;
        }
        if (jH != 0) {
            return g2Var.M(jH);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:108:0x0207, code lost:
    
        if (okhttp3.internal.publicsuffix.PublicSuffixDatabase.g.a(r15) == null) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void d(@org.jetbrains.annotations.NotNull spi.q4 r40, @org.jetbrains.annotations.NotNull spi.hf r41, @org.jetbrains.annotations.NotNull spi.ke r42) {
        /*
            Method dump skipped, instruction units count: 641
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.ff.d(spi.q4, spi.hf, spi.ke):void");
    }

    public static final boolean e(g2 g2Var) throws EOFException {
        boolean z = false;
        while (!g2Var.h()) {
            byte bF = g2Var.F(0L);
            if (bF == 9 || bF == 32) {
                g2Var.readByte();
            } else {
                if (bF != 44) {
                    break;
                }
                g2Var.readByte();
                z = true;
            }
        }
        return z;
    }
}
