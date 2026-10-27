package org.chromium.chrome.browser.password_manager;

import android.R;
import android.app.Activity;
import defpackage.eu1;
import defpackage.hj;
import defpackage.kx1;
import defpackage.lj1;
import defpackage.lx1;
import defpackage.mx1;
import defpackage.qx1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class CredentialLeakDialogBridge {
    public long a;
    public final lx1 b;
    public final WindowAndroid c;

    public CredentialLeakDialogBridge(long j, WindowAndroid windowAndroid) {
        this.a = j;
        this.c = windowAndroid;
        this.b = new lx1(windowAndroid.n(), ((Activity) windowAndroid.k().get()).findViewById(R.id.content), hj.t(windowAndroid));
    }

    @CalledByNative
    public static CredentialLeakDialogBridge create(WindowAndroid windowAndroid, long j) {
        return new CredentialLeakDialogBridge(j, windowAndroid);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
        mx1 mx1Var = this.b.a;
        mx1Var.e.b(4, mx1Var.i);
        mx1Var.f.removeOnLayoutChangeListener(mx1Var);
    }

    @CalledByNative
    public void showDialog(String str, String str2, String str3, String str4) {
        Activity activity = (Activity) this.c.k().get();
        if (activity == null) {
            return;
        }
        kx1 kx1Var = new kx1(str, str2, qx1.c() ? R$drawable.password_check_header_red : R$drawable.password_checkup_warning, str3, str4, new lj1(13, this));
        kx1Var.g = str4 != null;
        kx1Var.h = new eu1(4, this);
        lx1 lx1Var = this.b;
        lx1Var.a(activity, kx1Var);
        lx1Var.b();
    }
}
