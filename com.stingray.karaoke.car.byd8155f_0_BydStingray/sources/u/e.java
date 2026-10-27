package U;

import E0.Y;
import T.B0;
import T.C0733c;
import T.C0735d;
import T.v0;
import a.AbstractC0904a;
import java.util.ArrayList;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class e extends C {
    public static final e c = new e(0, 2, 1);

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        C0733c c0733c = (C0733c) zVar.g(0);
        Object objG = zVar.g(1);
        if (objG instanceof v0) {
            ((ArrayList) y10.c).add(((v0) objG).f8385a);
        }
        if (b02.f8167n != 0) {
            C0735d.y("Can only append a slot if not current inserting");
            throw null;
        }
        int i7 = b02.f8163i;
        int i10 = b02.f8164j;
        int iC = b02.c(c0733c);
        int iF = b02.f(b02.f8158b, b02.p(iC + 1));
        b02.f8163i = iF;
        b02.f8164j = iF;
        b02.s(1, iC);
        if (i7 >= iF) {
            i7++;
            i10++;
        }
        b02.c[iF] = objG;
        b02.f8163i = i7;
        b02.f8164j = i10;
    }

    @Override // U.C
    public final String c(int i7) {
        return AbstractC0904a.B(i7, 0) ? "anchor" : AbstractC0904a.B(i7, 1) ? "value" : super.c(i7);
    }
}
