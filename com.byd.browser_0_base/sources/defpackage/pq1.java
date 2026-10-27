package defpackage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.CollectionUtil;
import org.chromium.base.b;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: OfflineItemFilter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class pq1 implements qq1, rq1 {
    public final rq1 e;
    public final HashSet f = new HashSet();
    public final b<qq1> g = new b<>();

    public pq1(rq1 rq1Var) {
        this.e = rq1Var;
        rq1Var.a(this);
    }

    @Override // defpackage.rq1
    public final void a(qq1 qq1Var) {
        this.g.a(qq1Var);
    }

    @Override // defpackage.qq1
    public final void b(Collection<OfflineItem> collection) {
        i(collection);
    }

    @Override // defpackage.qq1
    public final void c() {
        Iterator<qq1> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((qq1) aVar.next()).c();
            }
        }
    }

    @Override // defpackage.qq1
    public void d(Collection<OfflineItem> collection) {
        HashSet hashSet = new HashSet();
        for (OfflineItem offlineItem : collection) {
            if (this.f.remove(offlineItem)) {
                hashSet.add(offlineItem);
            }
        }
        if (hashSet.isEmpty()) {
            return;
        }
        Iterator<qq1> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((qq1) aVar.next()).d(hashSet);
            }
        }
    }

    @Override // defpackage.qq1
    public final void e(OfflineItem offlineItem, OfflineItem offlineItem2) {
        HashSet hashSet = this.f;
        boolean zRemove = hashSet.remove(offlineItem);
        boolean z = !j(offlineItem2);
        b<qq1> bVar = this.g;
        if (zRemove && z) {
            hashSet.add(offlineItem2);
            Iterator<qq1> it = bVar.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((qq1) aVar.next()).e(offlineItem, offlineItem2);
                }
            }
        } else if (!zRemove && z) {
            hashSet.add(offlineItem2);
            HashSet hashSetE = CollectionUtil.e(offlineItem2);
            Iterator<qq1> it2 = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    return;
                } else {
                    ((qq1) aVar2.next()).b(hashSetE);
                }
            }
        } else {
            if (!zRemove || z) {
                return;
            }
            HashSet hashSetE2 = CollectionUtil.e(offlineItem);
            Iterator<qq1> it3 = bVar.iterator();
            while (true) {
                b.a aVar3 = (b.a) it3;
                if (!aVar3.hasNext()) {
                    return;
                } else {
                    ((qq1) aVar3.next()).d(hashSetE2);
                }
            }
        }
    }

    @Override // defpackage.rq1
    public final boolean f() {
        return this.e.f();
    }

    @Override // defpackage.rq1
    public final void g(qq1 qq1Var) {
        this.g.c(qq1Var);
    }

    @Override // defpackage.rq1
    public final Collection h() {
        return this.f;
    }

    public final void i(Collection<OfflineItem> collection) {
        HashSet hashSet = new HashSet();
        for (OfflineItem offlineItem : collection) {
            if (!j(offlineItem) && this.f.add(offlineItem)) {
                hashSet.add(offlineItem);
            }
        }
        if (hashSet.isEmpty()) {
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

    public abstract boolean j(OfflineItem offlineItem);

    public final void k() {
        HashSet hashSet = new HashSet();
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            OfflineItem offlineItem = (OfflineItem) it.next();
            if (j(offlineItem)) {
                it.remove();
                hashSet.add(offlineItem);
            }
        }
        if (!hashSet.isEmpty()) {
            Iterator<qq1> it2 = this.g.iterator();
            while (true) {
                b.a aVar = (b.a) it2;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((qq1) aVar.next()).d(hashSet);
                }
            }
        }
        i(this.e.h());
    }
}
