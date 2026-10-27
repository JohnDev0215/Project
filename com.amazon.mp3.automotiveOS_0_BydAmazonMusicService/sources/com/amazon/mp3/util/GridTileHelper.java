package com.amazon.mp3.util;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.browse.metadata.RecommendationReason;

/* JADX INFO: loaded from: classes4.dex */
public class GridTileHelper {
    public static void setRecommendationVisibility(boolean show, View gridTile, RecommendationReason recommendationReason) {
        String reason;
        if (recommendationReason == null || TextUtils.isEmpty(recommendationReason.getReason()) || !show) {
            show = false;
            reason = null;
        } else {
            reason = recommendationReason.getReason();
        }
        setDetailSectionLayoutParams(gridTile, show, reason);
    }

    private static void setDetailSectionLayoutParams(View gridTile, boolean showReason, String recommendationReason) {
        int dimensionPixelOffset;
        TextView textView = (TextView) gridTile.findViewById(R.id.title);
        TextView textView2 = (TextView) gridTile.findViewById(R.id.recommendation_reason);
        if (showReason) {
            dimensionPixelOffset = gridTile.getResources().getDimensionPixelOffset(R.dimen.grid_title_bottom_padding_with_reason_code);
            textView2.setText(recommendationReason);
            textView2.setVisibility(0);
        } else {
            dimensionPixelOffset = gridTile.getResources().getDimensionPixelOffset(R.dimen.grid_title_bottom_padding_no_reason);
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
        }
        textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), dimensionPixelOffset);
    }
}
