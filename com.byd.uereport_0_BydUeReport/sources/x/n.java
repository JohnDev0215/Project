package x;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import b0.b;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import x.g;
import x.r;
import x.s;

/* JADX INFO: compiled from: MotionController.java */
/* JADX INFO: loaded from: classes.dex */
public class n {
    public l[] A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f8913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8914b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f8915c;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public w.b[] f8921i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public w.b f8922j;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f8926n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public double[] f8927o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public double[] f8928p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String[] f8929q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int[] f8930r;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public HashMap<String, s> f8936x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public HashMap<String, r> f8937y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public HashMap<String, g> f8938z;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8916d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public p f8917e = new p();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public p f8918f = new p();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m f8919g = new m();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public m f8920h = new m();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8923k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f8924l = 0.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f8925m = 1.0f;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f8931s = 4;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float[] f8932t = new float[4];

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ArrayList<p> f8933u = new ArrayList<>();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float[] f8934v = new float[1];

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ArrayList<c> f8935w = new ArrayList<>();
    public int B = c.f8764a;

    public n(View view) {
        u(view);
    }

    public void a(c cVar) {
        this.f8935w.add(cVar);
    }

    public void b(ArrayList<c> arrayList) {
        this.f8935w.addAll(arrayList);
    }

    public int c(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] dArrH = this.f8921i[0].h();
        if (iArr != null) {
            Iterator<p> it = this.f8933u.iterator();
            int i8 = 0;
            while (it.hasNext()) {
                iArr[i8] = it.next().f8952q;
                i8++;
            }
        }
        int i9 = 0;
        for (double d8 : dArrH) {
            this.f8921i[0].d(d8, this.f8927o);
            this.f8917e.f(this.f8926n, this.f8927o, fArr, i9);
            i9 += 2;
        }
        return i9 / 2;
    }

    public void d(float[] fArr, int i8) {
        int i9 = i8;
        float f8 = 1.0f;
        float f9 = 1.0f / (i9 - 1);
        HashMap<String, r> map = this.f8937y;
        r rVar = map == null ? null : map.get("translationX");
        HashMap<String, r> map2 = this.f8937y;
        r rVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, g> map3 = this.f8938z;
        g gVar = map3 == null ? null : map3.get("translationX");
        HashMap<String, g> map4 = this.f8938z;
        g gVar2 = map4 != null ? map4.get("translationY") : null;
        int i10 = 0;
        while (i10 < i9) {
            float f10 = i10 * f9;
            float f11 = this.f8925m;
            if (f11 != f8) {
                float f12 = this.f8924l;
                if (f10 < f12) {
                    f10 = 0.0f;
                }
                if (f10 > f12 && f10 < 1.0d) {
                    f10 = (f10 - f12) * f11;
                }
            }
            double dA = f10;
            w.c cVar = this.f8917e.f8940e;
            float f13 = Float.NaN;
            float f14 = 0.0f;
            for (p pVar : this.f8933u) {
                w.c cVar2 = pVar.f8940e;
                if (cVar2 != null) {
                    float f15 = pVar.f8942g;
                    if (f15 < f10) {
                        f14 = f15;
                        cVar = cVar2;
                    } else if (Float.isNaN(f13)) {
                        f13 = pVar.f8942g;
                    }
                }
            }
            if (cVar != null) {
                if (Float.isNaN(f13)) {
                    f13 = 1.0f;
                }
                dA = (((float) cVar.a((f10 - f14) / r15)) * (f13 - f14)) + f14;
            }
            this.f8921i[0].d(dA, this.f8927o);
            w.b bVar = this.f8922j;
            if (bVar != null) {
                double[] dArr = this.f8927o;
                if (dArr.length > 0) {
                    bVar.d(dA, dArr);
                }
            }
            int i11 = i10 * 2;
            this.f8917e.f(this.f8926n, this.f8927o, fArr, i11);
            if (gVar != null) {
                fArr[i11] = fArr[i11] + gVar.a(f10);
            } else if (rVar != null) {
                fArr[i11] = fArr[i11] + rVar.a(f10);
            }
            if (gVar2 != null) {
                int i12 = i11 + 1;
                fArr[i12] = fArr[i12] + gVar2.a(f10);
            } else if (rVar2 != null) {
                int i13 = i11 + 1;
                fArr[i13] = fArr[i13] + rVar2.a(f10);
            }
            i10++;
            i9 = i8;
            f8 = 1.0f;
        }
    }

    public void e(float f8, float[] fArr, int i8) {
        this.f8921i[0].d(f(f8, null), this.f8927o);
        this.f8917e.i(this.f8926n, this.f8927o, fArr, i8);
    }

    public final float f(float f8, float[] fArr) {
        float f9 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f10 = this.f8925m;
            if (f10 != 1.0d) {
                float f11 = this.f8924l;
                if (f8 < f11) {
                    f8 = 0.0f;
                }
                if (f8 > f11 && f8 < 1.0d) {
                    f8 = (f8 - f11) * f10;
                }
            }
        }
        w.c cVar = this.f8917e.f8940e;
        float f12 = Float.NaN;
        for (p pVar : this.f8933u) {
            w.c cVar2 = pVar.f8940e;
            if (cVar2 != null) {
                float f13 = pVar.f8942g;
                if (f13 < f8) {
                    cVar = cVar2;
                    f9 = f13;
                } else if (Float.isNaN(f12)) {
                    f12 = pVar.f8942g;
                }
            }
        }
        if (cVar != null) {
            float f14 = (Float.isNaN(f12) ? 1.0f : f12) - f9;
            double d8 = (f8 - f9) / f14;
            f8 = (((float) cVar.a(d8)) * f14) + f9;
            if (fArr != null) {
                fArr[0] = (float) cVar.b(d8);
            }
        }
        return f8;
    }

    public void g(float f8, float f9, float f10, float[] fArr) {
        double[] dArr;
        float f11 = f(f8, this.f8934v);
        w.b[] bVarArr = this.f8921i;
        int i8 = 0;
        if (bVarArr == null) {
            p pVar = this.f8918f;
            float f12 = pVar.f8944i;
            p pVar2 = this.f8917e;
            float f13 = f12 - pVar2.f8944i;
            float f14 = pVar.f8945j - pVar2.f8945j;
            float f15 = (pVar.f8946k - pVar2.f8946k) + f13;
            float f16 = (pVar.f8947l - pVar2.f8947l) + f14;
            fArr[0] = (f13 * (1.0f - f9)) + (f15 * f9);
            fArr[1] = (f14 * (1.0f - f10)) + (f16 * f10);
            return;
        }
        double d8 = f11;
        bVarArr[0].g(d8, this.f8928p);
        this.f8921i[0].d(d8, this.f8927o);
        float f17 = this.f8934v[0];
        while (true) {
            dArr = this.f8928p;
            if (i8 >= dArr.length) {
                break;
            }
            dArr[i8] = dArr[i8] * ((double) f17);
            i8++;
        }
        w.b bVar = this.f8922j;
        if (bVar == null) {
            this.f8917e.o(f9, f10, fArr, this.f8926n, dArr, this.f8927o);
            return;
        }
        double[] dArr2 = this.f8927o;
        if (dArr2.length > 0) {
            bVar.d(d8, dArr2);
            this.f8922j.g(d8, this.f8928p);
            this.f8917e.o(f9, f10, fArr, this.f8926n, this.f8928p, this.f8927o);
        }
    }

    public int h() {
        int iMax = this.f8917e.f8941f;
        Iterator<p> it = this.f8933u.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().f8941f);
        }
        return Math.max(iMax, this.f8918f.f8941f);
    }

    public float i() {
        return this.f8918f.f8944i;
    }

    public float j() {
        return this.f8918f.f8945j;
    }

    public p k(int i8) {
        return this.f8933u.get(i8);
    }

    public void l(float f8, int i8, int i9, float f9, float f10, float[] fArr) {
        float f11 = f(f8, this.f8934v);
        HashMap<String, r> map = this.f8937y;
        r rVar = map == null ? null : map.get("translationX");
        HashMap<String, r> map2 = this.f8937y;
        r rVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, r> map3 = this.f8937y;
        r rVar3 = map3 == null ? null : map3.get("rotation");
        HashMap<String, r> map4 = this.f8937y;
        r rVar4 = map4 == null ? null : map4.get("scaleX");
        HashMap<String, r> map5 = this.f8937y;
        r rVar5 = map5 == null ? null : map5.get("scaleY");
        HashMap<String, g> map6 = this.f8938z;
        g gVar = map6 == null ? null : map6.get("translationX");
        HashMap<String, g> map7 = this.f8938z;
        g gVar2 = map7 == null ? null : map7.get("translationY");
        HashMap<String, g> map8 = this.f8938z;
        g gVar3 = map8 == null ? null : map8.get("rotation");
        HashMap<String, g> map9 = this.f8938z;
        g gVar4 = map9 == null ? null : map9.get("scaleX");
        HashMap<String, g> map10 = this.f8938z;
        g gVar5 = map10 != null ? map10.get("scaleY") : null;
        w.h hVar = new w.h();
        hVar.b();
        hVar.d(rVar3, f11);
        hVar.h(rVar, rVar2, f11);
        hVar.f(rVar4, rVar5, f11);
        hVar.c(gVar3, f11);
        hVar.g(gVar, gVar2, f11);
        hVar.e(gVar4, gVar5, f11);
        w.b bVar = this.f8922j;
        if (bVar != null) {
            double[] dArr = this.f8927o;
            if (dArr.length > 0) {
                double d8 = f11;
                bVar.d(d8, dArr);
                this.f8922j.g(d8, this.f8928p);
                this.f8917e.o(f9, f10, fArr, this.f8926n, this.f8928p, this.f8927o);
            }
            hVar.a(f9, f10, i8, i9, fArr);
            return;
        }
        int i10 = 0;
        if (this.f8921i == null) {
            p pVar = this.f8918f;
            float f12 = pVar.f8944i;
            p pVar2 = this.f8917e;
            float f13 = f12 - pVar2.f8944i;
            g gVar6 = gVar5;
            float f14 = pVar.f8945j - pVar2.f8945j;
            g gVar7 = gVar4;
            float f15 = (pVar.f8946k - pVar2.f8946k) + f13;
            float f16 = (pVar.f8947l - pVar2.f8947l) + f14;
            fArr[0] = (f13 * (1.0f - f9)) + (f15 * f9);
            fArr[1] = (f14 * (1.0f - f10)) + (f16 * f10);
            hVar.b();
            hVar.d(rVar3, f11);
            hVar.h(rVar, rVar2, f11);
            hVar.f(rVar4, rVar5, f11);
            hVar.c(gVar3, f11);
            hVar.g(gVar, gVar2, f11);
            hVar.e(gVar7, gVar6, f11);
            hVar.a(f9, f10, i8, i9, fArr);
            return;
        }
        double dF = f(f11, this.f8934v);
        this.f8921i[0].g(dF, this.f8928p);
        this.f8921i[0].d(dF, this.f8927o);
        float f17 = this.f8934v[0];
        while (true) {
            double[] dArr2 = this.f8928p;
            if (i10 >= dArr2.length) {
                this.f8917e.o(f9, f10, fArr, this.f8926n, dArr2, this.f8927o);
                hVar.a(f9, f10, i8, i9, fArr);
                return;
            } else {
                dArr2[i10] = dArr2[i10] * ((double) f17);
                i10++;
            }
        }
    }

    public final float m() {
        float[] fArr = new float[2];
        float f8 = 1.0f / 99;
        double d8 = 0.0d;
        double d9 = 0.0d;
        int i8 = 0;
        float fHypot = 0.0f;
        while (i8 < 100) {
            float f9 = i8 * f8;
            double dA = f9;
            w.c cVar = this.f8917e.f8940e;
            float f10 = Float.NaN;
            float f11 = 0.0f;
            for (p pVar : this.f8933u) {
                w.c cVar2 = pVar.f8940e;
                float f12 = f8;
                if (cVar2 != null) {
                    float f13 = pVar.f8942g;
                    if (f13 < f9) {
                        f11 = f13;
                        cVar = cVar2;
                    } else if (Float.isNaN(f10)) {
                        f10 = pVar.f8942g;
                    }
                }
                f8 = f12;
            }
            float f14 = f8;
            if (cVar != null) {
                if (Float.isNaN(f10)) {
                    f10 = 1.0f;
                }
                dA = (((float) cVar.a((f9 - f11) / r16)) * (f10 - f11)) + f11;
            }
            this.f8921i[0].d(dA, this.f8927o);
            this.f8917e.f(this.f8926n, this.f8927o, fArr, 0);
            if (i8 > 0) {
                fHypot = (float) (((double) fHypot) + Math.hypot(d9 - ((double) fArr[1]), d8 - ((double) fArr[0])));
            }
            d8 = fArr[0];
            d9 = fArr[1];
            i8++;
            f8 = f14;
        }
        return fHypot;
    }

    public final void n(p pVar) {
        if (Collections.binarySearch(this.f8933u, pVar) == 0) {
            Log.e("MotionController", " KeyPath positon \"" + pVar.f8943h + "\" outside of range");
        }
        this.f8933u.add((-r0) - 1, pVar);
    }

    public boolean o(View view, float f8, long j8, e eVar) {
        s.d dVar;
        boolean zJ;
        double d8;
        float f9 = f(f8, null);
        HashMap<String, r> map = this.f8937y;
        if (map != null) {
            Iterator<r> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().f(view, f9);
            }
        }
        HashMap<String, s> map2 = this.f8936x;
        if (map2 != null) {
            dVar = null;
            boolean zF = false;
            for (s sVar : map2.values()) {
                if (sVar instanceof s.d) {
                    dVar = (s.d) sVar;
                } else {
                    zF |= sVar.f(view, f9, j8, eVar);
                }
            }
            zJ = zF;
        } else {
            dVar = null;
            zJ = false;
        }
        w.b[] bVarArr = this.f8921i;
        if (bVarArr != null) {
            double d9 = f9;
            bVarArr[0].d(d9, this.f8927o);
            this.f8921i[0].g(d9, this.f8928p);
            w.b bVar = this.f8922j;
            if (bVar != null) {
                double[] dArr = this.f8927o;
                if (dArr.length > 0) {
                    bVar.d(d9, dArr);
                    this.f8922j.g(d9, this.f8928p);
                }
            }
            this.f8917e.p(view, this.f8926n, this.f8927o, this.f8928p, null);
            HashMap<String, r> map3 = this.f8937y;
            if (map3 != null) {
                for (r rVar : map3.values()) {
                    if (rVar instanceof r.d) {
                        double[] dArr2 = this.f8928p;
                        ((r.d) rVar).i(view, f9, dArr2[0], dArr2[1]);
                    }
                }
            }
            if (dVar != null) {
                double[] dArr3 = this.f8928p;
                d8 = d9;
                zJ = dVar.j(view, eVar, f9, j8, dArr3[0], dArr3[1]) | zJ;
            } else {
                d8 = d9;
            }
            int i8 = 1;
            while (true) {
                w.b[] bVarArr2 = this.f8921i;
                if (i8 >= bVarArr2.length) {
                    break;
                }
                bVarArr2[i8].e(d8, this.f8932t);
                this.f8917e.f8951p.get(this.f8929q[i8 - 1]).i(view, this.f8932t);
                i8++;
            }
            m mVar = this.f8919g;
            if (mVar.f8892f == 0) {
                if (f9 <= 0.0f) {
                    view.setVisibility(mVar.f8893g);
                } else if (f9 >= 1.0f) {
                    view.setVisibility(this.f8920h.f8893g);
                } else if (this.f8920h.f8893g != mVar.f8893g) {
                    view.setVisibility(0);
                }
            }
            if (this.A != null) {
                int i9 = 0;
                while (true) {
                    l[] lVarArr = this.A;
                    if (i9 >= lVarArr.length) {
                        break;
                    }
                    lVarArr[i9].r(f9, view);
                    i9++;
                }
            }
        } else {
            p pVar = this.f8917e;
            float f10 = pVar.f8944i;
            p pVar2 = this.f8918f;
            float f11 = f10 + ((pVar2.f8944i - f10) * f9);
            float f12 = pVar.f8945j;
            float f13 = f12 + ((pVar2.f8945j - f12) * f9);
            float f14 = pVar.f8946k;
            float f15 = pVar2.f8946k;
            float f16 = pVar.f8947l;
            float f17 = pVar2.f8947l;
            float f18 = f11 + 0.5f;
            int i10 = (int) f18;
            float f19 = f13 + 0.5f;
            int i11 = (int) f19;
            int i12 = (int) (f18 + ((f15 - f14) * f9) + f14);
            int i13 = (int) (f19 + ((f17 - f16) * f9) + f16);
            int i14 = i12 - i10;
            int i15 = i13 - i11;
            if (f15 != f14 || f17 != f16) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i14, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i15, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
            view.layout(i10, i11, i12, i13);
        }
        HashMap<String, g> map4 = this.f8938z;
        if (map4 != null) {
            for (g gVar : map4.values()) {
                if (gVar instanceof g.f) {
                    double[] dArr4 = this.f8928p;
                    ((g.f) gVar).j(view, f9, dArr4[0], dArr4[1]);
                } else {
                    gVar.f(view, f9);
                }
            }
        }
        return zJ;
    }

    public final void p(p pVar) {
        pVar.n((int) this.f8913a.getX(), (int) this.f8913a.getY(), this.f8913a.getWidth(), this.f8913a.getHeight());
    }

    public void q(ConstraintWidget constraintWidget, b0.b bVar) {
        p pVar = this.f8918f;
        pVar.f8942g = 1.0f;
        pVar.f8943h = 1.0f;
        p(pVar);
        this.f8918f.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        this.f8918f.a(bVar.q(this.f8914b));
        this.f8920h.i(constraintWidget, bVar, this.f8914b);
    }

    public void r(int i8) {
        this.B = i8;
    }

    public void s(View view) {
        p pVar = this.f8917e;
        pVar.f8942g = 0.0f;
        pVar.f8943h = 0.0f;
        pVar.n(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f8919g.h(view);
    }

    public void t(ConstraintWidget constraintWidget, b0.b bVar) {
        p pVar = this.f8917e;
        pVar.f8942g = 0.0f;
        pVar.f8943h = 0.0f;
        p(pVar);
        this.f8917e.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        b.a aVarQ = bVar.q(this.f8914b);
        this.f8917e.a(aVarQ);
        this.f8923k = aVarQ.f1944c.f1991g;
        this.f8919g.i(constraintWidget, bVar, this.f8914b);
    }

    public String toString() {
        return " start: x: " + this.f8917e.f8944i + " y: " + this.f8917e.f8945j + " end: x: " + this.f8918f.f8944i + " y: " + this.f8918f.f8945j;
    }

    public void u(View view) {
        this.f8913a = view;
        this.f8914b = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            this.f8915c = ((ConstraintLayout.b) layoutParams).a();
        }
    }

    public void v(int i8, int i9, float f8, long j8) {
        ArrayList arrayList;
        String[] strArr;
        s sVarD;
        ConstraintAttribute constraintAttribute;
        r rVarD;
        ConstraintAttribute constraintAttribute2;
        new HashSet();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        int i10 = this.B;
        if (i10 != c.f8764a) {
            this.f8917e.f8950o = i10;
        }
        this.f8919g.f(this.f8920h, hashSet2);
        ArrayList<c> arrayList2 = this.f8935w;
        if (arrayList2 != null) {
            arrayList = null;
            for (c cVar : arrayList2) {
                if (cVar instanceof i) {
                    i iVar = (i) cVar;
                    n(new p(i8, i9, iVar, this.f8917e, this.f8918f));
                    int i11 = iVar.f8850g;
                    if (i11 != c.f8764a) {
                        this.f8916d = i11;
                    }
                } else if (cVar instanceof f) {
                    cVar.b(hashSet3);
                } else if (cVar instanceof k) {
                    cVar.b(hashSet);
                } else if (cVar instanceof l) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((l) cVar);
                } else {
                    cVar.e(map);
                    cVar.b(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        int i12 = 0;
        if (arrayList != null) {
            this.A = (l[]) arrayList.toArray(new l[0]);
        }
        char c8 = 1;
        if (!hashSet2.isEmpty()) {
            this.f8937y = new HashMap<>();
            for (String str : hashSet2) {
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[c8];
                    for (c cVar2 : this.f8935w) {
                        HashMap<String, ConstraintAttribute> map2 = cVar2.f8769f;
                        if (map2 != null && (constraintAttribute2 = map2.get(str2)) != null) {
                            sparseArray.append(cVar2.f8765b, constraintAttribute2);
                        }
                    }
                    rVarD = r.c(str, sparseArray);
                } else {
                    rVarD = r.d(str);
                }
                if (rVarD != null) {
                    rVarD.g(str);
                    this.f8937y.put(str, rVarD);
                }
                c8 = 1;
            }
            ArrayList<c> arrayList3 = this.f8935w;
            if (arrayList3 != null) {
                for (c cVar3 : arrayList3) {
                    if (cVar3 instanceof d) {
                        cVar3.a(this.f8937y);
                    }
                }
            }
            this.f8919g.a(this.f8937y, 0);
            this.f8920h.a(this.f8937y, 100);
            for (String str3 : this.f8937y.keySet()) {
                this.f8937y.get(str3).h(map.containsKey(str3) ? map.get(str3).intValue() : 0);
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.f8936x == null) {
                this.f8936x = new HashMap<>();
            }
            for (String str4 : hashSet) {
                if (!this.f8936x.containsKey(str4)) {
                    if (str4.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str5 = str4.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
                        for (c cVar4 : this.f8935w) {
                            HashMap<String, ConstraintAttribute> map3 = cVar4.f8769f;
                            if (map3 != null && (constraintAttribute = map3.get(str5)) != null) {
                                sparseArray2.append(cVar4.f8765b, constraintAttribute);
                            }
                        }
                        sVarD = s.c(str4, sparseArray2);
                    } else {
                        sVarD = s.d(str4, j8);
                    }
                    if (sVarD != null) {
                        sVarD.h(str4);
                        this.f8936x.put(str4, sVarD);
                    }
                }
            }
            ArrayList<c> arrayList4 = this.f8935w;
            if (arrayList4 != null) {
                for (c cVar5 : arrayList4) {
                    if (cVar5 instanceof k) {
                        ((k) cVar5).M(this.f8936x);
                    }
                }
            }
            for (String str6 : this.f8936x.keySet()) {
                this.f8936x.get(str6).i(map.containsKey(str6) ? map.get(str6).intValue() : 0);
            }
        }
        int i13 = 2;
        int size = this.f8933u.size() + 2;
        p[] pVarArr = new p[size];
        pVarArr[0] = this.f8917e;
        pVarArr[size - 1] = this.f8918f;
        if (this.f8933u.size() > 0 && this.f8916d == -1) {
            this.f8916d = 0;
        }
        Iterator<p> it = this.f8933u.iterator();
        int i14 = 1;
        while (it.hasNext()) {
            pVarArr[i14] = it.next();
            i14++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.f8918f.f8951p.keySet()) {
            if (this.f8917e.f8951p.containsKey(str7)) {
                if (!hashSet2.contains("CUSTOM," + str7)) {
                    hashSet4.add(str7);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.f8929q = strArr2;
        this.f8930r = new int[strArr2.length];
        int i15 = 0;
        while (true) {
            strArr = this.f8929q;
            if (i15 >= strArr.length) {
                break;
            }
            String str8 = strArr[i15];
            this.f8930r[i15] = 0;
            int i16 = 0;
            while (true) {
                if (i16 >= size) {
                    break;
                }
                if (pVarArr[i16].f8951p.containsKey(str8)) {
                    int[] iArr = this.f8930r;
                    iArr[i15] = iArr[i15] + pVarArr[i16].f8951p.get(str8).f();
                    break;
                }
                i16++;
            }
            i15++;
        }
        boolean z7 = pVarArr[0].f8950o != c.f8764a;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i17 = 1; i17 < size; i17++) {
            pVarArr[i17].d(pVarArr[i17 - 1], zArr, this.f8929q, z7);
        }
        int i18 = 0;
        for (int i19 = 1; i19 < length; i19++) {
            if (zArr[i19]) {
                i18++;
            }
        }
        int[] iArr2 = new int[i18];
        this.f8926n = iArr2;
        this.f8927o = new double[iArr2.length];
        this.f8928p = new double[iArr2.length];
        int i20 = 0;
        for (int i21 = 1; i21 < length; i21++) {
            if (zArr[i21]) {
                this.f8926n[i20] = i21;
                i20++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, size, this.f8926n.length);
        double[] dArr2 = new double[size];
        for (int i22 = 0; i22 < size; i22++) {
            pVarArr[i22].e(dArr[i22], this.f8926n);
            dArr2[i22] = pVarArr[i22].f8942g;
        }
        int i23 = 0;
        while (true) {
            int[] iArr3 = this.f8926n;
            if (i23 >= iArr3.length) {
                break;
            }
            if (iArr3[i23] < p.f8939d.length) {
                String str9 = p.f8939d[this.f8926n[i23]] + " [";
                for (int i24 = 0; i24 < size; i24++) {
                    str9 = str9 + dArr[i24][i23];
                }
            }
            i23++;
        }
        this.f8921i = new w.b[this.f8929q.length + 1];
        int i25 = 0;
        while (true) {
            String[] strArr3 = this.f8929q;
            if (i25 >= strArr3.length) {
                break;
            }
            String str10 = strArr3[i25];
            int i26 = i12;
            int i27 = i26;
            double[] dArr3 = null;
            double[][] dArr4 = null;
            while (i26 < size) {
                if (pVarArr[i26].j(str10)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr4 = new int[i13];
                        iArr4[1] = pVarArr[i26].h(str10);
                        iArr4[i12] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr4);
                    }
                    dArr3[i27] = pVarArr[i26].f8942g;
                    pVarArr[i26].g(str10, dArr4[i27], 0);
                    i27++;
                }
                i26++;
                i13 = 2;
                i12 = 0;
            }
            i25++;
            this.f8921i[i25] = w.b.a(this.f8916d, Arrays.copyOf(dArr3, i27), (double[][]) Arrays.copyOf(dArr4, i27));
            i13 = 2;
            i12 = 0;
        }
        this.f8921i[0] = w.b.a(this.f8916d, dArr2, dArr);
        if (pVarArr[0].f8950o != c.f8764a) {
            int[] iArr5 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i28 = 0; i28 < size; i28++) {
                iArr5[i28] = pVarArr[i28].f8950o;
                dArr5[i28] = pVarArr[i28].f8942g;
                dArr6[i28][0] = pVarArr[i28].f8944i;
                dArr6[i28][1] = pVarArr[i28].f8945j;
            }
            this.f8922j = w.b.b(iArr5, dArr5, dArr6);
        }
        float fM = Float.NaN;
        this.f8938z = new HashMap<>();
        if (this.f8935w != null) {
            for (String str11 : hashSet3) {
                g gVarC = g.c(str11);
                if (gVarC != null) {
                    if (gVarC.i() && Float.isNaN(fM)) {
                        fM = m();
                    }
                    gVarC.g(str11);
                    this.f8938z.put(str11, gVarC);
                }
            }
            for (c cVar6 : this.f8935w) {
                if (cVar6 instanceof f) {
                    ((f) cVar6).O(this.f8938z);
                }
            }
            Iterator<g> it2 = this.f8938z.values().iterator();
            while (it2.hasNext()) {
                it2.next().h(fM);
            }
        }
    }
}
