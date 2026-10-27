package org.chromium.content.browser;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.Log;
import org.chromium.content.R;

/* JADX INFO: loaded from: classes3.dex */
class PopupZoomer extends View {
    private static final long ANIMATION_DURATION = 300;
    private static final String TAG = "cr.PopupZoomer";
    private static final int ZOOM_BOUNDS_MARGIN = 25;
    private static float sOverlayCornerRadius;
    private static Drawable sOverlayDrawable;
    private static Rect sOverlayPadding;
    private boolean mAnimating;
    private long mAnimationStartTime;
    private float mBottomExtrusion;
    private RectF mClipRect;
    private RectF mDrawRect;
    private GestureDetector mGestureDetector;
    private final Interpolator mHideInterpolator;
    private float mLeftExtrusion;
    private float mMaxScrollX;
    private float mMaxScrollY;
    private float mMinScrollX;
    private float mMinScrollY;
    private boolean mNeedsToInitDimensions;
    private OnTapListener mOnTapListener;
    private OnVisibilityChangedListener mOnVisibilityChangedListener;
    private float mPopupScrollX;
    private float mPopupScrollY;
    private float mRightExtrusion;
    private float mScale;
    private float mShiftX;
    private float mShiftY;
    private final Interpolator mShowInterpolator;
    private boolean mShowing;
    private Rect mTargetBounds;
    private long mTimeLeft;
    private float mTopExtrusion;
    private final PointF mTouch;
    private RectF mViewClipRect;
    private Bitmap mZoomedBitmap;

    public interface OnTapListener {
        boolean onLongPress(View view, MotionEvent motionEvent);

        boolean onSingleTap(View view, MotionEvent motionEvent);
    }

    public interface OnVisibilityChangedListener {
        void onPopupZoomerHidden(PopupZoomer popupZoomer);

        void onPopupZoomerShown(PopupZoomer popupZoomer);
    }

