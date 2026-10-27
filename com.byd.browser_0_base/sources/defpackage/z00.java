package defpackage;

import J.N;
import defpackage.a10;
import java.util.Locale;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: CouponPersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z00 extends nh0 {
    public final /* synthetic */ a10 e;

    public z00(a10 a10Var) {
        this.e = a10Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void C0(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (navigationHandle.c || tabImpl.getUrl().equals(navigationHandle.e)) {
            return;
        }
        a10 a10Var = this.e;
        a10Var.a();
        a10Var.p = new a10.a();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        String strI = navigationHandle.e.i();
        if (strI.equals("http") || strI.equals("https")) {
            a10 a10Var = this.e;
            a10Var.a();
            a10Var.p = new a10.a();
            N.M70bBKgp(Profile.d(), "COPTD", String.format(Locale.US, "https://task-management-chrome.sandbox.google.com/promotions?url=%s", tabImpl.getUrl().j()), "GET", "application/json; charset=UTF-8", a10.q, "", 10000L, a10.s.a, new on0(15, a10Var));
        }
    }
}
