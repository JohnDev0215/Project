package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import java.util.HashMap;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListLayout;

/* JADX INFO: loaded from: classes2.dex */
class TabSelectionEditorLayout extends SelectableListLayout<Integer> {
    public TabSelectionEditorToolbar s;
    public ViewGroup t;
    public final HashMap u;

    public TabSelectionEditorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.u = new HashMap();
    }
}
