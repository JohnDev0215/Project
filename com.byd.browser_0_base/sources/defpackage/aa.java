package defpackage;

import J.N;
import android.text.style.ClickableSpan;
import android.view.View;
import org.chromium.chrome.browser.password_manager.AutoSigninFirstRunDialog;

/* JADX INFO: compiled from: AutoSigninFirstRunDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class aa extends ClickableSpan {
    public final /* synthetic */ AutoSigninFirstRunDialog e;

    public aa(AutoSigninFirstRunDialog autoSigninFirstRunDialog) {
        this.e = autoSigninFirstRunDialog;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        AutoSigninFirstRunDialog autoSigninFirstRunDialog = this.e;
        N.MQjsefF9(autoSigninFirstRunDialog.e, autoSigninFirstRunDialog);
        autoSigninFirstRunDialog.f.dismiss();
    }
}
