package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: TouchResponse.java */
/* JADX INFO: loaded from: classes.dex */
class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final float[][] f966a = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final float[][] f967b = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    private float t;
    private float u;
    private final MotionLayout v;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f968c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f969d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f970e = 0;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    private float i = 0.5f;
    private float j = 0.5f;
    float k = 0.5f;
    float l = 0.5f;
    private int m = -1;
    boolean n = false;
    private float o = 0.0f;
    private float p = 1.0f;
    private boolean q = false;
    private float[] r = new float[2];
    private int[] s = new int[2];
    private float w = 4.0f;
    private float x = 1.2f;
    private boolean y = true;
    private float z = 1.0f;
    private int A = 0;
    private float B = 10.0f;
    private float C = 10.0f;
    private float D = 1.0f;
    private float E = Float.NaN;
    private float F = Float.NaN;
    private int G = 0;
    private int H = 0;

    /* JADX INFO: compiled from: TouchResponse.java */
    class a implements View.OnTouchListener {
        a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    /* JADX INFO: compiled from: TouchResponse.java */
    class b implements NestedScrollView.b {
        b() {
        }

        @Override // androidx.core.widget.NestedScrollView.b
        public void a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        }
    }

    r(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.v = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    private void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index == androidx.constraintlayout.widget.e.Ka) {
                this.f = typedArray.getResourceId(index, this.f);
            } else if (index == androidx.constraintlayout.widget.e.La) {
                int i2 = typedArray.getInt(index, this.f968c);
                this.f968c = i2;
                float[][] fArr = f966a;
                this.j = fArr[i2][0];
                this.i = fArr[i2][1];
            } else if (index == androidx.constraintlayout.widget.e.va) {
                int i3 = typedArray.getInt(index, this.f969d);
                this.f969d = i3;
                float[][] fArr2 = f967b;
                if (i3 < fArr2.length) {
                    this.o = fArr2[i3][0];
                    this.p = fArr2[i3][1];
                } else {
                    this.p = Float.NaN;
                    this.o = Float.NaN;
                    this.n = true;
                }
            } else if (index == androidx.constraintlayout.widget.e.Aa) {
                this.w = typedArray.getFloat(index, this.w);
            } else if (index == androidx.constraintlayout.widget.e.za) {
                this.x = typedArray.getFloat(index, this.x);
            } else if (index == androidx.constraintlayout.widget.e.Ba) {
                this.y = typedArray.getBoolean(index, this.y);
            } else if (index == androidx.constraintlayout.widget.e.wa) {
                this.z = typedArray.getFloat(index, this.z);
            } else if (index == androidx.constraintlayout.widget.e.xa) {
                this.B = typedArray.getFloat(index, this.B);
            } else if (index == androidx.constraintlayout.widget.e.Ma) {
                this.g = typedArray.getResourceId(index, this.g);
            } else if (index == androidx.constraintlayout.widget.e.Da) {
                this.f970e = typedArray.getInt(index, this.f970e);
            } else if (index == androidx.constraintlayout.widget.e.Ca) {
                this.A = typedArray.getInteger(index, 0);
            } else if (index == androidx.constraintlayout.widget.e.ya) {
                this.h = typedArray.getResourceId(index, 0);
            } else if (index == androidx.constraintlayout.widget.e.Ea) {
                this.m = typedArray.getResourceId(index, this.m);
            } else if (index == androidx.constraintlayout.widget.e.Ga) {
                this.C = typedArray.getFloat(index, this.C);
            } else if (index == androidx.constraintlayout.widget.e.Ha) {
                this.D = typedArray.getFloat(index, this.D);
            } else if (index == androidx.constraintlayout.widget.e.Ia) {
                this.E = typedArray.getFloat(index, this.E);
            } else if (index == androidx.constraintlayout.widget.e.Ja) {
                this.F = typedArray.getFloat(index, this.F);
            } else if (index == androidx.constraintlayout.widget.e.Fa) {
                this.G = typedArray.getInt(index, this.G);
            } else if (index == androidx.constraintlayout.widget.e.ua) {
                this.H = typedArray.getInt(index, this.H);
            }
        }
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.ta);
        b(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    void A() {
        View viewFindViewById;
        int i = this.f;
        if (i != -1) {
            viewFindViewById = this.v.findViewById(i);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + androidx.constraintlayout.motion.widget.b.c(this.v.getContext(), this.f));
            }
        } else {
            viewFindViewById = null;
        }
        if (viewFindViewById instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) viewFindViewById;
            nestedScrollView.setOnTouchListener(new a());
            nestedScrollView.setOnScrollChangeListener(new b());
        }
    }

    float a(float f, float f2) {
        return (f * this.o) + (f2 * this.p);
    }

    public int d() {
        return this.H;
    }

    public int e() {
        return this.A;
    }

    RectF f(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i = this.h;
        if (i == -1 || (viewFindViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    float g() {
        return this.x;
    }

    public float h() {
        return this.w;
    }

    boolean i() {
        return this.y;
    }

    float j(float f, float f2) {
        this.v.k0(this.f, this.v.getProgress(), this.j, this.i, this.r);
        float f3 = this.o;
        if (f3 != 0.0f) {
            float[] fArr = this.r;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f * f3) / fArr[0];
        }
        float[] fArr2 = this.r;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f2 * this.p) / fArr2[1];
    }

    public int k() {
        return this.G;
    }

    public float l() {
        return this.C;
    }

    public float m() {
        return this.D;
    }

    public float n() {
        return this.E;
    }

    public float o() {
        return this.F;
    }

    RectF p(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i = this.g;
        if (i == -1 || (viewFindViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    int q() {
        return this.g;
    }

    boolean r() {
        return this.q;
    }

    void s(MotionEvent motionEvent, MotionLayout.g gVar, int i, q qVar) {
        int i2;
        if (this.n) {
            t(motionEvent, gVar, i, qVar);
            return;
        }
        gVar.a(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.t = motionEvent.getRawX();
            this.u = motionEvent.getRawY();
            this.q = false;
            return;
        }
        if (action != 1) {
            if (action != 2) {
                return;
            }
            float rawY = motionEvent.getRawY() - this.u;
            float rawX = motionEvent.getRawX() - this.t;
            if (Math.abs((this.o * rawX) + (this.p * rawY)) > this.B || this.q) {
                float progress = this.v.getProgress();
                if (!this.q) {
                    this.q = true;
                    this.v.setProgress(progress);
                }
                int i3 = this.f;
                if (i3 != -1) {
                    this.v.k0(i3, progress, this.j, this.i, this.r);
                } else {
                    float fMin = Math.min(this.v.getWidth(), this.v.getHeight());
                    float[] fArr = this.r;
                    fArr[1] = this.p * fMin;
                    fArr[0] = fMin * this.o;
                }
                float f = this.o;
                float[] fArr2 = this.r;
                if (Math.abs(((f * fArr2[0]) + (this.p * fArr2[1])) * this.z) < 0.01d) {
                    float[] fArr3 = this.r;
                    fArr3[0] = 0.01f;
                    fArr3[1] = 0.01f;
                }
                float fMax = Math.max(Math.min(progress + (this.o != 0.0f ? rawX / this.r[0] : rawY / this.r[1]), 1.0f), 0.0f);
                if (this.f970e == 6) {
                    fMax = Math.max(fMax, 0.01f);
                }
                if (this.f970e == 7) {
                    fMax = Math.min(fMax, 0.99f);
                }
                float progress2 = this.v.getProgress();
                if (fMax != progress2) {
                    if (progress2 == 0.0f || progress2 == 1.0f) {
                        this.v.e0(progress2 == 0.0f);
                    }
                    this.v.setProgress(fMax);
                    gVar.e(1000);
                    this.v.D = this.o != 0.0f ? gVar.c() / this.r[0] : gVar.b() / this.r[1];
                } else {
                    this.v.D = 0.0f;
                }
                this.t = motionEvent.getRawX();
                this.u = motionEvent.getRawY();
                return;
            }
            return;
        }
        this.q = false;
        gVar.e(1000);
        float fC = gVar.c();
        float fB = gVar.b();
        float progress3 = this.v.getProgress();
        int i4 = this.f;
        if (i4 != -1) {
            this.v.k0(i4, progress3, this.j, this.i, this.r);
        } else {
            float fMin2 = Math.min(this.v.getWidth(), this.v.getHeight());
            float[] fArr4 = this.r;
            fArr4[1] = this.p * fMin2;
            fArr4[0] = fMin2 * this.o;
        }
        float f2 = this.o;
        float[] fArr5 = this.r;
        float f3 = fArr5[0];
        float f4 = fArr5[1];
        float fAbs = f2 != 0.0f ? fC / fArr5[0] : fB / fArr5[1];
        float f5 = !Float.isNaN(fAbs) ? (fAbs / 3.0f) + progress3 : progress3;
        if (f5 == 0.0f || f5 == 1.0f || (i2 = this.f970e) == 3) {
            if (0.0f >= f5 || 1.0f <= f5) {
                this.v.setState(MotionLayout.TransitionState.FINISHED);
                return;
            }
            return;
        }
        float f6 = ((double) f5) < 0.5d ? 0.0f : 1.0f;
        if (i2 == 6) {
            if (progress3 + fAbs < 0.0f) {
                fAbs = Math.abs(fAbs);
            }
            f6 = 1.0f;
        }
        if (this.f970e == 7) {
            if (progress3 + fAbs > 1.0f) {
                fAbs = -Math.abs(fAbs);
            }
            f6 = 0.0f;
        }
        this.v.B0(this.f970e, f6, fAbs);
        if (0.0f >= progress3 || 1.0f <= progress3) {
            this.v.setState(MotionLayout.TransitionState.FINISHED);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x02bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void t(android.view.MotionEvent r24, androidx.constraintlayout.motion.widget.MotionLayout.g r25, int r26, androidx.constraintlayout.motion.widget.q r27) {
        /*
            Method dump skipped, instruction units count: 833
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.r.t(android.view.MotionEvent, androidx.constraintlayout.motion.widget.MotionLayout$g, int, androidx.constraintlayout.motion.widget.q):void");
    }

    public String toString() {
        if (Float.isNaN(this.o)) {
            return "rotation";
        }
        return this.o + " , " + this.p;
    }

    void u(float f, float f2) {
        float progress = this.v.getProgress();
        if (!this.q) {
            this.q = true;
            this.v.setProgress(progress);
        }
        this.v.k0(this.f, progress, this.j, this.i, this.r);
        float f3 = this.o;
        float[] fArr = this.r;
        if (Math.abs((f3 * fArr[0]) + (this.p * fArr[1])) < 0.01d) {
            float[] fArr2 = this.r;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f4 = this.o;
        float fMax = Math.max(Math.min(progress + (f4 != 0.0f ? (f * f4) / this.r[0] : (f2 * this.p) / this.r[1]), 1.0f), 0.0f);
        if (fMax != this.v.getProgress()) {
            this.v.setProgress(fMax);
        }
    }

    void v(float f, float f2) {
        this.q = false;
        float progress = this.v.getProgress();
        this.v.k0(this.f, progress, this.j, this.i, this.r);
        float f3 = this.o;
        float[] fArr = this.r;
        float f4 = fArr[0];
        float f5 = this.p;
        float f6 = fArr[1];
        float f7 = f3 != 0.0f ? (f * f3) / fArr[0] : (f2 * f5) / fArr[1];
        if (!Float.isNaN(f7)) {
            progress += f7 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z = progress != 1.0f;
            int i = this.f970e;
            if ((i != 3) && z) {
                this.v.B0(i, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f7);
            }
        }
    }

    void w(float f, float f2) {
        this.t = f;
        this.u = f2;
    }

    public void x(boolean z) {
        if (z) {
            float[][] fArr = f967b;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = f966a;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = f967b;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = f966a;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = f966a;
        int i = this.f968c;
        this.j = fArr5[i][0];
        this.i = fArr5[i][1];
        int i2 = this.f969d;
        float[][] fArr6 = f967b;
        if (i2 >= fArr6.length) {
            return;
        }
        this.o = fArr6[i2][0];
        this.p = fArr6[i2][1];
    }

    public void y(int i) {
        this.f970e = i;
    }

    void z(float f, float f2) {
        this.t = f;
        this.u = f2;
        this.q = false;
    }
}
