package org.chromium.components.javascript_dialogs;

import J.N;
import android.content.Context;
import defpackage.AbstractC0566bn;
import defpackage.C1546tO;
import defpackage.Lx;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JavascriptTabModalDialog extends Lx {
    public long j;

    public JavascriptTabModalDialog(String str, String str2, String str3, int i) {
        super(str, str2, str3, false, AbstractC0566bn.a2, i);
    }

    public static JavascriptTabModalDialog createAlertDialog(String str, String str2) {
        return new JavascriptTabModalDialog(str, str2, null, 0);
    }

    public static JavascriptTabModalDialog createConfirmDialog(String str, String str2) {
        return new JavascriptTabModalDialog(str, str2, null, AbstractC0566bn.t0);
    }

    public static JavascriptTabModalDialog createPromptDialog(String str, String str2, String str3) {
        return new JavascriptTabModalDialog(str, str2, str3, AbstractC0566bn.t0);
    }

    private void dismiss() {
        C1546tO c1546tO = this.g;
        if (c1546tO != null) {
            c1546tO.b(this.h, 4);
        }
        this.j = 0L;
    }

    private String getUserInput() {
        return this.i.s.getText().toString();
    }

    private void showDialog(WindowAndroid windowAndroid, long j) {
        Context context = (Context) windowAndroid.S().get();
        C1546tO c1546tOT = windowAndroid.T();
        if (context == null || c1546tOT == null) {
            N.M0YaeICP(j, this, false);
        } else {
            this.j = j;
            e(context, c1546tOT, 1);
        }
    }

    @Override // defpackage.Lx
    public void a(String str, boolean z) {
        if (this.j == 0) {
            return;
        }
        N.M9yPJzg8(this.j, this, str);
    }

    @Override // defpackage.Lx
    public void c(boolean z, boolean z2) {
        long j = this.j;
        if (j == 0) {
            return;
        }
        N.M0YaeICP(j, this, z);
    }
}
