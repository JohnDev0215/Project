package com.byd.base.view.scrollRelativeLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import b.b.a.j.e.a;
import b.b.a.j.e.c.b;
import b.b.a.j.e.c.c;
import b.b.a.j.e.c.d;
import com.byd.base.R$dimen;
import com.byd.base.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class BydScrollRelativeLayout extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f2517a = new a(Looper.getMainLooper());
    public boolean A;
    public boolean B;
    public boolean D;
    public boolean E;
    public boolean F;
    public c G;
    public int H;
    public int I;
    public View.OnClickListener J;
    public boolean K;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f2518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2519c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f2520d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2521e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2522f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public float l;
    public float m;
    public float n;
    public float o;
    public float p;
    public float q;
    public float r;
    public float s;
    public float t;
    public int u;
    public PointF v;
    public PointF w;
    public PointF x;
    public PointF y;
    public PointF z;

    public BydScrollRelativeLayout(Context context) {
        this(context, null);
    }

    private float getTargetHeight() {
        return this.q;
    }

    private float getTargetWidth() {
        return this.q;
    }

    public final void a(int i) {
        if (i == 0) {
            j(getId());
            return;
        }
        if (i == 1) {
            f(getId());
            return;
        }
        if (i == 2) {
            h(getId());
        } else if (i != 3) {
            g(getId());
        } else {
            i(getId());
        }
    }

    public final boolean b(PointF pointF, PointF pointF2) {
        float f2 = this.r - this.q;
        float f3 = pointF.x;
        float f4 = pointF2.x;
        float f5 = pointF.y;
        float f6 = pointF2.y;
        boolean z = ((f3 - f4) * (f3 - f4)) + ((f5 - f6) * (f5 - f6)) > f2 * f2;
        b.b.a.h.a.a(this, " is out area : " + z);
        return z;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BydScrollRelativeLayout);
        this.g = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_centerX, -1.0f);
        this.h = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_centerY, -1.0f);
        this.i = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointUpX, -1.0f);
        this.j = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointUpY, -1.0f);
        this.k = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointDownX, -1.0f);
        this.l = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointDownY, -1.0f);
        this.m = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointLeftX, -1.0f);
        this.n = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointLeftY, -1.0f);
        this.o = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointRightX, -1.0f);
        this.p = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointRightY, -1.0f);
        this.q = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_targetWidth, -1.0f);
        this.r = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_turnAreaWidth, -1.0f);
        this.u = typedArrayObtainStyledAttributes.getInt(R$styleable.BydScrollRelativeLayout_turnType, 1);
        this.f2522f = typedArrayObtainStyledAttributes.getInt(R$styleable.BydScrollRelativeLayout_turnDirection, 3);
        this.H = typedArrayObtainStyledAttributes.getInt(R$styleable.BydScrollRelativeLayout_calBy, 1);
        this.v = new PointF(this.i, this.j);
        this.w = new PointF(this.g, this.h);
        this.x = new PointF(this.k, this.l);
        this.y = new PointF(this.m, this.n);
        this.z = new PointF(this.o, this.p);
        this.f2519c = this.g;
        this.f2520d = this.h;
        typedArrayObtainStyledAttributes.recycle();
        if (this.u == 3) {
            b bVar = new b();
            this.G = bVar;
            int i = this.H;
            bVar.b(i == 2 ? this.v : this.y, this.w, i == 2 ? this.x : this.z);
            setScrollDirection(this.H == 2 ? 1 : 2);
            this.G.a(this.H == 2 ? 1 : 0);
        }
        if (this.u == 1) {
            d dVar = new d();
            this.G = dVar;
            dVar.d(this.y, this.z, this.w, this.v, this.x);
        }
        if (this.u == 2) {
            b.b.a.j.e.c.a aVar = new b.b.a.j.e.c.a();
            this.G = aVar;
            aVar.b(this.v, this.w, this.x);
            setScrollDirection(1);
        }
        this.I = (int) context.getResources().getDimension(R$dimen.dp_21);
    }

    public final boolean d(float f2, float f3) {
        float fMin;
        float fMax;
        float fMin2;
        float fMax2;
        int i;
        if (this.f2521e == 1) {
            fMin = Math.min(this.i, this.k) - this.I;
            fMax = Math.max(this.i, this.k) + this.I;
            fMin2 = Math.min(this.j, this.l) - this.I;
            fMax2 = Math.max(this.j, this.l);
            i = this.I;
        } else {
            fMin = Math.min(this.m, this.o) - this.I;
            fMax = Math.max(this.m, this.o) + this.I;
            fMin2 = Math.min(this.n, this.p) - this.I;
            fMax2 = Math.max(this.n, this.p);
            i = this.I;
        }
        return f2 >= fMin && f2 <= fMax && f3 >= fMin2 && f3 <= fMax2 + ((float) i);
    }

    public final boolean e(int i) {
        if (i != -1) {
            if (i == 0 || i == 1) {
                int i2 = this.f2522f;
                if (i2 != 1 && i2 != 3 && i2 != 4) {
                    return false;
                }
            } else {
                if (i != 2 && i != 3) {
                    return false;
                }
                int i3 = this.f2522f;
                if (i3 != 2 && i3 != 3 && i3 != 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void f(int i) {
        if (this.B) {
            return;
        }
        this.F = true;
        this.B = true;
        f2517a.g(i);
    }

    public final void g(int i) {
        if (this.F) {
            f2517a.h(i);
            this.F = false;
            this.A = false;
            this.B = false;
            this.D = false;
            this.E = false;
        }
    }

    public final void h(int i) {
        if (this.D) {
            return;
        }
        this.F = true;
        this.D = true;
        f2517a.i(i);
    }

    public final void i(int i) {
        if (this.E) {
            return;
        }
        this.F = true;
        this.E = true;
        f2517a.j(i);
    }

    public final void j(int i) {
        if (this.A) {
            return;
        }
        this.A = true;
        this.F = true;
        f2517a.k(i);
    }

    public final void k(PointF pointF, PointF pointF2, boolean z, int i) {
        if (e(i)) {
            boolean zB = b(pointF, pointF2);
            if (!zB && !z) {
                a(i);
            } else if (zB && z) {
                a(-1);
            }
        }
    }

    public final boolean l(MotionEvent motionEvent) {
        return motionEvent.getX() >= this.f2519c - (getTargetWidth() / 2.0f) && motionEvent.getX() <= this.f2519c + (getTargetWidth() / 2.0f) && motionEvent.getY() >= this.f2520d - (getTargetHeight() / 2.0f) && motionEvent.getY() <= this.f2520d + (getTargetHeight() / 2.0f);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f2518b != null) {
            return;
        }
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null && childAt.getTag() != null && childAt.getTag().equals("target")) {
                this.f2518b = childAt;
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        View.OnClickListener onClickListener;
        float f2;
        int action = motionEvent.getAction();
        if (action == 0) {
            b.b.a.h.a.a(this, " onTouchEvent : MotionEvent.ACTION_DOWN ");
            this.t = motionEvent.getY();
            this.s = motionEvent.getX();
            this.f2521e = -1;
            if (!l(motionEvent)) {
                b.b.a.h.a.a(this, "onTouchEvent : 不在控件范围内 ");
                return false;
            }
            getParent().requestDisallowInterceptTouchEvent(true);
            this.K = false;
        } else if (action == 1) {
            b.b.a.h.a.a(this, " MotionEvent.ACTION_UP ");
            if (l(motionEvent) && (onClickListener = this.J) != null && !this.K) {
                onClickListener.onClick(this.f2518b);
                this.K = false;
            }
            this.f2518b.layout((int) (((-getTargetWidth()) / 2.0f) + this.g), (int) (((-getTargetHeight()) / 2.0f) + this.h), (int) ((getTargetWidth() / 2.0f) + this.g), (int) ((getTargetHeight() / 2.0f) + this.h));
            this.f2519c = this.g;
            this.f2520d = this.h;
            this.F = true;
            a(-1);
        } else if (action == 2) {
            b.b.a.h.a.a(this, " MotionEvent.ACTION_MOVE  event.getX() : " + motionEvent.getX() + " event.getY() : " + motionEvent.getX() + " event.getY() : " + motionEvent.getY() + " height : " + getHeight());
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int i = this.u;
            if (i == 2 || i == 3) {
                this.f2521e = this.H == 2 ? 1 : 0;
            } else {
                if (this.f2522f == 2 && Math.abs(y - this.t) > 20.0f) {
                    return true;
                }
                if (this.f2522f == 1 && Math.abs(x - this.s) > 20.0f) {
                    return true;
                }
                if (Math.abs(x - this.g) < this.q / 2.0f && Math.abs(x - this.h) < this.q / 2.0f) {
                    this.f2521e = -1;
                }
                if (Math.abs(y - this.t) > Math.abs(x - this.s)) {
                    if (this.f2521e == -1) {
                        this.f2521e = 1;
                    }
                } else if (this.f2521e == -1) {
                    this.f2521e = 0;
                }
            }
            if (!d(x, y)) {
                return true;
            }
            c cVar = this.G;
            int i2 = this.f2521e;
            if (i2 == 1) {
                float f3 = this.j;
                f2 = (y - f3) / (this.l - f3);
            } else {
                float f4 = this.m;
                f2 = (x - f4) / (this.o - f4);
            }
            PointF pointFC = cVar.c(i2, f2);
            this.f2519c = pointFC.x;
            this.f2520d = pointFC.y;
            k(pointFC, this.v, this.A, 0);
            k(pointFC, this.x, this.B, 1);
            k(pointFC, this.y, this.D, 2);
            k(pointFC, this.z, this.E, 3);
            this.f2518b.layout((int) (this.f2519c - (getTargetWidth() / 2.0f)), (int) (this.f2520d - (getTargetHeight() / 2.0f)), (int) (this.f2519c + (getTargetWidth() / 2.0f)), (int) (this.f2520d + (getTargetHeight() / 2.0f)));
            b.b.a.h.a.a(this, "MotionEvent.ACTION_MOVE  l : " + ((int) (this.f2519c - (getTargetWidth() / 2.0f))) + " t ： " + ((int) (this.f2520d - (getTargetHeight() / 2.0f))) + " r ： " + ((int) (this.f2519c + (getTargetWidth() / 2.0f))) + " b : " + ((int) (this.f2520d + (getTargetHeight() / 2.0f))) + "  centerX : " + this.f2519c + "  centerY  : " + this.f2520d);
            this.K = true;
        }
        return true;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.J = onClickListener;
    }

    public void setScrollDirection(int i) {
        this.f2522f = i;
    }

    public void setScrollerListener(b.b.a.j.e.b bVar) {
        f2517a.l(bVar);
    }

    public BydScrollRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydScrollRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2522f = 1;
        this.A = false;
        this.B = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.K = false;
        c(context, attributeSet);
    }
}
