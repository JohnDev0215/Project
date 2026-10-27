package org.chromium.chrome.browser.password_manager;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import defpackage.C0204p0;
import defpackage.DialogInterfaceOnClickListenerC0206q0;
import defpackage.ob;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class AccountChooserDialog implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    public final Context e;
    public final Credential[] f;
    public final String g;
    public final int h;
    public final int i;
    public final String j;
    public final String k;
    public a l;
    public Credential m;
    public long n;
    public AlertDialog o;
    public boolean p = false;

    public AccountChooserDialog(Activity activity, long j, Credential[] credentialArr, String str, int i, int i2, String str2, String str3) {
        this.n = j;
        this.e = activity;
        this.f = (Credential[]) credentialArr.clone();
        this.g = str;
        this.h = i;
        this.i = i2;
        this.j = str2;
        this.k = str3;
    }

    @CalledByNative
    public static AccountChooserDialog createAndShowAccountChooser(WindowAndroid windowAndroid, long j, Credential[] credentialArr, String str, int i, int i2, String str2, String str3) {
        int i3;
        Activity activity = (Activity) windowAndroid.k().get();
        if (activity == null) {
            return null;
        }
        AccountChooserDialog accountChooserDialog = new AccountChooserDialog(activity, j, credentialArr, str, i, i2, str2, str3);
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.account_chooser_dialog_title, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R$id.origin)).setText(accountChooserDialog.j);
        TextView textView = (TextView) viewInflate.findViewById(R$id.title);
        String str4 = accountChooserDialog.g;
        int i4 = accountChooserDialog.h;
        if (i4 == 0 || (i3 = accountChooserDialog.i) == 0) {
            textView.setText(str4);
        } else {
            SpannableString spannableString = new SpannableString(str4);
            spannableString.setSpan(new C0204p0(accountChooserDialog), i4, i3, 18);
            textView.setText(spannableString, TextView.BufferType.SPANNABLE);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        accountChooserDialog.l = new a(accountChooserDialog, activity, accountChooserDialog.f);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R$style.ThemeOverlay_BrowserUI_AlertDialog);
        AlertController.AlertParams alertParams = builder.a;
        alertParams.e = viewInflate;
        builder.e(R$string.cancel, accountChooserDialog);
        a aVar = accountChooserDialog.l;
        DialogInterfaceOnClickListenerC0206q0 dialogInterfaceOnClickListenerC0206q0 = new DialogInterfaceOnClickListenerC0206q0(accountChooserDialog);
        alertParams.p = aVar;
        alertParams.q = dialogInterfaceOnClickListenerC0206q0;
        String str5 = accountChooserDialog.k;
        if (!TextUtils.isEmpty(str5)) {
            builder.g(str5, accountChooserDialog);
        }
        AlertDialog alertDialogA = builder.a();
        accountChooserDialog.o = alertDialogA;
        alertDialogA.setOnDismissListener(accountChooserDialog);
        accountChooserDialog.o.show();
        return accountChooserDialog;
    }

    @CalledByNative
    public final void imageFetchComplete(int i, Bitmap bitmap) {
        View childAt;
        if (this.n == 0) {
            return;
        }
        BitmapDrawable bitmapDrawableA = ob.a(this.e.getResources(), bitmap, bitmap.getHeight());
        this.f[i].f = bitmapDrawableA;
        ListView listViewF = this.o.f();
        if (i < listViewF.getFirstVisiblePosition() || i > listViewF.getLastVisiblePosition() || (childAt = listViewF.getChildAt(i - listViewF.getFirstVisiblePosition())) == null) {
            return;
        }
        ((ImageView) childAt.findViewById(R$id.profile_image)).setImageDrawable(bitmapDrawableA);
    }

    @CalledByNative
    public final void notifyNativeDestroyed() {
        this.n = 0L;
        AlertDialog alertDialog = this.o;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            this.m = this.f[0];
            this.p = true;
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.o = null;
        long j = this.n;
        if (j == 0) {
            return;
        }
        Credential credential = this.m;
        if (credential != null) {
            N.MJZem$De(j, this, credential.e, this.p);
        } else {
            N.M$NQU8jD(j, this);
        }
    }
}
