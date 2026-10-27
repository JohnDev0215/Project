package com.amazon.music.inappmessaging.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WrapWidthCenteredTextView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0011\u001a\u00020\tH\u0016J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\u0018\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\tH\u0014R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/inappmessaging/ui/widget/WrapWidthCenteredTextView;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "extraPaddingRight", "getExtraPaddingRight", "()Ljava/lang/Integer;", "setExtraPaddingRight", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCompoundPaddingRight", "getMaxLineWidth", "layout", "Landroid/text/Layout;", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "DMMNotificationUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WrapWidthCenteredTextView extends TextView {
    private Integer extraPaddingRight;

    public final Integer getExtraPaddingRight() {
        return this.extraPaddingRight;
    }

    public final void setExtraPaddingRight(Integer num) {
        this.extraPaddingRight = num;
    }

    public WrapWidthCenteredTextView(Context context) {
        super(context);
    }

    public WrapWidthCenteredTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WrapWidthCenteredTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getLayout() != null) {
            Layout layout = getLayout();
            Intrinsics.checkNotNullExpressionValue(layout, "layout");
            setMeasuredDimension(getMaxLineWidth(layout) + getCompoundPaddingLeft() + getCompoundPaddingRight(), getMeasuredHeight());
        }
    }

    private final int getMaxLineWidth(Layout layout) {
        int lineCount = layout.getLineCount();
        float fMax = 0.0f;
        for (int i = 0; i < lineCount; i++) {
            fMax = Math.max(fMax, layout.getLineWidth(i));
        }
        return (int) Math.ceil(fMax);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (getLayout() == null || getLayout().getLineCount() < 2) {
            super.onDraw(canvas);
            return;
        }
        int width = getLayout().getWidth();
        Layout layout = getLayout();
        Intrinsics.checkNotNullExpressionValue(layout, "layout");
        int maxLineWidth = ((width - getMaxLineWidth(layout)) * (-1)) / 2;
        this.extraPaddingRight = Integer.valueOf(maxLineWidth);
        canvas.save();
        canvas.translate(maxLineWidth, 0.0f);
        super.onDraw(canvas);
        this.extraPaddingRight = null;
        canvas.restore();
    }

    @Override // android.widget.TextView
    public int getCompoundPaddingRight() {
        Integer num = this.extraPaddingRight;
        if (num != null) {
            return num.intValue();
        }
        return super.getCompoundPaddingRight();
    }
}
