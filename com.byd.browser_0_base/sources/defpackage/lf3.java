package defpackage;

import android.os.Bundle;
import defpackage.sf3;

/* JADX INFO: compiled from: TrustedWebActivityClient.java */
/* JADX INFO: loaded from: classes.dex */
public final class lf3 implements sf3.a {
    public final /* synthetic */ String e;
    public final /* synthetic */ sf3.b f;
    public final /* synthetic */ sf3 g;

    public lf3(sf3 sf3Var, String str, sf3.b bVar) {
        this.g = sf3Var;
        this.e = str;
        this.f = bVar;
    }

    @Override // sf3.a
    public final void c() {
        this.f.c();
    }

    @Override // sf3.a
    public final void f(ft1 ft1Var, vf3 vf3Var) {
        Bundle bundle = new Bundle();
        String str = this.e;
        bundle.putString("notificationChannelName", str);
        Bundle bundleA = vf3Var.a("checkNotificationPermission", bundle, null);
        boolean z = bundleA == null ? false : bundleA.getBoolean("success");
        this.g.c.getClass();
        oj2.O("TrustedWebActivity.ExtraCommandSuccess.".concat("checkNotificationPermission"), z);
        sf3.b bVar = this.f;
        dg3 dg3Var = vf3Var.a;
        if (z) {
            int i = bundleA.getInt("permissionStatus", 1);
            bVar.a(dg3Var.b, i != 0 ? i == 2 ? 3 : 2 : 1);
            return;
        }
        dg3Var.getClass();
        Bundle bundle2 = new Bundle();
        bundle2.putString("android.support.customtabs.trusted.CHANNEL_NAME", str);
        Bundle bundleAreNotificationsEnabled = dg3Var.a.areNotificationsEnabled(bundle2);
        dg3.a(bundleAreNotificationsEnabled, "android.support.customtabs.trusted.NOTIFICATION_SUCCESS");
        bVar.a(dg3Var.b, bundleAreNotificationsEnabled.getBoolean("android.support.customtabs.trusted.NOTIFICATION_SUCCESS") ? 1 : 2);
    }
}
