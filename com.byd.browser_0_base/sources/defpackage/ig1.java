package defpackage;

import android.os.Handler;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: MessageAutoDismissTimer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ig1 {
    public long a;
    public Runnable b;
    public final Handler c = new Handler(ThreadUtils.d());

    public final void a() {
        if (this.b == null) {
            return;
        }
        this.c.removeCallbacksAndMessages(null);
        this.b = null;
    }
}
