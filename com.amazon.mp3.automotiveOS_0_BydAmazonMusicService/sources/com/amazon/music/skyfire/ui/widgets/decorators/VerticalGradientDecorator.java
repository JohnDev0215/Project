package com.amazon.music.skyfire.ui.widgets.decorators;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Shader;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt__RangesKt;

/* JADX INFO: compiled from: VerticalGradientDecorator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000b¨\u0006!"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/decorators/VerticalGradientDecorator;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "()V", "dimensions", "Landroid/graphics/PointF;", "value", "", "endColor", "getEndColor", "()I", "setEndColor", "(I)V", "gradientPaint", "Landroid/graphics/Paint;", "Lkotlin/ranges/ClosedRange;", "", "range", "getRange", "()Lkotlin/ranges/ClosedRange;", "setRange", "(Lkotlin/ranges/ClosedRange;)V", "startColor", "getStartColor", "setStartColor", "onDrawOver", "", "canvas", "Landroid/graphics/Canvas;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "resetShader", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VerticalGradientDecorator extends RecyclerView.ItemDecoration {
    private PointF dimensions = new PointF();
    private int endColor;
    private final Paint gradientPaint;
    private ClosedRange<Float> range;
    private int startColor;

    public VerticalGradientDecorator() {
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        this.gradientPaint = paint;
        this.endColor = ViewCompat.MEASURED_STATE_MASK;
        this.range = RangesKt__RangesKt.rangeTo(0.0f, 1.0f);
    }

    public final int getStartColor() {
        return this.startColor;
    }

    public final void setStartColor(int i) {
        if (this.startColor != i) {
            this.startColor = i;
            this.dimensions.set(0.0f, 0.0f);
        }
    }

    public final int getEndColor() {
        return this.endColor;
    }

    public final void setEndColor(int i) {
        if (this.endColor != i) {
            this.endColor = i;
            this.dimensions.set(0.0f, 0.0f);
        }
    }

    public final ClosedRange<Float> getRange() {
        return this.range;
    }

    public final void setRange(ClosedRange<Float> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(this.range, value)) {
            return;
        }
        this.range = value;
        this.dimensions.set(0.0f, 0.0f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        if (parent.getWidth() != ((int) this.dimensions.x) || parent.getHeight() != ((int) this.dimensions.y)) {
            this.dimensions.set(parent.getWidth(), parent.getHeight());
            resetShader();
        }
        canvas.drawRect(0.0f, 0.0f, parent.getRight(), parent.getBottom(), this.gradientPaint);
    }

    private final void resetShader() {
        this.gradientPaint.setShader(new LinearGradient(0.0f, ((Number) this.range.getStart()).floatValue() * this.dimensions.y, 0.0f, ((Number) this.range.getEndInclusive()).floatValue() * this.dimensions.y, this.startColor, this.endColor, Shader.TileMode.CLAMP));
    }
}
