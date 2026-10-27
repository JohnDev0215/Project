package defpackage;

import android.os.Bundle;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* JADX INFO: compiled from: ClientPackageNameProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class eu implements vd2 {
    public final String e;

    public eu(q2 q2Var, oj ojVar, CustomTabsConnection customTabsConnection, nw2<Bundle> nw2Var) {
        Bundle bundle = nw2Var.get();
        if (bundle != null) {
            this.e = bundle.getString("twaClientPackageName");
        } else {
            this.e = customTabsConnection.d(ojVar.z());
        }
        q2Var.f(this);
    }

    @Override // defpackage.vd2
    public final void c0(Bundle bundle) {
        bundle.putString("twaClientPackageName", this.e);
    }
}
