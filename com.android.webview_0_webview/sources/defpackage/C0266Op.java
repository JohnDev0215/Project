package defpackage;

import java.security.PrivateKey;
import java.util.Arrays;

/* JADX INFO: renamed from: Op, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0266Op {
    public PrivateKey a;
    public byte[][] b;

    public C0266Op(PrivateKey privateKey, byte[][] bArr) {
        this.a = privateKey;
        byte[][] bArr2 = new byte[bArr.length][];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = Arrays.copyOf(bArr[i], bArr[i].length);
        }
        this.b = bArr2;
    }
}
