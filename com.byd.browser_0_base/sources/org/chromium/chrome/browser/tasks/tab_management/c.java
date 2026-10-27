package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import defpackage.nr;
import defpackage.sy0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.chromium.chrome.browser.tasks.tab_management.d;

/* JADX INFO: compiled from: MessageCardProviderCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    public final d a;
    public final ArrayList b = new ArrayList();

    public c(Context context, nr nrVar, sy0 sy0Var) {
        this.a = new d(context, nrVar, sy0Var);
    }

    public final ArrayList a() {
        LinkedHashMap linkedHashMap;
        d dVar = this.a;
        LinkedHashMap linkedHashMap2 = dVar.c;
        Iterator it = linkedHashMap2.keySet().iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            linkedHashMap = dVar.d;
            if (!zHasNext) {
                break;
            }
            int iIntValue = ((Integer) it.next()).intValue();
            if (!linkedHashMap.containsKey(Integer.valueOf(iIntValue))) {
                List list = (List) linkedHashMap2.get(Integer.valueOf(iIntValue));
                linkedHashMap.put(Integer.valueOf(iIntValue), (d.a) list.remove(0));
                if (list.size() == 0) {
                    it.remove();
                }
            }
        }
        Iterator it2 = linkedHashMap.values().iterator();
        while (it2.hasNext()) {
            ((d.a) it2.next()).b.l(f.s, dVar.b.get().booleanValue());
        }
        return new ArrayList(linkedHashMap.values());
    }

    public final void b(g gVar) {
        this.b.add(gVar);
        gVar.c(this.a);
    }
}
