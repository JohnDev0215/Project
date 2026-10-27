package com.amazon.music.explore.views.swipeablePages.pages;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.amazon.music.explore.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageXrayFullScreenProgressBar.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005H\u0014J\u0016\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/explore/views/swipeablePages/pages/StageXrayFullScreenProgressBar;", "Landroid/view/View;", "context", "Landroid/content/Context;", "totalSeekBarHeight", "", "currentSeekBarHeight", "(Landroid/content/Context;II)V", "getCurrentSeekBarHeight", "()I", "setCurrentSeekBarHeight", "(I)V", "getTotalSeekBarHeight", "setTotalSeekBarHeight", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "setCurrentHeightFromProgress", NotificationCompat.CATEGORY_PROGRESS, "", "totalContainerDuration", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StageXrayFullScreenProgressBar extends View {
    private int currentSeekBarHeight;
    private int totalSeekBarHeight;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StageXrayFullScreenProgressBar(Context context, int i, int i2) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.totalSeekBarHeight = i;
        this.currentSeekBarHeight = i2;
    }

    public /* synthetic */ StageXrayFullScreenProgressBar(Context context, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2);
    }

    public final int getCurrentSeekBarHeight() {
        return this.currentSeekBarHeight;
    }

    public final int getTotalSeekBarHeight() {
        return this.totalSeekBarHeight;
    }

    public final void setCurrentSeekBarHeight(int i) {
        this.currentSeekBarHeight = i;
    }

    public final void setTotalSeekBarHeight(int i) {
        this.totalSeekBarHeight = i;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float dimension = getResources().getDimension(R.dimen.stage_x_ray_full_screen_progress_bar_width);
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        paint2.setColor(ContextCompat.getColor(getContext(), R.color.white));
        paint.setColor(ContextCompat.getColor(getContext(), R.color.secondary_glass_3));
        if (canvas != null) {
            canvas.drawRect(0.0f, 0.0f, dimension, this.totalSeekBarHeight, paint);
        }
        if (canvas == null) {
            return;
        }
        canvas.drawRect(0.0f, 0.0f, dimension, this.currentSeekBarHeight, paint2);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec((int) getResources().getDimension(R.dimen.stage_x_ray_full_screen_progress_bar_width), 1073741824), View.MeasureSpec.makeMeasureSpec(this.totalSeekBarHeight, 1073741824));
    }

    public final void setCurrentHeightFromProgress(long progress, long totalContainerDuration) {
        this.currentSeekBarHeight = (int) ((progress / totalContainerDuration) * this.totalSeekBarHeight);
        invalidate();
    }
}
