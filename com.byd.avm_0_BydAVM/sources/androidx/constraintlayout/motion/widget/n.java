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
    View f585a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f586b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f587c;
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
    private int f588d = -1;
    private p e = new p();
    private p f = new p();
    private m g = new m();
    private m h = new m();
    float k = Float.NaN;
    float l = 0.0f;
    float m = 1.0f;
    private int s = 4;
    private float[] t = new float[4];
    private ArrayList<p> u = new ArrayList<>();
    private float[] v = new float[1];
    private ArrayList<c> w = new ArrayList<>();
    private int B = c.f557a;

    n(View view) {
        u(view);
    }

    private float f(float f, float[] fArr) {
        float f2 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f3 = this.m;
            if (f3 != 1.0d) {
                float f4 = this.l;
                if (f < f4) {
                    f = 0.0f;
                }
                if (f > f4 && f < 1.0d) {
                    f = (f - f4) * f3;
                }
            }
        }
        b.e.a.a.c cVar = this.e.f590c;
        float f5 = Float.NaN;
        for (p pVar : this.u) {
            b.e.a.a.c cVar2 = pVar.f590c;
            if (cVar2 != null) {
                float f6 = pVar.e;
                if (f6 < f) {
                    cVar = cVar2;
                    f2 = f6;
                } else if (Float.isNaN(f5)) {
                    f5 = pVar.e;
                }
            }
        }
        if (cVar == null) {
            return f;
        }
        float f7 = (Float.isNaN(f5) ? 1.0f : f5) - f2;
        double d2 = (f - f2) / f7;
        float fA = f2 + (((float) cVar.a(d2)) * f7);
        if (fArr != null) {
            fArr[0] = (float) cVar.b(d2);
        }
        return fA;
    }

    private float m() {
        float[] fArr = new float[2];
        float f = 1.0f / 99;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i = 0;
        float fHypot = 0.0f;
        while (i < 100) {
            float f2 = i * f;
            double dA = f2;
            b.e.a.a.c cVar = this.e.f590c;
            float f3 = Float.NaN;
            float f4 = 0.0f;
            for (p pVar : this.u) {
                b.e.a.a.c cVar2 = pVar.f590c;
                float f5 = f;
                if (cVar2 != null) {
                    float f6 = pVar.e;
                    if (f6 < f2) {
                        f4 = f6;
                        cVar = cVar2;
                    } else if (Float.isNaN(f3)) {
                        f3 = pVar.e;
                    }
                }
                f = f5;
            }
            float f7 = f;
            if (cVar != null) {
                if (Float.isNaN(f3)) {
                    f3 = 1.0f;
                }
                dA = (((float) cVar.a((f2 - f4) / r16)) * (f3 - f4)) + f4;
            }
            this.i[0].d(dA, this.o);
            this.e.f(this.n, this.o, fArr, 0);
            if (i > 0) {
                fHypot = (float) (((double) fHypot) + Math.hypot(d3 - ((double) fArr[1]), d2 - ((double) fArr[0])));
            }
            d2 = fArr[0];
            d3 = fArr[1];
            i++;
            f = f7;
        }
        return fHypot;
    }

    private void n(p pVar) {
        if (Collections.binarySearch(this.u, pVar) == 0) {
            Log.e("MotionController", " KeyPath positon \"" + pVar.f + "\" outside of range");
        }
        this.u.add((-r0) - 1, pVar);
    }

    private void p(p pVar) {
        pVar.n((int) this.f585a.getX(), (int) this.f585a.getY(), this.f585a.getWidth(), this.f585a.getHeight());
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
            this.e.f(this.n, this.o, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    void d(float[] fArr, int i) {
        int i2 = i;
        float f = 1.0f;
        float f2 = 1.0f / (i2 - 1);
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
            float f3 = i3 * f2;
            float f4 = this.m;
            if (f4 != f) {
                float f5 = this.l;
                if (f3 < f5) {
                    f3 = 0.0f;
                }
                if (f3 > f5 && f3 < 1.0d) {
                    f3 = (f3 - f5) * f4;
                }
            }
            double dA = f3;
            b.e.a.a.c cVar = this.e.f590c;
            float f6 = Float.NaN;
            float f7 = 0.0f;
            for (p pVar : this.u) {
                b.e.a.a.c cVar2 = pVar.f590c;
                if (cVar2 != null) {
                    float f8 = pVar.e;
                    if (f8 < f3) {
                        f7 = f8;
                        cVar = cVar2;
                    } else if (Float.isNaN(f6)) {
                        f6 = pVar.e;
                    }
                }
            }
            if (cVar != null) {
                if (Float.isNaN(f6)) {
                    f6 = 1.0f;
                }
                dA = (((float) cVar.a((f3 - f7) / r15)) * (f6 - f7)) + f7;
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
            this.e.f(this.n, this.o, fArr, i4);
            if (gVar != null) {
                fArr[i4] = fArr[i4] + gVar.a(f3);
            } else if (rVar != null) {
                fArr[i4] = fArr[i4] + rVar.a(f3);
            }
            if (gVar2 != null) {
                int i5 = i4 + 1;
                fArr[i5] = fArr[i5] + gVar2.a(f3);
            } else if (rVar2 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + rVar2.a(f3);
            }
            i3++;
            i2 = i;
            f = 1.0f;
        }
    }

    void e(float f, float[] fArr, int i) {
        this.i[0].d(f(f, null), this.o);
        this.e.i(this.n, this.o, fArr, i);
    }

    void g(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float f4 = f(f, this.v);
        b.e.a.a.b[] bVarArr = this.i;
        int i = 0;
        if (bVarArr == null) {
            p pVar = this.f;
            float f5 = pVar.g;
            p pVar2 = this.e;
            float f6 = f5 - pVar2.g;
            float f7 = pVar.h - pVar2.h;
            float f8 = (pVar.i - pVar2.i) + f6;
            float f9 = (pVar.j - pVar2.j) + f7;
            fArr[0] = (f6 * (1.0f - f2)) + (f8 * f2);
            fArr[1] = (f7 * (1.0f - f3)) + (f9 * f3);
            return;
        }
        double d2 = f4;
        bVarArr[0].g(d2, this.p);
        this.i[0].d(d2, this.o);
        float f10 = this.v[0];
        while (true) {
            dArr = this.p;
            if (i >= dArr.length) {
                break;
            }
            dArr[i] = dArr[i] * ((double) f10);
            i++;
        }
        b.e.a.a.b bVar = this.j;
        if (bVar == null) {
            this.e.o(f2, f3, fArr, this.n, dArr, this.o);
            return;
        }
        double[] dArr2 = this.o;
        if (dArr2.length > 0) {
            bVar.d(d2, dArr2);
            this.j.g(d2, this.p);
            this.e.o(f2, f3, fArr, this.n, this.p, this.o);
        }
    }

    public int h() {
        int iMax = this.e.f591d;
        Iterator<p> it = this.u.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().f591d);
        }
        return Math.max(iMax, this.f.f591d);
    }

    float i() {
        return this.f.g;
    }

    float j() {
        return this.f.h;
    }

    p k(int i) {
        return this.u.get(i);
    }

    void l(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float f4 = f(f, this.v);
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
        hVar.d(rVar3, f4);
        hVar.h(rVar, rVar2, f4);
        hVar.f(rVar4, rVar5, f4);
        hVar.c(gVar3, f4);
        hVar.g(gVar, gVar2, f4);
        hVar.e(gVar4, gVar5, f4);
        b.e.a.a.b bVar = this.j;
        if (bVar != null) {
            double[] dArr = this.o;
            if (dArr.length > 0) {
                double d2 = f4;
                bVar.d(d2, dArr);
                this.j.g(d2, this.p);
                this.e.o(f2, f3, fArr, this.n, this.p, this.o);
            }
            hVar.a(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.i == null) {
            p pVar = this.f;
            float f5 = pVar.g;
            p pVar2 = this.e;
            float f6 = f5 - pVar2.g;
            g gVar6 = gVar5;
            float f7 = pVar.h - pVar2.h;
            g gVar7 = gVar4;
            float f8 = (pVar.i - pVar2.i) + f6;
            float f9 = (pVar.j - pVar2.j) + f7;
            fArr[0] = (f6 * (1.0f - f2)) + (f8 * f2);
            fArr[1] = (f7 * (1.0f - f3)) + (f9 * f3);
            hVar.b();
            hVar.d(rVar3, f4);
            hVar.h(rVar, rVar2, f4);
            hVar.f(rVar4, rVar5, f4);
            hVar.c(gVar3, f4);
            hVar.g(gVar, gVar2, f4);
            hVar.e(gVar7, gVar6, f4);
            hVar.a(f2, f3, i, i2, fArr);
            return;
        }
        double dF = f(f4, this.v);
        this.i[0].g(dF, this.p);
        this.i[0].d(dF, this.o);
        float f10 = this.v[0];
        while (true) {
            double[] dArr2 = this.p;
            if (i3 >= dArr2.length) {
                this.e.o(f2, f3, fArr, this.n, dArr2, this.o);
                hVar.a(f2, f3, i, i2, fArr);
                return;
            } else {
                dArr2[i3] = dArr2[i3] * ((double) f10);
                i3++;
            }
        }
    }

    boolean o(View view, float f, long j, e eVar) {
        s.d dVar;
        boolean zJ;
        double d2;
        float f2 = f(f, null);
        HashMap<String, r> map = this.y;
        if (map != null) {
            Iterator<r> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().f(view, f2);
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
                    zF |= sVar.f(view, f2, j, eVar);
                }
            }
            zJ = zF;
        } else {
            dVar = null;
            zJ = false;
        }
        b.e.a.a.b[] bVarArr = this.i;
        if (bVarArr != null) {
            double d3 = f2;
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
            this.e.p(view, this.n, this.o, this.p, null);
            HashMap<String, r> map3 = this.y;
            if (map3 != null) {
                for (r rVar : map3.values()) {
                    if (rVar instanceof r.d) {
                        double[] dArr2 = this.p;
                        ((r.d) rVar).i(view, f2, dArr2[0], dArr2[1]);
                    }
                }
            }
            if (dVar != null) {
                double[] dArr3 = this.p;
                d2 = d3;
                zJ = dVar.j(view, eVar, f2, j, dArr3[0], dArr3[1]) | zJ;
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
                this.e.n.get(this.q[i - 1]).i(view, this.t);
                i++;
            }
            m mVar = this.g;
            if (mVar.f584d == 0) {
                if (f2 <= 0.0f) {
                    view.setVisibility(mVar.e);
                } else if (f2 >= 1.0f) {
                    view.setVisibility(this.h.e);
                } else if (this.h.e != mVar.e) {
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
                    lVarArr[i2].r(f2, view);
                    i2++;
                }
            }
        } else {
            p pVar = this.e;
            float f3 = pVar.g;
            p pVar2 = this.f;
            float f4 = f3 + ((pVar2.g - f3) * f2);
            float f5 = pVar.h;
            float f6 = f5 + ((pVar2.h - f5) * f2);
            float f7 = pVar.i;
            float f8 = pVar2.i;
            float f9 = pVar.j;
            float f10 = pVar2.j;
            float f11 = f4 + 0.5f;
            int i3 = (int) f11;
            float f12 = f6 + 0.5f;
            int i4 = (int) f12;
            int i5 = (int) (f11 + ((f8 - f7) * f2) + f7);
            int i6 = (int) (f12 + ((f10 - f9) * f2) + f9);
            int i7 = i5 - i3;
            int i8 = i6 - i4;
            if (f8 != f7 || f10 != f9) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i8, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
            view.layout(i3, i4, i5, i6);
        }
        HashMap<String, g> map4 = this.z;
        if (map4 != null) {
            for (g gVar : map4.values()) {
                if (gVar instanceof g.f) {
                    double[] dArr4 = this.p;
                    ((g.f) gVar).j(view, f2, dArr4[0], dArr4[1]);
                } else {
                    gVar.f(view, f2);
                }
            }
        }
        return zJ;
    }

    void q(ConstraintWidget constraintWidget, androidx.constraintlayout.widget.b bVar) {
        p pVar = this.f;
        pVar.e = 1.0f;
        pVar.f = 1.0f;
        p(pVar);
        this.f.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        this.f.a(bVar.q(this.f586b));
        this.h.i(constraintWidget, bVar, this.f586b);
    }

    public void r(int i) {
        this.B = i;
    }

    void s(View view) {
        p pVar = this.e;
        pVar.e = 0.0f;
        pVar.f = 0.0f;
        pVar.n(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.g.h(view);
    }

    void t(ConstraintWidget constraintWidget, androidx.constraintlayout.widget.b bVar) {
        p pVar = this.e;
        pVar.e = 0.0f;
        pVar.f = 0.0f;
        p(pVar);
        this.e.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        b.a aVarQ = bVar.q(this.f586b);
        this.e.a(aVarQ);
        this.k = aVarQ.f758c.g;
        this.g.i(constraintWidget, bVar, this.f586b);
    }

    public String toString() {
        return " start: x: " + this.e.g + " y: " + this.e.h + " end: x: " + this.f.g + " y: " + this.f.h;
    }

    public void u(View view) {
        this.f585a = view;
        this.f586b = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            this.f587c = ((ConstraintLayout.b) layoutParams).a();
        }
    }

    public void v(int i, int i2, float f, long j) {
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
        if (i3 != c.f557a) {
            this.e.m = i3;
        }
        this.g.f(this.h, hashSet2);
        ArrayList<c> arrayList2 = this.w;
        if (arrayList2 != null) {
            arrayList = null;
            for (c cVar : arrayList2) {
                if (cVar instanceof i) {
                    i iVar = (i) cVar;
                    n(new p(i, i2, iVar, this.e, this.f));
                    int i4 = iVar.g;
                    if (i4 != c.f557a) {
                        this.f588d = i4;
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
                        HashMap<String, ConstraintAttribute> map2 = cVar2.f;
                        if (map2 != null && (constraintAttribute2 = map2.get(str2)) != null) {
                            sparseArray.append(cVar2.f558b, constraintAttribute2);
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
            this.g.a(this.y, 0);
            this.h.a(this.y, 100);
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
                            HashMap<String, ConstraintAttribute> map3 = cVar4.f;
                            if (map3 != null && (constraintAttribute = map3.get(str5)) != null) {
                                sparseArray2.append(cVar4.f558b, constraintAttribute);
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
        pVarArr[0] = this.e;
        pVarArr[size - 1] = this.f;
        if (this.u.size() > 0 && this.f588d == -1) {
            this.f588d = 0;
        }
        Iterator<p> it = this.u.iterator();
        int i7 = 1;
        while (it.hasNext()) {
            pVarArr[i7] = it.next();
            i7++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.f.n.keySet()) {
            if (this.e.n.containsKey(str7)) {
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
        boolean z = pVarArr[0].m != c.f557a;
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
            dArr2[i15] = pVarArr[i15].e;
        }
        int i16 = 0;
        while (true) {
            int[] iArr3 = this.n;
            if (i16 >= iArr3.length) {
                break;
            }
            if (iArr3[i16] < p.f589b.length) {
                String str9 = p.f589b[this.n[i16]] + " [";
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
                    dArr3[i20] = pVarArr[i19].e;
                    pVarArr[i19].g(str10, dArr4[i20], 0);
                    i20++;
                }
                i19++;
                i6 = 2;
                i5 = 0;
            }
            i18++;
            this.i[i18] = b.e.a.a.b.a(this.f588d, Arrays.copyOf(dArr3, i20), (double[][]) Arrays.copyOf(dArr4, i20));
            i6 = 2;
            i5 = 0;
        }
        this.i[0] = b.e.a.a.b.a(this.f588d, dArr2, dArr);
        if (pVarArr[0].m != c.f557a) {
            int[] iArr5 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i21 = 0; i21 < size; i21++) {
                iArr5[i21] = pVarArr[i21].m;
                dArr5[i21] = pVarArr[i21].e;
                dArr6[i21][0] = pVarArr[i21].g;
                dArr6[i21][1] = pVarArr[i21].h;
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
