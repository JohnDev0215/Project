package com.byd.music.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.byd.music.common.AppExecutors;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes5.dex */
public class ReflectImageView extends ShapeableImageView {
    Bitmap bitmap;
    Handler handler;
    ReflectImageView imageview;
    private boolean mReflectionMode;

    public ReflectImageView(Context context) {
        this(context, null);
    }

    public ReflectImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ReflectImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mReflectionMode = true;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.byd.music.view.ReflectImageView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 39321 && ReflectImageView.this.imageview.isAttachedToWindow()) {
                    ReflectImageView.this.imageview.setImageBitmap(ReflectImageView.this.bitmap);
                }
            }
        };
        this.imageview = this;
    }

    public void setReflectionMode(boolean z) {
        this.mReflectionMode = z;
    }

    public boolean getReflectionMode() {
        return this.mReflectionMode;
    }

    public Bitmap createReflectedImage(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, 400.0f, 553648127, 16777215, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, 0.0f, width, bitmapCreateBitmap2.getHeight(), paint);
        return bitmapCreateBitmap2;
    }

    public void startReflect(final Bitmap bitmap) {
        setScaleType(ImageView.ScaleType.CENTER_CROP);
        AppExecutors.getInstance().schedule().execute(new Runnable() { // from class: com.byd.music.view.ReflectImageView.2
            @Override // java.lang.Runnable
            public void run() {
                ReflectImageView reflectImageView = ReflectImageView.this;
                reflectImageView.bitmap = reflectImageView.createReflectedImage(bitmap);
                ReflectImageView.this.handler.sendEmptyMessage(39321);
            }
        });
    }

    @Override // com.google.android.material.imageview.ShapeableImageView, android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.handler.removeCallbacksAndMessages(null);
    }
}
