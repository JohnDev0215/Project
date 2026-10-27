package org.chromium.chrome.browser.tasks.tab_management;

import android.graphics.drawable.Drawable;
import defpackage.ac2;
import defpackage.e33;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.z0;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabSelectionEditorSelectionAction.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f1 extends v0 {
    public int g;
    public final Drawable h;
    public final Drawable i;

    public f1(Drawable drawable, Drawable drawable2) {
        super(R$id.tab_selection_editor_selection_menu_item, 1, R$string.tab_selection_editor_select_all, null, drawable);
        this.g = 0;
        this.h = drawable;
        this.i = drawable2;
        this.b.l(e33.p, false);
        g(1);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void c(List<Integer> list) {
        e(list.size(), true);
        a1 a1Var = (a1) this.d;
        g(a1Var.j.c.size() == a1Var.r.size() ? 2 : 1);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void d(ArrayList arrayList) {
        int i = this.g;
        if (i != 1) {
            if (i == 2) {
                a1 a1Var = (a1) this.d;
                org.chromium.components.browser_ui.widget.selectable_list.a<Integer> aVar = a1Var.j;
                Set<Integer> set = aVar.c;
                set.clear();
                aVar.h(set);
                ((z0.b) a1Var.h).a(a1Var.r, 0, null, true);
                ac2.c(4);
                return;
            }
            return;
        }
        a1 a1Var2 = (a1) this.d;
        org.chromium.components.browser_ui.widget.selectable_list.a<Integer> aVar2 = a1Var2.j;
        Set<Integer> set2 = aVar2.c;
        ArrayList arrayList2 = a1Var2.r;
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            set2.add(Integer.valueOf(((Tab) it.next()).getId()));
        }
        aVar2.h(set2);
        ((z0.b) a1Var2.h).a(arrayList2, arrayList2.size(), null, true);
        ac2.c(3);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final boolean f() {
        return false;
    }

    public final void g(int i) {
        if (this.g == i) {
            return;
        }
        this.g = i;
        PropertyModel propertyModel = this.b;
        if (i == 1) {
            propertyModel.n(e33.e, R$string.tab_selection_editor_select_all);
            propertyModel.p(e33.j, this.h);
        } else if (i == 2) {
            propertyModel.n(e33.e, R$string.tab_selection_editor_deselect_all);
            propertyModel.p(e33.j, this.i);
        }
    }
}
