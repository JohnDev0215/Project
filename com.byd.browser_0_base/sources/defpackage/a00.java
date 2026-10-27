package defpackage;

import android.os.Handler;
import android.os.Looper;
import defpackage.ak;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: ContextualPageActionController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a00 {
    public final zp1<Profile> a;
    public final zp1<Tab> b;
    public final m3 c;
    public i20 d;
    public final ArrayList e = new ArrayList();

    public a00(zp1 zp1Var, z2 z2Var, m3 m3Var, fp fpVar, zp1 zp1Var2) {
        this.a = zp1Var;
        this.b = z2Var;
        this.c = m3Var;
        zp1Var.p(new yq3(this, z2Var, fpVar, zp1Var2, 1));
    }

    public final Tab a() {
        Tab tab;
        zp1<Profile> zp1Var = this.a;
        if (zp1Var == null || zp1Var.get().h() || (tab = this.b.get()) == null || tab.isIncognito() || tab.isDestroyed()) {
            return null;
        }
        return tab;
    }

    public final void b() {
        Tab tabA = a();
        if (tabA == null) {
            c(0);
            return;
        }
        ArrayList arrayList = this.e;
        if (arrayList.isEmpty()) {
            return;
        }
        gm2 gm2Var = new gm2(new Handler(Looper.getMainLooper()), tabA, arrayList);
        gm2Var.e = new a9(this, 19, gm2Var);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(gm2Var.g, gm2Var);
        }
        gm2Var.h.postDelayed(new zx1(8, gm2Var), 100L);
    }

    public final void c(int i) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((a) it.next()).b(i, this.b.get());
        }
        m3 m3Var = this.c;
        if (i != 0) {
            m3Var.getClass();
        } else {
            i = m3Var.r;
        }
        oj2.U(i, 8, "Android.AdaptiveToolbarButton.Variant.OnPageLoad");
        ak.a aVar = m3Var.i;
        if (aVar == null || aVar.g != i) {
            m3Var.n(i);
            m3Var.m(true);
        }
    }

    /* JADX INFO: compiled from: ContextualPageActionController.java */
    public interface a {
        void a(Tab tab, gm2 gm2Var);

        default void b(int i, Tab tab) {
        }
    }
}
