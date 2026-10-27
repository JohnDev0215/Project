package x;

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
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[][] f9023a = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[][] f9024b = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f9037o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f9038p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final MotionLayout f9039q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f9025c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f9026d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9027e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9028f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9029g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f9030h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f9031i = 0.5f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f9032j = 0.5f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f9033k = 0.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f9034l = 1.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f9035m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float[] f9036n = new float[2];

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f9040r = 4.0f;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f9041s = 1.2f;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f9042t = true;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f9043u = 1.0f;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f9044v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f9045w = 10.0f;

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
        public void a(NestedScrollView nestedScrollView, int i8, int i9, int i10, int i11) {
        }
    }

    public t(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.f9039q = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    public float a(float f8, float f9) {
        return (f8 * this.f9033k) + (f9 * this.f9034l);
    }

    public final void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i8 = 0; i8 < indexCount; i8++) {
            int index = typedArray.getIndex(i8);
            if (index == R$styleable.OnSwipe_touchAnchorId) {
                this.f9028f = typedArray.getResourceId(index, this.f9028f);
            } else if (index == R$styleable.OnSwipe_touchAnchorSide) {
                int i9 = typedArray.getInt(index, this.f9025c);
                this.f9025c = i9;
                float[][] fArr = f9023a;
                this.f9032j = fArr[i9][0];
                this.f9031i = fArr[i9][1];
            } else if (index == R$styleable.OnSwipe_dragDirection) {
                int i10 = typedArray.getInt(index, this.f9026d);
                this.f9026d = i10;
                float[][] fArr2 = f9024b;
                this.f9033k = fArr2[i10][0];
                this.f9034l = fArr2[i10][1];
            } else if (index == R$styleable.OnSwipe_maxVelocity) {
                this.f9040r = typedArray.getFloat(index, this.f9040r);
            } else if (index == R$styleable.OnSwipe_maxAcceleration) {
                this.f9041s = typedArray.getFloat(index, this.f9041s);
            } else if (index == R$styleable.OnSwipe_moveWhenScrollAtTop) {
                this.f9042t = typedArray.getBoolean(index, this.f9042t);
            } else if (index == R$styleable.OnSwipe_dragScale) {
                this.f9043u = typedArray.getFloat(index, this.f9043u);
            } else if (index == R$styleable.OnSwipe_dragThreshold) {
                this.f9045w = typedArray.getFloat(index, this.f9045w);
            } else if (index == R$styleable.OnSwipe_touchRegionId) {
                this.f9029g = typedArray.getResourceId(index, this.f9029g);
            } else if (index == R$styleable.OnSwipe_onTouchUp) {
                this.f9027e = typedArray.getInt(index, this.f9027e);
            } else if (index == R$styleable.OnSwipe_nestedScrollFlags) {
                this.f9044v = typedArray.getInteger(index, 0);
            } else if (index == R$styleable.OnSwipe_limitBoundsTo) {
                this.f9030h = typedArray.getResourceId(index, 0);
            }
        }
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.OnSwipe);
        b(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int d() {
        return this.f9044v;
    }

    public RectF e(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i8 = this.f9030h;
        if (i8 == -1 || (viewFindViewById = viewGroup.findViewById(i8)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public float f() {
        return this.f9041s;
    }

    public float g() {
        return this.f9040r;
    }

    public boolean h() {
        return this.f9042t;
    }

    public float i(float f8, float f9) {
        this.f9039q.d0(this.f9028f, this.f9039q.getProgress(), this.f9032j, this.f9031i, this.f9036n);
        float f10 = this.f9033k;
        if (f10 != 0.0f) {
            float[] fArr = this.f9036n;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f8 * f10) / fArr[0];
        }
        float[] fArr2 = this.f9036n;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f9 * this.f9034l) / fArr2[1];
    }

    public RectF j(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i8 = this.f9029g;
        if (i8 == -1 || (viewFindViewById = viewGroup.findViewById(i8)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public int k() {
        return this.f9029g;
    }

    public void l(MotionEvent motionEvent, MotionLayout.f fVar, int i8, q qVar) {
        int i9;
        fVar.a(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f9037o = motionEvent.getRawX();
            this.f9038p = motionEvent.getRawY();
            this.f9035m = false;
            return;
        }
        if (action == 1) {
            this.f9035m = false;
            fVar.e(1000);
            float fC = fVar.c();
            float fB = fVar.b();
            float progress = this.f9039q.getProgress();
            int i10 = this.f9028f;
            if (i10 != -1) {
                this.f9039q.d0(i10, progress, this.f9032j, this.f9031i, this.f9036n);
            } else {
                float fMin = Math.min(this.f9039q.getWidth(), this.f9039q.getHeight());
                float[] fArr = this.f9036n;
                fArr[1] = this.f9034l * fMin;
                fArr[0] = fMin * this.f9033k;
            }
            float f8 = this.f9033k;
            float[] fArr2 = this.f9036n;
            float f9 = fArr2[0];
            float f10 = fArr2[1];
            float f11 = f8 != 0.0f ? fC / fArr2[0] : fB / fArr2[1];
            float f12 = !Float.isNaN(f11) ? (f11 / 3.0f) + progress : progress;
            if (f12 == 0.0f || f12 == 1.0f || (i9 = this.f9027e) == 3) {
                if (0.0f >= f12 || 1.0f <= f12) {
                    this.f9039q.setState(MotionLayout.TransitionState.FINISHED);
                    return;
                }
                return;
            }
            this.f9039q.r0(i9, ((double) f12) < 0.5d ? 0.0f : 1.0f, f11);
            if (0.0f >= progress || 1.0f <= progress) {
                this.f9039q.setState(MotionLayout.TransitionState.FINISHED);
                return;
            }
            return;
        }
        if (action != 2) {
            return;
        }
        float rawY = motionEvent.getRawY() - this.f9038p;
        float rawX = motionEvent.getRawX() - this.f9037o;
        if (Math.abs((this.f9033k * rawX) + (this.f9034l * rawY)) > this.f9045w || this.f9035m) {
            float progress2 = this.f9039q.getProgress();
            if (!this.f9035m) {
                this.f9035m = true;
                this.f9039q.setProgress(progress2);
            }
            int i11 = this.f9028f;
            if (i11 != -1) {
                this.f9039q.d0(i11, progress2, this.f9032j, this.f9031i, this.f9036n);
            } else {
                float fMin2 = Math.min(this.f9039q.getWidth(), this.f9039q.getHeight());
                float[] fArr3 = this.f9036n;
                fArr3[1] = this.f9034l * fMin2;
                fArr3[0] = fMin2 * this.f9033k;
            }
            float f13 = this.f9033k;
            float[] fArr4 = this.f9036n;
            if (Math.abs(((f13 * fArr4[0]) + (this.f9034l * fArr4[1])) * this.f9043u) < 0.01d) {
                float[] fArr5 = this.f9036n;
                fArr5[0] = 0.01f;
                fArr5[1] = 0.01f;
            }
            float fMax = Math.max(Math.min(progress2 + (this.f9033k != 0.0f ? rawX / this.f9036n[0] : rawY / this.f9036n[1]), 1.0f), 0.0f);
            if (fMax != this.f9039q.getProgress()) {
                this.f9039q.setProgress(fMax);
                fVar.e(1000);
                this.f9039q.D = this.f9033k != 0.0f ? fVar.c() / this.f9036n[0] : fVar.b() / this.f9036n[1];
            } else {
                this.f9039q.D = 0.0f;
            }
            this.f9037o = motionEvent.getRawX();
            this.f9038p = motionEvent.getRawY();
        }
    }

    public void m(float f8, float f9) {
        float progress = this.f9039q.getProgress();
        if (!this.f9035m) {
            this.f9035m = true;
            this.f9039q.setProgress(progress);
        }
        this.f9039q.d0(this.f9028f, progress, this.f9032j, this.f9031i, this.f9036n);
        float f10 = this.f9033k;
        float[] fArr = this.f9036n;
        if (Math.abs((f10 * fArr[0]) + (this.f9034l * fArr[1])) < 0.01d) {
            float[] fArr2 = this.f9036n;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f11 = this.f9033k;
        float fMax = Math.max(Math.min(progress + (f11 != 0.0f ? (f8 * f11) / this.f9036n[0] : (f9 * this.f9034l) / this.f9036n[1]), 1.0f), 0.0f);
        if (fMax != this.f9039q.getProgress()) {
            this.f9039q.setProgress(fMax);
        }
    }

    public void n(float f8, float f9) {
        this.f9035m = false;
        float progress = this.f9039q.getProgress();
        this.f9039q.d0(this.f9028f, progress, this.f9032j, this.f9031i, this.f9036n);
        float f10 = this.f9033k;
        float[] fArr = this.f9036n;
        float f11 = fArr[0];
        float f12 = this.f9034l;
        float f13 = fArr[1];
        float f14 = f10 != 0.0f ? (f8 * f10) / fArr[0] : (f9 * f12) / fArr[1];
        if (!Float.isNaN(f14)) {
            progress += f14 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z7 = progress != 1.0f;
            int i8 = this.f9027e;
            if ((i8 != 3) && z7) {
                this.f9039q.r0(i8, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f14);
            }
        }
    }

    public void o(float f8, float f9) {
        this.f9037o = f8;
        this.f9038p = f9;
    }

    public void p(boolean z7) {
        if (z7) {
            float[][] fArr = f9024b;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = f9023a;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = f9024b;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = f9023a;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = f9023a;
        int i8 = this.f9025c;
        this.f9032j = fArr5[i8][0];
        this.f9031i = fArr5[i8][1];
        float[][] fArr6 = f9024b;
        int i9 = this.f9026d;
        this.f9033k = fArr6[i9][0];
        this.f9034l = fArr6[i9][1];
    }

    public void q(float f8, float f9) {
        this.f9037o = f8;
        this.f9038p = f9;
        this.f9035m = false;
    }

    public void r() {
        View viewFindViewById;
        int i8 = this.f9028f;
        if (i8 != -1) {
            viewFindViewById = this.f9039q.findViewById(i8);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + x.a.b(this.f9039q.getContext(), this.f9028f));
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
        return this.f9033k + " , " + this.f9034l;
    }
}
