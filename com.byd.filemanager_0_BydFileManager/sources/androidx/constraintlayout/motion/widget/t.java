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
class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final float[][] f685a = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final float[][] f686b = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    private float o;
    private float p;
    private final MotionLayout q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f687c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f688d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f689e = 0;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    private float i = 0.5f;
    private float j = 0.5f;
    private float k = 0.0f;
    private float l = 1.0f;
    private boolean m = false;
    private float[] n = new float[2];
    private float r = 4.0f;
    private float s = 1.2f;
    private boolean t = true;
    private float u = 1.0f;
    private int v = 0;
    private float w = 10.0f;

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

    t(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.q = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    private void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index == androidx.constraintlayout.widget.e.i7) {
                this.f = typedArray.getResourceId(index, this.f);
            } else if (index == androidx.constraintlayout.widget.e.j7) {
                int i2 = typedArray.getInt(index, this.f687c);
                this.f687c = i2;
                float[][] fArr = f685a;
                this.j = fArr[i2][0];
                this.i = fArr[i2][1];
            } else if (index == androidx.constraintlayout.widget.e.Z6) {
                int i3 = typedArray.getInt(index, this.f688d);
                this.f688d = i3;
                float[][] fArr2 = f686b;
                this.k = fArr2[i3][0];
                this.l = fArr2[i3][1];
            } else if (index == androidx.constraintlayout.widget.e.e7) {
                this.r = typedArray.getFloat(index, this.r);
            } else if (index == androidx.constraintlayout.widget.e.d7) {
                this.s = typedArray.getFloat(index, this.s);
            } else if (index == androidx.constraintlayout.widget.e.f7) {
                this.t = typedArray.getBoolean(index, this.t);
            } else if (index == androidx.constraintlayout.widget.e.a7) {
                this.u = typedArray.getFloat(index, this.u);
            } else if (index == androidx.constraintlayout.widget.e.b7) {
                this.w = typedArray.getFloat(index, this.w);
            } else if (index == androidx.constraintlayout.widget.e.k7) {
                this.g = typedArray.getResourceId(index, this.g);
            } else if (index == androidx.constraintlayout.widget.e.h7) {
                this.f689e = typedArray.getInt(index, this.f689e);
            } else if (index == androidx.constraintlayout.widget.e.g7) {
                this.v = typedArray.getInteger(index, 0);
            } else if (index == androidx.constraintlayout.widget.e.c7) {
                this.h = typedArray.getResourceId(index, 0);
            }
        }
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.Y6);
        b(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    float a(float f, float f2) {
        return (f * this.k) + (f2 * this.l);
    }

    public int d() {
        return this.v;
    }

    RectF e(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i = this.h;
        if (i == -1 || (viewFindViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    float f() {
        return this.s;
    }

    public float g() {
        return this.r;
    }

    boolean h() {
        return this.t;
    }

    float i(float f, float f2) {
        this.q.d0(this.f, this.q.getProgress(), this.j, this.i, this.n);
        float f3 = this.k;
        if (f3 != 0.0f) {
            float[] fArr = this.n;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f * f3) / fArr[0];
        }
        float[] fArr2 = this.n;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f2 * this.l) / fArr2[1];
    }

    RectF j(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i = this.g;
        if (i == -1 || (viewFindViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    int k() {
        return this.g;
    }

    void l(MotionEvent motionEvent, MotionLayout.f fVar, int i, q qVar) {
        int i2;
        fVar.b(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.o = motionEvent.getRawX();
            this.p = motionEvent.getRawY();
            this.m = false;
            return;
        }
        if (action == 1) {
            this.m = false;
            fVar.e(1000);
            float fD = fVar.d();
            float fC = fVar.c();
            float progress = this.q.getProgress();
            int i3 = this.f;
            if (i3 != -1) {
                this.q.d0(i3, progress, this.j, this.i, this.n);
            } else {
                float fMin = Math.min(this.q.getWidth(), this.q.getHeight());
                float[] fArr = this.n;
                fArr[1] = this.l * fMin;
                fArr[0] = fMin * this.k;
            }
            float f = this.k;
            float[] fArr2 = this.n;
            float f2 = fArr2[0];
            float f3 = fArr2[1];
            float f4 = f != 0.0f ? fD / fArr2[0] : fC / fArr2[1];
            float f5 = !Float.isNaN(f4) ? (f4 / 3.0f) + progress : progress;
            if (f5 == 0.0f || f5 == 1.0f || (i2 = this.f689e) == 3) {
                if (0.0f >= f5 || 1.0f <= f5) {
                    this.q.setState(MotionLayout.TransitionState.FINISHED);
                    return;
                }
                return;
            }
            this.q.r0(i2, ((double) f5) < 0.5d ? 0.0f : 1.0f, f4);
            if (0.0f >= progress || 1.0f <= progress) {
                this.q.setState(MotionLayout.TransitionState.FINISHED);
                return;
            }
            return;
        }
        if (action != 2) {
            return;
        }
        float rawY = motionEvent.getRawY() - this.p;
        float rawX = motionEvent.getRawX() - this.o;
        if (Math.abs((this.k * rawX) + (this.l * rawY)) > this.w || this.m) {
            float progress2 = this.q.getProgress();
            if (!this.m) {
                this.m = true;
                this.q.setProgress(progress2);
            }
            int i4 = this.f;
            if (i4 != -1) {
                this.q.d0(i4, progress2, this.j, this.i, this.n);
            } else {
                float fMin2 = Math.min(this.q.getWidth(), this.q.getHeight());
                float[] fArr3 = this.n;
                fArr3[1] = this.l * fMin2;
                fArr3[0] = fMin2 * this.k;
            }
            float f6 = this.k;
            float[] fArr4 = this.n;
            if (Math.abs(((f6 * fArr4[0]) + (this.l * fArr4[1])) * this.u) < 0.01d) {
                float[] fArr5 = this.n;
                fArr5[0] = 0.01f;
                fArr5[1] = 0.01f;
            }
            float fMax = Math.max(Math.min(progress2 + (this.k != 0.0f ? rawX / this.n[0] : rawY / this.n[1]), 1.0f), 0.0f);
            if (fMax != this.q.getProgress()) {
                this.q.setProgress(fMax);
                fVar.e(1000);
                this.q.B = this.k != 0.0f ? fVar.d() / this.n[0] : fVar.c() / this.n[1];
            } else {
                this.q.B = 0.0f;
            }
            this.o = motionEvent.getRawX();
            this.p = motionEvent.getRawY();
        }
    }

    void m(float f, float f2) {
        float progress = this.q.getProgress();
        if (!this.m) {
            this.m = true;
            this.q.setProgress(progress);
        }
        this.q.d0(this.f, progress, this.j, this.i, this.n);
        float f3 = this.k;
        float[] fArr = this.n;
        if (Math.abs((f3 * fArr[0]) + (this.l * fArr[1])) < 0.01d) {
            float[] fArr2 = this.n;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f4 = this.k;
        float fMax = Math.max(Math.min(progress + (f4 != 0.0f ? (f * f4) / this.n[0] : (f2 * this.l) / this.n[1]), 1.0f), 0.0f);
        if (fMax != this.q.getProgress()) {
            this.q.setProgress(fMax);
        }
    }

    void n(float f, float f2) {
        this.m = false;
        float progress = this.q.getProgress();
        this.q.d0(this.f, progress, this.j, this.i, this.n);
        float f3 = this.k;
        float[] fArr = this.n;
        float f4 = fArr[0];
        float f5 = this.l;
        float f6 = fArr[1];
        float f7 = f3 != 0.0f ? (f * f3) / fArr[0] : (f2 * f5) / fArr[1];
        if (!Float.isNaN(f7)) {
            progress += f7 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z = progress != 1.0f;
            int i = this.f689e;
            if ((i != 3) && z) {
                this.q.r0(i, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f7);
            }
        }
    }

    void o(float f, float f2) {
        this.o = f;
        this.p = f2;
    }

    public void p(boolean z) {
        if (z) {
            float[][] fArr = f686b;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = f685a;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = f686b;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = f685a;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = f685a;
        int i = this.f687c;
        this.j = fArr5[i][0];
        this.i = fArr5[i][1];
        float[][] fArr6 = f686b;
        int i2 = this.f688d;
        this.k = fArr6[i2][0];
        this.l = fArr6[i2][1];
    }

    void q(float f, float f2) {
        this.o = f;
        this.p = f2;
        this.m = false;
    }

    void r() {
        View viewFindViewById;
        int i = this.f;
        if (i != -1) {
            viewFindViewById = this.q.findViewById(i);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + androidx.constraintlayout.motion.widget.a.b(this.q.getContext(), this.f));
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

    public String toString() {
        return this.k + " , " + this.l;
    }
}
