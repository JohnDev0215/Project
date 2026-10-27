package defpackage;

import android.app.Activity;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.firstrun.FirstRunActivity;

/* JADX INFO: compiled from: FirstRunActivity.java */
/* JADX INFO: loaded from: classes.dex */
public final class dn0 implements ApplicationStatus.c {
    public final /* synthetic */ FirstRunActivity e;

    public dn0(FirstRunActivity firstRunActivity) {
        this.e = firstRunActivity;
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        FirstRunActivity firstRunActivity = this.e;
        if (activity != firstRunActivity ? i == 3 : i == 5 || i == 6) {
            firstRunActivity.finish();
            ApplicationStatus.i(this);
        }
    }
}
