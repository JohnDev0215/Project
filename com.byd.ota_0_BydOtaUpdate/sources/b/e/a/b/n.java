package b.e.a.b;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.e.a.b.g;
import b.e.a.b.r;
import b.e.a.b.s;
import b.e.c.b;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: MotionController.java */
/* JADX INFO: loaded from: classes.dex */
public class n {
    public l[] A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f1294a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1295b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f1296c;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b.e.a.a.b[] f1302i;
    public b.e.a.a.b j;
    public int[] n;
    public double[] o;
    public double[] p;
    public String[] q;
    public int[] r;
    public HashMap<String, s> x;
    public HashMap<String, r> y;
    public HashMap<String, g> z;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1297d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public p f1298e = new p();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public p f1299f = new p();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m f1300g = new m();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public m f1301h = new m();
    public float k = Float.NaN;
    public float l = 0.0f;
    public float m = 1.0f;
    public int s = 4;
    public float[] t = new float[4];
    public ArrayList<p> u = new ArrayList<>();
    public float[] v = new float[1];
    public ArrayList<c> w = new ArrayList<>();
    public int B = c.f1240a;

    public n(View view) {
        u(view);
    }

    public void a(c cVar) {
        this.w.add(cVar);
    }

    public void b(ArrayList<c> arrayList) {
        this.w.addAll(arrayList);
    }

