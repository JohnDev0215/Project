package b.e.a.b;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import b.e.a.b.r;
import b.e.c.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
public class m implements Comparable<m> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String[] f1292c = {"position", "x", "y", "width", "height", "pathRotate"};
    public b.e.a.a.c P3;
    public float R3;
    public float S3;
    public float T3;
    public float U3;
    public float V3;
    public int x;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1293d = 1.0f;
    public int s = 0;
    public boolean y = false;
    public float E3 = 0.0f;
    public float F3 = 0.0f;
    public float G3 = 0.0f;
    public float H3 = 0.0f;
    public float I3 = 1.0f;
    public float J3 = 1.0f;
    public float K3 = Float.NaN;
    public float L3 = Float.NaN;
    public float M3 = 0.0f;
    public float N3 = 0.0f;
    public float O3 = 0.0f;
    public int Q3 = 0;
    public float W3 = Float.NaN;
    public float X3 = Float.NaN;
    public LinkedHashMap<String, ConstraintAttribute> Y3 = new LinkedHashMap<>();
    public int Z3 = 0;
    public double[] a4 = new double[18];
    public double[] b4 = new double[18];

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
                    rVar.e(i2, Float.isNaN(this.G3) ? 0.0f : this.G3);
                    break;
                case 1:
                    rVar.e(i2, Float.isNaN(this.H3) ? 0.0f : this.H3);
                    break;
                case 2:
                    rVar.e(i2, Float.isNaN(this.M3) ? 0.0f : this.M3);
                    break;
                case 3:
                    rVar.e(i2, Float.isNaN(this.N3) ? 0.0f : this.N3);
                    break;
                case 4:
                    rVar.e(i2, Float.isNaN(this.O3) ? 0.0f : this.O3);
                    break;
                case 5:
                    rVar.e(i2, Float.isNaN(this.X3) ? 0.0f : this.X3);
                    break;
                case 6:
                    rVar.e(i2, Float.isNaN(this.I3) ? 1.0f : this.I3);
                    break;
                case 7:
                    rVar.e(i2, Float.isNaN(this.J3) ? 1.0f : this.J3);
                    break;
                case 8:
                    rVar.e(i2, Float.isNaN(this.K3) ? 0.0f : this.K3);
                    break;
                case 9:
                    rVar.e(i2, Float.isNaN(this.L3) ? 0.0f : this.L3);
                    break;
                case 10:
                    rVar.e(i2, Float.isNaN(this.F3) ? 0.0f : this.F3);
                    break;
                case 11:
                    rVar.e(i2, Float.isNaN(this.E3) ? 0.0f : this.E3);
                    break;
                case 12:
                    rVar.e(i2, Float.isNaN(this.W3) ? 0.0f : this.W3);
                    break;
                case 13:
                    rVar.e(i2, Float.isNaN(this.f1293d) ? 1.0f : this.f1293d);
                    break;
                default:
                    if (str.startsWith("CUSTOM")) {
                        String str2 = str.split(",")[1];
                        if (this.Y3.containsKey(str2)) {
                            ConstraintAttribute constraintAttribute = this.Y3.get(str2);
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
        this.x = view.getVisibility();
        this.f1293d = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.y = false;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            this.E3 = view.getElevation();
        }
        this.F3 = view.getRotation();
        this.G3 = view.getRotationX();
        this.H3 = view.getRotationY();
        this.I3 = view.getScaleX();
        this.J3 = view.getScaleY();
        this.K3 = view.getPivotX();
        this.L3 = view.getPivotY();
        this.M3 = view.getTranslationX();
        this.N3 = view.getTranslationY();
        if (i2 >= 21) {
            this.O3 = view.getTranslationZ();
        }
    }

    public void c(b.a aVar) {
        b.d dVar = aVar.f1477b;
        int i2 = dVar.f1501c;
        this.s = i2;
        int i3 = dVar.f1500b;
        this.x = i3;
        this.f1293d = (i3 == 0 || i2 != 0) ? dVar.f1502d : 0.0f;
        b.e eVar = aVar.f1480e;
        this.y = eVar.m;
        this.E3 = eVar.n;
        this.F3 = eVar.f1506c;
        this.G3 = eVar.f1507d;
        this.H3 = eVar.f1508e;
        this.I3 = eVar.f1509f;
        this.J3 = eVar.f1510g;
        this.K3 = eVar.f1511h;
        this.L3 = eVar.f1512i;
        this.M3 = eVar.j;
        this.N3 = eVar.k;
        this.O3 = eVar.l;
        this.P3 = b.e.a.a.c.c(aVar.f1478c.f1494d);
        b.c cVar = aVar.f1478c;
        this.W3 = cVar.f1498h;
        this.Q3 = cVar.f1496f;
        this.X3 = aVar.f1477b.f1503e;
        for (String str : aVar.f1481f.keySet()) {
            ConstraintAttribute constraintAttribute = aVar.f1481f.get(str);
            if (constraintAttribute.c() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.Y3.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(m mVar) {
        return Float.compare(this.R3, mVar.R3);
    }

    public final boolean e(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    public void f(m mVar, HashSet<String> hashSet) {
        if (e(this.f1293d, mVar.f1293d)) {
            hashSet.add("alpha");
        }
        if (e(this.E3, mVar.E3)) {
            hashSet.add("elevation");
        }
        int i2 = this.x;
        int i3 = mVar.x;
        if (i2 != i3 && this.s == 0 && (i2 == 0 || i3 == 0)) {
            hashSet.add("alpha");
        }
        if (e(this.F3, mVar.F3)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.W3) || !Float.isNaN(mVar.W3)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.X3) || !Float.isNaN(mVar.X3)) {
            hashSet.add("progress");
        }
        if (e(this.G3, mVar.G3)) {
            hashSet.add("rotationX");
        }
        if (e(this.H3, mVar.H3)) {
            hashSet.add("rotationY");
        }
        if (e(this.K3, mVar.K3)) {
            hashSet.add("transformPivotX");
        }
        if (e(this.L3, mVar.L3)) {
            hashSet.add("transformPivotY");
        }
        if (e(this.I3, mVar.I3)) {
            hashSet.add("scaleX");
        }
        if (e(this.J3, mVar.J3)) {
            hashSet.add("scaleY");
        }
        if (e(this.M3, mVar.M3)) {
            hashSet.add("translationX");
        }
        if (e(this.N3, mVar.N3)) {
            hashSet.add("translationY");
        }
        if (e(this.O3, mVar.O3)) {
            hashSet.add("translationZ");
        }
    }

    public void g(float f2, float f3, float f4, float f5) {
        this.S3 = f2;
        this.T3 = f3;
        this.U3 = f4;
        this.V3 = f5;
    }

    public void h(View view) {
        g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        b(view);
    }

    public void i(ConstraintWidget constraintWidget, b.e.c.b bVar, int i2) {
        g(constraintWidget.R(), constraintWidget.S(), constraintWidget.Q(), constraintWidget.w());
        c(bVar.q(i2));
    }
}
