package org.chromium.chrome.browser.suggestions.tile;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ViewGroup;
import defpackage.ao2;
import defpackage.w93;
import org.chromium.chrome.R$dimen;
import org.chromium.components.browser_ui.widget.tile.TileView;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class SuggestionsTileView extends TileView {
    public ao2 j;

    public SuggestionsTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ao2 getData() {
        return this.j;
    }

    public GURL getUrl() {
        return this.j.b;
    }

    public void setIconViewLayoutParams(w93 w93Var) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getIconView().getLayoutParams();
        Resources resources = getResources();
        int i = w93Var.c;
        if (i == 2 || i == 3) {
            int i2 = R$dimen.tile_view_monogram_size_modern;
            marginLayoutParams.width = resources.getDimensionPixelSize(i2);
            marginLayoutParams.height = resources.getDimensionPixelSize(i2);
            marginLayoutParams.topMargin = resources.getDimensionPixelSize(R$dimen.tile_view_monogram_margin_top_modern);
        } else {
            int i3 = R$dimen.tile_view_icon_size_modern;
            marginLayoutParams.width = resources.getDimensionPixelSize(i3);
            marginLayoutParams.height = resources.getDimensionPixelSize(i3);
            marginLayoutParams.topMargin = resources.getDimensionPixelSize(R$dimen.tile_view_icon_margin_top_modern);
        }
        getIconView().setLayoutParams(marginLayoutParams);
    }
}
