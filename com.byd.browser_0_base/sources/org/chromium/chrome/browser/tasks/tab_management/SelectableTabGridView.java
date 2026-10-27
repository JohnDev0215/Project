package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import defpackage.p5;
import defpackage.rb2;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView;

/* JADX INFO: loaded from: classes2.dex */
public class SelectableTabGridView extends SelectableItemView<Integer> {
    public SelectableTabGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSelectionOnLongClick(false);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void m() {
        super.onClick(this);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView, org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        Resources resources = getResources();
        int i = R$drawable.tab_grid_selection_list_icon;
        Resources.Theme theme = getContext().getTheme();
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        Drawable drawable = resources.getDrawable(i, theme);
        ImageView imageView = (ImageView) j(R$id.action_button);
        if (imageView != null) {
            imageView.setBackground(new InsetDrawable(drawable, (int) getContext().getResources().getDimension(R$dimen.selection_tab_grid_toggle_button_inset)));
            removeView(this.p);
        } else {
            imageView = this.r;
            imageView.setVisibility(0);
            Resources resources2 = getResources();
            int i2 = R$dimen.selection_tab_list_toggle_button_lateral_inset;
            int dimension = (int) resources2.getDimension(i2);
            Resources resources3 = getResources();
            int i3 = R$dimen.selection_tab_list_toggle_button_vertical_inset;
            imageView.setBackground(new InsetDrawable(drawable, dimension, (int) resources3.getDimension(i3), (int) getResources().getDimension(i2), (int) getResources().getDimension(i3)));
            this.q.setBackground(null);
        }
        imageView.getBackground().setLevel(getResources().getInteger(R$integer.list_item_level_default));
        imageView.setImageDrawable(p5.b(getContext(), R$drawable.ic_check_googblue_20dp_animated));
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCheckable(true);
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView, org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void p(boolean z) {
    }
}
