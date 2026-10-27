package org.chromium.chrome.browser.password_check;

import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import defpackage.n3;
import defpackage.n40;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_check.PasswordCheckDialogFragment;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordCheckViewDialogFragment extends PasswordCheckDialogFragment {
    public final CompromisedCredential q0;

    public PasswordCheckViewDialogFragment(PasswordCheckDialogFragment.a aVar, CompromisedCredential compromisedCredential) {
        super(aVar);
        this.q0 = compromisedCredential;
    }

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        D().getWindow().setFlags(8192, 8192);
        View viewInflate = D().getLayoutInflater().inflate(R$layout.password_check_view_credential_dialog, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.view_dialog_compromised_password);
        CompromisedCredential compromisedCredential = this.q0;
        textView.setText(compromisedCredential.getPassword());
        textView.setInputType(131217);
        ClipboardManager clipboardManager = (ClipboardManager) D().getApplicationContext().getSystemService("clipboard");
        ImageButton imageButton = (ImageButton) viewInflate.findViewById(R$id.view_dialog_copy_button);
        imageButton.setClickable(true);
        imageButton.setOnClickListener(new n40(this, 6, clipboardManager));
        AlertDialog.Builder builder = new AlertDialog.Builder(D());
        builder.a.d = compromisedCredential.h;
        builder.e(R$string.close, this.p0);
        builder.j(viewInflate);
        return builder.a();
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        if (n3.o(0)) {
            return;
        }
        I0(false, false);
    }

    @Override // org.chromium.chrome.browser.password_check.PasswordCheckDialogFragment, defpackage.r90, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        D().getWindow().clearFlags(8192);
    }
}
