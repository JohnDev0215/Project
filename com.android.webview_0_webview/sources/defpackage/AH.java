package defpackage;

import java.util.UUID;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AH {
    public static final char[] d = "0123456789ABCDEF".toCharArray();
    public final byte[] a;
    public byte[] b;
    public byte[] c;

    public AH(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.a = bArr;
        this.b = bArr2;
        this.c = null;
    }

    public static AH a(byte[] bArr) {
        return new AH(AbstractC1623us.b(UUID.randomUUID().toString().replace('-', '0')), bArr, null);
    }

    public static AH b(byte[] bArr) {
        return new AH(bArr, bArr, null);
    }

    public static String d(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(d[bArr[i] >>> 4]);
            sb.append(d[bArr[i] & 15]);
        }
        return sb.toString();
    }

    public String c() {
        return d(this.a);
    }

    public AH(byte[] bArr, byte[] bArr2, byte[] bArr3, C1869zH c1869zH) {
        this.a = bArr;
        this.b = null;
        this.c = bArr3;
    }
}
