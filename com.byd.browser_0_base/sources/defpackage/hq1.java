package defpackage;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.UpdateDelta;
import org.chromium.components.offline_items_collection.a;

/* JADX INFO: compiled from: OfflineContentAvailabilityStatusProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class hq1 implements a.InterfaceC0134a {
    public static hq1 g;
    public final HashSet e = new HashSet();
    public final HashSet f = new HashSet();

    public static hq1 a() {
        if (g == null) {
            g = new hq1();
            a aVarZ = a8.z();
            aVarZ.k(g);
            hq1 hq1Var = g;
            Objects.requireNonNull(hq1Var);
            aVarZ.b(new c3(17, hq1Var));
        }
        return g;
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void b(List<OfflineItem> list) {
        if (list.isEmpty()) {
            return;
        }
        for (OfflineItem offlineItem : list) {
            if (offlineItem.j) {
                this.e.add(offlineItem.e);
            }
            if (!offlineItem.i) {
                this.f.add(offlineItem.e);
            }
        }
        c();
    }

    public final void c() {
        SharedPreferencesManager.getInstance().l("Chrome.NTPExploreOfflineCard.HasExploreOfflineContent", !this.e.isEmpty());
        SharedPreferencesManager.getInstance().l("Chrome.OfflineIndicatorV2.HasPersistentOfflineContent", !this.f.isEmpty());
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void e(ny nyVar) {
        boolean zRemove = this.e.remove(nyVar);
        boolean zRemove2 = this.f.remove(nyVar);
        if (zRemove || zRemove2) {
            c();
        }
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void d(OfflineItem offlineItem, UpdateDelta updateDelta) {
    }
}
