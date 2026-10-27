package defpackage;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.v0;

/* JADX INFO: compiled from: TabSelectionEditorGroupAction.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h33 extends v0 {
    public h33(Drawable drawable) {
        super(R$id.tab_selection_editor_group_menu_item, 0, R$plurals.tab_selection_editor_group_tabs, Integer.valueOf(R$plurals.accessibility_tab_selection_editor_group_tabs), drawable);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void c(List<Integer> list) {
        e(this.f.booleanValue() ? v0.a(list, this.c) : list.size(), list.size() > 1);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void d(ArrayList arrayList) {
        g03 g03Var = (g03) this.c.u().c();
        HashSet hashSet = new HashSet(arrayList);
        TabModel tabModelZ = this.c.z();
        boolean zBooleanValue = this.f.booleanValue();
        Iterator it = arrayList.iterator();
        int iMin = -1;
        int iMax = -1;
        while (it.hasNext()) {
            Tab tab = (Tab) it.next();
            int iG = z7.G(tabModelZ, tab.getId());
            iMax = Math.max(iG, iMax);
            if (zBooleanValue && g03Var.F(tab)) {
                iMin = iMin == -1 ? iG : Math.min(iG, iMin);
            }
        }
        if (iMin == -1) {
            iMin = iMax;
        }
        Tab tabAt = tabModelZ.getTabAt(iMin);
        hashSet.removeAll(g03Var.B(tabAt.getId()));
        ArrayList arrayList2 = new ArrayList(hashSet.size());
        TabModel tabModelZ2 = this.c.z();
        for (int i = 0; i < tabModelZ2.getCount(); i++) {
            Tab tabAt2 = tabModelZ2.getTabAt(i);
            if (hashSet.contains(tabAt2)) {
                arrayList2.add(tabAt2);
            }
        }
        g03Var.R(arrayList2, tabAt, true, true);
        ac2.c(2);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final boolean f() {
        return true;
    }
}
