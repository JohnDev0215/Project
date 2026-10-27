package defpackage;

import J.N;
import android.content.DialogInterface;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.browserservices.ClearDataDialogActivity;
import org.chromium.chrome.browser.login.ChromeHttpAuthHandler;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nf0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ nf0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.e;
        Object obj = this.f;
        switch (i2) {
            case 0:
                ((tf0) obj).getClass();
                dialogInterface.cancel();
                break;
            case 1:
                ClearDataDialogActivity clearDataDialogActivity = (ClearDataDialogActivity) obj;
                int i3 = ClearDataDialogActivity.D;
                clearDataDialogActivity.D1(true);
                ArrayList<String> arrayListQ = q11.q(clearDataDialogActivity.getIntent(), "org.chromium.chrome.extra.origins");
                ArrayList<String> arrayListQ2 = q11.q(clearDataDialogActivity.getIntent(), "org.chromium.chrome.extra.domains");
                if (arrayListQ != null && !arrayListQ.isEmpty() && arrayListQ2 != null && !arrayListQ2.isEmpty()) {
                    fg3.a(clearDataDialogActivity, arrayListQ, arrayListQ2);
                }
                clearDataDialogActivity.finish();
                break;
            case 2:
                ja1 ja1Var = (ja1) obj;
                String string = ja1Var.c.getText().toString();
                String string2 = ja1Var.d.getText().toString();
                ChromeHttpAuthHandler chromeHttpAuthHandler = (ChromeHttpAuthHandler) ja1Var.a;
                N.MAMBiVB$(chromeHttpAuthHandler.e, chromeHttpAuthHandler, string, string2);
                break;
            default:
                ((Callback) obj).onResult("User closed the Payment Request UI.");
                break;
        }
    }
}
