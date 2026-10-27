package defpackage;

import J.N;
import android.app.Activity;
import org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: LongScreenshotsCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oa1 extends se2 {
    public final Activity m;
    public final c n;
    public final Tab o;
    public ra1 p;

    public oa1(Activity activity, Tab tab, String str, rq rqVar, ji jiVar) {
        super(activity, tab.D(), str, rqVar, jiVar);
        this.m = activity;
        this.o = tab;
        this.n = new c(activity, tab, false);
        this.p = null;
        N.MJ3oAy5s();
    }

    @Override // defpackage.ud
    public final void f() {
        if (this.p == null) {
            this.p = new ra1(this.m, this.n);
        }
        this.p.b(new eu1(14, this));
    }
}
