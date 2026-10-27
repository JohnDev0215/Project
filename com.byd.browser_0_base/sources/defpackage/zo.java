package defpackage;

import android.R;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.app.ChromeActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zo implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ ChromeActivity f;

    public /* synthetic */ zo(ChromeActivity chromeActivity, int i) {
        this.e = i;
        this.f = chromeActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        ChromeActivity chromeActivity = this.f;
        switch (i) {
            case 0:
                chromeActivity.J2();
                break;
            case 1:
                k11 k11Var = ChromeActivity.Z0;
                chromeActivity.findViewById(R.id.content).setVisibility(0);
                chromeActivity.X0 = false;
                break;
            default:
                k11 k11Var2 = ChromeActivity.Z0;
                if (!chromeActivity.d()) {
                    if (!bs0.a().c()) {
                        oj2.U(1, 21, "Search.IcingContextReportingStatus");
                    } else {
                        if (chromeActivity.Q0 == null) {
                            AppHooks.get().getClass();
                            chromeActivity.Q0 = new yr0(xz.a, new a8());
                        }
                        yr0 yr0Var = chromeActivity.Q0;
                        as0 as0Var = yr0Var.b;
                        if (as0Var != null) {
                            as0Var.a();
                        }
                        yr0Var.a++;
                    }
                    break;
                }
                break;
        }
    }
}
