package org.chromium.chrome.browser.webapps;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import defpackage.cp3;
import defpackage.hs3;
import defpackage.hy1;
import defpackage.nw2;
import defpackage.oj;
import defpackage.p61;
import defpackage.q2;
import defpackage.sq2;
import defpackage.sz0;
import defpackage.yp3;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: WebApkActivityLifecycleUmaTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements ApplicationStatus.c, sz0, hy1 {
    public final Activity e;
    public final oj f;
    public final sq2 g;
    public final p61<org.chromium.chrome.browser.metrics.a> h;
    public final nw2<Bundle> i;
    public long j;

    public b(Activity activity, oj ojVar, sq2 sq2Var, q2 q2Var, e eVar, p61<org.chromium.chrome.browser.metrics.a> p61Var, nw2<Bundle> nw2Var) {
        this.e = activity;
        this.f = ojVar;
        this.g = sq2Var;
        this.h = p61Var;
        this.i = nw2Var;
        q2Var.f(this);
        ApplicationStatus.f(this, activity);
        eVar.d.add(0, new hs3(this, q2Var, 1));
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 3) {
            this.j = SystemClock.elapsedRealtime();
        }
    }

    @Override // defpackage.hy1
    public final void h() {
        cp3 cp3VarL = this.f.L();
        N.ME5y4AG$(cp3VarL.e, cp3VarL.i, cp3VarL.n, SystemClock.elapsedRealtime() - this.j);
    }

    @Override // defpackage.sz0
    public final void q() {
        if (org.chromium.base.library_loader.a.l.d()) {
            return;
        }
        org.chromium.chrome.browser.metrics.a aVar = this.h.get();
        aVar.c = 4;
        aVar.f = true;
        if (this.i.get() == null) {
            Intent intent = this.e.getIntent();
            this.g.u.a(new yp3(intent.getLongExtra("org.chromium.chrome.browser.webapk_launch_time", -1L), intent.getLongExtra("org.chromium.webapk.new_style_splash_shown_time", -1L)));
        }
    }

    @Override // defpackage.sz0
    public final void Y0() {
    }

    @Override // defpackage.hy1
    public final void f() {
    }
}
