package defpackage;

import J.N;
import java.security.PrivateKey;
import org.chromium.android_webview.AwContentsClientBridge;

/* JADX INFO: renamed from: no, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1235no {
    public final int a;
    public final String b;
    public final int c;
    public boolean d;
    public final /* synthetic */ AwContentsClientBridge e;

    public C1235no(AwContentsClientBridge awContentsClientBridge, int i, String str, int i2) {
        this.e = awContentsClientBridge;
        this.a = i;
        this.b = str;
        this.c = i2;
    }

    public final void a() {
        if (this.d) {
            throw new IllegalStateException("The callback was already called.");
        }
        this.d = true;
    }

    public final void b(PrivateKey privateKey, byte[][] bArr) {
        AwContentsClientBridge awContentsClientBridge = this.e;
        long j = awContentsClientBridge.c;
        if (j == 0) {
            return;
        }
        N.MZT_Y79r(j, awContentsClientBridge, this.a, bArr, privateKey);
    }
}
