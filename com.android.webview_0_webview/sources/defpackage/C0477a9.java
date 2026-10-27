package defpackage;

import android.webkit.WebMessage;
import android.webkit.WebMessagePort;
import org.chromium.content_public.browser.MessagePort;

/* JADX INFO: renamed from: a9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0477a9 implements UD {
    public final /* synthetic */ WebMessagePort.WebMessageCallback a;
    public final /* synthetic */ C0533b9 b;

    public C0477a9(C0533b9 c0533b9, WebMessagePort.WebMessageCallback webMessageCallback) {
        this.b = c0533b9;
        this.a = webMessageCallback;
    }

    @Override // defpackage.UD
    public void a(String str, MessagePort[] messagePortArr) {
        this.a.onMessage(this.b, new WebMessage(str, C0533b9.a(messagePortArr)));
    }
}
