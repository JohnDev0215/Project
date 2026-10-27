package defpackage;

import defpackage.f81;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: DateSorter.java */
/* JADX INFO: loaded from: classes.dex */
public final class v60 implements b81 {
    public final um0 a;
    public b81 b;

    public v60(um0 um0Var) {
        this.a = um0Var;
    }

    @Override // defpackage.b81
    public final b81 a(b81 b81Var) {
        this.b = b81Var;
        return b81Var;
    }

    @Override // defpackage.b81
    public final void b(ArrayList arrayList) {
        b81 b81Var = this.b;
        if (b81Var == null) {
            return;
        }
        Collections.sort(arrayList, new Comparator() { // from class: u60
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int[] iArr;
                v60 v60Var = this.e;
                v60Var.getClass();
                OfflineItem offlineItem = ((f81.f) ((f81) obj)).e;
                OfflineItem offlineItem2 = ((f81.f) ((f81) obj2)).e;
                int i = 0;
                um0 um0Var = v60Var.a;
                boolean z = um0Var != null && um0Var.a(offlineItem);
                boolean z2 = um0Var != null && um0Var.a(offlineItem2);
                int i2 = z == z2 ? 0 : (!z || z2) ? 1 : -1;
                if (i2 != 0) {
                    return i2;
                }
                int iCompareTo = jl.a(offlineItem2.q).getTime().compareTo(jl.a(offlineItem.q).getTime());
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
                int iIntValue = z7.l(offlineItem).intValue();
                int iIntValue2 = z7.l(offlineItem2).intValue();
                int i3 = 0;
                while (true) {
                    iArr = a91.a;
                    if (i3 >= 8) {
                        i3 = 0;
                        break;
                    }
                    if (iArr[i3] == iIntValue) {
                        break;
                    }
                    i3++;
                }
                int i4 = 0;
                while (true) {
                    if (i4 >= 8) {
                        i4 = 0;
                        break;
                    }
                    if (iArr[i4] == iIntValue2) {
                        break;
                    }
                    i4++;
                }
                if (i3 < i4) {
                    i = -1;
                } else if (i3 != i4) {
                    i = 1;
                }
                if (i != 0) {
                    return i;
                }
                int iCompare = Long.compare(offlineItem2.q, offlineItem.q);
                if (iCompare != 0) {
                    return iCompare;
                }
                int iCompareTo2 = offlineItem.e.a.compareTo(offlineItem2.e.a);
                return iCompareTo2 != 0 ? iCompareTo2 : offlineItem.e.b.compareTo(offlineItem2.e.b);
            }
        });
        b81Var.b(arrayList);
    }
}
