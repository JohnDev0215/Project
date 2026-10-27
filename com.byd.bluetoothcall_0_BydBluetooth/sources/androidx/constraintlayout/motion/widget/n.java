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
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.b;
import b.e.a.a.c;
import b.e.a.a.d;
import b.e.a.a.f;
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
    private HashMap<String, b.e.a.a.f> B;
    private HashMap<String, b.e.a.a.d> C;
    private HashMap<String, b.e.a.a.c> D;
    private l[] E;
    private int F;
    private int G;
    private View H;
    private int I;
    private float J;
    private Interpolator K;
    private boolean L;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    View f942b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f943c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f945e;
    private androidx.constraintlayout.core.j.a.b[] k;
    private androidx.constraintlayout.core.j.a.b l;
    float p;
    float q;
    private int[] r;
    private double[] s;
    private double[] t;
    private String[] u;
    private int[] v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Rect f941a = new Rect();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f944d = false;
    private int f = -1;
    private p g = new p();
    private p h = new p();
    private m i = new m();
    private m j = new m();
    float m = Float.NaN;
    float n = 0.0f;
    float o = 1.0f;
    private int w = 4;
    private float[] x = new float[4];
    private ArrayList<p> y = new ArrayList<>();
    private float[] z = new float[1];
    private ArrayList<e> A = new ArrayList<>();

    /* JADX INFO: compiled from: MotionController.java */
    class a implements Interpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ androidx.constraintlayout.core.j.a.c f946a;

        a(androidx.constraintlayout.core.j.a.c cVar) {
            this.f946a = cVar;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) this.f946a.a(f);
        }
    }

    n(View view) {
        int i = e.f925a;
        this.F = i;
        this.G = i;
        this.H = null;
        this.I = i;
        this.J = Float.NaN;
        this.K = null;
        this.L = false;
        H(view);
    }

    private float g(float f, float[] fArr) {
        float f2 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f3 = this.o;
            if (f3 != 1.0d) {
                float f4 = this.n;
                if (f < f4) {
                    f = 0.0f;
                }
                if (f > f4 && f < 1.0d) {
                    f = Math.min((f - f4) * f3, 1.0f);
                }
            }
        }
        androidx.constraintlayout.core.j.a.c cVar = this.g.f948c;
        float f5 = Float.NaN;
        for (p pVar : this.y) {
            androidx.constraintlayout.core.j.a.c cVar2 = pVar.f948c;
            if (cVar2 != null) {
                float f6 = pVar.f950e;
                if (f6 < f) {
                    cVar = cVar2;
                    f2 = f6;
                } else if (Float.isNaN(f5)) {
                    f5 = pVar.f950e;
                }
            }
        }
        if (cVar != null) {
            float f7 = (Float.isNaN(f5) ? 1.0f : f5) - f2;
            double d2 = (f - f2) / f7;
            f = (((float) cVar.a(d2)) * f7) + f2;
            if (fArr != null) {
                fArr[0] = (float) cVar.b(d2);
            }
        }
        return f;
    }

    private static Interpolator p(Context context, int i, String str, int i2) {
        if (i == -2) {
            return AnimationUtils.loadInterpolator(context, i2);
        }
        if (i == -1) {
            return new a(androidx.constraintlayout.core.j.a.c.c(str));
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
        if (i != 5) {
            return null;
        }
        return new OvershootInterpolator();
    }

    private float s() {
        char c2;
        float fHypot;
        float[] fArr = new float[2];
        float f = 1.0f / 99;
        double d2 = 0.0d;
        double d3 = 0.0d;
        float f2 = 0.0f;
        int i = 0;
        while (i < 100) {
            float f3 = i * f;
            double dA = f3;
            androidx.constraintlayout.core.j.a.c cVar = this.g.f948c;
            float f4 = Float.NaN;
            float f5 = 0.0f;
            for (p pVar : this.y) {
                androidx.constraintlayout.core.j.a.c cVar2 = pVar.f948c;
                if (cVar2 != null) {
                    float f6 = pVar.f950e;
                    if (f6 < f3) {
                        cVar = cVar2;
                        f5 = f6;
                    } else if (Float.isNaN(f4)) {
                        f4 = pVar.f950e;
                    }
                }
            }
            if (cVar != null) {
                if (Float.isNaN(f4)) {
                    f4 = 1.0f;
                }
                dA = (((float) cVar.a((f3 - f5) / r17)) * (f4 - f5)) + f5;
            }
            this.k[0].d(dA, this.s);
            float f7 = f2;
            int i2 = i;
            this.g.i(dA, this.r, this.s, fArr, 0);
            if (i2 > 0) {
                c2 = 0;
                fHypot = (float) (((double) f7) + Math.hypot(d3 - ((double) fArr[1]), d2 - ((double) fArr[0])));
            } else {
                c2 = 0;
                fHypot = f7;
            }
            d2 = fArr[c2];
            i = i2 + 1;
            f2 = fHypot;
            d3 = fArr[1];
        }
        return f2;
    }

    private void w(p pVar) {
        if (Collections.binarySearch(this.y, pVar) == 0) {
            Log.e("MotionController", " KeyPath position \"" + pVar.f + "\" outside of range");
        }
        this.y.add((-r0) - 1, pVar);
    }

    private void y(p pVar) {
        pVar.s((int) this.f942b.getX(), (int) this.f942b.getY(), this.f942b.getWidth(), this.f942b.getHeight());
    }

    void A(Rect rect, Rect rect2, int i, int i2, int i3) {
        if (i == 1) {
            int i4 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i3 - ((i4 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
            return;
        }
        if (i == 2) {
            int i5 = rect.left + rect.right;
            rect2.left = i2 - (((rect.top + rect.bottom) + rect.width()) / 2);
            rect2.top = (i5 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
            return;
        }
        if (i == 3) {
            int i6 = rect.left + rect.right;
            rect2.left = ((rect.height() / 2) + rect.top) - (i6 / 2);
            rect2.top = i3 - ((i6 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
            return;
        }
        if (i != 4) {
            return;
        }
        int i7 = rect.left + rect.right;
        rect2.left = i2 - (((rect.bottom + rect.top) + rect.width()) / 2);
        rect2.top = (i7 - rect.height()) / 2;
        rect2.right = rect2.left + rect.width();
        rect2.bottom = rect2.top + rect.height();
    }

    void B(View view) {
        p pVar = this.g;
        pVar.f950e = 0.0f;
        pVar.f = 0.0f;
        this.L = true;
        pVar.s(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.h.s(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.i.m(view);
        this.j.m(view);
    }

    void C(Rect rect, androidx.constraintlayout.widget.b bVar, int i, int i2) {
        int i3 = bVar.g;
        if (i3 != 0) {
            A(rect, this.f941a, i3, i, i2);
            rect = this.f941a;
        }
        p pVar = this.h;
        pVar.f950e = 1.0f;
        pVar.f = 1.0f;
        y(pVar);
        this.h.s(rect.left, rect.top, rect.width(), rect.height());
        this.h.b(bVar.x(this.f943c));
        this.j.l(rect, bVar, i3, this.f943c);
    }

    public void D(int i) {
        this.F = i;
    }

    void E(View view) {
        p pVar = this.g;
        pVar.f950e = 0.0f;
        pVar.f = 0.0f;
        pVar.s(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.i.m(view);
    }

    void F(Rect rect, androidx.constraintlayout.widget.b bVar, int i, int i2) {
        int i3 = bVar.g;
        if (i3 != 0) {
            A(rect, this.f941a, i3, i, i2);
        }
        p pVar = this.g;
        pVar.f950e = 0.0f;
        pVar.f = 0.0f;
        y(pVar);
        this.g.s(rect.left, rect.top, rect.width(), rect.height());
        b.a aVarX = bVar.x(this.f943c);
        this.g.b(aVarX);
        this.m = aVarX.f1078d.h;
        this.i.l(rect, bVar, i3, this.f943c);
        this.G = aVarX.f.j;
        b.c cVar = aVarX.f1078d;
        this.I = cVar.l;
        this.J = cVar.k;
        Context context = this.f942b.getContext();
        b.c cVar2 = aVarX.f1078d;
        this.K = p(context, cVar2.n, cVar2.m, cVar2.o);
    }

    public void G(b.e.a.a.e eVar, View view, int i, int i2, int i3) {
        p pVar = this.g;
        pVar.f950e = 0.0f;
        pVar.f = 0.0f;
        Rect rect = new Rect();
        if (i == 1) {
            int i4 = eVar.f2158b + eVar.f2160d;
            rect.left = ((eVar.f2159c + eVar.f2161e) - eVar.b()) / 2;
            rect.top = i2 - ((i4 + eVar.a()) / 2);
            rect.right = rect.left + eVar.b();
            rect.bottom = rect.top + eVar.a();
        } else if (i == 2) {
            int i5 = eVar.f2158b + eVar.f2160d;
            rect.left = i3 - (((eVar.f2159c + eVar.f2161e) + eVar.b()) / 2);
            rect.top = (i5 - eVar.a()) / 2;
            rect.right = rect.left + eVar.b();
            rect.bottom = rect.top + eVar.a();
        }
        this.g.s(rect.left, rect.top, rect.width(), rect.height());
        this.i.k(rect, view, i, eVar.f2157a);
    }

    public void H(View view) {
        this.f942b = view;
        this.f943c = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            this.f945e = ((ConstraintLayout.b) layoutParams).a();
        }
    }

    public void I(int i, int i2, float f, long j) {
        ArrayList arrayList;
        String[] strArr;
        ConstraintAttribute constraintAttribute;
        b.e.a.a.f fVarH;
        ConstraintAttribute constraintAttribute2;
        Integer num;
        b.e.a.a.d dVarG;
        ConstraintAttribute constraintAttribute3;
        new HashSet();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        int i3 = this.F;
        if (i3 != e.f925a) {
            this.g.m = i3;
        }
        this.i.i(this.j, hashSet2);
        ArrayList<e> arrayList2 = this.A;
        if (arrayList2 != null) {
            arrayList = null;
            for (e eVar : arrayList2) {
                if (eVar instanceof i) {
                    i iVar = (i) eVar;
                    w(new p(i, i2, iVar, this.g, this.h));
                    int i4 = iVar.g;
                    if (i4 != e.f925a) {
                        this.f = i4;
                    }
                } else if (eVar instanceof g) {
                    eVar.d(hashSet3);
                } else if (eVar instanceof k) {
                    eVar.d(hashSet);
                } else if (eVar instanceof l) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((l) eVar);
                } else {
                    eVar.h(map);
                    eVar.d(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        int i5 = 0;
        if (arrayList != null) {
            this.E = (l[]) arrayList.toArray(new l[0]);
        }
        char c2 = 1;
        if (!hashSet2.isEmpty()) {
            this.C = new HashMap<>();
            for (String str : hashSet2) {
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[c2];
                    for (e eVar2 : this.A) {
                        HashMap<String, ConstraintAttribute> map2 = eVar2.f;
                        if (map2 != null && (constraintAttribute3 = map2.get(str2)) != null) {
                            sparseArray.append(eVar2.f926b, constraintAttribute3);
                        }
                    }
                    dVarG = b.e.a.a.d.f(str, sparseArray);
                } else {
                    dVarG = b.e.a.a.d.g(str);
                }
                if (dVarG != null) {
                    dVarG.d(str);
                    this.C.put(str, dVarG);
                }
                c2 = 1;
            }
            ArrayList<e> arrayList3 = this.A;
            if (arrayList3 != null) {
                for (e eVar3 : arrayList3) {
                    if (eVar3 instanceof f) {
                        eVar3.a(this.C);
                    }
                }
            }
            this.i.b(this.C, 0);
            this.j.b(this.C, 100);
            for (String str3 : this.C.keySet()) {
                int iIntValue = (!map.containsKey(str3) || (num = map.get(str3)) == null) ? 0 : num.intValue();
                b.e.a.a.d dVar = this.C.get(str3);
                if (dVar != null) {
                    dVar.e(iIntValue);
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
                        String str5 = str4.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
                        for (e eVar4 : this.A) {
                            HashMap<String, ConstraintAttribute> map3 = eVar4.f;
                            if (map3 != null && (constraintAttribute2 = map3.get(str5)) != null) {
                                sparseArray2.append(eVar4.f926b, constraintAttribute2);
                            }
                        }
                        fVarH = b.e.a.a.f.g(str4, sparseArray2);
                    } else {
                        fVarH = b.e.a.a.f.h(str4, j);
                    }
                    if (fVarH != null) {
                        fVarH.d(str4);
                        this.B.put(str4, fVarH);
                    }
                }
            }
            ArrayList<e> arrayList4 = this.A;
            if (arrayList4 != null) {
                for (e eVar5 : arrayList4) {
                    if (eVar5 instanceof k) {
                        ((k) eVar5).U(this.B);
                    }
                }
            }
            for (String str6 : this.B.keySet()) {
                this.B.get(str6).e(map.containsKey(str6) ? map.get(str6).intValue() : 0);
            }
        }
        int i6 = 2;
        int size = this.y.size() + 2;
        p[] pVarArr = new p[size];
        pVarArr[0] = this.g;
        pVarArr[size - 1] = this.h;
        if (this.y.size() > 0 && this.f == -1) {
            this.f = 0;
        }
        Iterator<p> it = this.y.iterator();
        int i7 = 1;
        while (it.hasNext()) {
            pVarArr[i7] = it.next();
            i7++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.h.q.keySet()) {
            if (this.g.q.containsKey(str7)) {
                if (!hashSet2.contains("CUSTOM," + str7)) {
                    hashSet4.add(str7);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.u = strArr2;
        this.v = new int[strArr2.length];
        int i8 = 0;
        while (true) {
            strArr = this.u;
            if (i8 >= strArr.length) {
                break;
            }
            String str8 = strArr[i8];
            this.v[i8] = 0;
            int i9 = 0;
            while (true) {
                if (i9 >= size) {
                    break;
                }
                if (pVarArr[i9].q.containsKey(str8) && (constraintAttribute = pVarArr[i9].q.get(str8)) != null) {
                    int[] iArr = this.v;
                    iArr[i8] = iArr[i8] + constraintAttribute.h();
                    break;
                }
                i9++;
            }
            i8++;
        }
        boolean z = pVarArr[0].m != e.f925a;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i10 = 1; i10 < size; i10++) {
            pVarArr[i10].g(pVarArr[i10 - 1], zArr, this.u, z);
        }
        int i11 = 0;
        for (int i12 = 1; i12 < length; i12++) {
            if (zArr[i12]) {
                i11++;
            }
        }
        this.r = new int[i11];
        int iMax = Math.max(2, i11);
        this.s = new double[iMax];
        this.t = new double[iMax];
        int i13 = 0;
        for (int i14 = 1; i14 < length; i14++) {
            if (zArr[i14]) {
                this.r[i13] = i14;
                i13++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, size, this.r.length);
        double[] dArr2 = new double[size];
        for (int i15 = 0; i15 < size; i15++) {
            pVarArr[i15].h(dArr[i15], this.r);
            dArr2[i15] = pVarArr[i15].f950e;
        }
        int i16 = 0;
        while (true) {
            int[] iArr2 = this.r;
            if (i16 >= iArr2.length) {
                break;
            }
            if (iArr2[i16] < p.f947b.length) {
                String str9 = p.f947b[this.r[i16]] + " [";
                for (int i17 = 0; i17 < size; i17++) {
                    str9 = str9 + dArr[i17][i16];
                }
            }
            i16++;
        }
        this.k = new androidx.constraintlayout.core.j.a.b[this.u.length + 1];
        int i18 = 0;
        while (true) {
            String[] strArr3 = this.u;
            if (i18 >= strArr3.length) {
                break;
            }
            String str10 = strArr3[i18];
            int i19 = i5;
            int i20 = i19;
            double[] dArr3 = null;
            double[][] dArr4 = null;
            while (i19 < size) {
                if (pVarArr[i19].n(str10)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr3 = new int[i6];
                        iArr3[1] = pVarArr[i19].l(str10);
                        iArr3[i5] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr3);
                    }
                    dArr3[i20] = pVarArr[i19].f950e;
                    pVarArr[i19].k(str10, dArr4[i20], 0);
                    i20++;
                }
                i19++;
                i6 = 2;
                i5 = 0;
            }
            i18++;
            this.k[i18] = androidx.constraintlayout.core.j.a.b.a(this.f, Arrays.copyOf(dArr3, i20), (double[][]) Arrays.copyOf(dArr4, i20));
            i6 = 2;
            i5 = 0;
        }
        this.k[0] = androidx.constraintlayout.core.j.a.b.a(this.f, dArr2, dArr);
        if (pVarArr[0].m != e.f925a) {
            int[] iArr4 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i21 = 0; i21 < size; i21++) {
                iArr4[i21] = pVarArr[i21].m;
                dArr5[i21] = pVarArr[i21].f950e;
                dArr6[i21][0] = pVarArr[i21].g;
                dArr6[i21][1] = pVarArr[i21].h;
            }
            this.l = androidx.constraintlayout.core.j.a.b.b(iArr4, dArr5, dArr6);
        }
        float fS = Float.NaN;
        this.D = new HashMap<>();
        if (this.A != null) {
            for (String str11 : hashSet3) {
                b.e.a.a.c cVarI = b.e.a.a.c.i(str11);
                if (cVarI != null) {
                    if (cVarI.h() && Float.isNaN(fS)) {
                        fS = s();
                    }
                    cVarI.f(str11);
                    this.D.put(str11, cVarI);
                }
            }
            for (e eVar6 : this.A) {
                if (eVar6 instanceof g) {
                    ((g) eVar6).Y(this.D);
                }
            }
            Iterator<b.e.a.a.c> it2 = this.D.values().iterator();
            while (it2.hasNext()) {
                it2.next().g(fS);
            }
        }
    }

    public void J(n nVar) {
        this.g.v(nVar, nVar.g);
        this.h.v(nVar, nVar.h);
    }

    public void a(e eVar) {
        this.A.add(eVar);
    }

    void b(ArrayList<e> arrayList) {
        this.A.addAll(arrayList);
    }

    int c(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] dArrH = this.k[0].h();
        if (iArr != null) {
            Iterator<p> it = this.y.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().r;
                i++;
            }
        }
        int i2 = 0;
        for (int i3 = 0; i3 < dArrH.length; i3++) {
            this.k[0].d(dArrH[i3], this.s);
            this.g.i(dArrH[i3], this.r, this.s, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    void d(float[] fArr, int i) {
        double dA;
        float f = 1.0f;
        float f2 = 1.0f / (i - 1);
        HashMap<String, b.e.a.a.d> map = this.C;
        b.e.a.a.d dVar = map == null ? null : map.get("translationX");
        HashMap<String, b.e.a.a.d> map2 = this.C;
        b.e.a.a.d dVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, b.e.a.a.c> map3 = this.D;
        b.e.a.a.c cVar = map3 == null ? null : map3.get("translationX");
        HashMap<String, b.e.a.a.c> map4 = this.D;
        b.e.a.a.c cVar2 = map4 != null ? map4.get("translationY") : null;
        int i2 = 0;
        while (i2 < i) {
            float fMin = i2 * f2;
            float f3 = this.o;
            if (f3 != f) {
                float f4 = this.n;
                if (fMin < f4) {
                    fMin = 0.0f;
                }
                if (fMin > f4 && fMin < 1.0d) {
                    fMin = Math.min((fMin - f4) * f3, f);
                }
            }
            float f5 = fMin;
            double d2 = f5;
            androidx.constraintlayout.core.j.a.c cVar3 = this.g.f948c;
            float f6 = Float.NaN;
            float f7 = 0.0f;
            for (p pVar : this.y) {
                androidx.constraintlayout.core.j.a.c cVar4 = pVar.f948c;
                double d3 = d2;
                if (cVar4 != null) {
                    float f8 = pVar.f950e;
                    if (f8 < f5) {
                        f7 = f8;
                        cVar3 = cVar4;
                    } else if (Float.isNaN(f6)) {
                        f6 = pVar.f950e;
                    }
                }
                d2 = d3;
            }
            double d4 = d2;
            if (cVar3 != null) {
                if (Float.isNaN(f6)) {
                    f6 = 1.0f;
                }
                dA = (((float) cVar3.a((f5 - f7) / r5)) * (f6 - f7)) + f7;
            } else {
                dA = d4;
            }
            this.k[0].d(dA, this.s);
            androidx.constraintlayout.core.j.a.b bVar = this.l;
            if (bVar != null) {
                double[] dArr = this.s;
                if (dArr.length > 0) {
                    bVar.d(dA, dArr);
                }
            }
            int i3 = i2 * 2;
            int i4 = i2;
            this.g.i(dA, this.r, this.s, fArr, i3);
            if (cVar != null) {
                fArr[i3] = fArr[i3] + cVar.a(f5);
            } else if (dVar != null) {
                fArr[i3] = fArr[i3] + dVar.a(f5);
            }
            if (cVar2 != null) {
                int i5 = i3 + 1;
                fArr[i5] = fArr[i5] + cVar2.a(f5);
            } else if (dVar2 != null) {
                int i6 = i3 + 1;
                fArr[i6] = fArr[i6] + dVar2.a(f5);
            }
            i2 = i4 + 1;
            f = 1.0f;
        }
    }

    void e(float f, float[] fArr, int i) {
        this.k[0].d(g(f, null), this.s);
        this.g.m(this.r, this.s, fArr, i);
    }

    void f(boolean z) {
        if (!"button".equals(b.d(this.f942b)) || this.E == null) {
            return;
        }
        int i = 0;
        while (true) {
            l[] lVarArr = this.E;
            if (i >= lVarArr.length) {
                return;
            }
            lVarArr[i].y(z ? -100.0f : 100.0f, this.f942b);
            i++;
        }
    }

    public int h() {
        return this.g.n;
    }

    public void i(double d2, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.k[0].d(d2, dArr);
        this.k[0].g(d2, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.g.j(d2, this.r, dArr, fArr, dArr2, fArr2);
    }

    public float j() {
        return this.p;
    }

    public float k() {
        return this.q;
    }

    void l(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float fG = g(f, this.z);
        androidx.constraintlayout.core.j.a.b[] bVarArr = this.k;
        int i = 0;
        if (bVarArr == null) {
            p pVar = this.h;
            float f4 = pVar.g;
            p pVar2 = this.g;
            float f5 = f4 - pVar2.g;
            float f6 = pVar.h - pVar2.h;
            float f7 = (pVar.i - pVar2.i) + f5;
            float f8 = (pVar.j - pVar2.j) + f6;
            fArr[0] = (f5 * (1.0f - f2)) + (f7 * f2);
            fArr[1] = (f6 * (1.0f - f3)) + (f8 * f3);
            return;
        }
        double d2 = fG;
        bVarArr[0].g(d2, this.t);
        this.k[0].d(d2, this.s);
        float f9 = this.z[0];
        while (true) {
            dArr = this.t;
            if (i >= dArr.length) {
                break;
            }
            dArr[i] = dArr[i] * ((double) f9);
            i++;
        }
        androidx.constraintlayout.core.j.a.b bVar = this.l;
        if (bVar == null) {
            this.g.t(f2, f3, fArr, this.r, dArr, this.s);
            return;
        }
        double[] dArr2 = this.s;
        if (dArr2.length > 0) {
            bVar.d(d2, dArr2);
            this.l.g(d2, this.t);
            this.g.t(f2, f3, fArr, this.r, this.t, this.s);
        }
    }

    public int m() {
        int iMax = this.g.f949d;
        Iterator<p> it = this.y.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().f949d);
        }
        return Math.max(iMax, this.h.f949d);
    }

    public float n() {
        return this.h.g;
    }

    public float o() {
        return this.h.h;
    }

    p q(int i) {
        return this.y.get(i);
    }

    void r(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float fG = g(f, this.z);
        HashMap<String, b.e.a.a.d> map = this.C;
        b.e.a.a.d dVar = map == null ? null : map.get("translationX");
        HashMap<String, b.e.a.a.d> map2 = this.C;
        b.e.a.a.d dVar2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, b.e.a.a.d> map3 = this.C;
        b.e.a.a.d dVar3 = map3 == null ? null : map3.get("rotation");
        HashMap<String, b.e.a.a.d> map4 = this.C;
        b.e.a.a.d dVar4 = map4 == null ? null : map4.get("scaleX");
        HashMap<String, b.e.a.a.d> map5 = this.C;
        b.e.a.a.d dVar5 = map5 == null ? null : map5.get("scaleY");
        HashMap<String, b.e.a.a.c> map6 = this.D;
        b.e.a.a.c cVar = map6 == null ? null : map6.get("translationX");
        HashMap<String, b.e.a.a.c> map7 = this.D;
        b.e.a.a.c cVar2 = map7 == null ? null : map7.get("translationY");
        HashMap<String, b.e.a.a.c> map8 = this.D;
        b.e.a.a.c cVar3 = map8 == null ? null : map8.get("rotation");
        HashMap<String, b.e.a.a.c> map9 = this.D;
        b.e.a.a.c cVar4 = map9 == null ? null : map9.get("scaleX");
        HashMap<String, b.e.a.a.c> map10 = this.D;
        b.e.a.a.c cVar5 = map10 != null ? map10.get("scaleY") : null;
        androidx.constraintlayout.core.j.a.p pVar = new androidx.constraintlayout.core.j.a.p();
        pVar.b();
        pVar.d(dVar3, fG);
        pVar.h(dVar, dVar2, fG);
        pVar.f(dVar4, dVar5, fG);
        pVar.c(cVar3, fG);
        pVar.g(cVar, cVar2, fG);
        pVar.e(cVar4, cVar5, fG);
        androidx.constraintlayout.core.j.a.b bVar = this.l;
        if (bVar != null) {
            double[] dArr = this.s;
            if (dArr.length > 0) {
                double d2 = fG;
                bVar.d(d2, dArr);
                this.l.g(d2, this.t);
                this.g.t(f2, f3, fArr, this.r, this.t, this.s);
            }
            pVar.a(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.k == null) {
            p pVar2 = this.h;
            float f4 = pVar2.g;
            p pVar3 = this.g;
            float f5 = f4 - pVar3.g;
            b.e.a.a.c cVar6 = cVar5;
            float f6 = pVar2.h - pVar3.h;
            b.e.a.a.c cVar7 = cVar4;
            float f7 = (pVar2.i - pVar3.i) + f5;
            float f8 = (pVar2.j - pVar3.j) + f6;
            fArr[0] = (f5 * (1.0f - f2)) + (f7 * f2);
            fArr[1] = (f6 * (1.0f - f3)) + (f8 * f3);
            pVar.b();
            pVar.d(dVar3, fG);
            pVar.h(dVar, dVar2, fG);
            pVar.f(dVar4, dVar5, fG);
            pVar.c(cVar3, fG);
            pVar.g(cVar, cVar2, fG);
            pVar.e(cVar7, cVar6, fG);
            pVar.a(f2, f3, i, i2, fArr);
            return;
        }
        double dG = g(fG, this.z);
        this.k[0].g(dG, this.t);
        this.k[0].d(dG, this.s);
        float f9 = this.z[0];
        while (true) {
            double[] dArr2 = this.t;
            if (i3 >= dArr2.length) {
                this.g.t(f2, f3, fArr, this.r, dArr2, this.s);
                pVar.a(f2, f3, i, i2, fArr);
                return;
            } else {
                dArr2[i3] = dArr2[i3] * ((double) f9);
                i3++;
            }
        }
    }

    public float t() {
        return this.g.g;
    }

    public String toString() {
        return " start: x: " + this.g.g + " y: " + this.g.h + " end: x: " + this.h.g + " y: " + this.h.h;
    }

    public float u() {
        return this.g.h;
    }

    public View v() {
        return this.f942b;
    }

    boolean x(View view, float f, long j, androidx.constraintlayout.core.j.a.d dVar) {
        f.d dVar2;
        boolean zJ;
        int i;
        double d2;
        float fG = g(f, null);
        int i2 = this.I;
        if (i2 != e.f925a) {
            float f2 = 1.0f / i2;
            float fFloor = ((float) Math.floor(fG / f2)) * f2;
            float f3 = (fG % f2) / f2;
            if (!Float.isNaN(this.J)) {
                f3 = (f3 + this.J) % 1.0f;
            }
            Interpolator interpolator = this.K;
            fG = ((interpolator != null ? interpolator.getInterpolation(f3) : ((double) f3) > 0.5d ? 1.0f : 0.0f) * f2) + fFloor;
        }
        float f4 = fG;
        HashMap<String, b.e.a.a.d> map = this.C;
        if (map != null) {
            Iterator<b.e.a.a.d> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().h(view, f4);
            }
        }
        HashMap<String, b.e.a.a.f> map2 = this.B;
        if (map2 != null) {
            f.d dVar3 = null;
            boolean zI = false;
            for (b.e.a.a.f fVar : map2.values()) {
                if (fVar instanceof f.d) {
                    dVar3 = (f.d) fVar;
                } else {
                    zI |= fVar.i(view, f4, j, dVar);
                }
            }
            zJ = zI;
            dVar2 = dVar3;
        } else {
            dVar2 = null;
            zJ = false;
        }
        androidx.constraintlayout.core.j.a.b[] bVarArr = this.k;
        if (bVarArr != null) {
            double d3 = f4;
            bVarArr[0].d(d3, this.s);
            this.k[0].g(d3, this.t);
            androidx.constraintlayout.core.j.a.b bVar = this.l;
            if (bVar != null) {
                double[] dArr = this.s;
                if (dArr.length > 0) {
                    bVar.d(d3, dArr);
                    this.l.g(d3, this.t);
                }
            }
            if (this.L) {
                d2 = d3;
            } else {
                d2 = d3;
                this.g.u(f4, view, this.r, this.s, this.t, null, this.f944d);
                this.f944d = false;
            }
            if (this.G != e.f925a) {
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
            HashMap<String, b.e.a.a.d> map3 = this.C;
            if (map3 != null) {
                for (b.e.a.a.d dVar4 : map3.values()) {
                    if (dVar4 instanceof d.C0045d) {
                        double[] dArr2 = this.t;
                        if (dArr2.length > 1) {
                            ((d.C0045d) dVar4).i(view, f4, dArr2[0], dArr2[1]);
                        }
                    }
                }
            }
            if (dVar2 != null) {
                double[] dArr3 = this.t;
                i = 1;
                zJ |= dVar2.j(view, dVar, f4, j, dArr3[0], dArr3[1]);
            } else {
                i = 1;
            }
            int i3 = i;
            while (true) {
                androidx.constraintlayout.core.j.a.b[] bVarArr2 = this.k;
                if (i3 >= bVarArr2.length) {
                    break;
                }
                bVarArr2[i3].e(d2, this.x);
                b.e.a.a.a.b(this.g.q.get(this.u[i3 - 1]), view, this.x);
                i3++;
            }
            m mVar = this.i;
            if (mVar.f939d == 0) {
                if (f4 <= 0.0f) {
                    view.setVisibility(mVar.f940e);
                } else if (f4 >= 1.0f) {
                    view.setVisibility(this.j.f940e);
                } else if (this.j.f940e != mVar.f940e) {
                    view.setVisibility(0);
                }
            }
            if (this.E != null) {
                int i4 = 0;
                while (true) {
                    l[] lVarArr = this.E;
                    if (i4 >= lVarArr.length) {
                        break;
                    }
                    lVarArr[i4].y(f4, view);
                    i4++;
                }
            }
        } else {
            i = 1;
            p pVar = this.g;
            float f5 = pVar.g;
            p pVar2 = this.h;
            float f6 = f5 + ((pVar2.g - f5) * f4);
            float f7 = pVar.h;
            float f8 = f7 + ((pVar2.h - f7) * f4);
            float f9 = pVar.i;
            float f10 = pVar2.i;
            float f11 = pVar.j;
            float f12 = pVar2.j;
            float f13 = f6 + 0.5f;
            int i5 = (int) f13;
            float f14 = f8 + 0.5f;
            int i6 = (int) f14;
            int i7 = (int) (f13 + ((f10 - f9) * f4) + f9);
            int i8 = (int) (f14 + ((f12 - f11) * f4) + f11);
            int i9 = i7 - i5;
            int i10 = i8 - i6;
            if (f10 != f9 || f12 != f11 || this.f944d) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i9, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
                this.f944d = false;
            }
            view.layout(i5, i6, i7, i8);
        }
        HashMap<String, b.e.a.a.c> map4 = this.D;
        if (map4 != null) {
            for (b.e.a.a.c cVar : map4.values()) {
                if (cVar instanceof c.d) {
                    double[] dArr4 = this.t;
                    ((c.d) cVar).k(view, f4, dArr4[0], dArr4[i]);
                } else {
                    cVar.j(view, f4);
                }
            }
        }
        return zJ;
    }

    public void z() {
        this.f944d = true;
    }
}
