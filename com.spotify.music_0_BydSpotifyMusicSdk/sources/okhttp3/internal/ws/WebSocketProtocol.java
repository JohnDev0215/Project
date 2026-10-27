package okhttp3.internal.ws;

import p.eo;
import p.fo;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class WebSocketProtocol {
    public static final WebSocketProtocol a = new WebSocketProtocol();

    private WebSocketProtocol() {
    }

    public static String a(int i) {
        if (i < 1000 || i >= 5000) {
            return u4.e("Code must be in range [1000,5000): ", i);
        }
        if ((1004 > i || i >= 1007) && (1015 > i || i >= 3000)) {
            return null;
        }
        return u4.f("Code ", i, " is reserved and may not be used.");
    }

    public static void b(eo eoVar, byte[] bArr) {
        long j;
        v03.j(eoVar, "cursor");
        v03.j(bArr, "key");
        int length = bArr.length;
        int i = 0;
        do {
            byte[] bArr2 = eoVar.m;
            int i2 = eoVar.n;
            int i3 = eoVar.o;
            if (bArr2 != null) {
                while (i2 < i3) {
                    int i4 = i % length;
                    bArr2[i2] = (byte) (bArr2[i2] ^ bArr[i4]);
                    i2++;
                    i = i4 + 1;
                }
            }
            long j2 = eoVar.l;
            fo foVar = eoVar.c;
            v03.h(foVar);
            if (j2 == foVar.e) {
                throw new IllegalStateException("no more bytes".toString());
            }
            j = eoVar.l;
        } while (eoVar.c(j == -1 ? 0L : j + ((long) (eoVar.o - eoVar.n))) != -1);
    }
}
