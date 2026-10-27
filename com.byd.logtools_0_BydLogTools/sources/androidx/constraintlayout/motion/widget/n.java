package androidx.constraintlayout.motion.widget;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.g;
import androidx.constraintlayout.motion.widget.r;
import androidx.constraintlayout.motion.widget.s;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.b;
import com.bydux.api.common.tools.util.ListUtils;
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
    private l[] A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    View f842a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f843b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f844c;
    private b.e.a.a.b[] i;
    private b.e.a.a.b j;
    private int[] n;
    private double[] o;
    private double[] p;
    private String[] q;
    private int[] r;
    private HashMap<String, s> x;
    private HashMap<String, r> y;
    private HashMap<String, g> z;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f845d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private p f846e = new p();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private p f847f = new p();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private m f848g = new m();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private m f849h = new m();
    float k = Float.NaN;
    float l = 0.0f;
    float m = 1.0f;
    private int s = 4;
    private float[] t = new float[4];
    private ArrayList<p> u = new ArrayList<>();
    private float[] v = new float[1];
    private ArrayList<c> w = new ArrayList<>();
    private int B = c.f789a;

    n(View view) {
        u(view);
    }

    private float f(float f2, float[] fArr) {
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
        b.e.a.a.c cVar = this.f846e.f851c;
        float f6 = Float.NaN;
        for (p pVar : this.u) {
            b.e.a.a.c cVar2 = pVar.f851c;
            if (cVar2 != null) {
                float f7 = pVar.f853e;
                if (f7 < f2) {
                    cVar = cVar2;
                    f3 = f7;
                } else if (Float.isNaN(f6)) {
                    f6 = pVar.f853e;
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

    private float m() {
        float[] fArr = new float[2];
        float f2 = 1.0f / 99;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i = 0;
        float fHypot = 0.0f;
        while (i < 100) {
            float f3 = i * f2;
            double dA = f3;
            b.e.a.a.c cVar = this.f846e.f851c;
            float f4 = Float.NaN;
            float f5 = 0.0f;
            for (p pVar : this.u) {
                b.e.a.a.c cVar2 = pVar.f851c;
                float f6 = f2;
                if (cVar2 != null) {
                    float f7 = pVar.f853e;
                    if (f7 < f3) {
                        f5 = f7;
                        cVar = cVar2;
                    } else if (Float.isNaN(f4)) {
                        f4 = pVar.f853e;
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
            this.i[0].d(dA, this.o);
            this.f846e.f(this.n, this.o, fArr, 0);
            if (i > 0) {
                fHypot = (float) (((double) fHypot) + Math.hypot(d3 - ((double) fArr[1]), d2 - ((double) fArr[0])));
            }
            d2 = fArr[0];
            d3 = fArr[1];
            i++;
            f2 = f8;
        }
        return fHypot;
    }

    private void n(p pVar) {
        if (Collections.binarySearch(this.u, pVar) == 0) {
            Log.e("MotionController", " KeyPath positon \"" + pVar.f854f + "\" outside of range");
        }
        this.u.add((-r0) - 1, pVar);
    }

    private void p(p pVar) {
        pVar.n((int) this.f842a.getX(), (int) this.f842a.getY(), this.f842a.getWidth(), this.f842a.getHeight());
    }

    void a(c cVar) {
        this.w.add(cVar);
    }

    void b(ArrayList<c> arrayList) {
        this.w.addAll(arrayList);
    }

    int c(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] dArrH = this.i[0].h();
        if (iArr != null) {
            Iterator<p> it = this.u.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().o;
                i++;
            }
        }
        int i2 = 0;
        for (double d2 : dArrH) {
            this.i[0].d(d2, this.o);
            this.f846e.f(this.n, this.o, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    void d(float[] fArr, int i) {
        int i2 = i;
        float f2 = 1.0f;
        float f3 = 1.0f / (i2 - 1);
        HashMap<String, r> map = this.y;
        r rVar = map == null ? null : map.get("translationX");
        HashMap<String, r> map2 = this.y;
        r rVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, g> map3 = this.z;
        g gVar = map3 == null ? null : map3.get("translationX");
        HashMap<String, g> map4 = this.z;
        g gVar2 = map4 != null ? map4.get("translationY") : null;
        int i3 = 0;
        while (i3 < i2) {
            float f4 = i3 * f3;
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
            b.e.a.a.c cVar = this.f846e.f851c;
            float f7 = Float.NaN;
            float f8 = 0.0f;
            for (p pVar : this.u) {
                b.e.a.a.c cVar2 = pVar.f851c;
                if (cVar2 != null) {
                    float f9 = pVar.f853e;
                    if (f9 < f4) {
                        f8 = f9;
                        cVar = cVar2;
                    } else if (Float.isNaN(f7)) {
                        f7 = pVar.f853e;
                    }
                }
            }
            if (cVar != null) {
                if (Float.isNaN(f7)) {
                    f7 = 1.0f;
                }
                dA = (((float) cVar.a((f4 - f8) / r15)) * (f7 - f8)) + f8;
            }
            this.i[0].d(dA, this.o);
            b.e.a.a.b bVar = this.j;
            if (bVar != null) {
                double[] dArr = this.o;
                if (dArr.length > 0) {
                    bVar.d(dA, dArr);
                }
            }
            int i4 = i3 * 2;
            this.f846e.f(this.n, this.o, fArr, i4);
            if (gVar != null) {
                fArr[i4] = fArr[i4] + gVar.a(f4);
            } else if (rVar != null) {
                fArr[i4] = fArr[i4] + rVar.a(f4);
            }
            if (gVar2 != null) {
                int i5 = i4 + 1;
                fArr[i5] = fArr[i5] + gVar2.a(f4);
            } else if (rVar2 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + rVar2.a(f4);
            }
            i3++;
            i2 = i;
            f2 = 1.0f;
        }
    }

    void e(float f2, float[] fArr, int i) {
        this.i[0].d(f(f2, null), this.o);
        this.f846e.i(this.n, this.o, fArr, i);
    }

    void g(float f2, float f3, float f4, float[] fArr) {
        double[] dArr;
        float f5 = f(f2, this.v);
        b.e.a.a.b[] bVarArr = this.i;
        int i = 0;
        if (bVarArr == null) {
            p pVar = this.f847f;
            float f6 = pVar.f855g;
            p pVar2 = this.f846e;
            float f7 = f6 - pVar2.f855g;
            float f8 = pVar.f856h - pVar2.f856h;
            float f9 = (pVar.i - pVar2.i) + f7;
            float f10 = (pVar.j - pVar2.j) + f8;
            fArr[0] = (f7 * (1.0f - f3)) + (f9 * f3);
            fArr[1] = (f8 * (1.0f - f4)) + (f10 * f4);
            return;
        }
        double d2 = f5;
        bVarArr[0].g(d2, this.p);
        this.i[0].d(d2, this.o);
        float f11 = this.v[0];
        while (true) {
            dArr = this.p;
            if (i >= dArr.length) {
                break;
            }
            dArr[i] = dArr[i] * ((double) f11);
            i++;
        }
        b.e.a.a.b bVar = this.j;
        if (bVar == null) {
            this.f846e.o(f3, f4, fArr, this.n, dArr, this.o);
            return;
        }
        double[] dArr2 = this.o;
        if (dArr2.length > 0) {
            bVar.d(d2, dArr2);
            this.j.g(d2, this.p);
            this.f846e.o(f3, f4, fArr, this.n, this.p, this.o);
        }
    }

    public int h() {
        int iMax = this.f846e.f852d;
        Iterator<p> it = this.u.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().f852d);
        }
        return Math.max(iMax, this.f847f.f852d);
    }

    float i() {
        return this.f847f.f855g;
    }

    float j() {
        return this.f847f.f856h;
    }

    p k(int i) {
        return this.u.get(i);
    }

    void l(float f2, int i, int i2, float f3, float f4, float[] fArr) {
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
                this.f846e.o(f3, f4, fArr, this.n, this.p, this.o);
            }
            hVar.a(f3, f4, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.i == null) {
            p pVar = this.f847f;
            float f6 = pVar.f855g;
            p pVar2 = this.f846e;
            float f7 = f6 - pVar2.f855g;
            g gVar6 = gVar5;
            float f8 = pVar.f856h - pVar2.f856h;
            g gVar7 = gVar4;
            float f9 = (pVar.i - pVar2.i) + f7;
            float f10 = (pVar.j - pVar2.j) + f8;
            fArr[0] = (f7 * (1.0f - f3)) + (f9 * f3);
            fArr[1] = (f8 * (1.0f - f4)) + (f10 * f4);
            hVar.b();
            hVar.d(rVar3, f5);
            hVar.h(rVar, rVar2, f5);
            hVar.f(rVar4, rVar5, f5);
            hVar.c(gVar3, f5);
            hVar.g(gVar, gVar2, f5);
            hVar.e(gVar7, gVar6, f5);
            hVar.a(f3, f4, i, i2, fArr);
            return;
        }
        double dF = f(f5, this.v);
        this.i[0].g(dF, this.p);
        this.i[0].d(dF, this.o);
        float f11 = this.v[0];
        while (true) {
            double[] dArr2 = this.p;
            if (i3 >= dArr2.length) {
                this.f846e.o(f3, f4, fArr, this.n, dArr2, this.o);
                hVar.a(f3, f4, i, i2, fArr);
                return;
            } else {
                dArr2[i3] = dArr2[i3] * ((double) f11);
                i3++;
            }
        }
    }

    boolean o(View view, float f2, long j, e eVar) {
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
        b.e.a.a.b[] bVarArr = this.i;
        if (bVarArr != null) {
            double d3 = f3;
            bVarArr[0].d(d3, this.o);
            this.i[0].g(d3, this.p);
            b.e.a.a.b bVar = this.j;
            if (bVar != null) {
                double[] dArr = this.o;
                if (dArr.length > 0) {
                    bVar.d(d3, dArr);
                    this.j.g(d3, this.p);
                }
            }
            this.f846e.p(view, this.n, this.o, this.p, null);
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
            int i = 1;
            while (true) {
                b.e.a.a.b[] bVarArr2 = this.i;
                if (i >= bVarArr2.length) {
                    break;
                }
                bVarArr2[i].e(d2, this.t);
                this.f846e.n.get(this.q[i - 1]).i(view, this.t);
                i++;
            }
            m mVar = this.f848g;
            if (mVar.f837d == 0) {
                if (f3 <= 0.0f) {
                    view.setVisibility(mVar.f838e);
                } else if (f3 >= 1.0f) {
                    view.setVisibility(this.f849h.f838e);
                } else if (this.f849h.f838e != mVar.f838e) {
                    view.setVisibility(0);
                }
            }
            if (this.A != null) {
                int i2 = 0;
                while (true) {
                    l[] lVarArr = this.A;
                    if (i2 >= lVarArr.length) {
                        break;
                    }
                    lVarArr[i2].r(f3, view);
                    i2++;
                }
            }
        } else {
            p pVar = this.f846e;
            float f4 = pVar.f855g;
            p pVar2 = this.f847f;
            float f5 = f4 + ((pVar2.f855g - f4) * f3);
            float f6 = pVar.f856h;
            float f7 = f6 + ((pVar2.f856h - f6) * f3);
            float f8 = pVar.i;
            float f9 = pVar2.i;
            float f10 = pVar.j;
            float f11 = pVar2.j;
            float f12 = f5 + 0.5f;
            int i3 = (int) f12;
            float f13 = f7 + 0.5f;
            int i4 = (int) f13;
            int i5 = (int) (f12 + ((f9 - f8) * f3) + f8);
            int i6 = (int) (f13 + ((f11 - f10) * f3) + f10);
            int i7 = i5 - i3;
            int i8 = i6 - i4;
            if (f9 != f8 || f11 != f10) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i8, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
            view.layout(i3, i4, i5, i6);
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

    void q(ConstraintWidget constraintWidget, androidx.constraintlayout.widget.b bVar) {
        p pVar = this.f847f;
        pVar.f853e = 1.0f;
        pVar.f854f = 1.0f;
        p(pVar);
        this.f847f.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        this.f847f.a(bVar.q(this.f843b));
        this.f849h.i(constraintWidget, bVar, this.f843b);
    }

    public void r(int i) {
        this.B = i;
    }

    void s(View view) {
        p pVar = this.f846e;
        pVar.f853e = 0.0f;
        pVar.f854f = 0.0f;
        pVar.n(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f848g.h(view);
    }

    void t(ConstraintWidget constraintWidget, androidx.constraintlayout.widget.b bVar) {
        p pVar = this.f846e;
        pVar.f853e = 0.0f;
        pVar.f854f = 0.0f;
        p(pVar);
        this.f846e.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        b.a aVarQ = bVar.q(this.f843b);
        this.f846e.a(aVarQ);
        this.k = aVarQ.f1141c.f1159g;
        this.f848g.i(constraintWidget, bVar, this.f843b);
    }

    public String toString() {
        return " start: x: " + this.f846e.f855g + " y: " + this.f846e.f856h + " end: x: " + this.f847f.f855g + " y: " + this.f847f.f856h;
    }

    public void u(View view) {
        this.f842a = view;
        this.f843b = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            this.f844c = ((ConstraintLayout.b) layoutParams).a();
        }
    }

    public void v(int i, int i2, float f2, long j) {
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
        int i3 = this.B;
        if (i3 != c.f789a) {
            this.f846e.m = i3;
        }
        this.f848g.f(this.f849h, hashSet2);
        ArrayList<c> arrayList2 = this.w;
        if (arrayList2 != null) {
            arrayList = null;
            for (c cVar : arrayList2) {
                if (cVar instanceof i) {
                    i iVar = (i) cVar;
                    n(new p(i, i2, iVar, this.f846e, this.f847f));
                    int i4 = iVar.f828g;
                    if (i4 != c.f789a) {
                        this.f845d = i4;
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
        int i5 = 0;
        if (arrayList != null) {
            this.A = (l[]) arrayList.toArray(new l[0]);
        }
        char c2 = 1;
        if (!hashSet2.isEmpty()) {
            this.y = new HashMap<>();
            for (String str : hashSet2) {
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[c2];
                    for (c cVar2 : this.w) {
                        HashMap<String, ConstraintAttribute> map2 = cVar2.f794f;
                        if (map2 != null && (constraintAttribute2 = map2.get(str2)) != null) {
                            sparseArray.append(cVar2.f790b, constraintAttribute2);
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
            this.f848g.a(this.y, 0);
            this.f849h.a(this.y, 100);
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
                        String str5 = str4.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
                        for (c cVar4 : this.w) {
                            HashMap<String, ConstraintAttribute> map3 = cVar4.f794f;
                            if (map3 != null && (constraintAttribute = map3.get(str5)) != null) {
                                sparseArray2.append(cVar4.f790b, constraintAttribute);
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
        int i6 = 2;
        int size = this.u.size() + 2;
        p[] pVarArr = new p[size];
        pVarArr[0] = this.f846e;
        pVarArr[size - 1] = this.f847f;
        if (this.u.size() > 0 && this.f845d == -1) {
            this.f845d = 0;
        }
        Iterator<p> it = this.u.iterator();
        int i7 = 1;
        while (it.hasNext()) {
            pVarArr[i7] = it.next();
            i7++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.f847f.n.keySet()) {
            if (this.f846e.n.containsKey(str7)) {
                if (!hashSet2.contains("CUSTOM," + str7)) {
                    hashSet4.add(str7);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.q = strArr2;
        this.r = new int[strArr2.length];
        int i8 = 0;
        while (true) {
            strArr = this.q;
            if (i8 >= strArr.length) {
                break;
            }
            String str8 = strArr[i8];
            this.r[i8] = 0;
            int i9 = 0;
            while (true) {
                if (i9 >= size) {
                    break;
                }
                if (pVarArr[i9].n.containsKey(str8)) {
                    int[] iArr = this.r;
                    iArr[i8] = iArr[i8] + pVarArr[i9].n.get(str8).f();
                    break;
                }
                i9++;
            }
            i8++;
        }
        boolean z = pVarArr[0].m != c.f789a;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i10 = 1; i10 < size; i10++) {
            pVarArr[i10].d(pVarArr[i10 - 1], zArr, this.q, z);
        }
        int i11 = 0;
        for (int i12 = 1; i12 < length; i12++) {
            if (zArr[i12]) {
                i11++;
            }
        }
        int[] iArr2 = new int[i11];
        this.n = iArr2;
        this.o = new double[iArr2.length];
        this.p = new double[iArr2.length];
        int i13 = 0;
        for (int i14 = 1; i14 < length; i14++) {
            if (zArr[i14]) {
                this.n[i13] = i14;
                i13++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, size, this.n.length);
        double[] dArr2 = new double[size];
        for (int i15 = 0; i15 < size; i15++) {
            pVarArr[i15].e(dArr[i15], this.n);
            dArr2[i15] = pVarArr[i15].f853e;
        }
        int i16 = 0;
        while (true) {
            int[] iArr3 = this.n;
            if (i16 >= iArr3.length) {
                break;
            }
            if (iArr3[i16] < p.f850b.length) {
                String str9 = p.f850b[this.n[i16]] + " [";
                for (int i17 = 0; i17 < size; i17++) {
                    str9 = str9 + dArr[i17][i16];
                }
            }
            i16++;
        }
        this.i = new b.e.a.a.b[this.q.length + 1];
        int i18 = 0;
        while (true) {
            String[] strArr3 = this.q;
            if (i18 >= strArr3.length) {
                break;
            }
            String str10 = strArr3[i18];
            int i19 = i5;
            int i20 = i19;
            double[] dArr3 = null;
            double[][] dArr4 = null;
            while (i19 < size) {
                if (pVarArr[i19].j(str10)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr4 = new int[i6];
                        iArr4[1] = pVarArr[i19].h(str10);
                        iArr4[i5] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr4);
                    }
                    dArr3[i20] = pVarArr[i19].f853e;
                    pVarArr[i19].g(str10, dArr4[i20], 0);
                    i20++;
                }
                i19++;
                i6 = 2;
                i5 = 0;
            }
            i18++;
            this.i[i18] = b.e.a.a.b.a(this.f845d, Arrays.copyOf(dArr3, i20), (double[][]) Arrays.copyOf(dArr4, i20));
            i6 = 2;
            i5 = 0;
        }
        this.i[0] = b.e.a.a.b.a(this.f845d, dArr2, dArr);
        if (pVarArr[0].m != c.f789a) {
            int[] iArr5 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i21 = 0; i21 < size; i21++) {
                iArr5[i21] = pVarArr[i21].m;
                dArr5[i21] = pVarArr[i21].f853e;
                dArr6[i21][0] = pVarArr[i21].f855g;
                dArr6[i21][1] = pVarArr[i21].f856h;
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
