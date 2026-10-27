package org.chromium.chrome.browser.password_check;

import android.content.DialogInterface;
import android.os.Bundle;
import defpackage.r90;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordCheckDialogFragment extends r90 {
    public final a p0;

    public interface a extends DialogInterface.OnClickListener {
        void onDismiss();
    }

    public PasswordCheckDialogFragment(a aVar) {
        this.p0 = aVar;
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (bundle != null) {
            I0(false, false);
        }
    }

    @Override // defpackage.r90, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        a aVar = this.p0;
        if (aVar != null) {
            aVar.onDismiss();
        }
    }
}
