package org.chromium.chrome.browser.suggestions.tile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import org.chromium.components.browser_ui.widget.tile.TileView;

/* JADX INFO: loaded from: classes2.dex */
public class MostVisitedTilesCarouselLayout extends LinearLayout {
    public MostVisitedTilesCarouselLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setEdgePaddings(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        TileView tileView = (TileView) getChildAt(0);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) tileView.getLayoutParams();
        if (i != marginLayoutParams.getMarginStart()) {
            marginLayoutParams.setMarginStart(i);
            tileView.setLayoutParams(marginLayoutParams);
        }
        TileView tileView2 = (TileView) getChildAt(childCount - 1);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) tileView2.getLayoutParams();
        if (i != marginLayoutParams2.getMarginEnd()) {
            marginLayoutParams2.setMarginEnd(i);
            tileView2.setLayoutParams(marginLayoutParams2);
        }
    }

    public void setIntervalPaddings(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        for (int i2 = 1; i2 < childCount; i2++) {
            TileView tileView = (TileView) getChildAt(i2);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) tileView.getLayoutParams();
            if (i != marginLayoutParams.getMarginStart()) {
                marginLayoutParams.setMarginStart(i);
                tileView.setLayoutParams(marginLayoutParams);
            }
        }
    }
}
