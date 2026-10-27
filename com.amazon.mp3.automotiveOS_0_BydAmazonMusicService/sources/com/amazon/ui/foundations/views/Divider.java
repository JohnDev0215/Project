package com.amazon.ui.foundations.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.amazon.ui.foundations.R$layout;
import com.amazon.ui.foundations.styles.DividerStyle;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Divider.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0016B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/ui/foundations/views/Divider;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "dividerStyle", "Lcom/amazon/ui/foundations/styles/DividerStyle;", "length", "", "paint", "Landroid/graphics/Paint;", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "setLayoutParams", "setLength", "setStyle", "StyleBuilder", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Divider extends FrameLayout {
    private DividerStyle dividerStyle;
    private float length;
    private final Paint paint;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public Divider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ Divider(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public Divider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        this.paint = paint;
        View.inflate(context, R$layout.divider, this);
        setWillNotDraw(false);
    }

    public final void setStyle(DividerStyle dividerStyle) {
        Intrinsics.checkNotNullParameter(dividerStyle, "dividerStyle");
        this.dividerStyle = dividerStyle;
        setLayoutParams();
    }

    public final void setLength(float length) {
        this.length = length;
        setLayoutParams();
    }

    private final void setLayoutParams() {
        DividerStyle dividerStyle;
        Boolean isHorizontal;
        FrameLayout.LayoutParams layoutParams;
        if (this.length <= 0.0f || (dividerStyle = this.dividerStyle) == null || (isHorizontal = dividerStyle.getIsHorizontal()) == null) {
            return;
        }
        if (isHorizontal.booleanValue()) {
            layoutParams = new FrameLayout.LayoutParams((int) this.length, (int) dividerStyle.getLineWidth());
        } else {
            layoutParams = new FrameLayout.LayoutParams((int) dividerStyle.getLineWidth(), (int) this.length);
        }
        setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DividerStyle dividerStyle = this.dividerStyle;
        if (dividerStyle != null) {
            this.paint.setColor(dividerStyle.getColor());
            Boolean isHorizontal = dividerStyle.getIsHorizontal();
            if (isHorizontal == null) {
                return;
            }
            if (isHorizontal.booleanValue()) {
                if (canvas == null) {
                    return;
                }
                canvas.drawRect(0.0f, 0.0f, this.length, dividerStyle.getLineWidth(), this.paint);
            } else {
                if (canvas == null) {
                    return;
                }
                canvas.drawRect(0.0f, 0.0f, dividerStyle.getLineWidth(), this.length, this.paint);
            }
        }
    }

    /* JADX INFO: compiled from: Divider.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\b\u0010\f\u001a\u00020\u0000H\u0016R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/ui/foundations/views/Divider$StyleBuilder;", "", "Lcom/amazon/ui/foundations/views/Divider;", "Lcom/amazon/ui/foundations/styles/DividerStyle;", "dividerStyle", "withStyle", "", "length", "withLength", "view", "", "applyStyle", "copy", "builderDividerLength", "Ljava/lang/Float;", "builderDividerStyle", "Lcom/amazon/ui/foundations/styles/DividerStyle;", "<init>", "()V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
    public static final class StyleBuilder {
        private Float builderDividerLength;
        private DividerStyle builderDividerStyle;

        public StyleBuilder withStyle(DividerStyle dividerStyle) {
            Intrinsics.checkNotNullParameter(dividerStyle, "dividerStyle");
            this.builderDividerStyle = dividerStyle;
            return this;
        }

        public final StyleBuilder withLength(float length) {
            this.builderDividerLength = Float.valueOf(length);
            return this;
        }

        public void applyStyle(Divider view) {
            Intrinsics.checkNotNullParameter(view, "view");
            DividerStyle dividerStyle = this.builderDividerStyle;
            if (dividerStyle != null) {
                view.setStyle(dividerStyle);
            }
            Float f = this.builderDividerLength;
            if (f == null) {
                return;
            }
            view.setLength(f.floatValue());
        }

        public StyleBuilder copy() {
            StyleBuilder styleBuilder = new StyleBuilder();
            DividerStyle dividerStyle = this.builderDividerStyle;
            if (dividerStyle != null) {
                styleBuilder.withStyle(dividerStyle);
            }
            Float f = this.builderDividerLength;
            if (f != null) {
                styleBuilder.withLength(f.floatValue());
            }
            return styleBuilder;
        }
    }
}
