package com.byd.view;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class ColorSelectBar extends View {
    public static final int SHAPE_CIRCLE = 0;
    public static final int SHAPE_RECT = 1;
    private Paint barPaint;
    private int centerY;
    private int color;
    private int height;
    private Paint mPaint;
    private OnColorChangeListener onColorChangeListener;
    private int progressRadius;
    private int rectCornerRadius;
    private int rectThumbOffset;
    private int shapeType;
    private int tempColor;
    private int thumbBorderColor;
    private int thumbBorderWidth;
    private int thumbBoundHeight;
    private int thumbBoundWidth;
    private Drawable thumbDrawable;
    private int thumbOffset;
    private int thumbRadius;
    private RectF thumbRect;
    private boolean thumbStroke;
    private int thumbStrokeBgColor;
    private int width;
    private static final int[] PRESS_STATE_SET = {R.attr.state_pressed};
    private static final int[] COLORS = {SupportMenu.CATEGORY_MASK, InputDeviceCompat.SOURCE_ANY, -16711936, -16711681, -16776961, -65281};

    public interface OnColorChangeListener {
        void onColorChange(int i);
    }

    public ColorSelectBar(Context context) {
        this(context, null);
    }

    public ColorSelectBar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.color = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.byd.auto_photo.R.styleable.ColorSelectBar);
        this.progressRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.thumbRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.shapeType = typedArrayObtainStyledAttributes.getInteger(3, 0);
        this.rectCornerRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.thumbBorderColor = typedArrayObtainStyledAttributes.getColor(4, 0);
        this.rectThumbOffset = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.thumbBorderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 2);
        this.thumbDrawable = typedArrayObtainStyledAttributes.getDrawable(6);
        typedArrayObtainStyledAttributes.recycle();
        this.barPaint = new Paint();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
    }

    private int ave(int i, int i2, int i3, int i4) {
        return i + (((i2 - i) * i4) / i3);
    }

    private int calcSize(int i, int i2) {
        return View.MeasureSpec.getMode(i) == 1073741824 ? View.MeasureSpec.getSize(i) : i2;
    }

    private void checkThumbOffset() {
        if (this.thumbDrawable != null) {
            int i = this.thumbOffset;
            if (i < 0) {
                this.thumbOffset = 0;
                return;
            }
            int i2 = this.width;
            int i3 = this.thumbBoundWidth;
            if (i > i2 - i3) {
                this.thumbOffset = i2 - i3;
                return;
            }
            return;
        }
        if (this.shapeType == 1) {
            int i4 = this.thumbOffset;
            int i5 = this.height;
            if (i4 <= i5) {
                this.thumbOffset = i5;
            }
            int i6 = this.thumbOffset;
            int i7 = this.width;
            if (i6 >= i7 - i5) {
                this.thumbOffset = i7 - i5;
                return;
            }
            return;
        }
        int i8 = this.thumbOffset;
        int i9 = this.thumbRadius;
        if (i8 <= i9) {
            this.thumbOffset = i9;
        }
        int i10 = this.thumbOffset;
        int i11 = this.width;
        if (i10 >= i11 - i9) {
            this.thumbOffset = i11 - i9;
        }
    }

    private void drawBackground(Canvas canvas) {
        if (this.shapeType != 1) {
            int i = this.thumbRadius;
            int i2 = this.progressRadius;
            int i3 = i + i2;
            int i4 = (this.width - i) - i2;
            canvas.drawCircle(i3, this.centerY, i2, this.barPaint);
            canvas.drawCircle(i4, this.centerY, this.progressRadius, this.barPaint);
            int i5 = this.centerY;
            int i6 = this.progressRadius;
            canvas.drawRect(new Rect(i3, i5 - i6, i4, i5 + i6), this.barPaint);
            return;
        }
        int i7 = this.rectCornerRadius;
        if (i7 == 0) {
            int i8 = this.thumbRadius;
            int i9 = this.centerY;
            int i10 = this.progressRadius;
            canvas.drawRect(i8, i9 - i10, this.width - i8, i9 + i10, this.barPaint);
            return;
        }
        int i11 = this.thumbRadius;
        int i12 = this.centerY;
        int i13 = this.progressRadius;
        canvas.drawRoundRect(i11, i12 - i13, this.width - i11, i12 + i13, i7, i7, this.barPaint);
    }

    private void drawThumb(Canvas canvas) {
        int currentColor = getCurrentColor();
        this.tempColor = currentColor;
        Drawable drawable = this.thumbDrawable;
        if (drawable != null) {
            int i = this.thumbOffset;
            int i2 = this.centerY;
            int i3 = this.thumbBoundHeight;
            drawable.setBounds(i, i2 - (i3 / 2), this.thumbBoundWidth + i, i2 + (i3 / 2));
            this.thumbDrawable.draw(canvas);
            return;
        }
        this.mPaint.setColor(currentColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        if (this.shapeType != 1) {
            if (!this.thumbStroke) {
                canvas.drawCircle(this.thumbOffset, this.centerY, this.thumbRadius, this.mPaint);
                return;
            }
            canvas.drawCircle(this.thumbOffset, this.centerY, this.thumbRadius, this.mPaint);
            this.mPaint.setColor(this.thumbStrokeBgColor);
            canvas.drawCircle(this.thumbOffset, this.centerY, this.thumbRadius - 6, this.mPaint);
            return;
        }
        RectF rectF = this.thumbRect;
        if (rectF == null) {
            int i4 = this.thumbOffset;
            int i5 = this.thumbRadius;
            int i6 = this.rectThumbOffset;
            int i7 = this.centerY;
            this.thumbRect = new RectF((i4 - i5) + i6, (i7 - i5) + i6, (i4 + i5) - i6, (i7 + i5) - i6);
        } else {
            int i8 = this.thumbOffset;
            int i9 = this.thumbRadius;
            int i10 = this.rectThumbOffset;
            int i11 = this.centerY;
            rectF.set((i8 - i9) + i10, (i11 - i9) + i10, (i8 + i9) - i10, (i11 + i9) - i10);
        }
        RectF rectF2 = this.thumbRect;
        int i12 = this.rectCornerRadius;
        canvas.drawRoundRect(rectF2, i12, i12, this.mPaint);
        int i13 = this.thumbBorderColor;
        if (i13 != 0) {
            this.mPaint.setColor(i13);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.thumbBorderWidth);
            RectF rectF3 = this.thumbRect;
            int i14 = this.rectCornerRadius;
            canvas.drawRoundRect(rectF3, i14, i14, this.mPaint);
        }
    }

    private int getCurrentColor() {
        int length;
        int i;
        int i2;
        if (this.thumbDrawable != null) {
            length = this.width / (COLORS.length - 1);
            i = this.thumbOffset;
        } else {
            int i3 = this.width;
            int i4 = this.thumbRadius;
            length = (i3 - (i4 * 2)) / (COLORS.length - 1);
            i = this.thumbOffset - i4;
        }
        int i5 = 0;
        if (length == 0) {
            i2 = 0;
        } else {
            i5 = i / length;
            i2 = i % length;
        }
        int[] iArr = COLORS;
        if (i5 >= iArr.length - 1) {
            return iArr[iArr.length - 1];
        }
        int i6 = iArr[i5];
        int i7 = iArr[i5 + 1];
        return Color.argb(ave(Color.alpha(i6), Color.alpha(i7), length, i2), ave(Color.red(i6), Color.red(i7), length, i2), ave(Color.green(i6), Color.green(i7), length, i2), ave(Color.blue(i6), Color.blue(i7), length, i2));
    }

    private boolean updateDrawable(Drawable drawable) {
        if (!drawable.isStateful() || !drawable.setState(getDrawableState())) {
            return false;
        }
        invalidateDrawable(drawable);
        return true;
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.thumbDrawable;
        if (drawable == null || !updateDrawable(drawable)) {
            return;
        }
        invalidate();
    }

    public int getColor() {
        if (this.color == -1) {
            this.color = getCurrentColor();
        }
        return this.color;
    }

    public int getThumbOffset() {
        return this.thumbOffset;
    }

    @Override // android.view.View
    protected int[] onCreateDrawableState(int i) {
        if (this.thumbDrawable == null) {
            return super.onCreateDrawableState(i);
        }
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isPressed()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, PRESS_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawThumb(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.thumbOffset == 0) {
            this.thumbOffset = this.shapeType == 1 ? this.thumbRadius * 2 : this.thumbRadius;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        this.width = calcSize(i, 120);
        int iCalcSize = calcSize(i2, 40);
        this.height = iCalcSize;
        setMeasuredDimension(this.width, iCalcSize);
        if (this.thumbRadius == 0) {
            this.thumbRadius = this.height >> 1;
        }
        if (this.progressRadius == 0) {
            this.progressRadius = this.shapeType == 1 ? this.thumbRadius : this.thumbRadius / 3;
        }
        int i3 = this.thumbRadius;
        int i4 = this.width - i3;
        int i5 = this.height >> 1;
        this.centerY = i5;
        this.barPaint.setShader(new LinearGradient(i3, i5, i4, i5, COLORS, (float[]) null, Shader.TileMode.CLAMP));
        Drawable drawable = this.thumbDrawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = this.thumbDrawable.getIntrinsicHeight();
            int i6 = this.height;
            if (intrinsicHeight > i6) {
                this.thumbBoundHeight = i6;
                this.thumbBoundWidth = (intrinsicWidth * i6) / intrinsicHeight;
            } else {
                this.thumbBoundWidth = intrinsicWidth;
                this.thumbBoundHeight = intrinsicHeight;
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.thumbOffset = (int) motionEvent.getX();
        checkThumbOffset();
        invalidate();
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            setPressed(false);
            int i = this.color;
            int i2 = this.tempColor;
            if (i != i2) {
                this.color = i2;
                OnColorChangeListener onColorChangeListener = this.onColorChangeListener;
                if (onColorChangeListener != null) {
                    onColorChangeListener.onColorChange(i2);
                }
            }
        } else {
            setPressed(true);
        }
        return true;
    }

    public void reset() {
        this.color = -1;
        this.thumbOffset = this.thumbRadius;
    }

    public void setOnColorChangeListener(OnColorChangeListener onColorChangeListener) {
        this.onColorChangeListener = onColorChangeListener;
    }

    public void setProgressRadius(int i) {
        this.progressRadius = i;
    }

    public void setRectCornerRadius(int i) {
        this.rectCornerRadius = i;
    }

    public void setShapeType(int i) {
        this.shapeType = i;
    }

    public void setThumbDrawable(Drawable drawable) {
        this.thumbDrawable = drawable;
    }

    public void setThumbOffset(int i) {
        int i2 = this.thumbRadius;
        if (i <= i2) {
            i = i2;
        }
        int i3 = this.width;
        if (i >= i3 - i2) {
            i = i3 - i2;
        }
        this.thumbOffset = i;
        w.c("setThumbOffset : " + i);
        invalidate();
    }

    public void setThumbRadius(int i) {
        this.thumbRadius = i;
    }

    public void setThumbStroke(boolean z) {
        this.thumbStroke = z;
    }

    public void setThumbStrokeBgColor(int i) {
        this.thumbStrokeBgColor = i;
    }
}
