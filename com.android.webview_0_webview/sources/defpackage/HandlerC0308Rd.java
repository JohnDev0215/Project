package defpackage;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.Objects;

/* JADX INFO: renamed from: Rd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class HandlerC0308Rd extends HandlerC0347Tg {
    public final /* synthetic */ C0272Pd a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0308Rd(C0272Pd c0272Pd, Looper looper) {
        super(looper);
        this.a = c0272Pd;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i != 1) {
            if (i == 2) {
                throw ((RuntimeException) message.obj);
            }
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i);
            Log.w("GACStateManager", sb.toString());
            return;
        }
        AbstractC0326Sd abstractC0326Sd = (AbstractC0326Sd) message.obj;
        C0272Pd c0272Pd = this.a;
        Objects.requireNonNull(abstractC0326Sd);
        c0272Pd.a.lock();
        try {
            if (c0272Pd.k != abstractC0326Sd.a) {
                return;
            }
            abstractC0326Sd.a();
        } finally {
            c0272Pd.a.unlock();
        }
    }
}
