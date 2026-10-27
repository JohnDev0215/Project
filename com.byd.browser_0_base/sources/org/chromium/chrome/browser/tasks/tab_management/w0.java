package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.ac2;
import defpackage.g03;
import defpackage.y13;
import defpackage.z7;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.z0;

/* JADX INFO: compiled from: TabSelectionEditorActionProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public class w0 {
    public final z0.d a;
    public final int b;

    public w0(z0.d dVar, int i) {
        this.a = dVar;
        this.b = i;
    }

    public void a(ArrayList arrayList, y13 y13Var) {
        z0.d dVar = this.a;
        int i = this.b;
        if (i == 1) {
            int iMax = -1;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                iMax = Math.max(z7.G(y13Var.z(), ((Tab) arrayList.get(i2)).getId()), iMax);
            }
            ((g03) y13Var.u().c()).R(arrayList, y13Var.z().getTabAt(iMax), false, true);
            dVar.b();
            ac2.c(8);
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            y13Var.z().p(arrayList);
            dVar.b();
            return;
        }
        g03 g03Var = (g03) y13Var.u().c();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            g03Var.U(((Tab) it.next()).getId());
        }
        dVar.b();
        ac2.c(9);
    }
}
