package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes3.dex */
public class ShadowOverlayContainer extends FrameLayout {
    public static final int SHADOW_DYNAMIC = 3;
    public static final int SHADOW_NONE = 1;
    public static final int SHADOW_STATIC = 2;
    private static final Rect sTempRect = new Rect();
    private float mFocusedZ;
    private boolean mInitialized;
    int mOverlayColor;
    private Paint mOverlayPaint;
    private int mRoundedCornerRadius;
    private boolean mRoundedCorners;
    private Object mShadowImpl;
    private int mShadowType;
    private float mUnfocusedZ;
    private View mWrappedView;

    public ShadowOverlayContainer(Context context) {
        this(context, null, 0);
    }

    public ShadowOverlayContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowOverlayContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mShadowType = 1;
        useStaticShadow();
        useDynamicShadow();
    }

    ShadowOverlayContainer(Context context, int shadowType, boolean hasColorDimOverlay, float unfocusedZ, float focusedZ, int roundedCornerRadius) {
        super(context);
        this.mShadowType = 1;
        this.mUnfocusedZ = unfocusedZ;
        this.mFocusedZ = focusedZ;
        initialize(shadowType, hasColorDimOverlay, roundedCornerRadius);
    }

    public static boolean supportsShadow() {
        return StaticShadowHelper.supportsShadow();
    }

    public static boolean supportsDynamicShadow() {
        return ShadowHelper.supportsDynamicShadow();
    }

    public static void prepareParentForShadow(ViewGroup parent) {
        StaticShadowHelper.prepareParent(parent);
    }

    public void useDynamicShadow() {
        useDynamicShadow(getResources().getDimension(R.dimen.lb_material_shadow_normal_z), getResources().getDimension(R.dimen.lb_material_shadow_focused_z));
    }

    public void useDynamicShadow(float unfocusedZ, float focusedZ) {
        if (this.mInitialized) {
            throw new IllegalStateException("Already initialized");
        }
        if (supportsDynamicShadow()) {
            this.mShadowType = 3;
            this.mUnfocusedZ = unfocusedZ;
            this.mFocusedZ = focusedZ;
        }
    }

    public void useStaticShadow() {
        if (this.mInitialized) {
            throw new IllegalStateException("Already initialized");
        }
        if (supportsShadow()) {
            this.mShadowType = 2;
        }
    }

    public int getShadowType() {
        return this.mShadowType;
    }

    @Deprecated
    public void initialize(boolean hasShadow, boolean hasColorDimOverlay) {
        initialize(hasShadow, hasColorDimOverlay, true);
    }

    @Deprecated
    public void initialize(boolean hasShadow, boolean hasColorDimOverlay, boolean roundedCorners) {
        int shadowType;
        if (!hasShadow) {
            shadowType = 1;
        } else {
            shadowType = this.mShadowType;
        }
        int roundedCornerRadius = roundedCorners ? getContext().getResources().getDimensionPixelSize(R.dimen.lb_rounded_rect_corner_radius) : 0;
        initialize(shadowType, hasColorDimOverlay, roundedCornerRadius);
    }

    void initialize(int shadowType, boolean hasColorDimOverlay, int roundedCornerRadius) {
        boolean z;
        if (this.mInitialized) {
            throw new IllegalStateException();
        }
        this.mInitialized = true;
        this.mRoundedCornerRadius = roundedCornerRadius;
        if (roundedCornerRadius > 0) {
            z = true;
        } else {
            z = false;
        }
        this.mRoundedCorners = z;
        this.mShadowType = shadowType;
        if (shadowType == 2) {
            this.mShadowImpl = StaticShadowHelper.addStaticShadow(this);
        } else if (shadowType == 3) {
            this.mShadowImpl = ShadowHelper.addDynamicShadow(this, this.mUnfocusedZ, this.mFocusedZ, this.mRoundedCornerRadius);
        }
        if (hasColorDimOverlay) {
            setWillNotDraw(false);
            this.mOverlayColor = 0;
            Paint paint = new Paint();
            this.mOverlayPaint = paint;
            paint.setColor(this.mOverlayColor);
            this.mOverlayPaint.setStyle(Paint.Style.FILL);
            return;
        }
        setWillNotDraw(true);
        this.mOverlayPaint = null;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mOverlayPaint != null && this.mOverlayColor != 0) {
            canvas.drawRect(this.mWrappedView.getLeft(), this.mWrappedView.getTop(), this.mWrappedView.getRight(), this.mWrappedView.getBottom(), this.mOverlayPaint);
        }
    }

    public void setShadowFocusLevel(float level) {
        Object obj = this.mShadowImpl;
        if (obj != null) {
            ShadowOverlayHelper.setShadowFocusLevel(obj, this.mShadowType, level);
        }
    }

    public void setOverlayColor(int overlayColor) {
        Paint paint = this.mOverlayPaint;
        if (paint != null && overlayColor != this.mOverlayColor) {
            this.mOverlayColor = overlayColor;
            paint.setColor(overlayColor);
            invalidate();
        }
    }

    public void wrap(View view) {
        if (!this.mInitialized || this.mWrappedView != null) {
            throw new IllegalStateException();
        }
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null) {
            ViewGroup.LayoutParams wrapped_lp = new FrameLayout.LayoutParams(lp.width, lp.height);
            lp.width = lp.width == -1 ? -1 : -2;
            lp.height = lp.height == -1 ? -1 : -2;
            setLayoutParams(lp);
            addView(view, wrapped_lp);
        } else {
            addView(view);
        }
        if (this.mRoundedCorners && this.mShadowType != 3) {
            RoundedRectHelper.setClipToRoundedOutline(this, true);
        }
        this.mWrappedView = view;
    }

    public View getWrappedView() {
        return this.mWrappedView;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View view;
        super.onLayout(changed, l, t, r, b);
        if (changed && (view = this.mWrappedView) != null) {
            sTempRect.left = (int) view.getPivotX();
            sTempRect.top = (int) this.mWrappedView.getPivotY();
            offsetDescendantRectToMyCoords(this.mWrappedView, sTempRect);
            setPivotX(sTempRect.left);
            setPivotY(sTempRect.top);
        }
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }
}
