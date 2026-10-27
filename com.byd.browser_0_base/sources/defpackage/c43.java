package defpackage;

import J.N;
import android.content.SharedPreferences;
import android.os.SystemClock;
import defpackage.zy2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: TabSuggestionsOrchestrator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c43 extends cz2 {
    public final /* synthetic */ d43 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c43(d43 d43Var, y13 y13Var) {
        super(y13Var);
        this.d = d43Var;
    }

    @Override // defpackage.cz2
    public final void a() {
        boolean z;
        synchronized (this.d.i) {
            d43 d43Var = this.d;
            if (d43Var.j != null) {
                Iterator<b43> it = d43Var.l.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((b43) aVar.next()).a();
                    }
                }
            }
        }
        d43 d43Var2 = this.d;
        synchronized (d43Var2.g) {
            long j = d43Var2.g.getLong("LastTimestamp", -1L);
            if (j != -1) {
                long j2 = d43Var2.g.getLong("BackoffCountKey", -1L);
                if (j2 > 0) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long j3 = j2 - (jCurrentTimeMillis - j);
                    SharedPreferences.Editor editorEdit = d43Var2.g.edit();
                    editorEdit.putLong("LastTimestamp", jCurrentTimeMillis);
                    editorEdit.putLong("BackoffCountKey", j3);
                    editorEdit.apply();
                    z = j3 > 0;
                }
            }
            z = false;
        }
        if (z) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime < d43Var2.n) {
            return;
        }
        int i = d43Var2.o;
        sk skVar = bq.a;
        d43Var2.n = jElapsedRealtime + ((long) N.M37SqSAy("CloseTabSuggestions", "min_time_between_prefetches", i));
        p13 p13VarC = d43Var2.k.u().c();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < p13VarC.getCount(); i2++) {
            Tab tabAt = p13VarC.getTabAt(i2);
            if (tabAt != null) {
                List<Tab> listB = p13VarC.B(tabAt.getId());
                if (listB.size() > 1) {
                    ArrayList arrayList3 = new ArrayList();
                    for (int i3 = 0; i3 < listB.size(); i3++) {
                        Tab tab = listB.get(i3);
                        if (!tab.n()) {
                            arrayList3.add(tab);
                        }
                    }
                    int i4 = CriticalPersistedTabData.m(tabAt).q;
                    ArrayList arrayList4 = new ArrayList();
                    Iterator it2 = arrayList3.iterator();
                    while (it2.hasNext()) {
                        arrayList4.add(zy2.b.a((Tab) it2.next()));
                    }
                    arrayList2.add(new zy2.a(i4, arrayList4));
                } else if (!tabAt.n()) {
                    arrayList.add(zy2.b.a(tabAt));
                }
            }
        }
        zy2 zy2Var = new zy2(arrayList, arrayList2);
        synchronized (d43Var2.i) {
            d43Var2.m = 0;
            d43Var2.j = zy2Var;
            d43Var2.i = new LinkedList();
            for (z33 z33Var : d43Var2.h) {
                z33Var.isEnabled();
                d43Var2.m++;
                z33Var.a(zy2Var, new jb1(11, d43Var2));
            }
        }
    }
}
