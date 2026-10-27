package defpackage;

import android.app.Activity;
import android.os.Handler;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: UserEducationHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pk3 {
    public final Activity a;
    public final Handler b;

    public pk3(Activity activity, Handler handler) {
        this.a = activity;
        this.b = handler;
    }

    public final void a(gw0 gw0Var) {
        TraceEvent traceEventH0 = TraceEvent.H0("UserEducationHelper::requestShowIPH", null);
        try {
            xe3 xe3VarT = oj2.t(Profile.d());
            xe3VarT.c(new mc(this, 8, xe3VarT, gw0Var));
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
