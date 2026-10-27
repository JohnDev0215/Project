package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.browserservices.ClearDataDialogActivity;
import org.chromium.chrome.browser.login.ChromeHttpAuthHandler;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class rt implements DialogInterface.OnCancelListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ rt(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                ClearDataDialogActivity clearDataDialogActivity = (ClearDataDialogActivity) obj;
                int i2 = ClearDataDialogActivity.D;
                clearDataDialogActivity.D1(false);
                clearDataDialogActivity.finish();
                break;
            default:
                ((ChromeHttpAuthHandler) ((ja1) obj).a).y1();
                break;
        }
    }
}
