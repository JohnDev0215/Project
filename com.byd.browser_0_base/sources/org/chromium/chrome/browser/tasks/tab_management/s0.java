package org.chromium.chrome.browser.tasks.tab_management;

import J.N;
import android.os.SystemClock;
import android.view.View;
import defpackage.bq;
import defpackage.pn3;
import defpackage.sk;

/* JADX INFO: compiled from: TabListRecyclerView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s0 extends pn3 {
    public long l;
    public final /* synthetic */ TabListRecyclerView m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(TabListRecyclerView tabListRecyclerView, View view) {
        super(view, false);
        this.m = tabListRecyclerView;
    }

    @Override // defpackage.pn3
    public final boolean h() {
        boolean zH = super.h();
        TabListRecyclerView tabListRecyclerView = this.m;
        if (zH) {
            tabListRecyclerView.W0 = SystemClock.elapsedRealtime();
        }
        if (SystemClock.elapsedRealtime() < this.l || tabListRecyclerView.c1) {
            return false;
        }
        return zH;
    }

    @Override // defpackage.pn3
    public final void i() {
        float fFloatValue;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        super.i();
        long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
        if (jElapsedRealtime2 == 0) {
            jElapsedRealtime2 = 1;
        }
        int i = TabListRecyclerView.d1;
        this.m.getClass();
        sk skVar = bq.a;
        try {
            fFloatValue = Float.valueOf(N.MMltG$kc("TabGridLayoutAndroid", "max-duty-cycle")).floatValue();
        } catch (NumberFormatException unused) {
            fFloatValue = 0.2f;
        }
        sk skVar2 = bq.a;
        try {
            Float.valueOf(N.MMltG$kc("TabGridLayoutAndroid", "max-duty-cycle")).floatValue();
        } catch (NumberFormatException unused2) {
        }
        this.l = SystemClock.elapsedRealtime() + Math.min((long) (((1.0f - fFloatValue) * jElapsedRealtime2) / fFloatValue), 300L);
    }
}
