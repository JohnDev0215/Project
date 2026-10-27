package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.b;
import b.e.a.a.d;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
class m implements Comparable<m> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String[] f937b = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f940e;
    private androidx.constraintlayout.core.j.a.c r;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f938c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f939d = 0;
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
    private int A = -1;
    LinkedHashMap<String, ConstraintAttribute> B = new LinkedHashMap<>();
    int C = 0;
    double[] D = new double[18];
    double[] E = new double[18];

    private boolean h(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void b(HashMap<String, b.e.a.a.d> map, int i) {
        for (String str : map.keySet()) {
            b.e.a.a.d dVar = map.get(str);
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
                    dVar.c(i, Float.isNaN(this.i) ? 0.0f : this.i);
                    break;
                case 1:
                    dVar.c(i, Float.isNaN(this.j) ? 0.0f : this.j);
                    break;
                case 2:
                    dVar.c(i, Float.isNaN(this.o) ? 0.0f : this.o);
                    break;
                case 3:
                    dVar.c(i, Float.isNaN(this.p) ? 0.0f : this.p);
                    break;
                case 4:
                    dVar.c(i, Float.isNaN(this.q) ? 0.0f : this.q);
                    break;
                case 5:
                    dVar.c(i, Float.isNaN(this.z) ? 0.0f : this.z);
                    break;
                case 6:
                    dVar.c(i, Float.isNaN(this.k) ? 1.0f : this.k);
                    break;
                case 7:
                    dVar.c(i, Float.isNaN(this.l) ? 1.0f : this.l);
                    break;
                case 8:
                    dVar.c(i, Float.isNaN(this.m) ? 0.0f : this.m);
                    break;
                case 9:
                    dVar.c(i, Float.isNaN(this.n) ? 0.0f : this.n);
                    break;
                case 10:
                    dVar.c(i, Float.isNaN(this.h) ? 0.0f : this.h);
                    break;
                case 11:
                    dVar.c(i, Float.isNaN(this.g) ? 0.0f : this.g);
                    break;
                case 12:
                    dVar.c(i, Float.isNaN(this.y) ? 0.0f : this.y);
                    break;
                case 13:
                    dVar.c(i, Float.isNaN(this.f938c) ? 1.0f : this.f938c);
                    break;
                default:
                    if (str.startsWith("CUSTOM")) {
                        String str2 = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
                        if (this.B.containsKey(str2)) {
                            ConstraintAttribute constraintAttribute = this.B.get(str2);
                            if (dVar instanceof d.b) {
                                ((d.b) dVar).i(i, constraintAttribute);
                            } else {
                                Log.e("MotionPaths", str + " ViewSpline not a CustomSet frame = " + i + ", value" + constraintAttribute.e() + dVar);
                            }
                        }
                    } else {
                        Log.e("MotionPaths", "UNKNOWN spline " + str);
                    }
                    break;
            }
        }
    }

    public void d(View view) {
        this.f940e = view.getVisibility();
        this.f938c = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
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

    public void e(b.a aVar) {
        b.d dVar = aVar.f1077c;
        int i = dVar.f1097c;
        this.f939d = i;
        int i2 = dVar.f1096b;
        this.f940e = i2;
        this.f938c = (i2 == 0 || i != 0) ? dVar.f1098d : 0.0f;
        b.e eVar = aVar.f;
        this.f = eVar.n;
        this.g = eVar.o;
        this.h = eVar.f1102c;
        this.i = eVar.f1103d;
        this.j = eVar.f1104e;
        this.k = eVar.f;
        this.l = eVar.g;
        this.m = eVar.h;
        this.n = eVar.i;
        this.o = eVar.k;
        this.p = eVar.l;
        this.q = eVar.m;
        this.r = androidx.constraintlayout.core.j.a.c.c(aVar.f1078d.f1094e);
        b.c cVar = aVar.f1078d;
        this.y = cVar.j;
        this.s = cVar.g;
        this.A = cVar.f1092c;
        this.z = aVar.f1077c.f1099e;
        for (String str : aVar.g.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.g.get(str);
            if (constraintAttribute.g()) {
                this.B.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public int compareTo(m mVar) {
        return Float.compare(this.t, mVar.t);
    }

    void i(m mVar, HashSet<String> hashSet) {
        if (h(this.f938c, mVar.f938c)) {
            hashSet.add("alpha");
        }
        if (h(this.g, mVar.g)) {
            hashSet.add("elevation");
        }
        int i = this.f940e;
        int i2 = mVar.f940e;
        if (i != i2 && this.f939d == 0 && (i == 0 || i2 == 0)) {
            hashSet.add("alpha");
        }
        if (h(this.h, mVar.h)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.y) || !Float.isNaN(mVar.y)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.z) || !Float.isNaN(mVar.z)) {
            hashSet.add("progress");
        }
        if (h(this.i, mVar.i)) {
            hashSet.add("rotationX");
        }
        if (h(this.j, mVar.j)) {
            hashSet.add("rotationY");
        }
        if (h(this.m, mVar.m)) {
            hashSet.add("transformPivotX");
        }
        if (h(this.n, mVar.n)) {
            hashSet.add("transformPivotY");
        }
        if (h(this.k, mVar.k)) {
            hashSet.add("scaleX");
        }
        if (h(this.l, mVar.l)) {
            hashSet.add("scaleY");
        }
        if (h(this.o, mVar.o)) {
            hashSet.add("translationX");
        }
        if (h(this.p, mVar.p)) {
            hashSet.add("translationY");
        }
        if (h(this.q, mVar.q)) {
            hashSet.add("translationZ");
        }
    }

    void j(float f, float f2, float f3, float f4) {
        this.u = f;
        this.v = f2;
        this.w = f3;
        this.x = f4;
    }

    public void k(Rect rect, View view, int i, float f) {
        j(rect.left, rect.top, rect.width(), rect.height());
        d(view);
        this.m = Float.NaN;
        this.n = Float.NaN;
        if (i == 1) {
            this.h = f - 90.0f;
        } else {
            if (i != 2) {
                return;
            }
            this.h = f + 90.0f;
        }
    }

    public void l(Rect rect, androidx.constraintlayout.widget.b bVar, int i, int i2) {
        j(rect.left, rect.top, rect.width(), rect.height());
        e(bVar.x(i2));
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return;
                    }
                }
            }
            float f = this.h + 90.0f;
            this.h = f;
            if (f > 180.0f) {
                this.h = f - 360.0f;
                return;
            }
            return;
        }
        this.h -= 90.0f;
    }

    public void m(View view) {
        j(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        d(view);
    }
}
