package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.q;
import androidx.constraintlayout.widget.b;
import androidx.constraintlayout.widget.e;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Carousel extends MotionHelper {
    private int A;
    private int B;
    private int C;
    private float D;
    private int E;
    private int F;
    int G;
    Runnable H;
    private b o;
    private final ArrayList<View> p;
    private int q;
    private int r;
    private MotionLayout s;
    private int t;
    private boolean u;
    private int v;
    private int w;
    private int x;
    private int y;
    private float z;

    class a implements Runnable {

        /* JADX INFO: renamed from: androidx.constraintlayout.helper.widget.Carousel$a$a, reason: collision with other inner class name */
        class RunnableC0017a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ float f889b;

            RunnableC0017a(float f) {
                this.f889b = f;
            }

            @Override // java.lang.Runnable
            public void run() {
                Carousel.this.s.B0(5, 1.0f, this.f889b);
            }
        }

        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Carousel.this.s.setProgress(0.0f);
            Carousel.this.R();
            Carousel.this.o.c(Carousel.this.r);
            float velocity = Carousel.this.s.getVelocity();
            if (Carousel.this.C != 2 || velocity <= Carousel.this.D || Carousel.this.r >= Carousel.this.o.a() - 1) {
                return;
            }
            float f = velocity * Carousel.this.z;
            if (Carousel.this.r != 0 || Carousel.this.q <= Carousel.this.r) {
                if (Carousel.this.r != Carousel.this.o.a() - 1 || Carousel.this.q >= Carousel.this.r) {
                    Carousel.this.s.post(new RunnableC0017a(f));
                }
            }
        }
    }

    public interface b {
        int a();

        void b(View view, int i);

        void c(int i);
    }

    public Carousel(Context context) {
        super(context);
        this.o = null;
        this.p = new ArrayList<>();
        this.q = 0;
        this.r = 0;
        this.t = -1;
        this.u = false;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = 0.9f;
        this.A = 0;
        this.B = 4;
        this.C = 1;
        this.D = 2.0f;
        this.E = -1;
        this.F = 200;
        this.G = -1;
        this.H = new a();
    }

    private boolean N(int i, boolean z) {
        MotionLayout motionLayout;
        q.b bVarN0;
        if (i == -1 || (motionLayout = this.s) == null || (bVarN0 = motionLayout.n0(i)) == null || z == bVarN0.C()) {
            return false;
        }
        bVarN0.F(z);
        return true;
    }

    private void O(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.q);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.t) {
                    this.t = typedArrayObtainStyledAttributes.getResourceId(index, this.t);
                } else if (index == e.r) {
                    this.v = typedArrayObtainStyledAttributes.getResourceId(index, this.v);
                } else if (index == e.u) {
                    this.w = typedArrayObtainStyledAttributes.getResourceId(index, this.w);
                } else if (index == e.s) {
                    this.B = typedArrayObtainStyledAttributes.getInt(index, this.B);
                } else if (index == e.x) {
                    this.x = typedArrayObtainStyledAttributes.getResourceId(index, this.x);
                } else if (index == e.w) {
                    this.y = typedArrayObtainStyledAttributes.getResourceId(index, this.y);
                } else if (index == e.z) {
                    this.z = typedArrayObtainStyledAttributes.getFloat(index, this.z);
                } else if (index == e.y) {
                    this.C = typedArrayObtainStyledAttributes.getInt(index, this.C);
                } else if (index == e.A) {
                    this.D = typedArrayObtainStyledAttributes.getFloat(index, this.D);
                } else if (index == e.v) {
                    this.u = typedArrayObtainStyledAttributes.getBoolean(index, this.u);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Q() {
        this.s.setTransitionDuration(this.F);
        if (this.E < this.r) {
            this.s.G0(this.x, this.F);
        } else {
            this.s.G0(this.y, this.F);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        b bVar = this.o;
        if (bVar == null || this.s == null || bVar.a() == 0) {
            return;
        }
        int size = this.p.size();
        for (int i = 0; i < size; i++) {
            View view = this.p.get(i);
            int iA = (this.r + i) - this.A;
            if (this.u) {
                if (iA < 0) {
                    int i2 = this.B;
                    if (i2 != 4) {
                        T(view, i2);
                    } else {
                        T(view, 0);
                    }
                    if (iA % this.o.a() == 0) {
                        this.o.b(view, 0);
                    } else {
                        b bVar2 = this.o;
                        bVar2.b(view, bVar2.a() + (iA % this.o.a()));
                    }
                } else if (iA >= this.o.a()) {
                    if (iA == this.o.a()) {
                        iA = 0;
                    } else if (iA > this.o.a()) {
                        iA %= this.o.a();
                    }
                    int i3 = this.B;
                    if (i3 != 4) {
                        T(view, i3);
                    } else {
                        T(view, 0);
                    }
                    this.o.b(view, iA);
                } else {
                    T(view, 0);
                    this.o.b(view, iA);
                }
            } else if (iA < 0) {
                T(view, this.B);
            } else if (iA >= this.o.a()) {
                T(view, this.B);
            } else {
                T(view, 0);
                this.o.b(view, iA);
            }
        }
        int i4 = this.E;
        if (i4 != -1 && i4 != this.r) {
            this.s.post(new Runnable() { // from class: androidx.constraintlayout.helper.widget.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f891b.Q();
                }
            });
        } else if (i4 == this.r) {
            this.E = -1;
        }
        if (this.v == -1 || this.w == -1) {
            Log.w("Carousel", "No backward or forward transitions defined for Carousel!");
            return;
        }
        if (this.u) {
            return;
        }
        int iA2 = this.o.a();
        if (this.r == 0) {
            N(this.v, false);
        } else {
            N(this.v, true);
            this.s.setTransition(this.v);
        }
        if (this.r == iA2 - 1) {
            N(this.w, false);
        } else {
            N(this.w, true);
            this.s.setTransition(this.w);
        }
    }

    private boolean S(int i, View view, int i2) {
        b.a aVarU;
        androidx.constraintlayout.widget.b bVarL0 = this.s.l0(i);
        if (bVarL0 == null || (aVarU = bVarL0.u(view.getId())) == null) {
            return false;
        }
        aVarU.f1077c.f1097c = 1;
        view.setVisibility(i2);
        return true;
    }

    private boolean T(View view, int i) {
        MotionLayout motionLayout = this.s;
        if (motionLayout == null) {
            return false;
        }
        boolean zS = false;
        for (int i2 : motionLayout.getConstraintSetIds()) {
            zS |= S(i2, view, i);
        }
        return zS;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionLayout.j
    public void c(MotionLayout motionLayout, int i, int i2, float f) {
        this.G = i;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionLayout.j
    public void d(MotionLayout motionLayout, int i) {
        int i2 = this.r;
        this.q = i2;
        if (i == this.y) {
            this.r = i2 + 1;
        } else if (i == this.x) {
            this.r = i2 - 1;
        }
        if (this.u) {
            if (this.r >= this.o.a()) {
                this.r = 0;
            }
            if (this.r < 0) {
                this.r = this.o.a() - 1;
            }
        } else {
            if (this.r >= this.o.a()) {
                this.r = this.o.a() - 1;
            }
            if (this.r < 0) {
                this.r = 0;
            }
        }
        if (this.q != this.r) {
            this.s.post(this.H);
        }
    }

    public int getCount() {
        b bVar = this.o;
        if (bVar != null) {
            return bVar.a();
        }
        return 0;
    }

    public int getCurrentIndex() {
        return this.r;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof MotionLayout) {
            MotionLayout motionLayout = (MotionLayout) getParent();
            for (int i = 0; i < this.f1029c; i++) {
                int i2 = this.f1028b[i];
                View viewK = motionLayout.k(i2);
                if (this.t == i2) {
                    this.A = i;
                }
                this.p.add(viewK);
            }
            this.s = motionLayout;
            if (this.C == 2) {
                q.b bVarN0 = motionLayout.n0(this.w);
                if (bVarN0 != null) {
                    bVarN0.H(5);
                }
                q.b bVarN02 = this.s.n0(this.v);
                if (bVarN02 != null) {
                    bVarN02.H(5);
                }
            }
            R();
        }
    }

    public void setAdapter(b bVar) {
        this.o = bVar;
    }

    public Carousel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = null;
        this.p = new ArrayList<>();
        this.q = 0;
        this.r = 0;
        this.t = -1;
        this.u = false;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = 0.9f;
        this.A = 0;
        this.B = 4;
        this.C = 1;
        this.D = 2.0f;
        this.E = -1;
        this.F = 200;
        this.G = -1;
        this.H = new a();
        O(context, attributeSet);
    }

    public Carousel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.o = null;
        this.p = new ArrayList<>();
        this.q = 0;
        this.r = 0;
        this.t = -1;
        this.u = false;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = 0.9f;
        this.A = 0;
        this.B = 4;
        this.C = 1;
        this.D = 2.0f;
        this.E = -1;
        this.F = 200;
        this.G = -1;
        this.H = new a();
        O(context, attributeSet);
    }
}
