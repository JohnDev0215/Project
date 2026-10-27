package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import b.b.g.q.h.c.c;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class SoundFieldView extends RelativeLayout implements View.OnClickListener {
    public int A;
    public MotionEvent B;
    public int D;
    public int E;
    public int F;
    public int G;
    public volatile boolean H;
    public ImageView I;
    public ImageView J;
    public ImageView K;
    public ImageView L;
    public ImageView M;
    public View N;
    public View O;
    public c P;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3453a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3454b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3455c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3456d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3457e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3458f;
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
    public int r;
    public int s;
    public float t;
    public float u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    public enum OutsideType {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3460a;

        static {
            int[] iArr = new int[OutsideType.values().length];
            f3460a = iArr;
            try {
                iArr[OutsideType.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3460a[OutsideType.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3460a[OutsideType.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3460a[OutsideType.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public SoundFieldView(Context context) {
        super(context);
        this.f3453a = 0;
        this.f3454b = 0;
        this.f3455c = 0;
        this.f3456d = 0;
        this.f3457e = 0;
        this.f3458f = 0;
        this.g = 0;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0;
        this.s = 0;
        this.t = 1.0f;
        this.u = 1.0f;
        this.v = 0;
        this.w = 0;
        this.x = -1;
        this.y = -1;
        this.z = 0;
        this.A = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = false;
        j(context);
    }

    private float getBottomWithoutPadding() {
        return (getHeight() - this.G) - this.q;
    }

    private float getHeightWithoutPadding() {
        return (((getHeight() - (this.I != null ? r0.getHeight() : 0)) - this.o) - this.q) * 1.0f;
    }

    private float getLeftWithoutPadding() {
        return (this.F + this.n) * 1.0f;
    }

    private float getRightWithoutPadding() {
        return (getWidth() - this.F) - this.p;
    }

    private float getTopWithoutPadding() {
        return (this.G + this.o) * 1.0f;
    }

    private float getWidthWithoutPadding() {
        return (((getWidth() - (this.I != null ? r0.getWidth() : 0)) - this.n) - this.p) * 1.0f;
    }

    public final void a(float f2) {
        ImageView imageView = this.I;
        if (imageView == null) {
            return;
        }
        imageView.setTranslationX(f2 - (imageView.getWidth() / 2));
    }

    public final void b(float f2) {
        ImageView imageView = this.I;
        if (imageView == null) {
            return;
        }
        imageView.setTranslationY(f2 - (imageView.getHeight() / 2));
    }

    public final OutsideType c(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return null;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (x < getLeftWithoutPadding()) {
            return OutsideType.LEFT;
        }
        if (x > getRightWithoutPadding()) {
            return OutsideType.RIGHT;
        }
        if (y < getTopWithoutPadding()) {
            return OutsideType.TOP;
        }
        if (y > getBottomWithoutPadding()) {
            return OutsideType.BOTTOM;
        }
        return null;
    }

    public final ImageView d(Context context, int i, int i2, int i3) {
        float f2 = this.h;
        return e(context, null, f2, f2, i, i2, i3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            b.b.m.c.a.a("SoundFieldView", "dispatchTouchEvent", "ACTION_DOWN");
        } else if (action == 1) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            OutsideType outsideTypeC = c(motionEvent);
            b.b.m.c.a.a("SoundFieldView", "dispatchTouchEvent", "ACTION_UP", "type: " + outsideTypeC, "isMoving: " + this.H, "x: " + x + ", y: " + y);
            if (this.H && outsideTypeC != null) {
                k();
            }
            this.H = false;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final ImageView e(Context context, RelativeLayout.LayoutParams layoutParams, float f2, float f3, int i, int i2, int i3) {
        if (layoutParams == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        }
        ImageView imageView = new ImageView(context);
        if (f2 > 0.0f) {
            layoutParams.width = (int) f2;
        }
        if (f3 > 0.0f) {
            layoutParams.height = (int) f3;
        }
        if (i2 > 0) {
            layoutParams.addRule(i2);
        }
        if (i3 > 0) {
            layoutParams.addRule(i3);
        }
        imageView.setLayoutParams(layoutParams);
        if (i > 0) {
            imageView.setImageResource(i);
        }
        return imageView;
    }

    public final int f(int i) {
        int i2 = this.D + ((int) ((i - (this.r / 2)) * this.t));
        int leftWithoutPadding = (int) getLeftWithoutPadding();
        int rightWithoutPadding = (int) getRightWithoutPadding();
        return i2 < leftWithoutPadding ? leftWithoutPadding : i2 > rightWithoutPadding ? rightWithoutPadding : i2;
    }

    public final int g(int i) {
        int i2 = this.E + ((int) ((i - (this.s / 2)) * this.u));
        int topWithoutPadding = (int) getTopWithoutPadding();
        int bottomWithoutPadding = (int) getBottomWithoutPadding();
        return i2 < topWithoutPadding ? topWithoutPadding : i2 > bottomWithoutPadding ? bottomWithoutPadding : i2;
    }

    public final void h(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.sound_field_view);
        this.f3453a = typedArrayObtainStyledAttributes.getResourceId(6, this.f3453a);
        this.f3454b = typedArrayObtainStyledAttributes.getResourceId(8, this.f3454b);
        this.f3455c = typedArrayObtainStyledAttributes.getResourceId(7, this.f3455c);
        this.f3456d = typedArrayObtainStyledAttributes.getResourceId(4, this.f3456d);
        this.h = typedArrayObtainStyledAttributes.getDimension(9, this.h);
        this.i = typedArrayObtainStyledAttributes.getDimension(5, this.i);
        this.f3457e = typedArrayObtainStyledAttributes.getResourceId(2, this.f3457e);
        this.f3458f = typedArrayObtainStyledAttributes.getResourceId(3, this.f3458f);
        this.l = typedArrayObtainStyledAttributes.getDimension(0, this.l);
        this.m = typedArrayObtainStyledAttributes.getDimension(1, this.m);
        this.o = typedArrayObtainStyledAttributes.getDimension(13, this.o);
        this.q = typedArrayObtainStyledAttributes.getDimension(10, this.q);
        this.n = typedArrayObtainStyledAttributes.getDimension(11, this.n);
        this.p = typedArrayObtainStyledAttributes.getDimension(12, this.p);
        this.g = typedArrayObtainStyledAttributes.getResourceId(15, this.g);
        this.j = typedArrayObtainStyledAttributes.getDimension(16, this.j);
        this.k = typedArrayObtainStyledAttributes.getDimension(14, this.k);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void i(int i, int i2) {
        b.b.m.c.a.a("SoundFieldView", "initPosition", ", positionX: " + i + ", positionY: " + i2);
        this.v = i;
        this.w = i2;
    }

    public final void j(Context context) {
        int i = this.f3453a;
        if (i != 0) {
            ImageView imageViewD = d(context, i, 20, 15);
            this.J = imageViewD;
            imageViewD.setTag("sound_iv_left");
            this.J.setOnClickListener(this);
            addView(this.J);
        }
        int i2 = this.f3454b;
        if (i2 != 0) {
            ImageView imageViewD2 = d(context, i2, 10, 14);
            this.K = imageViewD2;
            imageViewD2.setTag("sound_iv_top");
            this.K.setOnClickListener(this);
            addView(this.K);
        }
        int i3 = this.f3455c;
        if (i3 != 0) {
            ImageView imageViewD3 = d(context, i3, 21, 15);
            this.L = imageViewD3;
            imageViewD3.setTag("sound_iv_right");
            this.L.setOnClickListener(this);
            addView(this.L);
        }
        int i4 = this.f3456d;
        if (i4 != 0) {
            ImageView imageViewD4 = d(context, i4, 12, 14);
            this.M = imageViewD4;
            imageViewD4.setTag("sound_iv_bottom");
            this.M.setOnClickListener(this);
            addView(this.M);
        }
        o();
        p();
        int i5 = this.g;
        if (i5 != 0) {
            ImageView imageViewE = e(context, null, this.j, this.k, i5, 13, -1);
            this.I = imageViewE;
            addView(imageViewE);
        }
    }

    public final void k() {
        int i = this.z;
        this.v = i;
        int i2 = this.A;
        this.w = i2;
        if (i == this.x && i2 == this.y) {
            return;
        }
        this.x = i;
        this.y = i2;
        b.b.m.c.a.a("SoundFieldView", "---onAcitonUp---curPositionX: " + this.v + ", curPositionY: " + this.w);
        c cVar = this.P;
        if (cVar != null) {
            cVar.a(this.v, this.w);
        }
    }

    public void l() {
        b.b.m.c.a.a("SoundFieldView", "---resetPosition---");
        q(this.D, this.E);
        this.v = this.r / 2;
        this.w = this.s / 2;
        this.x = -1;
        this.y = -1;
    }

    public final void m(float f2, float f3) {
        if (this.I == null) {
            return;
        }
        float leftWithoutPadding = getLeftWithoutPadding();
        int i = (int) ((f2 - leftWithoutPadding) / this.t);
        if (Float.compare(f2, getRightWithoutPadding()) > 0) {
            i = this.r;
        } else if (Float.compare(f2, leftWithoutPadding) >= 0) {
            a(f2 - (this.I != null ? r0.getLeft() : 0));
        } else {
            i = 0;
        }
        this.z = i;
        float topWithoutPadding = getTopWithoutPadding();
        int i2 = (int) ((f3 - topWithoutPadding) / this.u);
        if (Float.compare(f3, getBottomWithoutPadding()) > 0) {
            i = this.s;
        } else if (Float.compare(f3, topWithoutPadding) >= 0) {
            b(f3 - (this.I != null ? r5.getTop() : 0));
            i = i2;
        }
        this.A = i;
        c cVar = this.P;
        if (cVar == null) {
            return;
        }
        cVar.c(i, i);
    }

    public final void n(OutsideType outsideType, MotionEvent motionEvent) {
        if (outsideType == null || motionEvent == null) {
            return;
        }
        int i = a.f3460a[outsideType.ordinal()];
        if (i == 1) {
            m(getLeftWithoutPadding(), motionEvent.getY());
            return;
        }
        if (i == 2) {
            m(getRightWithoutPadding(), motionEvent.getY());
        } else if (i == 3) {
            m(motionEvent.getX(), getTopWithoutPadding());
        } else {
            if (i != 4) {
                return;
            }
            m(motionEvent.getX(), getBottomWithoutPadding());
        }
    }

    public final void o() {
        if (this.f3457e == 0) {
            return;
        }
        View view = this.N;
        if (view != null) {
            removeView(view);
        }
        ImageView imageViewE = e(getContext(), new RelativeLayout.LayoutParams(-2, -2), this.l, -1.0f, this.f3457e, 13, -1);
        this.N = imageViewE;
        addView(imageViewE);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str = (String) view.getTag();
        str.hashCode();
        switch (str) {
            case "sound_iv_top":
                this.w--;
                break;
            case "sound_iv_bottom":
                this.w++;
                break;
            case "sound_iv_left":
                this.v--;
                break;
            case "sound_iv_right":
                this.v++;
                break;
        }
        int i = this.v;
        int i2 = this.r;
        if (i > i2) {
            this.v = i2;
        }
        if (this.v < 0) {
            this.v = 0;
        }
        int i3 = this.w;
        int i4 = this.s;
        if (i3 > i4) {
            this.w = i4;
        }
        if (this.w < 0) {
            this.w = 0;
        }
        b.b.m.c.a.a("SoundFieldView", "onClick", ", curPositionX: " + this.v + ", curPositionY: " + this.w + ", stepWidthX: " + this.t + ", stepWidthY: " + this.u);
        q(f(this.v), g(this.w));
        c cVar = this.P;
        if (cVar != null) {
            cVar.b(this.v, this.w);
        }
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.D = getWidth() / 2;
        this.E = getHeight() / 2;
        ImageView imageView = this.I;
        if (imageView != null) {
            this.F = imageView.getWidth() / 2;
            this.G = this.I.getHeight() / 2;
        }
        if (this.r > 0) {
            this.t = getWidthWithoutPadding() / this.r;
        }
        if (this.s > 0) {
            this.u = getHeightWithoutPadding() / this.s;
        }
        q(f(this.v), g(this.w));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        int action = motionEvent.getAction();
        OutsideType outsideTypeC = c(motionEvent);
        if (outsideTypeC == null) {
            if (action == 0) {
                this.H = false;
                m(motionEvent.getX(), motionEvent.getY());
            } else if (action == 2) {
                this.H = true;
                m(motionEvent.getX(), motionEvent.getY());
            } else if (action == 1) {
                b.b.m.c.a.a("SoundFieldView", "onTouchEvent", "ACTION_UP");
                k();
            }
            this.B = motionEvent;
            return true;
        }
        b.b.m.c.a.a("SoundFieldView", "onTouchEvent", "to outside, type:" + outsideTypeC);
        if (action == 1 && (motionEvent2 = this.B) != null && motionEvent2.getAction() == 2) {
            b.b.m.c.a.a("SoundFieldView", "onTouchEvent", "preEvent moving to outside, type:" + outsideTypeC);
            n(outsideTypeC, motionEvent);
            k();
        }
        this.B = motionEvent;
        return true;
    }

    public final void p() {
        if (this.f3458f == 0) {
            return;
        }
        View view = this.O;
        if (view != null) {
            removeView(view);
        }
        ImageView imageViewE = e(getContext(), new RelativeLayout.LayoutParams(-2, -2), -1.0f, this.m, this.f3458f, 13, -1);
        this.O = imageViewE;
        addView(imageViewE);
    }

    public final void q(int i, int i2) {
        int top;
        ImageView imageView = this.I;
        int left = 0;
        if (imageView != null) {
            left = imageView.getLeft();
            top = this.I.getTop();
        } else {
            top = 0;
        }
        a(i - left);
        b(i2 - top);
    }

    public void setAxisHeightY(float f2) {
        this.m = f2;
        p();
    }

    public void setAxisWidthX(float f2) {
        this.l = f2;
        o();
    }

    public void setFieldPadding(float f2, float f3, float f4, float f5) {
        this.n = f2;
        this.o = f3;
        this.p = f4;
        this.q = f5;
        invalidate();
    }

    public void setOnSoundFieldChanhedCallback(c cVar) {
        this.P = cVar;
    }

    public void setRangeOfWidthAndHeight(int i, int i2) {
        b.b.m.c.a.a("SoundFieldView", "setRangeOfWidthAndHeight", ", sizeX = " + i + ", sizeY = " + i2);
        this.r = i;
        this.s = i2;
        this.v = i / 2;
        this.w = i2 / 2;
    }

    public SoundFieldView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3453a = 0;
        this.f3454b = 0;
        this.f3455c = 0;
        this.f3456d = 0;
        this.f3457e = 0;
        this.f3458f = 0;
        this.g = 0;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0;
        this.s = 0;
        this.t = 1.0f;
        this.u = 1.0f;
        this.v = 0;
        this.w = 0;
        this.x = -1;
        this.y = -1;
        this.z = 0;
        this.A = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = false;
        h(context, attributeSet);
        j(context);
    }

    public SoundFieldView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3453a = 0;
        this.f3454b = 0;
        this.f3455c = 0;
        this.f3456d = 0;
        this.f3457e = 0;
        this.f3458f = 0;
        this.g = 0;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0;
        this.s = 0;
        this.t = 1.0f;
        this.u = 1.0f;
        this.v = 0;
        this.w = 0;
        this.x = -1;
        this.y = -1;
        this.z = 0;
        this.A = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = false;
        h(context, attributeSet);
        j(context);
    }
}
