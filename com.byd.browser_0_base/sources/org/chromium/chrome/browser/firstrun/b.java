package org.chromium.chrome.browser.firstrun;

import android.os.SystemClock;
import defpackage.fn0;
import defpackage.xr;

/* JADX INFO: compiled from: TosDialogBehaviorSharedPrefInvalidator.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final a a;
    public final fn0 b;

    public b(a aVar, fn0 fn0Var) {
        SystemClock.elapsedRealtime();
        this.b = fn0Var;
        this.a = aVar;
        aVar.h(new xr(21, this));
    }
}
