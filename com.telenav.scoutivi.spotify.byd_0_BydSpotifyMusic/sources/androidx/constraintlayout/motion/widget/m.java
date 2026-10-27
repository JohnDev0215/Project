package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.d;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import t.c;
import t.d;
import t.f;

/* JADX INFO: compiled from: MotionController.java */
/* JADX INFO: loaded from: classes.dex */
public class m {
    private HashMap<String, t.f> B;
    private HashMap<String, t.d> C;
    private HashMap<String, t.c> D;
    private k[] E;
    private int F;
    private int G;
    private View H;
    private int I;
    private float J;
    private Interpolator K;
    private boolean L;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    View f1472b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1473c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f1475e;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private p.b[] f1481k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private p.b f1482l;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    float f1486p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    float f1487q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int[] f1488r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private double[] f1489s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private double[] f1490t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private String[] f1491u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int[] f1492v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Rect f1471a = new Rect();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1474d = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1476f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private p f1477g = new p();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private p f1478h = new p();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private l f1479i = new l();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private l f1480j = new l();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    float f1483m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    float f1484n = 0.0f;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    float f1485o = 1.0f;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f1493w = 4;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float[] f1494x = new float[4];

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private ArrayList<p> f1495y = new ArrayList<>();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private float[] f1496z = new float[1];
    private ArrayList<d> A = new ArrayList<>();

    /* JADX INFO: compiled from: MotionController.java */
    class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p.c f1497a;

