package defpackage;

import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: TypeOfflineItemFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class eh3 extends pq1 {
    public int h;

    public eh3(lg2 lg2Var) {
        super(lg2Var);
        this.h = 0;
        k();
    }

    @Override // defpackage.pq1
    public final boolean j(OfflineItem offlineItem) {
        int i;
        int iIntValue = z7.l(offlineItem).intValue();
        return (iIntValue == 7 || (i = this.h) == 7) ? iIntValue != this.h : (i == 0 || i == iIntValue) ? false : true;
    }
}
