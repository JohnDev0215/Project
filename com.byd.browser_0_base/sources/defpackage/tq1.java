package defpackage;

import android.os.Handler;
import org.chromium.chrome.browser.profiles.OTRProfileID;

/* JADX INFO: compiled from: OfflineItemStartupLogger.java */
/* JADX INFO: loaded from: classes.dex */
public final class tq1 implements qq1 {
    public final Handler e = new Handler();
    public final boolean f;
    public final rq1 g;

    public tq1(wc0 wc0Var, o21 o21Var) {
        OTRProfileID oTRProfileID = OTRProfileID.b;
        this.f = !(wc0Var.a != null);
        this.g = o21Var;
        o21Var.a(this);
        if (o21Var.f()) {
            c();
        }
    }

    @Override // defpackage.qq1
    public final void c() {
        this.g.g(this);
        this.e.post(new aw(28, this));
    }
}
