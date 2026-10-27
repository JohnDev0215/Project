package u;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import u.r;
import y.b;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
public class m implements Comparable<m> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String[] f5176b = {"position", "x", "y", "width", "height", "pathRotate"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5179e;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public t.c f5192r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f5194t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f5195u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f5196v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f5197w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f5198x;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f5177c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5178d = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5180f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f5181g = 0.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f5182h = 0.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f5183i = 0.0f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f5184j = 0.0f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f5185k = 1.0f;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f5186l = 1.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f5187m = Float.NaN;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f5188n = Float.NaN;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f5189o = 0.0f;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f5190p = 0.0f;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f5191q = 0.0f;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f5193s = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f5199y = Float.NaN;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f5200z = Float.NaN;
    public LinkedHashMap<String, ConstraintAttribute> A = new LinkedHashMap<>();
    public int B = 0;
    public double[] C = new double[18];
    public double[] D = new double[18];

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void a(HashMap<String, r> map, int i3) {
        for (String str : map.keySet()) {
            r rVar = map.get(str);
            str.hashCode();
            byte b3 = -1;
            switch (str.hashCode()) {
                case -1249320806:
                    if (str.equals("rotationX")) {
                        b3 = 0;
                    }
                    break;
                case -1249320805:
                    if (str.equals("rotationY")) {
                        b3 = 1;
                    }
                    break;
                case -1225497657:
                    if (str.equals("translationX")) {
                        b3 = 2;
                    }
                    break;
                case -1225497656:
                    if (str.equals("translationY")) {
                        b3 = 3;
                    }
                    break;
                case -1225497655:
                    if (str.equals("translationZ")) {
                        b3 = 4;
                    }
                    break;
                case -1001078227:
                    if (str.equals("progress")) {
                        b3 = 5;
                    }
                    break;
                case -908189618:
                    if (str.equals("scaleX")) {
                        b3 = 6;
                    }
                    break;
                case -908189617:
                    if (str.equals("scaleY")) {
                        b3 = 7;
                    }
                    break;
                case -760884510:
                    if (str.equals("transformPivotX")) {
                        b3 = 8;
                    }
                    break;
                case -760884509:
                    if (str.equals("transformPivotY")) {
                        b3 = 9;
                    }
                    break;
                case -40300674:
                    if (str.equals("rotation")) {
                        b3 = 10;
                    }
                    break;
                case -4379043:
                    if (str.equals("elevation")) {
                        b3 = 11;
                    }
                    break;
                case 37232917:
                    if (str.equals("transitionPathRotate")) {
                        b3 = 12;
                    }
                    break;
                case 92909918:
                    if (str.equals("alpha")) {
                        b3 = 13;
                    }
                    break;
            }
            switch (b3) {
                case 0:
                    rVar.e(i3, Float.isNaN(this.f5183i) ? 0.0f : this.f5183i);
                    break;
                case 1:
                    rVar.e(i3, Float.isNaN(this.f5184j) ? 0.0f : this.f5184j);
                    break;
                case 2:
                    rVar.e(i3, Float.isNaN(this.f5189o) ? 0.0f : this.f5189o);
                    break;
                case 3:
                    rVar.e(i3, Float.isNaN(this.f5190p) ? 0.0f : this.f5190p);
                    break;
                case 4:
                    rVar.e(i3, Float.isNaN(this.f5191q) ? 0.0f : this.f5191q);
                    break;
                case 5:
                    rVar.e(i3, Float.isNaN(this.f5200z) ? 0.0f : this.f5200z);
                    break;
                case 6:
                    rVar.e(i3, Float.isNaN(this.f5185k) ? 1.0f : this.f5185k);
                    break;
                case 7:
                    rVar.e(i3, Float.isNaN(this.f5186l) ? 1.0f : this.f5186l);
                    break;
                case 8:
                    rVar.e(i3, Float.isNaN(this.f5187m) ? 0.0f : this.f5187m);
                    break;
                case 9:
                    rVar.e(i3, Float.isNaN(this.f5188n) ? 0.0f : this.f5188n);
                    break;
                case 10:
                    rVar.e(i3, Float.isNaN(this.f5182h) ? 0.0f : this.f5182h);
                    break;
                case 11:
                    rVar.e(i3, Float.isNaN(this.f5181g) ? 0.0f : this.f5181g);
                    break;
                case 12:
                    rVar.e(i3, Float.isNaN(this.f5199y) ? 0.0f : this.f5199y);
                    break;
                case 13:
                    rVar.e(i3, Float.isNaN(this.f5177c) ? 1.0f : this.f5177c);
                    break;
                default:
                    if (str.startsWith("CUSTOM")) {
                        String str2 = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR)[1];
                        if (this.A.containsKey(str2)) {
                            ConstraintAttribute constraintAttribute = this.A.get(str2);
                            if (rVar instanceof r.b) {
                                ((r.b) rVar).i(i3, constraintAttribute);
                            } else {
                                Log.e("MotionPaths", str + " splineSet not a CustomSet frame = " + i3 + ", value" + constraintAttribute.d() + rVar);
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
        this.f5179e = view.getVisibility();
        this.f5177c = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.f5180f = false;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21) {
            this.f5181g = view.getElevation();
        }
        this.f5182h = view.getRotation();
        this.f5183i = view.getRotationX();
        this.f5184j = view.getRotationY();
        this.f5185k = view.getScaleX();
        this.f5186l = view.getScaleY();
        this.f5187m = view.getPivotX();
        this.f5188n = view.getPivotY();
        this.f5189o = view.getTranslationX();
        this.f5190p = view.getTranslationY();
        if (i3 >= 21) {
            this.f5191q = view.getTranslationZ();
        }
    }

    public void c(b.a aVar) {
        b.d dVar = aVar.f5767b;
        int i3 = dVar.f5819c;
        this.f5178d = i3;
        int i4 = dVar.f5818b;
        this.f5179e = i4;
        this.f5177c = (i4 == 0 || i3 != 0) ? dVar.f5820d : 0.0f;
        b.e eVar = aVar.f5770e;
        this.f5180f = eVar.f5834m;
        this.f5181g = eVar.f5835n;
        this.f5182h = eVar.f5824c;
        this.f5183i = eVar.f5825d;
        this.f5184j = eVar.f5826e;
        this.f5185k = eVar.f5827f;
        this.f5186l = eVar.f5828g;
        this.f5187m = eVar.f5829h;
        this.f5188n = eVar.f5830i;
        this.f5189o = eVar.f5831j;
        this.f5190p = eVar.f5832k;
        this.f5191q = eVar.f5833l;
        this.f5192r = t.c.c(aVar.f5768c.f5812d);
        b.c cVar = aVar.f5768c;
        this.f5199y = cVar.f5816h;
        this.f5193s = cVar.f5814f;
        this.f5200z = aVar.f5767b.f5821e;
        for (String str : aVar.f5771f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f5771f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.A.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(m mVar) {
        return Float.compare(this.f5194t, mVar.f5194t);
    }

    public final boolean e(float f3, float f4) {
        return (Float.isNaN(f3) || Float.isNaN(f4)) ? Float.isNaN(f3) != Float.isNaN(f4) : Math.abs(f3 - f4) > 1.0E-6f;
    }

    public void f(m mVar, HashSet<String> hashSet) {
        if (e(this.f5177c, mVar.f5177c)) {
            hashSet.add("alpha");
        }
        if (e(this.f5181g, mVar.f5181g)) {
            hashSet.add("elevation");
        }
        int i3 = this.f5179e;
        int i4 = mVar.f5179e;
        if (i3 != i4 && this.f5178d == 0 && (i3 == 0 || i4 == 0)) {
            hashSet.add("alpha");
        }
        if (e(this.f5182h, mVar.f5182h)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f5199y) || !Float.isNaN(mVar.f5199y)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f5200z) || !Float.isNaN(mVar.f5200z)) {
            hashSet.add("progress");
        }
        if (e(this.f5183i, mVar.f5183i)) {
            hashSet.add("rotationX");
        }
        if (e(this.f5184j, mVar.f5184j)) {
            hashSet.add("rotationY");
        }
        if (e(this.f5187m, mVar.f5187m)) {
            hashSet.add("transformPivotX");
        }
        if (e(this.f5188n, mVar.f5188n)) {
            hashSet.add("transformPivotY");
        }
        if (e(this.f5185k, mVar.f5185k)) {
            hashSet.add("scaleX");
        }
        if (e(this.f5186l, mVar.f5186l)) {
            hashSet.add("scaleY");
        }
        if (e(this.f5189o, mVar.f5189o)) {
            hashSet.add("translationX");
        }
        if (e(this.f5190p, mVar.f5190p)) {
            hashSet.add("translationY");
        }
        if (e(this.f5191q, mVar.f5191q)) {
            hashSet.add("translationZ");
        }
    }

    public void g(float f3, float f4, float f5, float f6) {
        this.f5195u = f3;
        this.f5196v = f4;
        this.f5197w = f5;
        this.f5198x = f6;
    }

    public void h(View view) {
        g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        b(view);
    }

    public void i(ConstraintWidget constraintWidget, y.b bVar, int i3) {
        g(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        c(bVar.q(i3));
    }
}
