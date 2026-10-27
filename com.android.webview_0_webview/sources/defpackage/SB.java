package defpackage;

import android.view.View;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SB implements Runnable {
    public final /* synthetic */ View s;
    public final /* synthetic */ WB t;

    public SB(WB wb, View view) {
        this.t = wb;
        this.s = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.b.onWindowFocusChanged(true);
        ((WA) this.t.a).c(this.s);
        Objects.requireNonNull(this.t);
        VB.a.post(new RB(this));
    }
}
