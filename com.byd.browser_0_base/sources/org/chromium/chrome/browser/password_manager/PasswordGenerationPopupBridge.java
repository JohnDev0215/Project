package org.chromium.chrome.browser.password_manager;

import J.N;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.PopupWindow;
import defpackage.eu1;
import defpackage.jx1;
import defpackage.p4;
import defpackage.qe0;
import defpackage.te0;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordGenerationPopupBridge implements PopupWindow.OnDismissListener {
    public final long e;
    public final Context f;
    public final qe0 g;
    public final View h;

    public PasswordGenerationPopupBridge(View view, long j, WindowAndroid windowAndroid) {
        this.e = j;
        Context context = (Context) windowAndroid.k().get();
        this.f = context;
        this.h = view;
        if (context == null) {
            this.g = null;
            new Handler().post(new eu1(5, this));
            return;
        }
        qe0 qe0Var = new qe0(context, view, null);
        this.g = qe0Var;
        qe0Var.setOnDismissListener(this);
        te0 te0Var = qe0Var.e;
        p4 p4Var = te0Var.k;
        p4Var.m = false;
        p4Var.j.setOutsideTouchable(false);
        te0Var.j = context.getString(R$string.password_generation_popup_content_description);
    }

    @CalledByNative
    public static PasswordGenerationPopupBridge create(View view, long j, WindowAndroid windowAndroid) {
        return new PasswordGenerationPopupBridge(view, j, windowAndroid);
    }

    @CalledByNative
    public final void hide() {
        qe0 qe0Var = this.g;
        if (qe0Var != null) {
            qe0Var.e.k.a();
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        N.M6qXk$DQ(this.e, this);
    }

    @CalledByNative
    public final void show(boolean z, String str) {
        qe0 qe0Var = this.g;
        if (qe0Var != null) {
            int i = this.h.getLayoutParams().width;
            qe0Var.a(new jx1(this.f, str));
            te0 te0Var = qe0Var.e;
            te0Var.g = z;
            te0Var.b();
        }
    }
}
