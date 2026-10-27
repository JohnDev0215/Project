package com.android.launcher3.settings;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Property;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public class PreferenceHighlighter extends RecyclerView.ItemDecoration implements Runnable {
    private static final long HIGHLIGHT_DURATION = 15000;
    private static final long HIGHLIGHT_FADE_IN_DURATION = 200;
    private static final long HIGHLIGHT_FADE_OUT_DURATION = 500;
    private final int mIndex;
    private final RecyclerView mRv;
    private static final Property<PreferenceHighlighter, Integer> HIGHLIGHT_COLOR = new Property<PreferenceHighlighter, Integer>(Integer.TYPE, "highlightColor") { // from class: com.android.launcher3.settings.PreferenceHighlighter.1
        @Override // android.util.Property
        public Integer get(PreferenceHighlighter highlighter) {
            return Integer.valueOf(highlighter.mHighlightColor);
        }

        @Override // android.util.Property
        public void set(PreferenceHighlighter highlighter, Integer value) {
            highlighter.mHighlightColor = value.intValue();
            highlighter.mRv.invalidateItemDecorations();
        }
    };
    private static final int END_COLOR = GraphicsUtils.setColorAlphaBound(-1, 0);
    private final Paint mPaint = new Paint();
    private boolean mHighLightStarted = false;
    private int mHighlightColor = END_COLOR;

    public PreferenceHighlighter(RecyclerView rv, int index) {
        this.mRv = rv;
        this.mIndex = index;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mRv.addItemDecoration(this);
        this.mRv.smoothScrollToPosition(this.mIndex);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = parent.findViewHolderForAdapterPosition(this.mIndex);
        if (viewHolderFindViewHolderForAdapterPosition == null) {
            return;
        }
        if (this.mHighLightStarted || state.getRemainingScrollVertical() == 0) {
            if (!this.mHighLightStarted) {
                ObjectAnimator objectAnimatorOfArgb = ObjectAnimator.ofArgb(this, HIGHLIGHT_COLOR, END_COLOR, GraphicsUtils.setColorAlphaBound(Themes.getColorAccent(this.mRv.getContext()), 66));
                objectAnimatorOfArgb.setDuration(HIGHLIGHT_FADE_IN_DURATION);
                objectAnimatorOfArgb.setRepeatMode(2);
                objectAnimatorOfArgb.setRepeatCount(4);
                objectAnimatorOfArgb.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.settings.PreferenceHighlighter.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animation) {
                        PreferenceHighlighter.this.removeHighlight();
                    }
                });
                objectAnimatorOfArgb.start();
                this.mHighLightStarted = true;
            }
            View view = viewHolderFindViewHolderForAdapterPosition.itemView;
            this.mPaint.setColor(this.mHighlightColor);
            c.drawRect(0.0f, view.getY(), parent.getWidth(), view.getY() + view.getHeight(), this.mPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeHighlight() {
        ObjectAnimator objectAnimatorOfArgb = ObjectAnimator.ofArgb(this, HIGHLIGHT_COLOR, this.mHighlightColor, END_COLOR);
        objectAnimatorOfArgb.setDuration(HIGHLIGHT_FADE_OUT_DURATION);
        objectAnimatorOfArgb.setStartDelay(HIGHLIGHT_DURATION);
        objectAnimatorOfArgb.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.settings.PreferenceHighlighter.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                PreferenceHighlighter.this.mRv.removeItemDecoration(PreferenceHighlighter.this);
            }
        });
        objectAnimatorOfArgb.start();
    }
}
