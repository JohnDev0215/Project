package u;

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
    public static final float[][] f5311a = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[][] f5312b = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f5325o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f5326p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final MotionLayout f5327q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5313c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5314d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5315e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5316f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5317g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5318h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f5319i = 0.5f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f5320j = 0.5f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5321k = 0.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f5322l = 1.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f5323m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float[] f5324n = new float[2];

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5328r = 4.0f;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f5329s = 1.2f;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f5330t = true;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f5331u = 1.0f;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f5332v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f5333w = 10.0f;

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
        public void a(NestedScrollView nestedScrollView, int i3, int i4, int i5, int i6) {
        }
    }

    public t(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.f5327q = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    public float a(float f3, float f4) {
        return (f3 * this.f5321k) + (f4 * this.f5322l);
    }

    public final void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = typedArray.getIndex(i3);
            if (index == R$styleable.OnSwipe_touchAnchorId) {
                this.f5316f = typedArray.getResourceId(index, this.f5316f);
            } else if (index == R$styleable.OnSwipe_touchAnchorSide) {
                int i4 = typedArray.getInt(index, this.f5313c);
                this.f5313c = i4;
                float[][] fArr = f5311a;
                this.f5320j = fArr[i4][0];
                this.f5319i = fArr[i4][1];
            } else if (index == R$styleable.OnSwipe_dragDirection) {
                int i5 = typedArray.getInt(index, this.f5314d);
                this.f5314d = i5;
                float[][] fArr2 = f5312b;
                this.f5321k = fArr2[i5][0];
                this.f5322l = fArr2[i5][1];
            } else if (index == R$styleable.OnSwipe_maxVelocity) {
                this.f5328r = typedArray.getFloat(index, this.f5328r);
            } else if (index == R$styleable.OnSwipe_maxAcceleration) {
                this.f5329s = typedArray.getFloat(index, this.f5329s);
            } else if (index == R$styleable.OnSwipe_moveWhenScrollAtTop) {
                this.f5330t = typedArray.getBoolean(index, this.f5330t);
            } else if (index == R$styleable.OnSwipe_dragScale) {
                this.f5331u = typedArray.getFloat(index, this.f5331u);
            } else if (index == R$styleable.OnSwipe_dragThreshold) {
                this.f5333w = typedArray.getFloat(index, this.f5333w);
            } else if (index == R$styleable.OnSwipe_touchRegionId) {
                this.f5317g = typedArray.getResourceId(index, this.f5317g);
            } else if (index == R$styleable.OnSwipe_onTouchUp) {
                this.f5315e = typedArray.getInt(index, this.f5315e);
            } else if (index == R$styleable.OnSwipe_nestedScrollFlags) {
                this.f5332v = typedArray.getInteger(index, 0);
            } else if (index == R$styleable.OnSwipe_limitBoundsTo) {
                this.f5318h = typedArray.getResourceId(index, 0);
            }
        }
    }

    public final void c(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.OnSwipe);
        b(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int d() {
        return this.f5332v;
    }

    public RectF e(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i3 = this.f5318h;
        if (i3 == -1 || (viewFindViewById = viewGroup.findViewById(i3)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public float f() {
        return this.f5329s;
    }

    public float g() {
        return this.f5328r;
    }

    public boolean h() {
        return this.f5330t;
    }

    public float i(float f3, float f4) {
        this.f5327q.d0(this.f5316f, this.f5327q.getProgress(), this.f5320j, this.f5319i, this.f5324n);
        float f5 = this.f5321k;
        if (f5 != 0.0f) {
            float[] fArr = this.f5324n;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f3 * f5) / fArr[0];
        }
        float[] fArr2 = this.f5324n;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f4 * this.f5322l) / fArr2[1];
    }

    public RectF j(ViewGroup viewGroup, RectF rectF) {
        View viewFindViewById;
        int i3 = this.f5317g;
        if (i3 == -1 || (viewFindViewById = viewGroup.findViewById(i3)) == null) {
            return null;
        }
        rectF.set(viewFindViewById.getLeft(), viewFindViewById.getTop(), viewFindViewById.getRight(), viewFindViewById.getBottom());
        return rectF;
    }

    public int k() {
        return this.f5317g;
    }

    public void l(MotionEvent motionEvent, MotionLayout.f fVar, int i3, q qVar) {
        int i4;
        fVar.a(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f5325o = motionEvent.getRawX();
            this.f5326p = motionEvent.getRawY();
            this.f5323m = false;
            return;
        }
        if (action == 1) {
            this.f5323m = false;
            fVar.e(1000);
            float fC = fVar.c();
            float fB = fVar.b();
            float progress = this.f5327q.getProgress();
            int i5 = this.f5316f;
            if (i5 != -1) {
                this.f5327q.d0(i5, progress, this.f5320j, this.f5319i, this.f5324n);
            } else {
                float fMin = Math.min(this.f5327q.getWidth(), this.f5327q.getHeight());
                float[] fArr = this.f5324n;
                fArr[1] = this.f5322l * fMin;
                fArr[0] = fMin * this.f5321k;
            }
            float f3 = this.f5321k;
            float[] fArr2 = this.f5324n;
            float f4 = fArr2[0];
            float f5 = fArr2[1];
            float f6 = f3 != 0.0f ? fC / fArr2[0] : fB / fArr2[1];
            float f7 = !Float.isNaN(f6) ? (f6 / 3.0f) + progress : progress;
            if (f7 == 0.0f || f7 == 1.0f || (i4 = this.f5315e) == 3) {
                if (0.0f >= f7 || 1.0f <= f7) {
                    this.f5327q.setState(MotionLayout.TransitionState.FINISHED);
                    return;
                }
                return;
            }
            this.f5327q.r0(i4, ((double) f7) < 0.5d ? 0.0f : 1.0f, f6);
            if (0.0f >= progress || 1.0f <= progress) {
                this.f5327q.setState(MotionLayout.TransitionState.FINISHED);
                return;
            }
            return;
        }
        if (action != 2) {
            return;
        }
        float rawY = motionEvent.getRawY() - this.f5326p;
        float rawX = motionEvent.getRawX() - this.f5325o;
        if (Math.abs((this.f5321k * rawX) + (this.f5322l * rawY)) > this.f5333w || this.f5323m) {
            float progress2 = this.f5327q.getProgress();
            if (!this.f5323m) {
                this.f5323m = true;
                this.f5327q.setProgress(progress2);
            }
            int i6 = this.f5316f;
            if (i6 != -1) {
                this.f5327q.d0(i6, progress2, this.f5320j, this.f5319i, this.f5324n);
            } else {
                float fMin2 = Math.min(this.f5327q.getWidth(), this.f5327q.getHeight());
                float[] fArr3 = this.f5324n;
                fArr3[1] = this.f5322l * fMin2;
                fArr3[0] = fMin2 * this.f5321k;
            }
            float f8 = this.f5321k;
            float[] fArr4 = this.f5324n;
            if (Math.abs(((f8 * fArr4[0]) + (this.f5322l * fArr4[1])) * this.f5331u) < 0.01d) {
                float[] fArr5 = this.f5324n;
                fArr5[0] = 0.01f;
                fArr5[1] = 0.01f;
            }
            float fMax = Math.max(Math.min(progress2 + (this.f5321k != 0.0f ? rawX / this.f5324n[0] : rawY / this.f5324n[1]), 1.0f), 0.0f);
            if (fMax != this.f5327q.getProgress()) {
                this.f5327q.setProgress(fMax);
                fVar.e(1000);
                this.f5327q.B = this.f5321k != 0.0f ? fVar.c() / this.f5324n[0] : fVar.b() / this.f5324n[1];
            } else {
                this.f5327q.B = 0.0f;
            }
            this.f5325o = motionEvent.getRawX();
            this.f5326p = motionEvent.getRawY();
        }
    }

    public void m(float f3, float f4) {
        float progress = this.f5327q.getProgress();
        if (!this.f5323m) {
            this.f5323m = true;
            this.f5327q.setProgress(progress);
        }
        this.f5327q.d0(this.f5316f, progress, this.f5320j, this.f5319i, this.f5324n);
        float f5 = this.f5321k;
        float[] fArr = this.f5324n;
        if (Math.abs((f5 * fArr[0]) + (this.f5322l * fArr[1])) < 0.01d) {
            float[] fArr2 = this.f5324n;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f6 = this.f5321k;
        float fMax = Math.max(Math.min(progress + (f6 != 0.0f ? (f3 * f6) / this.f5324n[0] : (f4 * this.f5322l) / this.f5324n[1]), 1.0f), 0.0f);
        if (fMax != this.f5327q.getProgress()) {
            this.f5327q.setProgress(fMax);
        }
    }

    public void n(float f3, float f4) {
        this.f5323m = false;
        float progress = this.f5327q.getProgress();
        this.f5327q.d0(this.f5316f, progress, this.f5320j, this.f5319i, this.f5324n);
        float f5 = this.f5321k;
        float[] fArr = this.f5324n;
        float f6 = fArr[0];
        float f7 = this.f5322l;
        float f8 = fArr[1];
        float f9 = f5 != 0.0f ? (f3 * f5) / fArr[0] : (f4 * f7) / fArr[1];
        if (!Float.isNaN(f9)) {
            progress += f9 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z3 = progress != 1.0f;
            int i3 = this.f5315e;
            if ((i3 != 3) && z3) {
                this.f5327q.r0(i3, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f9);
            }
        }
    }

    public void o(float f3, float f4) {
        this.f5325o = f3;
        this.f5326p = f4;
    }

    public void p(boolean z3) {
        if (z3) {
            float[][] fArr = f5312b;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = f5311a;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = f5312b;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = f5311a;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = f5311a;
        int i3 = this.f5313c;
        this.f5320j = fArr5[i3][0];
        this.f5319i = fArr5[i3][1];
        float[][] fArr6 = f5312b;
        int i4 = this.f5314d;
        this.f5321k = fArr6[i4][0];
        this.f5322l = fArr6[i4][1];
    }

    public void q(float f3, float f4) {
        this.f5325o = f3;
        this.f5326p = f4;
        this.f5323m = false;
    }

    public void r() {
        View viewFindViewById;
        int i3 = this.f5316f;
        if (i3 != -1) {
            viewFindViewById = this.f5327q.findViewById(i3);
            if (viewFindViewById == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + u.a.b(this.f5327q.getContext(), this.f5316f));
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
        return this.f5321k + " , " + this.f5322l;
    }
}
