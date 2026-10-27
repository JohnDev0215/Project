package com.byd.music.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class ProgressBitmap {
    Bitmap bitmap;
    Canvas canvas;
    float height;
    float width;

    public ProgressBitmap(float f, float f2) {
        this.width = f;
        this.height = f2;
        this.bitmap = Bitmap.createBitmap((int) f, (int) f2, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(this.bitmap);
    }

    public void setWidthHeight(int i, int i2) {
        this.width = i;
        this.height = i2;
        this.bitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
    }

    public Bitmap generateBitmap(Context context, long j, long j2) {
        if (j2 == 0) {
            return null;
        }
        this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        int i = (int) (this.height * (1.0f - (j / j2)));
        Drawable drawable = context.getResources().getDrawable(R.drawable.bg_widget_progress_line);
        Drawable drawable2 = context.getResources().getDrawable(R.drawable.bg_widget_progress_shape);
        drawable2.setBounds(0, i, (int) this.width, (int) this.height);
        drawable2.draw(this.canvas);
        drawable.setBounds(0, i, (int) this.width, i + 2);
        drawable.draw(this.canvas);
        return this.bitmap;
    }
}
