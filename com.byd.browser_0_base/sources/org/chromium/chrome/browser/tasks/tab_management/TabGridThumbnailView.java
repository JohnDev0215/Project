package org.chromium.chrome.browser.tasks.tab_management;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.components.browser_ui.widget.RoundedCornerImageView;

/* JADX INFO: loaded from: classes2.dex */
public class TabGridThumbnailView extends RoundedCornerImageView {
    public final Context p;
    public final Paint q;
    public final int r;
    public int s;
    public int t;

    public TabGridThumbnailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.p = context;
        Paint paint = new Paint();
        this.q = paint;
        paint.setColor(context.getColor(R$color.tab_thumbnail_tin_color));
        this.r = (int) context.getResources().getDimension(R$dimen.tab_grid_card_thumbnail_corner_radius_bottom);
    }

    @Override // org.chromium.components.browser_ui.widget.RoundedCornerImageView, android.widget.ImageView, android.view.View
    @SuppressLint({"DrawAllocation"})
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0.0f, 0.0f, this.t, this.s);
        float f = this.r;
        canvas.drawRoundRect(rectF, f, f, this.q);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.t = getMeasuredWidth();
        int measuredHeight = (int) ((((double) getMeasuredHeight()) * 1.0d) - ((double) this.p.getResources().getDimension(R$dimen.tab_grid_card_header_height)));
        this.s = measuredHeight;
        setMeasuredDimension(this.t, measuredHeight);
    }
}
