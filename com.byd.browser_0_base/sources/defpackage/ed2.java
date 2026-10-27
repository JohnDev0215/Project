package defpackage;

import J.N;
import android.content.Intent;
import android.net.ProxyInfo;
import android.os.Bundle;
import defpackage.q01;
import defpackage.x01;
import org.chromium.base.task.PostTask;
import org.chromium.components.browser_ui.client_certificate.SSLClientCertificateRequest;
import org.chromium.content.browser.TtsPlatformImpl;
import org.chromium.net.ProxyChangeListener;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ed2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ ed2(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                SSLClientCertificateRequest.b bVar = (SSLClientCertificateRequest.b) obj2;
                String str = (String) obj;
                bVar.getClass();
                if (str != null) {
                    new SSLClientCertificateRequest.a(bVar.b, bVar.a, str).c(i9.e);
                } else {
                    PostTask.d(rh3.a, new d92(24, bVar));
                }
                break;
            case 1:
                ((x01.e) ((q01.a) obj2)).c((ka2[]) obj);
                break;
            case 2:
                String str2 = (String) obj;
                long j = ((TtsPlatformImpl) obj2).a;
                if (j != 0) {
                    N.M2$X0reE(j, Integer.parseInt(str2));
                }
                break;
            default:
                ProxyChangeListener.ProxyReceiver proxyReceiver = (ProxyChangeListener.ProxyReceiver) obj2;
                int i2 = ProxyChangeListener.ProxyReceiver.b;
                proxyReceiver.getClass();
                Bundle extras = ((Intent) obj).getExtras();
                ProxyChangeListener.a aVarA = extras == null ? null : ProxyChangeListener.a.a((ProxyInfo) extras.get("android.intent.extra.PROXY_INFO"));
                ProxyChangeListener proxyChangeListener = ProxyChangeListener.this;
                long j2 = proxyChangeListener.c;
                if (j2 != 0) {
                    if (aVarA == null) {
                        N.MCIk73GZ(j2, proxyChangeListener);
                    } else {
                        N.MyoFZt$2(j2, proxyChangeListener, aVarA.a, aVarA.b, aVarA.c, aVarA.d);
                    }
                    break;
                }
                break;
        }
    }
}
