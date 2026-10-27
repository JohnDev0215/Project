package com.amazon.music.skyfire.ui.widgets.decorators;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: PageDotsDecorator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J*\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0010H\u0002J \u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/decorators/PageDotsDecorator;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "context", "Landroid/content/Context;", "itemCount", "", "(Landroid/content/Context;I)V", "dotFill", "Landroid/graphics/Paint;", "dotStroke", "dots", "", "Lkotlin/Pair;", "", "dp", "isDotsInitialized", "", "selectedDot", "drawDot", "", "canvas", "Landroid/graphics/Canvas;", "x", "y", "isSelected", "onDrawOver", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "setupDots", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PageDotsDecorator extends RecyclerView.ItemDecoration {
    public static final float DOT_PADDING = 4.0f;
    public static final float DOT_RADIUS = 4.0f;
    private final Paint dotFill;
    private final Paint dotStroke;
    private final List<Pair<Float, Float>> dots;
    private final float dp;
    private boolean isDotsInitialized;
    private final int itemCount;
    private int selectedDot;

    public PageDotsDecorator(Context context, int i) {
        DisplayMetrics displayMetrics;
        Intrinsics.checkNotNullParameter(context, "context");
        this.itemCount = i;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(-7829368);
        this.dotStroke = paint;
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(-1);
        this.dotFill = paint2;
        this.dots = new ArrayList();
        Resources resources = context.getResources();
        float f = 0.0f;
        if (resources != null && (displayMetrics = resources.getDisplayMetrics()) != null) {
            f = displayMetrics.density;
        }
        this.dp = f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        if (this.itemCount == 1) {
            return;
        }
        if (!this.isDotsInitialized) {
            setupDots(parent);
        }
        if (parent.getAdapter() == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null) {
            return;
        }
        int iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (iFindFirstVisibleItemPosition >= 0) {
            this.selectedDot = iFindFirstVisibleItemPosition;
        }
        int i = this.itemCount;
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            drawDot(canvas, this.dots.get(i2).getFirst().floatValue(), this.dots.get(i2).getSecond().floatValue(), this.selectedDot == i2);
            i2 = i3;
        }
    }

    static /* synthetic */ void drawDot$default(PageDotsDecorator pageDotsDecorator, Canvas canvas, float f, float f2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        pageDotsDecorator.drawDot(canvas, f, f2, z);
    }

    private final void drawDot(Canvas canvas, float x, float y, boolean isSelected) {
        canvas.drawCircle(x, y, this.dp * 4.0f, isSelected ? this.dotFill : this.dotStroke);
    }

    private final void setupDots(RecyclerView parent) {
        float f = this.dp;
        float width = ((parent.getWidth() - ((this.itemCount * 12.0f) * f)) / 2.0f) + (f * 4.0f);
        float height = parent.getHeight() - (this.dp * 16.0f);
        Iterator<Integer> it = RangesKt___RangesKt.until(0, this.itemCount).iterator();
        while (it.hasNext()) {
            this.dots.add(TuplesKt.to(Float.valueOf((((IntIterator) it).nextInt() * 12.0f * this.dp) + width), Float.valueOf(height)));
        }
        this.isDotsInitialized = true;
    }
}
