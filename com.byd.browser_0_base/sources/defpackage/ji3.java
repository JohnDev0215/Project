package defpackage;

import android.content.Context;
import defpackage.wp2;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: UndoBarController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ji3 implements wp2.b {
    public final y13 e;
    public final ii3 f;
    public final wp2.c g;
    public final Context h;

    public ji3(Context context, a23 a23Var, qs qsVar, js jsVar) {
        this.g = qsVar;
        this.e = a23Var;
        this.h = context;
        this.f = new ii3(this, jsVar, context);
    }

    @Override // wp2.b
    public final void d(Object obj) {
        boolean z = obj instanceof Integer;
        y13 y13Var = this.e;
        if (z) {
            int iIntValue = ((Integer) obj).intValue();
            TabModel tabModelJ = y13Var.j(iIntValue);
            if (tabModelJ != null) {
                tabModelJ.x(iIntValue);
                return;
            }
            return;
        }
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            int id = ((Tab) it.next()).getId();
            TabModel tabModelJ2 = y13Var.j(id);
            if (tabModelJ2 != null) {
                tabModelJ2.x(id);
            }
        }
    }

    @Override // wp2.b
    public final void j(Object obj) {
        boolean z = obj instanceof Integer;
        y13 y13Var = this.e;
        if (z) {
            int iIntValue = ((Integer) obj).intValue();
            TabModel tabModelJ = y13Var.j(iIntValue);
            if (tabModelJ != null) {
                tabModelJ.n(iIntValue);
                return;
            }
            return;
        }
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            int id = ((Tab) it.next()).getId();
            TabModel tabModelJ2 = y13Var.j(id);
            if (tabModelJ2 != null) {
                tabModelJ2.n(id);
            }
        }
        TabModel tabModelZ = y13Var.z();
        if (tabModelZ != null) {
            tabModelZ.v();
        }
    }
}
