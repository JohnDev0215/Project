package org.chromium.chrome.browser.autofill;

import android.app.Activity;
import defpackage.bi1;
import defpackage.rh3;
import defpackage.wv;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.autofill.a;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public final class AutofillExpirationDateFixFlowBridge implements a.InterfaceC0072a {
    public long a;
    public final String b;
    public final String c;
    public final int d;
    public final String e;
    public a f;

    public AutofillExpirationDateFixFlowBridge(long j, String str, String str2, int i, String str3) {
        this.a = j;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
    }

    @CalledByNative
    public static AutofillExpirationDateFixFlowBridge create(long j, String str, String str2, int i, String str3) {
        return new AutofillExpirationDateFixFlowBridge(j, str, str2, i, str3);
    }

    @CalledByNative
    public final void dismiss() {
        a aVar = this.f;
        if (aVar != null) {
            aVar.f.b(4, aVar.e);
        }
    }

    @CalledByNative
    public final void show(WindowAndroid windowAndroid) {
        Activity activity = (Activity) windowAndroid.k().get();
        if (activity == null) {
            PostTask.c(rh3.a, new wv(15, this));
            return;
        }
        a aVar = new a(activity, this, this.b, this.d, this.e, this.c);
        this.f = aVar;
        bi1 bi1VarN = windowAndroid.n();
        if (bi1VarN == null) {
            return;
        }
        aVar.g = activity;
        aVar.f = bi1VarN;
        bi1VarN.j(aVar.e, 1, false);
    }
}
