package defpackage;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.v0;

/* JADX INFO: compiled from: TabSelectionEditorCloseAction.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f33 extends v0 {
    public f33(Drawable drawable) {
        super(R$id.tab_selection_editor_close_menu_item, 0, R$plurals.tab_selection_editor_close_tabs, Integer.valueOf(R$plurals.accessibility_tab_selection_editor_close_tabs), drawable);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void c(List<Integer> list) {
        e(this.f.booleanValue() ? v0.a(list, this.c) : list.size(), !list.isEmpty());
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final void d(ArrayList arrayList) {
        if (arrayList.size() == 1) {
            this.c.z().m((Tab) arrayList.get(0), false, true);
        } else {
            this.c.z().p(arrayList);
        }
        ac2.c(1);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.v0
    public final boolean f() {
        return true;
    }
}
