package org.chromium.chrome.browser.browsing_data;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import defpackage.iq2;
import defpackage.r90;
import defpackage.un1;
import defpackage.xo;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes.dex */
public class OtherFormsOfHistoryDialogFragment extends r90 implements DialogInterface.OnClickListener {
    public static final /* synthetic */ int p0 = 0;

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        super.J0(bundle);
        View viewInflate = D().getLayoutInflater().inflate(R$layout.other_forms_of_history_dialog, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.text);
        textView.setText(iq2.a(textView.getText().toString(), new iq2.a(new un1(K(), new xo(1)), "<link>", "</link>")));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        AlertDialog.Builder builder = new AlertDialog.Builder(D(), R$style.ThemeOverlay_BrowserUI_AlertDialog);
        builder.j(viewInflate);
        builder.i(R$string.clear_browsing_data_history_dialog_title);
        builder.f(R$string.ok_got_it, this);
        AlertDialog alertDialogA = builder.a();
        alertDialogA.setCanceledOnTouchOutside(false);
        return alertDialogA;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        SharedPreferencesManager.getInstance().l("org.chromium.chrome.browser.settings.privacy.PREF_OTHER_FORMS_OF_HISTORY_DIALOG_SHOWN", true);
        D().finish();
    }
}
