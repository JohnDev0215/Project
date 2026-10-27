package p;

import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class e2 extends q30 {
    @Override // p.q30
    public boolean f(g2 g2Var, c2 c2Var, c2 c2Var2) {
        synchronized (g2Var) {
            try {
                if (g2Var.e != c2Var) {
                    return false;
                }
                g2Var.e = c2Var2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p.q30
    public boolean g(g2 g2Var, Object obj, Object obj2) {
        synchronized (g2Var) {
            try {
                if (g2Var.c != obj) {
                    return false;
                }
                g2Var.c = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p.q30
    public boolean h(g2 g2Var, f2 f2Var, f2 f2Var2) {
        synchronized (g2Var) {
            try {
                if (g2Var.k != f2Var) {
                    return false;
                }
                g2Var.k = f2Var2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p.q30
    public Object n(Intent intent, int i) {
        if (i == -1 && intent != null) {
            String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
            int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
            if (intArrayExtra == null || stringArrayExtra == null) {
                return kotlin.collections.c.h0();
            }
            ArrayList arrayList = new ArrayList(intArrayExtra.length);
            for (int i2 : intArrayExtra) {
                arrayList.add(Boolean.valueOf(i2 == 0));
            }
            ArrayList arrayListQ0 = kotlin.collections.b.q0(stringArrayExtra);
            Iterator it = arrayListQ0.iterator();
            Iterator it2 = arrayList.iterator();
            ArrayList arrayList2 = new ArrayList(Math.min(lu.W(arrayListQ0), lu.W(arrayList)));
            while (it.hasNext() && it2.hasNext()) {
                arrayList2.add(new Pair(it.next(), it2.next()));
            }
            return kotlin.collections.c.k0(arrayList2);
        }
        return kotlin.collections.c.h0();
    }

    @Override // p.q30
    public void p(f2 f2Var, f2 f2Var2) {
        f2Var.b = f2Var2;
    }

    @Override // p.q30
    public void q(f2 f2Var, Thread thread) {
        f2Var.a = thread;
    }
}
