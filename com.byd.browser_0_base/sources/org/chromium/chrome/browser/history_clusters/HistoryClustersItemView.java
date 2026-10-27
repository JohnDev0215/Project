package org.chromium.chrome.browser.history_clusters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageButton;
import defpackage.bz;
import defpackage.lb0;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView;

/* JADX INFO: loaded from: classes.dex */
class HistoryClustersItemView extends SelectableItemView<ClusterVisit> {
    public lb0 y;

    public HistoryClustersItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView, org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        lb0 lb0Var = new lb0(getContext(), R$style.HorizontalDivider);
        this.y = lb0Var;
        lb0Var.a(this, generateDefaultLayoutParams());
        this.r.setVisibility(0);
        this.r.setImageResource(R$drawable.btn_delete_24dp);
        this.r.setContentDescription(getContext().getString(R$string.remove));
        this.r.setImageTintList(bz.b(getContext(), R$color.default_icon_color_secondary_tint_list));
        this.r.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        AppCompatImageButton appCompatImageButton = this.r;
        Resources resources = getResources();
        int i = R$dimen.visit_item_remove_button_lateral_padding;
        appCompatImageButton.setPaddingRelative(resources.getDimensionPixelSize(i), getPaddingTop(), getResources().getDimensionPixelSize(i), getPaddingBottom());
    }

    public final void q(Drawable drawable) {
        super.setStartIconDrawable(drawable);
    }

    public void setEndButtonClickHandler(View.OnClickListener onClickListener) {
        this.r.setOnClickListener(onClickListener);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void m() {
    }
}
