package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.util.SparseArray;
import android.view.Display;
import java.util.List;
import java.util.WeakHashMap;
import org.chromium.ui.display.DisplayAndroidManager;

/* JADX INFO: compiled from: DisplayAndroid.java */
/* JADX INFO: loaded from: classes3.dex */
public class ya0 {
    public static final a[] o = new a[0];
    public final int b;
    public float d;
    public float e;
    public float f;
    public int g;
    public int h;
    public int i;
    public float j;
    public Display.Mode k;
    public List<Display.Mode> l;
    public boolean m;
    public boolean n;
    public final WeakHashMap<a, Object> a = new WeakHashMap<>();
    public Point c = new Point();

    public ya0(int i) {
        this.b = i;
    }

    public static ya0 a(Context context) {
        Display displayB = DisplayAndroidManager.b(context);
        DisplayAndroidManager displayAndroidManagerC = DisplayAndroidManager.c();
        displayAndroidManagerC.getClass();
        int displayId = displayB.getDisplayId();
        SparseArray<ya0> sparseArray = displayAndroidManagerC.c;
        ya0 ya0Var = sparseArray.get(displayId);
        if (ya0Var != null) {
            return ya0Var;
        }
        int displayId2 = displayB.getDisplayId();
        z02 z02Var = new z02(displayB);
        sparseArray.put(displayId2, z02Var);
        z02Var.g(displayB);
        return z02Var;
    }

    public final a[] b() {
        return (a[]) this.a.keySet().toArray(o);
    }

    public Context c() {
        return null;
    }

    @SuppressLint({"NewApi"})
    public final void d(Point point, Float f, Float f2, Float f3, Integer num, Integer num2, Integer num3, Boolean bool, Boolean bool2, Float f4, Display.Mode mode, List<Display.Mode> list) {
        List<Display.Mode> list2;
        boolean z = (point == null || this.c.equals(point)) ? false : true;
        boolean z2 = (f == null || this.d == f.floatValue()) ? false : true;
        boolean z3 = (f2 == null || this.e == f2.floatValue()) ? false : true;
        boolean z4 = (f3 == null || this.f == f3.floatValue()) ? false : true;
        boolean z5 = (num == null || this.g == num.intValue()) ? false : true;
        boolean z6 = (num2 == null || this.h == num2.intValue()) ? false : true;
        boolean z7 = (num3 == null || this.i == num3.intValue()) ? false : true;
        boolean z8 = (bool == null || this.m == bool.booleanValue()) ? false : true;
        boolean z9 = (bool2 == null || this.n == bool2.booleanValue()) ? false : true;
        boolean z10 = (f4 == null || this.j == f4.floatValue()) ? false : true;
        boolean z11 = list != null && ((list2 = this.l) == null || list2.equals(list));
        boolean z12 = (mode == null || mode.equals(this.k)) ? false : true;
        if (z || z2 || z5 || z6 || z7 || z8 || z9 || z10 || z11 || z12) {
            if (z) {
                this.c = point;
            }
            if (z2) {
                this.d = f.floatValue();
            }
            if (z3) {
                this.e = f2.floatValue();
            }
            if (z4) {
                this.f = f3.floatValue();
            }
            if (z5) {
                this.g = num.intValue();
            }
            if (z6) {
                this.h = num2.intValue();
            }
            if (z7) {
                this.i = num3.intValue();
            }
            if (z8) {
                this.m = bool.booleanValue();
            }
            if (z9) {
                this.n = bool2.booleanValue();
            }
            if (z10) {
                this.j = f4.floatValue();
            }
            if (z11) {
                this.l = list;
            }
            if (z12) {
                this.k = mode;
            }
            DisplayAndroidManager.c().d(this);
            if (z7) {
                for (a aVar : b()) {
                    aVar.w(this.i);
                }
            }
            if (z2) {
                for (a aVar2 : b()) {
                    aVar2.t(this.d);
                }
            }
            if (z10) {
                for (a aVar3 : b()) {
                    aVar3.L(this.j);
                }
            }
            if (z11) {
                for (a aVar4 : b()) {
                    aVar4.G();
                }
            }
            if (z12) {
                for (a aVar5 : b()) {
                    aVar5.c();
                }
            }
        }
    }

    /* JADX INFO: compiled from: DisplayAndroid.java */
    public interface a {
        default void G() {
        }

        default void c() {
        }

        default void L(float f) {
        }

        default void t(float f) {
        }

        default void w(int i) {
        }
    }
}
