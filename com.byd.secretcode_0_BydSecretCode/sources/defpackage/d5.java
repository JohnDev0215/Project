package defpackage;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bydux.api.common.tools.util.ListUtils;
import defpackage.h5;
import defpackage.i5;
import defpackage.q6;
import defpackage.w4;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: MotionController.java */
/* JADX INFO: loaded from: classes.dex */
public class d5 {
    public b5[] A;
    public View a;
    public int b;
    public String c;
    public j4[] i;
    public j4 j;
    public int[] n;
    public double[] o;
    public double[] p;
    public String[] q;
    public int[] r;
    public HashMap<String, i5> x;
    public HashMap<String, h5> y;
    public HashMap<String, w4> z;
    public int d = -1;
    public f5 e = new f5();
    public f5 f = new f5();
    public c5 g = new c5();
    public c5 h = new c5();
    public float k = Float.NaN;
    public float l = 0.0f;
    public float m = 1.0f;
    public int s = 4;
    public float[] t = new float[4];
    public ArrayList<f5> u = new ArrayList<>();
    public float[] v = new float[1];
    public ArrayList<s4> w = new ArrayList<>();
    public int B = s4.a;

    public d5(View view) {
        u(view);
    }

    public void a(s4 s4Var) {
        this.w.add(s4Var);
    }

    public void b(ArrayList<s4> arrayList) {
        this.w.addAll(arrayList);
    }

