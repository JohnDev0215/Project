package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import com.android.internal.R;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsSeekBar extends ProgressBar {
    private static final int NO_ALPHA = 255;
    private float mDisabledAlpha;
    private final List<Rect> mGestureExclusionRects;
    private boolean mHasThumbBlendMode;
    private boolean mHasThumbTint;
    private boolean mHasTickMarkBlendMode;
    private boolean mHasTickMarkTint;
    private boolean mIsDragging;
    boolean mIsUserSeekable;
    private int mKeyProgressIncrement;
    private int mScaledTouchSlop;
    private boolean mSplitTrack;
    private final Rect mTempRect;
    private Drawable mThumb;
    private BlendMode mThumbBlendMode;
    private int mThumbExclusionMaxSize;
    private int mThumbOffset;
    private final Rect mThumbRect;
    private ColorStateList mThumbTintList;
    private Drawable mTickMark;
    private BlendMode mTickMarkBlendMode;
    private ColorStateList mTickMarkTintList;
    private float mTouchDownX;
    float mTouchProgressOffset;
    private float mTouchThumbOffset;
    private List<Rect> mUserGestureExclusionRects;

    void onKeyChange() {
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<AbsSeekBar> {
        private boolean mPropertiesMapped = false;
        private int mThumbTintId;
        private int mThumbTintModeId;
        private int mTickMarkTintBlendModeId;
        private int mTickMarkTintId;
        private int mTickMarkTintModeId;

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            this.mThumbTintId = propertyMapper.mapObject("thumbTint", 16843889);
            this.mThumbTintModeId = propertyMapper.mapObject("thumbTintMode", 16843890);
            this.mTickMarkTintId = propertyMapper.mapObject("tickMarkTint", 16844043);
            this.mTickMarkTintBlendModeId = propertyMapper.mapObject("tickMarkTintBlendMode", 7);
            this.mTickMarkTintModeId = propertyMapper.mapObject("tickMarkTintMode", 16844044);
            this.mPropertiesMapped = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public void readProperties(AbsSeekBar absSeekBar, PropertyReader propertyReader) {
            if (!this.mPropertiesMapped) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readObject(this.mThumbTintId, absSeekBar.getThumbTintList());
            propertyReader.readObject(this.mThumbTintModeId, absSeekBar.getThumbTintMode());
            propertyReader.readObject(this.mTickMarkTintId, absSeekBar.getTickMarkTintList());
            propertyReader.readObject(this.mTickMarkTintBlendModeId, absSeekBar.getTickMarkTintBlendMode());
            propertyReader.readObject(this.mTickMarkTintModeId, absSeekBar.getTickMarkTintMode());
        }
    }

    public AbsSeekBar(Context context) {
        super(context);
        this.mTempRect = new Rect();
        this.mThumbTintList = null;
        this.mThumbBlendMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbBlendMode = false;
        this.mTickMarkTintList = null;
        this.mTickMarkBlendMode = null;
        this.mHasTickMarkTint = false;
        this.mHasTickMarkBlendMode = false;
        this.mIsUserSeekable = true;
        this.mKeyProgressIncrement = 1;
        this.mTouchThumbOffset = 0.0f;
        this.mUserGestureExclusionRects = Collections.emptyList();
        this.mGestureExclusionRects = new ArrayList();
        this.mThumbRect = new Rect();
    }

    public AbsSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTempRect = new Rect();
        this.mThumbTintList = null;
        this.mThumbBlendMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbBlendMode = false;
        this.mTickMarkTintList = null;
        this.mTickMarkBlendMode = null;
        this.mHasTickMarkTint = false;
        this.mHasTickMarkBlendMode = false;
        this.mIsUserSeekable = true;
        this.mKeyProgressIncrement = 1;
        this.mTouchThumbOffset = 0.0f;
        this.mUserGestureExclusionRects = Collections.emptyList();
        this.mGestureExclusionRects = new ArrayList();
        this.mThumbRect = new Rect();
    }

    public AbsSeekBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public AbsSeekBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTempRect = new Rect();
        this.mThumbTintList = null;
        this.mThumbBlendMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbBlendMode = false;
        this.mTickMarkTintList = null;
        this.mTickMarkBlendMode = null;
        this.mHasTickMarkTint = false;
        this.mHasTickMarkBlendMode = false;
        this.mIsUserSeekable = true;
        this.mKeyProgressIncrement = 1;
        this.mTouchThumbOffset = 0.0f;
        this.mUserGestureExclusionRects = Collections.emptyList();
        this.mGestureExclusionRects = new ArrayList();
        this.mThumbRect = new Rect();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SeekBar, i, i2);
        saveAttributeDataForStyleable(context, R.styleable.SeekBar, attributeSet, typedArrayObtainStyledAttributes, i, i2);
        setThumb(typedArrayObtainStyledAttributes.getDrawable(0));
        if (typedArrayObtainStyledAttributes.hasValue(4)) {
            this.mThumbBlendMode = Drawable.parseBlendMode(typedArrayObtainStyledAttributes.getInt(4, -1), this.mThumbBlendMode);
            this.mHasThumbBlendMode = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(3)) {
            this.mThumbTintList = typedArrayObtainStyledAttributes.getColorStateList(3);
            this.mHasThumbTint = true;
        }
        setTickMark(typedArrayObtainStyledAttributes.getDrawable(5));
        if (typedArrayObtainStyledAttributes.hasValue(7)) {
            this.mTickMarkBlendMode = Drawable.parseBlendMode(typedArrayObtainStyledAttributes.getInt(7, -1), this.mTickMarkBlendMode);
            this.mHasTickMarkBlendMode = true;
        }
        if (typedArrayObtainStyledAttributes.hasValue(6)) {
            this.mTickMarkTintList = typedArrayObtainStyledAttributes.getColorStateList(6);
            this.mHasTickMarkTint = true;
        }
        this.mSplitTrack = typedArrayObtainStyledAttributes.getBoolean(2, false);
        setThumbOffset(typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, getThumbOffset()));
        boolean z = typedArrayObtainStyledAttributes.getBoolean(8, true);
        typedArrayObtainStyledAttributes.recycle();
        if (z) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.Theme, 0, 0);
            this.mDisabledAlpha = typedArrayObtainStyledAttributes2.getFloat(3, 0.5f);
            typedArrayObtainStyledAttributes2.recycle();
        } else {
            this.mDisabledAlpha = 1.0f;
        }
        applyThumbTint();
        applyTickMarkTint();
        this.mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mThumbExclusionMaxSize = getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_exclusion_max_size);
    }

    public void setThumb(Drawable drawable) {
        boolean z;
        Drawable drawable2 = this.mThumb;
        if (drawable2 == null || drawable == drawable2) {
            z = false;
        } else {
            drawable2.setCallback(null);
            z = true;
        }
        if (drawable != null) {
            drawable.setCallback(this);
            if (canResolveLayoutDirection()) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            this.mThumbOffset = drawable.getIntrinsicWidth() / 2;
            if (z && (drawable.getIntrinsicWidth() != this.mThumb.getIntrinsicWidth() || drawable.getIntrinsicHeight() != this.mThumb.getIntrinsicHeight())) {
                requestLayout();
            }
        }
        this.mThumb = drawable;
        applyThumbTint();
        invalidate();
        if (z) {
            updateThumbAndTrackPos(getWidth(), getHeight());
            if (drawable == null || !drawable.isStateful()) {
                return;
            }
            drawable.setState(getDrawableState());
        }
    }

    public Drawable getThumb() {
        return this.mThumb;
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.mThumbTintList = colorStateList;
        this.mHasThumbTint = true;
        applyThumbTint();
    }

    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        setThumbTintBlendMode(mode != null ? BlendMode.fromValue(mode.nativeInt) : null);
    }

    public void setThumbTintBlendMode(BlendMode blendMode) {
        this.mThumbBlendMode = blendMode;
        this.mHasThumbBlendMode = true;
        applyThumbTint();
    }

    public PorterDuff.Mode getThumbTintMode() {
        BlendMode blendMode = this.mThumbBlendMode;
        if (blendMode != null) {
            return BlendMode.blendModeToPorterDuffMode(blendMode);
        }
        return null;
    }

    public BlendMode getThumbTintBlendMode() {
        return this.mThumbBlendMode;
    }

    private void applyThumbTint() {
        if (this.mThumb != null) {
            if (this.mHasThumbTint || this.mHasThumbBlendMode) {
                Drawable drawableMutate = this.mThumb.mutate();
                this.mThumb = drawableMutate;
                if (this.mHasThumbTint) {
                    drawableMutate.setTintList(this.mThumbTintList);
                }
                if (this.mHasThumbBlendMode) {
                    this.mThumb.setTintBlendMode(this.mThumbBlendMode);
                }
                if (this.mThumb.isStateful()) {
                    this.mThumb.setState(getDrawableState());
                }
            }
        }
    }

    public int getThumbOffset() {
        return this.mThumbOffset;
    }

    public void setThumbOffset(int i) {
        this.mThumbOffset = i;
        invalidate();
    }

    public void setSplitTrack(boolean z) {
        this.mSplitTrack = z;
        invalidate();
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public void setTickMark(Drawable drawable) {
        Drawable drawable2 = this.mTickMark;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mTickMark = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            drawable.setLayoutDirection(getLayoutDirection());
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
            applyTickMarkTint();
        }
        invalidate();
    }

    public Drawable getTickMark() {
        return this.mTickMark;
    }

    public void setTickMarkTintList(ColorStateList colorStateList) {
        this.mTickMarkTintList = colorStateList;
        this.mHasTickMarkTint = true;
        applyTickMarkTint();
    }

    public ColorStateList getTickMarkTintList() {
        return this.mTickMarkTintList;
    }

    public void setTickMarkTintMode(PorterDuff.Mode mode) {
        setTickMarkTintBlendMode(mode != null ? BlendMode.fromValue(mode.nativeInt) : null);
    }

    public void setTickMarkTintBlendMode(BlendMode blendMode) {
        this.mTickMarkBlendMode = blendMode;
        this.mHasTickMarkBlendMode = true;
        applyTickMarkTint();
    }

    public PorterDuff.Mode getTickMarkTintMode() {
        BlendMode blendMode = this.mTickMarkBlendMode;
        if (blendMode != null) {
            return BlendMode.blendModeToPorterDuffMode(blendMode);
        }
        return null;
    }

    public BlendMode getTickMarkTintBlendMode() {
        return this.mTickMarkBlendMode;
    }

    private void applyTickMarkTint() {
        if (this.mTickMark != null) {
            if (this.mHasTickMarkTint || this.mHasTickMarkBlendMode) {
                Drawable drawableMutate = this.mTickMark.mutate();
                this.mTickMark = drawableMutate;
                if (this.mHasTickMarkTint) {
                    drawableMutate.setTintList(this.mTickMarkTintList);
                }
                if (this.mHasTickMarkBlendMode) {
                    this.mTickMark.setTintBlendMode(this.mTickMarkBlendMode);
                }
                if (this.mTickMark.isStateful()) {
                    this.mTickMark.setState(getDrawableState());
                }
            }
        }
    }

    public void setKeyProgressIncrement(int i) {
        if (i < 0) {
            i = -i;
        }
        this.mKeyProgressIncrement = i;
    }

    public int getKeyProgressIncrement() {
        return this.mKeyProgressIncrement;
    }

    @Override // android.widget.ProgressBar
    public synchronized void setMin(int i) {
        super.setMin(i);
        int max = getMax() - getMin();
        if (this.mKeyProgressIncrement == 0 || max / this.mKeyProgressIncrement > 20) {
            setKeyProgressIncrement(Math.max(1, Math.round(max / 20.0f)));
        }
    }

    @Override // android.widget.ProgressBar
    public synchronized void setMax(int i) {
        super.setMax(i);
        int max = getMax() - getMin();
        if (this.mKeyProgressIncrement == 0 || max / this.mKeyProgressIncrement > 20) {
            setKeyProgressIncrement(Math.max(1, Math.round(max / 20.0f)));
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.mThumb || drawable == this.mTickMark || super.verifyDrawable(drawable);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.mTickMark;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null && this.mDisabledAlpha < 1.0f) {
            progressDrawable.setAlpha(isEnabled() ? 255 : (int) (this.mDisabledAlpha * 255.0f));
        }
        Drawable drawable = this.mThumb;
        if (drawable != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidateDrawable(drawable);
        }
        Drawable drawable2 = this.mTickMark;
        if (drawable2 != null && drawable2.isStateful() && drawable2.setState(getDrawableState())) {
            invalidateDrawable(drawable2);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }

    @Override // android.widget.ProgressBar
    void onVisualProgressChanged(int i, float f) {
        Drawable drawable;
        super.onVisualProgressChanged(i, f);
        if (i != 16908301 || (drawable = this.mThumb) == null) {
            return;
        }
        setThumbPos(getWidth(), drawable, f, Integer.MIN_VALUE);
        invalidate();
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateThumbAndTrackPos(i, i2);
    }

    private void updateThumbAndTrackPos(int i, int i2) {
        int i3;
        int i4;
        int i5 = (i2 - this.mPaddingTop) - this.mPaddingBottom;
        Drawable currentDrawable = getCurrentDrawable();
        Drawable drawable = this.mThumb;
        int iMin = Math.min(this.mMaxHeight, i5);
        int intrinsicHeight = drawable == null ? 0 : drawable.getIntrinsicHeight();
        if (intrinsicHeight > iMin) {
            i4 = (i5 - intrinsicHeight) / 2;
            i3 = ((intrinsicHeight - iMin) / 2) + i4;
        } else {
            int i6 = (i5 - iMin) / 2;
            int i7 = ((iMin - intrinsicHeight) / 2) + i6;
            i3 = i6;
            i4 = i7;
        }
        if (currentDrawable != null) {
            currentDrawable.setBounds(0, i3, (i - this.mPaddingRight) - this.mPaddingLeft, iMin + i3);
        }
        if (drawable != null) {
            setThumbPos(i, drawable, getScale(), i4);
        }
    }

    private float getScale() {
        int max = getMax() - getMin();
        if (max > 0) {
            return (getProgress() - r0) / max;
        }
        return 0.0f;
    }

    private void setThumbPos(int i, Drawable drawable, float f, int i2) {
        int i3;
        int i4 = (i - this.mPaddingLeft) - this.mPaddingRight;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i5 = (i4 - intrinsicWidth) + (this.mThumbOffset * 2);
        int i6 = (int) ((f * i5) + 0.5f);
        if (i2 == Integer.MIN_VALUE) {
            Rect bounds = drawable.getBounds();
            int i7 = bounds.top;
            i3 = bounds.bottom;
            i2 = i7;
        } else {
            i3 = intrinsicHeight + i2;
        }
        if (isLayoutRtl() && this.mMirrorForRtl) {
            i6 = i5 - i6;
        }
        int i8 = intrinsicWidth + i6;
        Drawable background = getBackground();
        if (background != null) {
            int i9 = this.mPaddingLeft - this.mThumbOffset;
            int i10 = this.mPaddingTop;
            background.setHotspotBounds(i6 + i9, i2 + i10, i9 + i8, i10 + i3);
        }
        drawable.setBounds(i6, i2, i8, i3);
        updateGestureExclusionRects();
    }

    @Override // android.view.View
    public void setSystemGestureExclusionRects(List<Rect> list) {
        Preconditions.checkNotNull(list, "rects must not be null");
        this.mUserGestureExclusionRects = list;
        updateGestureExclusionRects();
    }

    private void updateGestureExclusionRects() {
        Drawable drawable = this.mThumb;
        if (drawable == null) {
            super.setSystemGestureExclusionRects(this.mUserGestureExclusionRects);
            return;
        }
        this.mGestureExclusionRects.clear();
        drawable.copyBounds(this.mThumbRect);
        this.mThumbRect.offset(this.mPaddingLeft - this.mThumbOffset, this.mPaddingTop);
        growRectTo(this.mThumbRect, Math.min(getHeight(), this.mThumbExclusionMaxSize));
        this.mGestureExclusionRects.add(this.mThumbRect);
        this.mGestureExclusionRects.addAll(this.mUserGestureExclusionRects);
        super.setSystemGestureExclusionRects(this.mGestureExclusionRects);
    }

    private void growRectTo(Rect rect, int i) {
        int iHeight = (i - rect.height()) / 2;
        if (iHeight > 0) {
            rect.top -= iHeight;
            rect.bottom += iHeight;
        }
        int iWidth = (i - rect.width()) / 2;
        if (iWidth > 0) {
            rect.left -= iWidth;
            rect.right += iWidth;
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onResolveDrawables(int i) {
        super.onResolveDrawables(i);
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            drawable.setLayoutDirection(i);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawThumb(canvas);
    }

    @Override // android.widget.ProgressBar
    void drawTrack(Canvas canvas) {
        Drawable drawable = this.mThumb;
        if (drawable != null && this.mSplitTrack) {
            Insets opticalInsets = drawable.getOpticalInsets();
            Rect rect = this.mTempRect;
            drawable.copyBounds(rect);
            rect.offset(this.mPaddingLeft - this.mThumbOffset, this.mPaddingTop);
            rect.left += opticalInsets.left;
            rect.right -= opticalInsets.right;
            int iSave = canvas.save();
            canvas.clipRect(rect, Region.Op.DIFFERENCE);
            super.drawTrack(canvas);
            drawTickMarks(canvas);
            canvas.restoreToCount(iSave);
            return;
        }
        super.drawTrack(canvas);
        drawTickMarks(canvas);
    }

    protected void drawTickMarks(Canvas canvas) {
        if (this.mTickMark != null) {
            int max = getMax() - getMin();
            if (max > 1) {
                int intrinsicWidth = this.mTickMark.getIntrinsicWidth();
                int intrinsicHeight = this.mTickMark.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.mTickMark.setBounds(-i, -i2, i, i2);
                float width = ((getWidth() - this.mPaddingLeft) - this.mPaddingRight) / max;
                int iSave = canvas.save();
                canvas.translate(this.mPaddingLeft, getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.mTickMark.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    void drawThumb(Canvas canvas) {
        if (this.mThumb != null) {
            int iSave = canvas.save();
            canvas.translate(this.mPaddingLeft - this.mThumbOffset, this.mPaddingTop);
            this.mThumb.draw(canvas);
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        int iMax;
        int iMax2;
        Drawable currentDrawable = getCurrentDrawable();
        int intrinsicHeight = this.mThumb == null ? 0 : this.mThumb.getIntrinsicHeight();
        if (currentDrawable != null) {
            iMax2 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, currentDrawable.getIntrinsicWidth()));
            iMax = Math.max(intrinsicHeight, Math.max(this.mMinHeight, Math.min(this.mMaxHeight, currentDrawable.getIntrinsicHeight())));
        } else {
            iMax = 0;
            iMax2 = 0;
        }
        setMeasuredDimension(resolveSizeAndState(iMax2 + this.mPaddingLeft + this.mPaddingRight, i, 0), resolveSizeAndState(iMax + this.mPaddingTop + this.mPaddingBottom, i2, 0));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsUserSeekable || !isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (this.mThumb != null) {
                float width = (getWidth() - this.mPaddingLeft) - this.mPaddingRight;
                float progress = ((getProgress() - getMin()) / (getMax() - getMin())) - ((motionEvent.getX() - this.mPaddingLeft) / width);
                this.mTouchThumbOffset = progress;
                if (Math.abs(progress * width) > getThumbOffset()) {
                    this.mTouchThumbOffset = 0.0f;
                }
            }
            if (isInScrollingContainer()) {
                this.mTouchDownX = motionEvent.getX();
            } else {
                startDrag(motionEvent);
            }
        } else if (action == 1) {
            if (this.mIsDragging) {
                trackTouchEvent(motionEvent);
                onStopTrackingTouch();
                setPressed(false);
            } else {
                onStartTrackingTouch();
                trackTouchEvent(motionEvent);
                onStopTrackingTouch();
            }
            invalidate();
        } else if (action != 2) {
            if (action == 3) {
                if (this.mIsDragging) {
                    onStopTrackingTouch();
                    setPressed(false);
                }
                invalidate();
            }
        } else if (this.mIsDragging) {
            trackTouchEvent(motionEvent);
        } else if (Math.abs(motionEvent.getX() - this.mTouchDownX) > this.mScaledTouchSlop) {
            startDrag(motionEvent);
        }
        return true;
    }

    private void startDrag(MotionEvent motionEvent) {
        setPressed(true);
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            invalidate(drawable.getBounds());
        }
        onStartTrackingTouch();
        trackTouchEvent(motionEvent);
        attemptClaimDrag();
    }

    private void setHotspot(float f, float f2) {
        Drawable background = getBackground();
        if (background != null) {
            background.setHotspot(f, f2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void trackTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            float r0 = r7.getX()
            int r0 = java.lang.Math.round(r0)
            float r7 = r7.getY()
            int r7 = java.lang.Math.round(r7)
            int r1 = r6.getWidth()
            int r2 = r6.mPaddingLeft
            int r2 = r1 - r2
            int r3 = r6.mPaddingRight
            int r2 = r2 - r3
            boolean r3 = r6.isLayoutRtl()
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 0
            if (r3 == 0) goto L42
            boolean r3 = r6.mMirrorForRtl
            if (r3 == 0) goto L42
            int r3 = r6.mPaddingRight
            int r1 = r1 - r3
            if (r0 <= r1) goto L2e
            goto L46
        L2e:
            int r1 = r6.mPaddingLeft
            if (r0 >= r1) goto L33
            goto L5b
        L33:
            int r1 = r2 - r0
            int r3 = r6.mPaddingLeft
            int r1 = r1 + r3
            float r1 = (float) r1
            float r2 = (float) r2
            float r1 = r1 / r2
            float r2 = r6.mTouchThumbOffset
            float r4 = r1 + r2
            float r5 = r6.mTouchProgressOffset
            goto L5b
        L42:
            int r3 = r6.mPaddingLeft
            if (r0 >= r3) goto L48
        L46:
            r4 = r5
            goto L5b
        L48:
            int r3 = r6.mPaddingRight
            int r1 = r1 - r3
            if (r0 <= r1) goto L4e
            goto L5b
        L4e:
            int r1 = r6.mPaddingLeft
            int r1 = r0 - r1
            float r1 = (float) r1
            float r2 = (float) r2
            float r1 = r1 / r2
            float r2 = r6.mTouchThumbOffset
            float r4 = r1 + r2
            float r5 = r6.mTouchProgressOffset
        L5b:
            int r1 = r6.getMax()
            int r2 = r6.getMin()
            int r1 = r1 - r2
            float r1 = (float) r1
            float r4 = r4 * r1
            int r1 = r6.getMin()
            float r1 = (float) r1
            float r4 = r4 + r1
            float r5 = r5 + r4
            float r0 = (float) r0
            float r7 = (float) r7
            r6.setHotspot(r0, r7)
            int r7 = java.lang.Math.round(r5)
            r0 = 1
            r1 = 0
            r6.setProgressInternal(r7, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.AbsSeekBar.trackTouchEvent(android.view.MotionEvent):void");
    }

    private void attemptClaimDrag() {
        if (this.mParent != null) {
            this.mParent.requestDisallowInterceptTouchEvent(true);
        }
    }

    void onStartTrackingTouch() {
        this.mIsDragging = true;
    }

    void onStopTrackingTouch() {
        this.mIsDragging = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0031  */
    @Override // android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onKeyDown(int r3, android.view.KeyEvent r4) {
        /*
            r2 = this;
            boolean r0 = r2.isEnabled()
            if (r0 == 0) goto L35
            int r0 = r2.mKeyProgressIncrement
            r1 = 21
            if (r3 == r1) goto L1d
            r1 = 22
            if (r3 == r1) goto L1e
            r1 = 69
            if (r3 == r1) goto L1d
            r1 = 70
            if (r3 == r1) goto L1e
            r1 = 81
            if (r3 == r1) goto L1e
            goto L35
        L1d:
            int r0 = -r0
        L1e:
            boolean r1 = r2.isLayoutRtl()
            if (r1 == 0) goto L25
            int r0 = -r0
        L25:
            int r1 = r2.getProgress()
            int r1 = r1 + r0
            r0 = 1
            boolean r1 = r2.setProgressInternal(r1, r0, r0)
            if (r1 == 0) goto L35
            r2.onKeyChange()
            return r0
        L35:
            boolean r3 = super.onKeyDown(r3, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.AbsSeekBar.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    @Override // android.widget.ProgressBar, android.view.View
    public CharSequence getAccessibilityClassName() {
        return AbsSeekBar.class.getName();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        if (isEnabled()) {
            int progress = getProgress();
            if (progress > getMin()) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
            }
            if (progress < getMax()) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            }
        }
    }

    @Override // android.view.View
    public boolean performAccessibilityActionInternal(int i, Bundle bundle) {
        if (super.performAccessibilityActionInternal(i, bundle)) {
            return true;
        }
        if (!isEnabled()) {
            return false;
        }
        if (i != 4096 && i != 8192) {
            if (i == 16908349 && canUserSetProgress() && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                return setProgressInternal((int) bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"), true, true);
            }
            return false;
        }
        if (!canUserSetProgress()) {
            return false;
        }
        int iMax = Math.max(1, Math.round((getMax() - getMin()) / 20.0f));
        if (i == 8192) {
            iMax = -iMax;
        }
        if (!setProgressInternal(getProgress() + iMax, true, true)) {
            return false;
        }
        onKeyChange();
        return true;
    }

    boolean canUserSetProgress() {
        return !isIndeterminate() && isEnabled();
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        Drawable drawable = this.mThumb;
        if (drawable != null) {
            setThumbPos(getWidth(), drawable, getScale(), Integer.MIN_VALUE);
            invalidate();
        }
    }
}
