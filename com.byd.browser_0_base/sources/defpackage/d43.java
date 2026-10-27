package defpackage;

import android.content.SharedPreferences;
import android.util.Log;
import defpackage.zy2;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.base.b;

/* JADX INFO: compiled from: TabSuggestionsOrchestrator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d43 implements v80 {
    public static final long[] p;
    public final c43 e;
    public final q2 f;
    public final SharedPreferences g;
    public final LinkedList h;
    public LinkedList i;
    public zy2 j;
    public final y13 k;
    public final b<b43> l;
    public int m;
    public long n;
    public final int o;

    static {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        TimeUnit timeUnit2 = TimeUnit.HOURS;
        TimeUnit timeUnit3 = TimeUnit.DAYS;
        p = new long[]{timeUnit.toMillis(1L), timeUnit.toMillis(30L), timeUnit2.toMillis(1L), timeUnit2.toMillis(2L), timeUnit2.toMillis(12L), timeUnit3.toMillis(1L), timeUnit3.toMillis(2L), timeUnit3.toMillis(7L), timeUnit3.toMillis(10L)};
    }

    public d43(y13 y13Var, q2 q2Var) {
        SharedPreferences sharedPreferences = xz.a.getSharedPreferences("TabsuggestionsPreferences", 0);
        this.i = new LinkedList();
        this.o = 30000;
        this.k = y13Var;
        LinkedList linkedList = new LinkedList();
        this.h = linkedList;
        linkedList.add(new y33());
        this.l = new b<>();
        this.e = new c43(this, y13Var);
        this.f = q2Var;
        q2Var.f(this);
        this.g = sharedPreferences;
    }

    public static LinkedList a(LinkedList linkedList) {
        LinkedList linkedList2 = new LinkedList();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            v33 v33Var = (v33) it.next();
            int i = v33Var.b;
            List<zy2.b> list = v33Var.a;
            if (i != 0) {
                if (i != 1) {
                    Log.e("TabSuggestDetailed", String.format("Unknown action: %d", Integer.valueOf(i)));
                } else if (list.size() >= 3) {
                    linkedList2.add(v33Var);
                }
            } else if (!list.isEmpty()) {
                linkedList2.add(v33Var);
            }
        }
        Collections.sort(linkedList2, new j4(2));
        return linkedList2;
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        c43 c43Var = this.e;
        c43Var.c.u().e(c43Var.b);
        c43Var.a.destroy();
        this.f.e(this);
    }
}
