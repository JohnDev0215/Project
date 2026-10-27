package com.byd.mvp.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class FrameAnimationView extends SurfaceView implements SurfaceHolder.Callback {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SurfaceHolder f2881d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile boolean f2882e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2883f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List<Integer> f2884g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2885h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Paint f2886i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public volatile ConcurrentHashMap<Integer, Bitmap> f2887j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public volatile boolean f2888k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile boolean f2889l;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (FrameAnimationView.this.f2887j != null) {
                Iterator it = FrameAnimationView.this.f2887j.values().iterator();
                while (it.hasNext()) {
                    ((Bitmap) it.next()).recycle();
                }
                FrameAnimationView.this.f2887j.clear();
            }
            x3.a.a("FrameAnimationView", "--releaseBitmaps--ThreadName: " + Thread.currentThread().getName() + ", excuteTime: " + new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(jCurrentTimeMillis)).longValue());
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            x3.a.a("FrameAnimationView", "--runOnThread--START.");
            if (FrameAnimationView.this.f2887j == null) {
                FrameAnimationView.this.f2887j = new ConcurrentHashMap();
            }
            while (FrameAnimationView.this.f2882e) {
                boolean zH = FrameAnimationView.this.h();
                int size = FrameAnimationView.this.f2884g != null ? FrameAnimationView.this.f2884g.size() : 0;
                if (size <= 0 || !FrameAnimationView.this.f2882e) {
                    break;
                }
                FrameAnimationView frameAnimationView = FrameAnimationView.this;
                frameAnimationView.f2885h = (frameAnimationView.f2885h + 1) % size;
                try {
                    Thread.sleep(zH ? 40L : 1L);
                } catch (InterruptedException unused) {
                }
            }
            x3.a.a("FrameAnimationView", "--runOnThread--END.");
        }
    }

    public FrameAnimationView(Context context) {
        super(context);
        this.f2882e = false;
        this.f2888k = false;
        this.f2889l = false;
        i(context);
    }

    public final boolean h() throws Throwable {
        int iIntValue;
        Integer num = new Integer(this.f2885h);
        List<Integer> list = this.f2884g;
        boolean z7 = true;
        if (list == null || list.size() <= this.f2885h) {
            iIntValue = 0;
        } else {
            Integer num2 = this.f2884g.get(num.intValue());
            iIntValue = num2 != null ? num2.intValue() : 0;
            if (this.f2885h == 0) {
                x3.a.a("FrameAnimationView", "--drawCanvas--frames.size: " + this.f2884g.size());
            }
        }
        Canvas canvas = null;
        try {
            Canvas canvasLockCanvas = this.f2881d.lockCanvas();
            if (canvasLockCanvas != null) {
                try {
                    canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    Bitmap bitmapDecodeResource = this.f2887j.get(num);
                    if (bitmapDecodeResource == null && iIntValue != 0) {
                        bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), iIntValue);
                        this.f2887j.put(num, bitmapDecodeResource);
                        z7 = false;
                    }
                    if (bitmapDecodeResource != null) {
                        canvasLockCanvas.drawBitmap(bitmapDecodeResource, 0.0f, 0.0f, (Paint) null);
                    }
                } catch (Throwable th) {
                    th = th;
                    canvas = canvasLockCanvas;
                    if (canvas != null) {
                        this.f2881d.unlockCanvasAndPost(canvas);
                    }
                    throw th;
                }
            }
            if (canvasLockCanvas != null) {
                this.f2881d.unlockCanvasAndPost(canvasLockCanvas);
            }
            return z7;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final void i(Context context) {
        SurfaceHolder holder = getHolder();
        this.f2881d = holder;
        holder.addCallback(this);
        this.f2884g = new ArrayList();
        this.f2885h = 0;
        this.f2886i = new Paint();
        setZOrderOnTop(true);
        this.f2881d.setFormat(-3);
    }

    public void j() {
        x3.a.a("FrameAnimationView", "--release--released: " + this.f2889l);
        if (this.f2889l) {
            return;
        }
        n();
        List<Integer> list = this.f2884g;
        if (list != null) {
            list.clear();
        }
        k();
        this.f2889l = true;
    }

    public final synchronized void k() {
        m4.b.b(new a());
    }

    public final void l() {
        x3.a.a("FrameAnimationView", "--runOnThread--surfaceCreated: " + this.f2888k);
        if (this.f2888k) {
            this.f2882e = true;
            m4.b.b(new b());
        }
    }

    public void m() {
        x3.a.a("FrameAnimationView", "--start--surfaceCreated: " + this.f2888k);
        this.f2883f = true;
        if (this.f2882e) {
            return;
        }
        l();
    }

    public void n() {
        x3.a.a("FrameAnimationView", "--stop--");
        this.f2883f = false;
        this.f2882e = false;
        this.f2885h = 0;
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        x3.a.a("FrameAnimationView", "--onDetachedFromWindow--");
        j();
    }

    public void setFrames(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.f2884g.clear();
        this.f2884g.addAll(list);
        x3.a.a("FrameAnimationView", "--setFrames--frames.size:" + this.f2884g.size());
        setVisibility(0);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i8, int i9, int i10) {
        x3.a.a("FrameAnimationView", "--surfaceChanged--");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        x3.a.a("FrameAnimationView", "--surfaceCreated--");
        this.f2888k = true;
        m();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        x3.a.a("FrameAnimationView", "--surfaceDestroyed--");
        this.f2888k = false;
        n();
    }

    public FrameAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2882e = false;
        this.f2888k = false;
        this.f2889l = false;
        i(context);
    }

    public FrameAnimationView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2882e = false;
        this.f2888k = false;
        this.f2889l = false;
        i(context);
    }
}
