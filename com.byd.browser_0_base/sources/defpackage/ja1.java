package defpackage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import org.chromium.components.browser_ui.http_auth.R$id;
import org.chromium.components.browser_ui.http_auth.R$layout;
import org.chromium.components.browser_ui.http_auth.R$string;
import org.chromium.components.browser_ui.http_auth.R$style;
import org.chromium.components.browser_ui.widget.text.AlertDialogEditText;

/* JADX INFO: compiled from: LoginPrompt.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ja1 {
    public final a a;
    public AlertDialog b;
    public AlertDialogEditText c;
    public AlertDialogEditText d;

    /* JADX INFO: compiled from: LoginPrompt.java */
    public interface a {
    }

    public ja1(Activity activity, String str, a aVar) {
        this.a = aVar;
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.http_auth_dialog, (ViewGroup) null);
        this.c = (AlertDialogEditText) viewInflate.findViewById(R$id.username);
        AlertDialogEditText alertDialogEditText = (AlertDialogEditText) viewInflate.findViewById(R$id.password);
        this.d = alertDialogEditText;
        int i = 1;
        alertDialogEditText.setOnEditorActionListener(new za(i, this));
        ((TextView) viewInflate.findViewById(R$id.explanation)).setText(str);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R$style.ThemeOverlay_BrowserUI_AlertDialog);
        builder.i(R$string.login_dialog_title);
        AlertController.AlertParams alertParams = builder.a;
        alertParams.r = viewInflate;
        int i2 = 2;
        builder.f(R$string.login_dialog_ok_button_label, new nf0(i2, this));
        builder.e(R$string.cancel, new of0(i2, this));
        alertParams.l = new rt(i, this);
        AlertDialog alertDialogA = builder.a();
        this.b = alertDialogA;
        alertDialogA.c().C();
        this.b.getWindow().setSoftInputMode(4);
    }
}
