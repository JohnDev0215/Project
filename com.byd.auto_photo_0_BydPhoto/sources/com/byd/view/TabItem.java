package com.byd.view;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

/* JADX INFO: loaded from: classes2.dex */
public class TabItem extends View implements Checkable {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int LINE_SPACING = 5;
    private boolean checkable;
    private boolean checked;
    private boolean checkedTextBold;
    private Drawable drawableLeft;
    private int drawablePadding;
    private Drawable drawableTop;
    private OnCheckedChangeListener onCheckedChangeListener;
    private Paint paint;
    private int singleLineHeight;
    private CharSequence text;
    private ColorStateList textColor;
    private int textGravity;
    private int textHeight;
    private Rect textRect;
    private int textSize;
    private int textWidth;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(TabItem tabItem, boolean z);
    }

    public TabItem(Context context) {
        this(context, null, 0, 0);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public TabItem(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public TabItem(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.textSize = 12;
        this.checkable = true;
        this.textWidth = 0;
        this.textHeight = 0;
        this.textRect = new Rect();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.byd.auto_photo.R.styleable.TabItem, i, i2);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(1, false);
        this.textColor = typedArrayObtainStyledAttributes.getColorStateList(7);
        this.textSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, this.textSize);
        this.drawableTop = typedArrayObtainStyledAttributes.getDrawable(5);
        this.drawableLeft = typedArrayObtainStyledAttributes.getDrawable(3);
        this.text = typedArrayObtainStyledAttributes.getText(6);
        this.drawablePadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 15);
        this.checkable = typedArrayObtainStyledAttributes.getBoolean(0, true);
        this.checkedTextBold = typedArrayObtainStyledAttributes.getBoolean(2, false);
        this.textGravity = typedArrayObtainStyledAttributes.getInteger(8, 0);
        typedArrayObtainStyledAttributes.recycle();
        setClickable(true);
        setLongClickable(true);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setTextSize(this.textSize);
        setChecked(z);
    }

    private int getTextBaseLine() {
        Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
        float height = getHeight() / 2.0f;
        float f = fontMetrics.bottom;
        return (int) ((height + ((f - fontMetrics.top) / 2.0f)) - f);
    }

    private int getTextLineHeight() {
        Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
        return (int) (fontMetrics.bottom - fontMetrics.top);
    }

    private boolean hasExtrasWidth() {
        String language = getContext().getResources().getConfiguration().locale.getLanguage();
        return (language.startsWith("zh") || language.startsWith("ja") || language.startsWith("ko")) ? false : true;
    }

    private void initTextBound() {
        if (this.text != null) {
            Rect rect = new Rect();
            if (!this.text.toString().contains("\n")) {
                this.paint.getTextBounds(this.text.toString(), 0, this.text.length(), rect);
                int textLineHeight = getTextLineHeight();
                this.textHeight = textLineHeight;
                this.singleLineHeight = textLineHeight;
                this.textWidth = rect.width();
                if (hasExtrasWidth()) {
                    this.textWidth += 10;
                    return;
                }
                return;
            }
            String[] strArrSplit = this.text.toString().split("\n");
            int textLineHeight2 = 0;
            for (String str : strArrSplit) {
                this.paint.getTextBounds(str, 0, str.length(), rect);
                int iWidth = rect.width();
                if (this.textWidth < iWidth) {
                    this.textWidth = iWidth;
                    textLineHeight2 = getTextLineHeight();
                }
            }
            if (textLineHeight2 == 0) {
                textLineHeight2 = getTextLineHeight();
            }
            int i = textLineHeight2 + 5;
            this.singleLineHeight = i;
            this.textHeight = i * strArrSplit.length;
        }
    }

    private void updateDrawable(Drawable drawable) {
        if (drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidateDrawable(drawable);
        }
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.drawableTop;
        if (drawable != null || (drawable = this.drawableLeft) != null) {
            updateDrawable(drawable);
        }
        invalidate();
    }

    public CharSequence getText() {
        return this.text;
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    @Override // android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, CHECKED_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.textWidth = 0;
        this.textHeight = 0;
        this.singleLineHeight = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0115  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onDraw(android.graphics.Canvas r11) {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.view.TabItem.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iMax;
        int i3;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        initTextBound();
        Drawable drawable = this.drawableTop;
        if (drawable != null) {
            int intrinsicHeight = drawable.getIntrinsicHeight();
            iMax = Math.max(this.drawableTop.getIntrinsicWidth(), this.textWidth);
            i3 = intrinsicHeight + this.textHeight + this.drawablePadding;
        } else {
            Drawable drawable2 = this.drawableLeft;
            if (drawable2 != null) {
                int intrinsicWidth = drawable2.getIntrinsicWidth();
                int intrinsicHeight2 = this.drawableLeft.getIntrinsicHeight();
                int i4 = intrinsicWidth + this.textWidth + this.drawablePadding;
                int iMax2 = Math.max(intrinsicHeight2, this.textHeight);
                iMax = i4;
                i3 = iMax2;
            } else {
                iMax = this.textWidth;
                i3 = this.textHeight;
            }
        }
        if (View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            size = iMax;
        }
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            size2 = i3;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public boolean performClick() {
        if (!this.checkable) {
            return super.performClick();
        }
        if (!this.checked) {
            setChecked(true);
        }
        boolean zPerformClick = super.performClick();
        if (!zPerformClick) {
            playSoundEffect(0);
        }
        return zPerformClick;
    }

    public void setCheckable(boolean z) {
        this.checkable = z;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.checked != z) {
            this.checked = z;
            refreshDrawableState();
            OnCheckedChangeListener onCheckedChangeListener = this.onCheckedChangeListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, z);
            }
        }
    }

    public void setDrawableLeft(Drawable drawable) {
        this.drawableLeft = drawable;
    }

    public void setDrawableTop(Drawable drawable) {
        this.drawableTop = drawable;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public void setText(CharSequence charSequence) {
        this.text = charSequence;
        invalidate();
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.textColor = colorStateList;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.checked);
    }
}
