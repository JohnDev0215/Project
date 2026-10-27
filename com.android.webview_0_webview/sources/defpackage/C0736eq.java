package defpackage;

import J.N;
import org.chromium.android_webview.AwContentsClientBridge;
import org.chromium.base.task.PostTask;

/* JADX INFO: renamed from: eq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0736eq implements InterfaceC0792fq, InterfaceC0513aq {
    public AwContentsClientBridge a;
    public final int b;

    public C0736eq(AwContentsClientBridge awContentsClientBridge, int i) {
        this.a = awContentsClientBridge;
        this.b = i;
    }

    public void a() {
        PostTask.b(AbstractC0538bE.a, new Runnable(this) { // from class: dq
            public final C0736eq s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0736eq c0736eq = this.s;
                AwContentsClientBridge awContentsClientBridge = c0736eq.a;
                if (awContentsClientBridge != null) {
                    int i = c0736eq.b;
                    long j = awContentsClientBridge.c;
                    if (j != 0) {
                        N.MXWyhv9x(j, awContentsClientBridge, i);
                    }
                }
                c0736eq.a = null;
            }
        });
    }
}
