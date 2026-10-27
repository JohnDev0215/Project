package defpackage;

import defpackage.f81;
import java.util.ArrayList;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: DateLabelAdder.java */
/* JADX INFO: loaded from: classes.dex */
public final class d60 implements b81 {
    public final um0 a;
    public b81 b;

    public d60(um0 um0Var) {
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
        ArrayList arrayList2 = new ArrayList();
        OfflineItem offlineItem = null;
        for (int i = 0; i < arrayList.size(); i++) {
            f81 f81Var = (f81) arrayList.get(i);
            if (f81Var instanceof f81.f) {
                OfflineItem offlineItem2 = ((f81.f) f81Var).e;
                int iD = d(offlineItem2);
                if (iD != d(offlineItem)) {
                    arrayList2.add(new f81.h(d(offlineItem2), jl.a(offlineItem2.q).getTime().getTime()));
                } else if (iD == 0) {
                    if (!jl.a(offlineItem2.q).getTime().equals(offlineItem == null ? null : jl.a(offlineItem.q).getTime())) {
                        arrayList2.add(new f81.h(d(offlineItem2), jl.a(offlineItem2.q).getTime().getTime()));
                    }
                }
                arrayList2.add(f81Var);
                offlineItem = offlineItem2;
            }
        }
        b81Var.b(arrayList2);
    }

    public final int d(OfflineItem offlineItem) {
        if (offlineItem == null) {
            return -1;
        }
        um0 um0Var = this.a;
        return um0Var != null && um0Var.a(offlineItem) ? 1 : 0;
    }
}
