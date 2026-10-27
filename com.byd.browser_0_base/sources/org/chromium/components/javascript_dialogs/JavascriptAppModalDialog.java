package org.chromium.components.javascript_dialogs;

import J.N;
import android.content.Context;
import defpackage.bi1;
import defpackage.l31;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class JavascriptAppModalDialog extends l31 {
    public long n;

    public JavascriptAppModalDialog(String str, String str2, String str3, boolean z, int i, int i2) {
        super(str, str2, str3, z, i, i2);
    }

    @CalledByNative
    public static JavascriptAppModalDialog createAlertDialog(String str, String str2, boolean z) {
        return new JavascriptAppModalDialog(str, str2, null, z, R$string.ok, 0);
    }

    @CalledByNative
    public static JavascriptAppModalDialog createBeforeUnloadDialog(String str, String str2, boolean z, boolean z2) {
        return new JavascriptAppModalDialog(str, str2, null, z2, z ? R$string.reload : R$string.leave, R$string.cancel);
    }

    @CalledByNative
    public static JavascriptAppModalDialog createConfirmDialog(String str, String str2, boolean z) {
        return new JavascriptAppModalDialog(str, str2, null, z, R$string.ok, R$string.cancel);
    }

    @CalledByNative
    public static JavascriptAppModalDialog createPromptDialog(String str, String str2, boolean z, String str3) {
        return new JavascriptAppModalDialog(str, str2, str3, z, R$string.ok, R$string.cancel);
    }

    @Override // defpackage.l31
    public final void a(String str, boolean z) {
        long j = this.n;
        if (j != 0) {
            N.Mo6rag0q(j, this, str, z);
        }
    }

    @Override // defpackage.l31
    public final void d(boolean z, boolean z2) {
        long j = this.n;
        if (j != 0) {
            N.MOSW2s7O(j, this, z2);
        }
    }

    @CalledByNative
    public final void dismiss() {
        bi1 bi1Var = this.k;
        if (bi1Var != null) {
            bi1Var.b(4, this.l);
        }
        this.n = 0L;
    }

    @CalledByNative
    public void showJavascriptAppModalDialog(WindowAndroid windowAndroid, long j) {
        Context context = windowAndroid.i.get();
        if (context == null || windowAndroid.n() == null) {
            N.MOSW2s7O(j, this, false);
        } else {
            this.n = j;
            e(context, windowAndroid.n(), 1);
        }
    }
}
