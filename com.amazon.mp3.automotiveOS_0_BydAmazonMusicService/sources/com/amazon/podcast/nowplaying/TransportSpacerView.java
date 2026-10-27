package com.amazon.podcast.nowplaying;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$layout;

/* JADX INFO: loaded from: classes5.dex */
final class TransportSpacerView extends LinearLayout {
    private static int maxSpacerWidth;

    public TransportSpacerView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        View.inflate(getContext(), R$layout.podcast_now_playing_spacer_template, this);
        maxSpacerWidth = getResources().getDimensionPixelSize(R$dimen.podcast_spacer_large);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int widthMeasureSpec, final int heightMeasureSpec) {
        if (View.MeasureSpec.getMode(widthMeasureSpec) != 0) {
            int size = View.MeasureSpec.getSize(widthMeasureSpec);
            int i = maxSpacerWidth;
            if (size > i) {
                widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
