package com.amazon.music.skyfire.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoundedRecyclerView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tH\u0014J\u0018\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\tH\u0002R$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/music/skyfire/ui/views/RoundedRecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "", "cornerRadius", "getCornerRadius", "()F", "setCornerRadius", "(F)V", "path", "Landroid/graphics/Path;", "radius", "dispatchDraw", "", "canvas", "Landroid/graphics/Canvas;", "draw", "onSizeChanged", "w", "h", "oldw", "oldh", "resetPath", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RoundedRecyclerView extends RecyclerView {
    private final Path path;
    private float radius;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoundedRecyclerView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.path = new Path();
        this.radius = context.getResources().getDimension(R.dimen.spacer_base);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoundedRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.path = new Path();
        this.radius = context.getResources().getDimension(R.dimen.spacer_base);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoundedRecyclerView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.path = new Path();
        this.radius = context.getResources().getDimension(R.dimen.spacer_base);
    }

    /* JADX INFO: renamed from: getCornerRadius, reason: from getter */
    public final float getRadius() {
        return this.radius;
    }

    public final void setCornerRadius(float f) {
        if (f == this.radius) {
            return;
        }
        this.radius = f;
        resetPath(getWidth(), getHeight());
        invalidate();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetPath(w, h);
    }

    private final void resetPath(int w, int h) {
        RectF rectF = new RectF(0.0f, 0.0f, w, h);
        this.path.reset();
        Path path = this.path;
        float f = this.radius;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        this.path.close();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int iSave = canvas.save();
        canvas.clipPath(this.path);
        super.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int iSave = canvas.save();
        canvas.clipPath(this.path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(iSave);
    }
}
