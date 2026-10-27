package com.byd.bluetoothcall.view.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class FloatingViewMagnet extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient float f3456b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private transient float f3457c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private transient float f3458d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private transient float f3459e;
    private transient long f;
    protected transient b g;
    protected transient int h;
    private transient int i;
    private transient int j;
    private transient boolean k;
    private transient float l;

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f3460b;

        a(boolean z) {
            this.f3460b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            FloatingViewMagnet.this.o();
            FloatingViewMagnet floatingViewMagnet = FloatingViewMagnet.this;
            floatingViewMagnet.n(floatingViewMagnet.k, this.f3460b);
        }
    }

    public FloatingViewMagnet(Context context) {
        this(context, null);
    }

    private void e(MotionEvent motionEvent) {
        this.f3458d = getX();
        this.f3459e = getY();
        this.f3456b = motionEvent.getRawX();
        this.f3457c = motionEvent.getRawY();
        this.f = System.currentTimeMillis();
    }

    private void f() {
        this.l = 0.0f;
    }

    private void h() {
        this.g = new b(this, null);
        this.j = 56;
        setClickable(true);
    }

    private void k(boolean z) {
        if (z) {
            this.l = getY();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(float f, float f2) {
        setX(getX() + f);
        setY(getY() + f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(boolean z, boolean z2) {
        float f = z ? 13.0f : this.h - 13;
        float y = getY();
        if (!z2) {
            float f2 = this.l;
            if (f2 != 0.0f) {
                f();
                y = f2;
            }
        }
        this.g.b(f, Math.min(Math.max(0.0f, y), this.i - getHeight()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            this.h = viewGroup.getWidth() - getWidth();
            this.i = viewGroup.getHeight();
        }
    }

    private void p(MotionEvent motionEvent) {
        setX((this.f3458d + motionEvent.getRawX()) - this.f3456b);
        float rawY = (this.f3459e + motionEvent.getRawY()) - this.f3457c;
        int i = this.j;
        if (rawY < i) {
            rawY = i;
        }
        if (rawY > this.i - getHeight()) {
            rawY = this.i - getHeight();
        }
        setY(rawY);
    }

    protected void g() {
    }

    protected boolean i() {
        boolean z = getX() < ((float) (this.h / 2));
        this.k = z;
        return z;
    }

    protected boolean j() {
        return System.currentTimeMillis() - this.f < 150;
    }

    public void m() {
        n(i(), false);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getParent() != null) {
            boolean z = configuration.orientation == 2;
            k(z);
            ((ViewGroup) getParent()).post(new a(z));
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            e(motionEvent);
            o();
            this.g.c();
        } else if (action == 1) {
            f();
            m();
            if (j()) {
                g();
            }
        } else if (action == 2) {
            p(motionEvent);
        }
        return true;
    }

    private class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private transient Handler f3462b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private transient float f3463c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private transient float f3464d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private transient long f3465e;

        private b() {
            this.f3462b = new Handler(Looper.getMainLooper());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            this.f3462b.removeCallbacks(this);
        }

        void b(float f, float f2) {
            this.f3463c = f;
            this.f3464d = f2;
            this.f3465e = System.currentTimeMillis();
            this.f3462b.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FloatingViewMagnet.this.getRootView() == null || FloatingViewMagnet.this.getRootView().getParent() == null) {
                return;
            }
            float fMin = Math.min(1.0f, (System.currentTimeMillis() - this.f3465e) / 400.0f);
            FloatingViewMagnet.this.l((this.f3463c - FloatingViewMagnet.this.getX()) * fMin, (this.f3464d - FloatingViewMagnet.this.getY()) * fMin);
            if (fMin < 1.0f) {
                this.f3462b.post(this);
            }
        }

        /* synthetic */ b(FloatingViewMagnet floatingViewMagnet, a aVar) {
            this();
        }
    }

    public FloatingViewMagnet(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingViewMagnet(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = true;
        h();
    }
}
