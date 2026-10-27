package defpackage;

import org.chromium.content_public.browser.MessagePort;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Q8 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ String t;
    public final /* synthetic */ MessagePort[] u;
    public final /* synthetic */ T8 v;

    public Q8(T8 t8, String str, String str2, MessagePort[] messagePortArr) {
        this.v = t8;
        this.s = str;
        this.t = str2;
        this.u = messagePortArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.v.g(this.s, this.t, this.u);
    }
}
