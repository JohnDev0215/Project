package defpackage;

import J.N;
import android.content.Context;
import defpackage.wp2;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: OfflinePageTabObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fr1 extends nh0 implements NetworkChangeNotifier.a {
    public static HashMap l;
    public final wp2 e;
    public final wp2.b f;
    public final y13 g;
    public final dr1 h;
    public final HashMap i = new HashMap();
    public boolean j = false;
    public Tab k;

    /* JADX INFO: compiled from: OfflinePageTabObserver.java */
    public static class a {
        public boolean a = true;
        public boolean b = false;
    }

    public fr1(y13 y13Var, wp2 wp2Var, er1 er1Var) {
        this.e = wp2Var;
        this.f = er1Var;
        this.g = y13Var;
        this.h = new dr1(this, y13Var);
    }

    public final void A1(Tab tab) {
        boolean zY1 = y1(tab);
        HashMap map = this.i;
        if (zY1) {
            br1 br1Var = (br1) tab.B().b(br1.class);
            if (br1Var == null) {
                br1Var = (br1) tab.B().d(br1.class, new br1());
            }
            br1Var.e = false;
            br1Var.f = false;
            map.remove(Integer.valueOf(tab.getId()));
            tab.t(this);
        }
        if (map.isEmpty() && this.j) {
            NetworkChangeNotifier.f(this);
            this.j = false;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        A1(tab);
        this.e.a(this.f);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        this.k = null;
        this.e.a(this.f);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        if (y1(tab)) {
            ((a) this.i.get(Integer.valueOf(tab.getId()))).a = true;
            z1(tab, false);
        }
    }

    @Override // org.chromium.net.NetworkChangeNotifier.a
    public final void d(int i) {
        d.d();
        Objects.toString(this.f);
        z1(this.k, true);
        if (d.d()) {
            return;
        }
        Iterator it = this.i.values().iterator();
        while (it.hasNext()) {
            ((a) it.next()).b = false;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, Tab tab) {
        z1(tab, false);
        this.k = tab;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void x0(TabImpl tabImpl) {
        if (!d.e(tabImpl)) {
            A1(tabImpl);
        } else if (y1(tabImpl)) {
            HashMap map = this.i;
            ((a) map.get(Integer.valueOf(tabImpl.getId()))).a = false;
            ((a) map.get(Integer.valueOf(tabImpl.getId()))).b = false;
        }
        this.e.a(this.f);
    }

    public final boolean y1(Tab tab) {
        return this.i.containsKey(Integer.valueOf(tab.getId()));
    }

    public final void z1(Tab tab, boolean z) {
        if (tab == null || tab.isFrozen() || tab.isHidden() || !d.e(tab)) {
            return;
        }
        ((d.c) d.a()).getClass();
        OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(Profile.b(tab.c()));
        if ((offlinePageBridgeA == null ? false : N.MYT2RMuB(offlinePageBridgeA.a, offlinePageBridgeA, tab.c())) || !d.d()) {
            return;
        }
        boolean zY1 = y1(tab);
        HashMap map = this.i;
        if (zY1 && ((a) map.get(Integer.valueOf(tab.getId()))).a) {
            if (!(y1(tab) && ((a) map.get(Integer.valueOf(tab.getId()))).b) || z) {
                Context context = tab.getContext();
                int id = tab.getId();
                ((d.c) d.a()).getClass();
                if (id != -1) {
                    wp2.b bVar = this.f;
                    Objects.toString(bVar);
                    tp2 tp2VarA = tp2.a(context.getString(R$string.offline_pages_viewing_offline_page), bVar, 0, 3);
                    tp2VarA.i = false;
                    String string = context.getString(R$string.reload);
                    Integer numValueOf = Integer.valueOf(id);
                    tp2VarA.d = string;
                    tp2VarA.e = numValueOf;
                    tp2VarA.j = 6000;
                    this.e.c(tp2VarA);
                }
                ((a) map.get(Integer.valueOf(tab.getId()))).b = true;
            }
        }
    }
}