    public int c(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] dArrH = this.i[0].h();
        if (iArr != null) {
            Iterator<f5> it = this.u.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().o;
                i++;
            }
        }
        int i2 = 0;
        for (double d : dArrH) {
            this.i[0].d(d, this.o);
            this.e.f(this.n, this.o, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    public void d(float[] fArr, int i) {
        int i2 = i;
        float f = 1.0f;
        float f2 = 1.0f / (i2 - 1);
        HashMap<String, h5> map = this.y;
        h5 h5Var = map == null ? null : map.get("translationX");
        HashMap<String, h5> map2 = this.y;
        h5 h5Var2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, w4> map3 = this.z;
        w4 w4Var = map3 == null ? null : map3.get("translationX");
        HashMap<String, w4> map4 = this.z;
        w4 w4Var2 = map4 != null ? map4.get("translationY") : null;
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
            k4 k4Var = this.e.c;
            float f6 = Float.NaN;
            float f7 = 0.0f;
            for (f5 f5Var : this.u) {
                k4 k4Var2 = f5Var.c;
                if (k4Var2 != null) {
                    float f8 = f5Var.e;
                    if (f8 < f3) {
                        f7 = f8;
                        k4Var = k4Var2;
                    } else if (Float.isNaN(f6)) {
                        f6 = f5Var.e;
                    }
                }
            }
            if (k4Var != null) {
                if (Float.isNaN(f6)) {
                    f6 = 1.0f;
                }
                dA = (((float) k4Var.a((f3 - f7) / r15)) * (f6 - f7)) + f7;
            }
            this.i[0].d(dA, this.o);
            j4 j4Var = this.j;
            if (j4Var != null) {
                double[] dArr = this.o;
                if (dArr.length > 0) {
                    j4Var.d(dA, dArr);
                }
            }
            int i4 = i3 * 2;
            this.e.f(this.n, this.o, fArr, i4);
            if (w4Var != null) {
                fArr[i4] = fArr[i4] + w4Var.a(f3);
            } else if (h5Var != null) {
                fArr[i4] = fArr[i4] + h5Var.a(f3);
            }
            if (w4Var2 != null) {
                int i5 = i4 + 1;
                fArr[i5] = fArr[i5] + w4Var2.a(f3);
            } else if (h5Var2 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + h5Var2.a(f3);
            }
            i3++;
            i2 = i;
            f = 1.0f;
        }
    }

    public void e(float f, float[] fArr, int i) {
        this.i[0].d(f(f, null), this.o);
        this.e.i(this.n, this.o, fArr, i);
    }

    public final float f(float f, float[] fArr) {
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
        k4 k4Var = this.e.c;
        float f5 = Float.NaN;
        for (f5 f5Var : this.u) {
            k4 k4Var2 = f5Var.c;
            if (k4Var2 != null) {
                float f6 = f5Var.e;
                if (f6 < f) {
                    k4Var = k4Var2;
                    f2 = f6;
                } else if (Float.isNaN(f5)) {
                    f5 = f5Var.e;
                }
            }
        }
        if (k4Var != null) {
            float f7 = (Float.isNaN(f5) ? 1.0f : f5) - f2;
            double d = (f - f2) / f7;
            f = (((float) k4Var.a(d)) * f7) + f2;
            if (fArr != null) {
                fArr[0] = (float) k4Var.b(d);
            }
        }
        return f;
    }

    public void g(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float f4 = f(f, this.v);
        j4[] j4VarArr = this.i;
        int i = 0;
        if (j4VarArr == null) {
            f5 f5Var = this.f;
            float f5 = f5Var.g;
            f5 f5Var2 = this.e;
            float f6 = f5 - f5Var2.g;
            float f7 = f5Var.h - f5Var2.h;
            float f8 = (f5Var.i - f5Var2.i) + f6;
            float f9 = (f5Var.j - f5Var2.j) + f7;
            fArr[0] = (f6 * (1.0f - f2)) + (f8 * f2);
            fArr[1] = (f7 * (1.0f - f3)) + (f9 * f3);
            return;
        }
        double d = f4;
        j4VarArr[0].g(d, this.p);
        this.i[0].d(d, this.o);
        float f10 = this.v[0];
        while (true) {
            dArr = this.p;
            if (i >= dArr.length) {
                break;
            }
            dArr[i] = dArr[i] * ((double) f10);
            i++;
        }
        j4 j4Var = this.j;
        if (j4Var == null) {
            this.e.o(f2, f3, fArr, this.n, dArr, this.o);
            return;
        }
        double[] dArr2 = this.o;
        if (dArr2.length > 0) {
            j4Var.d(d, dArr2);
            this.j.g(d, this.p);
            this.e.o(f2, f3, fArr, this.n, this.p, this.o);
        }
    }

    public int h() {
        int iMax = this.e.d;
        Iterator<f5> it = this.u.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().d);
        }
        return Math.max(iMax, this.f.d);
    }

    public float i() {
        return this.f.g;
    }

    public float j() {
        return this.f.h;
    }

    public f5 k(int i) {
        return this.u.get(i);
    }

    public void l(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float f4 = f(f, this.v);
        HashMap<String, h5> map = this.y;
        h5 h5Var = map == null ? null : map.get("translationX");
        HashMap<String, h5> map2 = this.y;
        h5 h5Var2 = map2 == null ? null : map2.get("translationY");
        HashMap<String, h5> map3 = this.y;
        h5 h5Var3 = map3 == null ? null : map3.get("rotation");
        HashMap<String, h5> map4 = this.y;
        h5 h5Var4 = map4 == null ? null : map4.get("scaleX");
        HashMap<String, h5> map5 = this.y;
        h5 h5Var5 = map5 == null ? null : map5.get("scaleY");
        HashMap<String, w4> map6 = this.z;
        w4 w4Var = map6 == null ? null : map6.get("translationX");
        HashMap<String, w4> map7 = this.z;
        w4 w4Var2 = map7 == null ? null : map7.get("translationY");
        HashMap<String, w4> map8 = this.z;
        w4 w4Var3 = map8 == null ? null : map8.get("rotation");
        HashMap<String, w4> map9 = this.z;
        w4 w4Var4 = map9 == null ? null : map9.get("scaleX");
        HashMap<String, w4> map10 = this.z;
        w4 w4Var5 = map10 != null ? map10.get("scaleY") : null;
        p4 p4Var = new p4();
        p4Var.b();
        p4Var.d(h5Var3, f4);
        p4Var.h(h5Var, h5Var2, f4);
        p4Var.f(h5Var4, h5Var5, f4);
        p4Var.c(w4Var3, f4);
        p4Var.g(w4Var, w4Var2, f4);
        p4Var.e(w4Var4, w4Var5, f4);
        j4 j4Var = this.j;
        if (j4Var != null) {
            double[] dArr = this.o;
            if (dArr.length > 0) {
                double d = f4;
                j4Var.d(d, dArr);
                this.j.g(d, this.p);
                this.e.o(f2, f3, fArr, this.n, this.p, this.o);
            }
            p4Var.a(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.i == null) {
            f5 f5Var = this.f;
            float f5 = f5Var.g;
            f5 f5Var2 = this.e;
            float f6 = f5 - f5Var2.g;
            w4 w4Var6 = w4Var5;
            float f7 = f5Var.h - f5Var2.h;
            w4 w4Var7 = w4Var4;
            float f8 = (f5Var.i - f5Var2.i) + f6;
            float f9 = (f5Var.j - f5Var2.j) + f7;
            fArr[0] = (f6 * (1.0f - f2)) + (f8 * f2);
            fArr[1] = (f7 * (1.0f - f3)) + (f9 * f3);
            p4Var.b();
            p4Var.d(h5Var3, f4);
            p4Var.h(h5Var, h5Var2, f4);
            p4Var.f(h5Var4, h5Var5, f4);
            p4Var.c(w4Var3, f4);
            p4Var.g(w4Var, w4Var2, f4);
            p4Var.e(w4Var7, w4Var6, f4);
            p4Var.a(f2, f3, i, i2, fArr);
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
                p4Var.a(f2, f3, i, i2, fArr);
                return;
            } else {
                dArr2[i3] = dArr2[i3] * ((double) f10);
                i3++;
            }
        }
    }

    public final float m() {
        float[] fArr = new float[2];
        float f = 1.0f / 99;
        double d = 0.0d;
        double d2 = 0.0d;
        int i = 0;
        float fHypot = 0.0f;
        while (i < 100) {
            float f2 = i * f;
            double dA = f2;
            k4 k4Var = this.e.c;
            float f3 = Float.NaN;
            float f4 = 0.0f;
            for (f5 f5Var : this.u) {
                k4 k4Var2 = f5Var.c;
                float f5 = f;
                if (k4Var2 != null) {
                    float f6 = f5Var.e;
                    if (f6 < f2) {
                        f4 = f6;
                        k4Var = k4Var2;
                    } else if (Float.isNaN(f3)) {
                        f3 = f5Var.e;
                    }
                }
                f = f5;
            }
            float f7 = f;
            if (k4Var != null) {
                if (Float.isNaN(f3)) {
                    f3 = 1.0f;
                }
                dA = (((float) k4Var.a((f2 - f4) / r16)) * (f3 - f4)) + f4;
            }
            this.i[0].d(dA, this.o);
            this.e.f(this.n, this.o, fArr, 0);
            if (i > 0) {
                fHypot = (float) (((double) fHypot) + Math.hypot(d2 - ((double) fArr[1]), d - ((double) fArr[0])));
            }
            d = fArr[0];
            d2 = fArr[1];
            i++;
            f = f7;
        }
        return fHypot;
    }

    public final void n(f5 f5Var) {
        if (Collections.binarySearch(this.u, f5Var) == 0) {
            Log.e("MotionController", " KeyPath positon \"" + f5Var.f + "\" outside of range");
        }
        this.u.add((-r0) - 1, f5Var);
    }

    public boolean o(View view, float f, long j, u4 u4Var) {
        i5.d dVar;
        boolean zJ;
        double d;
        float f2 = f(f, null);
        HashMap<String, h5> map = this.y;
        if (map != null) {
            Iterator<h5> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().f(view, f2);
            }
        }
        HashMap<String, i5> map2 = this.x;
        if (map2 != null) {
            dVar = null;
            boolean zF = false;
            for (i5 i5Var : map2.values()) {
                if (i5Var instanceof i5.d) {
                    dVar = (i5.d) i5Var;
                } else {
                    zF |= i5Var.f(view, f2, j, u4Var);
                }
            }
            zJ = zF;
        } else {
            dVar = null;
            zJ = false;
        }
        j4[] j4VarArr = this.i;
        if (j4VarArr != null) {
            double d2 = f2;
            j4VarArr[0].d(d2, this.o);
            this.i[0].g(d2, this.p);
            j4 j4Var = this.j;
            if (j4Var != null) {
                double[] dArr = this.o;
                if (dArr.length > 0) {
                    j4Var.d(d2, dArr);
                    this.j.g(d2, this.p);
                }
            }
            this.e.p(view, this.n, this.o, this.p, null);
            HashMap<String, h5> map3 = this.y;
            if (map3 != null) {
                for (h5 h5Var : map3.values()) {
                    if (h5Var instanceof h5.d) {
                        double[] dArr2 = this.p;
                        ((h5.d) h5Var).i(view, f2, dArr2[0], dArr2[1]);
                    }
                }
            }
            if (dVar != null) {
                double[] dArr3 = this.p;
                d = d2;
                zJ = dVar.j(view, u4Var, f2, j, dArr3[0], dArr3[1]) | zJ;
            } else {
                d = d2;
            }
            int i = 1;
            while (true) {
                j4[] j4VarArr2 = this.i;
                if (i >= j4VarArr2.length) {
                    break;
                }
                j4VarArr2[i].e(d, this.t);
                this.e.n.get(this.q[i - 1]).i(view, this.t);
                i++;
            }
            c5 c5Var = this.g;
            if (c5Var.d == 0) {
                if (f2 <= 0.0f) {
                    view.setVisibility(c5Var.e);
                } else if (f2 >= 1.0f) {
                    view.setVisibility(this.h.e);
                } else if (this.h.e != c5Var.e) {
                    view.setVisibility(0);
                }
            }
            if (this.A != null) {
                int i2 = 0;
                while (true) {
                    b5[] b5VarArr = this.A;
                    if (i2 >= b5VarArr.length) {
                        break;
                    }
                    b5VarArr[i2].r(f2, view);
                    i2++;
                }
            }
        } else {
            f5 f5Var = this.e;
            float f3 = f5Var.g;
            f5 f5Var2 = this.f;
            float f4 = f3 + ((f5Var2.g - f3) * f2);
            float f5 = f5Var.h;
            float f6 = f5 + ((f5Var2.h - f5) * f2);
            float f7 = f5Var.i;
            float f8 = f5Var2.i;
            float f9 = f5Var.j;
            float f10 = f5Var2.j;
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
        HashMap<String, w4> map4 = this.z;
        if (map4 != null) {
            for (w4 w4Var : map4.values()) {
                if (w4Var instanceof w4.f) {
                    double[] dArr4 = this.p;
                    ((w4.f) w4Var).j(view, f2, dArr4[0], dArr4[1]);
                } else {
                    w4Var.f(view, f2);
                }
            }
        }
        return zJ;
    }

    public final void p(f5 f5Var) {
        f5Var.n((int) this.a.getX(), (int) this.a.getY(), this.a.getWidth(), this.a.getHeight());
    }

    public void q(ConstraintWidget constraintWidget, q6 q6Var) {
        f5 f5Var = this.f;
        f5Var.e = 1.0f;
        f5Var.f = 1.0f;
        p(f5Var);
        this.f.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        this.f.a(q6Var.q(this.b));
        this.h.i(constraintWidget, q6Var, this.b);
    }

    public void r(int i) {
        this.B = i;
    }

    public void s(View view) {
        f5 f5Var = this.e;
        f5Var.e = 0.0f;
        f5Var.f = 0.0f;
        f5Var.n(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.g.h(view);
    }

    public void t(ConstraintWidget constraintWidget, q6 q6Var) {
        f5 f5Var = this.e;
        f5Var.e = 0.0f;
        f5Var.f = 0.0f;
        p(f5Var);
        this.e.n(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        q6.a aVarQ = q6Var.q(this.b);
        this.e.a(aVarQ);
        this.k = aVarQ.c.g;
        this.g.i(constraintWidget, q6Var, this.b);
    }

    public String toString() {
        return " start: x: " + this.e.g + " y: " + this.e.h + " end: x: " + this.f.g + " y: " + this.f.h;
    }

    public void u(View view) {
        this.a = view;
        this.b = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.b) {
            this.c = ((ConstraintLayout.b) layoutParams).a();
        }
    }

    public void v(int i, int i2, float f, long j) {
        ArrayList arrayList;
        String[] strArr;
        i5 i5VarD;
        ConstraintAttribute constraintAttribute;
        h5 h5VarD;
        ConstraintAttribute constraintAttribute2;
        new HashSet();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        int i3 = this.B;
        if (i3 != s4.a) {
            this.e.m = i3;
        }
        this.g.f(this.h, hashSet2);
        ArrayList<s4> arrayList2 = this.w;
        if (arrayList2 != null) {
            arrayList = null;
            for (s4 s4Var : arrayList2) {
                if (s4Var instanceof y4) {
                    y4 y4Var = (y4) s4Var;
                    n(new f5(i, i2, y4Var, this.e, this.f));
                    int i4 = y4Var.g;
                    if (i4 != s4.a) {
                        this.d = i4;
                    }
                } else if (s4Var instanceof v4) {
                    s4Var.b(hashSet3);
                } else if (s4Var instanceof a5) {
                    s4Var.b(hashSet);
                } else if (s4Var instanceof b5) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((b5) s4Var);
                } else {
                    s4Var.e(map);
                    s4Var.b(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        int i5 = 0;
        if (arrayList != null) {
            this.A = (b5[]) arrayList.toArray(new b5[0]);
        }
        char c = 1;
        if (!hashSet2.isEmpty()) {
            this.y = new HashMap<>();
            for (String str : hashSet2) {
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[c];
                    for (s4 s4Var2 : this.w) {
                        HashMap<String, ConstraintAttribute> map2 = s4Var2.f;
                        if (map2 != null && (constraintAttribute2 = map2.get(str2)) != null) {
                            sparseArray.append(s4Var2.b, constraintAttribute2);
                        }
                    }
                    h5VarD = h5.c(str, sparseArray);
                } else {
                    h5VarD = h5.d(str);
                }
                if (h5VarD != null) {
                    h5VarD.g(str);
                    this.y.put(str, h5VarD);
                }
                c = 1;
            }
            ArrayList<s4> arrayList3 = this.w;
            if (arrayList3 != null) {
                for (s4 s4Var3 : arrayList3) {
                    if (s4Var3 instanceof t4) {
                        s4Var3.a(this.y);
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
                        for (s4 s4Var4 : this.w) {
                            HashMap<String, ConstraintAttribute> map3 = s4Var4.f;
                            if (map3 != null && (constraintAttribute = map3.get(str5)) != null) {
                                sparseArray2.append(s4Var4.b, constraintAttribute);
                            }
                        }
                        i5VarD = i5.c(str4, sparseArray2);
                    } else {
                        i5VarD = i5.d(str4, j);
                    }
                    if (i5VarD != null) {
                        i5VarD.h(str4);
                        this.x.put(str4, i5VarD);
                    }
                }
            }
            ArrayList<s4> arrayList4 = this.w;
            if (arrayList4 != null) {
                for (s4 s4Var5 : arrayList4) {
                    if (s4Var5 instanceof a5) {
                        ((a5) s4Var5).M(this.x);
                    }
                }
            }
            for (String str6 : this.x.keySet()) {
                this.x.get(str6).i(map.containsKey(str6) ? map.get(str6).intValue() : 0);
            }
        }
        int i6 = 2;
        int size = this.u.size() + 2;
        f5[] f5VarArr = new f5[size];
        f5VarArr[0] = this.e;
        f5VarArr[size - 1] = this.f;
        if (this.u.size() > 0 && this.d == -1) {
            this.d = 0;
        }
        Iterator<f5> it = this.u.iterator();
        int i7 = 1;
        while (it.hasNext()) {
            f5VarArr[i7] = it.next();
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
                if (f5VarArr[i9].n.containsKey(str8)) {
                    int[] iArr = this.r;
                    iArr[i8] = iArr[i8] + f5VarArr[i9].n.get(str8).f();
                    break;
                }
                i9++;
            }
            i8++;
        }
        boolean z = f5VarArr[0].m != s4.a;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i10 = 1; i10 < size; i10++) {
            f5VarArr[i10].d(f5VarArr[i10 - 1], zArr, this.q, z);
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
            f5VarArr[i15].e(dArr[i15], this.n);
            dArr2[i15] = f5VarArr[i15].e;
        }
        int i16 = 0;
        while (true) {
            int[] iArr3 = this.n;
            if (i16 >= iArr3.length) {
                break;
            }
            if (iArr3[i16] < f5.b.length) {
                String str9 = f5.b[this.n[i16]] + " [";
                for (int i17 = 0; i17 < size; i17++) {
                    str9 = str9 + dArr[i17][i16];
                }
            }
            i16++;
        }
        this.i = new j4[this.q.length + 1];
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
                if (f5VarArr[i19].j(str10)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr4 = new int[i6];
                        iArr4[1] = f5VarArr[i19].h(str10);
                        iArr4[i5] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr4);
                    }
                    dArr3[i20] = f5VarArr[i19].e;
                    f5VarArr[i19].g(str10, dArr4[i20], 0);
                    i20++;
                }
                i19++;
                i6 = 2;
                i5 = 0;
            }
            i18++;
            this.i[i18] = j4.a(this.d, Arrays.copyOf(dArr3, i20), (double[][]) Arrays.copyOf(dArr4, i20));
            i6 = 2;
            i5 = 0;
        }
        this.i[0] = j4.a(this.d, dArr2, dArr);
        if (f5VarArr[0].m != s4.a) {
            int[] iArr5 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i21 = 0; i21 < size; i21++) {
                iArr5[i21] = f5VarArr[i21].m;
                dArr5[i21] = f5VarArr[i21].e;
                dArr6[i21][0] = f5VarArr[i21].g;
                dArr6[i21][1] = f5VarArr[i21].h;
            }
            this.j = j4.b(iArr5, dArr5, dArr6);
        }
        float fM = Float.NaN;
        this.z = new HashMap<>();
        if (this.w != null) {
            for (String str11 : hashSet3) {
                w4 w4VarC = w4.c(str11);
                if (w4VarC != null) {
                    if (w4VarC.i() && Float.isNaN(fM)) {
                        fM = m();
                    }
                    w4VarC.g(str11);
                    this.z.put(str11, w4VarC);
                }
            }
            for (s4 s4Var6 : this.w) {
                if (s4Var6 instanceof v4) {
                    ((v4) s4Var6).O(this.z);
                }
            }
            Iterator<w4> it2 = this.z.values().iterator();
            while (it2.hasNext()) {
                it2.next().h(fM);
            }
        }
    }
}
