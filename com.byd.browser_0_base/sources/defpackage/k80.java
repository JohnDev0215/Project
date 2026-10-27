package defpackage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: DeleteUndoOfflineItemFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class k80 extends pq1 {
    public final HashSet h;

    public k80(o21 o21Var) {
        super(o21Var);
        this.h = new HashSet();
        k();
    }

    @Override // defpackage.pq1, defpackage.qq1
    public final void d(Collection<OfflineItem> collection) {
        super.d(collection);
        Iterator<OfflineItem> it = collection.iterator();
        while (it.hasNext()) {
            this.h.remove(it.next().e);
        }
    }

    @Override // defpackage.pq1
    public final boolean j(OfflineItem offlineItem) {
        HashSet hashSet = this.h;
        if (hashSet == null) {
            return false;
        }
        return hashSet.contains(offlineItem.e);
    }
}
