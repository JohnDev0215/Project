package defpackage;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.CollectionUtil;
import org.chromium.base.b;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.offline_items_collection.UpdateDelta;
import org.chromium.components.offline_items_collection.a;

/* JADX INFO: compiled from: OfflineItemSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class sq1 implements rq1, a.InterfaceC0134a {
    public final a e;
    public final HashMap f = new HashMap();
    public final b<qq1> g = new b<>();
    public boolean h;
    public boolean i;

    public sq1(a aVar) {
        this.e = aVar;
        aVar.k(this);
        aVar.b(new so(25, this));
    }

    @Override // defpackage.rq1
    public final void a(qq1 qq1Var) {
        this.g.a(qq1Var);
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void b(List<OfflineItem> list) {
        HashSet hashSet = new HashSet();
        for (OfflineItem offlineItem : list) {
            HashMap map = this.f;
            if (map.containsKey(offlineItem.e)) {
                d(offlineItem, null);
            } else {
                map.put(offlineItem.e, offlineItem);
                hashSet.add(offlineItem);
            }
        }
        if (hashSet.size() <= 0) {
            return;
        }
        Iterator<qq1> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((qq1) aVar.next()).b(hashSet);
            }
        }
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void d(OfflineItem offlineItem, UpdateDelta updateDelta) {
        HashMap map = this.f;
        OfflineItem offlineItem2 = (OfflineItem) map.get(offlineItem.e);
        if (offlineItem2 == null) {
            b(Collections.singletonList(offlineItem));
            return;
        }
        map.put(offlineItem.e, offlineItem);
        Iterator<qq1> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((qq1) aVar.next()).e(offlineItem2, offlineItem);
            }
        }
    }

    @Override // org.chromium.components.offline_items_collection.a.InterfaceC0134a
    public final void e(ny nyVar) {
        OfflineItem offlineItem = (OfflineItem) this.f.remove(nyVar);
        if (offlineItem == null) {
            return;
        }
        HashSet hashSetE = CollectionUtil.e(offlineItem);
        Iterator<qq1> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((qq1) aVar.next()).d(hashSetE);
            }
        }
    }

    @Override // defpackage.rq1
    public final boolean f() {
        return this.h;
    }

    @Override // defpackage.rq1
    public final void g(qq1 qq1Var) {
        this.g.c(qq1Var);
    }

    @Override // defpackage.rq1
    public final Collection<OfflineItem> h() {
        return this.f.values();
    }
}
