package org.chromium.components.javascript_dialogs;

import J.N;
import android.content.Context;
import defpackage.AbstractC0566bn;
import defpackage.C1546tO;
import defpackage.Lx;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JavascriptAppModalDialog extends Lx {
    public long j;

    public JavascriptAppModalDialog(String str, String str2, String str3, boolean z, int i, int i2) {
        super(str, str2, str3, z, i, i2);
    }

    public static JavascriptAppModalDialog createAlertDialog(String str, String str2, boolean z) {
        return new JavascriptAppModalDialog(str, str2, null, z, AbstractC0566bn.a2, 0);
    }

    public static JavascriptAppModalDialog createBeforeUnloadDialog(String str, String str2, boolean z, boolean z2) {
        return new JavascriptAppModalDialog(str, str2, null, z2, z ? AbstractC0566bn.n2 : AbstractC0566bn.I1, AbstractC0566bn.t0);
    }

    public static JavascriptAppModalDialog createConfirmDialog(String str, String str2, boolean z) {
        return new JavascriptAppModalDialog(str, str2, null, z, AbstractC0566bn.a2, AbstractC0566bn.t0);
    }

    public static JavascriptAppModalDialog createPromptDialog(String str, String str2, boolean z, String str3) {
        return new JavascriptAppModalDialog(str, str2, str3, z, AbstractC0566bn.a2, AbstractC0566bn.t0);
    }

    private void dismiss() {
        C1546tO c1546tO = this.g;
        if (c1546tO != null) {
            c1546tO.b(this.h, 4);
        }
        this.j = 0L;
    }

    @Override // defpackage.Lx
    public void a(String str, boolean z) {
        if (this.j != 0) {
            N.Mo6rag0q(this.j, this, str, z);
        }
    }

    @Override // defpackage.Lx
    public void c(boolean z, boolean z2) {
        long j = this.j;
        if (j != 0) {
            N.MOSW2s7O(j, this, z2);
        }
    }

    public void showJavascriptAppModalDialog(WindowAndroid windowAndroid, long j) {
        Context context = (Context) windowAndroid.S().get();
        if (context == null || windowAndroid.T() == null) {
            N.MOSW2s7O(j, this, false);
        } else {
            this.j = j;
            e(context, windowAndroid.T(), 0);
        }
    }
}
