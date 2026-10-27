package defpackage;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.g03;
import defpackage.wp2;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: UndoGroupSnackbarController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ni3 implements wp2.b {
    public final Context e;
    public final y13 f;
    public final wp2 g;
    public final ki3 h;
    public final li3 i;
    public final mi3 j;

    /* JADX INFO: compiled from: UndoGroupSnackbarController.java */
    public class a {
        public final Tab a;
        public final int b;
        public final int c;

        public a(Tab tab, int i, int i2) {
            this.a = tab;
            this.b = i;
            this.c = i2;
        }
    }

    public ni3(AppCompatActivity appCompatActivity, y13 y13Var, wp2 wp2Var) {
        this.e = appCompatActivity;
        this.f = y13Var;
        this.g = wp2Var;
        ki3 ki3Var = new ki3(this);
        this.h = ki3Var;
        ((g03) y13Var.u().d(false)).K(ki3Var);
        ((g03) y13Var.u().d(true)).K(ki3Var);
        li3 li3Var = new li3(this);
        this.i = li3Var;
        y13Var.m(li3Var);
        this.j = new mi3(this, y13Var);
    }

    @Override // wp2.b
    public final void j(Object obj) {
        List list = (List) obj;
        g03 g03Var = (g03) this.f.u().c();
        for (int size = list.size() - 1; size >= 0; size--) {
            a aVar = (a) list.get(size);
            Tab tab = aVar.a;
            g03Var.getClass();
            if (tab.isInitialized()) {
                int id = tab.getId();
                TabModel tabModel = g03Var.e;
                int iG = z7.G(tabModel, id);
                g03Var.s = true;
                HashMap map = g03Var.m;
                int i = aVar.c;
                boolean z = map.get(Integer.valueOf(i)) != null;
                g03.b0(i, tab);
                int i2 = aVar.b;
                if (iG == i2) {
                    g03Var.h1(i2, iG, tab);
                } else {
                    if (iG < i2) {
                        i2++;
                    }
                    tabModel.s(tab.getId(), i2);
                }
                g03Var.s = false;
                if (z) {
                    g03.b bVar = (g03.b) map.get(Integer.valueOf(i));
                    Iterator<g03.a> it = g03Var.k.iterator();
                    while (true) {
                        b.a aVar2 = (b.a) it;
                        if (aVar2.hasNext()) {
                            g03.a aVar3 = (g03.a) aVar2.next();
                            int i3 = bVar.b;
                            aVar3.d(tab);
                        }
                    }
                }
            }
        }
    }
}
