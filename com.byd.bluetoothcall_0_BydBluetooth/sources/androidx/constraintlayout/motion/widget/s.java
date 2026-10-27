package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.Xml;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.motion.widget.q;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.b;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: ViewTransition.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f973a = "ViewTransition";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f974b;
    int f;
    h g;
    b.a h;
    private int k;
    private String l;
    Context p;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f975c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f976d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f977e = 0;
    private int i = -1;
    private int j = -1;
    private int m = 0;
    private String n = null;
    private int o = -1;
    private int q = -1;
    private int r = -1;
    private int s = -1;
    private int t = -1;
    private int u = -1;
    private int v = -1;
    private int w = -1;

    /* JADX INFO: compiled from: ViewTransition.java */
    class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ androidx.constraintlayout.core.j.a.c f978a;

        a(androidx.constraintlayout.core.j.a.c cVar) {
            this.f978a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) this.f978a.a(f);
        }
    }

    /* JADX INFO: compiled from: ViewTransition.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f980a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f981b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f982c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        n f983d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f984e;
        int f;
        t h;
        Interpolator i;
        float k;
        float l;
        long m;
        boolean o;
        androidx.constraintlayout.core.j.a.d g = new androidx.constraintlayout.core.j.a.d();
        boolean j = false;
        Rect n = new Rect();

        b(t tVar, n nVar, int i, int i2, int i3, Interpolator interpolator, int i4, int i5) {
            this.o = false;
            this.h = tVar;
            this.f983d = nVar;
            this.f984e = i;
            this.f = i2;
            long jNanoTime = System.nanoTime();
            this.f982c = jNanoTime;
            this.m = jNanoTime;
            this.h.b(this);
            this.i = interpolator;
            this.f980a = i4;
            this.f981b = i5;
            if (i3 == 3) {
                this.o = true;
            }
            this.l = i == 0 ? Float.MAX_VALUE : 1.0f / i;
            a();
        }

        void a() {
            if (this.j) {
                c();
            } else {
                b();
            }
        }

        void b() {
            long jNanoTime = System.nanoTime();
            long j = jNanoTime - this.m;
            this.m = jNanoTime;
            float f = this.k + (((float) (j * 1.0E-6d)) * this.l);
            this.k = f;
            if (f >= 1.0f) {
                this.k = 1.0f;
            }
            Interpolator interpolator = this.i;
            float interpolation = interpolator == null ? this.k : interpolator.getInterpolation(this.k);
            n nVar = this.f983d;
            boolean zX = nVar.x(nVar.f942b, interpolation, jNanoTime, this.g);
            if (this.k >= 1.0f) {
                if (this.f980a != -1) {
                    this.f983d.v().setTag(this.f980a, Long.valueOf(System.nanoTime()));
                }
                if (this.f981b != -1) {
                    this.f983d.v().setTag(this.f981b, null);
                }
                if (!this.o) {
                    this.h.g(this);
                }
            }
            if (this.k < 1.0f || zX) {
                this.h.e();
            }
        }

        void c() {
            long jNanoTime = System.nanoTime();
            long j = jNanoTime - this.m;
            this.m = jNanoTime;
            float f = this.k - (((float) (j * 1.0E-6d)) * this.l);
            this.k = f;
            if (f < 0.0f) {
                this.k = 0.0f;
            }
            Interpolator interpolator = this.i;
            float interpolation = interpolator == null ? this.k : interpolator.getInterpolation(this.k);
            n nVar = this.f983d;
            boolean zX = nVar.x(nVar.f942b, interpolation, jNanoTime, this.g);
            if (this.k <= 0.0f) {
                if (this.f980a != -1) {
                    this.f983d.v().setTag(this.f980a, Long.valueOf(System.nanoTime()));
                }
                if (this.f981b != -1) {
                    this.f983d.v().setTag(this.f981b, null);
                }
                this.h.g(this);
            }
            if (this.k > 0.0f || zX) {
                this.h.e();
            }
        }

        public void d(int i, float f, float f2) {
            if (i == 1) {
                if (this.j) {
                    return;
                }
                e(true);
            } else {
                if (i != 2) {
                    return;
                }
                this.f983d.v().getHitRect(this.n);
                if (this.n.contains((int) f, (int) f2) || this.j) {
                    return;
                }
                e(true);
            }
        }

        void e(boolean z) {
            int i;
            this.j = z;
            if (z && (i = this.f) != -1) {
                this.l = i == 0 ? Float.MAX_VALUE : 1.0f / i;
            }
            this.h.e();
            this.m = System.nanoTime();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    s(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.s.<init>(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(View[] viewArr) {
        if (this.q != -1) {
            for (View view : viewArr) {
                view.setTag(this.q, Long.valueOf(System.nanoTime()));
            }
        }
        if (this.r != -1) {
            for (View view2 : viewArr) {
                view2.setTag(this.r, null);
            }
        }
    }

    private void l(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.e.Qb);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == androidx.constraintlayout.widget.e.Rb) {
                this.f974b = typedArrayObtainStyledAttributes.getResourceId(index, this.f974b);
            } else if (index == androidx.constraintlayout.widget.e.Zb) {
                if (MotionLayout.z) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.k);
                    this.k = resourceId;
                    if (resourceId == -1) {
                        this.l = typedArrayObtainStyledAttributes.getString(index);
                    }
                } else if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                    this.l = typedArrayObtainStyledAttributes.getString(index);
                } else {
                    this.k = typedArrayObtainStyledAttributes.getResourceId(index, this.k);
                }
            } else if (index == androidx.constraintlayout.widget.e.ac) {
                this.f975c = typedArrayObtainStyledAttributes.getInt(index, this.f975c);
            } else if (index == androidx.constraintlayout.widget.e.dc) {
                this.f976d = typedArrayObtainStyledAttributes.getBoolean(index, this.f976d);
            } else if (index == androidx.constraintlayout.widget.e.bc) {
                this.f977e = typedArrayObtainStyledAttributes.getInt(index, this.f977e);
            } else if (index == androidx.constraintlayout.widget.e.Vb) {
                this.i = typedArrayObtainStyledAttributes.getInt(index, this.i);
            } else if (index == androidx.constraintlayout.widget.e.ec) {
                this.j = typedArrayObtainStyledAttributes.getInt(index, this.j);
            } else if (index == androidx.constraintlayout.widget.e.fc) {
                this.f = typedArrayObtainStyledAttributes.getInt(index, this.f);
            } else if (index == androidx.constraintlayout.widget.e.Yb) {
                int i2 = typedArrayObtainStyledAttributes.peekValue(index).type;
                if (i2 == 1) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                    this.o = resourceId2;
                    if (resourceId2 != -1) {
                        this.m = -2;
                    }
                } else if (i2 == 3) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.n = string;
                    if (string == null || string.indexOf("/") <= 0) {
                        this.m = -1;
                    } else {
                        this.o = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                        this.m = -2;
                    }
                } else {
                    this.m = typedArrayObtainStyledAttributes.getInteger(index, this.m);
                }
            } else if (index == androidx.constraintlayout.widget.e.cc) {
                this.q = typedArrayObtainStyledAttributes.getResourceId(index, this.q);
            } else if (index == androidx.constraintlayout.widget.e.Ub) {
                this.r = typedArrayObtainStyledAttributes.getResourceId(index, this.r);
            } else if (index == androidx.constraintlayout.widget.e.Xb) {
                this.s = typedArrayObtainStyledAttributes.getResourceId(index, this.s);
            } else if (index == androidx.constraintlayout.widget.e.Wb) {
                this.t = typedArrayObtainStyledAttributes.getResourceId(index, this.t);
            } else if (index == androidx.constraintlayout.widget.e.Tb) {
                this.v = typedArrayObtainStyledAttributes.getResourceId(index, this.v);
            } else if (index == androidx.constraintlayout.widget.e.Sb) {
                this.u = typedArrayObtainStyledAttributes.getInteger(index, this.u);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void n(q.b bVar, View view) {
        int i = this.i;
        if (i != -1) {
            bVar.E(i);
        }
        bVar.I(this.f977e);
        bVar.G(this.m, this.n, this.o);
        int id = view.getId();
        h hVar = this.g;
        if (hVar != null) {
            ArrayList<e> arrayListD = hVar.d(-1);
            h hVar2 = new h();
            Iterator<e> it = arrayListD.iterator();
            while (it.hasNext()) {
                hVar2.c(it.next().clone().i(id));
            }
            bVar.t(hVar2);
        }
    }

    void a(t tVar, MotionLayout motionLayout, View view) {
        n nVar = new n(view);
        nVar.B(view);
        this.g.a(nVar);
        nVar.I(motionLayout.getWidth(), motionLayout.getHeight(), this.i, System.nanoTime());
        new b(tVar, nVar, this.i, this.j, this.f975c, e(motionLayout.getContext()), this.q, this.r);
    }

    void b(t tVar, MotionLayout motionLayout, int i, androidx.constraintlayout.widget.b bVar, final View... viewArr) {
        if (this.f976d) {
            return;
        }
        int i2 = this.f;
        if (i2 == 2) {
            a(tVar, motionLayout, viewArr[0]);
            return;
        }
        if (i2 == 1) {
            for (int i3 : motionLayout.getConstraintSetIds()) {
                if (i3 != i) {
                    androidx.constraintlayout.widget.b bVarL0 = motionLayout.l0(i3);
                    for (View view : viewArr) {
                        b.a aVarU = bVarL0.u(view.getId());
                        b.a aVar = this.h;
                        if (aVar != null) {
                            aVar.d(aVarU);
                            aVarU.g.putAll(this.h.g);
                        }
                    }
                }
            }
        }
        androidx.constraintlayout.widget.b bVar2 = new androidx.constraintlayout.widget.b();
        bVar2.p(bVar);
        for (View view2 : viewArr) {
            b.a aVarU2 = bVar2.u(view2.getId());
            b.a aVar2 = this.h;
            if (aVar2 != null) {
                aVar2.d(aVarU2);
                aVarU2.g.putAll(this.h.g);
            }
        }
        motionLayout.K0(i, bVar2);
        int i4 = androidx.constraintlayout.widget.d.f1106b;
        motionLayout.K0(i4, bVar);
        motionLayout.x0(i4, -1, -1);
        q.b bVar3 = new q.b(-1, motionLayout.A, i4, i);
        for (View view3 : viewArr) {
            n(bVar3, view3);
        }
        motionLayout.setTransition(bVar3);
        motionLayout.D0(new Runnable() { // from class: androidx.constraintlayout.motion.widget.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f918b.j(viewArr);
            }
        });
    }

    boolean c(View view) {
        int i = this.s;
        boolean z = i == -1 || view.getTag(i) != null;
        int i2 = this.t;
        return z && (i2 == -1 || view.getTag(i2) == null);
    }

    int d() {
        return this.f974b;
    }

    Interpolator e(Context context) {
        int i = this.m;
        if (i == -2) {
            return AnimationUtils.loadInterpolator(context, this.o);
        }
        if (i == -1) {
            return new a(androidx.constraintlayout.core.j.a.c.c(this.n));
        }
        if (i == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i == 1) {
            return new AccelerateInterpolator();
        }
        if (i == 2) {
            return new DecelerateInterpolator();
        }
        if (i == 4) {
            return new BounceInterpolator();
        }
        if (i == 5) {
            return new OvershootInterpolator();
        }
        if (i != 6) {
            return null;
        }
        return new AnticipateInterpolator();
    }

    public int f() {
        return this.u;
    }

    public int g() {
        return this.v;
    }

    public int h() {
        return this.f975c;
    }

    boolean k(View view) {
        String str;
        if (view == null) {
            return false;
        }
        if ((this.k == -1 && this.l == null) || !c(view)) {
            return false;
        }
        if (view.getId() == this.k) {
            return true;
        }
        return this.l != null && (view.getLayoutParams() instanceof ConstraintLayout.b) && (str = ((ConstraintLayout.b) view.getLayoutParams()).c0) != null && str.matches(this.l);
    }

    boolean m(int i) {
        int i2 = this.f975c;
        return i2 == 1 ? i == 0 : i2 == 2 ? i == 1 : i2 == 3 && i == 0;
    }

    public String toString() {
        return "ViewTransition(" + androidx.constraintlayout.motion.widget.b.c(this.p, this.f974b) + ")";
    }
}
