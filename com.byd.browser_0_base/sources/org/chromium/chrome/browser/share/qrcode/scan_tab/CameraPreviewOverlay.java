package org.chromium.chrome.browser.share.qrcode.scan_tab;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class CameraPreviewOverlay extends View {
    public final int e;
    public final int f;
    public final Paint g;
    public final Paint h;
    public final int i;
    public final StaticLayout j;
    public Rect k;

    public CameraPreviewOverlay(Context context) {
        super(context);
        this.e = getResources().getDimensionPixelSize(R$dimen.overlay_rect_size);
        this.f = getResources().getDimensionPixelSize(R$dimen.overlay_corner_size);
        this.i = getResources().getDimensionPixelSize(R$dimen.overlay_text_top_padding);
        a();
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(getResources().getColor(R$color.overlay_mask));
        Paint paint2 = new Paint();
        this.g = paint2;
        paint2.setColor(getResources().getColor(R.color.white));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(getResources().getDimensionPixelSize(R$dimen.overlay_corner_width));
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(getResources().getColor(R.color.white));
        textPaint.setTextSize(getResources().getDimensionPixelSize(R$dimen.text_size_large));
        this.j = new StaticLayout(getResources().getString(R$string.qr_code_camera_framing_rect_description), textPaint, this.k.width(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true);
    }

    public final void a() {
        int width = getWidth();
        int height = getHeight();
        int i = this.e;
        this.k = new Rect((width - i) / 2, (height - i) / 2, ((width - i) / 2) + i, ((height - i) / 2) + i);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float f = width;
        float f2 = this.k.top;
        Paint paint = this.h;
        canvas.drawRect(0.0f, 0.0f, f, f2, paint);
        Rect rect = this.k;
        canvas.drawRect(0.0f, rect.top, rect.left, rect.bottom + 1, paint);
        Rect rect2 = this.k;
        canvas.drawRect(rect2.right + 1, rect2.top, f, rect2.bottom + 1, paint);
        canvas.drawRect(0.0f, this.k.bottom + 1, f, height, paint);
        Path path = new Path();
        Rect rect3 = this.k;
        float f3 = rect3.left;
        int i = rect3.top;
        int i2 = this.f;
        path.moveTo(f3, i + i2);
        Rect rect4 = this.k;
        path.lineTo(rect4.left, rect4.top);
        Rect rect5 = this.k;
        path.lineTo(rect5.left + i2, rect5.top);
        Paint paint2 = this.g;
        canvas.drawPath(path, paint2);
        Rect rect6 = this.k;
        path.moveTo(rect6.right, rect6.top + i2);
        Rect rect7 = this.k;
        path.lineTo(rect7.right, rect7.top);
        Rect rect8 = this.k;
        path.lineTo(rect8.right - i2, rect8.top);
        canvas.drawPath(path, paint2);
        Rect rect9 = this.k;
        path.moveTo(rect9.right, rect9.bottom - i2);
        Rect rect10 = this.k;
        path.lineTo(rect10.right, rect10.bottom);
        Rect rect11 = this.k;
        path.lineTo(rect11.right - i2, rect11.bottom);
        canvas.drawPath(path, paint2);
        Rect rect12 = this.k;
        path.moveTo(rect12.left, rect12.bottom - i2);
        Rect rect13 = this.k;
        path.lineTo(rect13.left, rect13.bottom);
        Rect rect14 = this.k;
        path.lineTo(rect14.left + i2, rect14.bottom);
        canvas.drawPath(path, paint2);
        canvas.save();
        Rect rect15 = this.k;
        canvas.translate(rect15.left, rect15.bottom + this.i);
        this.j.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        a();
    }
}
