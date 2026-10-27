package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: renamed from: mK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1158mK {
    public static byte[] a(C1213nK c1213nK) {
        if (c1213nK.a == 0) {
            return c1213nK.b;
        }
        C1268oK c1268oK = c1213nK.c;
        ByteBuffer byteBufferD = c1268oK.b.d(0L, c1268oK.c, ZJ.c);
        byte[] bArr = new byte[c1268oK.c];
        byteBufferD.get(bArr);
        return bArr;
    }
}
