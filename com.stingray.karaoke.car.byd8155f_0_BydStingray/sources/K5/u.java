package K5;

import com.stingray.kca.model.KaraokeItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f3659a = new LinkedHashMap();

    public final void a(KaraokeItem karaokeItem) {
        A7.m.f(karaokeItem, "item");
        String str = karaokeItem.f13107a;
        if (str != null) {
            this.f3659a.put(str, karaokeItem);
            List list = karaokeItem.f13111g;
            if (list != null) {
                b(list);
            }
        }
    }

    public final void b(List list) {
        A7.m.f(list, "items");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            a((KaraokeItem) it.next());
        }
    }

    public final void c(String str, ArrayList arrayList) {
        A7.m.f(str, "id");
        KaraokeItem karaokeItem = (KaraokeItem) this.f3659a.get(str);
        List list = karaokeItem != null ? karaokeItem.f13111g : null;
        if (list == null) {
            list = n7.u.l;
        }
        ArrayList arrayListA0 = AbstractC1998m.A0(list);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = arrayListA0.iterator();
        while (it.hasNext()) {
            String str2 = ((KaraokeItem) it.next()).f13107a;
            if (str2 != null) {
                linkedHashSet.add(str2);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            KaraokeItem karaokeItem2 = (KaraokeItem) it2.next();
            if (!AbstractC1998m.T(linkedHashSet, karaokeItem2.f13107a)) {
                arrayListA0.add(karaokeItem2);
            }
        }
        if (karaokeItem != null) {
            karaokeItem.f13111g = AbstractC1998m.A0(AbstractC1998m.U(arrayListA0));
        }
    }

    public final KaraokeItem d(String str) {
        return (KaraokeItem) this.f3659a.get(str);
    }
}
