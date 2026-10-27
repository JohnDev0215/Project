package org.chromium.content.browser.input;

import android.content.Context;
import android.view.View;
import defpackage.qe0;
import defpackage.se3;
import defpackage.te0;
import defpackage.wh2;
import defpackage.xh2;
import defpackage.yd0;
import defpackage.yh2;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.input.SelectPopup;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* JADX INFO: compiled from: SelectPopupDropdown.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e implements SelectPopup.a {
    public final Callback<int[]> a;
    public final qe0 b;
    public boolean c;

    public e(Context context, se3 se3Var, View view, ArrayList arrayList, int[] iArr, boolean z, WebContentsImpl webContentsImpl) {
        this.a = se3Var;
        qe0 qe0Var = new qe0(context, view, null);
        this.b = qe0Var;
        qe0Var.setOnItemClickListener(new wh2(this));
        int i = iArr.length > 0 ? iArr[0] : -1;
        te0 te0Var = qe0Var.e;
        te0Var.h = i;
        qe0Var.a(new yd0(context, arrayList));
        te0Var.g = z;
        qe0Var.setOnDismissListener(new xh2(this));
        GestureListenerManagerImpl.e(webContentsImpl).d(new yh2(this), 0);
    }

    @Override // org.chromium.content.browser.input.SelectPopup.a
    public final void a() {
        this.b.e.k.d();
    }

    @Override // org.chromium.content.browser.input.SelectPopup.a
    public final void b(boolean z) {
        qe0 qe0Var = this.b;
        if (z) {
            qe0Var.e.k.a();
            c(null);
        } else {
            this.c = true;
            qe0Var.e.k.a();
        }
    }

    public final void c(int[] iArr) {
        if (this.c) {
            return;
        }
        this.a.onResult(iArr);
        this.c = true;
    }
}
