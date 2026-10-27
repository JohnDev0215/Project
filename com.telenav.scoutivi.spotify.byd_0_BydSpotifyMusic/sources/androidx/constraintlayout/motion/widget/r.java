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
    private static final float[][] G = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    private static final float[][] H = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f1583r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f1584s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final MotionLayout f1585t;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1566a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1567b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1568c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1569d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1570e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1571f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f1572g = 0.5f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f1573h = 0.5f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    float f1574i = 0.5f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    float f1575j = 0.5f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f1576k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    boolean f1577l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private float f1578m = 0.0f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f1579n = 1.0f;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f1580o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private float[] f1581p = new float[2];

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int[] f1582q = new int[2];

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private float f1586u = 4.0f;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private float f1587v = 1.2f;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f1588w = true;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f1589x = 1.0f;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f1590y = 0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private float f1591z = 10.0f;
    private float A = 10.0f;
    private float B = 1.0f;
    private float C = Float.NaN;
    private float D = Float.NaN;
    private int E = 0;
    private int F = 0;

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
        public void a(NestedScrollView nestedScrollView, int i7, int i8, int i9, int i10) {
        }
    }

    r(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.f1585t = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    private void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArray.getIndex(i7);
            if (index == androidx.constraintlayout.widget.i.OnSwipe_touchAnchorId) {
                this.f1569d = typedArray.getResourceId(index, this.f1569d);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_touchAnchorSide) {
                int i8 = typedArray.getInt(index, this.f1566a);
                this.f1566a = i8;
                float[][] fArr = G;
                this.f1573h = fArr[i8][0];
                this.f1572g = fArr[i8][1];
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_dragDirection) {
                int i9 = typedArray.getInt(index, this.f1567b);
                this.f1567b = i9;
                float[][] fArr2 = H;
                if (i9 < fArr2.length) {
                    this.f1578m = fArr2[i9][0];
                    this.f1579n = fArr2[i9][1];
                } else {
                    this.f1579n = Float.NaN;
                    this.f1578m = Float.NaN;
                    this.f1577l = true;
                }
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_maxVelocity) {
                this.f1586u = typedArray.getFloat(index, this.f1586u);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_maxAcceleration) {
                this.f1587v = typedArray.getFloat(index, this.f1587v);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_moveWhenScrollAtTop) {
                this.f1588w = typedArray.getBoolean(index, this.f1588w);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_dragScale) {
                this.f1589x = typedArray.getFloat(index, this.f1589x);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_dragThreshold) {
                this.f1591z = typedArray.getFloat(index, this.f1591z);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_touchRegionId) {
                this.f1570e = typedArray.getResourceId(index, this.f1570e);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_onTouchUp) {
                this.f1568c = typedArray.getInt(index, this.f1568c);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_nestedScrollFlags) {
                this.f1590y = typedArray.getInteger(index, 0);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_limitBoundsTo) {
                this.f1571f = typedArray.getResourceId(index, 0);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_rotationCenterId) {
                this.f1576k = typedArray.getResourceId(index, this.f1576k);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_springDamping) {
                this.A = typedArray.getFloat(index, this.A);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_springMass) {
                this.B = typedArray.getFloat(index, this.B);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_springStiffness) {
                this.C = typedArray.getFloat(index, this.C);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_springStopThreshold) {
                this.D = typedArray.getFloat(index, this.D);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_springBoundary) {
                this.E = typedArray.getInt(index, this.E);
            } else if (index == androidx.constraintlayout.widget.i.OnSwipe_autoCompleteMode) {
                this.F = typedArray.getInt(index, this.F);
            }
        }
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.i.OnSwipe);
        b(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    float a(float f7, float f8) {
        return (f7 * this.f1578m) + (f8 * this.f1579n);
    }

    public int d() {
        return this.F;
    }

    public int e() {
        return this.f1590y;
    }

    RectF f(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i7 = this.f1571f;
        if (i7 == -1 || (viewFindViewById = viewGroup.findViewById(i7)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    float g() {
        return this.f1587v;
    }

    public float h() {
        return this.f1586u;
    }

    boolean i() {
        return this.f1588w;
    }

    float j(float f7, float f8) {
        this.f1585t.l0(this.f1569d, this.f1585t.getProgress(), this.f1573h, this.f1572g, this.f1581p);
        float f9 = this.f1578m;
        if (f9 != 0.0f) {
            float[] fArr = this.f1581p;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f7 * f9) / fArr[0];
        }
        float[] fArr2 = this.f1581p;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f8 * this.f1579n) / fArr2[1];
    }

    public int k() {
        return this.E;
    }

    public float l() {
        return this.A;
    }

    public float m() {
        return this.B;
    }

    public float n() {
        return this.C;
    }

    public float o() {
        return this.D;
    }

    RectF p(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i7 = this.f1570e;
        if (i7 == -1 || (viewFindViewById = viewGroup.findViewById(i7)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    int q() {
        return this.f1570e;
    }

    boolean r() {
        return this.f1580o;
    }

    void s(MotionEvent motionEvent, MotionLayout.g gVar, int i7, q qVar) {
        int i8;
        if (this.f1577l) {
            t(motionEvent, gVar, i7, qVar);
            return;
        }
        gVar.b(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f1583r = motionEvent.getRawX();
            this.f1584s = motionEvent.getRawY();
            this.f1580o = false;
            return;
        }
        if (action != 1) {
            if (action != 2) {
                return;
            }
            float rawY = motionEvent.getRawY() - this.f1584s;
            float rawX = motionEvent.getRawX() - this.f1583r;
            if (Math.abs((this.f1578m * rawX) + (this.f1579n * rawY)) > this.f1591z || this.f1580o) {
                float progress = this.f1585t.getProgress();
                if (!this.f1580o) {
                    this.f1580o = true;
                    this.f1585t.setProgress(progress);
                }
                int i9 = this.f1569d;
                if (i9 != -1) {
                    this.f1585t.l0(i9, progress, this.f1573h, this.f1572g, this.f1581p);
                } else {
                    float fMin = Math.min(this.f1585t.getWidth(), this.f1585t.getHeight());
                    float[] fArr = this.f1581p;
                    fArr[1] = this.f1579n * fMin;
                    fArr[0] = fMin * this.f1578m;
                }
                float f7 = this.f1578m;
                float[] fArr2 = this.f1581p;
                if (Math.abs(((f7 * fArr2[0]) + (this.f1579n * fArr2[1])) * this.f1589x) < 0.01d) {
                    float[] fArr3 = this.f1581p;
                    fArr3[0] = 0.01f;
                    fArr3[1] = 0.01f;
                }
                float fMax = Math.max(Math.min(progress + (this.f1578m != 0.0f ? rawX / this.f1581p[0] : rawY / this.f1581p[1]), 1.0f), 0.0f);
                if (this.f1568c == 6) {
                    fMax = Math.max(fMax, 0.01f);
                }
                if (this.f1568c == 7) {
                    fMax = Math.min(fMax, 0.99f);
                }
                float progress2 = this.f1585t.getProgress();
                if (fMax != progress2) {
                    if (progress2 == 0.0f || progress2 == 1.0f) {
                        this.f1585t.f0(progress2 == 0.0f);
                    }
                    this.f1585t.setProgress(fMax);
                    gVar.e(1000);
                    this.f1585t.G = this.f1578m != 0.0f ? gVar.d() / this.f1581p[0] : gVar.c() / this.f1581p[1];
                } else {
                    this.f1585t.G = 0.0f;
                }
                this.f1583r = motionEvent.getRawX();
                this.f1584s = motionEvent.getRawY();
                return;
            }
            return;
        }
        this.f1580o = false;
        gVar.e(1000);
        float fD = gVar.d();
        float fC = gVar.c();
        float progress3 = this.f1585t.getProgress();
        int i10 = this.f1569d;
        if (i10 != -1) {
            this.f1585t.l0(i10, progress3, this.f1573h, this.f1572g, this.f1581p);
        } else {
            float fMin2 = Math.min(this.f1585t.getWidth(), this.f1585t.getHeight());
            float[] fArr4 = this.f1581p;
            fArr4[1] = this.f1579n * fMin2;
            fArr4[0] = fMin2 * this.f1578m;
        }
        float f8 = this.f1578m;
        float[] fArr5 = this.f1581p;
        float f9 = fArr5[0];
        float f10 = fArr5[1];
        float fAbs = f8 != 0.0f ? fD / fArr5[0] : fC / fArr5[1];
        float f11 = !Float.isNaN(fAbs) ? (fAbs / 3.0f) + progress3 : progress3;
        if (f11 == 0.0f || f11 == 1.0f || (i8 = this.f1568c) == 3) {
            if (0.0f >= f11 || 1.0f <= f11) {
                this.f1585t.setState(MotionLayout.k.FINISHED);
                return;
            }
            return;
        }
        float f12 = ((double) f11) < 0.5d ? 0.0f : 1.0f;
        if (i8 == 6) {
            if (progress3 + fAbs < 0.0f) {
                fAbs = Math.abs(fAbs);
            }
            f12 = 1.0f;
        }
        if (this.f1568c == 7) {
            if (progress3 + fAbs > 1.0f) {
                fAbs = -Math.abs(fAbs);
            }
            f12 = 0.0f;
        }
        this.f1585t.B0(this.f1568c, f12, fAbs);
        if (0.0f >= progress3 || 1.0f <= progress3) {
            this.f1585t.setState(MotionLayout.k.FINISHED);
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
        if (Float.isNaN(this.f1578m)) {
            return "rotation";
        }
        return this.f1578m + " , " + this.f1579n;
    }

    void u(float f7, float f8) {
        float progress = this.f1585t.getProgress();
        if (!this.f1580o) {
            this.f1580o = true;
            this.f1585t.setProgress(progress);
        }
        this.f1585t.l0(this.f1569d, progress, this.f1573h, this.f1572g, this.f1581p);
        float f9 = this.f1578m;
        float[] fArr = this.f1581p;
        if (Math.abs((f9 * fArr[0]) + (this.f1579n * fArr[1])) < 0.01d) {
            float[] fArr2 = this.f1581p;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f10 = this.f1578m;
        float fMax = Math.max(Math.min(progress + (f10 != 0.0f ? (f7 * f10) / this.f1581p[0] : (f8 * this.f1579n) / this.f1581p[1]), 1.0f), 0.0f);
        if (fMax != this.f1585t.getProgress()) {
            this.f1585t.setProgress(fMax);
        }
    }

    void v(float f7, float f8) {
        this.f1580o = false;
        float progress = this.f1585t.getProgress();
        this.f1585t.l0(this.f1569d, progress, this.f1573h, this.f1572g, this.f1581p);
        float f9 = this.f1578m;
        float[] fArr = this.f1581p;
        float f10 = fArr[0];
        float f11 = this.f1579n;
        float f12 = fArr[1];
        float f13 = f9 != 0.0f ? (f7 * f9) / fArr[0] : (f8 * f11) / fArr[1];
        if (!Float.isNaN(f13)) {
            progress += f13 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z6 = progress != 1.0f;
            int i7 = this.f1568c;
            if ((i7 != 3) && z6) {
                this.f1585t.B0(i7, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f13);
            }
        }
    }

    void w(float f7, float f8) {
        this.f1583r = f7;
        this.f1584s = f8;
    }

    public void x(boolean z6) {
        if (z6) {
            float[][] fArr = H;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = G;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = H;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = G;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = G;
        int i7 = this.f1566a;
        this.f1573h = fArr5[i7][0];
        this.f1572g = fArr5[i7][1];
        int i8 = this.f1567b;
        float[][] fArr6 = H;
        if (i8 >= fArr6.length) {
            return;
        }
        this.f1578m = fArr6[i8][0];
        this.f1579n = fArr6[i8][1];
    }

    void y(float f7, float f8) {
        this.f1583r = f7;
        this.f1584s = f8;
        this.f1580o = false;
    }

    void z() {
        View viewFindViewById;
        int i7 = this.f1569d;
        if (i7 != -1) {
            viewFindViewById = this.f1585t.findViewById(i7);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + androidx.constraintlayout.motion.widget.a.c(this.f1585t.getContext(), this.f1569d));
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
}