        a(p.c cVar) {
            this.f1497a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f7) {
            return (float) this.f1497a.a(f7);
        }
    }

    m(View view) {
        int i7 = d.f1349f;
        this.F = i7;
        this.G = i7;
        this.H = null;
        this.I = i7;
        this.J = Float.NaN;
        this.K = null;
        this.L = false;
        E(view);
    }

    private float g(float f7, float[] fArr) {
        float f8 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f9 = this.f1485o;
            if (f9 != 1.0d) {
                float f10 = this.f1484n;
                if (f7 < f10) {
                    f7 = 0.0f;
                }
                if (f7 > f10 && f7 < 1.0d) {
                    f7 = Math.min((f7 - f10) * f9, 1.0f);
                }
            }
        }
        p.c cVar = this.f1477g.f1503e;
        float f11 = Float.NaN;
        for (p pVar : this.f1495y) {
            p.c cVar2 = pVar.f1503e;
            if (cVar2 != null) {
                float f12 = pVar.f1505h;
                if (f12 < f7) {
                    cVar = cVar2;
                    f8 = f12;
                } else if (Float.isNaN(f11)) {
                    f11 = pVar.f1505h;
                }
            }
        }
        if (cVar != null) {
            float f13 = (Float.isNaN(f11) ? 1.0f : f11) - f8;
            double d7 = (f7 - f8) / f13;
            f7 = (((float) cVar.a(d7)) * f13) + f8;
            if (fArr != null) {
                fArr[0] = (float) cVar.b(d7);
            }
        }
        return f7;
    }

    private static Interpolator p(Context context, int i7, String str, int i8) {
        if (i7 == -2) {
            return AnimationUtils.loadInterpolator(context, i8);
        }
        if (i7 == -1) {
            return new a(p.c.c(str));
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
        if (i7 != 5) {
            return null;
        }
        return new OvershootInterpolator();
    }

    private float r() {
        char c7;
        float fHypot;
        float[] fArr = new float[2];
        float f7 = 1.0f / 99;
        double d7 = 0.0d;
        double d8 = 0.0d;
        float f8 = 0.0f;
        int i7 = 0;
        while (i7 < 100) {
            float f9 = i7 * f7;
            double dA = f9;
            p.c cVar = this.f1477g.f1503e;
            float f10 = Float.NaN;
            float f11 = 0.0f;
            for (p pVar : this.f1495y) {
                p.c cVar2 = pVar.f1503e;
                if (cVar2 != null) {
                    float f12 = pVar.f1505h;
                    if (f12 < f9) {
                        cVar = cVar2;
                        f11 = f12;
                    } else if (Float.isNaN(f10)) {
                        f10 = pVar.f1505h;
                    }
                }
            }
            if (cVar != null) {
                if (Float.isNaN(f10)) {
                    f10 = 1.0f;
                }
                dA = (((float) cVar.a((f9 - f11) / r17)) * (f10 - f11)) + f11;
            }
            this.f1481k[0].d(dA, this.f1489s);
            float f13 = f8;
            int i8 = i7;
            this.f1477g.f(dA, this.f1488r, this.f1489s, fArr, 0);
            if (i8 > 0) {
                c7 = 0;
                fHypot = (float) (((double) f13) + Math.hypot(d8 - ((double) fArr[1]), d7 - ((double) fArr[0])));
            } else {
                c7 = 0;
                fHypot = f13;
            }
            d7 = fArr[c7];
            i7 = i8 + 1;
            f8 = fHypot;
            d8 = fArr[1];
        }
        return f8;
    }

    private void t(p pVar) {
        if (Collections.binarySearch(this.f1495y, pVar) == 0) {
            Log.e("MotionController", " KeyPath position \"" + pVar.f1506i + "\" outside of range");
        }
        this.f1495y.add((-r0) - 1, pVar);
    }

    private void v(p pVar) {
        pVar.p((int) this.f1472b.getX(), (int) this.f1472b.getY(), this.f1472b.getWidth(), this.f1472b.getHeight());
    }

    public void A(int i7) {
        this.F = i7;
    }

    void B(View view) {
        p pVar = this.f1477g;
        pVar.f1505h = 0.0f;
        pVar.f1506i = 0.0f;
        pVar.p(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f1479i.j(view);
    }

    void C(Rect rect, androidx.constraintlayout.widget.d dVar, int i7, int i8) {
        int i9 = dVar.f1771d;
        if (i9 != 0) {
            x(rect, this.f1471a, i9, i7, i8);
        }
        p pVar = this.f1477g;
        pVar.f1505h = 0.0f;
        pVar.f1506i = 0.0f;
        v(pVar);
        this.f1477g.p(rect.left, rect.top, rect.width(), rect.height());
        d.a aVarZ = dVar.z(this.f1473c);
        this.f1477g.a(aVarZ);
        this.f1483m = aVarZ.f1778d.f1846g;
        this.f1479i.i(rect, dVar, i9, this.f1473c);
        this.G = aVarZ.f1780f.f1868i;
        d.c cVar = aVarZ.f1778d;
        this.I = cVar.f1850k;
        this.J = cVar.f1849j;
        Context context = this.f1472b.getContext();
        d.c cVar2 = aVarZ.f1778d;
        this.K = p(context, cVar2.f1852m, cVar2.f1851l, cVar2.f1853n);
    }

    public void D(t.e eVar, View view, int i7, int i8, int i9) {
        p pVar = this.f1477g;
        pVar.f1505h = 0.0f;
        pVar.f1506i = 0.0f;
        Rect rect = new Rect();
        if (i7 == 1) {
            int i10 = eVar.f9532b + eVar.f9534d;
            rect.left = ((eVar.f9533c + eVar.f9535e) - eVar.b()) / 2;
            rect.top = i8 - ((i10 + eVar.a()) / 2);
            rect.right = rect.left + eVar.b();
            rect.bottom = rect.top + eVar.a();
        } else if (i7 == 2) {
            int i11 = eVar.f9532b + eVar.f9534d;
            rect.left = i9 - (((eVar.f9533c + eVar.f9535e) + eVar.b()) / 2);
            rect.top = (i11 - eVar.a()) / 2;
            rect.right = rect.left + eVar.b();
            rect.bottom = rect.top + eVar.a();
        }
        this.f1477g.p(rect.left, rect.top, rect.width(), rect.height());
        this.f1479i.h(rect, view, i7, eVar.f9531a);
    }

    public void E(View view) {
        this.f1472b = view;
        this.f1473c = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            this.f1475e = ((ConstraintLayout.b) layoutParams).a();
        }
    }

    public void F(int i7, int i8, float f7, long j7) {
        ArrayList arrayList;
        String[] strArr;
        androidx.constraintlayout.widget.a aVar;
        t.f fVarH;
        androidx.constraintlayout.widget.a aVar2;
        Integer num;
        t.d dVarF;
        androidx.constraintlayout.widget.a aVar3;
        new HashSet();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        int i9 = this.F;
        if (i9 != d.f1349f) {
            this.f1477g.f1513p = i9;
        }
        this.f1479i.f(this.f1480j, hashSet2);
        ArrayList<d> arrayList2 = this.A;
        if (arrayList2 != null) {
            arrayList = null;
            for (d dVar : arrayList2) {
                if (dVar instanceof h) {
                    h hVar = (h) dVar;
                    t(new p(i7, i8, hVar, this.f1477g, this.f1478h));
                    int i10 = hVar.f1409g;
                    if (i10 != d.f1349f) {
                        this.f1476f = i10;
                    }
                } else if (dVar instanceof f) {
                    dVar.d(hashSet3);
                } else if (dVar instanceof j) {
                    dVar.d(hashSet);
                } else if (dVar instanceof k) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((k) dVar);
                } else {
                    dVar.g(map);
                    dVar.d(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        int i11 = 0;
        if (arrayList != null) {
            this.E = (k[]) arrayList.toArray(new k[0]);
        }
        char c7 = 1;
        if (!hashSet2.isEmpty()) {
            this.C = new HashMap<>();
            for (String str : hashSet2) {
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(",")[c7];
                    for (d dVar2 : this.A) {
                        HashMap<String, androidx.constraintlayout.widget.a> map2 = dVar2.f1354e;
                        if (map2 != null && (aVar3 = map2.get(str2)) != null) {
                            sparseArray.append(dVar2.f1350a, aVar3);
                        }
                    }
                    dVarF = t.d.e(str, sparseArray);
                } else {
                    dVarF = t.d.f(str);
                }
                if (dVarF != null) {
                    dVarF.c(str);
                    this.C.put(str, dVarF);
                }
                c7 = 1;
            }
            ArrayList<d> arrayList3 = this.A;
            if (arrayList3 != null) {
                for (d dVar3 : arrayList3) {
                    if (dVar3 instanceof e) {
                        dVar3.a(this.C);
                    }
                }
            }
            this.f1479i.a(this.C, 0);
            this.f1480j.a(this.C, 100);
            for (String str3 : this.C.keySet()) {
                int iIntValue = (!map.containsKey(str3) || (num = map.get(str3)) == null) ? 0 : num.intValue();
                t.d dVar4 = this.C.get(str3);
                if (dVar4 != null) {
                    dVar4.d(iIntValue);
                }
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.B == null) {
                this.B = new HashMap<>();
            }
            for (String str4 : hashSet) {
                if (!this.B.containsKey(str4)) {
                    if (str4.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str5 = str4.split(",")[1];
                        for (d dVar5 : this.A) {
                            HashMap<String, androidx.constraintlayout.widget.a> map3 = dVar5.f1354e;
                            if (map3 != null && (aVar2 = map3.get(str5)) != null) {
                                sparseArray2.append(dVar5.f1350a, aVar2);
                            }
                        }
                        fVarH = t.f.g(str4, sparseArray2);
                    } else {
                        fVarH = t.f.h(str4, j7);
                    }
                    if (fVarH != null) {
                        fVarH.d(str4);
                        this.B.put(str4, fVarH);
                    }
                }
            }
            ArrayList<d> arrayList4 = this.A;
            if (arrayList4 != null) {
                for (d dVar6 : arrayList4) {
                    if (dVar6 instanceof j) {
                        ((j) dVar6).Q(this.B);
                    }
                }
            }
            for (String str6 : this.B.keySet()) {
                this.B.get(str6).e(map.containsKey(str6) ? map.get(str6).intValue() : 0);
            }
        }
        int i12 = 2;
        int size = this.f1495y.size() + 2;
        p[] pVarArr = new p[size];
        pVarArr[0] = this.f1477g;
        pVarArr[size - 1] = this.f1478h;
        if (this.f1495y.size() > 0 && this.f1476f == -1) {
            this.f1476f = 0;
        }
        Iterator<p> it = this.f1495y.iterator();
        int i13 = 1;
        while (it.hasNext()) {
            pVarArr[i13] = it.next();
            i13++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.f1478h.f1517t.keySet()) {
            if (this.f1477g.f1517t.containsKey(str7)) {
                if (!hashSet2.contains("CUSTOM," + str7)) {
                    hashSet4.add(str7);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.f1491u = strArr2;
        this.f1492v = new int[strArr2.length];
        int i14 = 0;
        while (true) {
            strArr = this.f1491u;
            if (i14 >= strArr.length) {
                break;
            }
            String str8 = strArr[i14];
            this.f1492v[i14] = 0;
            int i15 = 0;
            while (true) {
                if (i15 >= size) {
                    break;
                }
                if (pVarArr[i15].f1517t.containsKey(str8) && (aVar = pVarArr[i15].f1517t.get(str8)) != null) {
                    int[] iArr = this.f1492v;
                    iArr[i14] = iArr[i14] + aVar.h();
                    break;
                }
                i15++;
            }
            i14++;
        }
        boolean z6 = pVarArr[0].f1513p != d.f1349f;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i16 = 1; i16 < size; i16++) {
            pVarArr[i16].d(pVarArr[i16 - 1], zArr, this.f1491u, z6);
        }
        int i17 = 0;
        for (int i18 = 1; i18 < length; i18++) {
            if (zArr[i18]) {
                i17++;
            }
        }
        this.f1488r = new int[i17];
        int iMax = Math.max(2, i17);
        this.f1489s = new double[iMax];
        this.f1490t = new double[iMax];
        int i19 = 0;
        for (int i20 = 1; i20 < length; i20++) {
            if (zArr[i20]) {
                this.f1488r[i19] = i20;
                i19++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, size, this.f1488r.length);
        double[] dArr2 = new double[size];
        for (int i21 = 0; i21 < size; i21++) {
            pVarArr[i21].e(dArr[i21], this.f1488r);
            dArr2[i21] = pVarArr[i21].f1505h;
        }
        int i22 = 0;
        while (true) {
            int[] iArr2 = this.f1488r;
            if (i22 >= iArr2.length) {
                break;
            }
            if (iArr2[i22] < p.f1502y.length) {
                String str9 = p.f1502y[this.f1488r[i22]] + " [";
                for (int i23 = 0; i23 < size; i23++) {
                    str9 = str9 + dArr[i23][i22];
                }
            }
            i22++;
        }
        this.f1481k = new p.b[this.f1491u.length + 1];
        int i24 = 0;
        while (true) {
            String[] strArr3 = this.f1491u;
            if (i24 >= strArr3.length) {
                break;
            }
            String str10 = strArr3[i24];
            int i25 = i11;
            int i26 = i25;
            double[] dArr3 = null;
            double[][] dArr4 = null;
            while (i25 < size) {
                if (pVarArr[i25].k(str10)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr3 = new int[i12];
                        iArr3[1] = pVarArr[i25].i(str10);
                        iArr3[i11] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr3);
                    }
                    dArr3[i26] = pVarArr[i25].f1505h;
                    pVarArr[i25].h(str10, dArr4[i26], 0);
                    i26++;
                }
                i25++;
                i12 = 2;
                i11 = 0;
            }
            i24++;
            this.f1481k[i24] = p.b.a(this.f1476f, Arrays.copyOf(dArr3, i26), (double[][]) Arrays.copyOf(dArr4, i26));
            i12 = 2;
            i11 = 0;
        }
        this.f1481k[0] = p.b.a(this.f1476f, dArr2, dArr);
        if (pVarArr[0].f1513p != d.f1349f) {
            int[] iArr4 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i27 = 0; i27 < size; i27++) {
                iArr4[i27] = pVarArr[i27].f1513p;
                dArr5[i27] = pVarArr[i27].f1505h;
                dArr6[i27][0] = pVarArr[i27].f1507j;
                dArr6[i27][1] = pVarArr[i27].f1508k;
            }
            this.f1482l = p.b.b(iArr4, dArr5, dArr6);
        }
        float fR = Float.NaN;
        this.D = new HashMap<>();
        if (this.A != null) {
            for (String str11 : hashSet3) {
                t.c cVarH = t.c.h(str11);
                if (cVarH != null) {
                    if (cVarH.g() && Float.isNaN(fR)) {
                        fR = r();
                    }
                    cVarH.e(str11);
                    this.D.put(str11, cVarH);
                }
            }
            for (d dVar7 : this.A) {
                if (dVar7 instanceof f) {
                    ((f) dVar7).U(this.D);
                }
            }
            Iterator<t.c> it2 = this.D.values().iterator();
            while (it2.hasNext()) {
                it2.next().f(fR);
            }
        }
    }

    public void G(m mVar) {
        this.f1477g.s(mVar, mVar.f1477g);
        this.f1478h.s(mVar, mVar.f1478h);
    }

    public void a(d dVar) {
        this.A.add(dVar);
    }

    void b(ArrayList<d> arrayList) {
        this.A.addAll(arrayList);
    }

    int c(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] dArrG = this.f1481k[0].g();
        if (iArr != null) {
            Iterator<p> it = this.f1495y.iterator();
            int i7 = 0;
            while (it.hasNext()) {
                iArr[i7] = it.next().f1518u;
                i7++;
            }
        }
        int i8 = 0;
        for (int i9 = 0; i9 < dArrG.length; i9++) {
            this.f1481k[0].d(dArrG[i9], this.f1489s);
            this.f1477g.f(dArrG[i9], this.f1488r, this.f1489s, fArr, i8);
            i8 += 2;
        }
        return i8 / 2;
    }

    void d(float[] fArr, int i7) {
        double dA;
        float f7 = 1.0f;
        float f8 = 1.0f / (i7 - 1);
        HashMap<String, t.d> map = this.C;
        t.d dVar = map == null ? null : map.get("translationX");
        HashMap<String, t.d> map2 = this.C;
        t.d dVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, t.c> map3 = this.D;
        t.c cVar = map3 == null ? null : map3.get("translationX");
        HashMap<String, t.c> map4 = this.D;
        t.c cVar2 = map4 != null ? map4.get("translationY") : null;
        int i8 = 0;
        while (i8 < i7) {
            float fMin = i8 * f8;
            float f9 = this.f1485o;
            if (f9 != f7) {
                float f10 = this.f1484n;
                if (fMin < f10) {
                    fMin = 0.0f;
                }
                if (fMin > f10 && fMin < 1.0d) {
                    fMin = Math.min((fMin - f10) * f9, f7);
                }
            }
            float f11 = fMin;
            double d7 = f11;
            p.c cVar3 = this.f1477g.f1503e;
            float f12 = Float.NaN;
            float f13 = 0.0f;
            for (p pVar : this.f1495y) {
                p.c cVar4 = pVar.f1503e;
                double d8 = d7;
                if (cVar4 != null) {
                    float f14 = pVar.f1505h;
                    if (f14 < f11) {
                        f13 = f14;
                        cVar3 = cVar4;
                    } else if (Float.isNaN(f12)) {
                        f12 = pVar.f1505h;
                    }
                }
                d7 = d8;
            }
            double d9 = d7;
            if (cVar3 != null) {
                if (Float.isNaN(f12)) {
                    f12 = 1.0f;
                }
                dA = (((float) cVar3.a((f11 - f13) / r5)) * (f12 - f13)) + f13;
            } else {
                dA = d9;
            }
            this.f1481k[0].d(dA, this.f1489s);
            p.b bVar = this.f1482l;
            if (bVar != null) {
                double[] dArr = this.f1489s;
                if (dArr.length > 0) {
                    bVar.d(dA, dArr);
                }
            }
            int i9 = i8 * 2;
            int i10 = i8;
            this.f1477g.f(dA, this.f1488r, this.f1489s, fArr, i9);
            if (cVar != null) {
                fArr[i9] = fArr[i9] + cVar.a(f11);
            } else if (dVar != null) {
                fArr[i9] = fArr[i9] + dVar.a(f11);
            }
            if (cVar2 != null) {
                int i11 = i9 + 1;
                fArr[i11] = fArr[i11] + cVar2.a(f11);
            } else if (dVar2 != null) {
                int i12 = i9 + 1;
                fArr[i12] = fArr[i12] + dVar2.a(f11);
            }
            i8 = i10 + 1;
            f7 = 1.0f;
        }
    }

    void e(float f7, float[] fArr, int i7) {
        this.f1481k[0].d(g(f7, null), this.f1489s);
        this.f1477g.j(this.f1488r, this.f1489s, fArr, i7);
    }

    void f(boolean z6) {
        if (!"button".equals(androidx.constraintlayout.motion.widget.a.d(this.f1472b)) || this.E == null) {
            return;
        }
        int i7 = 0;
        while (true) {
            k[] kVarArr = this.E;
            if (i7 >= kVarArr.length) {
                return;
            }
            kVarArr[i7].u(z6 ? -100.0f : 100.0f, this.f1472b);
            i7++;
        }
    }

    public int h() {
        return this.f1477g.f1514q;
    }

    public void i(double d7, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.f1481k[0].d(d7, dArr);
        this.f1481k[0].f(d7, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.f1477g.g(d7, this.f1488r, dArr, fArr, dArr2, fArr2);
    }

    public float j() {
        return this.f1486p;
    }

    public float k() {
        return this.f1487q;
    }

    void l(float f7, float f8, float f9, float[] fArr) {
        double[] dArr;
        float fG = g(f7, this.f1496z);
        p.b[] bVarArr = this.f1481k;
        int i7 = 0;
        if (bVarArr == null) {
            p pVar = this.f1478h;
            float f10 = pVar.f1507j;
            p pVar2 = this.f1477g;
            float f11 = f10 - pVar2.f1507j;
            float f12 = pVar.f1508k - pVar2.f1508k;
            float f13 = (pVar.f1509l - pVar2.f1509l) + f11;
            float f14 = (pVar.f1510m - pVar2.f1510m) + f12;
            fArr[0] = (f11 * (1.0f - f8)) + (f13 * f8);
            fArr[1] = (f12 * (1.0f - f9)) + (f14 * f9);
            return;
        }
        double d7 = fG;
        bVarArr[0].f(d7, this.f1490t);
        this.f1481k[0].d(d7, this.f1489s);
        float f15 = this.f1496z[0];
        while (true) {
            dArr = this.f1490t;
            if (i7 >= dArr.length) {
                break;
            }
            dArr[i7] = dArr[i7] * ((double) f15);
            i7++;
        }
        p.b bVar = this.f1482l;
        if (bVar == null) {
            this.f1477g.q(f8, f9, fArr, this.f1488r, dArr, this.f1489s);
            return;
        }
        double[] dArr2 = this.f1489s;
        if (dArr2.length > 0) {
            bVar.d(d7, dArr2);
            this.f1482l.f(d7, this.f1490t);
            this.f1477g.q(f8, f9, fArr, this.f1488r, this.f1490t, this.f1489s);
        }
    }

    public int m() {
        int iMax = this.f1477g.f1504g;
        Iterator<p> it = this.f1495y.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().f1504g);
        }
        return Math.max(iMax, this.f1478h.f1504g);
    }

    public float n() {
        return this.f1478h.f1507j;
    }

    public float o() {
        return this.f1478h.f1508k;
    }

    p q(int i7) {
        return this.f1495y.get(i7);
    }

    public View s() {
        return this.f1472b;
    }

    public String toString() {
        return " start: x: " + this.f1477g.f1507j + " y: " + this.f1477g.f1508k + " end: x: " + this.f1478h.f1507j + " y: " + this.f1478h.f1508k;
    }

    boolean u(View view, float f7, long j7, p.d dVar) {
        f.d dVar2;
        boolean zJ;
        int i7;
        double d7;
        float fG = g(f7, null);
        int i8 = this.I;
        if (i8 != d.f1349f) {
            float f8 = 1.0f / i8;
            float fFloor = ((float) Math.floor(fG / f8)) * f8;
            float f9 = (fG % f8) / f8;
            if (!Float.isNaN(this.J)) {
                f9 = (f9 + this.J) % 1.0f;
            }
            Interpolator interpolator = this.K;
            fG = ((interpolator != null ? interpolator.getInterpolation(f9) : ((double) f9) > 0.5d ? 1.0f : 0.0f) * f8) + fFloor;
        }
        float f10 = fG;
        HashMap<String, t.d> map = this.C;
        if (map != null) {
            Iterator<t.d> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().g(view, f10);
            }
        }
        HashMap<String, t.f> map2 = this.B;
        if (map2 != null) {
            f.d dVar3 = null;
            boolean zI = false;
            for (t.f fVar : map2.values()) {
                if (fVar instanceof f.d) {
                    dVar3 = (f.d) fVar;
                } else {
                    zI |= fVar.i(view, f10, j7, dVar);
                }
            }
            zJ = zI;
            dVar2 = dVar3;
        } else {
            dVar2 = null;
            zJ = false;
        }
        p.b[] bVarArr = this.f1481k;
        if (bVarArr != null) {
            double d8 = f10;
            bVarArr[0].d(d8, this.f1489s);
            this.f1481k[0].f(d8, this.f1490t);
            p.b bVar = this.f1482l;
            if (bVar != null) {
                double[] dArr = this.f1489s;
                if (dArr.length > 0) {
                    bVar.d(d8, dArr);
                    this.f1482l.f(d8, this.f1490t);
                }
            }
            if (this.L) {
                d7 = d8;
            } else {
                d7 = d8;
                this.f1477g.r(f10, view, this.f1488r, this.f1489s, this.f1490t, null, this.f1474d);
                this.f1474d = false;
            }
            if (this.G != d.f1349f) {
                if (this.H == null) {
                    this.H = ((View) view.getParent()).findViewById(this.G);
                }
                if (this.H != null) {
                    float top = (r1.getTop() + this.H.getBottom()) / 2.0f;
                    float left = (this.H.getLeft() + this.H.getRight()) / 2.0f;
                    if (view.getRight() - view.getLeft() > 0 && view.getBottom() - view.getTop() > 0) {
                        view.setPivotX(left - view.getLeft());
                        view.setPivotY(top - view.getTop());
                    }
                }
            }
            HashMap<String, t.d> map3 = this.C;
            if (map3 != null) {
                for (t.d dVar4 : map3.values()) {
                    if (dVar4 instanceof d.C0152d) {
                        double[] dArr2 = this.f1490t;
                        if (dArr2.length > 1) {
                            ((d.C0152d) dVar4).h(view, f10, dArr2[0], dArr2[1]);
                        }
                    }
                }
            }
            if (dVar2 != null) {
                double[] dArr3 = this.f1490t;
                i7 = 1;
                zJ |= dVar2.j(view, dVar, f10, j7, dArr3[0], dArr3[1]);
            } else {
                i7 = 1;
            }
            int i9 = i7;
            while (true) {
                p.b[] bVarArr2 = this.f1481k;
                if (i9 >= bVarArr2.length) {
                    break;
                }
                bVarArr2[i9].e(d7, this.f1494x);
                t.a.b(this.f1477g.f1517t.get(this.f1491u[i9 - 1]), view, this.f1494x);
                i9++;
            }
            l lVar = this.f1479i;
            if (lVar.f1451g == 0) {
                if (f10 <= 0.0f) {
                    view.setVisibility(lVar.f1452h);
                } else if (f10 >= 1.0f) {
                    view.setVisibility(this.f1480j.f1452h);
                } else if (this.f1480j.f1452h != lVar.f1452h) {
                    view.setVisibility(0);
                }
            }
            if (this.E != null) {
                int i10 = 0;
                while (true) {
                    k[] kVarArr = this.E;
                    if (i10 >= kVarArr.length) {
                        break;
                    }
                    kVarArr[i10].u(f10, view);
                    i10++;
                }
            }
        } else {
            i7 = 1;
            p pVar = this.f1477g;
            float f11 = pVar.f1507j;
            p pVar2 = this.f1478h;
            float f12 = f11 + ((pVar2.f1507j - f11) * f10);
            float f13 = pVar.f1508k;
            float f14 = f13 + ((pVar2.f1508k - f13) * f10);
            float f15 = pVar.f1509l;
            float f16 = pVar2.f1509l;
            float f17 = pVar.f1510m;
            float f18 = pVar2.f1510m;
            float f19 = f12 + 0.5f;
            int i11 = (int) f19;
            float f20 = f14 + 0.5f;
            int i12 = (int) f20;
            int i13 = (int) (f19 + ((f16 - f15) * f10) + f15);
            int i14 = (int) (f20 + ((f18 - f17) * f10) + f17);
            int i15 = i13 - i11;
            int i16 = i14 - i12;
            if (f16 != f15 || f18 != f17 || this.f1474d) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i15, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i16, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                this.f1474d = false;
            }
            view.layout(i11, i12, i13, i14);
        }
        HashMap<String, t.c> map4 = this.D;
        if (map4 != null) {
            for (t.c cVar : map4.values()) {
                if (cVar instanceof c.d) {
                    double[] dArr4 = this.f1490t;
                    ((c.d) cVar).j(view, f10, dArr4[0], dArr4[i7]);
                } else {
                    cVar.i(view, f10);
                }
            }
        }
        return zJ;
    }

    public void w() {
        this.f1474d = true;
    }

    void x(Rect rect, Rect rect2, int i7, int i8, int i9) {
        if (i7 == 1) {
            int i10 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i9 - ((i10 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
            return;
        }
        if (i7 == 2) {
            int i11 = rect.left + rect.right;
            rect2.left = i8 - (((rect.top + rect.bottom) + rect.width()) / 2);
            rect2.top = (i11 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
            return;
        }
        if (i7 == 3) {
            int i12 = rect.left + rect.right;
            rect2.left = ((rect.height() / 2) + rect.top) - (i12 / 2);
            rect2.top = i9 - ((i12 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
            return;
        }
        if (i7 != 4) {
            return;
        }
        int i13 = rect.left + rect.right;
        rect2.left = i8 - (((rect.bottom + rect.top) + rect.width()) / 2);
        rect2.top = (i13 - rect.height()) / 2;
        rect2.right = rect2.left + rect.width();
        rect2.bottom = rect2.top + rect.height();
    }

    void y(View view) {
        p pVar = this.f1477g;
        pVar.f1505h = 0.0f;
        pVar.f1506i = 0.0f;
        this.L = true;
        pVar.p(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f1478h.p(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f1479i.j(view);
        this.f1480j.j(view);
    }

    void z(Rect rect, androidx.constraintlayout.widget.d dVar, int i7, int i8) {
        int i9 = dVar.f1771d;
        if (i9 != 0) {
            x(rect, this.f1471a, i9, i7, i8);
            rect = this.f1471a;
        }
        p pVar = this.f1478h;
        pVar.f1505h = 1.0f;
        pVar.f1506i = 1.0f;
        v(pVar);
        this.f1478h.p(rect.left, rect.top, rect.width(), rect.height());
        this.f1478h.a(dVar.z(this.f1473c));
        this.f1480j.i(rect, dVar, i9, this.f1473c);
    }
}
