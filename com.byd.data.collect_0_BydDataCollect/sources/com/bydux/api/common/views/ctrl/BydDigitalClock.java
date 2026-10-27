package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bydux.api.common.views.ctrl.BydTimer;
import d.a.a.e;

/* JADX INFO: loaded from: classes.dex */
public class BydDigitalClock extends LinearLayout {
    private boolean A;
    private Drawable B;
    private Drawable C;
    private Drawable D;
    private Drawable E;
    private Drawable F;
    private Drawable G;
    private Drawable H;
    private Drawable I;
    private Drawable J;
    private Drawable K;
    private Drawable L;
    private Drawable M;
    private Drawable N;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3562b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3563c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BydTimer f3564d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f3565e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextView f3566f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f3567g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TextView f3568h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private TextView f3569i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    class a implements BydTimer.b {
        a() {
        }

        @Override // com.bydux.api.common.views.ctrl.BydTimer.b
        public void a(View view) {
            BydDigitalClock.this.e();
        }
    }

    public BydDigitalClock(Context context) {
        super(context);
        this.f3562b = getClass().getSimpleName();
        this.f3565e = null;
        this.f3566f = null;
        this.f3567g = null;
        this.f3568h = null;
        this.f3569i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = false;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        c(context);
    }

    private Drawable b(int i2) {
        switch (i2) {
            case 0:
                return this.B;
            case 1:
                return this.C;
            case 2:
                return this.D;
            case 3:
                return this.E;
            case 4:
                return this.F;
            case 5:
                return this.G;
            case 6:
                return this.H;
            case 7:
                return this.I;
            case 8:
                return this.J;
            case 9:
                return this.K;
            default:
                return null;
        }
    }

    private void c(Context context) {
        this.f3563c = context;
        this.f3565e = new TextView(context);
        this.f3566f = new TextView(this.f3563c);
        this.f3567g = new TextView(this.f3563c);
        this.f3568h = new TextView(this.f3563c);
        this.f3569i = new TextView(this.f3563c);
        this.j = new TextView(this.f3563c);
        this.k = new TextView(this.f3563c);
        this.l = new TextView(this.f3563c);
        this.m = new TextView(this.f3563c);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f3565e.setLayoutParams(layoutParams);
        this.f3566f.setLayoutParams(layoutParams);
        this.f3567g.setLayoutParams(layoutParams);
        this.f3568h.setLayoutParams(layoutParams);
        this.f3569i.setLayoutParams(layoutParams);
        this.j.setLayoutParams(layoutParams);
        this.k.setLayoutParams(layoutParams);
        this.l.setLayoutParams(layoutParams);
        this.m.setLayoutParams(layoutParams);
        addView(this.f3565e);
        addView(this.f3566f);
        addView(this.k);
        addView(this.f3567g);
        addView(this.f3568h);
        addView(this.l);
        addView(this.f3569i);
        addView(this.j);
        addView(this.m);
        setOrientation(0);
        BydTimer bydTimer = new BydTimer(context);
        this.f3564d = bydTimer;
        bydTimer.setBydTimerListener(new a());
    }

