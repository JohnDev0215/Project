package org.chromium.chrome.browser.safe_browsing;

import android.R;
import android.app.Activity;
import android.content.Context;
import defpackage.hj;
import defpackage.kx1;
import defpackage.lj1;
import defpackage.lx1;
import defpackage.mx1;
import defpackage.td2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class SafeBrowsingPasswordReuseDialogBridge {
    public long a;
    public final lx1 b;
    public final WindowAndroid c;

    public SafeBrowsingPasswordReuseDialogBridge(long j, WindowAndroid windowAndroid) {
        this.a = j;
        this.c = windowAndroid;
        this.b = new lx1(windowAndroid.n(), ((Activity) windowAndroid.k().get()).findViewById(R.id.content), hj.t(windowAndroid));
    }

    @CalledByNative
    public static SafeBrowsingPasswordReuseDialogBridge create(WindowAndroid windowAndroid, long j) {
        return new SafeBrowsingPasswordReuseDialogBridge(j, windowAndroid);
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
        WindowAndroid windowAndroid = this.c;
        if (windowAndroid.k().get() == null) {
            return;
        }
        kx1 kx1Var = new kx1(str, str2, R$drawable.password_checkup_warning, str3, str4, str4 != null ? new td2(19, this) : new lj1(18, this));
        kx1Var.g = str4 != null;
        Context context = (Context) windowAndroid.k().get();
        lx1 lx1Var = this.b;
        lx1Var.a(context, kx1Var);
        lx1Var.b();
    }
}
