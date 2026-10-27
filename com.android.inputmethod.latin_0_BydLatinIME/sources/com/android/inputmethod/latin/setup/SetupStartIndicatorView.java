package com.android.inputmethod.latin.setup;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOverlay;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
public final class SetupStartIndicatorView extends LinearLayout {
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return super.generateLayoutParams(lp);
    }

    @Override // android.view.ViewGroup, android.view.View
    public /* bridge */ /* synthetic */ ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    public SetupStartIndicatorView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.setup_start_indicator_label, this);
        ((LabelView) findViewById(R.id.setup_start_label)).setIndicatorView(findViewById(R.id.setup_start_indicator));
    }

    public static final class LabelView extends TextView {
        private View mIndicatorView;

        public LabelView(final Context context, final AttributeSet attrs) {
            super(context, attrs);
        }

        public void setIndicatorView(final View indicatorView) {
            this.mIndicatorView = indicatorView;
        }

        @Override // android.widget.TextView, android.view.View
        protected void drawableStateChanged() {
            super.drawableStateChanged();
            for (int i : getDrawableState()) {
                if (i == 16842919) {
                    updateIndicatorView(true);
                    return;
                }
            }
            updateIndicatorView(false);
        }

        private void updateIndicatorView(final boolean pressed) {
            View view = this.mIndicatorView;
            if (view != null) {
                view.setPressed(pressed);
                this.mIndicatorView.invalidate();
            }
        }
    }

    public static final class IndicatorView extends View {
        private final ColorStateList mIndicatorColor;
        private final Paint mIndicatorPaint;
        private final Path mIndicatorPath;

        public IndicatorView(final Context context, final AttributeSet attrs) {
            super(context, attrs);
            this.mIndicatorPath = new Path();
            Paint paint = new Paint();
            this.mIndicatorPaint = paint;
            this.mIndicatorColor = getResources().getColorStateList(R.color.setup_step_action_background);
            paint.setStyle(Paint.Style.FILL);
        }

        @Override // android.view.View
        protected void onDraw(final Canvas canvas) {
            super.onDraw(canvas);
            int layoutDirection = ViewCompat.getLayoutDirection(this);
            int width = getWidth();
            float height = getHeight();
            float f = height / 2.0f;
            Path path = this.mIndicatorPath;
            path.rewind();
            if (layoutDirection == 1) {
                float f2 = width;
                path.moveTo(f2, 0.0f);
                path.lineTo(0.0f, f);
                path.lineTo(f2, height);
            } else {
                path.moveTo(0.0f, 0.0f);
                path.lineTo(width, f);
                path.lineTo(0.0f, height);
            }
            path.close();
            this.mIndicatorPaint.setColor(this.mIndicatorColor.getColorForState(getDrawableState(), 0));
            canvas.drawPath(path, this.mIndicatorPaint);
        }
    }
}
