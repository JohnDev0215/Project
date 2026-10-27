package com.byd.base.view.scrollRelativeLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import b.a.b.e.e.a;
import b.a.b.e.e.c.b;
import b.a.b.e.e.c.c;
import b.a.b.e.e.c.d;
import com.byd.base.R$dimen;
import com.byd.base.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class BydScrollRelativeLayout extends RelativeLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static a f591b = new a(Looper.getMainLooper());
    public PointF A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public c G;
    public int H;
    public int I;
    public View.OnClickListener J;
    public boolean K;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f592c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f593d;
    public float e;
    public int f;
    public int g;
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
    public float u;
    public int v;
    public PointF w;
    public PointF x;
    public PointF y;
    public PointF z;

    public BydScrollRelativeLayout(Context context) {
        this(context, null);
    }

    private float getTargetHeight() {
        return this.r;
    }

    private float getTargetWidth() {
        return this.r;
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
        float f = this.s - this.r;
        float f2 = pointF.x;
        float f3 = pointF2.x;
        float f4 = pointF.y;
        float f5 = pointF2.y;
        boolean z = ((f2 - f3) * (f2 - f3)) + ((f4 - f5) * (f4 - f5)) > f * f;
        b.a.b.c.a.a(this, " is out area : " + z);
        return z;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BydScrollRelativeLayout);
        this.h = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_centerX, -1.0f);
        this.i = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_centerY, -1.0f);
        this.j = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointUpX, -1.0f);
        this.k = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointUpY, -1.0f);
        this.l = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointDownX, -1.0f);
        this.m = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointDownY, -1.0f);
        this.n = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointLeftX, -1.0f);
        this.o = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointLeftY, -1.0f);
        this.p = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointRightX, -1.0f);
        this.q = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_pointRightY, -1.0f);
        this.r = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_targetWidth, -1.0f);
        this.s = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydScrollRelativeLayout_turnAreaWidth, -1.0f);
        this.v = typedArrayObtainStyledAttributes.getInt(R$styleable.BydScrollRelativeLayout_turnType, 1);
        this.g = typedArrayObtainStyledAttributes.getInt(R$styleable.BydScrollRelativeLayout_turnDirection, 3);
        this.H = typedArrayObtainStyledAttributes.getInt(R$styleable.BydScrollRelativeLayout_calBy, 1);
        this.w = new PointF(this.j, this.k);
        this.x = new PointF(this.h, this.i);
        this.y = new PointF(this.l, this.m);
        this.z = new PointF(this.n, this.o);
        this.A = new PointF(this.p, this.q);
        this.f593d = this.h;
        this.e = this.i;
        typedArrayObtainStyledAttributes.recycle();
        if (this.v == 3) {
            b bVar = new b();
            this.G = bVar;
            int i = this.H;
            bVar.b(i == 2 ? this.w : this.z, this.x, i == 2 ? this.y : this.A);
            setScrollDirection(this.H == 2 ? 1 : 2);
            this.G.a(this.H == 2 ? 1 : 0);
        }
        if (this.v == 1) {
            d dVar = new d();
            this.G = dVar;
            dVar.d(this.z, this.A, this.x, this.w, this.y);
        }
        if (this.v == 2) {
            b.a.b.e.e.c.a aVar = new b.a.b.e.e.c.a();
            this.G = aVar;
            aVar.b(this.w, this.x, this.y);
            setScrollDirection(1);
        }
        this.I = (int) context.getResources().getDimension(R$dimen.dp_21);
    }

    public final boolean d(float f, float f2) {
        float fMin;
        float fMax;
        float fMin2;
        float fMax2;
        int i;
        if (this.f == 1) {
            fMin = Math.min(this.j, this.l) - this.I;
            fMax = Math.max(this.j, this.l) + this.I;
            fMin2 = Math.min(this.k, this.m) - this.I;
            fMax2 = Math.max(this.k, this.m);
            i = this.I;
        } else {
            fMin = Math.min(this.n, this.p) - this.I;
            fMax = Math.max(this.n, this.p) + this.I;
            fMin2 = Math.min(this.o, this.q) - this.I;
            fMax2 = Math.max(this.o, this.q);
            i = this.I;
        }
        return f >= fMin && f <= fMax && f2 >= fMin2 && f2 <= fMax2 + ((float) i);
    }

    public final boolean e(int i) {
        if (i != -1) {
            if (i == 0 || i == 1) {
                int i2 = this.g;
                if (i2 != 1 && i2 != 3 && i2 != 4) {
                    return false;
                }
            } else {
                if (i != 2 && i != 3) {
                    return false;
                }
                int i3 = this.g;
                if (i3 != 2 && i3 != 3 && i3 != 4) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void f(int i) {
        if (this.C) {
            return;
        }
        this.F = true;
        this.C = true;
        f591b.g(i);
    }

    public final void g(int i) {
        if (this.F) {
            f591b.h(i);
            this.F = false;
            this.B = false;
            this.C = false;
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
        f591b.i(i);
    }

    public final void i(int i) {
        if (this.E) {
            return;
        }
        this.F = true;
        this.E = true;
        f591b.j(i);
    }

    public final void j(int i) {
        if (this.B) {
            return;
        }
        this.B = true;
        this.F = true;
        f591b.k(i);
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
        return motionEvent.getX() >= this.f593d - (getTargetWidth() / 2.0f) && motionEvent.getX() <= this.f593d + (getTargetWidth() / 2.0f) && motionEvent.getY() >= this.e - (getTargetHeight() / 2.0f) && motionEvent.getY() <= this.e + (getTargetHeight() / 2.0f);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f592c != null) {
            return;
        }
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null && childAt.getTag() != null && childAt.getTag().equals("target")) {
                this.f592c = childAt;
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        View.OnClickListener onClickListener;
        float f;
        int action = motionEvent.getAction();
        if (action == 0) {
            b.a.b.c.a.a(this, " onTouchEvent : MotionEvent.ACTION_DOWN ");
            this.u = motionEvent.getY();
            this.t = motionEvent.getX();
            this.f = -1;
            if (!l(motionEvent)) {
                b.a.b.c.a.a(this, "onTouchEvent : 不在控件范围内 ");
                return false;
            }
            getParent().requestDisallowInterceptTouchEvent(true);
            this.K = false;
        } else if (action == 1) {
            b.a.b.c.a.a(this, " MotionEvent.ACTION_UP ");
            if (l(motionEvent) && (onClickListener = this.J) != null && !this.K) {
                onClickListener.onClick(this.f592c);
                this.K = false;
            }
            this.f592c.layout((int) (((-getTargetWidth()) / 2.0f) + this.h), (int) (((-getTargetHeight()) / 2.0f) + this.i), (int) ((getTargetWidth() / 2.0f) + this.h), (int) ((getTargetHeight() / 2.0f) + this.i));
            this.f593d = this.h;
            this.e = this.i;
            this.F = true;
            a(-1);
        } else if (action == 2) {
            b.a.b.c.a.a(this, " MotionEvent.ACTION_MOVE  event.getX() : " + motionEvent.getX() + " event.getY() : " + motionEvent.getX() + " event.getY() : " + motionEvent.getY() + " height : " + getHeight());
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int i = this.v;
            if (i == 2 || i == 3) {
                this.f = this.H == 2 ? 1 : 0;
            } else {
                if (this.g == 2 && Math.abs(y - this.u) > 20.0f) {
                    return true;
                }
                if (this.g == 1 && Math.abs(x - this.t) > 20.0f) {
                    return true;
                }
                if (Math.abs(x - this.h) < this.r / 2.0f && Math.abs(x - this.i) < this.r / 2.0f) {
                    this.f = -1;
                }
                if (Math.abs(y - this.u) > Math.abs(x - this.t)) {
                    if (this.f == -1) {
                        this.f = 1;
                    }
                } else if (this.f == -1) {
                    this.f = 0;
                }
            }
            if (!d(x, y)) {
                return true;
            }
            c cVar = this.G;
            int i2 = this.f;
            if (i2 == 1) {
                float f2 = this.k;
                f = (y - f2) / (this.m - f2);
            } else {
                float f3 = this.n;
                f = (x - f3) / (this.p - f3);
            }
            PointF pointFC = cVar.c(i2, f);
            this.f593d = pointFC.x;
            this.e = pointFC.y;
            k(pointFC, this.w, this.B, 0);
            k(pointFC, this.y, this.C, 1);
            k(pointFC, this.z, this.D, 2);
            k(pointFC, this.A, this.E, 3);
            this.f592c.layout((int) (this.f593d - (getTargetWidth() / 2.0f)), (int) (this.e - (getTargetHeight() / 2.0f)), (int) (this.f593d + (getTargetWidth() / 2.0f)), (int) (this.e + (getTargetHeight() / 2.0f)));
            b.a.b.c.a.a(this, "MotionEvent.ACTION_MOVE  l : " + ((int) (this.f593d - (getTargetWidth() / 2.0f))) + " t ： " + ((int) (this.e - (getTargetHeight() / 2.0f))) + " r ： " + ((int) (this.f593d + (getTargetWidth() / 2.0f))) + " b : " + ((int) (this.e + (getTargetHeight() / 2.0f))) + "  centerX : " + this.f593d + "  centerY  : " + this.e);
            this.K = true;
        }
        return true;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.J = onClickListener;
    }

    public void setScrollDirection(int i) {
        this.g = i;
    }

    public void setScrollerListener(b.a.b.e.e.b bVar) {
        f591b.l(bVar);
    }

    public BydScrollRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydScrollRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 1;
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.K = false;
        c(context, attributeSet);
    }
}
