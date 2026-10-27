package defpackage;

import android.content.Intent;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.Arrays;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;

/* JADX INFO: compiled from: CustomTabActivityClientConnectionKeeper.java */
/* JADX INFO: loaded from: classes.dex */
public final class u20 implements kr2 {
    public final CustomTabsConnection e;
    public final oj f;
    public final a30 g;
    public boolean h;

    public u20(CustomTabsConnection customTabsConnection, oj ojVar, q2 q2Var, a30 a30Var) {
        this.e = customTabsConnection;
        this.f = ojVar;
        this.g = a30Var;
        q2Var.f(this);
    }

    @Override // defpackage.kr2
    public final void j() {
        boolean z;
        CustomTabsConnection customTabsConnection = this.e;
        CustomTabsSessionToken customTabsSessionTokenZ = this.f.z();
        Intent intentU = this.f.u();
        a aVar = customTabsConnection.c;
        synchronized (aVar) {
            z = false;
            if (intentU != null) {
                if (intentU.getComponent() != null) {
                    a.e eVar = (a.e) aVar.c.get(customTabsSessionTokenZ);
                    if (eVar != null) {
                        a.c cVar = eVar.l;
                        if (cVar == null) {
                            if (Arrays.asList(xz.a.getPackageManager().getPackagesForUid(eVar.a)).contains(intentU.getComponent().getPackageName())) {
                                cVar = new a.c(xz.a, new Intent().setComponent(intentU.getComponent()));
                            }
                        }
                        if (cVar.h) {
                            z = true;
                        } else if (!cVar.g) {
                            try {
                                boolean zBindService = cVar.e.bindService(cVar.f, cVar, 1);
                                cVar.h = zBindService;
                                z = zBindService;
                            } catch (SecurityException unused) {
                            }
                        }
                        if (z) {
                            eVar.l = cVar;
                        }
                    }
                }
            }
        }
        this.h = z;
    }

    @Override // defpackage.kr2
    public final void l() {
        CustomTabsConnection customTabsConnection = this.e;
        CustomTabsSessionToken customTabsSessionTokenZ = this.f.z();
        a aVar = customTabsConnection.c;
        synchronized (aVar) {
            a.e eVar = (a.e) aVar.c.get(customTabsSessionTokenZ);
            if (eVar != null) {
                a.c cVar = eVar.l;
                if (cVar != null && cVar.h) {
                    cVar.e.unbindService(cVar);
                    cVar.h = false;
                }
            }
        }
        this.h = false;
    }
}
