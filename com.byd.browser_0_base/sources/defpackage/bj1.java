package defpackage;

import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.constraintlayout.widget.ConstraintLayout;
import defpackage.d41;
import defpackage.dn3;
import defpackage.rn3;
import defpackage.sn3;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: MotionController.java */
/* JADX INFO: loaded from: classes.dex */
public final class bj1 {
    public k41[] A;
    public View b;
    public int c;
    public l20[] j;
    public d8 k;
    public int[] o;
    public double[] p;
    public double[] q;
    public String[] r;
    public int[] s;
    public HashMap<String, sn3> x;
    public HashMap<String, rn3> y;
    public HashMap<String, dn3> z;
    public final Rect a = new Rect();
    public boolean d = false;
    public int e = -1;
    public final ej1 f = new ej1();
    public final ej1 g = new ej1();
    public final zi1 h = new zi1();
    public final zi1 i = new zi1();
    public float l = Float.NaN;
    public float m = 0.0f;
    public float n = 1.0f;
    public final float[] t = new float[4];
    public final ArrayList<ej1> u = new ArrayList<>();
    public final float[] v = new float[1];
    public final ArrayList<y31> w = new ArrayList<>();
    public int B = -1;
    public int C = -1;
    public View D = null;
    public int E = -1;
    public float F = Float.NaN;
    public Interpolator G = null;
    public boolean H = false;

