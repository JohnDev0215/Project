package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.d;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import t.d;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
class l implements Comparable<l> {
    static String[] I = {"position", "x", "y", "width", "height", "pathRotate"};
    private float A;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f1452h;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private p.c f1465u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private float f1467w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f1468x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private float f1469y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private float f1470z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f1450e = 1.0f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f1451g = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1453i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private float f1454j = 0.0f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float f1455k = 0.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private float f1456l = 0.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f1457m = 0.0f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private float f1458n = 1.0f;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private float f1459o = 1.0f;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private float f1460p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f1461q = Float.NaN;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f1462r = 0.0f;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f1463s = 0.0f;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private float f1464t = 0.0f;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f1466v = 0;
    private float B = Float.NaN;
    private float C = Float.NaN;
    private int D = -1;
    LinkedHashMap<String, androidx.constraintlayout.widget.a> E = new LinkedHashMap<>();
    int F = 0;
    double[] G = new double[18];
    double[] H = new double[18];

    private boolean e(float f7, float f8) {
        return (Float.isNaN(f7) || Float.isNaN(f8)) ? Float.isNaN(f7) != Float.isNaN(f8) : Math.abs(f7 - f8) > 1.0E-6f;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void a(HashMap<String, t.d> map, int i7) {
        for (String str : map.keySet()) {
            t.d dVar = map.get(str);
            str.hashCode();
            byte b7 = -1;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        b7 = 0;
                    }
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        b7 = 1;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        b7 = 2;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        b7 = 3;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        b7 = 4;
                    }
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        b7 = 5;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        b7 = 6;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        b7 = 7;
                    }
                    break;
                case -760884510:
                    if (str.equals("transformPivotX")) {
                        b7 = 8;
                    }
                    break;
                case -760884509:
                    if (str.equals("transformPivotY")) {
                        b7 = 9;
                    }
                    break;
                case -40300674:
                    if (str.equals("rotation")) {
                        b7 = 10;
                    }
                    break;
                case -4379043:
                    if (str.equals("elevation")) {
                        b7 = 11;
                    }
                    break;
                case 37232917:
                    if (str.equals("transitionPathRotate")) {
                        b7 = 12;
                    }
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        b7 = 13;
                    }
                    break;
            }
            switch (b7) {
                case 0:
                    dVar.b(i7, Float.isNaN(this.f1456l) ? 0.0f : this.f1456l);
                    break;
                case 1:
                    dVar.b(i7, Float.isNaN(this.f1457m) ? 0.0f : this.f1457m);
                    break;
                case 2:
                    dVar.b(i7, Float.isNaN(this.f1462r) ? 0.0f : this.f1462r);
                    break;
                case 3:
                    dVar.b(i7, Float.isNaN(this.f1463s) ? 0.0f : this.f1463s);
                    break;
                case 4:
                    dVar.b(i7, Float.isNaN(this.f1464t) ? 0.0f : this.f1464t);
                    break;
                case 5:
                    dVar.b(i7, Float.isNaN(this.C) ? 0.0f : this.C);
                    break;
                case 6:
                    dVar.b(i7, Float.isNaN(this.f1458n) ? 1.0f : this.f1458n);
                    break;
                case 7:
                    dVar.b(i7, Float.isNaN(this.f1459o) ? 1.0f : this.f1459o);
                    break;
                case 8:
                    dVar.b(i7, Float.isNaN(this.f1460p) ? 0.0f : this.f1460p);
                    break;
                case 9:
                    dVar.b(i7, Float.isNaN(this.f1461q) ? 0.0f : this.f1461q);
                    break;
                case 10:
                    dVar.b(i7, Float.isNaN(this.f1455k) ? 0.0f : this.f1455k);
                    break;
                case 11:
                    dVar.b(i7, Float.isNaN(this.f1454j) ? 0.0f : this.f1454j);
                    break;
                case 12:
                    dVar.b(i7, Float.isNaN(this.B) ? 0.0f : this.B);
                    break;
                case 13:
                    dVar.b(i7, Float.isNaN(this.f1450e) ? 1.0f : this.f1450e);
                    break;
                default:
                    if (str.startsWith("CUSTOM")) {
                        String str2 = str.split(",")[1];
                        if (this.E.containsKey(str2)) {
                            androidx.constraintlayout.widget.a aVar = this.E.get(str2);
                            if (dVar instanceof d.b) {
                                ((d.b) dVar).h(i7, aVar);
                            } else {
                                Log.e("MotionPaths", str + " ViewSpline not a CustomSet frame = " + i7 + ", value" + aVar.e() + dVar);
                            }
                        }
                    } else {
                        Log.e("MotionPaths", "UNKNOWN spline " + str);
                    }
                    break;
            }
        }
    }

    public void b(View view) {
        this.f1452h = view.getVisibility();
        this.f1450e = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.f1453i = false;
        this.f1454j = view.getElevation();
        this.f1455k = view.getRotation();
        this.f1456l = view.getRotationX();
        this.f1457m = view.getRotationY();
        this.f1458n = view.getScaleX();
        this.f1459o = view.getScaleY();
        this.f1460p = view.getPivotX();
        this.f1461q = view.getPivotY();
        this.f1462r = view.getTranslationX();
        this.f1463s = view.getTranslationY();
        this.f1464t = view.getTranslationZ();
    }

    public void c(d.a aVar) {
        d.C0018d c0018d = aVar.f1777c;
        int i7 = c0018d.f1856c;
        this.f1451g = i7;
        int i8 = c0018d.f1855b;
        this.f1452h = i8;
        this.f1450e = (i8 == 0 || i7 != 0) ? c0018d.f1857d : 0.0f;
        d.e eVar = aVar.f1780f;
        this.f1453i = eVar.f1872m;
        this.f1454j = eVar.f1873n;
        this.f1455k = eVar.f1861b;
        this.f1456l = eVar.f1862c;
        this.f1457m = eVar.f1863d;
        this.f1458n = eVar.f1864e;
        this.f1459o = eVar.f1865f;
        this.f1460p = eVar.f1866g;
        this.f1461q = eVar.f1867h;
        this.f1462r = eVar.f1869j;
        this.f1463s = eVar.f1870k;
        this.f1464t = eVar.f1871l;
        this.f1465u = p.c.c(aVar.f1778d.f1843d);
        d.c cVar = aVar.f1778d;
        this.B = cVar.f1848i;
        this.f1466v = cVar.f1845f;
        this.D = cVar.f1841b;
        this.C = aVar.f1777c.f1858e;
        for (String str : aVar.f1781g.keySet()) {
            androidx.constraintlayout.widget.a aVar2 = aVar.f1781g.get(str);
            if (aVar2.g()) {
                this.E.put(str, aVar2);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(l lVar) {
        return Float.compare(this.f1467w, lVar.f1467w);
    }

    void f(l lVar, HashSet<String> hashSet) {
        if (e(this.f1450e, lVar.f1450e)) {
            hashSet.add("alpha");
        }
        if (e(this.f1454j, lVar.f1454j)) {
            hashSet.add("elevation");
        }
        int i7 = this.f1452h;
        int i8 = lVar.f1452h;
        if (i7 != i8 && this.f1451g == 0 && (i7 == 0 || i8 == 0)) {
            hashSet.add("alpha");
        }
        if (e(this.f1455k, lVar.f1455k)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.B) || !Float.isNaN(lVar.B)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.C) || !Float.isNaN(lVar.C)) {
            hashSet.add("progress");
        }
        if (e(this.f1456l, lVar.f1456l)) {
            hashSet.add("rotationX");
        }
        if (e(this.f1457m, lVar.f1457m)) {
            hashSet.add("rotationY");
        }
        if (e(this.f1460p, lVar.f1460p)) {
            hashSet.add("transformPivotX");
        }
        if (e(this.f1461q, lVar.f1461q)) {
            hashSet.add("transformPivotY");
        }
        if (e(this.f1458n, lVar.f1458n)) {
            hashSet.add("scaleX");
        }
        if (e(this.f1459o, lVar.f1459o)) {
            hashSet.add("scaleY");
        }
        if (e(this.f1462r, lVar.f1462r)) {
            hashSet.add("translationX");
        }
        if (e(this.f1463s, lVar.f1463s)) {
            hashSet.add("translationY");
        }
        if (e(this.f1464t, lVar.f1464t)) {
            hashSet.add("translationZ");
        }
    }

    void g(float f7, float f8, float f9, float f10) {
        this.f1468x = f7;
        this.f1469y = f8;
        this.f1470z = f9;
        this.A = f10;
    }

    public void h(Rect rect, View view, int i7, float f7) {
        g(rect.left, rect.top, rect.width(), rect.height());
        b(view);
        this.f1460p = Float.NaN;
        this.f1461q = Float.NaN;
        if (i7 == 1) {
            this.f1455k = f7 - 90.0f;
        } else {
            if (i7 != 2) {
                return;
            }
            this.f1455k = f7 + 90.0f;
        }
    }

    public void i(Rect rect, androidx.constraintlayout.widget.d dVar, int i7, int i8) {
        g(rect.left, rect.top, rect.width(), rect.height());
        c(dVar.z(i8));
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 != 3) {
                    if (i7 != 4) {
                        return;
                    }
                }
            }
            float f7 = this.f1455k + 90.0f;
            this.f1455k = f7;
            if (f7 > 180.0f) {
                this.f1455k = f7 - 360.0f;
                return;
            }
            return;
        }
        this.f1455k -= 90.0f;
    }

    public void j(View view) {
        g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        b(view);
    }
}
