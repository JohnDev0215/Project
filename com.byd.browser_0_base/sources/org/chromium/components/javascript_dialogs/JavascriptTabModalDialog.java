package org.chromium.components.javascript_dialogs;

import J.N;
import android.content.Context;
import defpackage.bi1;
import defpackage.l31;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class JavascriptTabModalDialog extends l31 {
    public long n;

    public JavascriptTabModalDialog(String str, int i, String str2, String str3) {
        super(str, str2, str3, false, R$string.ok, i);
    }

    @CalledByNative
    public static JavascriptTabModalDialog createAlertDialog(String str, String str2) {
        return new JavascriptTabModalDialog(str, 0, str2, null);
    }

    @CalledByNative
    public static JavascriptTabModalDialog createConfirmDialog(String str, String str2) {
        return new JavascriptTabModalDialog(str, R$string.cancel, str2, null);
    }

    @CalledByNative
    public static JavascriptTabModalDialog createPromptDialog(String str, String str2, String str3) {
        return new JavascriptTabModalDialog(str, R$string.cancel, str2, str3);
    }

    @Override // defpackage.l31
    public final void a(String str, boolean z) {
        long j = this.n;
        if (j == 0) {
            return;
        }
        N.M9yPJzg8(j, this, str);
    }

    @Override // defpackage.l31
    public final void d(boolean z, boolean z2) {
        long j = this.n;
        if (j == 0) {
            return;
        }
        N.M0YaeICP(j, this, z);
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
    public final String getUserInput() {
        return this.m.getPromptText();
    }

    @CalledByNative
    public final void showDialog(WindowAndroid windowAndroid, long j) {
        Context context = windowAndroid.i.get();
        bi1 bi1VarN = windowAndroid.n();
        if (context == null || bi1VarN == null) {
            N.M0YaeICP(j, this, false);
        } else {
            this.n = j;
            e(context, bi1VarN, 0);
        }
    }
}
