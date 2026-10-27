package org.chromium.chrome.browser.history_clusters;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$menu;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes.dex */
class HistoryClustersToolbar extends SelectableListToolbar<PropertyModel> {
    public HistoryClustersToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        o(R$menu.history_clusters_menu);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        super.f(arrayList);
        if (this.b0) {
            getMenu().findItem(R$id.selection_mode_copy_link).setVisible(this.c0.c.size() == 1);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar
    public final void z(SelectableListToolbar.b bVar, int i, int i2) {
        super.z(bVar, i, i2);
    }
}
