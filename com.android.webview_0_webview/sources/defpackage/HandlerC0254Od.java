package defpackage;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: renamed from: Od, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class HandlerC0254Od extends HandlerC0347Tg {
    public final /* synthetic */ C0165Jd a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0254Od(C0165Jd c0165Jd, Looper looper) {
        super(looper);
        this.a = c0165Jd;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            C0165Jd c0165Jd = this.a;
            c0165Jd.b.lock();
            try {
                if (c0165Jd.o()) {
                    c0165Jd.n();
                }
                return;
            } finally {
                c0165Jd.b.unlock();
            }
        }
        if (i == 2) {
            C0165Jd.m(this.a);
            return;
        }
        StringBuilder sb = new StringBuilder(31);
        sb.append("Unknown message id: ");
        sb.append(i);
        Log.w("GoogleApiClientImpl", sb.toString());
    }
}
