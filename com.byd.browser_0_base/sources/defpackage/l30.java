package defpackage;

import defpackage.a30;
import java.util.HashSet;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: CustomTabCountObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class l30 extends a30.a {
    public final HashSet<Integer> a = new HashSet<>();

    public l30(a30 a30Var) {
        a30Var.a.a(this);
        Tab tab = a30Var.b;
        if (tab != null) {
            d(tab);
        }
    }

    @Override // a30.a
    public final void a() {
        this.a.clear();
    }

    @Override // a30.a
    public final void b(Tab tab) {
        d(tab);
    }

    @Override // a30.a
    public final void c(Tab tab) {
        d(tab);
    }

    public final void d(Tab tab) {
        Integer numValueOf = Integer.valueOf(tab.getId());
        HashSet<Integer> hashSet = this.a;
        if (hashSet.add(numValueOf)) {
            oj2.Q(hashSet.size(), "CustomTabs.TabCounts.UniqueTabsSeen");
        }
    }
}
