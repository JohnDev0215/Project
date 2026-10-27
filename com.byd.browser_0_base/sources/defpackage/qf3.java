package defpackage;

import android.os.Bundle;
import defpackage.sf3;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappGeolocationBridge;

/* JADX INFO: compiled from: TrustedWebActivityClient.java */
/* JADX INFO: loaded from: classes.dex */
public final class qf3 implements sf3.a {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ vd1 f;
    public final /* synthetic */ sf3 g;

    public qf3(sf3 sf3Var, boolean z, InstalledWebappGeolocationBridge.a aVar) {
        this.g = sf3Var;
        this.e = z;
        this.f = aVar;
    }

    @Override // sf3.a
    public final void c() {
        this.g.getClass();
        Bundle bundle = new Bundle();
        bundle.putString("message", "NoTwaFound");
        this.f.q("onNewLocationError", bundle);
    }

    @Override // sf3.a
    public final void f(ft1 ft1Var, vf3 vf3Var) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("enableHighAccuracy", this.e);
        vd1 vd1Var = this.f;
        Bundle bundleA = vf3Var.a("startLocation", bundle, vd1Var);
        if (bundleA == null || !bundleA.getBoolean("success")) {
            this.g.getClass();
            Bundle bundle2 = new Bundle();
            bundle2.putString("message", "Failed to request location updates");
            vd1Var.q("onNewLocationError", bundle2);
        }
    }
}
