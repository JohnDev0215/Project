package defpackage;

import android.app.Activity;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: TabWindowManagerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o53 implements ApplicationStatus.c, n53 {
    public final d70 e;
    public final f9 f;
    public final int g;
    public final ArrayList h = new ArrayList();
    public final HashMap i = new HashMap();

    public o53(d70 d70Var, g9 g9Var, int i) {
        this.e = d70Var;
        this.f = g9Var;
        ApplicationStatus.g(this);
        this.g = i;
        for (int i2 = 0; i2 < this.g; i2++) {
            this.h.add(null);
        }
    }

    @Override // defpackage.n53
    public final TabModel a(Tab tab) {
        TabModel tabModelJ;
        int i = 0;
        while (true) {
            ArrayList arrayList = this.h;
            if (i >= arrayList.size()) {
                return null;
            }
            y13 y13Var = (y13) arrayList.get(i);
            if (y13Var != null && (tabModelJ = y13Var.j(tab.getId())) != null) {
                return tabModelJ;
            }
            i++;
        }
    }

    @Override // defpackage.n53
    public final int b(Activity activity) {
        y13 y13Var;
        int iIndexOf;
        if (activity == null || (y13Var = (y13) this.i.get(activity)) == null || (iIndexOf = this.h.indexOf(y13Var)) == -1) {
            return -1;
        }
        return iIndexOf;
    }

    public final int c() {
        return this.i.size();
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        ArrayList arrayList;
        int iIndexOf;
        if (i == 6) {
            HashMap map = this.i;
            if (!map.containsKey(activity) || (iIndexOf = (arrayList = this.h).indexOf(map.remove(activity))) < 0) {
                return;
            }
            arrayList.set(iIndexOf, null);
        }
    }

    public final Tab e(int i) {
        Tab tabS;
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.h;
            if (i2 >= arrayList.size()) {
                f9 f9Var = this.f;
                if (f9Var.a(i)) {
                    return f9Var.b().get(i).d();
                }
                return null;
            }
            y13 y13Var = (y13) arrayList.get(i2);
            if (y13Var != null && (tabS = y13Var.s(i)) != null) {
                return tabS;
            }
            i2++;
        }
    }

    public final y13 f(int i) {
        return (y13) this.h.get(i);
    }

    public final Pair g(Activity activity, gz2 gz2Var, pq pqVar, int i) {
        if (i >= 0) {
            ArrayList arrayList = this.h;
            if (i < arrayList.size()) {
                HashMap map = this.i;
                int i2 = 0;
                if (map.get(activity) != null) {
                    y13 y13Var = (y13) map.get(activity);
                    while (i2 < arrayList.size()) {
                        if (arrayList.get(i2) == y13Var) {
                            return Pair.create(Integer.valueOf(i2), y13Var);
                        }
                        i2++;
                    }
                    throw new IllegalStateException("TabModelSelector is assigned to an Activity but has no index.");
                }
                if (arrayList.get(i) != null) {
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        }
                        if (arrayList.get(i2) == null) {
                            i = i2;
                            break;
                        }
                        i2++;
                    }
                }
                if (arrayList.get(i) != null) {
                    return null;
                }
                this.e.getClass();
                d23 d23Var = new d23(null, gz2Var, new qr(activity), pqVar, h9.a, true, 0);
                arrayList.set(i, d23Var);
                map.put(activity, d23Var);
                return Pair.create(Integer.valueOf(i), d23Var);
            }
        }
        return null;
    }
}
