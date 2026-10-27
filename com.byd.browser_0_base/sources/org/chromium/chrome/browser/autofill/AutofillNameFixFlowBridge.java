package org.chromium.chrome.browser.autofill;

import android.app.Activity;
import defpackage.bi1;
import defpackage.rh3;
import defpackage.wv;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.autofill.b;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public final class AutofillNameFixFlowBridge implements b.a {
    public final long a;
    public final Activity b;
    public final String c;
    public final String d;
    public final String e;
    public final int f;
    public b g;

    public AutofillNameFixFlowBridge(long j, String str, String str2, String str3, int i, WindowAndroid windowAndroid) {
        this.a = j;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = i;
        Activity activity = (Activity) windowAndroid.k().get();
        this.b = activity;
        if (activity == null) {
            this.g = null;
            PostTask.c(rh3.a, new wv(16, this));
        }
    }

    @CalledByNative
    public static AutofillNameFixFlowBridge create(long j, String str, String str2, String str3, int i, WindowAndroid windowAndroid) {
        return new AutofillNameFixFlowBridge(j, str, str2, str3, i, windowAndroid);
    }

    @CalledByNative
    public final void dismiss() {
        b bVar = this.g;
        if (bVar != null) {
            bVar.f.b(4, bVar.e);
        }
    }

    @CalledByNative
    public final void show(WindowAndroid windowAndroid) {
        b bVar = new b(this.b, this, this.d, this.c, this.f, this.e);
        this.g = bVar;
        Activity activity = (Activity) windowAndroid.k().get();
        bi1 bi1VarN = windowAndroid.n();
        if (activity == null || bi1VarN == null) {
            return;
        }
        bVar.g = activity;
        bVar.f = bi1VarN;
        bi1VarN.j(bVar.e, 1, false);
    }
}
