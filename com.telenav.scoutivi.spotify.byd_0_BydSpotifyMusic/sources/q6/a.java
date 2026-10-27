package q6;

import java.io.EOFException;
import kotlin.jvm.internal.l;
import p6.e;

/* JADX INFO: compiled from: Buffer.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final byte[] f9169a = p6.b.a("0123456789abcdef");

    public static final byte[] a() {
        return f9169a;
    }

    public static final String b(e readUtf8Line, long j7) throws EOFException {
        l.e(readUtf8Line, "$this$readUtf8Line");
        if (j7 > 0) {
            long j8 = j7 - 1;
            if (readUtf8Line.N(j8) == ((byte) 13)) {
                String strH0 = readUtf8Line.h0(j8);
                readUtf8Line.a(2L);
                return strH0;
            }
        }
        String strH02 = readUtf8Line.h0(j7);
        readUtf8Line.a(1L);
        return strH02;
    }
}
