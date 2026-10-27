package x;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import b0.b;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import x.r;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
public class m implements Comparable<m> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String[] f8890d = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8893g;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public w.c f8906t;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f8908v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f8909w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f8910x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f8911y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f8912z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f8891e = 1.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8892f = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8894h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f8895i = 0.0f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f8896j = 0.0f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8897k = 0.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f8898l = 0.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f8899m = 1.0f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f8900n = 1.0f;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f8901o = Float.NaN;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f8902p = Float.NaN;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f8903q = 0.0f;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f8904r = 0.0f;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f8905s = 0.0f;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f8907u = 0;
    public float A = Float.NaN;
    public float B = Float.NaN;
    public LinkedHashMap<String, ConstraintAttribute> C = new LinkedHashMap<>();
    public int D = 0;
    public double[] E = new double[18];
    public double[] F = new double[18];

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void a(HashMap<String, r> map, int i8) {
        for (String str : map.keySet()) {
            r rVar = map.get(str);
            str.hashCode();
            byte b8 = -1;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        b8 = 0;
                    }
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        b8 = 1;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        b8 = 2;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        b8 = 3;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        b8 = 4;
                    }
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        b8 = 5;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        b8 = 6;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        b8 = 7;
                    }
                    break;
                case -760884510:
                    if (str.equals("transformPivotX")) {
                        b8 = 8;
                    }
                    break;
                case -760884509:
                    if (str.equals("transformPivotY")) {
                        b8 = 9;
                    }
                    break;
                case -40300674:
                    if (str.equals("rotation")) {
                        b8 = 10;
                    }
                    break;
                case -4379043:
                    if (str.equals("elevation")) {
                        b8 = 11;
                    }
                    break;
                case 37232917:
                    if (str.equals("transitionPathRotate")) {
                        b8 = 12;
                    }
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        b8 = 13;
                    }
                    break;
            }
            switch (b8) {
                case 0:
                    rVar.e(i8, Float.isNaN(this.f8897k) ? 0.0f : this.f8897k);
                    break;
                case 1:
                    rVar.e(i8, Float.isNaN(this.f8898l) ? 0.0f : this.f8898l);
                    break;
                case 2:
                    rVar.e(i8, Float.isNaN(this.f8903q) ? 0.0f : this.f8903q);
                    break;
                case 3:
                    rVar.e(i8, Float.isNaN(this.f8904r) ? 0.0f : this.f8904r);
                    break;
                case 4:
                    rVar.e(i8, Float.isNaN(this.f8905s) ? 0.0f : this.f8905s);
                    break;
                case 5:
                    rVar.e(i8, Float.isNaN(this.B) ? 0.0f : this.B);
                    break;
                case 6:
                    rVar.e(i8, Float.isNaN(this.f8899m) ? 1.0f : this.f8899m);
                    break;
                case 7:
                    rVar.e(i8, Float.isNaN(this.f8900n) ? 1.0f : this.f8900n);
                    break;
                case 8:
                    rVar.e(i8, Float.isNaN(this.f8901o) ? 0.0f : this.f8901o);
                    break;
                case 9:
                    rVar.e(i8, Float.isNaN(this.f8902p) ? 0.0f : this.f8902p);
                    break;
                case 10:
                    rVar.e(i8, Float.isNaN(this.f8896j) ? 0.0f : this.f8896j);
                    break;
                case 11:
                    rVar.e(i8, Float.isNaN(this.f8895i) ? 0.0f : this.f8895i);
                    break;
                case 12:
                    rVar.e(i8, Float.isNaN(this.A) ? 0.0f : this.A);
                    break;
                case 13:
                    rVar.e(i8, Float.isNaN(this.f8891e) ? 1.0f : this.f8891e);
                    break;
                default:
                    if (str.startsWith("CUSTOM")) {
                        String str2 = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
                        if (this.C.containsKey(str2)) {
                            ConstraintAttribute constraintAttribute = this.C.get(str2);
                            if (rVar instanceof r.b) {
                                ((r.b) rVar).i(i8, constraintAttribute);
                            } else {
                                Log.e("MotionPaths", str + " splineSet not a CustomSet frame = " + i8 + ", value" + constraintAttribute.d() + rVar);
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
        this.f8893g = view.getVisibility();
        this.f8891e = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.f8894h = false;
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 21) {
            this.f8895i = view.getElevation();
        }
        this.f8896j = view.getRotation();
        this.f8897k = view.getRotationX();
        this.f8898l = view.getRotationY();
        this.f8899m = view.getScaleX();
        this.f8900n = view.getScaleY();
        this.f8901o = view.getPivotX();
        this.f8902p = view.getPivotY();
        this.f8903q = view.getTranslationX();
        this.f8904r = view.getTranslationY();
        if (i8 >= 21) {
            this.f8905s = view.getTranslationZ();
        }
    }

    public void c(b.a aVar) {
        b.d dVar = aVar.f1943b;
        int i8 = dVar.f1995c;
        this.f8892f = i8;
        int i9 = dVar.f1994b;
        this.f8893g = i9;
        this.f8891e = (i9 == 0 || i8 != 0) ? dVar.f1996d : 0.0f;
        b.e eVar = aVar.f1946e;
        this.f8894h = eVar.f2010m;
        this.f8895i = eVar.f2011n;
        this.f8896j = eVar.f2000c;
        this.f8897k = eVar.f2001d;
        this.f8898l = eVar.f2002e;
        this.f8899m = eVar.f2003f;
        this.f8900n = eVar.f2004g;
        this.f8901o = eVar.f2005h;
        this.f8902p = eVar.f2006i;
        this.f8903q = eVar.f2007j;
        this.f8904r = eVar.f2008k;
        this.f8905s = eVar.f2009l;
        this.f8906t = w.c.c(aVar.f1944c.f1988d);
        b.c cVar = aVar.f1944c;
        this.A = cVar.f1992h;
        this.f8907u = cVar.f1990f;
        this.B = aVar.f1943b.f1997e;
        for (String str : aVar.f1947f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f1947f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.C.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(m mVar) {
        return Float.compare(this.f8908v, mVar.f8908v);
    }

    public final boolean e(float f8, float f9) {
        return (Float.isNaN(f8) || Float.isNaN(f9)) ? Float.isNaN(f8) != Float.isNaN(f9) : Math.abs(f8 - f9) > 1.0E-6f;
    }

    public void f(m mVar, HashSet<String> hashSet) {
        if (e(this.f8891e, mVar.f8891e)) {
            hashSet.add("alpha");
        }
        if (e(this.f8895i, mVar.f8895i)) {
            hashSet.add("elevation");
        }
        int i8 = this.f8893g;
        int i9 = mVar.f8893g;
        if (i8 != i9 && this.f8892f == 0 && (i8 == 0 || i9 == 0)) {
            hashSet.add("alpha");
        }
        if (e(this.f8896j, mVar.f8896j)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.A) || !Float.isNaN(mVar.A)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.B) || !Float.isNaN(mVar.B)) {
            hashSet.add("progress");
        }
        if (e(this.f8897k, mVar.f8897k)) {
            hashSet.add("rotationX");
        }
        if (e(this.f8898l, mVar.f8898l)) {
            hashSet.add("rotationY");
        }
        if (e(this.f8901o, mVar.f8901o)) {
            hashSet.add("transformPivotX");
        }
        if (e(this.f8902p, mVar.f8902p)) {
            hashSet.add("transformPivotY");
        }
        if (e(this.f8899m, mVar.f8899m)) {
            hashSet.add("scaleX");
        }
        if (e(this.f8900n, mVar.f8900n)) {
            hashSet.add("scaleY");
        }
        if (e(this.f8903q, mVar.f8903q)) {
            hashSet.add("translationX");
        }
        if (e(this.f8904r, mVar.f8904r)) {
            hashSet.add("translationY");
        }
        if (e(this.f8905s, mVar.f8905s)) {
            hashSet.add("translationZ");
        }
    }

    public void g(float f8, float f9, float f10, float f11) {
        this.f8909w = f8;
        this.f8910x = f9;
        this.f8911y = f10;
        this.f8912z = f11;
    }

    public void h(View view) {
        g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        b(view);
    }

    public void i(ConstraintWidget constraintWidget, b0.b bVar, int i8) {
        g(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        c(bVar.q(i8));
    }
}
