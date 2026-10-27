package u;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import u.g;
import u.r;
import u.s;
import y.b;

/* JADX INFO: compiled from: MotionController.java */
/* JADX INFO: loaded from: classes.dex */
public class n {
    public l[] A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f5201a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5202b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5203c;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public t.b[] f5209i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public t.b f5210j;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f5214n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public double[] f5215o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public double[] f5216p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String[] f5217q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int[] f5218r;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public HashMap<String, s> f5224x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public HashMap<String, r> f5225y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public HashMap<String, g> f5226z;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5204d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public p f5205e = new p();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public p f5206f = new p();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m f5207g = new m();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public m f5208h = new m();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5211k = Float.NaN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f5212l = 0.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f5213m = 1.0f;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f5219s = 4;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float[] f5220t = new float[4];

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ArrayList<p> f5221u = new ArrayList<>();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float[] f5222v = new float[1];

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ArrayList<c> f5223w = new ArrayList<>();
    public int B = c.f5050a;

    public n(View view) {
        u(view);
    }

    public void a(c cVar) {
        this.f5223w.add(cVar);
    }

    public void b(ArrayList<c> arrayList) {
        this.f5223w.addAll(arrayList);
    }

    public int c(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] dArrH = this.f5209i[0].h();
        if (iArr != null) {
            Iterator<p> it = this.f5221u.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                iArr[i3] = it.next().f5240o;
                i3++;
            }
        }
        int i4 = 0;
        for (double d3 : dArrH) {
            this.f5209i[0].d(d3, this.f5215o);
            this.f5205e.f(this.f5214n, this.f5215o, fArr, i4);
            i4 += 2;
        }
        return i4 / 2;
    }

    public void d(float[] fArr, int i3) {
        int i4 = i3;
        float f3 = 1.0f;
        float f4 = 1.0f / (i4 - 1);
        HashMap<String, r> map = this.f5225y;
        r rVar = map == null ? null : map.get("translationX");
        HashMap<String, r> map2 = this.f5225y;
        r rVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, g> map3 = this.f5226z;
        g gVar = map3 == null ? null : map3.get("translationX");
        HashMap<String, g> map4 = this.f5226z;
        g gVar2 = map4 != null ? map4.get("translationY") : null;
        int i5 = 0;
        while (i5 < i4) {
            float f5 = i5 * f4;
            float f6 = this.f5213m;
            if (f6 != f3) {
                float f7 = this.f5212l;
                if (f5 < f7) {
                    f5 = 0.0f;
                }
                if (f5 > f7 && f5 < 1.0d) {
                    f5 = (f5 - f7) * f6;
                }
            }
            double dA = f5;
            t.c cVar = this.f5205e.f5228c;
            float f8 = Float.NaN;
            float f9 = 0.0f;
            for (p pVar : this.f5221u) {
                t.c cVar2 = pVar.f5228c;
                if (cVar2 != null) {
                    float f10 = pVar.f5230e;
                    if (f10 < f5) {
                        f9 = f10;
                        cVar = cVar2;
                    } else if (Float.isNaN(f8)) {
                        f8 = pVar.f5230e;
                    }
                }
            }
            if (cVar != null) {
                if (Float.isNaN(f8)) {
                    f8 = 1.0f;
                }
                dA = (((float) cVar.a((f5 - f9) / r15)) * (f8 - f9)) + f9;
            }
            this.f5209i[0].d(dA, this.f5215o);
            t.b bVar = this.f5210j;
            if (bVar != null) {
                double[] dArr = this.f5215o;
                if (dArr.length > 0) {
                    bVar.d(dA, dArr);
                }
            }
            int i6 = i5 * 2;
            this.f5205e.f(this.f5214n, this.f5215o, fArr, i6);
            if (gVar != null) {
                fArr[i6] = fArr[i6] + gVar.a(f5);
            } else if (rVar != null) {
                fArr[i6] = fArr[i6] + rVar.a(f5);
            }
            if (gVar2 != null) {
                int i7 = i6 + 1;
                fArr[i7] = fArr[i7] + gVar2.a(f5);
            } else if (rVar2 != null) {
                int i8 = i6 + 1;
                fArr[i8] = fArr[i8] + rVar2.a(f5);
            }
            i5++;
            i4 = i3;
            f3 = 1.0f;
        }
    }

    public void e(float f3, float[] fArr, int i3) {
        this.f5209i[0].d(f(f3, null), this.f5215o);
        this.f5205e.i(this.f5214n, this.f5215o, fArr, i3);
    }

    public final float f(float f3, float[] fArr) {
        float f4 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f5 = this.f5213m;
            if (f5 != 1.0d) {
                float f6 = this.f5212l;
                if (f3 < f6) {
                    f3 = 0.0f;
                }
                if (f3 > f6 && f3 < 1.0d) {
                    f3 = (f3 - f6) * f5;
                }
            }
        }
        t.c cVar = this.f5205e.f5228c;
        float f7 = Float.NaN;
        for (p pVar : this.f5221u) {
            t.c cVar2 = pVar.f5228c;
            if (cVar2 != null) {
                float f8 = pVar.f5230e;
                if (f8 < f3) {
                    cVar = cVar2;
                    f4 = f8;
                } else if (Float.isNaN(f7)) {
                    f7 = pVar.f5230e;
                }
            }
        }
        if (cVar != null) {
            float f9 = (Float.isNaN(f7) ? 1.0f : f7) - f4;
            double d3 = (f3 - f4) / f9;
            f3 = (((float) cVar.a(d3)) * f9) + f4;
            if (fArr != null) {
                fArr[0] = (float) cVar.b(d3);
            }
        }
        return f3;
    }

    public void g(float f3, float f4, float f5, float[] fArr) {
        double[] dArr;
        float f6 = f(f3, this.f5222v);
        t.b[] bVarArr = this.f5209i;
        int i3 = 0;
        if (bVarArr == null) {
            p pVar = this.f5206f;
            float f7 = pVar.f5232g;
            p pVar2 = this.f5205e;
            float f8 = f7 - pVar2.f5232g;
            float f9 = pVar.f5233h - pVar2.f5233h;
            float f10 = (pVar.f5234i - pVar2.f5234i) + f8;
            float f11 = (pVar.f5235j - pVar2.f5235j) + f9;
            fArr[0] = (f8 * (1.0f - f4)) + (f10 * f4);
            fArr[1] = (f9 * (1.0f - f5)) + (f11 * f5);
            return;
        }
        double d3 = f6;
        bVarArr[0].g(d3, this.f5216p);
        this.f5209i[0].d(d3, this.f5215o);
        float f12 = this.f5222v[0];
        while (true) {
            dArr = this.f5216p;
            if (i3 >= dArr.length) {
                break;
            }
            dArr[i3] = dArr[i3] * ((double) f12);
            i3++;
        }
        t.b bVar = this.f5210j;
        if (bVar == null) {
            this.f5205e.o(f4, f5, fArr, this.f5214n, dArr, this.f5215o);
            return;
        }
        double[] dArr2 = this.f5215o;
        if (dArr2.length > 0) {
            bVar.d(d3, dArr2);
            this.f5210j.g(d3, this.f5216p);
            this.f5205e.o(f4, f5, fArr, this.f5214n, this.f5216p, this.f5215o);
        }
    }

    public int h() {
        int iMax = this.f5205e.f5229d;
        Iterator<p> it = this.f5221u.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().f5229d);
        }
        return Math.max(iMax, this.f5206f.f5229d);
    }

    public float i() {
        return this.f5206f.f5232g;
    }

    public float j() {
        return this.f5206f.f5233h;
    }

    public p k(int i3) {
        return this.f5221u.get(i3);
    }

    public void l(float f3, int i3, int i4, float f4, float f5, float[] fArr) {
        float f6 = f(f3, this.f5222v);
        HashMap<String, r> map = this.f5225y;
        r rVar = map == null ? null : map.get("translationX");
        HashMap<String, r> map2 = this.f5225y;
        r rVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, r> map3 = this.f5225y;
        r rVar3 = map3 == null ? null : map3.get("rotation");
        HashMap<String, r> map4 = this.f5225y;
        r rVar4 = map4 == null ? null : map4.get("scaleX");
        HashMap<String, r> map5 = this.f5225y;
        r rVar5 = map5 == null ? null : map5.get("scaleY");
        HashMap<String, g> map6 = this.f5226z;
        g gVar = map6 == null ? null : map6.get("translationX");
        HashMap<String, g> map7 = this.f5226z;
        g gVar2 = map7 == null ? null : map7.get("translationY");
        HashMap<String, g> map8 = this.f5226z;
        g gVar3 = map8 == null ? null : map8.get("rotation");
        HashMap<String, g> map9 = this.f5226z;
        g gVar4 = map9 == null ? null : map9.get("scaleX");
        HashMap<String, g> map10 = this.f5226z;
        g gVar5 = map10 != null ? map10.get("scaleY") : null;
        t.h hVar = new t.h();
        hVar.b();
        hVar.d(rVar3, f6);
        hVar.h(rVar, rVar2, f6);
        hVar.f(rVar4, rVar5, f6);
        hVar.c(gVar3, f6);
        hVar.g(gVar, gVar2, f6);
        hVar.e(gVar4, gVar5, f6);
        t.b bVar = this.f5210j;
        if (bVar != null) {
            double[] dArr = this.f5215o;
            if (dArr.length > 0) {
                double d3 = f6;
                bVar.d(d3, dArr);
                this.f5210j.g(d3, this.f5216p);
                this.f5205e.o(f4, f5, fArr, this.f5214n, this.f5216p, this.f5215o);
            }
            hVar.a(f4, f5, i3, i4, fArr);
            return;
        }
        int i5 = 0;
        if (this.f5209i == null) {
            p pVar = this.f5206f;
            float f7 = pVar.f5232g;
            p pVar2 = this.f5205e;
            float f8 = f7 - pVar2.f5232g;
            g gVar6 = gVar5;
            float f9 = pVar.f5233h - pVar2.f5233h;
            g gVar7 = gVar4;
            float f10 = (pVar.f5234i - pVar2.f5234i) + f8;
            float f11 = (pVar.f5235j - pVar2.f5235j) + f9;
            fArr[0] = (f8 * (1.0f - f4)) + (f10 * f4);
            fArr[1] = (f9 * (1.0f - f5)) + (f11 * f5);
            hVar.b();
            hVar.d(rVar3, f6);
            hVar.h(rVar, rVar2, f6);
            hVar.f(rVar4, rVar5, f6);
            hVar.c(gVar3, f6);
            hVar.g(gVar, gVar2, f6);
            hVar.e(gVar7, gVar6, f6);
            hVar.a(f4, f5, i3, i4, fArr);
            return;
        }
        double dF = f(f6, this.f5222v);
        this.f5209i[0].g(dF, this.f5216p);
        this.f5209i[0].d(dF, this.f5215o);
        float f12 = this.f5222v[0];
        while (true) {
            double[] dArr2 = this.f5216p;
            if (i5 >= dArr2.length) {
                this.f5205e.o(f4, f5, fArr, this.f5214n, dArr2, this.f5215o);
                hVar.a(f4, f5, i3, i4, fArr);
                return;
            } else {
                dArr2[i5] = dArr2[i5] * ((double) f12);
                i5++;
            }
        }
    }

    public final float m() {
        float[] fArr = new float[2];
        float f3 = 1.0f / 99;
        double d3 = 0.0d;
        double d4 = 0.0d;
        int i3 = 0;
        float fHypot = 0.0f;
        while (i3 < 100) {
            float f4 = i3 * f3;
            double dA = f4;
            t.c cVar = this.f5205e.f5228c;
            float f5 = Float.NaN;
            float f6 = 0.0f;
            for (p pVar : this.f5221u) {
                t.c cVar2 = pVar.f5228c;
                float f7 = f3;
                if (cVar2 != null) {
                    float f8 = pVar.f5230e;
                    if (f8 < f4) {
                        f6 = f8;
                        cVar = cVar2;
                    } else if (Float.isNaN(f5)) {
                        f5 = pVar.f5230e;
                    }
                }
                f3 = f7;
            }
            float f9 = f3;
            if (cVar != null) {
                if (Float.isNaN(f5)) {
                    f5 = 1.0f;
                }
                dA = (((float) cVar.a((f4 - f6) / r16)) * (f5 - f6)) + f6;
            }
            this.f5209i[0].d(dA, this.f5215o);
            this.f5205e.f(this.f5214n, this.f5215o, fArr, 0);
            if (i3 > 0) {
                fHypot = (float) (((double) fHypot) + Math.hypot(d4 - ((double) fArr[1]), d3 - ((double) fArr[0])));
            }
            d3 = fArr[0];
            d4 = fArr[1];
            i3++;
            f3 = f9;
        }
        return fHypot;
    }

    public final void n(p pVar) {
        if (Collections.binarySearch(this.f5221u, pVar) == 0) {
            Log.e("MotionController", " KeyPath positon \"" + pVar.f5231f + "\" outside of range");
        }
        this.f5221u.add((-r0) - 1, pVar);
    }

    public boolean o(View view, float f3, long j3, e eVar) {
        s.d dVar;
        boolean zJ;
        double d3;
        float f4 = f(f3, null);
        HashMap<String, r> map = this.f5225y;
        if (map != null) {
            Iterator<r> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().f(view, f4);
            }
        }
        HashMap<String, s> map2 = this.f5224x;
        if (map2 != null) {
            dVar = null;
            boolean zF = false;
            for (s sVar : map2.values()) {
                if (sVar instanceof s.d) {
                    dVar = (s.d) sVar;
                } else {
                    zF |= sVar.f(view, f4, j3, eVar);
                }
            }
            zJ = zF;
        } else {
            dVar = null;
            zJ = false;
        }
        t.b[] bVarArr = this.f5209i;
        if (bVarArr != null) {
            double d4 = f4;
            bVarArr[0].d(d4, this.f5215o);
            this.f5209i[0].g(d4, this.f5216p);
            t.b bVar = this.f5210j;
            if (bVar != null) {
                double[] dArr = this.f5215o;
                if (dArr.length > 0) {
                    bVar.d(d4, dArr);
                    this.f5210j.g(d4, this.f5216p);
                }
            }
            this.f5205e.p(view, this.f5214n, this.f5215o, this.f5216p, null);
            HashMap<String, r> map3 = this.f5225y;
            if (map3 != null) {
                for (r rVar : map3.values()) {
                    if (rVar instanceof r.d) {
                        double[] dArr2 = this.f5216p;
                        ((r.d) rVar).i(view, f4, dArr2[0], dArr2[1]);
                    }
                }
            }
            if (dVar != null) {
                double[] dArr3 = this.f5216p;
                d3 = d4;
                zJ = dVar.j(view, eVar, f4, j3, dArr3[0], dArr3[1]) | zJ;
            } else {
                d3 = d4;
            }
            int i3 = 1;
            while (true) {
                t.b[] bVarArr2 = this.f5209i;
                if (i3 >= bVarArr2.length) {
                    break;
                }
                bVarArr2[i3].e(d3, this.f5220t);
                this.f5205e.f5239n.get(this.f5217q[i3 - 1]).i(view, this.f5220t);
                i3++;
            }
            m mVar = this.f5207g;
            if (mVar.f5178d == 0) {
                if (f4 <= 0.0f) {
                    view.setVisibility(mVar.f5179e);
                } else if (f4 >= 1.0f) {
                    view.setVisibility(this.f5208h.f5179e);
                } else if (this.f5208h.f5179e != mVar.f5179e) {
                    view.setVisibility(0);
                }
            }
            if (this.A != null) {
                int i4 = 0;
                while (true) {
                    l[] lVarArr = this.A;
                    if (i4 >= lVarArr.length) {
                        break;
                    }
                    lVarArr[i4].r(f4, view);
                    i4++;
                }
            }
        } else {
            p pVar = this.f5205e;
            float f5 = pVar.f5232g;
            p pVar2 = this.f5206f;
            float f6 = f5 + ((pVar2.f5232g - f5) * f4);
            float f7 = pVar.f5233h;
            float f8 = f7 + ((pVar2.f5233h - f7) * f4);
            float f9 = pVar.f5234i;
            float f10 = pVar2.f5234i;
            float f11 = pVar.f5235j;
            float f12 = pVar2.f5235j;
            float f13 = f6 + 0.5f;
            int i5 = (int) f13;
            float f14 = f8 + 0.5f;
            int i6 = (int) f14;
            int i7 = (int) (f13 + ((f10 - f9) * f4) + f9);
            int i8 = (int) (f14 + ((f12 - f11) * f4) + f11);
            int i9 = i7 - i5;
            int i10 = i8 - i6;
            if (f10 != f9 || f12 != f11) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i9, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
            view.layout(i5, i6, i7, i8);
        }
        HashMap<String, g> map4 = this.f5226z;
        if (map4 != null) {
            for (g gVar : map4.values()) {
                if (gVar instanceof g.f) {
                    double[] dArr4 = this.f5216p;
                    ((g.f) gVar).j(view, f4, dArr4[0], dArr4[1]);
                } else {
                    gVar.f(view, f4);
                }
            }
        }
        return zJ;
    }

    public final void p(p pVar) {
        pVar.n((int) this.f5201a.getX(), (int) this.f5201a.getY(), this.f5201a.getWidth(), this.f5201a.getHeight());
    }

    public void q(ConstraintWidget constraintWidget, y.b bVar) {
        p pVar = this.f5206f;
        pVar.f5230e = 1.0f;
        pVar.f5231f = 1.0f;
        p(pVar);
        this.f5206f.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        this.f5206f.a(bVar.q(this.f5202b));
        this.f5208h.i(constraintWidget, bVar, this.f5202b);
    }

    public void r(int i3) {
        this.B = i3;
    }

    public void s(View view) {
        p pVar = this.f5205e;
        pVar.f5230e = 0.0f;
        pVar.f5231f = 0.0f;
        pVar.n(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f5207g.h(view);
    }

    public void t(ConstraintWidget constraintWidget, y.b bVar) {
        p pVar = this.f5205e;
        pVar.f5230e = 0.0f;
        pVar.f5231f = 0.0f;
        p(pVar);
        this.f5205e.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        b.a aVarQ = bVar.q(this.f5202b);
        this.f5205e.a(aVarQ);
        this.f5211k = aVarQ.f5768c.f5815g;
        this.f5207g.i(constraintWidget, bVar, this.f5202b);
    }

    public String toString() {
        return " start: x: " + this.f5205e.f5232g + " y: " + this.f5205e.f5233h + " end: x: " + this.f5206f.f5232g + " y: " + this.f5206f.f5233h;
    }

    public void u(View view) {
        this.f5201a = view;
        this.f5202b = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            this.f5203c = ((ConstraintLayout.b) layoutParams).a();
        }
    }

    public void v(int i3, int i4, float f3, long j3) {
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
        int i5 = this.B;
        if (i5 != c.f5050a) {
            this.f5205e.f5238m = i5;
        }
        this.f5207g.f(this.f5208h, hashSet2);
        ArrayList<c> arrayList2 = this.f5223w;
        if (arrayList2 != null) {
            arrayList = null;
            for (c cVar : arrayList2) {
                if (cVar instanceof i) {
                    i iVar = (i) cVar;
                    n(new p(i3, i4, iVar, this.f5205e, this.f5206f));
                    int i6 = iVar.f5136g;
                    if (i6 != c.f5050a) {
                        this.f5204d = i6;
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
        int i7 = 0;
        if (arrayList != null) {
            this.A = (l[]) arrayList.toArray(new l[0]);
        }
        char c3 = 1;
        if (!hashSet2.isEmpty()) {
            this.f5225y = new HashMap<>();
            for (String str : hashSet2) {
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[c3];
                    for (c cVar2 : this.f5223w) {
                        HashMap<String, ConstraintAttribute> map2 = cVar2.f5055f;
                        if (map2 != null && (constraintAttribute2 = map2.get(str2)) != null) {
                            sparseArray.append(cVar2.f5051b, constraintAttribute2);
                        }
                    }
                    rVarD = r.c(str, sparseArray);
                } else {
                    rVarD = r.d(str);
                }
                if (rVarD != null) {
                    rVarD.g(str);
                    this.f5225y.put(str, rVarD);
                }
                c3 = 1;
            }
            ArrayList<c> arrayList3 = this.f5223w;
            if (arrayList3 != null) {
                for (c cVar3 : arrayList3) {
                    if (cVar3 instanceof d) {
                        cVar3.a(this.f5225y);
                    }
                }
            }
            this.f5207g.a(this.f5225y, 0);
            this.f5208h.a(this.f5225y, 100);
            for (String str3 : this.f5225y.keySet()) {
                this.f5225y.get(str3).h(map.containsKey(str3) ? map.get(str3).intValue() : 0);
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.f5224x == null) {
                this.f5224x = new HashMap<>();
            }
            for (String str4 : hashSet) {
                if (!this.f5224x.containsKey(str4)) {
                    if (str4.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str5 = str4.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
                        for (c cVar4 : this.f5223w) {
                            HashMap<String, ConstraintAttribute> map3 = cVar4.f5055f;
                            if (map3 != null && (constraintAttribute = map3.get(str5)) != null) {
                                sparseArray2.append(cVar4.f5051b, constraintAttribute);
                            }
                        }
                        sVarD = s.c(str4, sparseArray2);
                    } else {
                        sVarD = s.d(str4, j3);
                    }
                    if (sVarD != null) {
                        sVarD.h(str4);
                        this.f5224x.put(str4, sVarD);
                    }
                }
            }
            ArrayList<c> arrayList4 = this.f5223w;
            if (arrayList4 != null) {
                for (c cVar5 : arrayList4) {
                    if (cVar5 instanceof k) {
                        ((k) cVar5).M(this.f5224x);
                    }
                }
            }
            for (String str6 : this.f5224x.keySet()) {
                this.f5224x.get(str6).i(map.containsKey(str6) ? map.get(str6).intValue() : 0);
            }
        }
        int i8 = 2;
        int size = this.f5221u.size() + 2;
        p[] pVarArr = new p[size];
        pVarArr[0] = this.f5205e;
        pVarArr[size - 1] = this.f5206f;
        if (this.f5221u.size() > 0 && this.f5204d == -1) {
            this.f5204d = 0;
        }
        Iterator<p> it = this.f5221u.iterator();
        int i9 = 1;
        while (it.hasNext()) {
            pVarArr[i9] = it.next();
            i9++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.f5206f.f5239n.keySet()) {
            if (this.f5205e.f5239n.containsKey(str7)) {
                if (!hashSet2.contains("CUSTOM," + str7)) {
                    hashSet4.add(str7);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.f5217q = strArr2;
        this.f5218r = new int[strArr2.length];
        int i10 = 0;
        while (true) {
            strArr = this.f5217q;
            if (i10 >= strArr.length) {
                break;
            }
            String str8 = strArr[i10];
            this.f5218r[i10] = 0;
            int i11 = 0;
            while (true) {
                if (i11 >= size) {
                    break;
                }
                if (pVarArr[i11].f5239n.containsKey(str8)) {
                    int[] iArr = this.f5218r;
                    iArr[i10] = iArr[i10] + pVarArr[i11].f5239n.get(str8).f();
                    break;
                }
                i11++;
            }
            i10++;
        }
        boolean z3 = pVarArr[0].f5238m != c.f5050a;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i12 = 1; i12 < size; i12++) {
            pVarArr[i12].d(pVarArr[i12 - 1], zArr, this.f5217q, z3);
        }
        int i13 = 0;
        for (int i14 = 1; i14 < length; i14++) {
            if (zArr[i14]) {
                i13++;
            }
        }
        int[] iArr2 = new int[i13];
        this.f5214n = iArr2;
        this.f5215o = new double[iArr2.length];
        this.f5216p = new double[iArr2.length];
        int i15 = 0;
        for (int i16 = 1; i16 < length; i16++) {
            if (zArr[i16]) {
                this.f5214n[i15] = i16;
                i15++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, size, this.f5214n.length);
        double[] dArr2 = new double[size];
        for (int i17 = 0; i17 < size; i17++) {
            pVarArr[i17].e(dArr[i17], this.f5214n);
            dArr2[i17] = pVarArr[i17].f5230e;
        }
        int i18 = 0;
        while (true) {
            int[] iArr3 = this.f5214n;
            if (i18 >= iArr3.length) {
                break;
            }
            if (iArr3[i18] < p.f5227b.length) {
                String str9 = p.f5227b[this.f5214n[i18]] + " [";
                for (int i19 = 0; i19 < size; i19++) {
                    str9 = str9 + dArr[i19][i18];
                }
            }
            i18++;
        }
        this.f5209i = new t.b[this.f5217q.length + 1];
        int i20 = 0;
        while (true) {
            String[] strArr3 = this.f5217q;
            if (i20 >= strArr3.length) {
                break;
            }
            String str10 = strArr3[i20];
            int i21 = i7;
            int i22 = i21;
            double[] dArr3 = null;
            double[][] dArr4 = null;
            while (i21 < size) {
                if (pVarArr[i21].j(str10)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr4 = new int[i8];
                        iArr4[1] = pVarArr[i21].h(str10);
                        iArr4[i7] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr4);
                    }
                    dArr3[i22] = pVarArr[i21].f5230e;
                    pVarArr[i21].g(str10, dArr4[i22], 0);
                    i22++;
                }
                i21++;
                i8 = 2;
                i7 = 0;
            }
            i20++;
            this.f5209i[i20] = t.b.a(this.f5204d, Arrays.copyOf(dArr3, i22), (double[][]) Arrays.copyOf(dArr4, i22));
            i8 = 2;
            i7 = 0;
        }
        this.f5209i[0] = t.b.a(this.f5204d, dArr2, dArr);
        if (pVarArr[0].f5238m != c.f5050a) {
            int[] iArr5 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i23 = 0; i23 < size; i23++) {
                iArr5[i23] = pVarArr[i23].f5238m;
                dArr5[i23] = pVarArr[i23].f5230e;
                dArr6[i23][0] = pVarArr[i23].f5232g;
                dArr6[i23][1] = pVarArr[i23].f5233h;
            }
            this.f5210j = t.b.b(iArr5, dArr5, dArr6);
        }
        float fM = Float.NaN;
        this.f5226z = new HashMap<>();
        if (this.f5223w != null) {
            for (String str11 : hashSet3) {
                g gVarC = g.c(str11);
                if (gVarC != null) {
                    if (gVarC.i() && Float.isNaN(fM)) {
                        fM = m();
                    }
                    gVarC.g(str11);
                    this.f5226z.put(str11, gVarC);
                }
            }
            for (c cVar6 : this.f5223w) {
                if (cVar6 instanceof f) {
                    ((f) cVar6).O(this.f5226z);
                }
            }
            Iterator<g> it2 = this.f5226z.values().iterator();
            while (it2.hasNext()) {
                it2.next().h(fM);
            }
        }
    }
}