    private static float constrain(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    private static int constrain(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    protected boolean acceptZeroSizeView() {
        return false;
    }

    private static float getOverlayCornerRadius(Context context) {
        if (sOverlayCornerRadius == 0.0f) {
            try {
                sOverlayCornerRadius = context.getResources().getDimension(R.dimen.link_preview_overlay_radius);
            } catch (Resources.NotFoundException unused) {
                Log.w(TAG, "No corner radius resource for PopupZoomer overlay found.", new Object[0]);
                sOverlayCornerRadius = 1.0f;
            }
        }
        return sOverlayCornerRadius;
    }

    private static Drawable getOverlayDrawable(Context context) {
        if (sOverlayDrawable == null) {
            try {
                sOverlayDrawable = ApiCompatibilityUtils.getDrawable(context.getResources(), R.drawable.ondemand_overlay);
            } catch (Resources.NotFoundException unused) {
                Log.w(TAG, "No drawable resource for PopupZoomer overlay found.", new Object[0]);
                sOverlayDrawable = new ColorDrawable();
            }
            Rect rect = new Rect();
            sOverlayPadding = rect;
            sOverlayDrawable.getPadding(rect);
        }
        return sOverlayDrawable;
    }

    public PopupZoomer(Context context) {
        super(context);
        this.mOnTapListener = null;
        this.mOnVisibilityChangedListener = null;
        OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
        this.mShowInterpolator = overshootInterpolator;
        this.mHideInterpolator = new ReverseInterpolator(overshootInterpolator);
        this.mAnimating = false;
        this.mShowing = false;
        this.mAnimationStartTime = 0L;
        this.mTimeLeft = 0L;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
        this.mScale = 1.0f;
        this.mTouch = new PointF();
        setVisibility(4);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: org.chromium.content.browser.PopupZoomer.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (PopupZoomer.this.mAnimating) {
                    return true;
                }
                if (!PopupZoomer.this.isTouchOutsideArea(motionEvent.getX(), motionEvent.getY())) {
                    PopupZoomer.this.scroll(f, f2);
                } else {
                    PopupZoomer.this.hide(true);
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return handleTapOrPress(motionEvent, false);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                handleTapOrPress(motionEvent, true);
            }

            private boolean handleTapOrPress(MotionEvent motionEvent, boolean z) {
                if (PopupZoomer.this.mAnimating) {
                    return true;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (!PopupZoomer.this.isTouchOutsideArea(x, y)) {
                    if (PopupZoomer.this.mOnTapListener != null) {
                        PointF pointFConvertTouchPoint = PopupZoomer.this.convertTouchPoint(x, y);
                        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                        motionEventObtainNoHistory.setLocation(pointFConvertTouchPoint.x, pointFConvertTouchPoint.y);
                        if (z) {
                            PopupZoomer.this.mOnTapListener.onLongPress(PopupZoomer.this, motionEventObtainNoHistory);
                        } else {
                            PopupZoomer.this.mOnTapListener.onSingleTap(PopupZoomer.this, motionEventObtainNoHistory);
                        }
                        PopupZoomer.this.hide(true);
                    }
                } else {
                    PopupZoomer.this.hide(true);
                }
                return true;
            }
        });
    }

    public void setOnTapListener(OnTapListener onTapListener) {
        this.mOnTapListener = onTapListener;
    }

    public void setOnVisibilityChangedListener(OnVisibilityChangedListener onVisibilityChangedListener) {
        this.mOnVisibilityChangedListener = onVisibilityChangedListener;
    }

    public void setBitmap(Bitmap bitmap) {
        Bitmap bitmap2 = this.mZoomedBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.mZoomedBitmap = null;
        }
        this.mZoomedBitmap = bitmap;
        Canvas canvas = new Canvas(this.mZoomedBitmap);
        Path path = new Path();
        RectF rectF = new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        float overlayCornerRadius = getOverlayCornerRadius(getContext());
        path.addRoundRect(rectF, overlayCornerRadius, overlayCornerRadius, Path.Direction.CCW);
        canvas.clipPath(path, Region.Op.XOR);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setColor(0);
        canvas.drawPaint(paint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scroll(float f, float f2) {
        this.mPopupScrollX = constrain(this.mPopupScrollX - f, this.mMinScrollX, this.mMaxScrollX);
        this.mPopupScrollY = constrain(this.mPopupScrollY - f2, this.mMinScrollY, this.mMaxScrollY);
        invalidate();
    }

    private void startAnimation(boolean z) {
        this.mAnimating = true;
        this.mShowing = z;
        this.mTimeLeft = 0L;
        if (z) {
            setVisibility(0);
            this.mNeedsToInitDimensions = true;
            OnVisibilityChangedListener onVisibilityChangedListener = this.mOnVisibilityChangedListener;
            if (onVisibilityChangedListener != null) {
                onVisibilityChangedListener.onPopupZoomerShown(this);
            }
        } else {
            long jUptimeMillis = (this.mAnimationStartTime + ANIMATION_DURATION) - SystemClock.uptimeMillis();
            this.mTimeLeft = jUptimeMillis;
            if (jUptimeMillis < 0) {
                this.mTimeLeft = 0L;
            }
        }
        this.mAnimationStartTime = SystemClock.uptimeMillis();
        invalidate();
    }

    private void hideImmediately() {
        this.mAnimating = false;
        this.mShowing = false;
        this.mTimeLeft = 0L;
        OnVisibilityChangedListener onVisibilityChangedListener = this.mOnVisibilityChangedListener;
        if (onVisibilityChangedListener != null) {
            onVisibilityChangedListener.onPopupZoomerHidden(this);
        }
        setVisibility(4);
        this.mZoomedBitmap.recycle();
        this.mZoomedBitmap = null;
    }

    public boolean isShowing() {
        return this.mShowing || this.mAnimating;
    }

    public void setLastTouch(float f, float f2) {
        this.mTouch.x = f;
        this.mTouch.y = f2;
    }

    private void setTargetBounds(Rect rect) {
        this.mTargetBounds = rect;
    }

    private void initDimensions() {
        if (this.mTargetBounds == null || this.mTouch == null) {
            return;
        }
        this.mScale = this.mZoomedBitmap.getWidth() / this.mTargetBounds.width();
        float f = this.mTouch.x - (this.mScale * (this.mTouch.x - this.mTargetBounds.left));
        float f2 = this.mTouch.y - (this.mScale * (this.mTouch.y - this.mTargetBounds.top));
        this.mClipRect = new RectF(f, f2, this.mZoomedBitmap.getWidth() + f, this.mZoomedBitmap.getHeight() + f2);
        float width = getWidth() - 25;
        float height = getHeight() - 25;
        this.mViewClipRect = new RectF(25.0f, 25.0f, width, height);
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
        if (this.mClipRect.left < 25.0f) {
            this.mShiftX = 25.0f - this.mClipRect.left;
            this.mClipRect.left += this.mShiftX;
            this.mClipRect.right += this.mShiftX;
        } else if (this.mClipRect.right > width) {
            this.mShiftX = width - this.mClipRect.right;
            this.mClipRect.right += this.mShiftX;
            this.mClipRect.left += this.mShiftX;
        }
        if (this.mClipRect.top < 25.0f) {
            this.mShiftY = 25.0f - this.mClipRect.top;
            this.mClipRect.top += this.mShiftY;
            this.mClipRect.bottom += this.mShiftY;
        } else if (this.mClipRect.bottom > height) {
            this.mShiftY = height - this.mClipRect.bottom;
            this.mClipRect.bottom += this.mShiftY;
            this.mClipRect.top += this.mShiftY;
        }
        this.mMaxScrollY = 0.0f;
        this.mMinScrollY = 0.0f;
        this.mMaxScrollX = 0.0f;
        this.mMinScrollX = 0.0f;
        if (this.mViewClipRect.right + this.mShiftX < this.mClipRect.right) {
            this.mMinScrollX = this.mViewClipRect.right - this.mClipRect.right;
        }
        if (this.mViewClipRect.left + this.mShiftX > this.mClipRect.left) {
            this.mMaxScrollX = this.mViewClipRect.left - this.mClipRect.left;
        }
        if (this.mViewClipRect.top + this.mShiftY > this.mClipRect.top) {
            this.mMaxScrollY = this.mViewClipRect.top - this.mClipRect.top;
        }
        if (this.mViewClipRect.bottom + this.mShiftY < this.mClipRect.bottom) {
            this.mMinScrollY = this.mViewClipRect.bottom - this.mClipRect.bottom;
        }
        this.mClipRect.intersect(this.mViewClipRect);
        this.mLeftExtrusion = this.mTouch.x - this.mClipRect.left;
        this.mRightExtrusion = this.mClipRect.right - this.mTouch.x;
        this.mTopExtrusion = this.mTouch.y - this.mClipRect.top;
        this.mBottomExtrusion = this.mClipRect.bottom - this.mTouch.y;
        float fCenterX = ((this.mTouch.x - this.mTargetBounds.centerX()) / (this.mTargetBounds.width() / 2.0f)) + 0.5f;
        float fCenterY = ((this.mTouch.y - this.mTargetBounds.centerY()) / (this.mTargetBounds.height() / 2.0f)) + 0.5f;
        float f3 = this.mMaxScrollX;
        float f4 = this.mMinScrollX;
        float f5 = this.mMaxScrollY - this.mMinScrollY;
        float f6 = (f3 - f4) * fCenterX * (-1.0f);
        this.mPopupScrollX = f6;
        this.mPopupScrollY = f5 * fCenterY * (-1.0f);
        this.mPopupScrollX = constrain(f6, f4, f3);
        this.mPopupScrollY = constrain(this.mPopupScrollY, this.mMinScrollY, this.mMaxScrollY);
        this.mDrawRect = new RectF();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float interpolation;
        if (!isShowing() || this.mZoomedBitmap == null) {
            return;
        }
        if (acceptZeroSizeView() || !(getWidth() == 0 || getHeight() == 0)) {
            if (this.mNeedsToInitDimensions) {
                this.mNeedsToInitDimensions = false;
                initDimensions();
            }
            canvas.save();
            float fConstrain = constrain(((SystemClock.uptimeMillis() - this.mAnimationStartTime) + this.mTimeLeft) / 300.0f, 0.0f, 1.0f);
            if (fConstrain >= 1.0f) {
                this.mAnimating = false;
                if (!isShowing()) {
                    hideImmediately();
                    return;
                }
            } else {
                invalidate();
            }
            if (this.mShowing) {
                interpolation = this.mShowInterpolator.getInterpolation(fConstrain);
            } else {
                interpolation = this.mHideInterpolator.getInterpolation(fConstrain);
            }
            canvas.drawARGB((int) (80.0f * interpolation), 0, 0, 0);
            canvas.save();
            float f = this.mScale;
            float f2 = (((f - 1.0f) * interpolation) / f) + (1.0f / f);
            float f3 = 1.0f - interpolation;
            float f4 = ((-this.mShiftX) * f3) / f;
            float f5 = ((-this.mShiftY) * f3) / f;
            this.mDrawRect.left = (this.mTouch.x - (this.mLeftExtrusion * f2)) + f4;
            this.mDrawRect.top = (this.mTouch.y - (this.mTopExtrusion * f2)) + f5;
            this.mDrawRect.right = this.mTouch.x + (this.mRightExtrusion * f2) + f4;
            this.mDrawRect.bottom = this.mTouch.y + (this.mBottomExtrusion * f2) + f5;
            canvas.clipRect(this.mDrawRect);
            canvas.scale(f2, f2, this.mDrawRect.left, this.mDrawRect.top);
            canvas.translate(this.mPopupScrollX, this.mPopupScrollY);
            canvas.drawBitmap(this.mZoomedBitmap, this.mDrawRect.left, this.mDrawRect.top, (Paint) null);
            canvas.restore();
            Drawable overlayDrawable = getOverlayDrawable(getContext());
            overlayDrawable.setBounds(((int) this.mDrawRect.left) - sOverlayPadding.left, ((int) this.mDrawRect.top) - sOverlayPadding.top, ((int) this.mDrawRect.right) + sOverlayPadding.right, ((int) this.mDrawRect.bottom) + sOverlayPadding.bottom);
            overlayDrawable.setAlpha(constrain((int) (interpolation * 255.0f), 0, 255));
            overlayDrawable.draw(canvas);
            canvas.restore();
        }
    }

    public void show(Rect rect) {
        if (this.mShowing || this.mZoomedBitmap == null) {
            return;
        }
        setTargetBounds(rect);
        startAnimation(true);
    }

    public void hide(boolean z) {
        if (this.mShowing) {
            if (z) {
                startAnimation(false);
            } else {
                hideImmediately();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF convertTouchPoint(float f, float f2) {
        return new PointF(this.mTouch.x + ((((f - this.mShiftX) - this.mTouch.x) - this.mPopupScrollX) / this.mScale), this.mTouch.y + ((((f2 - this.mShiftY) - this.mTouch.y) - this.mPopupScrollY) / this.mScale));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTouchOutsideArea(float f, float f2) {
        return !this.mClipRect.contains(f, f2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private static class ReverseInterpolator implements Interpolator {
        private final Interpolator mInterpolator;

        public ReverseInterpolator(Interpolator interpolator) {
            this.mInterpolator = interpolator;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = 1.0f - f;
            Interpolator interpolator = this.mInterpolator;
            return interpolator == null ? f2 : interpolator.getInterpolation(f2);
        }
    }
}
