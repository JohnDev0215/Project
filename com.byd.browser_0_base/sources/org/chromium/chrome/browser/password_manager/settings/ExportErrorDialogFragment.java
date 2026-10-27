package org.chromium.chrome.browser.password_manager.settings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import defpackage.r90;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;

/* JADX INFO: loaded from: classes2.dex */
public class ExportErrorDialogFragment extends r90 {
    public DialogInterface.OnClickListener p0;
    public a q0;

    public static class a {
        public int a;
        public String b;
        public String c;
    }

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        View viewInflate = D().getLayoutInflater().inflate(R$layout.passwords_error_dialog, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R$id.error_main_description)).setText(this.q0.b);
        TextView textView = (TextView) viewInflate.findViewById(R$id.error_detailed_description);
        String str = this.q0.c;
        if (str != null) {
            textView.setText(str);
        } else {
            textView.setVisibility(8);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(D(), R$style.ThemeOverlay_BrowserUI_AlertDialog_NoActionBar);
        builder.j(viewInflate);
        builder.i(R$string.password_settings_export_error_title);
        builder.f(this.q0.a, this.p0);
        builder.e(R$string.close, this.p0);
        return builder.a();
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (bundle != null) {
            I0(false, false);
        }
    }

    public void setExportErrorHandler(DialogInterface.OnClickListener onClickListener) {
        this.p0 = onClickListener;
    }
}
