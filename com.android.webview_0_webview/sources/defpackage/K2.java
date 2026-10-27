package defpackage;

import android.os.Handler;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class K2 implements V2 {
    public final /* synthetic */ AbstractC1140m2 a;
    public final /* synthetic */ Handler b;

    public K2(AbstractC1140m2 abstractC1140m2, Handler handler) {
        this.a = abstractC1140m2;
        this.b = handler;
    }

    @Override // defpackage.V2
    public void a(Object obj) {
        P2 p2 = (P2) obj;
        if (p2 == null) {
            this.a.a(1, this.b);
            return;
        }
        int i = p2.b;
        if (i == 0) {
            this.a.b(p2.a, this.b);
        } else {
            this.a.a(i, this.b);
        }
    }
}
