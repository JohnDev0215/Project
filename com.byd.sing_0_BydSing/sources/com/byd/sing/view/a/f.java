package com.byd.sing.view.a;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.byd.sing.SingApplication;

/* JADX INFO: compiled from: FloatingFrame.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f extends g {
    private final WindowManager j;
    protected WindowManager.LayoutParams k;
    private View.OnTouchListener l;
    protected boolean m;
    private c n;
    private int o;

    /* JADX INFO: compiled from: FloatingFrame.java */
    private class b implements View.OnTouchListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3306b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3307c;

        private b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            f.this.v(motionEvent);
            f fVar = f.this;
            if (fVar.m && fVar.f3309b != null) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.f3306b = (int) motionEvent.getRawX();
                    this.f3307c = (int) motionEvent.getRawY();
                } else if (action == 2) {
                    int rawX = (int) motionEvent.getRawX();
                    int rawY = (int) motionEvent.getRawY();
                    int i = rawX - this.f3306b;
                    int i2 = rawY - this.f3307c;
                    this.f3306b = rawX;
                    this.f3307c = rawY;
                    int iC = com.byd.sing.c.c(f.this.f3309b);
                    if (i >= 0) {
                        int iH = com.byd.sing.c.h();
                        int measuredWidth = f.this.f3309b.getMeasuredWidth();
                        if (iC + measuredWidth + i > iH) {
                            i = (iH - iC) - measuredWidth;
                        }
                    } else if (iC + i < 0) {
                        i = -iC;
                    }
                    int iD = com.byd.sing.c.d(f.this.f3309b);
                    if (i2 >= 0) {
                        int iF = com.byd.sing.c.f();
                        int measuredHeight = f.this.f3309b.getMeasuredHeight();
                        int iE = com.byd.sing.c.e();
                        if (iD + measuredHeight + iE + i2 > iF) {
                            i2 = ((iF - iD) - measuredHeight) - iE;
                        }
                    } else if (iD + i2 < com.byd.sing.c.i()) {
                        i2 = com.byd.sing.c.i() - iD;
                    }
                    f.this.t(i, i2);
                    f fVar2 = f.this;
                    WindowManager.LayoutParams layoutParams = fVar2.k;
                    layoutParams.x += i;
                    layoutParams.y += i2;
                    fVar2.E();
                }
            }
            if (motionEvent.getAction() != 4) {
                return true;
            }
            f.this.w();
            return true;
        }
    }

    /* JADX INFO: compiled from: FloatingFrame.java */
    public interface c {
        void a();
    }

    public f() {
        this(0);
    }

    protected void A(int i, int i2) {
        super.p();
        if (this.f3309b != null) {
            B(i, i2, 8388659);
        }
    }

    public void B(int i, int i2, int i3) {
        try {
            this.k = new WindowManager.LayoutParams();
            boolean z = this.g;
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(z ? -1 : this.f3310c, z ? -1 : this.f3311d, 0, 0, -2);
            this.k = layoutParams;
            layoutParams.flags = 787240;
            layoutParams.type = z();
            WindowManager.LayoutParams layoutParams2 = this.k;
            layoutParams2.format = 1;
            layoutParams2.gravity = i3;
            layoutParams2.x = i;
            layoutParams2.y = i2;
            q();
            this.j.addView(this.f3309b, this.k);
            this.i = true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void C() {
        WindowManager.LayoutParams layoutParams;
        View view = this.f3309b;
        if (view == null || (layoutParams = this.k) == null) {
            return;
        }
        this.j.updateViewLayout(view, layoutParams);
    }

    public void D(int i, int i2) {
        WindowManager.LayoutParams layoutParams;
        View view = this.f3309b;
        if (view == null || (layoutParams = this.k) == null) {
            return;
        }
        layoutParams.x += i;
        layoutParams.y += i2;
        this.j.updateViewLayout(view, layoutParams);
    }

    public void E() {
        View view = this.f3309b;
        if (view != null) {
            this.j.updateViewLayout(view, this.k);
        }
    }

    @Override // com.byd.sing.view.a.g
    public void a() {
        View view = this.f3309b;
        if (view != null) {
            view.setOnTouchListener(null);
            try {
                if (this.i) {
                    c cVar = this.n;
                    if (cVar != null) {
                        cVar.a();
                    }
                    this.j.removeView(this.f3309b);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.f3309b = null;
        this.i = false;
        this.l = null;
        com.byd.sing.j.f().a(this);
    }

    @Override // com.byd.sing.view.a.g
    public void h() {
        super.h();
        if (this.f3309b != null) {
            b bVar = new b();
            this.l = bVar;
            this.f3309b.setOnTouchListener(bVar);
        }
    }

    @Override // com.byd.sing.view.a.g
    public void m() {
        View view = this.f3309b;
        if (view != null) {
            try {
                this.j.removeView(view);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        p();
    }

    @Override // com.byd.sing.view.a.g
    public void p() {
        super.p();
        if (this.f3309b != null) {
            B(0, 0, 17);
        }
    }

    protected abstract void q();

    protected int r() {
        return (com.byd.sing.c.h() - this.f3310c) - h.q;
    }

    protected int s() {
        return com.byd.sing.c.g() / 2;
    }

    protected abstract void t(int i, int i2);

    protected void u() {
        l();
        this.k.x = r();
        this.k.y = s();
        WindowManager.LayoutParams layoutParams = this.k;
        layoutParams.width = this.f3310c;
        layoutParams.height = this.f3311d;
        C();
    }

    protected abstract void v(MotionEvent motionEvent);

    protected abstract void w();

    public void x(c cVar) {
        this.n = cVar;
    }

    protected abstract boolean y();

    protected int z() {
        return 2002;
    }

    public f(int i) {
        super(i);
        this.o = -1;
        this.m = y();
        this.j = (WindowManager) SingApplication.a().getSystemService("window");
    }
}
