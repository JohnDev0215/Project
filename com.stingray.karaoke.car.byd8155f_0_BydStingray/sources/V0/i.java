package V0;

import N0.o;
import N0.r;
import android.text.TextPaint;
import java.util.ArrayList;
import n0.AbstractC1887p;
import n0.C1865O;
import n0.InterfaceC1889r;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f9310a = new j(false);

    public static final void a(o oVar, InterfaceC1889r interfaceC1889r, AbstractC1887p abstractC1887p, float f10, C1865O c1865o, Y0.j jVar, p0.e eVar, int i7) {
        ArrayList arrayList = oVar.f4396h;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            r rVar = (r) arrayList.get(i10);
            rVar.f4402a.g(interfaceC1889r, abstractC1887p, f10, c1865o, jVar, eVar, i7);
            interfaceC1889r.j(0.0f, rVar.f4402a.b());
        }
    }

    public static final void b(TextPaint textPaint, float f10) {
        if (Float.isNaN(f10)) {
            return;
        }
        if (f10 < 0.0f) {
            f10 = 0.0f;
        }
        if (f10 > 1.0f) {
            f10 = 1.0f;
        }
        textPaint.setAlpha(Math.round(f10 * 255));
    }
}
