package defpackage;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class L3 extends Handler {
    public final /* synthetic */ O3 a;

    public L3(O3 o3) {
        this.a = o3;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what != 2) {
            super.handleMessage(message);
        } else {
            this.a.h();
            this.a.u.a();
        }
    }
}
