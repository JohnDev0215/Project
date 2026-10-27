package defpackage;

import android.os.Bundle;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FB implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ Bundle t;
    public final /* synthetic */ QB u;

    public FB(QB qb, String str, Bundle bundle) {
        this.u = qb;
        this.s = str;
        this.t = bundle;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.e.y.a();
    }
}
