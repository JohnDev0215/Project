package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ExternalNavigationDelegateImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class cj0 implements aj0 {
    public final Tab a;
    public final zp1 b;
    public boolean c;

    public cj0(TabImpl tabImpl) {
        this.a = tabImpl;
        this.b = g23.t(tabImpl.e);
        tabImpl.x(new bj0(this));
    }

    public static boolean g(Intent intent, boolean z) {
        Context context = xz.a;
        if (q11.e(context, intent)) {
            return true;
        }
        ResolveInfo resolveInfoD = wt1.d(intent, z ? 65536 : 0);
        return resolveInfoD != null && resolveInfoD.activityInfo.packageName.equals(context.getPackageName());
    }

    @Override // defpackage.aj0
    public boolean a(GURL gurl) {
        return false;
    }

    @Override // defpackage.aj0
    public boolean c(GURL gurl) {
        return false;
    }

    public final Context e() {
        Tab tab = this.a;
        if (tab.D() == null) {
            return null;
        }
        return tab.D().i.get();
    }

    public final WindowAndroid f() {
        Tab tab = this.a;
        if (tab == null) {
            return null;
        }
        return tab.D();
    }

    @Override // defpackage.aj0
    public void d() {
    }

    @Override // defpackage.aj0
    public void b(Intent intent) {
    }
}
