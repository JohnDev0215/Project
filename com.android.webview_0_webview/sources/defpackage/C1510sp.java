package defpackage;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import org.chromium.android_webview.AwSettings;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: sp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1510sp {
    public Handler a;
    public boolean b;
    public final /* synthetic */ AwSettings c;

    public C1510sp(AwSettings awSettings) {
        this.c = awSettings;
    }

    public void a(Runnable runnable) {
        if (this.a == null) {
            return;
        }
        if (ThreadUtils.j()) {
            runnable.run();
            return;
        }
        this.b = true;
        this.a.sendMessage(Message.obtain(null, 0, runnable));
        while (this.b) {
            try {
                this.c.f.wait();
            } catch (InterruptedException e) {
                Log.e("AwSettings", "Interrupted waiting a Runnable to complete", e);
                this.b = false;
                return;
            }
        }
    }

    public void b() {
        a(new Runnable(this) { // from class: op
            public final C1510sp s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                AwSettings.c(this.s.c);
            }
        });
    }
}
