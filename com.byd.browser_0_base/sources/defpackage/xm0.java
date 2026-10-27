package defpackage;

import android.view.ActionMode;
import android.view.ViewStub;
import org.chromium.base.b;
import org.chromium.chrome.browser.findinpage.FindToolbar;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: FindToolbarManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class xm0 {
    public FindToolbar a;
    public final ViewStub b;
    public final y13 c;
    public final WindowAndroid d;
    public final ActionMode.Callback e;
    public final b<ym0> f = new b<>();
    public final zb g;

    public xm0(ViewStub viewStub, y13 y13Var, g3 g3Var, rb3 rb3Var, zb zbVar) {
        this.b = viewStub;
        this.c = y13Var;
        this.d = g3Var;
        this.e = rb3Var;
        this.g = zbVar;
    }

    public final void a(boolean z) {
        FindToolbar findToolbar = this.a;
        if (findToolbar == null) {
            return;
        }
        findToolbar.h(z);
    }
}
