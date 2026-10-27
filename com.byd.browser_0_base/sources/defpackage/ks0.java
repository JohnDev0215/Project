package defpackage;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import defpackage.zu2;
import org.chromium.chrome.browser.layouts.EventFilter;

/* JADX INFO: compiled from: GestureEventFilter.java */
/* JADX INFO: loaded from: classes.dex */
public class ks0 extends EventFilter {
    public final int e;
    public final GestureDetector f;
    public final ls0 g;
    public final boolean h;
    public final int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;
    public final a n;
    public final Handler o;

    /* JADX INFO: compiled from: GestureEventFilter.java */
    public class a implements Runnable {
        public MotionEvent e;
        public boolean f;

        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            MotionEvent motionEvent = this.e;
            ks0 ks0Var = ks0.this;
            if (ks0Var.j) {
                ks0Var.k = true;
                float x = motionEvent.getX();
                float f = ks0Var.a;
                float y = motionEvent.getY() * f;
                ks0Var.g.r(x * f, y);
            }
            this.f = false;
        }
    }

    public ks0(Context context, zu2.b bVar) {
        super(context, false);
        this.j = true;
        this.n = new a();
        this.o = new Handler();
        this.i = ViewConfiguration.get(context).getScaledTouchSlop();
        this.e = ViewConfiguration.getLongPressTimeout();
        this.h = false;
        this.g = bVar;
        context.getResources();
        GestureDetector gestureDetector = new GestureDetector(context, new js0(this));
        this.f = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
    }

    @Override // org.chromium.chrome.browser.layouts.EventFilter
    public void c(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        boolean z = this.h;
        if (!z) {
            int pointerCount = motionEvent.getPointerCount();
            a aVar = this.n;
            Handler handler = this.o;
            if (pointerCount > 1) {
                if (aVar.f) {
                    handler.removeCallbacks(aVar);
                    aVar.f = false;
                }
            } else if (actionMasked == 0) {
                if (aVar.f) {
                    handler.removeCallbacks(aVar);
                    aVar.f = false;
                }
                MotionEvent motionEvent2 = aVar.e;
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                aVar.e = MotionEvent.obtain(motionEvent);
                aVar.f = true;
                handler.postDelayed(aVar, this.e);
            } else if (actionMasked == 1 || actionMasked == 3) {
                handler.removeCallbacks(aVar);
                aVar.f = false;
            } else if (aVar.f) {
                MotionEvent motionEvent3 = aVar.e;
                float x = motionEvent3.getX() - motionEvent.getX();
                float y = motionEvent3.getY() - motionEvent.getY();
                float f = (y * y) + (x * x);
                int i = this.i;
                if (f > i * i) {
                    handler.removeCallbacks(aVar);
                    aVar.f = false;
                }
            }
        }
        int pointerCount2 = motionEvent.getPointerCount();
        GestureDetector gestureDetector = this.f;
        ls0 ls0Var = this.g;
        if (pointerCount2 > 1) {
            motionEvent.getX(0);
            motionEvent.getY(0);
            motionEvent.getX(1);
            motionEvent.getY(1);
            ls0Var.u();
            gestureDetector.setIsLongpressEnabled(false);
            this.j = false;
        } else {
            gestureDetector.setIsLongpressEnabled(z);
            this.j = true;
        }
        gestureDetector.onTouchEvent(motionEvent);
        if (actionMasked == 1 || actionMasked == 3) {
            ls0Var.e();
        }
    }
}
