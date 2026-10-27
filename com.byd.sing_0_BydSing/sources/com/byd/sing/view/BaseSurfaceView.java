package com.byd.sing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HandlerThread f3287b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f3288c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3289d;
    private Canvas e;
    private boolean f;

    private class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (BaseSurfaceView.this.f) {
                    try {
                        BaseSurfaceView baseSurfaceView = BaseSurfaceView.this;
                        baseSurfaceView.e = baseSurfaceView.getHolder().lockCanvas();
                        BaseSurfaceView baseSurfaceView2 = BaseSurfaceView.this;
                        baseSurfaceView2.h(baseSurfaceView2.e);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    BaseSurfaceView.this.getHolder().unlockCanvasAndPost(BaseSurfaceView.this.e);
                    BaseSurfaceView.this.i();
                    if (BaseSurfaceView.this.f3288c != null) {
                        BaseSurfaceView.this.f3288c.postDelayed(this, BaseSurfaceView.this.f3289d);
                    }
                }
            } catch (Throwable th) {
                BaseSurfaceView.this.getHolder().unlockCanvasAndPost(BaseSurfaceView.this.e);
                BaseSurfaceView.this.i();
                throw th;
            }
        }
    }

    public BaseSurfaceView(Context context) {
        this(context, null);
    }

    private void g() {
        getHolder().addCallback(this);
        j();
    }

    private void j() {
        getHolder().setFormat(-3);
        setZOrderOnTop(true);
    }

    private void k() {
        HandlerThread handlerThread = new HandlerThread("SurfaceViewThread");
        this.f3287b = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.f3287b.getLooper());
        this.f3288c = handler;
        handler.post(new b());
    }

    private void l() {
        this.f3287b.quit();
        this.f3288c = null;
    }

    public void f() {
        this.f3287b.quit();
        this.f3288c = null;
        this.f = false;
    }

    protected abstract void h(Canvas canvas);

    protected abstract void i();

    public void setFrameDuration(int i) {
        this.f3289d = i;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.f = true;
        surfaceHolder.setSizeFromLayout();
        k();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        l();
        this.f = false;
    }

    public BaseSurfaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3289d = 50;
        g();
    }
}
