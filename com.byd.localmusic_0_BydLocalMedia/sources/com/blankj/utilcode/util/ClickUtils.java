package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes4.dex */
public class ClickUtils {
    private static final long DEBOUNCING_DEFAULT_VALUE = 200;
    private static final int DEBOUNCING_TAG = -7;
    private static final float PRESSED_BG_ALPHA_DEFAULT_VALUE = 0.9f;
    private static final int PRESSED_BG_ALPHA_STYLE = 4;
    private static final float PRESSED_BG_DARK_DEFAULT_VALUE = 0.9f;
    private static final int PRESSED_BG_DARK_STYLE = 5;
    private static final float PRESSED_VIEW_ALPHA_DEFAULT_VALUE = 0.8f;
    private static final int PRESSED_VIEW_ALPHA_SRC_TAG = -3;
    private static final int PRESSED_VIEW_ALPHA_TAG = -2;
    private static final float PRESSED_VIEW_SCALE_DEFAULT_VALUE = -0.06f;
    private static final int PRESSED_VIEW_SCALE_TAG = -1;

    private ClickUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void applyPressedViewScale(View... viewArr) {
        applyPressedViewScale(viewArr, (float[]) null);
    }

    public static void applyPressedViewScale(View[] viewArr, float[] fArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (int i = 0; i < viewArr.length; i++) {
            if (fArr == null || i >= fArr.length) {
                applyPressedViewScale(viewArr[i], PRESSED_VIEW_SCALE_DEFAULT_VALUE);
            } else {
                applyPressedViewScale(viewArr[i], fArr[i]);
            }
        }
    }

    public static void applyPressedViewScale(View view, float f) {
        if (view == null) {
            return;
        }
        view.setTag(-1, Float.valueOf(f));
        view.setClickable(true);
        view.setOnTouchListener(OnUtilsTouchListener.getInstance());
    }

    public static void applyPressedViewAlpha(View... viewArr) {
        applyPressedViewAlpha(viewArr, (float[]) null);
    }

    public static void applyPressedViewAlpha(View[] viewArr, float[] fArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (int i = 0; i < viewArr.length; i++) {
            if (fArr == null || i >= fArr.length) {
                applyPressedViewAlpha(viewArr[i], PRESSED_VIEW_ALPHA_DEFAULT_VALUE);
            } else {
                applyPressedViewAlpha(viewArr[i], fArr[i]);
            }
        }
    }

    public static void applyPressedViewAlpha(View view, float f) {
        if (view == null) {
            return;
        }
        view.setTag(-2, Float.valueOf(f));
        view.setTag(-3, Float.valueOf(view.getAlpha()));
        view.setClickable(true);
        view.setOnTouchListener(OnUtilsTouchListener.getInstance());
    }

    public static void applyPressedBgAlpha(View view) {
        applyPressedBgAlpha(view, 0.9f);
    }

    public static void applyPressedBgAlpha(View view, float f) {
        applyPressedBgStyle(view, 4, f);
    }

    public static void applyPressedBgDark(View view) {
        applyPressedBgDark(view, 0.9f);
    }

    public static void applyPressedBgDark(View view, float f) {
        applyPressedBgStyle(view, 5, f);
    }

    private static void applyPressedBgStyle(View view, int i, float f) {
        if (view == null) {
            return;
        }
        Drawable background = view.getBackground();
        int i2 = -i;
        Object tag = view.getTag(i2);
        if (tag instanceof Drawable) {
            ViewCompat.setBackground(view, (Drawable) tag);
            return;
        }
        Drawable drawableCreateStyleDrawable = createStyleDrawable(background, i, f);
        ViewCompat.setBackground(view, drawableCreateStyleDrawable);
        view.setTag(i2, drawableCreateStyleDrawable);
    }

