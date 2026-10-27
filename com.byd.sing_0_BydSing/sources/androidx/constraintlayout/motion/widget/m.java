package androidx.constraintlayout.motion.widget;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.r;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
class m implements Comparable<m> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String[] f1101b = {"position", "x", "y", "width", "height", "pathRotate"};
    int e;
    private c.e.a.a.c r;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f1102c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1103d = 0;
    private boolean f = false;
    private float g = 0.0f;
    private float h = 0.0f;
    private float i = 0.0f;
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
    LinkedHashMap<String, ConstraintAttribute> A = new LinkedHashMap<>();
    int B = 0;
    double[] C = new double[18];
    double[] D = new double[18];

    private boolean e(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void a(HashMap<String, r> map, int i) {
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
                    rVar.e(i, Float.isNaN(this.i) ? 0.0f : this.i);
                    break;
                case 1:
                    rVar.e(i, Float.isNaN(this.j) ? 0.0f : this.j);
                    break;
                case 2:
                    rVar.e(i, Float.isNaN(this.o) ? 0.0f : this.o);
                    break;
                case 3:
                    rVar.e(i, Float.isNaN(this.p) ? 0.0f : this.p);
                    break;
                case 4:
                    rVar.e(i, Float.isNaN(this.q) ? 0.0f : this.q);
                    break;
                case 5:
                    rVar.e(i, Float.isNaN(this.z) ? 0.0f : this.z);
                    break;
                case 6:
                    rVar.e(i, Float.isNaN(this.k) ? 1.0f : this.k);
                    break;
                case 7:
                    rVar.e(i, Float.isNaN(this.l) ? 1.0f : this.l);
                    break;
                case 8:
                    rVar.e(i, Float.isNaN(this.m) ? 0.0f : this.m);
                    break;
                case 9:
                    rVar.e(i, Float.isNaN(this.n) ? 0.0f : this.n);
                    break;
                case 10:
                    rVar.e(i, Float.isNaN(this.h) ? 0.0f : this.h);
                    break;
                case 11:
                    rVar.e(i, Float.isNaN(this.g) ? 0.0f : this.g);
                    break;
                case 12:
                    rVar.e(i, Float.isNaN(this.y) ? 0.0f : this.y);
                    break;
                case 13:
                    rVar.e(i, Float.isNaN(this.f1102c) ? 1.0f : this.f1102c);
                    break;
                default:
                    if (str.startsWith("CUSTOM")) {
                        String str2 = str.split(",")[1];
                        if (this.A.containsKey(str2)) {
                            ConstraintAttribute constraintAttribute = this.A.get(str2);
                            if (rVar instanceof r.b) {
                                ((r.b) rVar).i(i, constraintAttribute);
                            } else {
                                Log.e("MotionPaths", str + " splineSet not a CustomSet frame = " + i + ", value" + constraintAttribute.d() + rVar);
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
        this.e = view.getVisibility();
        this.f1102c = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.f = false;
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            this.g = view.getElevation();
        }
        this.h = view.getRotation();
        this.i = view.getRotationX();
        this.j = view.getRotationY();
        this.k = view.getScaleX();
        this.l = view.getScaleY();
        this.m = view.getPivotX();
        this.n = view.getPivotY();
        this.o = view.getTranslationX();
        this.p = view.getTranslationY();
        if (i >= 21) {
            this.q = view.getTranslationZ();
        }
    }

    public void c(b.a aVar) {
        b.d dVar = aVar.f1276b;
        int i = dVar.f1289c;
        this.f1103d = i;
        int i2 = dVar.f1288b;
        this.e = i2;
        this.f1102c = (i2 == 0 || i != 0) ? dVar.f1290d : 0.0f;
        b.e eVar = aVar.e;
        this.f = eVar.m;
        this.g = eVar.n;
        this.h = eVar.f1293c;
        this.i = eVar.f1294d;
        this.j = eVar.e;
        this.k = eVar.f;
        this.l = eVar.g;
        this.m = eVar.h;
        this.n = eVar.i;
        this.o = eVar.j;
        this.p = eVar.k;
        this.q = eVar.l;
        this.r = c.e.a.a.c.c(aVar.f1277c.f1286d);
        b.c cVar = aVar.f1277c;
        this.y = cVar.h;
        this.s = cVar.f;
        this.z = aVar.f1276b.e;
        for (String str : aVar.f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f.get(str);
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

    void f(m mVar, HashSet<String> hashSet) {
        if (e(this.f1102c, mVar.f1102c)) {
            hashSet.add("alpha");
        }
        if (e(this.g, mVar.g)) {
            hashSet.add("elevation");
        }
        int i = this.e;
        int i2 = mVar.e;
        if (i != i2 && this.f1103d == 0 && (i == 0 || i2 == 0)) {
            hashSet.add("alpha");
        }
        if (e(this.h, mVar.h)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.y) || !Float.isNaN(mVar.y)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.z) || !Float.isNaN(mVar.z)) {
            hashSet.add("progress");
        }
        if (e(this.i, mVar.i)) {
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

    void g(float f, float f2, float f3, float f4) {
        this.u = f;
        this.v = f2;
        this.w = f3;
        this.x = f4;
    }

    public void h(View view) {
        g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        b(view);
    }

    public void i(ConstraintWidget constraintWidget, androidx.constraintlayout.widget.b bVar, int i) {
        g(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        c(bVar.q(i));
    }
}
