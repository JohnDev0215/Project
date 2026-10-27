package org.chromium.chrome.browser.history;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import defpackage.ev0;
import defpackage.gv0;
import defpackage.js1;
import java.util.ArrayList;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.browser.bookmark_history.BookmarkHistoryActivity;
import org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar;

/* JADX INFO: loaded from: classes.dex */
public class HistoryManagerToolbar extends BookMarkSelectableListToolbar<ev0> {
    public gv0 t0;
    public js1 u0;

    public HistoryManagerToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar
    public final void E() {
        super.E();
        this.t0.M();
        boolean z = this.t0.l.s;
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        boolean z = this.b0;
        super.f(arrayList);
        if (this.b0) {
            int size = this.c0.c.size();
            View viewFindViewById = findViewById(R$id.selection_mode_delete_menu_id);
            if (viewFindViewById != null) {
                viewFindViewById.setContentDescription(getResources().getQuantityString(R$plurals.accessibility_remove_selected_items, size, Integer.valueOf(size)));
            }
            if (!z) {
                this.t0.K("SelectionEstablished");
            }
        }
        js1 js1Var = this.u0;
        if (js1Var != null) {
            ((BookmarkHistoryActivity.a) js1Var).a(arrayList, this.b0);
        }
    }

    public void setManager(gv0 gv0Var) {
        this.t0 = gv0Var;
    }

    public void setOnSelectListener(js1 js1Var) {
        this.u0 = js1Var;
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar
    public void setSearchEnabled(boolean z) {
        super.setSearchEnabled(z);
        this.t0.M();
        boolean z2 = this.t0.l.s;
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar
    public final void z(org.chromium.components.browser_ui.widget.selectable_list.a<ev0> aVar, int i, int i2, int i3, boolean z) {
        super.z(aVar, 0, i2, i3, z);
        setBackgroundColor(getContext().getColor(R.color.transparent));
    }
}
