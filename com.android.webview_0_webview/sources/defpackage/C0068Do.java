package defpackage;

import android.os.Handler;
import android.os.Looper;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: Do, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0068Do implements Callback {
    public Callback a;
    public Handler b;

    public C0068Do(Callback callback) {
        if (callback != null) {
            if (Looper.myLooper() == null) {
                throw new IllegalStateException("new CookieCallback should be called on a thread with a running Looper.");
            }
            this.a = callback;
            this.b = new Handler();
        }
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        final Boolean bool = (Boolean) obj;
        Handler handler = this.b;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable(this, bool) { // from class: Co
            public final C0068Do s;
            public final Boolean t;

            {
                this.s = this;
                this.t = bool;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0068Do c0068Do = this.s;
                c0068Do.a.a(this.t);
            }
        });
    }
}
