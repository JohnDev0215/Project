package defpackage;

import android.content.ComponentName;
import defpackage.sf3;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge;

/* JADX INFO: compiled from: LocationPermissionUpdater.java */
/* JADX INFO: loaded from: classes.dex */
public final class y91 implements sf3.b {
    public boolean e;
    public final /* synthetic */ ft1 f;
    public final /* synthetic */ long g;
    public final /* synthetic */ z91 h;

    public y91(z91 z91Var, ft1 ft1Var, long j) {
        this.h = z91Var;
        this.f = ft1Var;
        this.g = j;
    }

    @Override // sf3.b
    public final void a(ComponentName componentName, int i) {
        if (this.e) {
            return;
        }
        this.e = true;
        z91 z91Var = this.h;
        z91Var.getClass();
        boolean z = i == 1;
        z91Var.a.e(this.f, componentName.getPackageName(), 4, i);
        z91Var.c.getClass();
        oj2.O("TrustedWebActivity.LocationPermissionRequestIsGranted", z);
        InstalledWebappBridge.b(i, this.g);
    }

    @Override // sf3.b
    public final void c() {
        if (this.e) {
            return;
        }
        this.e = true;
        this.h.a.d(4, this.f);
        InstalledWebappBridge.b(2, this.g);
    }
}
