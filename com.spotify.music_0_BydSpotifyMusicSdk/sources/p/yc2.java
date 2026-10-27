package p;

import android.graphics.Matrix;
import android.graphics.Path;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class yc2 {
    public float a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public final ArrayList g = new ArrayList();
    public final ArrayList h = new ArrayList();

    public yc2() {
        d(0.0f, 270.0f, 0.0f);
    }

    public final void a(float f) {
        float f2 = this.e;
        if (f2 == f) {
            return;
        }
        float f3 = ((f - f2) + 360.0f) % 360.0f;
        if (f3 > 180.0f) {
            return;
        }
        float f4 = this.c;
        float f5 = this.d;
        uc2 uc2Var = new uc2(f4, f5, f4, f5);
        uc2Var.f = this.e;
        uc2Var.g = f3;
        this.h.add(new sc2(uc2Var));
        this.e = f;
    }

    public final void b(Matrix matrix, Path path) {
        ArrayList arrayList = this.g;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((wc2) arrayList.get(i)).a(matrix, path);
        }
    }

    public final void c(float f, float f2) {
        vc2 vc2Var = new vc2();
        vc2Var.b = f;
        vc2Var.c = f2;
        this.g.add(vc2Var);
        tc2 tc2Var = new tc2(vc2Var, this.c, this.d);
        float fB = tc2Var.b() + 270.0f;
        float fB2 = tc2Var.b() + 270.0f;
        a(fB);
        this.h.add(tc2Var);
        this.e = fB2;
        this.c = f;
        this.d = f2;
    }

    public final void d(float f, float f2, float f3) {
        this.a = 0.0f;
        this.b = f;
        this.c = 0.0f;
        this.d = f;
        this.e = f2;
        this.f = (f2 + f3) % 360.0f;
        this.g.clear();
        this.h.clear();
    }
}
