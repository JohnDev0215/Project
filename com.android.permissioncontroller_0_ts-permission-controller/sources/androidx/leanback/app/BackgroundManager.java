package androidx.leanback.app;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Interpolator;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.leanback.R;
import androidx.leanback.widget.BackgroundHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public final class BackgroundManager {
    private static final int CHANGE_BG_DELAY_MS = 500;
    static final boolean DEBUG = false;
    private static final int FADE_DURATION = 500;
    private static final String FRAGMENT_TAG = BackgroundManager.class.getCanonicalName();
    static final int FULL_ALPHA = 255;
    static final String TAG = "BackgroundManager";
    final ValueAnimator mAnimator;
    boolean mAttached;
    int mBackgroundColor;
    Drawable mBackgroundDrawable;
    private View mBgView;
    ChangeBackgroundRunnable mChangeRunnable;
    private boolean mChangeRunnablePending;
    Activity mContext;
    private BackgroundFragment mFragmentState;
    private int mHeightPx;
    int mImageInWrapperIndex;
    int mImageOutWrapperIndex;
    private long mLastSetTime;
    TranslucentLayerDrawable mLayerDrawable;
    private int mThemeDrawableResourceId;
    private int mWidthPx;
    private boolean mAutoReleaseOnStop = true;
    private final Animator.AnimatorListener mAnimationListener = new Animator.AnimatorListener() { // from class: androidx.leanback.app.BackgroundManager.1
        final Runnable mRunnable = new Runnable() { // from class: androidx.leanback.app.BackgroundManager.1.1
            @Override // java.lang.Runnable
            public void run() {
                BackgroundManager.this.postChangeRunnable();
            }
        };

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animation) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            if (BackgroundManager.this.mLayerDrawable != null) {
                BackgroundManager.this.mLayerDrawable.clearDrawable(R.id.background_imageout, BackgroundManager.this.mContext);
            }
            BackgroundManager.this.mHandler.post(this.mRunnable);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
        }
    };
    private final ValueAnimator.AnimatorUpdateListener mAnimationUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.leanback.app.BackgroundManager.2
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator animation) {
            int fadeInAlpha = ((Integer) animation.getAnimatedValue()).intValue();
            if (BackgroundManager.this.mImageInWrapperIndex != -1) {
                BackgroundManager.this.mLayerDrawable.setWrapperAlpha(BackgroundManager.this.mImageInWrapperIndex, fadeInAlpha);
            }
        }
    };
    private BackgroundContinuityService mService = BackgroundContinuityService.getInstance();
    Handler mHandler = new Handler();

    static class BitmapDrawable extends Drawable {
        boolean mMutated;
        ConstantState mState;

        static final class ConstantState extends Drawable.ConstantState {
            final Bitmap mBitmap;
            final Matrix mMatrix;
            final Paint mPaint = new Paint();

            ConstantState(Bitmap bitmap, Matrix matrix) {
                this.mBitmap = bitmap;
                this.mMatrix = matrix != null ? matrix : new Matrix();
                this.mPaint.setFilterBitmap(true);
            }

            ConstantState(ConstantState copyFrom) {
                this.mBitmap = copyFrom.mBitmap;
                this.mMatrix = copyFrom.mMatrix != null ? new Matrix(copyFrom.mMatrix) : new Matrix();
                if (copyFrom.mPaint.getAlpha() != 255) {
                    this.mPaint.setAlpha(copyFrom.mPaint.getAlpha());
                }
                if (copyFrom.mPaint.getColorFilter() != null) {
                    this.mPaint.setColorFilter(copyFrom.mPaint.getColorFilter());
                }
                this.mPaint.setFilterBitmap(true);
            }

            @Override // android.graphics.drawable.Drawable.ConstantState
            public Drawable newDrawable() {
                return new BitmapDrawable(this);
            }

            @Override // android.graphics.drawable.Drawable.ConstantState
            public int getChangingConfigurations() {
                return 0;
            }
        }

        BitmapDrawable(Resources resources, Bitmap bitmap) {
            this(resources, bitmap, null);
        }

        BitmapDrawable(Resources resources, Bitmap bitmap, Matrix matrix) {
            this.mState = new ConstantState(bitmap, matrix);
        }

        BitmapDrawable(ConstantState state) {
            this.mState = state;
        }

        Bitmap getBitmap() {
            return this.mState.mBitmap;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.mState.mBitmap == null) {
                return;
            }
            if (this.mState.mPaint.getAlpha() < 255 && this.mState.mPaint.getColorFilter() != null) {
                throw new IllegalStateException("Can't draw with translucent alpha and color filter");
            }
            canvas.drawBitmap(this.mState.mBitmap, this.mState.mMatrix, this.mState.mPaint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int alpha) {
            mutate();
            if (this.mState.mPaint.getAlpha() != alpha) {
                this.mState.mPaint.setAlpha(alpha);
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter cf) {
            mutate();
            this.mState.mPaint.setColorFilter(cf);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public ColorFilter getColorFilter() {
            return this.mState.mPaint.getColorFilter();
        }

        @Override // android.graphics.drawable.Drawable
        public ConstantState getConstantState() {
            return this.mState;
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable mutate() {
            if (!this.mMutated) {
                this.mMutated = true;
                this.mState = new ConstantState(this.mState);
            }
            return this;
        }
    }

    static final class DrawableWrapper {
        int mAlpha;
        final Drawable mDrawable;

        public DrawableWrapper(Drawable drawable) {
            this.mAlpha = 255;
            this.mDrawable = drawable;
        }

        public DrawableWrapper(DrawableWrapper wrapper, Drawable drawable) {
            this.mAlpha = 255;
            this.mDrawable = drawable;
            this.mAlpha = wrapper.mAlpha;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public void setColor(int color) {
            ((ColorDrawable) this.mDrawable).setColor(color);
        }
    }

    static final class TranslucentLayerDrawable extends LayerDrawable {
        int mAlpha;
        WeakReference<BackgroundManager> mManagerWeakReference;
        boolean mSuspendInvalidation;
        DrawableWrapper[] mWrapper;

        TranslucentLayerDrawable(BackgroundManager manager, Drawable[] drawables) {
            super(drawables);
            this.mAlpha = 255;
            this.mManagerWeakReference = new WeakReference<>(manager);
            int count = drawables.length;
            this.mWrapper = new DrawableWrapper[count];
            for (int i = 0; i < count; i++) {
                this.mWrapper[i] = new DrawableWrapper(drawables[i]);
            }
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public void setAlpha(int alpha) {
            if (this.mAlpha != alpha) {
                this.mAlpha = alpha;
                invalidateSelf();
                BackgroundManager manager = this.mManagerWeakReference.get();
                if (manager != null) {
                    manager.postChangeRunnable();
                }
            }
        }

        void setWrapperAlpha(int wrapperIndex, int alpha) {
            DrawableWrapper[] drawableWrapperArr = this.mWrapper;
            if (drawableWrapperArr[wrapperIndex] != null) {
                drawableWrapperArr[wrapperIndex].mAlpha = alpha;
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public int getAlpha() {
            return this.mAlpha;
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public Drawable mutate() {
            Drawable drawable = super.mutate();
            int count = getNumberOfLayers();
            for (int i = 0; i < count; i++) {
                DrawableWrapper[] drawableWrapperArr = this.mWrapper;
                if (drawableWrapperArr[i] != null) {
                    drawableWrapperArr[i] = new DrawableWrapper(drawableWrapperArr[i], getDrawable(i));
                }
            }
            return drawable;
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.LayerDrawable
        public boolean setDrawableByLayerId(int id, Drawable drawable) {
            return updateDrawable(id, drawable) != null;
        }

        public DrawableWrapper updateDrawable(int id, Drawable drawable) {
            super.setDrawableByLayerId(id, drawable);
            for (int i = 0; i < getNumberOfLayers(); i++) {
                if (getId(i) == id) {
                    this.mWrapper[i] = new DrawableWrapper(drawable);
                    invalidateSelf();
                    return this.mWrapper[i];
                }
            }
            return null;
        }

        public void clearDrawable(int id, Context context) {
            for (int i = 0; i < getNumberOfLayers(); i++) {
                if (getId(i) == id) {
                    this.mWrapper[i] = null;
                    if (!(getDrawable(i) instanceof EmptyDrawable)) {
                        super.setDrawableByLayerId(id, BackgroundManager.createEmptyDrawable(context));
                        return;
                    }
                    return;
                }
            }
        }

        public int findWrapperIndexById(int id) {
            for (int i = 0; i < getNumberOfLayers(); i++) {
                if (getId(i) == id) {
                    return i;
                }
            }
            return -1;
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable who) {
            if (!this.mSuspendInvalidation) {
                super.invalidateDrawable(who);
            }
        }

        @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            Drawable d;
            int i = 0;
            while (true) {
                DrawableWrapper[] drawableWrapperArr = this.mWrapper;
                if (i < drawableWrapperArr.length) {
                    if (drawableWrapperArr[i] != null && (d = drawableWrapperArr[i].getDrawable()) != null) {
                        int alpha = Build.VERSION.SDK_INT >= 19 ? DrawableCompat.getAlpha(d) : 255;
                        int savedAlpha = alpha;
                        int multiple = 0;
                        int i2 = this.mAlpha;
                        if (i2 < 255) {
                            alpha *= i2;
                            multiple = 0 + 1;
                        }
                        if (this.mWrapper[i].mAlpha < 255) {
                            alpha *= this.mWrapper[i].mAlpha;
                            multiple++;
                        }
                        if (multiple == 0) {
                            d.draw(canvas);
                        } else {
                            if (multiple == 1) {
                                alpha /= 255;
                            } else if (multiple == 2) {
                                alpha /= 65025;
                            }
                            try {
                                this.mSuspendInvalidation = true;
                                d.setAlpha(alpha);
                                d.draw(canvas);
                                d.setAlpha(savedAlpha);
                            } finally {
                                this.mSuspendInvalidation = false;
                            }
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    TranslucentLayerDrawable createTranslucentLayerDrawable(LayerDrawable layerDrawable) {
        int numChildren = layerDrawable.getNumberOfLayers();
        Drawable[] drawables = new Drawable[numChildren];
        for (int i = 0; i < numChildren; i++) {
            drawables[i] = layerDrawable.getDrawable(i);
        }
        TranslucentLayerDrawable result = new TranslucentLayerDrawable(this, drawables);
        for (int i2 = 0; i2 < numChildren; i2++) {
            result.setId(i2, layerDrawable.getId(i2));
        }
        return result;
    }

    private static class BackgroundContinuityService {
        private static final boolean DEBUG = false;
        private static final String TAG = "BackgroundContinuity";
        private static BackgroundContinuityService sService = new BackgroundContinuityService();
        private int mColor;
        private int mCount;
        private Drawable mDrawable;
        private int mLastThemeDrawableId;
        private WeakReference<Drawable.ConstantState> mLastThemeDrawableState;

        private BackgroundContinuityService() {
            reset();
        }

        private void reset() {
            this.mColor = 0;
            this.mDrawable = null;
        }

        public static BackgroundContinuityService getInstance() {
            BackgroundContinuityService backgroundContinuityService = sService;
            backgroundContinuityService.mCount++;
            return backgroundContinuityService;
        }

        public void unref() {
            int i = this.mCount;
            if (i <= 0) {
                throw new IllegalStateException("Can't unref, count " + this.mCount);
            }
            int i2 = i - 1;
            this.mCount = i2;
            if (i2 == 0) {
                reset();
            }
        }

        public int getColor() {
            return this.mColor;
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public void setColor(int color) {
            this.mColor = color;
            this.mDrawable = null;
        }

        public void setDrawable(Drawable drawable) {
            this.mDrawable = drawable;
        }

        public Drawable getThemeDrawable(Context context, int themeDrawableId) {
            Drawable.ConstantState drawableState;
            Drawable drawable = null;
            WeakReference<Drawable.ConstantState> weakReference = this.mLastThemeDrawableState;
            if (weakReference != null && this.mLastThemeDrawableId == themeDrawableId && (drawableState = weakReference.get()) != null) {
                drawable = drawableState.newDrawable();
            }
            if (drawable == null) {
                Drawable drawable2 = ContextCompat.getDrawable(context, themeDrawableId);
                this.mLastThemeDrawableState = new WeakReference<>(drawable2.getConstantState());
                this.mLastThemeDrawableId = themeDrawableId;
                return drawable2;
            }
            return drawable;
        }
    }

    Drawable getDefaultDrawable() {
        if (this.mBackgroundColor != 0) {
            return new ColorDrawable(this.mBackgroundColor);
        }
        return getThemeDrawable();
    }

    private Drawable getThemeDrawable() {
        Drawable drawable = null;
        int i = this.mThemeDrawableResourceId;
        if (i != -1) {
            drawable = this.mService.getThemeDrawable(this.mContext, i);
        }
        if (drawable == null) {
            Drawable drawable2 = createEmptyDrawable(this.mContext);
            return drawable2;
        }
        return drawable;
    }

    public static BackgroundManager getInstance(Activity activity) {
        BackgroundManager manager;
        BackgroundFragment fragment = (BackgroundFragment) activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (fragment != null && (manager = fragment.getBackgroundManager()) != null) {
            return manager;
        }
        return new BackgroundManager(activity);
    }

    private BackgroundManager(Activity activity) {
        this.mContext = activity;
        this.mHeightPx = this.mContext.getResources().getDisplayMetrics().heightPixels;
        this.mWidthPx = this.mContext.getResources().getDisplayMetrics().widthPixels;
        Interpolator defaultInterpolator = new FastOutLinearInInterpolator();
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 255);
        this.mAnimator = valueAnimatorOfInt;
        valueAnimatorOfInt.addListener(this.mAnimationListener);
        this.mAnimator.addUpdateListener(this.mAnimationUpdateListener);
        this.mAnimator.setInterpolator(defaultInterpolator);
        TypedArray ta = activity.getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowBackground});
        this.mThemeDrawableResourceId = ta.getResourceId(0, -1);
        ta.recycle();
        createFragment(activity);
    }

    private void createFragment(Activity activity) {
        BackgroundFragment fragment = (BackgroundFragment) activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = new BackgroundFragment();
            activity.getFragmentManager().beginTransaction().add(fragment, FRAGMENT_TAG).commit();
        } else if (fragment.getBackgroundManager() != null) {
            throw new IllegalStateException("Created duplicated BackgroundManager for same activity, please use getInstance() instead");
        }
        fragment.setBackgroundManager(this);
        this.mFragmentState = fragment;
    }

    DrawableWrapper getImageInWrapper() {
        TranslucentLayerDrawable translucentLayerDrawable = this.mLayerDrawable;
        if (translucentLayerDrawable == null) {
            return null;
        }
        return translucentLayerDrawable.mWrapper[this.mImageInWrapperIndex];
    }

    DrawableWrapper getImageOutWrapper() {
        TranslucentLayerDrawable translucentLayerDrawable = this.mLayerDrawable;
        if (translucentLayerDrawable == null) {
            return null;
        }
        return translucentLayerDrawable.mWrapper[this.mImageOutWrapperIndex];
    }

    void onActivityStart() {
        updateImmediate();
    }

    void onStop() {
        if (isAutoReleaseOnStop()) {
            release();
        }
    }

    void onResume() {
        postChangeRunnable();
    }

    private void syncWithService() {
        int color = this.mService.getColor();
        Drawable drawable = this.mService.getDrawable();
        this.mBackgroundColor = color;
        this.mBackgroundDrawable = drawable == null ? null : drawable.getConstantState().newDrawable().mutate();
        updateImmediate();
    }

    public void attach(Window window) {
        attachToViewInternal(window.getDecorView());
    }

    public void setThemeDrawableResourceId(int resourceId) {
        this.mThemeDrawableResourceId = resourceId;
    }

    public void attachToView(View sceneRoot) {
        attachToViewInternal(sceneRoot);
        this.mContext.getWindow().getDecorView().setBackground(Build.VERSION.SDK_INT >= 26 ? null : new ColorDrawable(0));
    }

    void attachToViewInternal(View sceneRoot) {
        if (this.mAttached) {
            throw new IllegalStateException("Already attached to " + this.mBgView);
        }
        this.mBgView = sceneRoot;
        this.mAttached = true;
        syncWithService();
    }

    public boolean isAttached() {
        return this.mAttached;
    }

    void detach() {
        release();
        this.mBgView = null;
        this.mAttached = false;
        BackgroundContinuityService backgroundContinuityService = this.mService;
        if (backgroundContinuityService != null) {
            backgroundContinuityService.unref();
            this.mService = null;
        }
    }

    public void release() {
        ChangeBackgroundRunnable changeBackgroundRunnable = this.mChangeRunnable;
        if (changeBackgroundRunnable != null) {
            this.mHandler.removeCallbacks(changeBackgroundRunnable);
            this.mChangeRunnable = null;
        }
        if (this.mAnimator.isStarted()) {
            this.mAnimator.cancel();
        }
        TranslucentLayerDrawable translucentLayerDrawable = this.mLayerDrawable;
        if (translucentLayerDrawable != null) {
            translucentLayerDrawable.clearDrawable(R.id.background_imagein, this.mContext);
            this.mLayerDrawable.clearDrawable(R.id.background_imageout, this.mContext);
            this.mLayerDrawable = null;
        }
        this.mBackgroundDrawable = null;
    }

    @Deprecated
    public void setDimLayer(Drawable drawable) {
    }

    @Deprecated
    public Drawable getDimLayer() {
        return null;
    }

    @Deprecated
    public Drawable getDefaultDimLayer() {
        return ContextCompat.getDrawable(this.mContext, R.color.lb_background_protection);
    }

    void postChangeRunnable() {
        if (this.mChangeRunnable != null && this.mChangeRunnablePending && !this.mAnimator.isStarted() && this.mFragmentState.isResumed() && this.mLayerDrawable.getAlpha() >= 255) {
            long delayMs = getRunnableDelay();
            this.mLastSetTime = System.currentTimeMillis();
            this.mHandler.postDelayed(this.mChangeRunnable, delayMs);
            this.mChangeRunnablePending = false;
        }
    }

    private void lazyInit() {
        if (this.mLayerDrawable != null) {
            return;
        }
        LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(this.mContext, R.drawable.lb_background).mutate();
        TranslucentLayerDrawable translucentLayerDrawableCreateTranslucentLayerDrawable = createTranslucentLayerDrawable(layerDrawable);
        this.mLayerDrawable = translucentLayerDrawableCreateTranslucentLayerDrawable;
        this.mImageInWrapperIndex = translucentLayerDrawableCreateTranslucentLayerDrawable.findWrapperIndexById(R.id.background_imagein);
        this.mImageOutWrapperIndex = this.mLayerDrawable.findWrapperIndexById(R.id.background_imageout);
        BackgroundHelper.setBackgroundPreservingAlpha(this.mBgView, this.mLayerDrawable);
    }

    private void updateImmediate() {
        if (!this.mAttached) {
            return;
        }
        lazyInit();
        if (this.mBackgroundDrawable == null) {
            this.mLayerDrawable.updateDrawable(R.id.background_imagein, getDefaultDrawable());
        } else {
            this.mLayerDrawable.updateDrawable(R.id.background_imagein, this.mBackgroundDrawable);
        }
        this.mLayerDrawable.clearDrawable(R.id.background_imageout, this.mContext);
    }

    public void setColor(int color) {
        this.mService.setColor(color);
        this.mBackgroundColor = color;
        this.mBackgroundDrawable = null;
        if (this.mLayerDrawable == null) {
            return;
        }
        setDrawableInternal(getDefaultDrawable());
    }

    public void setDrawable(Drawable drawable) {
        this.mService.setDrawable(drawable);
        this.mBackgroundDrawable = drawable;
        if (this.mLayerDrawable == null) {
            return;
        }
        if (drawable == null) {
            setDrawableInternal(getDefaultDrawable());
        } else {
            setDrawableInternal(drawable);
        }
    }

    public void clearDrawable() {
        setDrawable(null);
    }

    private void setDrawableInternal(Drawable drawable) {
        if (!this.mAttached) {
            throw new IllegalStateException("Must attach before setting background drawable");
        }
        ChangeBackgroundRunnable changeBackgroundRunnable = this.mChangeRunnable;
        if (changeBackgroundRunnable != null) {
            if (sameDrawable(drawable, changeBackgroundRunnable.mDrawable)) {
                return;
            }
            this.mHandler.removeCallbacks(this.mChangeRunnable);
            this.mChangeRunnable = null;
        }
        this.mChangeRunnable = new ChangeBackgroundRunnable(drawable);
        this.mChangeRunnablePending = true;
        postChangeRunnable();
    }

    private long getRunnableDelay() {
        return Math.max(0L, (this.mLastSetTime + 500) - System.currentTimeMillis());
    }

    public void setBitmap(Bitmap bitmap) {
        float scale;
        if (bitmap == null) {
            setDrawable(null);
            return;
        }
        if (bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            return;
        }
        Matrix matrix = null;
        if (bitmap.getWidth() != this.mWidthPx || bitmap.getHeight() != this.mHeightPx) {
            int dwidth = bitmap.getWidth();
            int dheight = bitmap.getHeight();
            int i = this.mHeightPx;
            int i2 = dwidth * i;
            int i3 = this.mWidthPx;
            if (i2 > i3 * dheight) {
                scale = i / dheight;
            } else {
                float scale2 = i3;
                scale = scale2 / dwidth;
            }
            int subX = Math.min((int) (this.mWidthPx / scale), dwidth);
            int dx = Math.max(0, (dwidth - subX) / 2);
            matrix = new Matrix();
            matrix.setScale(scale, scale);
            matrix.preTranslate(-dx, 0.0f);
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmap, matrix);
        setDrawable(bitmapDrawable);
    }

    public void setAutoReleaseOnStop(boolean autoReleaseOnStop) {
        this.mAutoReleaseOnStop = autoReleaseOnStop;
    }

    public boolean isAutoReleaseOnStop() {
        return this.mAutoReleaseOnStop;
    }

    public final int getColor() {
        return this.mBackgroundColor;
    }

    public Drawable getDrawable() {
        return this.mBackgroundDrawable;
    }

    boolean sameDrawable(Drawable first, Drawable second) {
        if (first == null || second == null) {
            return false;
        }
        if (first == second) {
            return true;
        }
        if ((first instanceof BitmapDrawable) && (second instanceof BitmapDrawable) && ((BitmapDrawable) first).getBitmap().sameAs(((BitmapDrawable) second).getBitmap())) {
            return true;
        }
        if (!(first instanceof ColorDrawable) || !(second instanceof ColorDrawable) || ((ColorDrawable) first).getColor() != ((ColorDrawable) second).getColor()) {
            return false;
        }
        return true;
    }

    final class ChangeBackgroundRunnable implements Runnable {
        final Drawable mDrawable;

        ChangeBackgroundRunnable(Drawable drawable) {
            this.mDrawable = drawable;
        }

        @Override // java.lang.Runnable
        public void run() {
            runTask();
            BackgroundManager.this.mChangeRunnable = null;
        }

        private void runTask() {
            if (BackgroundManager.this.mLayerDrawable == null) {
                return;
            }
            DrawableWrapper imageInWrapper = BackgroundManager.this.getImageInWrapper();
            if (imageInWrapper != null) {
                if (BackgroundManager.this.sameDrawable(this.mDrawable, imageInWrapper.getDrawable())) {
                    return;
                }
                BackgroundManager.this.mLayerDrawable.clearDrawable(R.id.background_imagein, BackgroundManager.this.mContext);
                BackgroundManager.this.mLayerDrawable.updateDrawable(R.id.background_imageout, imageInWrapper.getDrawable());
            }
            applyBackgroundChanges();
        }

        void applyBackgroundChanges() {
            if (!BackgroundManager.this.mAttached) {
                return;
            }
            DrawableWrapper imageInWrapper = BackgroundManager.this.getImageInWrapper();
            if (imageInWrapper == null && this.mDrawable != null) {
                BackgroundManager.this.mLayerDrawable.updateDrawable(R.id.background_imagein, this.mDrawable);
                BackgroundManager.this.mLayerDrawable.setWrapperAlpha(BackgroundManager.this.mImageInWrapperIndex, 0);
            }
            BackgroundManager.this.mAnimator.setDuration(500L);
            BackgroundManager.this.mAnimator.start();
        }
    }

    static class EmptyDrawable extends BitmapDrawable {
        EmptyDrawable(Resources res) {
            super(res, (Bitmap) null);
        }
    }

    static Drawable createEmptyDrawable(Context context) {
        return new EmptyDrawable(context.getResources());
    }
}