    public int c(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] dArrH = this.f1302i[0].h();
        if (iArr != null) {
            Iterator<p> it = this.u.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                iArr[i2] = it.next().M3;
                i2++;
            }
        }
        int i3 = 0;
        for (double d2 : dArrH) {
            this.f1302i[0].d(d2, this.o);
            this.f1298e.f(this.n, this.o, fArr, i3);
            i3 += 2;
        }
        return i3 / 2;
    }

    public void d(float[] fArr, int i2) {
        int i3 = i2;
        float f2 = 1.0f;
        float f3 = 1.0f / (i3 - 1);
        HashMap<String, r> map = this.y;
        r rVar = map == null ? null : map.get("translationX");
        HashMap<String, r> map2 = this.y;
        r rVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, g> map3 = this.z;
        g gVar = map3 == null ? null : map3.get("translationX");
        HashMap<String, g> map4 = this.z;
        g gVar2 = map4 != null ? map4.get("translationY") : null;
        int i4 = 0;
        while (i4 < i3) {
            float f4 = i4 * f3;
            float f5 = this.m;
            if (f5 != f2) {
                float f6 = this.l;
                if (f4 < f6) {
                    f4 = 0.0f;
                }
                if (f4 > f6 && f4 < 1.0d) {
                    f4 = (f4 - f6) * f5;
                }
            }
            double dA = f4;
            b.e.a.a.c cVar = this.f1298e.f1304d;
            float f7 = Float.NaN;
            float f8 = 0.0f;
            for (p pVar : this.u) {
                b.e.a.a.c cVar2 = pVar.f1304d;
                if (cVar2 != null) {
                    float f9 = pVar.x;
                    if (f9 < f4) {
                        f8 = f9;
                        cVar = cVar2;
                    } else if (Float.isNaN(f7)) {
                        f7 = pVar.x;
                    }
                }
            }
            if (cVar != null) {
                if (Float.isNaN(f7)) {
                    f7 = 1.0f;
                }
                dA = (((float) cVar.a((f4 - f8) / r15)) * (f7 - f8)) + f8;
            }
            this.f1302i[0].d(dA, this.o);
            b.e.a.a.b bVar = this.j;
            if (bVar != null) {
                double[] dArr = this.o;
                if (dArr.length > 0) {
                    bVar.d(dA, dArr);
                }
            }
            int i5 = i4 * 2;
            this.f1298e.f(this.n, this.o, fArr, i5);
            if (gVar != null) {
                fArr[i5] = fArr[i5] + gVar.a(f4);
            } else if (rVar != null) {
                fArr[i5] = fArr[i5] + rVar.a(f4);
            }
            if (gVar2 != null) {
                int i6 = i5 + 1;
                fArr[i6] = fArr[i6] + gVar2.a(f4);
            } else if (rVar2 != null) {
                int i7 = i5 + 1;
                fArr[i7] = fArr[i7] + rVar2.a(f4);
            }
            i4++;
            i3 = i2;
            f2 = 1.0f;
        }
    }

    public void e(float f2, float[] fArr, int i2) {
        this.f1302i[0].d(f(f2, null), this.o);
        this.f1298e.i(this.n, this.o, fArr, i2);
    }

    public final float f(float f2, float[] fArr) {
        float f3 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f4 = this.m;
            if (f4 != 1.0d) {
                float f5 = this.l;
                if (f2 < f5) {
                    f2 = 0.0f;
                }
                if (f2 > f5 && f2 < 1.0d) {
                    f2 = (f2 - f5) * f4;
                }
            }
        }
        b.e.a.a.c cVar = this.f1298e.f1304d;
        float f6 = Float.NaN;
        for (p pVar : this.u) {
            b.e.a.a.c cVar2 = pVar.f1304d;
            if (cVar2 != null) {
                float f7 = pVar.x;
                if (f7 < f2) {
                    cVar = cVar2;
                    f3 = f7;
                } else if (Float.isNaN(f6)) {
                    f6 = pVar.x;
                }
            }
        }
        if (cVar != null) {
            float f8 = (Float.isNaN(f6) ? 1.0f : f6) - f3;
            double d2 = (f2 - f3) / f8;
            f2 = (((float) cVar.a(d2)) * f8) + f3;
            if (fArr != null) {
                fArr[0] = (float) cVar.b(d2);
            }
        }
        return f2;
    }

    public void g(float f2, float f3, float f4, float[] fArr) {
        double[] dArr;
        float f5 = f(f2, this.v);
        b.e.a.a.b[] bVarArr = this.f1302i;
        int i2 = 0;
        if (bVarArr == null) {
            p pVar = this.f1299f;
            float f6 = pVar.E3;
            p pVar2 = this.f1298e;
            float f7 = f6 - pVar2.E3;
            float f8 = pVar.F3 - pVar2.F3;
            float f9 = (pVar.G3 - pVar2.G3) + f7;
            float f10 = (pVar.H3 - pVar2.H3) + f8;
            fArr[0] = (f7 * (1.0f - f3)) + (f9 * f3);
            fArr[1] = (f8 * (1.0f - f4)) + (f10 * f4);
            return;
        }
        double d2 = f5;
        bVarArr[0].g(d2, this.p);
        this.f1302i[0].d(d2, this.o);
        float f11 = this.v[0];
        while (true) {
            dArr = this.p;
            if (i2 >= dArr.length) {
                break;
            }
            dArr[i2] = dArr[i2] * ((double) f11);
            i2++;
        }
        b.e.a.a.b bVar = this.j;
        if (bVar == null) {
            this.f1298e.o(f3, f4, fArr, this.n, dArr, this.o);
            return;
        }
        double[] dArr2 = this.o;
        if (dArr2.length > 0) {
            bVar.d(d2, dArr2);
            this.j.g(d2, this.p);
            this.f1298e.o(f3, f4, fArr, this.n, this.p, this.o);
        }
    }

    public int h() {
        int iMax = this.f1298e.s;
        Iterator<p> it = this.u.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().s);
        }
        return Math.max(iMax, this.f1299f.s);
    }

    public float i() {
        return this.f1299f.E3;
    }

    public float j() {
        return this.f1299f.F3;
    }

    public p k(int i2) {
        return this.u.get(i2);
    }

    public void l(float f2, int i2, int i3, float f3, float f4, float[] fArr) {
        float f5 = f(f2, this.v);
        HashMap<String, r> map = this.y;
        r rVar = map == null ? null : map.get("translationX");
        HashMap<String, r> map2 = this.y;
        r rVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, r> map3 = this.y;
        r rVar3 = map3 == null ? null : map3.get("rotation");
        HashMap<String, r> map4 = this.y;
        r rVar4 = map4 == null ? null : map4.get("scaleX");
        HashMap<String, r> map5 = this.y;
        r rVar5 = map5 == null ? null : map5.get("scaleY");
        HashMap<String, g> map6 = this.z;
        g gVar = map6 == null ? null : map6.get("translationX");
        HashMap<String, g> map7 = this.z;
        g gVar2 = map7 == null ? null : map7.get("translationY");
        HashMap<String, g> map8 = this.z;
        g gVar3 = map8 == null ? null : map8.get("rotation");
        HashMap<String, g> map9 = this.z;
        g gVar4 = map9 == null ? null : map9.get("scaleX");
        HashMap<String, g> map10 = this.z;
        g gVar5 = map10 != null ? map10.get("scaleY") : null;
        b.e.a.a.h hVar = new b.e.a.a.h();
        hVar.b();
        hVar.d(rVar3, f5);
        hVar.h(rVar, rVar2, f5);
        hVar.f(rVar4, rVar5, f5);
        hVar.c(gVar3, f5);
        hVar.g(gVar, gVar2, f5);
        hVar.e(gVar4, gVar5, f5);
        b.e.a.a.b bVar = this.j;
        if (bVar != null) {
            double[] dArr = this.o;
            if (dArr.length > 0) {
                double d2 = f5;
                bVar.d(d2, dArr);
                this.j.g(d2, this.p);
                this.f1298e.o(f3, f4, fArr, this.n, this.p, this.o);
            }
            hVar.a(f3, f4, i2, i3, fArr);
            return;
        }
        int i4 = 0;
        if (this.f1302i == null) {
            p pVar = this.f1299f;
            float f6 = pVar.E3;
            p pVar2 = this.f1298e;
            float f7 = f6 - pVar2.E3;
            g gVar6 = gVar5;
            float f8 = pVar.F3 - pVar2.F3;
            g gVar7 = gVar4;
            float f9 = (pVar.G3 - pVar2.G3) + f7;
            float f10 = (pVar.H3 - pVar2.H3) + f8;
            fArr[0] = (f7 * (1.0f - f3)) + (f9 * f3);
            fArr[1] = (f8 * (1.0f - f4)) + (f10 * f4);
            hVar.b();
            hVar.d(rVar3, f5);
            hVar.h(rVar, rVar2, f5);
            hVar.f(rVar4, rVar5, f5);
            hVar.c(gVar3, f5);
            hVar.g(gVar, gVar2, f5);
            hVar.e(gVar7, gVar6, f5);
            hVar.a(f3, f4, i2, i3, fArr);
            return;
        }
        double dF = f(f5, this.v);
        this.f1302i[0].g(dF, this.p);
        this.f1302i[0].d(dF, this.o);
        float f11 = this.v[0];
        while (true) {
            double[] dArr2 = this.p;
            if (i4 >= dArr2.length) {
                this.f1298e.o(f3, f4, fArr, this.n, dArr2, this.o);
                hVar.a(f3, f4, i2, i3, fArr);
                return;
            } else {
                dArr2[i4] = dArr2[i4] * ((double) f11);
                i4++;
            }
        }
    }

    public final float m() {
        float[] fArr = new float[2];
        float f2 = 1.0f / 99;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i2 = 0;
        float fHypot = 0.0f;
        while (i2 < 100) {
            float f3 = i2 * f2;
            double dA = f3;
            b.e.a.a.c cVar = this.f1298e.f1304d;
            float f4 = Float.NaN;
            float f5 = 0.0f;
            for (p pVar : this.u) {
                b.e.a.a.c cVar2 = pVar.f1304d;
                float f6 = f2;
                if (cVar2 != null) {
                    float f7 = pVar.x;
                    if (f7 < f3) {
                        f5 = f7;
                        cVar = cVar2;
                    } else if (Float.isNaN(f4)) {
                        f4 = pVar.x;
                    }
                }
                f2 = f6;
            }
            float f8 = f2;
            if (cVar != null) {
                if (Float.isNaN(f4)) {
                    f4 = 1.0f;
                }
                dA = (((float) cVar.a((f3 - f5) / r16)) * (f4 - f5)) + f5;
            }
            this.f1302i[0].d(dA, this.o);
            this.f1298e.f(this.n, this.o, fArr, 0);
            if (i2 > 0) {
                fHypot = (float) (((double) fHypot) + Math.hypot(d3 - ((double) fArr[1]), d2 - ((double) fArr[0])));
            }
            d2 = fArr[0];
            d3 = fArr[1];
            i2++;
            f2 = f8;
        }
        return fHypot;
    }

    public final void n(p pVar) {
        if (Collections.binarySearch(this.u, pVar) == 0) {
            Log.e("MotionController", " KeyPath positon \"" + pVar.y + "\" outside of range");
        }
        this.u.add((-r0) - 1, pVar);
    }

    public boolean o(View view, float f2, long j, e eVar) {
        s.d dVar;
        boolean zJ;
        double d2;
        float f3 = f(f2, null);
        HashMap<String, r> map = this.y;
        if (map != null) {
            Iterator<r> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().f(view, f3);
            }
        }
        HashMap<String, s> map2 = this.x;
        if (map2 != null) {
            dVar = null;
            boolean zF = false;
            for (s sVar : map2.values()) {
                if (sVar instanceof s.d) {
                    dVar = (s.d) sVar;
                } else {
                    zF |= sVar.f(view, f3, j, eVar);
                }
            }
            zJ = zF;
        } else {
            dVar = null;
            zJ = false;
        }
        b.e.a.a.b[] bVarArr = this.f1302i;
        if (bVarArr != null) {
            double d3 = f3;
            bVarArr[0].d(d3, this.o);
            this.f1302i[0].g(d3, this.p);
            b.e.a.a.b bVar = this.j;
            if (bVar != null) {
                double[] dArr = this.o;
                if (dArr.length > 0) {
                    bVar.d(d3, dArr);
                    this.j.g(d3, this.p);
                }
            }
            this.f1298e.p(view, this.n, this.o, this.p, null);
            HashMap<String, r> map3 = this.y;
            if (map3 != null) {
                for (r rVar : map3.values()) {
                    if (rVar instanceof r.d) {
                        double[] dArr2 = this.p;
                        ((r.d) rVar).i(view, f3, dArr2[0], dArr2[1]);
                    }
                }
            }
            if (dVar != null) {
                double[] dArr3 = this.p;
                d2 = d3;
                zJ = dVar.j(view, eVar, f3, j, dArr3[0], dArr3[1]) | zJ;
            } else {
                d2 = d3;
            }
            int i2 = 1;
            while (true) {
                b.e.a.a.b[] bVarArr2 = this.f1302i;
                if (i2 >= bVarArr2.length) {
                    break;
                }
                bVarArr2[i2].e(d2, this.t);
                this.f1298e.L3.get(this.q[i2 - 1]).i(view, this.t);
                i2++;
            }
            m mVar = this.f1300g;
            if (mVar.s == 0) {
                if (f3 <= 0.0f) {
                    view.setVisibility(mVar.x);
                } else if (f3 >= 1.0f) {
                    view.setVisibility(this.f1301h.x);
                } else if (this.f1301h.x != mVar.x) {
                    view.setVisibility(0);
                }
            }
            if (this.A != null) {
                int i3 = 0;
                while (true) {
                    l[] lVarArr = this.A;
                    if (i3 >= lVarArr.length) {
                        break;
                    }
                    lVarArr[i3].r(f3, view);
                    i3++;
                }
            }
        } else {
            p pVar = this.f1298e;
            float f4 = pVar.E3;
            p pVar2 = this.f1299f;
            float f5 = f4 + ((pVar2.E3 - f4) * f3);
            float f6 = pVar.F3;
            float f7 = f6 + ((pVar2.F3 - f6) * f3);
            float f8 = pVar.G3;
            float f9 = pVar2.G3;
            float f10 = pVar.H3;
            float f11 = pVar2.H3;
            float f12 = f5 + 0.5f;
            int i4 = (int) f12;
            float f13 = f7 + 0.5f;
            int i5 = (int) f13;
            int i6 = (int) (f12 + ((f9 - f8) * f3) + f8);
            int i7 = (int) (f13 + ((f11 - f10) * f3) + f10);
            int i8 = i6 - i4;
            int i9 = i7 - i5;
            if (f9 != f8 || f11 != f10) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i8, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i9, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
            view.layout(i4, i5, i6, i7);
        }
        HashMap<String, g> map4 = this.z;
        if (map4 != null) {
            for (g gVar : map4.values()) {
                if (gVar instanceof g.f) {
                    double[] dArr4 = this.p;
                    ((g.f) gVar).j(view, f3, dArr4[0], dArr4[1]);
                } else {
                    gVar.f(view, f3);
                }
            }
        }
        return zJ;
    }

    public final void p(p pVar) {
        pVar.n((int) this.f1294a.getX(), (int) this.f1294a.getY(), this.f1294a.getWidth(), this.f1294a.getHeight());
    }

    public void q(ConstraintWidget constraintWidget, b.e.c.b bVar) {
        p pVar = this.f1299f;
        pVar.x = 1.0f;
        pVar.y = 1.0f;
        p(pVar);
        this.f1299f.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        this.f1299f.a(bVar.q(this.f1295b));
        this.f1301h.i(constraintWidget, bVar, this.f1295b);
    }

    public void r(int i2) {
        this.B = i2;
    }

    public void s(View view) {
        p pVar = this.f1298e;
        pVar.x = 0.0f;
        pVar.y = 0.0f;
        pVar.n(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f1300g.h(view);
    }

    public void t(ConstraintWidget constraintWidget, b.e.c.b bVar) {
        p pVar = this.f1298e;
        pVar.x = 0.0f;
        pVar.y = 0.0f;
        p(pVar);
        this.f1298e.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        b.a aVarQ = bVar.q(this.f1295b);
        this.f1298e.a(aVarQ);
        this.k = aVarQ.f1478c.f1497g;
        this.f1300g.i(constraintWidget, bVar, this.f1295b);
    }

    public String toString() {
        return " start: x: " + this.f1298e.E3 + " y: " + this.f1298e.F3 + " end: x: " + this.f1299f.E3 + " y: " + this.f1299f.F3;
    }

    public void u(View view) {
        this.f1294a = view;
        this.f1295b = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            this.f1296c = ((ConstraintLayout.b) layoutParams).a();
        }
    }

    public void v(int i2, int i3, float f2, long j) {
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
        int i4 = this.B;
        if (i4 != c.f1240a) {
            this.f1298e.K3 = i4;
        }
        this.f1300g.f(this.f1301h, hashSet2);
        ArrayList<c> arrayList2 = this.w;
        if (arrayList2 != null) {
            arrayList = null;
            for (c cVar : arrayList2) {
                if (cVar instanceof i) {
                    i iVar = (i) cVar;
                    n(new p(i2, i3, iVar, this.f1298e, this.f1299f));
                    int i5 = iVar.f1283g;
                    if (i5 != c.f1240a) {
                        this.f1297d = i5;
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
        int i6 = 0;
        if (arrayList != null) {
            this.A = (l[]) arrayList.toArray(new l[0]);
        }
        char c2 = 1;
        if (!hashSet2.isEmpty()) {
            this.y = new HashMap<>();
            for (String str : hashSet2) {
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(",")[c2];
                    for (c cVar2 : this.w) {
                        HashMap<String, ConstraintAttribute> map2 = cVar2.f1245f;
                        if (map2 != null && (constraintAttribute2 = map2.get(str2)) != null) {
                            sparseArray.append(cVar2.f1241b, constraintAttribute2);
                        }
                    }
                    rVarD = r.c(str, sparseArray);
                } else {
                    rVarD = r.d(str);
                }
                if (rVarD != null) {
                    rVarD.g(str);
                    this.y.put(str, rVarD);
                }
                c2 = 1;
            }
            ArrayList<c> arrayList3 = this.w;
            if (arrayList3 != null) {
                for (c cVar3 : arrayList3) {
                    if (cVar3 instanceof d) {
                        cVar3.a(this.y);
                    }
                }
            }
            this.f1300g.a(this.y, 0);
            this.f1301h.a(this.y, 100);
            for (String str3 : this.y.keySet()) {
                this.y.get(str3).h(map.containsKey(str3) ? map.get(str3).intValue() : 0);
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.x == null) {
                this.x = new HashMap<>();
            }
            for (String str4 : hashSet) {
                if (!this.x.containsKey(str4)) {
                    if (str4.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str5 = str4.split(",")[1];
                        for (c cVar4 : this.w) {
                            HashMap<String, ConstraintAttribute> map3 = cVar4.f1245f;
                            if (map3 != null && (constraintAttribute = map3.get(str5)) != null) {
                                sparseArray2.append(cVar4.f1241b, constraintAttribute);
                            }
                        }
                        sVarD = s.c(str4, sparseArray2);
                    } else {
                        sVarD = s.d(str4, j);
                    }
                    if (sVarD != null) {
                        sVarD.h(str4);
                        this.x.put(str4, sVarD);
                    }
                }
            }
            ArrayList<c> arrayList4 = this.w;
            if (arrayList4 != null) {
                for (c cVar5 : arrayList4) {
                    if (cVar5 instanceof k) {
                        ((k) cVar5).M(this.x);
                    }
                }
            }
            for (String str6 : this.x.keySet()) {
                this.x.get(str6).i(map.containsKey(str6) ? map.get(str6).intValue() : 0);
            }
        }
        int i7 = 2;
        int size = this.u.size() + 2;
        p[] pVarArr = new p[size];
        pVarArr[0] = this.f1298e;
        pVarArr[size - 1] = this.f1299f;
        if (this.u.size() > 0 && this.f1297d == -1) {
            this.f1297d = 0;
        }
        Iterator<p> it = this.u.iterator();
        int i8 = 1;
        while (it.hasNext()) {
            pVarArr[i8] = it.next();
            i8++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.f1299f.L3.keySet()) {
            if (this.f1298e.L3.containsKey(str7)) {
                if (!hashSet2.contains("CUSTOM," + str7)) {
                    hashSet4.add(str7);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.q = strArr2;
        this.r = new int[strArr2.length];
        int i9 = 0;
        while (true) {
            strArr = this.q;
            if (i9 >= strArr.length) {
                break;
            }
            String str8 = strArr[i9];
            this.r[i9] = 0;
            int i10 = 0;
            while (true) {
                if (i10 >= size) {
                    break;
                }
                if (pVarArr[i10].L3.containsKey(str8)) {
                    int[] iArr = this.r;
                    iArr[i9] = iArr[i9] + pVarArr[i10].L3.get(str8).f();
                    break;
                }
                i10++;
            }
            i9++;
        }
        boolean z = pVarArr[0].K3 != c.f1240a;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i11 = 1; i11 < size; i11++) {
            pVarArr[i11].d(pVarArr[i11 - 1], zArr, this.q, z);
        }
        int i12 = 0;
        for (int i13 = 1; i13 < length; i13++) {
            if (zArr[i13]) {
                i12++;
            }
        }
        int[] iArr2 = new int[i12];
        this.n = iArr2;
        this.o = new double[iArr2.length];
        this.p = new double[iArr2.length];
        int i14 = 0;
        for (int i15 = 1; i15 < length; i15++) {
            if (zArr[i15]) {
                this.n[i14] = i15;
                i14++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, size, this.n.length);
        double[] dArr2 = new double[size];
        for (int i16 = 0; i16 < size; i16++) {
            pVarArr[i16].e(dArr[i16], this.n);
            dArr2[i16] = pVarArr[i16].x;
        }
        int i17 = 0;
        while (true) {
            int[] iArr3 = this.n;
            if (i17 >= iArr3.length) {
                break;
            }
            if (iArr3[i17] < p.f1303c.length) {
                String str9 = p.f1303c[this.n[i17]] + " [";
                for (int i18 = 0; i18 < size; i18++) {
                    str9 = str9 + dArr[i18][i17];
                }
            }
            i17++;
        }
        this.f1302i = new b.e.a.a.b[this.q.length + 1];
        int i19 = 0;
        while (true) {
            String[] strArr3 = this.q;
            if (i19 >= strArr3.length) {
                break;
            }
            String str10 = strArr3[i19];
            int i20 = i6;
            int i21 = i20;
            double[] dArr3 = null;
            double[][] dArr4 = null;
            while (i20 < size) {
                if (pVarArr[i20].j(str10)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr4 = new int[i7];
                        iArr4[1] = pVarArr[i20].h(str10);
                        iArr4[i6] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr4);
                    }
                    dArr3[i21] = pVarArr[i20].x;
                    pVarArr[i20].g(str10, dArr4[i21], 0);
                    i21++;
                }
                i20++;
                i7 = 2;
                i6 = 0;
            }
            i19++;
            this.f1302i[i19] = b.e.a.a.b.a(this.f1297d, Arrays.copyOf(dArr3, i21), (double[][]) Arrays.copyOf(dArr4, i21));
            i7 = 2;
            i6 = 0;
        }
        this.f1302i[0] = b.e.a.a.b.a(this.f1297d, dArr2, dArr);
        if (pVarArr[0].K3 != c.f1240a) {
            int[] iArr5 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i22 = 0; i22 < size; i22++) {
                iArr5[i22] = pVarArr[i22].K3;
                dArr5[i22] = pVarArr[i22].x;
                dArr6[i22][0] = pVarArr[i22].E3;
                dArr6[i22][1] = pVarArr[i22].F3;
            }
            this.j = b.e.a.a.b.b(iArr5, dArr5, dArr6);
        }
        float fM = Float.NaN;
        this.z = new HashMap<>();
        if (this.w != null) {
            for (String str11 : hashSet3) {
                g gVarC = g.c(str11);
                if (gVarC != null) {
                    if (gVarC.i() && Float.isNaN(fM)) {
                        fM = m();
                    }
                    gVarC.g(str11);
                    this.z.put(str11, gVarC);
                }
            }
            for (c cVar6 : this.w) {
                if (cVar6 instanceof f) {
                    ((f) cVar6).O(this.z);
                }
            }
            Iterator<g> it2 = this.z.values().iterator();
            while (it2.hasNext()) {
                it2.next().h(fM);
            }
        }
    }
}
