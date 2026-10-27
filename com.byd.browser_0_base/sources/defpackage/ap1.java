package defpackage;

import android.content.ComponentName;
import android.content.SharedPreferences;
import defpackage.sf3;
import java.util.HashSet;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge;
import org.chromium.chrome.browser.browserservices.permissiondelegation.a;

/* JADX INFO: compiled from: NotificationPermissionUpdater.java */
/* JADX INFO: loaded from: classes.dex */
public final class ap1 implements sf3.b {
    public final /* synthetic */ ft1 e;
    public final /* synthetic */ dp1 f;

    public ap1(dp1 dp1Var, ft1 ft1Var) {
        this.f = dp1Var;
        this.e = ft1Var;
    }

    @Override // sf3.b
    public final void a(ComponentName componentName, int i) {
        this.f.a(this.e, 0L, componentName.getPackageName(), i);
    }

    @Override // sf3.b
    public final void c() {
        Integer numValueOf;
        a aVar = this.f.a;
        a11 a11Var = aVar.a;
        HashSet hashSetG = a11Var.g();
        ft1 ft1Var = this.e;
        hashSetG.remove(ft1Var.toString());
        a11Var.a.edit().putStringSet("origins", hashSetG).remove(a11.d(5, ft1Var)).remove(a11.e(5, ft1Var)).remove(a11.d(4, ft1Var)).remove(a11.e(4, ft1Var)).remove(a11.b(ft1Var)).remove(a11.c(ft1Var)).remove(a11.a(ft1Var)).apply();
        jo1 jo1Var = aVar.d.get();
        a11 a11Var2 = jo1Var.a;
        a11Var2.getClass();
        String str = "pre_twa_notification_permission_setting." + ft1Var.toString();
        SharedPreferences sharedPreferences = a11Var2.a;
        if (sharedPreferences.contains(str)) {
            int i = sharedPreferences.getInt(str, 3);
            sharedPreferences.edit().remove(str).apply();
            numValueOf = Integer.valueOf(i);
        } else {
            String str2 = "pre_twa_notification_permission." + ft1Var.toString();
            if (sharedPreferences.contains(str2)) {
                boolean z = sharedPreferences.getBoolean(str2, false);
                sharedPreferences.edit().remove(str2).apply();
                numValueOf = Integer.valueOf(z ? 1 : 2);
            } else {
                numValueOf = null;
            }
        }
        if (numValueOf != null) {
            jo1Var.b.a(System.currentTimeMillis(), ft1Var.toString(), numValueOf.intValue() == 1);
        }
        InstalledWebappBridge.a(5);
        InstalledWebappBridge.a(4);
    }
}
