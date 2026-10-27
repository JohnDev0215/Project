package defpackage;

import android.app.Activity;
import android.content.Context;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.stylus_handwriting.StylusWritingController;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: StylusWritingCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wv2 implements lt3 {
    public final i20 e;
    public final zp1<Tab> f;
    public final q2 g;
    public final StylusWritingController h;

    public wv2(Activity activity, r2 r2Var, z2 z2Var) {
        this.f = z2Var;
        this.h = new StylusWritingController(activity.getApplicationContext());
        r2Var.f(this);
        this.g = r2Var;
        this.e = new i20(z2Var, new vv2(this), new jb1(4, this));
    }

    @Override // defpackage.lt3
    public final void onWindowFocusChanged(boolean z) {
        StylusWritingController stylusWritingController = this.h;
        tv2 tv2VarA = stylusWritingController.a();
        Context context = stylusWritingController.a;
        tv2VarA.k(context, z);
        WebContents webContents = stylusWritingController.b;
        if (webContents == null) {
            return;
        }
        tv2VarA.h(context, webContents);
        if (stylusWritingController.b.u() == null) {
            return;
        }
        stylusWritingController.b.u().f = tv2VarA.f();
    }
}