    private void d(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.f3563c.obtainStyledAttributes(attributeSet, e.BydDigitalClock);
            this.n = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num0, this.n);
            this.o = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num1, this.o);
            this.p = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num2, this.p);
            this.q = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num3, this.q);
            this.r = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num4, this.r);
            this.s = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num5, this.s);
            this.t = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num6, this.t);
            this.u = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num7, this.u);
            this.v = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num8, this.v);
            this.w = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_num9, this.w);
            this.x = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_am, this.x);
            this.y = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_pm, this.y);
            this.z = typedArrayObtainStyledAttributes.getResourceId(e.BydDigitalClock_byd_dgclock_colon, this.z);
            this.A = typedArrayObtainStyledAttributes.getBoolean(e.BydDigitalClock_byd_dgclock_show_second, this.A);
            typedArrayObtainStyledAttributes.recycle();
            int i2 = this.n;
            if (i2 > 0) {
                setDigitalClockNum0(this.f3563c.getDrawable(i2));
            }
            int i3 = this.o;
            if (i3 > 0) {
                setDigitalClockNum1(this.f3563c.getDrawable(i3));
            }
            int i4 = this.p;
            if (i4 > 0) {
                setDigitalClockNum2(this.f3563c.getDrawable(i4));
            }
            int i5 = this.q;
            if (i5 > 0) {
                setDigitalClockNum3(this.f3563c.getDrawable(i5));
            }
            int i6 = this.r;
            if (i6 > 0) {
                setDigitalClockNum4(this.f3563c.getDrawable(i6));
            }
            int i7 = this.s;
            if (i7 > 0) {
                setDigitalClockNum5(this.f3563c.getDrawable(i7));
            }
            int i8 = this.t;
            if (i8 > 0) {
                setDigitalClockNum6(this.f3563c.getDrawable(i8));
            }
            int i9 = this.u;
            if (i9 > 0) {
                setDigitalClockNum7(this.f3563c.getDrawable(i9));
            }
            int i10 = this.v;
            if (i10 > 0) {
                setDigitalClockNum8(this.f3563c.getDrawable(i10));
            }
            int i11 = this.w;
            if (i11 > 0) {
                setDigitalClockNum9(this.f3563c.getDrawable(i11));
            }
            int i12 = this.x;
            if (i12 > 0) {
                setDigitalClockAm(this.f3563c.getDrawable(i12));
            }
            int i13 = this.y;
            if (i13 > 0) {
                setDigitalClockPm(this.f3563c.getDrawable(i13));
            }
            int i14 = this.z;
            if (i14 > 0) {
                setDigitalClockColon(this.f3563c.getDrawable(i14));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        int iA = d.a.a.f.b.a.a.a(this.f3563c, 3);
        int iA2 = d.a.a.f.b.a.a.a(this.f3563c, 4);
        int iA3 = d.a.a.f.b.a.a.a(this.f3563c, 5);
        if (iA < 10) {
            this.f3565e.setBackground(b(0));
            this.f3566f.setBackground(b(iA));
        } else {
            this.f3565e.setBackground(b(iA / 10));
            this.f3566f.setBackground(b(iA % 10));
        }
        if (iA2 < 10) {
            this.f3567g.setBackground(b(0));
            this.f3568h.setBackground(b(iA2));
        } else {
            this.f3567g.setBackground(b(iA2 / 10));
            this.f3568h.setBackground(b(iA2 % 10));
        }
        if (iA3 < 10) {
            this.f3569i.setBackground(b(0));
            this.j.setBackground(b(iA3));
        } else {
            this.f3569i.setBackground(b(iA3 / 10));
            this.j.setBackground(b(iA3 % 10));
        }
        this.k.setBackground(this.N);
        this.l.setBackground(this.N);
        if (this.A) {
            this.k.setVisibility(0);
            this.l.setVisibility(0);
            this.f3569i.setVisibility(0);
            this.j.setVisibility(0);
        } else {
            if (this.k.getVisibility() != 0) {
                this.k.setVisibility(0);
            } else {
                this.k.setVisibility(4);
            }
            this.l.setVisibility(8);
            this.f3569i.setVisibility(8);
            this.j.setVisibility(8);
        }
        int iA4 = d.a.a.f.b.a.a.a(this.f3563c, 7);
        if (iA4 == 0) {
            this.m.setVisibility(8);
            return;
        }
        this.m.setVisibility(0);
        if (iA4 == 1) {
            this.m.setBackground(this.L);
        } else {
            this.m.setBackground(this.M);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        e();
        BydTimer bydTimer = this.f3564d;
        if (bydTimer != null) {
            bydTimer.e(500L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.f3564d;
        if (bydTimer != null) {
            bydTimer.g();
        }
    }

    public void setDigitalClockAm(Drawable drawable) {
        this.L = drawable;
    }

    public void setDigitalClockColon(Drawable drawable) {
        this.N = drawable;
    }

    public void setDigitalClockNum0(Drawable drawable) {
        this.B = drawable;
    }

    public void setDigitalClockNum1(Drawable drawable) {
        this.C = drawable;
    }

    public void setDigitalClockNum2(Drawable drawable) {
        this.D = drawable;
    }

    public void setDigitalClockNum3(Drawable drawable) {
        this.E = drawable;
    }

    public void setDigitalClockNum4(Drawable drawable) {
        this.F = drawable;
    }

    public void setDigitalClockNum5(Drawable drawable) {
        this.G = drawable;
    }

    public void setDigitalClockNum6(Drawable drawable) {
        this.H = drawable;
    }

    public void setDigitalClockNum7(Drawable drawable) {
        this.I = drawable;
    }

    public void setDigitalClockNum8(Drawable drawable) {
        this.J = drawable;
    }

    public void setDigitalClockNum9(Drawable drawable) {
        this.K = drawable;
    }

    public void setDigitalClockPm(Drawable drawable) {
        this.M = drawable;
    }

    public void setSecondShow(boolean z) {
        this.A = z;
    }

    public BydDigitalClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3562b = getClass().getSimpleName();
        this.f3565e = null;
        this.f3566f = null;
        this.f3567g = null;
        this.f3568h = null;
        this.f3569i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = false;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        c(context);
        d(attributeSet);
    }
}
