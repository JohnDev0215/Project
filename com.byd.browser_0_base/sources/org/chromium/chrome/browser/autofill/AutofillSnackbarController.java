package org.chromium.chrome.browser.autofill;

import J.N;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import defpackage.tp2;
import defpackage.wp2;
import defpackage.xp2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class AutofillSnackbarController implements wp2.b {
    public final long e;
    public final wp2 f;

    public AutofillSnackbarController(long j, wp2 wp2Var) {
        this.e = j;
        this.f = wp2Var;
    }

    @CalledByNative
    public static AutofillSnackbarController create(long j, WindowAndroid windowAndroid) {
        return new AutofillSnackbarController(j, xp2.a(windowAndroid));
    }

    @Override // wp2.b
    public final void d(Object obj) {
        N.M$TZFnb$(this.e);
    }

    @CalledByNative
    public void dismiss() {
        this.f.a(this);
    }

    @Override // wp2.b
    public final void j(Object obj) {
        N.MoSp9aMr(this.e);
    }

    @CalledByNative
    public void show(String str, String str2) {
        tp2 tp2VarA = tp2.a(str, this, 0, 44);
        tp2VarA.d = str2;
        tp2VarA.e = null;
        tp2VarA.i = false;
        tp2VarA.j = TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE;
        this.f.c(tp2VarA);
    }
}
