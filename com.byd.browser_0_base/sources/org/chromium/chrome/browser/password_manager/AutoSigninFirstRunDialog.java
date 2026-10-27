package org.chromium.chrome.browser.password_manager;

import J.N;
import android.app.Activity;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import defpackage.aa;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$style;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class AutoSigninFirstRunDialog implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    public long e;
    public AlertDialog f;

    public AutoSigninFirstRunDialog(Activity activity, long j, String str, String str2, int i, int i2, String str3, String str4) {
        this.e = j;
    }

    @CalledByNative
    public static AutoSigninFirstRunDialog createAndShowDialog(WindowAndroid windowAndroid, long j, String str, String str2, int i, int i2, String str3, String str4) {
        Activity activity = (Activity) windowAndroid.k().get();
        if (activity == null) {
            return null;
        }
        AutoSigninFirstRunDialog autoSigninFirstRunDialog = new AutoSigninFirstRunDialog(activity, j, str, str2, i, i2, str3, str4);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R$style.ThemeOverlay_BrowserUI_AlertDialog);
        AlertController.AlertParams alertParams = builder.a;
        alertParams.d = str;
        builder.g(str3, autoSigninFirstRunDialog);
        alertParams.i = str4;
        alertParams.j = autoSigninFirstRunDialog;
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.auto_sign_in_first_run_dialog, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.summary);
        if (i == i2 || i2 == 0) {
            textView.setText(str2);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            SpannableString spannableString = new SpannableString(str2);
            spannableString.setSpan(new aa(autoSigninFirstRunDialog), i, i2, 18);
            textView.setText(spannableString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        alertParams.r = viewInflate;
        AlertDialog alertDialogA = builder.a();
        autoSigninFirstRunDialog.f = alertDialogA;
        alertDialogA.setCanceledOnTouchOutside(false);
        autoSigninFirstRunDialog.f.setOnDismissListener(autoSigninFirstRunDialog);
        autoSigninFirstRunDialog.f.show();
        return autoSigninFirstRunDialog;
    }

    @CalledByNative
    public final void dismissDialog() {
        this.f.dismiss();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -2) {
            N.MNvg9$ZU(this.e, this);
        } else if (i == -1) {
            N.MV90asHX(this.e, this);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        N.MTjiTA74(this.e, this);
        this.e = 0L;
        this.f = null;
    }
}
