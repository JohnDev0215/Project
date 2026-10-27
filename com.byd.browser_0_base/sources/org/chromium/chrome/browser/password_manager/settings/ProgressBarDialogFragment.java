package org.chromium.chrome.browser.password_manager.settings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import defpackage.r90;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.components.browser_ui.widget.MaterialProgressBar;

/* JADX INFO: loaded from: classes2.dex */
public class ProgressBarDialogFragment extends r90 {
    public DialogInterface.OnClickListener p0;

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        View viewInflate = D().getLayoutInflater().inflate(R$layout.passwords_progress_dialog, (ViewGroup) null);
        ((MaterialProgressBar) viewInflate.findViewById(R$id.passwords_progress_bar)).setIndeterminate(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(D(), R$style.ThemeOverlay_BrowserUI_AlertDialog_NoActionBar);
        AlertController.AlertParams alertParams = builder.a;
        alertParams.r = viewInflate;
        builder.e(R$string.cancel, this.p0);
        alertParams.d = D().getResources().getString(R$string.settings_passwords_preparing_export);
        return builder.a();
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (bundle != null) {
            I0(false, false);
        }
    }

    public void setCancelProgressHandler(DialogInterface.OnClickListener onClickListener) {
        this.p0 = onClickListener;
    }
}
