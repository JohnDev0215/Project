package defpackage;

import android.util.Pair;
import java.util.List;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* JADX INFO: compiled from: TabContextMenuPopulator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bz2 implements vz {
    public final vz a;
    public final TabImpl b;

    public bz2(vz vzVar, Tab tab) {
        this.a = vzVar;
        this.b = (TabImpl) tab;
    }

    @Override // defpackage.vz
    public final List<Pair<Integer, ya1>> a() {
        List<Pair<Integer, ya1>> listA = this.a.a();
        b.a aVarN = this.b.N();
        while (aVarN.hasNext()) {
            ((n23) aVarN.next()).V();
        }
        return listA;
    }

    @Override // defpackage.vz
    public final boolean b(int i) {
        return this.a.b(i);
    }

    @Override // defpackage.vz
    public final void c() {
        this.a.c();
    }

    @Override // defpackage.vz
    public final String d() {
        return this.a.d();
    }

    @Override // defpackage.vz
    public final void e() {
        this.a.e();
    }

    @Override // defpackage.vz
    public final boolean isIncognito() {
        return this.a.isIncognito();
    }
}
