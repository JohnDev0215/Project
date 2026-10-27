package b.f.a.b;

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
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[][] f2156a = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[][] f2157b = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    public float o;
    public float p;
    public final MotionLayout q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2158c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2159d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2160e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2161f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2162g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2163h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f2164i = 0.5f;
    public float j = 0.5f;
    public float k = 0.0f;
    public float l = 1.0f;
    public boolean m = false;
    public float[] n = new float[2];
    public float r = 4.0f;
    public float s = 1.2f;
    public boolean t = true;
    public float u = 1.0f;
    public int v = 0;
    public float w = 10.0f;

    /* JADX INFO: compiled from: TouchResponse.java */
    public class a implements View.OnTouchListener {
        public a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    /* JADX INFO: compiled from: TouchResponse.java */
    public class b implements NestedScrollView.b {
        public b() {
        }

        @Override // androidx.core.widget.NestedScrollView.b
        public void a(NestedScrollView nestedScrollView, int i2, int i3, int i4, int i5) {
        }
    }

    public t(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.q = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    public float a(float f2, float f3) {
        return (f2 * this.k) + (f3 * this.l);
    }

    public final void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (index == b.f.c.e.OnSwipe_touchAnchorId) {
                this.f2161f = typedArray.getResourceId(index, this.f2161f);
            } else if (index == b.f.c.e.OnSwipe_touchAnchorSide) {
                int i3 = typedArray.getInt(index, this.f2158c);
                this.f2158c = i3;
                float[][] fArr = f2156a;
                this.j = fArr[i3][0];
                this.f2164i = fArr[i3][1];
            } else if (index == b.f.c.e.OnSwipe_dragDirection) {
                int i4 = typedArray.getInt(index, this.f2159d);
                this.f2159d = i4;
                float[][] fArr2 = f2157b;
                this.k = fArr2[i4][0];
                this.l = fArr2[i4][1];
            } else if (index == b.f.c.e.OnSwipe_maxVelocity) {
                this.r = typedArray.getFloat(index, this.r);
            } else if (index == b.f.c.e.OnSwipe_maxAcceleration) {
                this.s = typedArray.getFloat(index, this.s);
            } else if (index == b.f.c.e.OnSwipe_moveWhenScrollAtTop) {
                this.t = typedArray.getBoolean(index, this.t);
            } else if (index == b.f.c.e.OnSwipe_dragScale) {
                this.u = typedArray.getFloat(index, this.u);
            } else if (index == b.f.c.e.OnSwipe_dragThreshold) {
                this.w = typedArray.getFloat(index, this.w);
            } else if (index == b.f.c.e.OnSwipe_touchRegionId) {
                this.f2162g = typedArray.getResourceId(index, this.f2162g);
            } else if (index == b.f.c.e.OnSwipe_onTouchUp) {
                this.f2160e = typedArray.getInt(index, this.f2160e);
            } else if (index == b.f.c.e.OnSwipe_nestedScrollFlags) {
                this.v = typedArray.getInteger(index, 0);
            } else if (index == b.f.c.e.OnSwipe_limitBoundsTo) {
                this.f2163h = typedArray.getResourceId(index, 0);
            }
        }
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.f.c.e.OnSwipe);
        b(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int d() {
        return this.v;
    }

    public RectF e(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i2 = this.f2163h;
        if (i2 == -1 || (viewFindViewById = viewGroup.findViewById(i2)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public float f() {
        return this.s;
    }

    public float g() {
        return this.r;
    }

    public boolean h() {
        return this.t;
    }

    public float i(float f2, float f3) {
        this.q.d0(this.f2161f, this.q.getProgress(), this.j, this.f2164i, this.n);
        float f4 = this.k;
        if (f4 != 0.0f) {
            float[] fArr = this.n;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f2 * f4) / fArr[0];
        }
        float[] fArr2 = this.n;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f3 * this.l) / fArr2[1];
    }

    public RectF j(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i2 = this.f2162g;
        if (i2 == -1 || (viewFindViewById = viewGroup.findViewById(i2)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public int k() {
        return this.f2162g;
    }

    public void l(MotionEvent motionEvent, MotionLayout.f fVar, int i2, q qVar) {
        int i3;
        fVar.a(motionEvent);
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
            float fC = fVar.c();
            float fB = fVar.b();
            float progress = this.q.getProgress();
            int i4 = this.f2161f;
            if (i4 != -1) {
                this.q.d0(i4, progress, this.j, this.f2164i, this.n);
            } else {
                float fMin = Math.min(this.q.getWidth(), this.q.getHeight());
                float[] fArr = this.n;
                fArr[1] = this.l * fMin;
                fArr[0] = fMin * this.k;
            }
            float f2 = this.k;
            float[] fArr2 = this.n;
            float f3 = fArr2[0];
            float f4 = fArr2[1];
            float f5 = f2 != 0.0f ? fC / fArr2[0] : fB / fArr2[1];
            float f6 = !Float.isNaN(f5) ? (f5 / 3.0f) + progress : progress;
            if (f6 == 0.0f || f6 == 1.0f || (i3 = this.f2160e) == 3) {
                if (0.0f >= f6 || 1.0f <= f6) {
                    this.q.setState(MotionLayout.TransitionState.FINISHED);
                    return;
                }
                return;
            }
            this.q.r0(i3, ((double) f6) < 0.5d ? 0.0f : 1.0f, f5);
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
            int i5 = this.f2161f;
            if (i5 != -1) {
                this.q.d0(i5, progress2, this.j, this.f2164i, this.n);
            } else {
                float fMin2 = Math.min(this.q.getWidth(), this.q.getHeight());
                float[] fArr3 = this.n;
                fArr3[1] = this.l * fMin2;
                fArr3[0] = fMin2 * this.k;
            }
            float f7 = this.k;
            float[] fArr4 = this.n;
            if (Math.abs(((f7 * fArr4[0]) + (this.l * fArr4[1])) * this.u) < 0.01d) {
                float[] fArr5 = this.n;
                fArr5[0] = 0.01f;
                fArr5[1] = 0.01f;
            }
            float fMax = Math.max(Math.min(progress2 + (this.k != 0.0f ? rawX / this.n[0] : rawY / this.n[1]), 1.0f), 0.0f);
            if (fMax != this.q.getProgress()) {
                this.q.setProgress(fMax);
                fVar.e(1000);
                this.q.B = this.k != 0.0f ? fVar.c() / this.n[0] : fVar.b() / this.n[1];
            } else {
                this.q.B = 0.0f;
            }
            this.o = motionEvent.getRawX();
            this.p = motionEvent.getRawY();
        }
    }

    public void m(float f2, float f3) {
        float progress = this.q.getProgress();
        if (!this.m) {
            this.m = true;
            this.q.setProgress(progress);
        }
        this.q.d0(this.f2161f, progress, this.j, this.f2164i, this.n);
        float f4 = this.k;
        float[] fArr = this.n;
        if (Math.abs((f4 * fArr[0]) + (this.l * fArr[1])) < 0.01d) {
            float[] fArr2 = this.n;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f5 = this.k;
        float fMax = Math.max(Math.min(progress + (f5 != 0.0f ? (f2 * f5) / this.n[0] : (f3 * this.l) / this.n[1]), 1.0f), 0.0f);
        if (fMax != this.q.getProgress()) {
            this.q.setProgress(fMax);
        }
    }

    public void n(float f2, float f3) {
        this.m = false;
        float progress = this.q.getProgress();
        this.q.d0(this.f2161f, progress, this.j, this.f2164i, this.n);
        float f4 = this.k;
        float[] fArr = this.n;
        float f5 = fArr[0];
        float f6 = this.l;
        float f7 = fArr[1];
        float f8 = f4 != 0.0f ? (f2 * f4) / fArr[0] : (f3 * f6) / fArr[1];
        if (!Float.isNaN(f8)) {
            progress += f8 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z = progress != 1.0f;
            int i2 = this.f2160e;
            if ((i2 != 3) && z) {
                this.q.r0(i2, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f8);
            }
        }
    }

    public void o(float f2, float f3) {
        this.o = f2;
        this.p = f3;
    }

    public void p(boolean z) {
        if (z) {
            float[][] fArr = f2157b;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = f2156a;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = f2157b;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = f2156a;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = f2156a;
        int i2 = this.f2158c;
        this.j = fArr5[i2][0];
        this.f2164i = fArr5[i2][1];
        float[][] fArr6 = f2157b;
        int i3 = this.f2159d;
        this.k = fArr6[i3][0];
        this.l = fArr6[i3][1];
    }

    public void q(float f2, float f3) {
        this.o = f2;
        this.p = f3;
        this.m = false;
    }

    public void r() {
        View viewFindViewById;
        int i2 = this.f2161f;
        if (i2 != -1) {
            viewFindViewById = this.q.findViewById(i2);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + b.f.a.b.a.b(this.q.getContext(), this.f2161f));
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
