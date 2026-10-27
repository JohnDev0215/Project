package com.android.launcher3.graphics;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Property;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.android.launcher3.CellLayout;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.ResourceUtils;
import com.android.launcher3.Workspace;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.uioverrides.WallpaperColorInfo;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public class WorkspaceAndHotseatScrim implements View.OnAttachStateChangeListener, WallpaperColorInfo.OnChangeListener {
    private static final int ALPHA_MASK_BITMAP_DP = 200;
    private static final int ALPHA_MASK_HEIGHT_DP = 500;
    private static final int ALPHA_MASK_WIDTH_DP = 2;
    private static final int DARK_SCRIM_COLOR = 1426063360;
    private static final int MAX_HOTSEAT_SCRIM_ALPHA = 100;
    private final Bitmap mBottomMask;
    private boolean mDrawBottomScrim;
    private boolean mDrawTopScrim;
    private int mFullScrimColor;
    private boolean mHideSysUiScrim;
    private final Launcher mLauncher;
    private final int mMaskHeight;
    private final View mRoot;
    private float mScrimProgress;
    private final Drawable mTopScrim;
    private final WallpaperColorInfo mWallpaperColorInfo;
    private Workspace mWorkspace;
    public static Property<WorkspaceAndHotseatScrim, Float> SCRIM_PROGRESS = new Property<WorkspaceAndHotseatScrim, Float>(Float.TYPE, "scrimProgress") { // from class: com.android.launcher3.graphics.WorkspaceAndHotseatScrim.1
        @Override // android.util.Property
        public Float get(WorkspaceAndHotseatScrim scrim) {
            return Float.valueOf(scrim.mScrimProgress);
        }

        @Override // android.util.Property
        public void set(WorkspaceAndHotseatScrim scrim, Float value) {
            scrim.setScrimProgress(value.floatValue());
        }
    };
    public static Property<WorkspaceAndHotseatScrim, Float> SYSUI_PROGRESS = new Property<WorkspaceAndHotseatScrim, Float>(Float.TYPE, "sysUiProgress") { // from class: com.android.launcher3.graphics.WorkspaceAndHotseatScrim.2
        @Override // android.util.Property
        public Float get(WorkspaceAndHotseatScrim scrim) {
            return Float.valueOf(scrim.mSysUiProgress);
        }

        @Override // android.util.Property
        public void set(WorkspaceAndHotseatScrim scrim, Float value) {
            scrim.setSysUiProgress(value.floatValue());
        }
    };
    private static Property<WorkspaceAndHotseatScrim, Float> SYSUI_ANIM_MULTIPLIER = new Property<WorkspaceAndHotseatScrim, Float>(Float.TYPE, "sysUiAnimMultiplier") { // from class: com.android.launcher3.graphics.WorkspaceAndHotseatScrim.3
        @Override // android.util.Property
        public Float get(WorkspaceAndHotseatScrim scrim) {
            return Float.valueOf(scrim.mSysUiAnimMultiplier);
        }

        @Override // android.util.Property
        public void set(WorkspaceAndHotseatScrim scrim, Float value) {
            scrim.mSysUiAnimMultiplier = value.floatValue();
            scrim.reapplySysUiAlpha();
        }
    };
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.launcher3.graphics.WorkspaceAndHotseatScrim.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                WorkspaceAndHotseatScrim.this.mAnimateScrimOnNextDraw = true;
            } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                WorkspaceAndHotseatScrim.this.mAnimateScrimOnNextDraw = false;
            }
        }
    };
    private final Rect mHighlightRect = new Rect();
    private final RectF mFinalMaskRect = new RectF();
    private final Paint mBottomMaskPaint = new Paint(2);
    private int mScrimAlpha = 0;
    private float mSysUiProgress = 1.0f;
    private boolean mAnimateScrimOnNextDraw = false;
    private float mSysUiAnimMultiplier = 1.0f;

    public WorkspaceAndHotseatScrim(View view) {
        this.mRoot = view;
        Launcher launcher = Launcher.getLauncher(view.getContext());
        this.mLauncher = launcher;
        WallpaperColorInfo wallpaperColorInfo = WallpaperColorInfo.getInstance(launcher);
        this.mWallpaperColorInfo = wallpaperColorInfo;
        this.mMaskHeight = ResourceUtils.pxFromDp(200.0f, view.getResources().getDisplayMetrics());
        Drawable attrDrawable = Themes.getAttrDrawable(view.getContext(), R.attr.workspaceStatusBarScrim);
        this.mTopScrim = attrDrawable;
        this.mBottomMask = attrDrawable == null ? null : createDitheredAlphaMask();
        this.mHideSysUiScrim = attrDrawable == null;
        view.addOnAttachStateChangeListener(this);
        onExtractedColorsChanged(wallpaperColorInfo);
    }

    public void setWorkspace(Workspace workspace) {
        this.mWorkspace = workspace;
    }

    public void draw(Canvas canvas) {
        if (this.mScrimAlpha > 0) {
            this.mWorkspace.computeScrollWithoutInvalidation();
            CellLayout currentDragOverlappingLayout = this.mWorkspace.getCurrentDragOverlappingLayout();
            canvas.save();
            if (currentDragOverlappingLayout != null && currentDragOverlappingLayout != this.mLauncher.getHotseat()) {
                this.mLauncher.getDragLayer().getDescendantRectRelativeToSelf(currentDragOverlappingLayout, this.mHighlightRect);
                canvas.clipRect(this.mHighlightRect, Region.Op.DIFFERENCE);
            }
            canvas.drawColor(GraphicsUtils.setColorAlphaBound(this.mFullScrimColor, this.mScrimAlpha));
            canvas.restore();
        }
        if (this.mHideSysUiScrim) {
            return;
        }
        if (this.mSysUiProgress <= 0.0f) {
            this.mAnimateScrimOnNextDraw = false;
            return;
        }
        if (this.mAnimateScrimOnNextDraw) {
            this.mSysUiAnimMultiplier = 0.0f;
            reapplySysUiAlphaNoInvalidate();
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, SYSUI_ANIM_MULTIPLIER, 1.0f);
            objectAnimatorOfFloat.setAutoCancel(true);
            objectAnimatorOfFloat.setDuration(600L);
            objectAnimatorOfFloat.setStartDelay(this.mLauncher.getWindow().getTransitionBackgroundFadeDuration());
            objectAnimatorOfFloat.start();
            this.mAnimateScrimOnNextDraw = false;
        }
        if (this.mDrawTopScrim) {
            this.mTopScrim.draw(canvas);
        }
        if (this.mDrawBottomScrim) {
            canvas.drawBitmap(this.mBottomMask, (Rect) null, this.mFinalMaskRect, this.mBottomMaskPaint);
        }
    }

    public void onInsetsChanged(Rect insets) {
        this.mDrawTopScrim = this.mTopScrim != null && insets.top > 0;
        this.mDrawBottomScrim = (this.mBottomMask == null || this.mLauncher.getDeviceProfile().isVerticalBarLayout()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrimProgress(float progress) {
        if (this.mScrimProgress != progress) {
            this.mScrimProgress = progress;
            this.mScrimAlpha = Math.round(progress * 255.0f);
            invalidate();
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.mWallpaperColorInfo.addOnChangeListener(this);
        onExtractedColorsChanged(this.mWallpaperColorInfo);
        if (this.mTopScrim != null) {
            IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
            intentFilter.addAction(Intent.ACTION_USER_PRESENT);
            this.mRoot.getContext().registerReceiver(this.mReceiver, intentFilter);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.mWallpaperColorInfo.removeOnChangeListener(this);
        if (this.mTopScrim != null) {
            this.mRoot.getContext().unregisterReceiver(this.mReceiver);
        }
    }

    @Override // com.android.launcher3.uioverrides.WallpaperColorInfo.OnChangeListener
    public void onExtractedColorsChanged(WallpaperColorInfo wallpaperColorInfo) {
        this.mBottomMaskPaint.setColor(ColorUtils.compositeColors(DARK_SCRIM_COLOR, wallpaperColorInfo.getMainColor()));
        reapplySysUiAlpha();
        this.mFullScrimColor = wallpaperColorInfo.getMainColor();
        if (this.mScrimAlpha > 0) {
            invalidate();
        }
    }

    public void setSize(int w, int h) {
        Drawable drawable = this.mTopScrim;
        if (drawable != null) {
            drawable.setBounds(0, 0, w, h);
            this.mFinalMaskRect.set(0.0f, h - this.mMaskHeight, w, h);
        }
    }

    public void hideSysUiScrim(boolean hideSysUiScrim) {
        this.mHideSysUiScrim = hideSysUiScrim || this.mTopScrim == null;
        if (!hideSysUiScrim) {
            this.mAnimateScrimOnNextDraw = true;
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSysUiProgress(float progress) {
        if (progress != this.mSysUiProgress) {
            this.mSysUiProgress = progress;
            reapplySysUiAlpha();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reapplySysUiAlpha() {
        reapplySysUiAlphaNoInvalidate();
        if (this.mHideSysUiScrim) {
            return;
        }
        invalidate();
    }

    private void reapplySysUiAlphaNoInvalidate() {
        float f = this.mSysUiProgress * this.mSysUiAnimMultiplier;
        this.mBottomMaskPaint.setAlpha(Math.round(100.0f * f));
        Drawable drawable = this.mTopScrim;
        if (drawable != null) {
            drawable.setAlpha(Math.round(f * 255.0f));
        }
    }

    public void invalidate() {
        this.mRoot.invalidate();
    }

    public Bitmap createDitheredAlphaMask() {
        DisplayMetrics displayMetrics = this.mLauncher.getResources().getDisplayMetrics();
        int iPxFromDp = ResourceUtils.pxFromDp(2.0f, displayMetrics);
        int iPxFromDp2 = ResourceUtils.pxFromDp(500.0f, displayMetrics);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iPxFromDp, this.mMaskHeight, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(4);
        float f = iPxFromDp2;
        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, f, new int[]{16777215, GraphicsUtils.setColorAlphaBound(-1, 242), -1}, new float[]{0.0f, 0.8f, 1.0f}, Shader.TileMode.CLAMP));
        canvas.drawRect(0.0f, 0.0f, iPxFromDp, f, paint);
        return bitmapCreateBitmap;
    }
}
