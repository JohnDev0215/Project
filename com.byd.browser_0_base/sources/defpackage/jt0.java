package defpackage;

import android.util.Pair;
import defpackage.f81;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: GroupCardLabelAdder.java */
/* JADX INFO: loaded from: classes.dex */
public final class jt0 implements b81 {
    public b81 a;
    public final gj3 b;
    public long c;

    public jt0(gj3 gj3Var) {
        this.b = gj3Var;
    }

    public static Pair<Date, String> f(f81 f81Var) {
        OfflineItem offlineItem = ((f81.f) f81Var).e;
        Date time = jl.a(offlineItem.q).getTime();
        int i = vh3.a;
        return Pair.create(time, oj2.j(1, offlineItem.w));
    }

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
        this.c = 200000L;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        f81 f81Var = null;
        while (it.hasNext()) {
            f81 f81Var2 = (f81) it.next();
            boolean z = false;
            if (f81Var2 instanceof f81.f) {
                ((f81.f) f81Var2).g = false;
            }
            if (a91.a(f81Var2) && a91.a(f81Var) && f(f81Var2).equals(f(f81Var))) {
                z = true;
            }
            if (z) {
                arrayList3.add(f81Var2);
            } else {
                e(arrayList3, arrayList2);
                arrayList3.clear();
                if (a91.a(f81Var2)) {
                    arrayList3.add(f81Var2);
                } else {
                    arrayList2.add(f81Var2);
                }
            }
            f81Var = f81Var2;
        }
        e(arrayList3, arrayList2);
        b81Var.b(arrayList2);
    }

    public final f81.b d(int i) {
        long j = this.c;
        this.c = 1 + j;
        return new f81.b(j, i);
    }

    public final void e(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        int size = arrayList.size();
        gj3 gj3Var = this.b;
        gj3Var.getClass();
        if (size < 3) {
            arrayList2.addAll(arrayList);
            return;
        }
        Pair<Date, String> pairF = f((f81) arrayList.get(0));
        String strJ = ((f81.f) arrayList.get(0)).e.w.j();
        Object obj = gj3Var.a;
        Map map = (Map) obj;
        if (!map.containsKey(pairF)) {
            map.put(pairF, 1);
        }
        arrayList2.add(d(1));
        arrayList2.add(new f81.d(pairF, strJ));
        Map map2 = (Map) obj;
        int iIntValue = (map2.containsKey(pairF) ? ((Integer) map2.get(pairF)).intValue() : 1) * 3;
        int iMin = Math.min(iIntValue, arrayList.size());
        for (int i = 0; i < iMin; i++) {
            f81.f fVar = (f81.f) arrayList.get(i);
            fVar.g = true;
            arrayList2.add(fVar);
            if (i < iMin - 1) {
                arrayList2.add(d(2));
            }
        }
        if (arrayList.size() > iIntValue) {
            arrayList2.add(d(2));
            arrayList2.add(new f81.c(pairF));
        }
        arrayList2.add(d(3));
    }
}
