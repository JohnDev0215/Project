package Y4;

import android.graphics.Matrix;
import android.graphics.Path;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f10776a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f10777b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f10778e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f10779f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f10780g = new ArrayList();

    public t() {
        d(0.0f, 270.0f, 0.0f);
    }

    public final void a(float f10) {
        float f11 = this.d;
        if (f11 == f10) {
            return;
        }
        float f12 = ((f10 - f11) + 360.0f) % 360.0f;
        if (f12 > 180.0f) {
            return;
        }
        float f13 = this.f10777b;
        float f14 = this.c;
        p pVar = new p(f13, f14, f13, f14);
        pVar.f10772f = this.d;
        pVar.f10773g = f12;
        this.f10780g.add(new n(pVar));
        this.d = f10;
    }

    public final void b(Matrix matrix, Path path) {
        ArrayList arrayList = this.f10779f;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((r) arrayList.get(i7)).a(matrix, path);
        }
    }

    public final void c(float f10, float f11) {
        q qVar = new q();
        qVar.f10774b = f10;
        qVar.c = f11;
        this.f10779f.add(qVar);
        o oVar = new o(qVar, this.f10777b, this.c);
        float fA = oVar.a() + 270.0f;
        float fA2 = oVar.a() + 270.0f;
        a(fA);
        this.f10780g.add(oVar);
        this.d = fA2;
        this.f10777b = f10;
        this.c = f11;
    }

    public final void d(float f10, float f11, float f12) {
        this.f10776a = f10;
        this.f10777b = 0.0f;
        this.c = f10;
        this.d = f11;
        this.f10778e = (f11 + f12) % 360.0f;
        this.f10779f.clear();
        this.f10780g.clear();
    }
}
