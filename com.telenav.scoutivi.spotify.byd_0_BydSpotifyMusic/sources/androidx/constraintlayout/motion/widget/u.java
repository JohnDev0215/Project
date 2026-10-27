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
import androidx.constraintlayout.widget.d;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: ViewTransition.java */
/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static String f1596w = "ViewTransition";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1597a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1601e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    g f1602f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    d.a f1603g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f1606j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private String f1607k;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    Context f1611o;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1598b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1599c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1600d = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1604h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f1605i = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f1608l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private String f1609m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f1610n = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f1612p = -1;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f1613q = -1;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f1614r = -1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f1615s = -1;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f1616t = -1;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f1617u = -1;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f1618v = -1;

    /* JADX INFO: compiled from: ViewTransition.java */
    class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p.c f1619a;

        a(p.c cVar) {
            this.f1619a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            return (float) this.f1619a.a(f7);
        }
    }

    /* JADX INFO: compiled from: ViewTransition.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f1621a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f1622b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f1623c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        m f1624d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1625e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1626f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        v f1628h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        Interpolator f1629i;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        float f1631k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        float f1632l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        long f1633m;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        boolean f1635o;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        p.d f1627g = new p.d();

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        boolean f1630j = false;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        Rect f1634n = new Rect();

        b(v vVar, m mVar, int i7, int i8, int i9, Interpolator interpolator, int i10, int i11) {
            this.f1635o = false;
            this.f1628h = vVar;
            this.f1624d = mVar;
            this.f1625e = i7;
            this.f1626f = i8;
            long jNanoTime = System.nanoTime();
            this.f1623c = jNanoTime;
            this.f1633m = jNanoTime;
            this.f1628h.b(this);
            this.f1629i = interpolator;
            this.f1621a = i10;
            this.f1622b = i11;
            if (i9 == 3) {
                this.f1635o = true;
            }
            this.f1632l = i7 == 0 ? Float.MAX_VALUE : 1.0f / i7;
            a();
        }

        void a() {
            if (this.f1630j) {
                c();
            } else {
                b();
            }
        }

        void b() {
            long jNanoTime = System.nanoTime();
            long j7 = jNanoTime - this.f1633m;
            this.f1633m = jNanoTime;
            float f7 = this.f1631k + (((float) (j7 * 1.0E-6d)) * this.f1632l);
            this.f1631k = f7;
            if (f7 >= 1.0f) {
                this.f1631k = 1.0f;
            }
            Interpolator interpolator = this.f1629i;
            float interpolation = interpolator == null ? this.f1631k : interpolator.getInterpolation(this.f1631k);
            m mVar = this.f1624d;
            boolean zU = mVar.u(mVar.f1472b, interpolation, jNanoTime, this.f1627g);
            if (this.f1631k >= 1.0f) {
                if (this.f1621a != -1) {
                    this.f1624d.s().setTag(this.f1621a, Long.valueOf(System.nanoTime()));
                }
                if (this.f1622b != -1) {
                    this.f1624d.s().setTag(this.f1622b, null);
                }
                if (!this.f1635o) {
                    this.f1628h.f(this);
                }
            }
            if (this.f1631k < 1.0f || zU) {
                this.f1628h.d();
            }
        }

        void c() {
            long jNanoTime = System.nanoTime();
            long j7 = jNanoTime - this.f1633m;
            this.f1633m = jNanoTime;
            float f7 = this.f1631k - (((float) (j7 * 1.0E-6d)) * this.f1632l);
            this.f1631k = f7;
            if (f7 < 0.0f) {
                this.f1631k = 0.0f;
            }
            Interpolator interpolator = this.f1629i;
            float interpolation = interpolator == null ? this.f1631k : interpolator.getInterpolation(this.f1631k);
            m mVar = this.f1624d;
            boolean zU = mVar.u(mVar.f1472b, interpolation, jNanoTime, this.f1627g);
            if (this.f1631k <= 0.0f) {
                if (this.f1621a != -1) {
                    this.f1624d.s().setTag(this.f1621a, Long.valueOf(System.nanoTime()));
                }
                if (this.f1622b != -1) {
                    this.f1624d.s().setTag(this.f1622b, null);
                }
                this.f1628h.f(this);
            }
            if (this.f1631k > 0.0f || zU) {
                this.f1628h.d();
            }
        }

        public void d(int i7, float f7, float f8) {
            if (i7 == 1) {
                if (this.f1630j) {
                    return;
                }
                e(true);
            } else {
                if (i7 != 2) {
                    return;
                }
                this.f1624d.s().getHitRect(this.f1634n);
                if (this.f1634n.contains((int) f7, (int) f8) || this.f1630j) {
                    return;
                }
                e(true);
            }
        }

        void e(boolean z6) {
            int i7;
            this.f1630j = z6;
            if (z6 && (i7 = this.f1626f) != -1) {
                this.f1632l = i7 == 0 ? Float.MAX_VALUE : 1.0f / i7;
            }
            this.f1628h.d();
            this.f1633m = System.nanoTime();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    u(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.u.<init>(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(View[] viewArr) {
        if (this.f1612p != -1) {
            for (View view : viewArr) {
                view.setTag(this.f1612p, Long.valueOf(System.nanoTime()));
            }
        }
        if (this.f1613q != -1) {
            for (View view2 : viewArr) {
                view2.setTag(this.f1613q, null);
            }
        }
    }

    private void l(Context context, XmlPullParser xmlPullParser) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), androidx.constraintlayout.widget.i.ViewTransition);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            if (index == androidx.constraintlayout.widget.i.ViewTransition_android_id) {
                this.f1597a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1597a);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_motionTarget) {
                if (MotionLayout.f1260h1) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.f1606j);
                    this.f1606j = resourceId;
                    if (resourceId == -1) {
                        this.f1607k = typedArrayObtainStyledAttributes.getString(index);
                    }
                } else if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                    this.f1607k = typedArrayObtainStyledAttributes.getString(index);
                } else {
                    this.f1606j = typedArrayObtainStyledAttributes.getResourceId(index, this.f1606j);
                }
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_onStateTransition) {
                this.f1598b = typedArrayObtainStyledAttributes.getInt(index, this.f1598b);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_transitionDisable) {
                this.f1599c = typedArrayObtainStyledAttributes.getBoolean(index, this.f1599c);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_pathMotionArc) {
                this.f1600d = typedArrayObtainStyledAttributes.getInt(index, this.f1600d);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_duration) {
                this.f1604h = typedArrayObtainStyledAttributes.getInt(index, this.f1604h);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_upDuration) {
                this.f1605i = typedArrayObtainStyledAttributes.getInt(index, this.f1605i);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_viewTransitionMode) {
                this.f1601e = typedArrayObtainStyledAttributes.getInt(index, this.f1601e);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_motionInterpolator) {
                int i8 = typedArrayObtainStyledAttributes.peekValue(index).type;
                if (i8 == 1) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                    this.f1610n = resourceId2;
                    if (resourceId2 != -1) {
                        this.f1608l = -2;
                    }
                } else if (i8 == 3) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f1609m = string;
                    if (string == null || string.indexOf("/") <= 0) {
                        this.f1608l = -1;
                    } else {
                        this.f1610n = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                        this.f1608l = -2;
                    }
                } else {
                    this.f1608l = typedArrayObtainStyledAttributes.getInteger(index, this.f1608l);
                }
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_setsTag) {
                this.f1612p = typedArrayObtainStyledAttributes.getResourceId(index, this.f1612p);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_clearsTag) {
                this.f1613q = typedArrayObtainStyledAttributes.getResourceId(index, this.f1613q);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_ifTagSet) {
                this.f1614r = typedArrayObtainStyledAttributes.getResourceId(index, this.f1614r);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_ifTagNotSet) {
                this.f1615s = typedArrayObtainStyledAttributes.getResourceId(index, this.f1615s);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_SharedValueId) {
                this.f1617u = typedArrayObtainStyledAttributes.getResourceId(index, this.f1617u);
            } else if (index == androidx.constraintlayout.widget.i.ViewTransition_SharedValue) {
                this.f1616t = typedArrayObtainStyledAttributes.getInteger(index, this.f1616t);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void n(q.b bVar, View view) {
        int i7 = this.f1604h;
        if (i7 != -1) {
            bVar.E(i7);
        }
        bVar.H(this.f1600d);
        bVar.G(this.f1608l, this.f1609m, this.f1610n);
        int id = view.getId();
        g gVar = this.f1602f;
        if (gVar != null) {
            ArrayList<d> arrayListD = gVar.d(-1);
            g gVar2 = new g();
            Iterator<d> it = arrayListD.iterator();
            while (it.hasNext()) {
                gVar2.c(it.next().clone().h(id));
            }
            bVar.t(gVar2);
        }
    }

    void b(v vVar, MotionLayout motionLayout, View view) {
        m mVar = new m(view);
        mVar.y(view);
        this.f1602f.a(mVar);
        mVar.F(motionLayout.getWidth(), motionLayout.getHeight(), this.f1604h, System.nanoTime());
        new b(vVar, mVar, this.f1604h, this.f1605i, this.f1598b, f(motionLayout.getContext()), this.f1612p, this.f1613q);
    }

    void c(v vVar, MotionLayout motionLayout, int i7, androidx.constraintlayout.widget.d dVar, final View... viewArr) {
        if (this.f1599c) {
            return;
        }
        int i8 = this.f1601e;
        if (i8 == 2) {
            b(vVar, motionLayout, viewArr[0]);
            return;
        }
        if (i8 == 1) {
            for (int i9 : motionLayout.getConstraintSetIds()) {
                if (i9 != i7) {
                    androidx.constraintlayout.widget.d dVarM0 = motionLayout.m0(i9);
                    for (View view : viewArr) {
                        d.a aVarW = dVarM0.w(view.getId());
                        d.a aVar = this.f1603g;
                        if (aVar != null) {
                            aVar.d(aVarW);
                            aVarW.f1781g.putAll(this.f1603g.f1781g);
                        }
                    }
                }
            }
        }
        androidx.constraintlayout.widget.d dVar2 = new androidx.constraintlayout.widget.d();
        dVar2.q(dVar);
        for (View view2 : viewArr) {
            d.a aVarW2 = dVar2.w(view2.getId());
            d.a aVar2 = this.f1603g;
            if (aVar2 != null) {
                aVar2.d(aVarW2);
                aVarW2.f1781g.putAll(this.f1603g.f1781g);
            }
        }
        motionLayout.J0(i7, dVar2);
        int i10 = androidx.constraintlayout.widget.h.view_transition;
        motionLayout.J0(i10, dVar);
        motionLayout.x0(i10, -1, -1);
        q.b bVar = new q.b(-1, motionLayout.D, i10, i7);
        for (View view3 : viewArr) {
            n(bVar, view3);
        }
        motionLayout.setTransition(bVar);
        motionLayout.D0(new Runnable() { // from class: androidx.constraintlayout.motion.widget.t
            @Override // java.lang.Runnable
            public final void run() {
                this.f1594e.j(viewArr);
            }
        });
    }

    boolean d(View view) {
        int i7 = this.f1614r;
        boolean z6 = i7 == -1 || view.getTag(i7) != null;
        int i8 = this.f1615s;
        return z6 && (i8 == -1 || view.getTag(i8) == null);
    }

    int e() {
        return this.f1597a;
    }

    Interpolator f(Context context) {
        int i7 = this.f1608l;
        if (i7 == -2) {
            return AnimationUtils.loadInterpolator(context, this.f1610n);
        }
        if (i7 == -1) {
            return new a(p.c.c(this.f1609m));
        }
        if (i7 == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i7 == 1) {
            return new AccelerateInterpolator();
        }
        if (i7 == 2) {
            return new DecelerateInterpolator();
        }
        if (i7 == 4) {
            return new BounceInterpolator();
        }
        if (i7 == 5) {
            return new OvershootInterpolator();
        }
        if (i7 != 6) {
            return null;
        }
        return new AnticipateInterpolator();
    }

    public int g() {
        return this.f1616t;
    }

    public int h() {
        return this.f1617u;
    }

    public int i() {
        return this.f1598b;
    }

    boolean k(View view) {
        String str;
        if (view == null) {
            return false;
        }
        if ((this.f1606j == -1 && this.f1607k == null) || !d(view)) {
            return false;
        }
        if (view.getId() == this.f1606j) {
            return true;
        }
        return this.f1607k != null && (view.getLayoutParams() instanceof ConstraintLayout.b) && (str = ((ConstraintLayout.b) view.getLayoutParams()).f1677c0) != null && str.matches(this.f1607k);
    }

    boolean m(int i7) {
        int i8 = this.f1598b;
        return i8 == 1 ? i7 == 0 : i8 == 2 ? i7 == 1 : i8 == 3 && i7 == 0;
    }

    public String toString() {
        return "ViewTransition(" + androidx.constraintlayout.motion.widget.a.c(this.f1611o, this.f1597a) + ")";
    }
}
