package defpackage;

import J.N;
import android.os.Looper;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class D8 {
    public C0122Go a;

    public void a(boolean z) {
        if (Looper.myLooper() != ThreadUtils.d()) {
            throw new RuntimeException("Toggling of Web Contents Debugging must be done on the UI thread");
        }
        if (this.a == null) {
            if (!z) {
                return;
            } else {
                this.a = new C0122Go();
            }
        }
        C0122Go c0122Go = this.a;
        N.MQE$FZ2u(c0122Go, c0122Go.a, z);
    }
}