    private static Drawable createStyleDrawable(Drawable drawable, int i, float f) {
        if (drawable == null) {
            drawable = new ColorDrawable(0);
        }
        if (drawable.getConstantState() == null) {
            return drawable;
        }
        Drawable drawableMutate = drawable.getConstantState().newDrawable().mutate();
        if (i == 4) {
            drawableMutate = createAlphaDrawable(drawableMutate, f);
        } else if (i == 5) {
            drawableMutate = createDarkDrawable(drawableMutate, f);
        }
        drawable.getConstantState().newDrawable().mutate();
        Drawable drawableCreateAlphaDrawable = createAlphaDrawable(drawableMutate, 0.5f);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, drawableMutate);
        stateListDrawable.addState(new int[]{-16842910}, drawableCreateAlphaDrawable);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);
        return stateListDrawable;
    }

    private static Drawable createAlphaDrawable(Drawable drawable, float f) {
        if (Build.VERSION.SDK_INT <= 19 && !(drawable instanceof ColorDrawable)) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setAlpha((int) (f * 255.0f));
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return new BitmapDrawable(Resources.getSystem(), bitmapCreateBitmap);
        }
        drawable.setAlpha((int) (f * 255.0f));
        return drawable;
    }

    private static Drawable createDarkDrawable(Drawable drawable, float f) {
        if (Build.VERSION.SDK_INT <= 19 && !(drawable instanceof ColorDrawable)) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setColorFilter(getDarkColorFilter(f));
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return new BitmapDrawable(Resources.getSystem(), bitmapCreateBitmap);
        }
        drawable.setColorFilter(getDarkColorFilter(f));
        return drawable;
    }

    private static ColorMatrixColorFilter getDarkColorFilter(float f) {
        return new ColorMatrixColorFilter(new ColorMatrix(new float[]{f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f}));
    }

    public static void applySingleDebouncing(View view, View.OnClickListener onClickListener) {
        applySingleDebouncing(new View[]{view}, onClickListener);
    }

    public static void applySingleDebouncing(View view, long j, View.OnClickListener onClickListener) {
        applySingleDebouncing(new View[]{view}, j, onClickListener);
    }

    public static void applySingleDebouncing(View[] viewArr, View.OnClickListener onClickListener) {
        applySingleDebouncing(viewArr, DEBOUNCING_DEFAULT_VALUE, onClickListener);
    }

    public static void applySingleDebouncing(View[] viewArr, long j, View.OnClickListener onClickListener) {
        applyDebouncing(viewArr, false, j, onClickListener);
    }

    public static void applyGlobalDebouncing(View view, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(new View[]{view}, onClickListener);
    }

    public static void applyGlobalDebouncing(View view, long j, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(new View[]{view}, j, onClickListener);
    }

    public static void applyGlobalDebouncing(View[] viewArr, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(viewArr, DEBOUNCING_DEFAULT_VALUE, onClickListener);
    }

    public static void applyGlobalDebouncing(View[] viewArr, long j, View.OnClickListener onClickListener) {
        applyDebouncing(viewArr, true, j, onClickListener);
    }

    private static void applyDebouncing(View[] viewArr, boolean z, long j, final View.OnClickListener onClickListener) {
        if (viewArr == null || viewArr.length == 0 || onClickListener == null) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setOnClickListener(new OnDebouncingClickListener(z, j) { // from class: com.blankj.utilcode.util.ClickUtils.1
                    @Override // com.blankj.utilcode.util.ClickUtils.OnDebouncingClickListener
                    public void onDebouncingClick(View view2) {
                        onClickListener.onClick(view2);
                    }
                });
            }
        }
    }

    private static int dp2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static abstract class OnDebouncingClickListener implements View.OnClickListener {
        private static final Runnable ENABLE_AGAIN = new Runnable() { // from class: com.blankj.utilcode.util.ClickUtils.OnDebouncingClickListener.1
            @Override // java.lang.Runnable
            public void run() {
                boolean unused = OnDebouncingClickListener.mEnabled = true;
            }
        };
        private static boolean mEnabled = true;
        private long mDuration;
        private boolean mIsGlobal;

        public abstract void onDebouncingClick(View view);

        private static boolean isValid(View view, long j) {
            if (view == null) {
                throw new NullPointerException("Argument 'view' of type View (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            Object tag = view.getTag(-7);
            if (!(tag instanceof Long)) {
                view.setTag(-7, Long.valueOf(jCurrentTimeMillis));
                return true;
            }
            if (jCurrentTimeMillis - ((Long) tag).longValue() <= j) {
                return false;
            }
            view.setTag(-7, Long.valueOf(jCurrentTimeMillis));
            return true;
        }

        public OnDebouncingClickListener() {
            this(true, ClickUtils.DEBOUNCING_DEFAULT_VALUE);
        }

        public OnDebouncingClickListener(boolean z) {
            this(z, ClickUtils.DEBOUNCING_DEFAULT_VALUE);
        }

        public OnDebouncingClickListener(long j) {
            this(true, j);
        }

        public OnDebouncingClickListener(boolean z, long j) {
            this.mIsGlobal = z;
            this.mDuration = j;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (this.mIsGlobal) {
                if (mEnabled) {
                    mEnabled = false;
                    view.postDelayed(ENABLE_AGAIN, this.mDuration);
                    onDebouncingClick(view);
                    return;
                }
                return;
            }
            if (isValid(view, this.mDuration)) {
                onDebouncingClick(view);
            }
        }
    }

    public static abstract class OnMultiClickListener implements View.OnClickListener {
        private static final long INTERVAL_DEFAULT_VALUE = 666;
        private int mClickCount;
        private final long mClickInterval;
        private long mLastClickTime;
        private final int mTriggerClickCount;

        public abstract void onBeforeTriggerClick(View view, int i);

        public abstract void onTriggerClick(View view);

        public OnMultiClickListener(int i) {
            this(i, INTERVAL_DEFAULT_VALUE);
        }

        public OnMultiClickListener(int i, long j) {
            this.mTriggerClickCount = i;
            this.mClickInterval = j;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.mTriggerClickCount <= 1) {
                onTriggerClick(view);
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.mLastClickTime < this.mClickInterval) {
                int i = this.mClickCount + 1;
                this.mClickCount = i;
                int i2 = this.mTriggerClickCount;
                if (i == i2) {
                    onTriggerClick(view);
                } else if (i < i2) {
                    onBeforeTriggerClick(view, i);
                } else {
                    this.mClickCount = 1;
                    onBeforeTriggerClick(view, 1);
                }
            } else {
                this.mClickCount = 1;
                onBeforeTriggerClick(view, 1);
            }
            this.mLastClickTime = jCurrentTimeMillis;
        }
    }

    private static class OnUtilsTouchListener implements View.OnTouchListener {
        public static OnUtilsTouchListener getInstance() {
            return LazyHolder.INSTANCE;
        }

        private OnUtilsTouchListener() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                processScale(view, true);
                processAlpha(view, true);
            } else if (action == 1 || action == 3) {
                processScale(view, false);
                processAlpha(view, false);
            }
            return false;
        }

        private void processScale(View view, boolean z) {
            Object tag = view.getTag(-1);
            if (tag instanceof Float) {
                float fFloatValue = z ? 1.0f + ((Float) tag).floatValue() : 1.0f;
                view.animate().scaleX(fFloatValue).scaleY(fFloatValue).setDuration(ClickUtils.DEBOUNCING_DEFAULT_VALUE).start();
            }
        }

        private void processAlpha(View view, boolean z) {
            Object tag = view.getTag(z ? -2 : -3);
            if (tag instanceof Float) {
                view.setAlpha(((Float) tag).floatValue());
            }
        }

        private static class LazyHolder {
            private static final OnUtilsTouchListener INSTANCE = new OnUtilsTouchListener();

            private LazyHolder() {
            }
        }
    }
}