    public bj1(View view) {
        this.b = view;
        this.c = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).getClass();
        }
    }

    public static void g(int i, int i2, int i3, Rect rect, Rect rect2) {
        if (i == 1) {
            int i4 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i3 - ((rect.height() + i4) / 2);
            rect2.right = rect.width() + rect2.left;
            rect2.bottom = rect.height() + rect2.top;
            return;
        }
        if (i == 2) {
            int i5 = rect.left + rect.right;
            rect2.left = i2 - ((rect.width() + (rect.top + rect.bottom)) / 2);
            rect2.top = (i5 - rect.height()) / 2;
            rect2.right = rect.width() + rect2.left;
            rect2.bottom = rect.height() + rect2.top;
            return;
        }
        if (i == 3) {
            int i6 = rect.left + rect.right;
            rect2.left = ((rect.height() / 2) + rect.top) - (i6 / 2);
            rect2.top = i3 - ((rect.height() + i6) / 2);
            rect2.right = rect.width() + rect2.left;
            rect2.bottom = rect.height() + rect2.top;
            return;
        }
        if (i != 4) {
            return;
        }
        int i7 = rect.left + rect.right;
        rect2.left = i2 - ((rect.width() + (rect.bottom + rect.top)) / 2);
        rect2.top = (i7 - rect.height()) / 2;
        rect2.right = rect.width() + rect2.left;
        rect2.bottom = rect.height() + rect2.top;
    }

    public final void a(y31 y31Var) {
        this.w.add(y31Var);
    }

    public final float b(float f, float[] fArr) {
        float f2 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f3 = this.n;
            if (f3 != 1.0d) {
                float f4 = this.m;
                if (f < f4) {
                    f = 0.0f;
                }
                if (f > f4 && f < 1.0d) {
                    f = Math.min((f - f4) * f3, 1.0f);
                }
            }
        }
        hf0 hf0Var = this.f.e;
        float f5 = Float.NaN;
        for (ej1 ej1Var : this.u) {
            hf0 hf0Var2 = ej1Var.e;
            if (hf0Var2 != null) {
                float f6 = ej1Var.g;
                if (f6 < f) {
                    hf0Var = hf0Var2;
                    f2 = f6;
                } else if (Float.isNaN(f5)) {
                    f5 = ej1Var.g;
                }
            }
        }
        if (hf0Var == null) {
            return f;
        }
        float f7 = (Float.isNaN(f5) ? 1.0f : f5) - f2;
        double d = (f - f2) / f7;
        float fA = f2 + (((float) hf0Var.a(d)) * f7);
        if (fArr != null) {
            fArr[0] = (float) hf0Var.b(d);
        }
        return fA;
    }

    public final void c(double d, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.j[0].c(d, dArr);
        this.j[0].f(d, dArr2);
        float f = 0.0f;
        Arrays.fill(fArr2, 0.0f);
        int[] iArr = this.o;
        ej1 ej1Var = this.f;
        float f2 = ej1Var.i;
        float fCos = ej1Var.j;
        float f3 = ej1Var.k;
        float f4 = ej1Var.l;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (int i = 0; i < iArr.length; i++) {
            float f8 = (float) dArr[i];
            float f9 = (float) dArr2[i];
            int i2 = iArr[i];
            if (i2 == 1) {
                f2 = f8;
                f = f9;
            } else if (i2 == 2) {
                fCos = f8;
                f7 = f9;
            } else if (i2 == 3) {
                f3 = f8;
                f5 = f9;
            } else if (i2 == 4) {
                f4 = f8;
                f6 = f9;
            }
        }
        float fCos2 = (f5 / 2.0f) + f;
        float fSin = (f6 / 2.0f) + f7;
        bj1 bj1Var = ej1Var.q;
        if (bj1Var != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            bj1Var.c(d, fArr3, fArr4);
            float f10 = fArr3[0];
            float f11 = fArr3[1];
            float f12 = fArr4[0];
            float f13 = fArr4[1];
            double d2 = f2;
            double d3 = fCos;
            float fSin2 = (float) (((Math.sin(d3) * d2) + ((double) f10)) - ((double) (f3 / 2.0f)));
            fCos = (float) ((((double) f11) - (Math.cos(d3) * d2)) - ((double) (f4 / 2.0f)));
            double d4 = f12;
            double d5 = f;
            double d6 = f7;
            fCos2 = (float) ((Math.cos(d3) * d6) + (Math.sin(d3) * d5) + d4);
            f2 = fSin2;
            fSin = (float) ((Math.sin(d3) * d6) + (((double) f13) - (Math.cos(d3) * d5)));
        }
        fArr[0] = (f3 / 2.0f) + f2 + 0.0f;
        fArr[1] = (f4 / 2.0f) + fCos + 0.0f;
        fArr2[0] = fCos2;
        fArr2[1] = fSin;
    }

    public final void d(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float[] fArr2 = this.v;
        float fB = b(f, fArr2);
        l20[] l20VarArr = this.j;
        ej1 ej1Var = this.f;
        int i = 0;
        if (l20VarArr == null) {
            ej1 ej1Var2 = this.g;
            float f4 = ej1Var2.i - ej1Var.i;
            float f5 = ej1Var2.j - ej1Var.j;
            float f6 = ej1Var2.k - ej1Var.k;
            float f7 = (ej1Var2.l - ej1Var.l) + f5;
            fArr[0] = ((f6 + f4) * f2) + ((1.0f - f2) * f4);
            fArr[1] = (f7 * f3) + ((1.0f - f3) * f5);
            return;
        }
        double d = fB;
        l20VarArr[0].f(d, this.q);
        this.j[0].c(d, this.p);
        float f8 = fArr2[0];
        while (true) {
            dArr = this.q;
            if (i >= dArr.length) {
                break;
            }
            dArr[i] = dArr[i] * ((double) f8);
            i++;
        }
        d8 d8Var = this.k;
        if (d8Var == null) {
            int[] iArr = this.o;
            double[] dArr2 = this.p;
            ej1Var.getClass();
            ej1.h(f2, f3, fArr, iArr, dArr, dArr2);
            return;
        }
        double[] dArr3 = this.p;
        if (dArr3.length > 0) {
            d8Var.c(d, dArr3);
            this.k.f(d, this.q);
            int[] iArr2 = this.o;
            double[] dArr4 = this.q;
            double[] dArr5 = this.p;
            ej1Var.getClass();
            ej1.h(f2, f3, fArr, iArr2, dArr4, dArr5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0322  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e(float r29, long r30, android.view.View r32, defpackage.a41 r33) {
        /*
            Method dump skipped, instruction units count: 1098
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bj1.e(float, long, android.view.View, a41):boolean");
    }

    public final void f(ej1 ej1Var) {
        ej1Var.g((int) this.b.getX(), (int) this.b.getY(), this.b.getWidth(), this.b.getHeight());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void h(int i, int i2, long j) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        ArrayList arrayList;
        HashSet<String> hashSet;
        HashSet<String> hashSet2;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        Object obj;
        Object obj2;
        Object obj3;
        String str11;
        ArrayList<ej1> arrayList2;
        ArrayList<y31> arrayList3;
        String str12;
        String str13;
        String str14;
        Object obj4;
        Object obj5;
        Object obj6;
        bj1 bj1Var;
        String str15;
        Iterator<y31> it;
        HashMap<String, dn3> map;
        String str16;
        Iterator<String> it2;
        dn3 dn3Var;
        String str17;
        String str18;
        Object obj7;
        String str19;
        Object obj8;
        String str20;
        Object obj9;
        String str21;
        String str22;
        byte b;
        byte b2;
        byte b3;
        byte b4;
        float f;
        float f2;
        String str23;
        String str24;
        HashMap<String, dn3> map2;
        Iterator<String> it3;
        Object obj10;
        Object obj11;
        String str25;
        String str26;
        String str27;
        String str28;
        String str29;
        String str30;
        String str31;
        String str32;
        String str33;
        Object obj12;
        byte b5;
        byte b6;
        byte b7;
        dn3 gVar;
        dn3 bVar;
        String str34;
        String str35;
        String str36;
        String str37;
        ej1 ej1Var;
        String str38;
        String str39;
        double dA;
        String str40;
        String str41;
        double[] dArr;
        double[][] dArr2;
        ox oxVar;
        HashSet<String> hashSet3;
        ArrayList<y31> arrayList4;
        HashMap<String, sn3> map3;
        String str42;
        Iterator<String> it4;
        String str43;
        Object obj13;
        j41 j41Var;
        Object obj14;
        Object obj15;
        Object obj16;
        byte b8;
        byte b9;
        byte b10;
        Iterator<String> it5;
        HashMap<String, Integer> map4;
        String str44;
        String str45;
        Object obj17;
        Object obj18;
        Object obj19;
        byte b11;
        byte b12;
        sn3 gVar2;
        Object obj20;
        sn3 bVar2;
        String str46;
        ox oxVar2;
        Integer num;
        HashSet<String> hashSet4;
        HashSet<String> hashSet5;
        Iterator<String> it6;
        String str47;
        String str48;
        String str49;
        String str50;
        String str51;
        Object obj21;
        String str52;
        ArrayList<ej1> arrayList5;
        Object obj22;
        Object obj23;
        byte b13;
        byte b14;
        rn3 iVar;
        Object obj24;
        rn3 rn3Var;
        String str53;
        ox oxVar3;
        String str54;
        String str55;
        String str56;
        bj1 bj1Var2 = this;
        new HashSet();
        HashSet<String> hashSet6 = new HashSet<>();
        HashSet<String> hashSet7 = new HashSet<>();
        HashSet<String> hashSet8 = new HashSet<>();
        HashMap<String, Integer> map5 = new HashMap<>();
        int i3 = bj1Var2.B;
        ej1 ej1Var2 = bj1Var2.f;
        if (i3 != -1) {
            ej1Var2.n = i3;
        }
        zi1 zi1Var = bj1Var2.h;
        float f3 = zi1Var.i;
        zi1 zi1Var2 = bj1Var2.i;
        String str57 = "alpha";
        if (zi1.f(f3, zi1Var2.i)) {
            hashSet7.add("alpha");
        }
        String str58 = "elevation";
        if (zi1.f(zi1Var.j, zi1Var2.j)) {
            hashSet7.add("elevation");
        }
        int i4 = zi1Var.g;
        int i5 = zi1Var2.g;
        if (i4 != i5 && zi1Var.f == 0 && (i4 == 0 || i5 == 0)) {
            hashSet7.add("alpha");
        }
        String str59 = "rotation";
        if (zi1.f(zi1Var.k, zi1Var2.k)) {
            hashSet7.add("rotation");
        }
        if (!Float.isNaN(zi1Var.t) || !Float.isNaN(zi1Var2.t)) {
            hashSet7.add("transitionPathRotate");
        }
        String str60 = "progress";
        if (!Float.isNaN(zi1Var.u) || !Float.isNaN(zi1Var2.u)) {
            hashSet7.add("progress");
        }
        if (zi1.f(zi1Var.l, zi1Var2.l)) {
            hashSet7.add("rotationX");
        }
        if (zi1.f(zi1Var.e, zi1Var2.e)) {
            hashSet7.add("rotationY");
        }
        ej1 ej1Var3 = ej1Var2;
        if (zi1.f(zi1Var.o, zi1Var2.o)) {
            hashSet7.add("transformPivotX");
        }
        if (zi1.f(zi1Var.p, zi1Var2.p)) {
            hashSet7.add("transformPivotY");
        }
        String str61 = "scaleX";
        if (zi1.f(zi1Var.m, zi1Var2.m)) {
            hashSet7.add("scaleX");
        }
        Object obj25 = "rotationX";
        String str62 = "scaleY";
        if (zi1.f(zi1Var.n, zi1Var2.n)) {
            hashSet7.add("scaleY");
        }
        Object obj26 = "rotationY";
        if (zi1.f(zi1Var.q, zi1Var2.q)) {
            hashSet7.add("translationX");
        }
        Object obj27 = "translationX";
        String str63 = "translationY";
        if (zi1.f(zi1Var.r, zi1Var2.r)) {
            hashSet7.add("translationY");
        }
        String str64 = "translationZ";
        if (zi1.f(zi1Var.s, zi1Var2.s)) {
            hashSet7.add("translationZ");
        }
        ArrayList<y31> arrayList6 = bj1Var2.w;
        ArrayList<ej1> arrayList7 = bj1Var2.u;
        if (arrayList6 != null) {
            ArrayList arrayList8 = null;
            for (y31 y31Var : arrayList6) {
                String str65 = str63;
                String str66 = str64;
                if (y31Var instanceof g41) {
                    g41 g41Var = (g41) y31Var;
                    str55 = str60;
                    str54 = str61;
                    ej1 ej1Var4 = new ej1(i, i2, g41Var, bj1Var2.f, bj1Var2.g);
                    if (Collections.binarySearch(arrayList7, ej1Var4) == 0) {
                        str56 = str62;
                        Log.e("MotionController", " KeyPath position \"" + ej1Var4.h + "\" outside of range");
                    } else {
                        str56 = str62;
                    }
                    arrayList7.add((-r7) - 1, ej1Var4);
                    int i6 = g41Var.e;
                    if (i6 != -1) {
                        bj1Var2.e = i6;
                    }
                } else {
                    str54 = str61;
                    str55 = str60;
                    str56 = str62;
                    if (y31Var instanceof b41) {
                        y31Var.d(hashSet8);
                    } else if (y31Var instanceof j41) {
                        y31Var.d(hashSet6);
                    } else if (y31Var instanceof k41) {
                        if (arrayList8 == null) {
                            arrayList8 = new ArrayList();
                        }
                        ArrayList arrayList9 = arrayList8;
                        arrayList9.add((k41) y31Var);
                        arrayList8 = arrayList9;
                    } else {
                        y31Var.f(map5);
                        y31Var.d(hashSet7);
                    }
                }
                str64 = str66;
                str63 = str65;
                str62 = str56;
                str60 = str55;
                str61 = str54;
            }
            str = str64;
            str2 = str61;
            str3 = str60;
            str4 = str62;
            str5 = str63;
            arrayList = arrayList8;
        } else {
            str = "translationZ";
            str2 = "scaleX";
            str3 = "progress";
            str4 = "scaleY";
            str5 = "translationY";
            arrayList = null;
        }
        if (arrayList != null) {
            bj1Var2.A = (k41[]) arrayList.toArray(new k41[0]);
        }
        String str67 = "waveOffset";
        String str68 = "CUSTOM,";
        if (hashSet7.isEmpty()) {
            hashSet = hashSet7;
            hashSet2 = hashSet8;
            str6 = str;
            str7 = str4;
            str8 = str3;
            str9 = str2;
            str10 = "waveOffset";
            obj = obj27;
            obj2 = obj26;
            obj3 = obj25;
            str11 = str5;
            arrayList2 = arrayList7;
        } else {
            bj1Var2.y = new HashMap<>();
            Iterator<String> it7 = hashSet7.iterator();
            while (it7.hasNext()) {
                String next = it7.next();
                if (next.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    it6 = it7;
                    String str69 = next.split(",")[1];
                    for (y31 y31Var2 : arrayList6) {
                        HashSet<String> hashSet9 = hashSet8;
                        HashSet<String> hashSet10 = hashSet7;
                        HashMap<String, ox> map6 = y31Var2.d;
                        if (map6 != null && (oxVar3 = map6.get(str69)) != null) {
                            sparseArray.append(y31Var2.a, oxVar3);
                        }
                        hashSet7 = hashSet10;
                        hashSet8 = hashSet9;
                    }
                    hashSet4 = hashSet7;
                    hashSet5 = hashSet8;
                    rn3.b bVar3 = new rn3.b(sparseArray, next);
                    str47 = str;
                    str49 = str3;
                    str50 = str2;
                    str51 = str67;
                    str52 = str5;
                    arrayList5 = arrayList7;
                    rn3Var = bVar3;
                    str48 = str4;
                    obj21 = obj27;
                    obj23 = obj26;
                    obj24 = obj25;
                } else {
                    hashSet4 = hashSet7;
                    hashSet5 = hashSet8;
                    it6 = it7;
                    switch (next.hashCode()) {
                        case -1249320806:
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            b13 = next.equals(obj22) ? (byte) 0 : (byte) -1;
                            break;
                        case -1249320805:
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            Object obj28 = obj26;
                            if (next.equals(obj28)) {
                                obj23 = obj28;
                                obj22 = obj25;
                                b13 = 1;
                            } else {
                                obj23 = obj28;
                                obj22 = obj25;
                            }
                            break;
                        case -1225497657:
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            Object obj29 = obj27;
                            if (next.equals(obj29)) {
                                obj21 = obj29;
                                obj22 = obj25;
                                b13 = 2;
                                obj23 = obj26;
                            } else {
                                obj21 = obj29;
                                obj22 = obj25;
                                obj23 = obj26;
                            }
                            break;
                        case -1225497656:
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            str52 = str5;
                            if (next.equals(str52)) {
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                obj21 = obj27;
                                b13 = 3;
                                obj23 = obj26;
                            } else {
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                obj21 = obj27;
                                obj23 = obj26;
                            }
                            break;
                        case -1225497655:
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            if (next.equals(str47)) {
                                str51 = str67;
                                obj21 = obj27;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                obj23 = obj26;
                                b13 = 4;
                            }
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                        case -1001078227:
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            obj21 = obj27;
                            if (next.equals(str49)) {
                                str47 = str;
                                str51 = str67;
                                obj23 = obj26;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = 5;
                            } else {
                                str47 = str;
                                str51 = str67;
                                obj23 = obj26;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                            }
                            break;
                        case -908189618:
                            str48 = str4;
                            str50 = str2;
                            if (next.equals(str50)) {
                                obj21 = obj27;
                                str47 = str;
                                str51 = str67;
                                obj23 = obj26;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = 6;
                                str49 = str3;
                            } else {
                                obj21 = obj27;
                                str47 = str;
                                str49 = str3;
                                str51 = str67;
                                obj23 = obj26;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                            }
                            break;
                        case -908189617:
                            str48 = str4;
                            if (next.equals(str48)) {
                                obj21 = obj27;
                                str47 = str;
                                str49 = str3;
                                str51 = str67;
                                obj23 = obj26;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = 7;
                                str50 = str2;
                            } else {
                                obj21 = obj27;
                                str47 = str;
                                str49 = str3;
                                str50 = str2;
                                str51 = str67;
                                obj23 = obj26;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                            }
                            break;
                        case -797520672:
                            if (next.equals("waveVariesBy")) {
                                b14 = 8;
                                str47 = str;
                                str49 = str3;
                                str50 = str2;
                                str51 = str67;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = b14;
                                str48 = str4;
                                obj21 = obj27;
                                obj23 = obj26;
                            }
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                        case -760884510:
                            if (next.equals("transformPivotX")) {
                                b14 = 9;
                                str47 = str;
                                str49 = str3;
                                str50 = str2;
                                str51 = str67;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = b14;
                                str48 = str4;
                                obj21 = obj27;
                                obj23 = obj26;
                            }
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                        case -760884509:
                            if (next.equals("transformPivotY")) {
                                b14 = 10;
                                str47 = str;
                                str49 = str3;
                                str50 = str2;
                                str51 = str67;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = b14;
                                str48 = str4;
                                obj21 = obj27;
                                obj23 = obj26;
                            }
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                        case -40300674:
                            if (next.equals("rotation")) {
                                b14 = 11;
                                str47 = str;
                                str49 = str3;
                                str50 = str2;
                                str51 = str67;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = b14;
                                str48 = str4;
                                obj21 = obj27;
                                obj23 = obj26;
                            }
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                        case -4379043:
                            if (next.equals("elevation")) {
                                b14 = 12;
                                str47 = str;
                                str49 = str3;
                                str50 = str2;
                                str51 = str67;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = b14;
                                str48 = str4;
                                obj21 = obj27;
                                obj23 = obj26;
                            }
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                        case 37232917:
                            if (next.equals("transitionPathRotate")) {
                                b14 = 13;
                                str47 = str;
                                str49 = str3;
                                str50 = str2;
                                str51 = str67;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = b14;
                                str48 = str4;
                                obj21 = obj27;
                                obj23 = obj26;
                            }
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                        case 92909918:
                            if (next.equals("alpha")) {
                                b14 = 14;
                                str47 = str;
                                str49 = str3;
                                str50 = str2;
                                str51 = str67;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = b14;
                                str48 = str4;
                                obj21 = obj27;
                                obj23 = obj26;
                            }
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                        case 156108012:
                            if (next.equals(str67)) {
                                b14 = 15;
                                str47 = str;
                                str49 = str3;
                                str50 = str2;
                                str51 = str67;
                                str52 = str5;
                                arrayList5 = arrayList7;
                                obj22 = obj25;
                                b13 = b14;
                                str48 = str4;
                                obj21 = obj27;
                                obj23 = obj26;
                            }
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                        default:
                            str47 = str;
                            str48 = str4;
                            str49 = str3;
                            str50 = str2;
                            str51 = str67;
                            obj21 = obj27;
                            str52 = str5;
                            arrayList5 = arrayList7;
                            obj22 = obj25;
                            obj23 = obj26;
                            break;
                    }
                    switch (b13) {
                        case 0:
                            iVar = new rn3.i();
                            break;
                        case 1:
                            iVar = new rn3.j();
                            break;
                        case 2:
                            iVar = new rn3.m();
                            break;
                        case 3:
                            iVar = new rn3.n();
                            break;
                        case 4:
                            iVar = new rn3.o();
                            break;
                        case 5:
                            iVar = new rn3.g();
                            break;
                        case 6:
                            iVar = new rn3.k();
                            break;
                        case 7:
                            iVar = new rn3.l();
                            break;
                        case 8:
                            iVar = new rn3.a();
                            break;
                        case 9:
                            iVar = new rn3.e();
                            break;
                        case 10:
                            iVar = new rn3.f();
                            break;
                        case 11:
                            iVar = new rn3.h();
                            break;
                        case 12:
                            iVar = new rn3.c();
                            break;
                        case 13:
                            iVar = new rn3.d();
                            break;
                        case 14:
                            iVar = new rn3.a();
                            break;
                        case 15:
                            iVar = new rn3.a();
                            break;
                        default:
                            iVar = null;
                            break;
                    }
                    obj24 = obj22;
                    rn3Var = iVar;
                }
                if (rn3Var == null) {
                    str53 = str52;
                } else {
                    rn3Var.e = next;
                    str53 = str52;
                    bj1Var2.y.put(next, rn3Var);
                }
                str2 = str50;
                str3 = str49;
                str67 = str51;
                arrayList7 = arrayList5;
                it7 = it6;
                hashSet8 = hashSet5;
                str = str47;
                str5 = str53;
                obj25 = obj24;
                obj26 = obj23;
                obj27 = obj21;
                str4 = str48;
                hashSet7 = hashSet4;
            }
            hashSet = hashSet7;
            hashSet2 = hashSet8;
            str6 = str;
            str7 = str4;
            str8 = str3;
            str9 = str2;
            str10 = str67;
            obj = obj27;
            obj2 = obj26;
            obj3 = obj25;
            str11 = str5;
            arrayList2 = arrayList7;
            if (arrayList6 != null) {
                for (y31 y31Var3 : arrayList6) {
                    if (y31Var3 instanceof z31) {
                        y31Var3.a(bj1Var2.y);
                    }
                }
            }
            bj1Var2.h.a(bj1Var2.y, 0);
            bj1Var2.i.a(bj1Var2.y, 100);
            Iterator<String> it8 = bj1Var2.y.keySet().iterator();
            while (it8.hasNext()) {
                String next2 = it8.next();
                int iIntValue = (!map5.containsKey(next2) || (num = map5.get(next2)) == null) ? 0 : num.intValue();
                Iterator<String> it9 = it8;
                rn3 rn3Var2 = bj1Var2.y.get(next2);
                if (rn3Var2 != null) {
                    rn3Var2.c(iIntValue);
                }
                it8 = it9;
            }
        }
        String str70 = "CUSTOM";
        if (hashSet6.isEmpty()) {
            arrayList3 = arrayList6;
            str12 = "CUSTOM";
            str13 = "CUSTOM,";
            str14 = str11;
            obj4 = obj3;
            obj5 = obj2;
            obj6 = obj;
            String str71 = str9;
            bj1Var = bj1Var2;
            str15 = str71;
        } else {
            if (bj1Var2.x == null) {
                bj1Var2.x = new HashMap<>();
            }
            Iterator<String> it10 = hashSet6.iterator();
            while (it10.hasNext()) {
                String next3 = it10.next();
                if (!bj1Var2.x.containsKey(next3)) {
                    if (!next3.startsWith(str68)) {
                        it5 = it10;
                        map4 = map5;
                        str44 = str68;
                        switch (next3.hashCode()) {
                            case -1249320806:
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                b11 = next3.equals(obj17) ? (byte) 0 : (byte) -1;
                                break;
                            case -1249320805:
                                str45 = str11;
                                obj18 = obj2;
                                obj19 = obj;
                                if (next3.equals(obj18)) {
                                    b11 = 1;
                                    obj17 = obj3;
                                } else {
                                    obj17 = obj3;
                                }
                                break;
                            case -1225497657:
                                str45 = str11;
                                obj19 = obj;
                                if (next3.equals(obj19)) {
                                    b11 = 2;
                                    obj17 = obj3;
                                    obj18 = obj2;
                                } else {
                                    obj17 = obj3;
                                    obj18 = obj2;
                                }
                                break;
                            case -1225497656:
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                if (next3.equals(str45)) {
                                    obj19 = obj;
                                    b11 = 3;
                                }
                                obj19 = obj;
                                break;
                            case -1225497655:
                                if (next3.equals(str6)) {
                                    str45 = str11;
                                    obj17 = obj3;
                                    obj18 = obj2;
                                    obj19 = obj;
                                    b11 = 4;
                                }
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                break;
                            case -1001078227:
                                if (next3.equals(str8)) {
                                    b12 = 5;
                                    obj17 = obj3;
                                    obj18 = obj2;
                                    obj19 = obj;
                                    String str72 = str11;
                                    b11 = b12;
                                    str45 = str72;
                                }
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                break;
                            case -908189618:
                                if (next3.equals(str9)) {
                                    b12 = 6;
                                    obj17 = obj3;
                                    obj18 = obj2;
                                    obj19 = obj;
                                    String str722 = str11;
                                    b11 = b12;
                                    str45 = str722;
                                }
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                break;
                            case -908189617:
                                if (next3.equals(str7)) {
                                    b12 = 7;
                                    obj17 = obj3;
                                    obj18 = obj2;
                                    obj19 = obj;
                                    String str7222 = str11;
                                    b11 = b12;
                                    str45 = str7222;
                                }
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                break;
                            case -40300674:
                                if (next3.equals("rotation")) {
                                    b12 = 8;
                                    obj17 = obj3;
                                    obj18 = obj2;
                                    obj19 = obj;
                                    String str72222 = str11;
                                    b11 = b12;
                                    str45 = str72222;
                                }
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                break;
                            case -4379043:
                                if (next3.equals("elevation")) {
                                    b12 = 9;
                                    obj17 = obj3;
                                    obj18 = obj2;
                                    obj19 = obj;
                                    String str722222 = str11;
                                    b11 = b12;
                                    str45 = str722222;
                                }
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                break;
                            case 37232917:
                                if (next3.equals("transitionPathRotate")) {
                                    b12 = 10;
                                    obj17 = obj3;
                                    obj18 = obj2;
                                    obj19 = obj;
                                    String str7222222 = str11;
                                    b11 = b12;
                                    str45 = str7222222;
                                }
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                break;
                            case 92909918:
                                if (next3.equals("alpha")) {
                                    b12 = 11;
                                    obj17 = obj3;
                                    obj18 = obj2;
                                    obj19 = obj;
                                    String str72222222 = str11;
                                    b11 = b12;
                                    str45 = str72222222;
                                }
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                break;
                            default:
                                str45 = str11;
                                obj17 = obj3;
                                obj18 = obj2;
                                obj19 = obj;
                                break;
                        }
                        switch (b11) {
                            case 0:
                                gVar2 = new sn3.g();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 1:
                                gVar2 = new sn3.h();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 2:
                                gVar2 = new sn3.k();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 3:
                                gVar2 = new sn3.l();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 4:
                                gVar2 = new sn3.m();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 5:
                                gVar2 = new sn3.e();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 6:
                                gVar2 = new sn3.i();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 7:
                                gVar2 = new sn3.j();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 8:
                                gVar2 = new sn3.f();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 9:
                                gVar2 = new sn3.c();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 10:
                                gVar2 = new sn3.d();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            case 11:
                                gVar2 = new sn3.a();
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = gVar2;
                                str46 = str9;
                                bVar2.i = j;
                                break;
                            default:
                                str46 = str9;
                                obj20 = obj19;
                                obj3 = obj17;
                                bVar2 = null;
                                break;
                        }
                    } else {
                        SparseArray sparseArray2 = new SparseArray();
                        it5 = it10;
                        String str73 = next3.split(",")[1];
                        for (y31 y31Var4 : arrayList6) {
                            String str74 = str68;
                            HashMap<String, Integer> map7 = map5;
                            HashMap<String, ox> map8 = y31Var4.d;
                            if (map8 != null && (oxVar2 = map8.get(str73)) != null) {
                                sparseArray2.append(y31Var4.a, oxVar2);
                            }
                            map5 = map7;
                            str68 = str74;
                        }
                        map4 = map5;
                        str44 = str68;
                        bVar2 = new sn3.b(sparseArray2, next3);
                        str45 = str11;
                        obj18 = obj2;
                        obj20 = obj;
                        str46 = str9;
                    }
                    if (bVar2 != null) {
                        bVar2.f = next3;
                        bj1Var2.x.put(next3, bVar2);
                    }
                    str9 = str46;
                    obj = obj20;
                    map5 = map4;
                    str11 = str45;
                    obj2 = obj18;
                    it10 = it5;
                    str68 = str44;
                }
            }
            HashMap<String, Integer> map9 = map5;
            str13 = str68;
            str14 = str11;
            Object obj30 = obj2;
            Object obj31 = obj;
            String str75 = str9;
            if (arrayList6 != null) {
                Iterator<y31> it11 = arrayList6.iterator();
                while (it11.hasNext()) {
                    y31 next4 = it11.next();
                    if (next4 instanceof j41) {
                        j41 j41Var2 = (j41) next4;
                        HashMap<String, sn3> map10 = bj1Var2.x;
                        j41Var2.getClass();
                        Iterator<String> it12 = map10.keySet().iterator();
                        while (it12.hasNext()) {
                            Iterator<y31> it13 = it11;
                            String next5 = it12.next();
                            sn3 sn3Var = map10.get(next5);
                            if (sn3Var == null) {
                                arrayList4 = arrayList6;
                                map3 = map10;
                                str42 = str70;
                                it4 = it12;
                                str43 = str75;
                                obj13 = obj3;
                                j41Var = j41Var2;
                                obj14 = obj30;
                                obj15 = obj31;
                            } else if (!next5.startsWith(str70)) {
                                j41 j41Var3 = j41Var2;
                                arrayList4 = arrayList6;
                                map3 = map10;
                                str42 = str70;
                                it4 = it12;
                                switch (next5.hashCode()) {
                                    case -1249320806:
                                        str43 = str75;
                                        obj16 = obj3;
                                        obj15 = obj31;
                                        b8 = next5.equals(obj16) ? (byte) 0 : (byte) -1;
                                        break;
                                    case -1249320805:
                                        str43 = str75;
                                        obj15 = obj31;
                                        if (next5.equals(obj30)) {
                                            b9 = 1;
                                            b8 = b9;
                                            obj16 = obj3;
                                        }
                                        obj16 = obj3;
                                        break;
                                    case -1225497657:
                                        str43 = str75;
                                        obj15 = obj31;
                                        if (next5.equals(obj15)) {
                                            b9 = 2;
                                            b8 = b9;
                                            obj16 = obj3;
                                        }
                                        obj16 = obj3;
                                        break;
                                    case -1225497656:
                                        str43 = str75;
                                        if (next5.equals(str14)) {
                                            b10 = 3;
                                            b8 = b10;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        }
                                        obj16 = obj3;
                                        obj15 = obj31;
                                        break;
                                    case -1225497655:
                                        str43 = str75;
                                        if (next5.equals(str6)) {
                                            b10 = 4;
                                            b8 = b10;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        }
                                        obj16 = obj3;
                                        obj15 = obj31;
                                        break;
                                    case -1001078227:
                                        str43 = str75;
                                        if (next5.equals(str8)) {
                                            b10 = 5;
                                            b8 = b10;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        }
                                        obj16 = obj3;
                                        obj15 = obj31;
                                        break;
                                    case -908189618:
                                        str43 = str75;
                                        if (next5.equals(str43)) {
                                            b10 = 6;
                                            b8 = b10;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        }
                                        obj16 = obj3;
                                        obj15 = obj31;
                                        break;
                                    case -908189617:
                                        if (next5.equals(str7)) {
                                            b8 = 7;
                                            str43 = str75;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        } else {
                                            str43 = str75;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        }
                                        break;
                                    case -40300674:
                                        if (next5.equals("rotation")) {
                                            str43 = str75;
                                            b10 = 8;
                                            b8 = b10;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        } else {
                                            str43 = str75;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        }
                                        break;
                                    case -4379043:
                                        if (next5.equals("elevation")) {
                                            str43 = str75;
                                            b10 = 9;
                                            b8 = b10;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        } else {
                                            str43 = str75;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        }
                                        break;
                                    case 37232917:
                                        if (next5.equals("transitionPathRotate")) {
                                            str43 = str75;
                                            b10 = 10;
                                            b8 = b10;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        } else {
                                            str43 = str75;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        }
                                        break;
                                    case 92909918:
                                        if (next5.equals("alpha")) {
                                            str43 = str75;
                                            b10 = 11;
                                            b8 = b10;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        } else {
                                            str43 = str75;
                                            obj16 = obj3;
                                            obj15 = obj31;
                                        }
                                        break;
                                    default:
                                        str43 = str75;
                                        obj16 = obj3;
                                        obj15 = obj31;
                                        break;
                                }
                                switch (b8) {
                                    case 0:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.i)) {
                                            sn3Var.b(j41Var.i, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 1:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.j)) {
                                            sn3Var.b(j41Var.j, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 2:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.n)) {
                                            sn3Var.b(j41Var.n, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 3:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.o)) {
                                            sn3Var.b(j41Var.o, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 4:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.p)) {
                                            sn3Var.b(j41Var.p, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 5:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.q)) {
                                            sn3Var.b(j41Var.q, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 6:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.l)) {
                                            sn3Var.b(j41Var.l, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 7:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.m)) {
                                            sn3Var.b(j41Var.m, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 8:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.h)) {
                                            sn3Var.b(j41Var.h, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 9:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.g)) {
                                            sn3Var.b(j41Var.g, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 10:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        if (!Float.isNaN(j41Var.k)) {
                                            sn3Var.b(j41Var.k, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    case 11:
                                        j41Var = j41Var3;
                                        if (Float.isNaN(j41Var.f)) {
                                            obj13 = obj16;
                                            obj14 = obj30;
                                        } else {
                                            obj13 = obj16;
                                            obj14 = obj30;
                                            sn3Var.b(j41Var.f, j41Var.s, j41Var.t, j41Var.a, j41Var.r);
                                        }
                                        break;
                                    default:
                                        obj13 = obj16;
                                        obj14 = obj30;
                                        j41Var = j41Var3;
                                        Log.e("KeyTimeCycles", "UNKNOWN addValues \"" + next5 + "\"");
                                        break;
                                }
                            } else {
                                HashMap<String, sn3> map11 = map10;
                                ox oxVar4 = j41Var2.d.get(next5.substring(7));
                                if (oxVar4 != null) {
                                    sn3.b bVar4 = (sn3.b) sn3Var;
                                    Iterator<String> it14 = it12;
                                    int i7 = j41Var2.a;
                                    String str76 = str70;
                                    float f4 = j41Var2.s;
                                    ArrayList<y31> arrayList10 = arrayList6;
                                    int i8 = j41Var2.r;
                                    float f5 = j41Var2.t;
                                    bVar4.l.append(i7, oxVar4);
                                    bVar4.m.append(i7, new float[]{f4, f5});
                                    bVar4.b = Math.max(bVar4.b, i8);
                                    it11 = it13;
                                    it12 = it14;
                                    map10 = map11;
                                    str70 = str76;
                                    arrayList6 = arrayList10;
                                    j41Var2 = j41Var2;
                                } else {
                                    it11 = it13;
                                    map10 = map11;
                                }
                            }
                            it11 = it13;
                            it12 = it4;
                            obj31 = obj15;
                            j41Var2 = j41Var;
                            obj30 = obj14;
                            map10 = map3;
                            str70 = str42;
                            arrayList6 = arrayList4;
                            obj3 = obj13;
                            str75 = str43;
                        }
                    }
                    it11 = it11;
                    obj31 = obj31;
                    obj30 = obj30;
                    str70 = str70;
                    arrayList6 = arrayList6;
                    obj3 = obj3;
                    str75 = str75;
                    bj1Var2 = this;
                }
            }
            arrayList3 = arrayList6;
            str12 = str70;
            str15 = str75;
            obj4 = obj3;
            obj6 = obj31;
            obj5 = obj30;
            bj1Var = this;
            for (String str77 : bj1Var.x.keySet()) {
                HashMap<String, Integer> map12 = map9;
                bj1Var.x.get(str77).c(map12.containsKey(str77) ? map12.get(str77).intValue() : 0);
                map9 = map12;
            }
        }
        int size = arrayList2.size() + 2;
        ej1[] ej1VarArr = new ej1[size];
        ej1VarArr[0] = ej1Var3;
        ej1VarArr[size - 1] = bj1Var.g;
        if (arrayList2.size() > 0 && bj1Var.e == -1) {
            bj1Var.e = 0;
        }
        Iterator<ej1> it15 = arrayList2.iterator();
        int i9 = 1;
        while (it15.hasNext()) {
            ej1VarArr[i9] = it15.next();
            i9++;
        }
        HashSet hashSet11 = new HashSet();
        Iterator<String> it16 = bj1Var.g.r.keySet().iterator();
        while (it16.hasNext()) {
            String next6 = it16.next();
            Object obj32 = obj6;
            Iterator<String> it17 = it16;
            ej1 ej1Var5 = ej1Var3;
            if (ej1Var5.r.containsKey(next6)) {
                ej1Var3 = ej1Var5;
                hashSet3 = hashSet;
                if (!hashSet3.contains(str13 + next6)) {
                    hashSet11.add(next6);
                }
            } else {
                ej1Var3 = ej1Var5;
                hashSet3 = hashSet;
            }
            hashSet = hashSet3;
            obj6 = obj32;
            it16 = it17;
        }
        Object obj33 = obj6;
        String[] strArr = (String[]) hashSet11.toArray(new String[0]);
        bj1Var.r = strArr;
        bj1Var.s = new int[strArr.length];
        int i10 = 0;
        while (true) {
            String[] strArr2 = bj1Var.r;
            if (i10 < strArr2.length) {
                String str78 = strArr2[i10];
                bj1Var.s[i10] = 0;
                int i11 = 0;
                while (true) {
                    if (i11 >= size) {
                        break;
                    }
                    if (!ej1VarArr[i11].r.containsKey(str78) || (oxVar = ej1VarArr[i11].r.get(str78)) == null) {
                        i11++;
                    } else {
                        int[] iArr = bj1Var.s;
                        iArr[i10] = oxVar.c() + iArr[i10];
                    }
                }
                i10++;
            } else {
                boolean z = ej1VarArr[0].n != -1;
                int length = strArr2.length + 18;
                boolean[] zArr = new boolean[length];
                int i12 = 1;
                while (i12 < size) {
                    String str79 = str14;
                    ej1 ej1Var6 = ej1VarArr[i12];
                    String str80 = str6;
                    ej1 ej1Var7 = ej1VarArr[i12 - 1];
                    String str81 = str15;
                    boolean zE = ej1.e(ej1Var6.i, ej1Var7.i);
                    String str82 = str7;
                    boolean zE2 = ej1.e(ej1Var6.j, ej1Var7.j);
                    zArr[0] = zArr[0] | ej1.e(ej1Var6.h, ej1Var7.h);
                    boolean z2 = zE | zE2 | z;
                    zArr[1] = zArr[1] | z2;
                    zArr[2] = z2 | zArr[2];
                    zArr[3] = zArr[3] | ej1.e(ej1Var6.k, ej1Var7.k);
                    zArr[4] = zArr[4] | ej1.e(ej1Var6.l, ej1Var7.l);
                    i12++;
                    str14 = str79;
                    str8 = str8;
                    str6 = str80;
                    str15 = str81;
                    str7 = str82;
                    str59 = str59;
                    str58 = str58;
                }
                String str83 = str15;
                String str84 = str14;
                String str85 = str7;
                String str86 = str8;
                String str87 = str58;
                String str88 = str59;
                String str89 = str6;
                int i13 = 0;
                for (int i14 = 1; i14 < length; i14++) {
                    if (zArr[i14]) {
                        i13++;
                    }
                }
                bj1Var.o = new int[i13];
                int iMax = Math.max(2, i13);
                bj1Var.p = new double[iMax];
                bj1Var.q = new double[iMax];
                int i15 = 0;
                for (int i16 = 1; i16 < length; i16++) {
                    if (zArr[i16]) {
                        bj1Var.o[i15] = i16;
                        i15++;
                    }
                }
                double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, bj1Var.o.length);
                double[] dArr4 = new double[size];
                for (int i17 = 0; i17 < size; i17++) {
                    ej1 ej1Var8 = ej1VarArr[i17];
                    double[] dArr5 = dArr3[i17];
                    int[] iArr2 = bj1Var.o;
                    float[] fArr = {ej1Var8.h, ej1Var8.i, ej1Var8.j, ej1Var8.k, ej1Var8.l, ej1Var8.m};
                    int i18 = 0;
                    for (int i19 : iArr2) {
                        if (i19 < 6) {
                            dArr5[i18] = fArr[r13];
                            i18++;
                        }
                    }
                    dArr4[i17] = ej1VarArr[i17].g;
                }
                int i20 = 0;
                while (true) {
                    int[] iArr3 = bj1Var.o;
                    if (i20 < iArr3.length) {
                        if (iArr3[i20] < 6) {
                            String strI = gq2.i(new StringBuilder(), ej1.v[bj1Var.o[i20]], " [");
                            for (int i21 = 0; i21 < size; i21++) {
                                StringBuilder sbJ = C0173a.j(strI);
                                sbJ.append(dArr3[i21][i20]);
                                strI = sbJ.toString();
                            }
                        }
                        i20++;
                    } else {
                        bj1Var.j = new l20[bj1Var.r.length + 1];
                        int i22 = 0;
                        while (true) {
                            String[] strArr3 = bj1Var.r;
                            if (i22 >= strArr3.length) {
                                String str90 = str57;
                                bj1Var.j[0] = l20.a(bj1Var.e, dArr4, dArr3);
                                if (ej1VarArr[0].n != -1) {
                                    int[] iArr4 = new int[size];
                                    double[] dArr6 = new double[size];
                                    double[][] dArr7 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, 2);
                                    for (int i23 = 0; i23 < size; i23++) {
                                        iArr4[i23] = ej1VarArr[i23].n;
                                        dArr6[i23] = r6.g;
                                        double[] dArr8 = dArr7[i23];
                                        dArr8[0] = r6.i;
                                        dArr8[1] = r6.j;
                                    }
                                    bj1Var.k = new d8(iArr4, dArr6, dArr7);
                                }
                                bj1Var.z = new HashMap<>();
                                if (arrayList3 != null) {
                                    Iterator<String> it18 = hashSet2.iterator();
                                    float f6 = Float.NaN;
                                    while (it18.hasNext()) {
                                        String next7 = it18.next();
                                        String str91 = str12;
                                        if (!next7.startsWith(str91)) {
                                            switch (next7.hashCode()) {
                                                case -1249320806:
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str25 = str10;
                                                    str26 = str84;
                                                    str27 = str86;
                                                    str28 = str89;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    obj12 = obj5;
                                                    b5 = next7.equals(obj10) ? (byte) 0 : (byte) -1;
                                                    break;
                                                case -1249320805:
                                                    it3 = it18;
                                                    Object obj34 = obj5;
                                                    obj11 = obj33;
                                                    str25 = str10;
                                                    str26 = str84;
                                                    str27 = str86;
                                                    str28 = str89;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    if (next7.equals(obj34)) {
                                                        obj12 = obj34;
                                                        obj10 = obj4;
                                                        b5 = 1;
                                                    } else {
                                                        obj12 = obj34;
                                                        obj10 = obj4;
                                                    }
                                                    break;
                                                case -1225497657:
                                                    obj11 = obj33;
                                                    str25 = str10;
                                                    str26 = str84;
                                                    str27 = str86;
                                                    str28 = str89;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    if (next7.equals(obj11)) {
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        b5 = 2;
                                                        obj12 = obj5;
                                                    } else {
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj12 = obj5;
                                                    }
                                                    break;
                                                case -1225497656:
                                                    str25 = str10;
                                                    str26 = str84;
                                                    str27 = str86;
                                                    str28 = str89;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    if (next7.equals(str26)) {
                                                        obj11 = obj33;
                                                        b5 = 3;
                                                        obj12 = obj5;
                                                    } else {
                                                        obj11 = obj33;
                                                        obj12 = obj5;
                                                    }
                                                    break;
                                                case -1225497655:
                                                    str25 = str10;
                                                    str27 = str86;
                                                    str28 = str89;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    if (next7.equals(str28)) {
                                                        str26 = str84;
                                                        b5 = 4;
                                                        obj12 = obj5;
                                                    } else {
                                                        str26 = str84;
                                                        obj12 = obj5;
                                                    }
                                                    break;
                                                case -1001078227:
                                                    str25 = str10;
                                                    str27 = str86;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    if (next7.equals(str27)) {
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj11 = obj33;
                                                        str26 = str84;
                                                        b5 = 5;
                                                        str28 = str89;
                                                        obj12 = obj5;
                                                    }
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str26 = str84;
                                                    str28 = str89;
                                                    obj12 = obj5;
                                                    break;
                                                case -908189618:
                                                    str25 = str10;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    if (next7.equals(str29)) {
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj11 = obj33;
                                                        str26 = str84;
                                                        str28 = str89;
                                                        b5 = 6;
                                                        obj12 = obj5;
                                                        str27 = str86;
                                                    }
                                                    str27 = str86;
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str26 = str84;
                                                    str28 = str89;
                                                    obj12 = obj5;
                                                    break;
                                                case -908189617:
                                                    str25 = str10;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    if (next7.equals(str30)) {
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj11 = obj33;
                                                        str26 = str84;
                                                        str27 = str86;
                                                        str28 = str89;
                                                        b5 = 7;
                                                        obj12 = obj5;
                                                        str29 = str83;
                                                    }
                                                    str29 = str83;
                                                    str27 = str86;
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str26 = str84;
                                                    str28 = str89;
                                                    obj12 = obj5;
                                                    break;
                                                case -797520672:
                                                    str25 = str10;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    if (next7.equals("waveVariesBy")) {
                                                        b6 = 8;
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj11 = obj33;
                                                        str26 = str84;
                                                        str27 = str86;
                                                        str28 = str89;
                                                        str29 = str83;
                                                        b5 = b6;
                                                        obj12 = obj5;
                                                        str30 = str85;
                                                    }
                                                    str30 = str85;
                                                    str29 = str83;
                                                    str27 = str86;
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str26 = str84;
                                                    str28 = str89;
                                                    obj12 = obj5;
                                                    break;
                                                case -40300674:
                                                    str25 = str10;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    if (next7.equals(str31)) {
                                                        b6 = 9;
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj11 = obj33;
                                                        str26 = str84;
                                                        str27 = str86;
                                                        str28 = str89;
                                                        str29 = str83;
                                                        b5 = b6;
                                                        obj12 = obj5;
                                                        str30 = str85;
                                                    }
                                                    str30 = str85;
                                                    str29 = str83;
                                                    str27 = str86;
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str26 = str84;
                                                    str28 = str89;
                                                    obj12 = obj5;
                                                    break;
                                                case -4379043:
                                                    str25 = str10;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str26 = str84;
                                                    str27 = str86;
                                                    str28 = str89;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    if (next7.equals(str32)) {
                                                        str31 = str88;
                                                        b5 = 10;
                                                        obj12 = obj5;
                                                    } else {
                                                        str31 = str88;
                                                        obj12 = obj5;
                                                    }
                                                    break;
                                                case 37232917:
                                                    str25 = str10;
                                                    str33 = str90;
                                                    if (next7.equals("transitionPathRotate")) {
                                                        b7 = 11;
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj11 = obj33;
                                                        str26 = str84;
                                                        str27 = str86;
                                                        str28 = str89;
                                                        str29 = str83;
                                                        str30 = str85;
                                                        str31 = str88;
                                                        b5 = b7;
                                                        obj12 = obj5;
                                                        str32 = str87;
                                                    }
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str26 = str84;
                                                    str27 = str86;
                                                    str28 = str89;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    obj12 = obj5;
                                                    break;
                                                case 92909918:
                                                    str25 = str10;
                                                    str33 = str90;
                                                    if (next7.equals(str33)) {
                                                        b7 = 12;
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj11 = obj33;
                                                        str26 = str84;
                                                        str27 = str86;
                                                        str28 = str89;
                                                        str29 = str83;
                                                        str30 = str85;
                                                        str31 = str88;
                                                        b5 = b7;
                                                        obj12 = obj5;
                                                        str32 = str87;
                                                    }
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str26 = str84;
                                                    str27 = str86;
                                                    str28 = str89;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    obj12 = obj5;
                                                    break;
                                                case 156108012:
                                                    str25 = str10;
                                                    if (next7.equals(str25)) {
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj11 = obj33;
                                                        str26 = str84;
                                                        str27 = str86;
                                                        str28 = str89;
                                                        str29 = str83;
                                                        str30 = str85;
                                                        str31 = str88;
                                                        str32 = str87;
                                                        b5 = 13;
                                                        obj12 = obj5;
                                                        str33 = str90;
                                                    } else {
                                                        it3 = it18;
                                                        obj10 = obj4;
                                                        obj11 = obj33;
                                                        str26 = str84;
                                                        str27 = str86;
                                                        str28 = str89;
                                                        str29 = str83;
                                                        str30 = str85;
                                                        str31 = str88;
                                                        str32 = str87;
                                                        str33 = str90;
                                                        obj12 = obj5;
                                                    }
                                                    break;
                                                default:
                                                    it3 = it18;
                                                    obj10 = obj4;
                                                    obj11 = obj33;
                                                    str25 = str10;
                                                    str26 = str84;
                                                    str27 = str86;
                                                    str28 = str89;
                                                    str29 = str83;
                                                    str30 = str85;
                                                    str31 = str88;
                                                    str32 = str87;
                                                    str33 = str90;
                                                    obj12 = obj5;
                                                    break;
                                            }
                                            switch (b5) {
                                                case 0:
                                                    gVar = new dn3.g();
                                                    dn3 dn3Var2 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var2;
                                                    break;
                                                case 1:
                                                    gVar = new dn3.h();
                                                    dn3 dn3Var22 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var22;
                                                    break;
                                                case 2:
                                                    gVar = new dn3.k();
                                                    dn3 dn3Var222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var222;
                                                    break;
                                                case 3:
                                                    gVar = new dn3.l();
                                                    dn3 dn3Var2222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var2222;
                                                    break;
                                                case 4:
                                                    gVar = new dn3.m();
                                                    dn3 dn3Var22222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var22222;
                                                    break;
                                                case 5:
                                                    gVar = new dn3.e();
                                                    dn3 dn3Var222222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var222222;
                                                    break;
                                                case 6:
                                                    gVar = new dn3.i();
                                                    dn3 dn3Var2222222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var2222222;
                                                    break;
                                                case 7:
                                                    gVar = new dn3.j();
                                                    dn3 dn3Var22222222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var22222222;
                                                    break;
                                                case 8:
                                                    gVar = new dn3.a();
                                                    dn3 dn3Var222222222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var222222222;
                                                    break;
                                                case 9:
                                                    gVar = new dn3.f();
                                                    dn3 dn3Var2222222222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var2222222222;
                                                    break;
                                                case 10:
                                                    gVar = new dn3.c();
                                                    dn3 dn3Var22222222222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var22222222222;
                                                    break;
                                                case 11:
                                                    gVar = new dn3.d();
                                                    dn3 dn3Var222222222222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var222222222222;
                                                    break;
                                                case 12:
                                                    gVar = new dn3.a();
                                                    dn3 dn3Var2222222222222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var2222222222222;
                                                    break;
                                                case 13:
                                                    gVar = new dn3.a();
                                                    dn3 dn3Var22222222222222 = gVar;
                                                    obj4 = obj10;
                                                    bVar = dn3Var22222222222222;
                                                    break;
                                                default:
                                                    obj4 = obj10;
                                                    bVar = null;
                                                    break;
                                            }
                                        } else {
                                            it3 = it18;
                                            bVar = new dn3.b();
                                            obj11 = obj33;
                                            str25 = str10;
                                            str26 = str84;
                                            str27 = str86;
                                            str28 = str89;
                                            str29 = str83;
                                            str30 = str85;
                                            str31 = str88;
                                            str32 = str87;
                                            str33 = str90;
                                            obj12 = obj5;
                                        }
                                        if (bVar == null) {
                                            it18 = it3;
                                            str12 = str91;
                                            str10 = str25;
                                            str90 = str33;
                                            str87 = str32;
                                            str88 = str31;
                                            str85 = str30;
                                            str83 = str29;
                                            str86 = str27;
                                            str89 = str28;
                                            str84 = str26;
                                            obj5 = obj12;
                                            obj33 = obj11;
                                        } else {
                                            Object obj35 = obj11;
                                            String str92 = str26;
                                            if ((bVar.e == 1) && Float.isNaN(f6)) {
                                                float[] fArr2 = new float[2];
                                                float f7 = 1.0f / 99;
                                                float fHypot = 0.0f;
                                                str36 = str27;
                                                double d = 0.0d;
                                                double d2 = 0.0d;
                                                str37 = str28;
                                                int i24 = 0;
                                                while (i24 < 100) {
                                                    float f8 = i24 * f7;
                                                    String str93 = str30;
                                                    String str94 = str29;
                                                    double d3 = f8;
                                                    ej1 ej1Var9 = ej1Var3;
                                                    hf0 hf0Var = ej1Var9.e;
                                                    float f9 = 0.0f;
                                                    float f10 = Float.NaN;
                                                    for (ej1 ej1Var10 : arrayList2) {
                                                        ej1 ej1Var11 = ej1Var9;
                                                        float f11 = f7;
                                                        hf0 hf0Var2 = ej1Var10.e;
                                                        if (hf0Var2 != null) {
                                                            float f12 = ej1Var10.g;
                                                            if (f12 < f8) {
                                                                f9 = f12;
                                                                hf0Var = hf0Var2;
                                                            } else if (Float.isNaN(f10)) {
                                                                f10 = ej1Var10.g;
                                                            }
                                                        }
                                                        ej1Var9 = ej1Var11;
                                                        f7 = f11;
                                                    }
                                                    ej1 ej1Var12 = ej1Var9;
                                                    float f13 = f7;
                                                    if (hf0Var != null) {
                                                        if (Float.isNaN(f10)) {
                                                            f10 = 1.0f;
                                                        }
                                                        str39 = str31;
                                                        dA = (((float) hf0Var.a((f8 - f9) / r34)) * (f10 - f9)) + f9;
                                                    } else {
                                                        str39 = str31;
                                                        dA = d3;
                                                    }
                                                    bj1Var.j[0].c(dA, bj1Var.p);
                                                    String str95 = str39;
                                                    bj1Var.f.f(dA, bj1Var.o, bj1Var.p, fArr2, 0);
                                                    if (i24 > 0) {
                                                        fHypot += (float) Math.hypot(d - ((double) fArr2[1]), d2 - ((double) fArr2[0]));
                                                    }
                                                    i24++;
                                                    d2 = fArr2[0];
                                                    d = fArr2[1];
                                                    str31 = str95;
                                                    str29 = str94;
                                                    ej1Var3 = ej1Var12;
                                                    f7 = f13;
                                                    str30 = str93;
                                                }
                                                str34 = str30;
                                                str35 = str29;
                                                ej1Var = ej1Var3;
                                                str38 = str31;
                                                f6 = fHypot;
                                            } else {
                                                str34 = str30;
                                                str35 = str29;
                                                str36 = str27;
                                                str37 = str28;
                                                ej1Var = ej1Var3;
                                                str38 = str31;
                                            }
                                            bVar.b = next7;
                                            bj1Var.z.put(next7, bVar);
                                            it18 = it3;
                                            str85 = str34;
                                            str90 = str33;
                                            str87 = str32;
                                            str88 = str38;
                                            str89 = str37;
                                            str86 = str36;
                                            str83 = str35;
                                            ej1Var3 = ej1Var;
                                            str12 = str91;
                                            str10 = str25;
                                            str84 = str92;
                                            obj5 = obj12;
                                            obj33 = obj35;
                                        }
                                    }
                                    String str96 = str10;
                                    String str97 = str12;
                                    String str98 = str86;
                                    String str99 = str89;
                                    String str100 = str83;
                                    String str101 = str85;
                                    String str102 = str88;
                                    String str103 = str87;
                                    String str104 = str90;
                                    Object obj36 = obj33;
                                    Object obj37 = obj5;
                                    String str105 = str84;
                                    Object obj38 = obj36;
                                    Iterator<y31> it19 = arrayList3.iterator();
                                    while (it19.hasNext()) {
                                        y31 next8 = it19.next();
                                        if (next8 instanceof b41) {
                                            b41 b41Var = (b41) next8;
                                            HashMap<String, dn3> map13 = bj1Var.z;
                                            b41Var.getClass();
                                            Iterator<String> it20 = map13.keySet().iterator();
                                            while (it20.hasNext()) {
                                                String next9 = it20.next();
                                                if (next9.startsWith(str97)) {
                                                    ox oxVar5 = b41Var.d.get(next9.substring(7));
                                                    if (oxVar5 != null) {
                                                        if (oxVar5.c == 2 && (dn3Var = map13.get(next9)) != null) {
                                                            int i25 = b41Var.a;
                                                            int i26 = b41Var.f;
                                                            String str106 = b41Var.g;
                                                            int i27 = b41Var.l;
                                                            it = it19;
                                                            it2 = it20;
                                                            map = map13;
                                                            str16 = str97;
                                                            dn3Var.f.add(new d41.b(i25, b41Var.h, b41Var.i, b41Var.j, oxVar5.a()));
                                                            if (i27 != -1) {
                                                                dn3Var.e = i27;
                                                            }
                                                            dn3Var.c = i26;
                                                            dn3Var.c(oxVar5);
                                                            dn3Var.d = str106;
                                                        } else {
                                                            it = it19;
                                                            map = map13;
                                                            str16 = str97;
                                                            it2 = it20;
                                                        }
                                                        it19 = it;
                                                        it20 = it2;
                                                        map13 = map;
                                                        str97 = str16;
                                                    }
                                                } else {
                                                    Iterator<y31> it21 = it19;
                                                    HashMap<String, dn3> map14 = map13;
                                                    String str107 = str97;
                                                    Iterator<String> it22 = it20;
                                                    switch (next9.hashCode()) {
                                                        case -1249320806:
                                                            str17 = str101;
                                                            str18 = str102;
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            b = next9.equals(obj7) ? (byte) 0 : (byte) -1;
                                                            break;
                                                        case -1249320805:
                                                            str17 = str101;
                                                            str18 = str102;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            if (next9.equals(obj8)) {
                                                                b = 1;
                                                                obj7 = obj4;
                                                            } else {
                                                                obj7 = obj4;
                                                            }
                                                            break;
                                                        case -1225497657:
                                                            str17 = str101;
                                                            str18 = str102;
                                                            str19 = str105;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            if (next9.equals(obj9)) {
                                                                b = 2;
                                                                obj7 = obj4;
                                                                obj8 = obj37;
                                                            } else {
                                                                obj7 = obj4;
                                                                obj8 = obj37;
                                                            }
                                                            break;
                                                        case -1225497656:
                                                            str17 = str101;
                                                            str18 = str102;
                                                            str19 = str105;
                                                            str20 = str99;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            obj7 = obj4;
                                                            obj8 = obj37;
                                                            if (next9.equals(str19)) {
                                                                obj9 = obj38;
                                                                b = 3;
                                                            }
                                                            obj9 = obj38;
                                                            break;
                                                        case -1225497655:
                                                            str17 = str101;
                                                            str18 = str102;
                                                            str20 = str99;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            if (next9.equals(str20)) {
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                obj9 = obj38;
                                                                b = 4;
                                                            }
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            obj9 = obj38;
                                                            break;
                                                        case -1001078227:
                                                            str17 = str101;
                                                            str18 = str102;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            if (next9.equals(str21)) {
                                                                b2 = 5;
                                                                b = b2;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                            }
                                                            str20 = str99;
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            obj9 = obj38;
                                                            break;
                                                        case -908189618:
                                                            str17 = str101;
                                                            str18 = str102;
                                                            str22 = str100;
                                                            if (next9.equals(str22)) {
                                                                b = 6;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                                str21 = str98;
                                                            } else {
                                                                str21 = str98;
                                                                str20 = str99;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                obj9 = obj38;
                                                            }
                                                            break;
                                                        case -908189617:
                                                            str17 = str101;
                                                            str18 = str102;
                                                            if (next9.equals(str17)) {
                                                                b = 7;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                                str21 = str98;
                                                                str22 = str100;
                                                            }
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            break;
                                                        case -40300674:
                                                            str18 = str102;
                                                            if (next9.equals(str18)) {
                                                                b3 = 8;
                                                                b2 = b3;
                                                                str21 = str98;
                                                                str22 = str100;
                                                                str17 = str101;
                                                                b = b2;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                            } else {
                                                                str17 = str101;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                                str21 = str98;
                                                                str22 = str100;
                                                            }
                                                            break;
                                                        case -4379043:
                                                            if (next9.equals(str103)) {
                                                                str18 = str102;
                                                                b3 = 9;
                                                                b2 = b3;
                                                                str21 = str98;
                                                                str22 = str100;
                                                                str17 = str101;
                                                                b = b2;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                            }
                                                            str17 = str101;
                                                            str18 = str102;
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            break;
                                                        case 37232917:
                                                            if (next9.equals("transitionPathRotate")) {
                                                                str18 = str102;
                                                                b3 = 10;
                                                                b2 = b3;
                                                                str21 = str98;
                                                                str22 = str100;
                                                                str17 = str101;
                                                                b = b2;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                            }
                                                            str17 = str101;
                                                            str18 = str102;
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            break;
                                                        case 92909918:
                                                            if (next9.equals(str104)) {
                                                                str18 = str102;
                                                                b3 = 11;
                                                                b2 = b3;
                                                                str21 = str98;
                                                                str22 = str100;
                                                                str17 = str101;
                                                                b = b2;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                            }
                                                            str17 = str101;
                                                            str18 = str102;
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            break;
                                                        case 156108012:
                                                            if (next9.equals(str96)) {
                                                                b4 = 12;
                                                                b3 = b4;
                                                                str18 = str102;
                                                                b2 = b3;
                                                                str21 = str98;
                                                                str22 = str100;
                                                                str17 = str101;
                                                                b = b2;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                            }
                                                            str17 = str101;
                                                            str18 = str102;
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            break;
                                                        case 1530034690:
                                                            if (next9.equals("wavePhase")) {
                                                                b4 = 13;
                                                                b3 = b4;
                                                                str18 = str102;
                                                                b2 = b3;
                                                                str21 = str98;
                                                                str22 = str100;
                                                                str17 = str101;
                                                                b = b2;
                                                                obj7 = obj4;
                                                                str19 = str105;
                                                                obj8 = obj37;
                                                                str20 = str99;
                                                                obj9 = obj38;
                                                            }
                                                            str17 = str101;
                                                            str18 = str102;
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            break;
                                                        default:
                                                            str17 = str101;
                                                            str18 = str102;
                                                            obj7 = obj4;
                                                            str19 = str105;
                                                            obj8 = obj37;
                                                            str20 = str99;
                                                            obj9 = obj38;
                                                            str21 = str98;
                                                            str22 = str100;
                                                            break;
                                                    }
                                                    switch (b) {
                                                        case 0:
                                                            f = b41Var.q;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 1:
                                                            f = b41Var.r;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 2:
                                                            f = b41Var.u;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 3:
                                                            f = b41Var.v;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 4:
                                                            f = b41Var.w;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 5:
                                                            f = b41Var.k;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 6:
                                                            f = b41Var.s;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 7:
                                                            f = b41Var.t;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 8:
                                                            f = b41Var.o;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 9:
                                                            f = b41Var.n;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 10:
                                                            f = b41Var.p;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 11:
                                                            f = b41Var.m;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 12:
                                                            f = b41Var.i;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        case 13:
                                                            f = b41Var.j;
                                                            f2 = f;
                                                            str23 = str107;
                                                            break;
                                                        default:
                                                            str23 = str107;
                                                            next9.startsWith(str23);
                                                            f2 = Float.NaN;
                                                            break;
                                                    }
                                                    if (Float.isNaN(f2)) {
                                                        str24 = str18;
                                                        map2 = map14;
                                                    } else {
                                                        str24 = str18;
                                                        map2 = map14;
                                                        dn3 dn3Var3 = map2.get(next9);
                                                        if (dn3Var3 != null) {
                                                            int i28 = b41Var.a;
                                                            String str108 = str17;
                                                            int i29 = b41Var.f;
                                                            String str109 = str22;
                                                            String str110 = b41Var.g;
                                                            String str111 = str21;
                                                            int i30 = b41Var.l;
                                                            String str112 = str96;
                                                            String str113 = str104;
                                                            String str114 = str103;
                                                            b41 b41Var2 = b41Var;
                                                            String str115 = str20;
                                                            dn3Var3.f.add(new d41.b(i28, b41Var.h, b41Var.i, b41Var.j, f2));
                                                            if (i30 != -1) {
                                                                dn3Var3.e = i30;
                                                            }
                                                            dn3Var3.c = i29;
                                                            dn3Var3.d = str110;
                                                            it19 = it21;
                                                            b41Var = b41Var2;
                                                            obj37 = obj8;
                                                            str97 = str23;
                                                            str98 = str111;
                                                            str100 = str109;
                                                            it20 = it22;
                                                            str96 = str112;
                                                            map13 = map2;
                                                            str101 = str108;
                                                            str102 = str24;
                                                            str103 = str114;
                                                            str104 = str113;
                                                            obj38 = obj9;
                                                            obj4 = obj7;
                                                            str99 = str115;
                                                            str105 = str19;
                                                        }
                                                    }
                                                    str101 = str17;
                                                    str100 = str22;
                                                    str98 = str21;
                                                    str99 = str20;
                                                    str105 = str19;
                                                    obj38 = obj9;
                                                    obj37 = obj8;
                                                    obj4 = obj7;
                                                    str97 = str23;
                                                    it20 = it22;
                                                    str102 = str24;
                                                    map13 = map2;
                                                    it19 = it21;
                                                }
                                            }
                                        }
                                        bj1Var = this;
                                        it19 = it19;
                                        obj37 = obj37;
                                        str97 = str97;
                                        str98 = str98;
                                        str100 = str100;
                                        str96 = str96;
                                        str101 = str101;
                                        str102 = str102;
                                        str103 = str103;
                                        str104 = str104;
                                        obj38 = obj38;
                                        obj4 = obj4;
                                        str99 = str99;
                                        str105 = str105;
                                    }
                                    Iterator<dn3> it23 = bj1Var.z.values().iterator();
                                    while (it23.hasNext()) {
                                        it23.next().d();
                                    }
                                    return;
                                }
                                return;
                            }
                            String str116 = strArr3[i22];
                            int i31 = 0;
                            int i32 = 0;
                            double[] dArr9 = null;
                            double[][] dArr10 = null;
                            while (i31 < size) {
                                if (ej1VarArr[i31].r.containsKey(str116)) {
                                    if (dArr10 == null) {
                                        dArr9 = new double[size];
                                        ox oxVar6 = ej1VarArr[i31].r.get(str116);
                                        dArr10 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, oxVar6 == null ? 0 : oxVar6.c());
                                    }
                                    ej1 ej1Var13 = ej1VarArr[i31];
                                    dArr9[i32] = ej1Var13.g;
                                    double[] dArr11 = dArr10[i32];
                                    ox oxVar7 = ej1Var13.r.get(str116);
                                    if (oxVar7 == null) {
                                        str40 = str116;
                                        str41 = str57;
                                        dArr = dArr9;
                                        dArr2 = dArr10;
                                    } else {
                                        str40 = str116;
                                        if (oxVar7.c() == 1) {
                                            dArr = dArr9;
                                            dArr2 = dArr10;
                                            dArr11[0] = oxVar7.a();
                                        } else {
                                            dArr = dArr9;
                                            dArr2 = dArr10;
                                            int iC = oxVar7.c();
                                            float[] fArr3 = new float[iC];
                                            oxVar7.b(fArr3);
                                            int i33 = 0;
                                            int i34 = 0;
                                            while (i33 < iC) {
                                                dArr11[i34] = fArr3[i33];
                                                i33++;
                                                i34++;
                                                iC = iC;
                                                str57 = str57;
                                                fArr3 = fArr3;
                                            }
                                        }
                                        str41 = str57;
                                    }
                                    i32++;
                                    dArr9 = dArr;
                                    dArr10 = dArr2;
                                } else {
                                    str40 = str116;
                                    str41 = str57;
                                }
                                i31++;
                                str116 = str40;
                                str57 = str41;
                            }
                            i22++;
                            bj1Var.j[i22] = l20.a(bj1Var.e, Arrays.copyOf(dArr9, i32), (double[][]) Arrays.copyOf(dArr10, i32));
                            str57 = str57;
                        }
                    }
                }
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(" start: x: ");
        ej1 ej1Var = this.f;
        sb.append(ej1Var.i);
        sb.append(" y: ");
        sb.append(ej1Var.j);
        sb.append(" end: x: ");
        ej1 ej1Var2 = this.g;
        sb.append(ej1Var2.i);
        sb.append(" y: ");
        sb.append(ej1Var2.j);
        return sb.toString();
    }
}
