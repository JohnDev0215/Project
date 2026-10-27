package com.amazon.music.skyfire.ui.widgets.decorators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: DrawableDecorator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/decorators/DrawableDecorator;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "context", "Landroid/content/Context;", "resId", "", "height", "(Landroid/content/Context;II)V", "getContext", "()Landroid/content/Context;", "drawable", "Landroid/graphics/drawable/Drawable;", "getHeight", "()I", "onDrawOver", "", "canvas", "Landroid/graphics/Canvas;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DrawableDecorator extends RecyclerView.ItemDecoration {
    private final Context context;
    private Drawable drawable;
    private final int height;

    public /* synthetic */ DrawableDecorator(Context context, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, i, (i3 & 4) != 0 ? -1 : i2);
    }

    public final Context getContext() {
        return this.context;
    }

    public DrawableDecorator(Context context, int i, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.height = i2;
        this.drawable = ContextCompat.getDrawable(context, i);
    }

    public final int getHeight() {
        return this.height;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int iCoerceAtLeast;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        Drawable drawable = this.drawable;
        if (drawable == null) {
            return;
        }
        if (getHeight() <= -1) {
            iCoerceAtLeast = 0;
        } else {
            iCoerceAtLeast = RangesKt___RangesKt.coerceAtLeast(parent.getHeight() - MathKt__MathJVMKt.roundToInt(getHeight() * getContext().getResources().getDisplayMetrics().density), 0);
        }
        drawable.setBounds(0, iCoerceAtLeast, parent.getWidth(), parent.getHeight());
        drawable.draw(canvas);
    }
}
