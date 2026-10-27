package defpackage;

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
import androidx.constraintlayout.widget.R$styleable;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: TouchResponse.java */
/* JADX INFO: loaded from: classes.dex */
public class j5 {
    public static final float[][] a = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    public static final float[][] b = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    public float o;
    public float p;
    public final MotionLayout q;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = -1;
    public int g = -1;
    public int h = -1;
    public float i = 0.5f;
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
        public void a(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        }
    }

    public j5(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.q = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    public float a(float f, float f2) {
        return (f * this.k) + (f2 * this.l);
    }

    public final void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index == R$styleable.OnSwipe_touchAnchorId) {
                this.f = typedArray.getResourceId(index, this.f);
            } else if (index == R$styleable.OnSwipe_touchAnchorSide) {
                int i2 = typedArray.getInt(index, this.c);
                this.c = i2;
                float[][] fArr = a;
                this.j = fArr[i2][0];
                this.i = fArr[i2][1];
            } else if (index == R$styleable.OnSwipe_dragDirection) {
                int i3 = typedArray.getInt(index, this.d);
                this.d = i3;
                float[][] fArr2 = b;
                this.k = fArr2[i3][0];
                this.l = fArr2[i3][1];
            } else if (index == R$styleable.OnSwipe_maxVelocity) {
                this.r = typedArray.getFloat(index, this.r);
            } else if (index == R$styleable.OnSwipe_maxAcceleration) {
                this.s = typedArray.getFloat(index, this.s);
            } else if (index == R$styleable.OnSwipe_moveWhenScrollAtTop) {
                this.t = typedArray.getBoolean(index, this.t);
            } else if (index == R$styleable.OnSwipe_dragScale) {
                this.u = typedArray.getFloat(index, this.u);
            } else if (index == R$styleable.OnSwipe_dragThreshold) {
                this.w = typedArray.getFloat(index, this.w);
            } else if (index == R$styleable.OnSwipe_touchRegionId) {
                this.g = typedArray.getResourceId(index, this.g);
            } else if (index == R$styleable.OnSwipe_onTouchUp) {
                this.e = typedArray.getInt(index, this.e);
            } else if (index == R$styleable.OnSwipe_nestedScrollFlags) {
                this.v = typedArray.getInteger(index, 0);
            } else if (index == R$styleable.OnSwipe_limitBoundsTo) {
                this.h = typedArray.getResourceId(index, 0);
            }
        }
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.OnSwipe);
        b(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int d() {
        return this.v;
    }

    public RectF e(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i = this.h;
        if (i == -1 || (viewFindViewById = viewGroup.findViewById(i)) == null) {
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

    public float i(float f, float f2) {
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

    public RectF j(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i = this.g;
        if (i == -1 || (viewFindViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public int k() {
        return this.g;
    }

    public void l(MotionEvent motionEvent, MotionLayout.f fVar, int i, g5 g5Var) {
        int i2;
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
            float f4 = f != 0.0f ? fC / fArr2[0] : fB / fArr2[1];
            float f5 = !Float.isNaN(f4) ? (f4 / 3.0f) + progress : progress;
            if (f5 == 0.0f || f5 == 1.0f || (i2 = this.e) == 3) {
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
                this.q.B = this.k != 0.0f ? fVar.c() / this.n[0] : fVar.b() / this.n[1];
            } else {
                this.q.B = 0.0f;
            }
            this.o = motionEvent.getRawX();
            this.p = motionEvent.getRawY();
        }
    }

    public void m(float f, float f2) {
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

    public void n(float f, float f2) {
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
            int i = this.e;
            if ((i != 3) && z) {
                this.q.r0(i, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f7);
            }
        }
    }

    public void o(float f, float f2) {
        this.o = f;
        this.p = f2;
    }

    public void p(boolean z) {
        if (z) {
            float[][] fArr = b;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = a;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = b;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = a;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = a;
        int i = this.c;
        this.j = fArr5[i][0];
        this.i = fArr5[i][1];
        float[][] fArr6 = b;
        int i2 = this.d;
        this.k = fArr6[i2][0];
        this.l = fArr6[i2][1];
    }

    public void q(float f, float f2) {
        this.o = f;
        this.p = f2;
        this.m = false;
    }

    public void r() {
        View viewFindViewById;
        int i = this.f;
        if (i != -1) {
            viewFindViewById = this.q.findViewById(i);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + q4.b(this.q.getContext(), this.f));
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
