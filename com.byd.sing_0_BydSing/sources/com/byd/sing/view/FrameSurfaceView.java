package com.byd.sing.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FrameSurfaceView extends BaseSurfaceView {
    private List<Integer> g;
    private Bitmap h;
    private int i;
    private Paint j;
    private BitmapFactory.Options k;
    private Rect l;
    private Rect m;
    private int n;
    private int o;
    private a p;

    public interface a {
        void a();
    }

    public FrameSurfaceView(Context context) {
        super(context);
        this.g = new ArrayList();
        this.i = Integer.MAX_VALUE;
        this.j = new Paint();
        this.k = new BitmapFactory.Options();
        this.m = new Rect();
    }

    private void m(Canvas canvas) {
        this.j.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(this.j);
        this.j.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    }

    private void n(Canvas canvas) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), this.g.get(this.i).intValue(), this.k);
        this.h = bitmapDecodeResource;
        canvas.drawBitmap(bitmapDecodeResource, (Rect) null, this.m, this.j);
        this.i++;
    }

    private void o(Integer num) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), num.intValue(), options);
        int i = options.outWidth;
        this.n = i;
        this.o = options.outHeight;
        com.byd.sing.a.d("options", Integer.valueOf(i), Integer.valueOf(this.o));
        this.l = new Rect(0, 0, this.n, this.o);
        requestLayout();
    }

    private boolean p() {
        return this.i >= this.g.size();
    }

    private boolean q() {
        return this.i != Integer.MAX_VALUE;
    }

    private void r() {
        t();
        a aVar = this.p;
        if (aVar != null) {
            aVar.a();
        }
        f();
    }

    private void s() {
        Bitmap bitmap = this.h;
        if (bitmap != null) {
            bitmap.recycle();
            this.h = null;
        }
    }

    private void t() {
        this.i = Integer.MAX_VALUE;
    }

    @Override // com.byd.sing.view.BaseSurfaceView
    protected void h(Canvas canvas) {
        m(canvas);
        if (q()) {
            if (p()) {
                r();
            } else {
                n(canvas);
            }
        }
    }

    @Override // com.byd.sing.view.BaseSurfaceView
    protected void i() {
        s();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        com.byd.sing.a.a("width = " + getWidth());
        this.m.set(0, 0, getWidth(), getHeight());
    }

    public void setAnimationChangeListener(a aVar) {
        this.p = aVar;
    }

    public void setBitmaps(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.g = list;
        o(list.get(0));
    }

    public void setDuration(int i) {
        setFrameDuration(i / this.g.size());
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public FrameSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new ArrayList();
        this.i = Integer.MAX_VALUE;
        this.j = new Paint();
        this.k = new BitmapFactory.Options();
        this.m = new Rect();
    }

    public FrameSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new ArrayList();
        this.i = Integer.MAX_VALUE;
        this.j = new Paint();
        this.k = new BitmapFactory.Options();
        this.m = new Rect();
    }
}
