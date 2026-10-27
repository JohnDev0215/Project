package defpackage;

import android.os.Bundle;
import android.os.Process;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: GSAAccountChangeListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class zr0 implements Callback<Bundle> {
    public final /* synthetic */ yr0 e;

    public zr0(yr0 yr0Var) {
        this.e = yr0Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Bundle bundle) {
        as0 as0Var;
        boolean z = bundle.getBoolean("ssb_service:ssb_broadcasts_account_change_to_chrome");
        yr0 yr0Var = this.e;
        if (z) {
            if ((c6.a(Process.myPid(), Process.myUid(), xz.a, "com.google.android.apps.now.CURRENT_ACCOUNT_ACCESS") == 0) && (as0Var = yr0Var.b) != null) {
                if (as0Var.g != null) {
                    as0Var.e.unbindService(as0Var.c);
                    as0Var.g = null;
                    as0Var.b.removeCallbacksAndMessages(null);
                }
                yr0Var.b = null;
            }
        }
        if (yr0Var.c) {
            return;
        }
        oj2.O("Search.GsaBroadcastsAccountChanges", z);
        yr0Var.c = true;
    }
}
