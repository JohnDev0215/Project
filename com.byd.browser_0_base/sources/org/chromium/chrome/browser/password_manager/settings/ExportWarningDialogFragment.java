package org.chromium.chrome.browser.password_manager.settings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import defpackage.oj2;
import defpackage.r90;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;

/* JADX INFO: loaded from: classes2.dex */
public class ExportWarningDialogFragment extends r90 {
    public a p0;

    public interface a extends DialogInterface.OnClickListener {
    }

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(D(), R$style.ThemeOverlay_BrowserUI_AlertDialog_NoActionBar);
        builder.f(R$string.password_settings_export_action_title, this.p0);
        builder.e(R$string.cancel, this.p0);
        builder.c(D().getResources().getString(R$string.settings_passwords_export_description));
        return builder.a();
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (bundle != null) {
            I0(false, false);
        }
    }

    @Override // defpackage.r90, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        a aVar = this.p0;
        if (aVar != null) {
            b bVar = ((org.chromium.chrome.browser.password_manager.settings.a) aVar).e;
            if (bVar.a != 2) {
                oj2.U(1, 3, "PasswordManager.PasswordExport.Event");
                bVar.a = 0;
            }
            bVar.f = null;
            if (bVar.e != null) {
                bVar.a();
            }
        }
    }
}
