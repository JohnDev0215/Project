package defpackage;

import defpackage.f81;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: DateSorterForCards.java */
/* JADX INFO: loaded from: classes.dex */
public final class x60 implements b81 {
    public b81 a;
    public final HashMap b = new HashMap();

    @Override // defpackage.b81
    public final b81 a(b81 b81Var) {
        this.a = b81Var;
        return b81Var;
    }

    @Override // defpackage.b81
    public final void b(ArrayList arrayList) {
        b81 b81Var = this.a;
        if (b81Var == null) {
            return;
        }
        HashMap map = this.b;
        map.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            f81 f81Var = (f81) it.next();
            if (a91.a(f81Var)) {
                OfflineItem offlineItem = ((f81.f) f81Var).e;
                int i = vh3.a;
                String strJ = oj2.j(1, offlineItem.w);
                map.put(strJ, Long.valueOf(Math.max(map.containsKey(strJ) ? ((Long) map.get(strJ)).longValue() : 0L, offlineItem.q)));
            }
        }
        Collections.sort(arrayList, new w60(0, this));
        b81Var.b(arrayList);
    }

    public final long d(f81 f81Var) {
        OfflineItem offlineItem = ((f81.f) f81Var).e;
        if (!a91.a(f81Var)) {
            return offlineItem.q;
        }
        int i = vh3.a;
        return ((Long) this.b.get(oj2.j(1, offlineItem.w))).longValue();
    }
}
