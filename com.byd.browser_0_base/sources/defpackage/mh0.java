package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: EmptyTabModelFilter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mh0 extends p13 {
    public mh0(TabModel tabModel) {
        super(tabModel);
    }

    @Override // defpackage.w03
    public final int getCount() {
        return this.e.getCount();
    }

    @Override // defpackage.w03
    public final Tab getTabAt(int i) {
        return this.e.getTabAt(i);
    }

    @Override // defpackage.w03
    public final int index() {
        return this.e.index();
    }

    @Override // defpackage.w03
    public final boolean isIncognito() {
        return this.e.isIncognito();
    }

    @Override // defpackage.w03
    public final int r(Tab tab) {
        return this.e.r(tab);
    }

    @Override // defpackage.p13
    public final void H() {
    }

    @Override // defpackage.p13
    public final void I(Tab tab) {
    }

    @Override // defpackage.p13
    public final void o(Tab tab) {
    }

    @Override // defpackage.p13
    public final void y(Tab tab) {
    }

    @Override // defpackage.p13
    public final void z(Tab tab) {
    }

    @Override // defpackage.p13
    public final int E(int i, Tab tab) {
        return i;
    }
}
