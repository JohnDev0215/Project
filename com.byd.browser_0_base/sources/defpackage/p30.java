package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p30 implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Tab f;

    public /* synthetic */ p30(int i, Tab tab) {
        this.e = i;
        this.f = tab;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        Tab tab = this.f;
        switch (i) {
            case 0:
                return tab.getContext();
            case 1:
                return tab;
            default:
                CriticalPersistedTabData criticalPersistedTabData = new CriticalPersistedTabData(tab, "", "", -1, tab.getId(), -1L, null, -1, "", 0, null, 0);
                criticalPersistedTabData.l();
                return criticalPersistedTabData;
        }
    }
}
