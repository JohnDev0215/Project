package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.password_manager.AccountChooserDialog;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: renamed from: q0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccountChooserDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class DialogInterfaceOnClickListenerC0206q0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ AccountChooserDialog e;

    public DialogInterfaceOnClickListenerC0206q0(AccountChooserDialog accountChooserDialog) {
        this.e = accountChooserDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AccountChooserDialog accountChooserDialog = this.e;
        accountChooserDialog.m = accountChooserDialog.f[i];
    }
}
