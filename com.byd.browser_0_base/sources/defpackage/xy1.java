package defpackage;

import android.app.Activity;
import defpackage.yy1;
import org.chromium.chrome.browser.payments.ui.j;
import org.chromium.components.thinwebview.internal.ThinWebViewImpl;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class xy1 implements Runnable {
    public final /* synthetic */ yy1 e;
    public final /* synthetic */ j f;
    public final /* synthetic */ ji g;
    public final /* synthetic */ az1 h;
    public final /* synthetic */ iz1 i;
    public final /* synthetic */ yy1.a j;
    public final /* synthetic */ Activity k;
    public final /* synthetic */ s83 l;

    public /* synthetic */ xy1(yy1 yy1Var, j jVar, ji jiVar, az1 az1Var, iz1 iz1Var, org.chromium.chrome.browser.payments.ui.j jVar2, Activity activity, ThinWebViewImpl thinWebViewImpl) {
        this.e = yy1Var;
        this.f = jVar;
        this.g = jiVar;
        this.h = az1Var;
        this.i = iz1Var;
        this.j = jVar2;
        this.k = activity;
        this.l = thinWebViewImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        yy1 yy1Var = this.e;
        yy1Var.getClass();
        this.f.c();
        ji jiVar = this.g;
        az1 az1Var = this.h;
        jiVar.e(az1Var);
        jiVar.c(this.i, true);
        org.chromium.chrome.browser.payments.ui.j jVar = (org.chromium.chrome.browser.payments.ui.j) this.j;
        j.d dVar = jVar.u;
        dVar.a = false;
        dVar.a();
        jVar.l = null;
        this.k.getWindow().getDecorView().removeOnLayoutChangeListener(az1Var);
        az1Var.destroy();
        ((ThinWebViewImpl) this.l).b();
        yy1Var.b.destroy();
    }
}
