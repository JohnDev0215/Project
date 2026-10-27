package androidx.constraintlayout.motion.widget;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.r;
import androidx.constraintlayout.widget.a;
import androidx.constraintlayout.widget.c;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
class m implements Comparable<m> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String[] f884b = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f887e;
    private b.e.a.a.c r;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f885c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f886d = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f888f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f889g = 0.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f890h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f891i = 0.0f;
    public float j = 0.0f;
    private float k = 1.0f;
    private float l = 1.0f;
    private float m = Float.NaN;
    private float n = Float.NaN;
    private float o = 0.0f;
    private float p = 0.0f;
    private float q = 0.0f;
    private int s = 0;
    private float y = Float.NaN;
    private float z = Float.NaN;
    LinkedHashMap<String, androidx.constraintlayout.widget.a> A = new LinkedHashMap<>();
    int B = 0;
    double[] C = new double[18];
    double[] D = new double[18];

    private boolean e(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void a(HashMap<String, r> map, int i2) {
        for (String str : map.keySet()) {
            r rVar = map.get(str);
            str.hashCode();
            byte b2 = -1;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        b2 = 0;
                    }
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        b2 = 1;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        b2 = 2;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        b2 = 3;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        b2 = 4;
                    }
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        b2 = 5;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        b2 = 6;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        b2 = 7;
                    }
                    break;
                case -760884510:
                    if (str.equals("transformPivotX")) {
                        b2 = 8;
                    }
                    break;
                case -760884509:
                    if (str.equals("transformPivotY")) {
                        b2 = 9;
                    }
                    break;
                case -40300674:
                    if (str.equals("rotation")) {
                        b2 = 10;
                    }
                    break;
                case -4379043:
                    if (str.equals("elevation")) {
                        b2 = 11;
                    }
                    break;
                case 37232917:
                    if (str.equals("transitionPathRotate")) {
                        b2 = 12;
                    }
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        b2 = 13;
                    }
                    break;
            }
            switch (b2) {
                case 0:
                    rVar.e(i2, Float.isNaN(this.f891i) ? 0.0f : this.f891i);
                    break;
                case 1:
                    rVar.e(i2, Float.isNaN(this.j) ? 0.0f : this.j);
                    break;
                case 2:
                    rVar.e(i2, Float.isNaN(this.o) ? 0.0f : this.o);
                    break;
                case 3:
                    rVar.e(i2, Float.isNaN(this.p) ? 0.0f : this.p);
                    break;
                case 4:
                    rVar.e(i2, Float.isNaN(this.q) ? 0.0f : this.q);
                    break;
                case 5:
                    rVar.e(i2, Float.isNaN(this.z) ? 0.0f : this.z);
                    break;
                case 6:
                    rVar.e(i2, Float.isNaN(this.k) ? 1.0f : this.k);
                    break;
                case 7:
                    rVar.e(i2, Float.isNaN(this.l) ? 1.0f : this.l);
                    break;
                case 8:
                    rVar.e(i2, Float.isNaN(this.m) ? 0.0f : this.m);
                    break;
                case 9:
                    rVar.e(i2, Float.isNaN(this.n) ? 0.0f : this.n);
                    break;
                case 10:
                    rVar.e(i2, Float.isNaN(this.f890h) ? 0.0f : this.f890h);
                    break;
                case 11:
                    rVar.e(i2, Float.isNaN(this.f889g) ? 0.0f : this.f889g);
                    break;
                case 12:
                    rVar.e(i2, Float.isNaN(this.y) ? 0.0f : this.y);
                    break;
                case 13:
                    rVar.e(i2, Float.isNaN(this.f885c) ? 1.0f : this.f885c);
                    break;
                default:
                    if (str.startsWith("CUSTOM")) {
                        String str2 = str.split(",")[1];
                        if (this.A.containsKey(str2)) {
                            androidx.constraintlayout.widget.a aVar = this.A.get(str2);
                            if (rVar instanceof r.b) {
                                ((r.b) rVar).i(i2, aVar);
                            } else {
                                Log.e("MotionPaths", str + " splineSet not a CustomSet frame = " + i2 + ", value" + aVar.d() + rVar);
                            }
                        } else {
                            Log.e("MotionPaths", "UNKNOWN customName " + str2);
                        }
                    } else {
                        Log.e("MotionPaths", "UNKNOWN spline " + str);
                    }
                    break;
            }
        }
    }

    public void b(View view) {
        this.f887e = view.getVisibility();
        this.f885c = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.f888f = false;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            this.f889g = view.getElevation();
        }
        this.f890h = view.getRotation();
        this.f891i = view.getRotationX();
        this.j = view.getRotationY();
        this.k = view.getScaleX();
        this.l = view.getScaleY();
        this.m = view.getPivotX();
        this.n = view.getPivotY();
        this.o = view.getTranslationX();
        this.p = view.getTranslationY();
        if (i2 >= 21) {
            this.q = view.getTranslationZ();
        }
    }

    public void c(c.a aVar) {
        c.d dVar = aVar.f1071b;
        int i2 = dVar.f1095c;
        this.f886d = i2;
        int i3 = dVar.f1094b;
        this.f887e = i3;
        this.f885c = (i3 == 0 || i2 != 0) ? dVar.f1096d : 0.0f;
        c.e eVar = aVar.f1074e;
        this.f888f = eVar.m;
        this.f889g = eVar.n;
        this.f890h = eVar.f1100c;
        this.f891i = eVar.f1101d;
        this.j = eVar.f1102e;
        this.k = eVar.f1103f;
        this.l = eVar.f1104g;
        this.m = eVar.f1105h;
        this.n = eVar.f1106i;
        this.o = eVar.j;
        this.p = eVar.k;
        this.q = eVar.l;
        this.r = b.e.a.a.c.c(aVar.f1072c.f1088d);
        c.C0012c c0012c = aVar.f1072c;
        this.y = c0012c.f1092h;
        this.s = c0012c.f1090f;
        this.z = aVar.f1071b.f1097e;
        for (String str : aVar.f1075f.keySet()) {
            androidx.constraintlayout.widget.a aVar2 = aVar.f1075f.get(str);
            if (aVar2.c() != a.b.STRING_TYPE) {
                this.A.put(str, aVar2);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(m mVar) {
        return Float.compare(this.t, mVar.t);
    }

    void f(m mVar, HashSet<String> hashSet) {
        if (e(this.f885c, mVar.f885c)) {
            hashSet.add("alpha");
        }
        if (e(this.f889g, mVar.f889g)) {
            hashSet.add("elevation");
        }
        int i2 = this.f887e;
        int i3 = mVar.f887e;
        if (i2 != i3 && this.f886d == 0 && (i2 == 0 || i3 == 0)) {
            hashSet.add("alpha");
        }
        if (e(this.f890h, mVar.f890h)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.y) || !Float.isNaN(mVar.y)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.z) || !Float.isNaN(mVar.z)) {
            hashSet.add("progress");
        }
        if (e(this.f891i, mVar.f891i)) {
            hashSet.add("rotationX");
        }
        if (e(this.j, mVar.j)) {
            hashSet.add("rotationY");
        }
        if (e(this.m, mVar.m)) {
            hashSet.add("transformPivotX");
        }
        if (e(this.n, mVar.n)) {
            hashSet.add("transformPivotY");
        }
        if (e(this.k, mVar.k)) {
            hashSet.add("scaleX");
        }
        if (e(this.l, mVar.l)) {
            hashSet.add("scaleY");
        }
        if (e(this.o, mVar.o)) {
            hashSet.add("translationX");
        }
        if (e(this.p, mVar.p)) {
            hashSet.add("translationY");
        }
        if (e(this.q, mVar.q)) {
            hashSet.add("translationZ");
        }
    }

    void g(float f2, float f3, float f4, float f5) {
        this.u = f2;
        this.v = f3;
        this.w = f4;
        this.x = f5;
    }

    public void h(View view) {
        g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        b(view);
    }

    public void i(b.e.b.k.e eVar, androidx.constraintlayout.widget.c cVar, int i2) {
        g(eVar.R(), eVar.S(), eVar.Q(), eVar.w());
        c(cVar.q(i2));
    }
}
