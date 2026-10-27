package org.chromium.chrome.browser.tasks.tab_management;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import defpackage.dr;
import defpackage.e33;
import defpackage.g03;
import defpackage.xz;
import defpackage.y13;
import defpackage.z7;
import defpackage.zx1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabSelectionEditorAction.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class v0 {
    public final org.chromium.base.b<b> a = new org.chromium.base.b<>();
    public final PropertyModel b;
    public y13 c;
    public a d;
    public org.chromium.components.browser_ui.widget.selectable_list.a<Integer> e;
    public Boolean f;

    /* JADX INFO: compiled from: TabSelectionEditorAction.java */
    public interface a {
    }

    /* JADX INFO: compiled from: TabSelectionEditorAction.java */
    public interface b {
        void a();
    }

    public v0(int i, int i2, int i3, Integer num, Drawable drawable) {
        boolean zEquals = "plurals".equals(xz.a.getResources().getResourceTypeName(i3));
        PropertyModel.b bVar = new PropertyModel.b(e33.r);
        bVar.c(e33.a, i);
        bVar.c(e33.b, 0);
        bVar.c(e33.c, 2);
        bVar.c(e33.d, i2);
        bVar.c(e33.e, i3);
        bVar.b(e33.f, zEquals);
        bVar.e(e33.g, num);
        bVar.e(e33.j, drawable);
        bVar.b(e33.k, false);
        bVar.c(e33.l, 0);
        bVar.e(e33.m, ColorStateList.valueOf(0));
        bVar.e(e33.n, ColorStateList.valueOf(0));
        bVar.e(e33.o, new zx1(19, this));
        bVar.b(e33.p, true);
        bVar.e(e33.q, new dr(9, this));
        this.b = bVar.a();
    }

    public static int a(List list, y13 y13Var) {
        g03.b bVar;
        if (!(y13Var.u().c() instanceof g03)) {
            return list.size();
        }
        g03 g03Var = (g03) y13Var.u().c();
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            Tab tabF = z7.F(y13Var.z(), ((Integer) it.next()).intValue());
            g03Var.getClass();
            int iO = g03.O(tabF);
            int iC = 1;
            if (iO != -1 && (bVar = (g03.b) g03Var.m.get(Integer.valueOf(iO))) != null) {
                iC = bVar.c();
            }
            i += iC;
        }
        return i;
    }

    public final ArrayList b() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.e.c.iterator();
        while (it.hasNext()) {
            Tab tabF = z7.F(this.c.z(), it.next().intValue());
            if (tabF != null) {
                arrayList.add(tabF);
            }
        }
        return arrayList;
    }

    public abstract void c(List<Integer> list);

    public abstract void d(ArrayList arrayList);

    public final void e(int i, boolean z) {
        PropertyModel propertyModel = this.b;
        propertyModel.l(e33.k, z);
        propertyModel.n(e33.l, i);
    }

    public abstract boolean f();
}
