package defpackage;

import android.app.Activity;
import android.os.SystemClock;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.PowerBroadcastReceiver;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content.browser.LauncherThread;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class kp implements ApplicationStatus.d {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ kp(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.base.ApplicationStatus.d
    public final void v(int i) {
        int iP = 0;
        switch (this.e) {
            case 0:
                lp lpVar = (lp) this.f;
                if (i != 3) {
                    lpVar.getClass();
                    if (i == 4 && ApplicationStatus.a.isEmpty()) {
                        PartnerBrowserCustomizations partnerBrowserCustomizationsB = PartnerBrowserCustomizations.b();
                        partnerBrowserCustomizationsB.e.clear();
                        partnerBrowserCustomizationsB.f = null;
                        PartnerBrowserCustomizations.g = null;
                        i9.f.execute(new gg3(14));
                        break;
                    }
                } else if (lpVar.e) {
                    UmaUtils.d = SystemClock.uptimeMillis();
                    z7.i();
                    lpVar.e = false;
                    PowerBroadcastReceiver powerBroadcastReceiver = lpVar.a;
                    PowerBroadcastReceiver.a aVar = powerBroadcastReceiver.b;
                    if (aVar.f == 1) {
                        aVar.f = 2;
                        aVar.e.removeCallbacks(aVar);
                    }
                    if (powerBroadcastReceiver.a.getAndSet(false)) {
                        xz.a.unregisterReceiver(powerBroadcastReceiver);
                    }
                    b.c = null;
                    b.e = null;
                    Iterator it = ApplicationStatus.b().iterator();
                    while (it.hasNext()) {
                        nw2 nw2Var = (nw2) lpVar.b.get((Activity) it.next());
                        if (nw2Var != null && nw2Var.f()) {
                            iP += ((y13) nw2Var.get()).p();
                        }
                    }
                    oj2.R(iP, "Tab.TotalTabCount.BeforeLeavingApp");
                    oj2.t(Profile.d()).notifyEvent("foreground_session_destroyed");
                    break;
                }
                break;
            default:
                rn rnVar = (rn) this.f;
                rnVar.getClass();
                if (i != 0) {
                    if (i == 1 || i == 2) {
                        if (!rnVar.a) {
                            rnVar.a = true;
                            LauncherThread.b.postDelayed(rnVar.e, Math.round(Math.log(rnVar.d.nextDouble()) * (-1.0d) * 60000));
                        }
                    } else if (rnVar.a) {
                        rnVar.a = false;
                        LauncherThread.a(new ve3(28, rnVar));
                    }
                }
                break;
        }
    }
}
