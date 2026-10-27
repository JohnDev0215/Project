package org.chromium.chrome.browser.password_check;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.password_check.PasswordCheckDialogFragment;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordCheckDeletionDialogFragment extends PasswordCheckDialogFragment {
    public final String q0;

    public PasswordCheckDeletionDialogFragment(PasswordCheckDialogFragment.a aVar, String str) {
        super(aVar);
        this.q0 = str;
    }

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(D(), R$style.ThemeOverlay_BrowserUI_AlertDialog_NoActionBar);
        builder.i(R$string.password_entry_edit_delete_credential_dialog_title);
        int i = R$string.password_entry_edit_delete_credential_dialog_confirm;
        PasswordCheckDialogFragment.a aVar = this.p0;
        builder.f(i, aVar);
        builder.e(R$string.password_check_credential_dialog_cancel, aVar);
        builder.c(P(R$string.password_check_delete_credential_dialog_body, this.q0));
        return builder.a();
    }
}
