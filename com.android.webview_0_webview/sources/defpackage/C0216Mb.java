package defpackage;

import J.N;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import java.util.Objects;
import org.chromium.android_webview.AwContentsClientBridge;
import org.chromium.base.task.PostTask;

/* JADX INFO: renamed from: Mb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0216Mb implements JsResult.ResultReceiver {
    public InterfaceC0513aq a;
    public InterfaceC0792fq b;
    public final JsPromptResult c = new JsPromptResult(this);

    public C0216Mb(InterfaceC0513aq interfaceC0513aq) {
        this.a = interfaceC0513aq;
    }

    public void onJsResultComplete(JsResult jsResult) {
        if (this.a == null) {
            if (!this.c.getResult()) {
                ((C0736eq) this.b).a();
                return;
            }
            final C0736eq c0736eq = (C0736eq) this.b;
            Objects.requireNonNull(c0736eq);
            final String str = null;
            PostTask.b(AbstractC0538bE.a, new Runnable(c0736eq, str) { // from class: cq
                public final C0736eq s;
                public final String t;

                {
                    this.s = c0736eq;
                    this.t = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    C0736eq c0736eq2 = this.s;
                    String str2 = this.t;
                    AwContentsClientBridge awContentsClientBridge = c0736eq2.a;
                    if (awContentsClientBridge != null) {
                        int i = c0736eq2.b;
                        long j = awContentsClientBridge.c;
                        if (j != 0) {
                            N.MNISBPG1(j, awContentsClientBridge, i, str2);
                        }
                    }
                    c0736eq2.a = null;
                }
            });
            return;
        }
        if (!this.c.getResult()) {
            ((C0736eq) this.a).a();
            return;
        }
        InterfaceC0513aq interfaceC0513aq = this.a;
        final String stringResult = this.c.getStringResult();
        final C0736eq c0736eq2 = (C0736eq) interfaceC0513aq;
        Objects.requireNonNull(c0736eq2);
        PostTask.b(AbstractC0538bE.a, new Runnable(c0736eq2, stringResult) { // from class: cq
            public final C0736eq s;
            public final String t;

            {
                this.s = c0736eq2;
                this.t = stringResult;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0736eq c0736eq22 = this.s;
                String str2 = this.t;
                AwContentsClientBridge awContentsClientBridge = c0736eq22.a;
                if (awContentsClientBridge != null) {
                    int i = c0736eq22.b;
                    long j = awContentsClientBridge.c;
                    if (j != 0) {
                        N.MNISBPG1(j, awContentsClientBridge, i, str2);
                    }
                }
                c0736eq22.a = null;
            }
        });
    }

    public C0216Mb(InterfaceC0792fq interfaceC0792fq) {
        this.b = interfaceC0792fq;
    }
}
