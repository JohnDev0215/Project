package com.byd.ota.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class TextProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3141c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f3142d;

    public TextProgressBar(Context context) {
        super(context);
        a();
    }

    public final void a() {
        this.f3141c = "0/0";
        Paint paint = new Paint();
        this.f3142d = paint;
        paint.setAntiAlias(true);
        this.f3142d.setColor(-65536);
    }

    public final void b(long j, long j2) {
        this.f3141c = j + "/" + j2;
    }

    public void c(long j, long j2) {
        setProgress((int) j);
        b(j, j2);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        Paint paint = this.f3142d;
        String str = this.f3141c;
        paint.getTextBounds(str, 0, str.length(), rect);
        canvas.drawText(this.f3141c, (getWidth() / 2) - rect.centerX(), (getHeight() / 2) - rect.centerY(), this.f3142d);
    }

    public TextProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public TextProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }
}
