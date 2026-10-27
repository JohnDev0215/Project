package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import com.android.systemui.Interpolators;
import com.android.systemui.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class BarTransitions {
    public static final int BACKGROUND_DURATION = 200;
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_COLORS = false;
    public static final int LIGHTS_IN_DURATION = 250;
    public static final int LIGHTS_OUT_DURATION = 1500;
    public static final int MODE_LIGHTS_OUT = 3;
    public static final int MODE_LIGHTS_OUT_TRANSPARENT = 6;
    public static final int MODE_OPAQUE = 4;
    public static final int MODE_SEMI_TRANSPARENT = 1;
    public static final int MODE_TRANSLUCENT = 2;
    public static final int MODE_TRANSPARENT = 0;
    public static final int MODE_WARNING = 5;
    private boolean mAlwaysOpaque = false;
    protected final BarBackgroundDrawable mBarBackground;
    private int mMode;
    private final String mTag;
    private final View mView;

    @Retention(RetentionPolicy.SOURCE)
    public @interface TransitionMode {
    }

    public void destroy() {
    }

    protected boolean isLightsOut(int mode) {
        return mode == 3 || mode == 6;
    }

    public void setAutoDim(boolean autoDim) {
    }

    public BarTransitions(View view, int gradientResourceId) {
        this.mTag = "BarTransitions." + view.getClass().getSimpleName();
        this.mView = view;
        BarBackgroundDrawable barBackgroundDrawable = new BarBackgroundDrawable(view.getContext(), gradientResourceId);
        this.mBarBackground = barBackgroundDrawable;
        view.setBackground(barBackgroundDrawable);
    }

    public int getMode() {
        return this.mMode;
    }

    public void setAlwaysOpaque(boolean alwaysOpaque) {
        this.mAlwaysOpaque = alwaysOpaque;
    }

    public boolean isAlwaysOpaque() {
        return this.mAlwaysOpaque;
    }

    public void transitionTo(int mode, boolean animate) {
        if (isAlwaysOpaque() && (mode == 1 || mode == 2 || mode == 0)) {
            mode = 4;
        }
        if (isAlwaysOpaque() && mode == 6) {
            mode = 3;
        }
        int i = this.mMode;
        if (i == mode) {
            return;
        }
        this.mMode = mode;
        onTransition(i, mode, animate);
    }

    protected void onTransition(int oldMode, int newMode, boolean animate) {
        applyModeBackground(oldMode, newMode, animate);
    }

    protected void applyModeBackground(int oldMode, int newMode, boolean animate) {
        this.mBarBackground.applyModeBackground(oldMode, newMode, animate);
    }

    public static String modeToString(int mode) {
        if (mode == 4) {
            return "MODE_OPAQUE";
        }
        if (mode == 1) {
            return "MODE_SEMI_TRANSPARENT";
        }
        if (mode == 2) {
            return "MODE_TRANSLUCENT";
        }
        if (mode == 3) {
            return "MODE_LIGHTS_OUT";
        }
        if (mode == 0) {
            return "MODE_TRANSPARENT";
        }
        if (mode == 5) {
            return "MODE_WARNING";
        }
        if (mode == 6) {
            return "MODE_LIGHTS_OUT_TRANSPARENT";
        }
        throw new IllegalArgumentException("Unknown mode " + mode);
    }

    public void finishAnimations() {
        this.mBarBackground.finishAnimation();
    }

    protected static class BarBackgroundDrawable extends Drawable {
        private boolean mAnimating;
        private int mColor;
        private int mColorStart;
        private long mEndTime;
        private Rect mFrame;
        private final Drawable mGradient;
        private int mGradientAlpha;
        private int mGradientAlphaStart;
        private final int mOpaque;
        private final int mSemiTransparent;
        private long mStartTime;
        private PorterDuffColorFilter mTintFilter;
        private final int mTransparent;
        private final int mWarning;
        private int mMode = -1;
        private Paint mPaint = new Paint();

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int alpha) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
        }

        public BarBackgroundDrawable(Context context, int gradientResourceId) {
            context.getResources();
            this.mOpaque = context.getColor(R.color.system_bar_background_opaque);
            this.mSemiTransparent = context.getColor(android.R.color.dim_foreground_inverse_holo_dark);
            this.mTransparent = context.getColor(R.color.system_bar_background_transparent);
            this.mWarning = 0;
            this.mGradient = context.getDrawable(gradientResourceId);
        }

        public void setFrame(Rect frame) {
            this.mFrame = frame;
        }

        @Override // android.graphics.drawable.Drawable
        public void setTint(int color) {
            PorterDuffColorFilter porterDuffColorFilter = this.mTintFilter;
            PorterDuff.Mode mode = porterDuffColorFilter == null ? PorterDuff.Mode.SRC_IN : porterDuffColorFilter.getMode();
            PorterDuffColorFilter porterDuffColorFilter2 = this.mTintFilter;
            if (porterDuffColorFilter2 == null || porterDuffColorFilter2.getColor() != color) {
                this.mTintFilter = new PorterDuffColorFilter(color, mode);
            }
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setTintMode(PorterDuff.Mode tintMode) {
            PorterDuffColorFilter porterDuffColorFilter = this.mTintFilter;
            int color = porterDuffColorFilter == null ? 0 : porterDuffColorFilter.getColor();
            PorterDuffColorFilter porterDuffColorFilter2 = this.mTintFilter;
            if (porterDuffColorFilter2 == null || porterDuffColorFilter2.getMode() != tintMode) {
                this.mTintFilter = new PorterDuffColorFilter(color, tintMode);
            }
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            this.mGradient.setBounds(bounds);
        }

        public void applyModeBackground(int oldMode, int newMode, boolean animate) {
            if (this.mMode == newMode) {
                return;
            }
            this.mMode = newMode;
            this.mAnimating = animate;
            if (animate) {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                this.mStartTime = jElapsedRealtime;
                this.mEndTime = jElapsedRealtime + 200;
                this.mGradientAlphaStart = this.mGradientAlpha;
                this.mColorStart = this.mColor;
            }
            invalidateSelf();
        }

        public void finishAnimation() {
            if (this.mAnimating) {
                this.mAnimating = false;
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            int i;
            int i2 = this.mMode;
            if (i2 == 5) {
                i = this.mWarning;
            } else if (i2 == 2 || i2 == 1) {
                i = this.mSemiTransparent;
            } else if (i2 == 0 || i2 == 6) {
                i = this.mTransparent;
            } else {
                i = this.mOpaque;
            }
            if (!this.mAnimating) {
                this.mColor = i;
                this.mGradientAlpha = 0;
            } else {
                if (SystemClock.elapsedRealtime() >= this.mEndTime) {
                    this.mAnimating = false;
                    this.mColor = i;
                    this.mGradientAlpha = 0;
                } else {
                    long j = this.mStartTime;
                    float fMax = Math.max(0.0f, Math.min(Interpolators.LINEAR.getInterpolation((r3 - j) / (r5 - j)), 1.0f));
                    float f = 1.0f - fMax;
                    this.mGradientAlpha = (int) ((0 * fMax) + (this.mGradientAlphaStart * f));
                    this.mColor = Color.argb((int) ((Color.alpha(i) * fMax) + (Color.alpha(this.mColorStart) * f)), (int) ((Color.red(i) * fMax) + (Color.red(this.mColorStart) * f)), (int) ((Color.green(i) * fMax) + (Color.green(this.mColorStart) * f)), (int) ((fMax * Color.blue(i)) + (Color.blue(this.mColorStart) * f)));
                }
            }
            int i3 = this.mGradientAlpha;
            if (i3 > 0) {
                this.mGradient.setAlpha(i3);
                this.mGradient.draw(canvas);
            }
            if (Color.alpha(this.mColor) > 0) {
                this.mPaint.setColor(this.mColor);
                PorterDuffColorFilter porterDuffColorFilter = this.mTintFilter;
                if (porterDuffColorFilter != null) {
                    this.mPaint.setColorFilter(porterDuffColorFilter);
                }
                Rect rect = this.mFrame;
                if (rect != null) {
                    canvas.drawRect(rect, this.mPaint);
                } else {
                    canvas.drawPaint(this.mPaint);
                }
            }
            if (this.mAnimating) {
                invalidateSelf();
            }
        }
    }
}
