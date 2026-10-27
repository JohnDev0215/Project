package org.chromium.chrome.browser.suggestions.tile;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup;
import defpackage.w93;
import org.chromium.chrome.R$dimen;

/* JADX INFO: loaded from: classes2.dex */
public class TopSitesTileView extends SuggestionsTileView {
    public TopSitesTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.suggestions.tile.SuggestionsTileView
    public void setIconViewLayoutParams(w93 w93Var) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getIconView().getLayoutParams();
        Resources resources = getResources();
        if (w93Var.c == 1) {
            int i = R$dimen.tile_view_icon_size;
            marginLayoutParams.width = resources.getDimensionPixelOffset(i);
            marginLayoutParams.height = resources.getDimensionPixelSize(i);
            marginLayoutParams.topMargin = resources.getDimensionPixelSize(R$dimen.tile_view_icon_background_margin_top_modern);
        } else {
            int i2 = R$dimen.tile_view_icon_size_modern;
            marginLayoutParams.width = resources.getDimensionPixelSize(i2);
            marginLayoutParams.height = resources.getDimensionPixelSize(i2);
            marginLayoutParams.topMargin = resources.getDimensionPixelSize(R$dimen.tile_view_icon_margin_top_modern);
        }
        getIconView().setLayoutParams(marginLayoutParams);
    }
}
