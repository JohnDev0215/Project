package defpackage;

import android.graphics.Bitmap;
import android.os.SystemClock;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.ui.android.webid.AccountSelectionBridge;
import org.chromium.chrome.browser.ui.android.webid.data.Account;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class p1 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ r1 f;

    public /* synthetic */ p1(r1 r1Var, int i) {
        this.e = i;
        this.f = r1Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        r1 r1Var = this.f;
        switch (i) {
            case 0:
                Bitmap bitmap = (Bitmap) obj;
                r1Var.getClass();
                if (bitmap != null && bitmap.getWidth() == bitmap.getHeight() && bitmap.getWidth() >= AccountSelectionBridge.getBrandIconMinimumSize()) {
                    r1Var.p = bitmap;
                    r1Var.e();
                    break;
                }
                break;
            default:
                Account account = (Account) obj;
                r1Var.getClass();
                if (SystemClock.elapsedRealtime() - r1Var.t > 500) {
                    r1Var.a(account);
                    break;
                }
                break;
        }
    }
}
