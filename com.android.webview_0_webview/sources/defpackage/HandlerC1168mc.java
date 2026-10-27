package defpackage;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.Objects;

/* JADX INFO: renamed from: mc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class HandlerC1168mc extends HandlerC0347Tg {
    public final Context a;
    public final /* synthetic */ C1223nc b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC1168mc(C1223nc c1223nc, Context context) {
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
        this.b = c1223nc;
        this.a = context.getApplicationContext();
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i != 1) {
            StringBuilder sb = new StringBuilder(50);
            sb.append("Don't know how to handle this message: ");
            sb.append(i);
            Log.w("GoogleApiAvailability", sb.toString());
            return;
        }
        int iE = this.b.e(this.a);
        Objects.requireNonNull(this.b);
        if (AbstractC1388qc.d(iE)) {
            this.b.g(this.a, iE);
        }
    }
}
