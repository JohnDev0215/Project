package b.f.a.b;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import b.f.a.b.r;
import b.f.c.b;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
public class m implements Comparable<m> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String[] f2090b = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2093e;
    public b.f.a.a.c r;
    public float t;
    public float u;
    public float v;
    public float w;
    public float x;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2091c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2092d = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2094f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f2095g = 0.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f2096h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f2097i = 0.0f;
    public float j = 0.0f;
    public float k = 1.0f;
    public float l = 1.0f;
    public float m = Float.NaN;
    public float n = Float.NaN;
    public float o = 0.0f;
    public float p = 0.0f;
    public float q = 0.0f;
    public int s = 0;
    public float y = Float.NaN;
    public float z = Float.NaN;
    public LinkedHashMap<String, ConstraintAttribute> A = new LinkedHashMap<>();
    public int B = 0;
    public double[] C = new double[18];
    public double[] D = new double[18];

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
                    rVar.e(i2, Float.isNaN(this.f2097i) ? 0.0f : this.f2097i);
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
                    rVar.e(i2, Float.isNaN(this.f2096h) ? 0.0f : this.f2096h);
                    break;
                case 11:
                    rVar.e(i2, Float.isNaN(this.f2095g) ? 0.0f : this.f2095g);
                    break;
                case 12:
                    rVar.e(i2, Float.isNaN(this.y) ? 0.0f : this.y);
                    break;
                case 13:
                    rVar.e(i2, Float.isNaN(this.f2091c) ? 1.0f : this.f2091c);
                    break;
                default:
                    if (str.startsWith("CUSTOM")) {
                        String str2 = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
                        if (this.A.containsKey(str2)) {
                            ConstraintAttribute constraintAttribute = this.A.get(str2);
                            if (rVar instanceof r.b) {
                                ((r.b) rVar).i(i2, constraintAttribute);
                            } else {
                                Log.e("MotionPaths", str + " splineSet not a CustomSet frame = " + i2 + ", value" + constraintAttribute.d() + rVar);
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
        this.f2093e = view.getVisibility();
        this.f2091c = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.f2094f = false;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            this.f2095g = view.getElevation();
        }
        this.f2096h = view.getRotation();
        this.f2097i = view.getRotationX();
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

    public void c(b.a aVar) {
        b.d dVar = aVar.f2289b;
        int i2 = dVar.f2313c;
        this.f2092d = i2;
        int i3 = dVar.f2312b;
        this.f2093e = i3;
        this.f2091c = (i3 == 0 || i2 != 0) ? dVar.f2314d : 0.0f;
        b.e eVar = aVar.f2292e;
        this.f2094f = eVar.m;
        this.f2095g = eVar.n;
        this.f2096h = eVar.f2318c;
        this.f2097i = eVar.f2319d;
        this.j = eVar.f2320e;
        this.k = eVar.f2321f;
        this.l = eVar.f2322g;
        this.m = eVar.f2323h;
        this.n = eVar.f2324i;
        this.o = eVar.j;
        this.p = eVar.k;
        this.q = eVar.l;
        this.r = b.f.a.a.c.c(aVar.f2290c.f2306d);
        b.c cVar = aVar.f2290c;
        this.y = cVar.f2310h;
        this.s = cVar.f2308f;
        this.z = aVar.f2289b.f2315e;
        for (String str : aVar.f2293f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f2293f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.A.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(m mVar) {
        return Float.compare(this.t, mVar.t);
    }

    public final boolean e(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    public void f(m mVar, HashSet<String> hashSet) {
        if (e(this.f2091c, mVar.f2091c)) {
            hashSet.add("alpha");
        }
        if (e(this.f2095g, mVar.f2095g)) {
            hashSet.add("elevation");
        }
        int i2 = this.f2093e;
        int i3 = mVar.f2093e;
        if (i2 != i3 && this.f2092d == 0 && (i2 == 0 || i3 == 0)) {
            hashSet.add("alpha");
        }
        if (e(this.f2096h, mVar.f2096h)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.y) || !Float.isNaN(mVar.y)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.z) || !Float.isNaN(mVar.z)) {
            hashSet.add("progress");
        }
        if (e(this.f2097i, mVar.f2097i)) {
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

    public void g(float f2, float f3, float f4, float f5) {
        this.u = f2;
        this.v = f3;
        this.w = f4;
        this.x = f5;
    }

    public void h(View view) {
        g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        b(view);
    }

    public void i(ConstraintWidget constraintWidget, b.f.c.b bVar, int i2) {
        g(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        c(bVar.q(i2));
    }
}
