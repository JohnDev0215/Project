package defpackage;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.v0;

/* JADX INFO: compiled from: TabSelectionEditorUngroupAction.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l33 extends v0 {
    public l33(Drawable drawable) {
        super(R$id.tab_selection_editor_ungroup_menu_item, 0, R$plurals.tab_selection_editor_ungroup_tabs, Integer.valueOf(R$plurals.accessibility_tab_selection_editor_ungroup_tabs), drawable);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void c(List<Integer> list) {
        e(list.size(), !list.isEmpty());
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void d(ArrayList arrayList) {
        g03 g03Var = (g03) this.c.u().c();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            g03Var.U(((Tab) it.next()).getId());
        }
        ac2.c(7);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final boolean f() {
        return true;
    }
}
