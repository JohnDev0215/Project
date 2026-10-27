package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.browserservices.ClearDataDialogActivity;
import org.chromium.chrome.browser.login.ChromeHttpAuthHandler;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class of0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ of0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.e;
        Object obj = this.f;
        switch (i2) {
            case 0:
                tf0 tf0Var = (tf0) obj;
                tf0Var.z.run();
                tf0Var.b();
                break;
            case 1:
                ClearDataDialogActivity clearDataDialogActivity = (ClearDataDialogActivity) obj;
                int i3 = ClearDataDialogActivity.D;
                clearDataDialogActivity.D1(false);
                clearDataDialogActivity.finish();
                break;
            default:
                ((ChromeHttpAuthHandler) ((ja1) obj).a).y1();
                break;
        }
    }
}
