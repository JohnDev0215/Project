package defpackage;

import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: OffTheRecordOfflineItemFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class cq1 extends pq1 {
    public final boolean h;

    public cq1(boolean z, sq1 sq1Var) {
        super(sq1Var);
        this.h = z;
        k();
    }

    @Override // defpackage.pq1
    public final boolean j(OfflineItem offlineItem) {
        if (offlineItem.y) {
            return (this.h && OTRProfileID.a(offlineItem.z).equals(OTRProfileID.b)) ? false : true;
        }
        return false;
    }
}
