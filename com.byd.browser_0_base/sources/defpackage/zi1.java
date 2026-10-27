package defpackage;

import android.graphics.Rect;
import android.view.View;
import androidx.constraintlayout.widget.a;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: MotionConstrainedPoint.java */
/* JADX INFO: loaded from: classes.dex */
public final class zi1 implements Comparable<zi1> {
    public int g;
    public float e = 0.0f;
    public int f = 0;
    public final LinkedHashMap<String, ox> h = new LinkedHashMap<>();
    public float i = 1.0f;
    public float j = 0.0f;
    public float k = 0.0f;
    public float l = 0.0f;
    public float m = 1.0f;
    public float n = 1.0f;
    public float o = Float.NaN;
    public float p = Float.NaN;
    public float q = 0.0f;
    public float r = 0.0f;
    public float s = 0.0f;
    public float t = Float.NaN;
    public float u = Float.NaN;

    public static boolean f(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.util.HashMap<java.lang.String, defpackage.rn3> r8, int r9) {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.zi1.a(java.util.HashMap, int):void");
    }

    @Override // java.lang.Comparable
    public final int compareTo(zi1 zi1Var) {
        zi1Var.getClass();
        return Float.compare(0.0f, 0.0f);
    }

    public final void e(View view) {
        this.g = view.getVisibility();
        this.i = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.j = view.getElevation();
        this.k = view.getRotation();
        this.l = view.getRotationX();
        this.e = view.getRotationY();
        this.m = view.getScaleX();
        this.n = view.getScaleY();
        this.o = view.getPivotX();
        this.p = view.getPivotY();
        this.q = view.getTranslationX();
        this.r = view.getTranslationY();
        this.s = view.getTranslationZ();
    }

    public final void g(Rect rect, a aVar, int i, int i2) {
        rect.width();
        rect.height();
        a.C0004a c0004aH = aVar.h(i2);
        a.d dVar = c0004aH.c;
        int i3 = dVar.c;
        this.f = i3;
        int i4 = dVar.b;
        this.g = i4;
        this.i = (i4 == 0 || i3 != 0) ? dVar.d : 0.0f;
        a.e eVar = c0004aH.f;
        boolean z = eVar.m;
        this.j = eVar.n;
        this.k = eVar.b;
        this.l = eVar.c;
        this.e = eVar.d;
        this.m = eVar.e;
        this.n = eVar.f;
        this.o = eVar.g;
        this.p = eVar.h;
        this.q = eVar.j;
        this.r = eVar.k;
        this.s = eVar.l;
        a.c cVar = c0004aH.d;
        hf0.c(cVar.d);
        this.t = cVar.h;
        this.u = c0004aH.c.e;
        Iterator<String> it = c0004aH.g.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            ox oxVar = c0004aH.g.get(next);
            int iM = gq2.m(oxVar.c);
            if ((iM == 4 || iM == 5 || iM == 7) ? false : true) {
                this.h.put(next, oxVar);
            }
        }
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return;
                    }
                }
            }
            float f = this.k + 90.0f;
            this.k = f;
            if (f > 180.0f) {
                this.k = f - 360.0f;
                return;
            }
            return;
        }
        this.k -= 90.0f;
    }
}
