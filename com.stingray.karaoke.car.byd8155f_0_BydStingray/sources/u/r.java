package U;

import E0.Y;
import T.B0;
import T.InterfaceC0769u0;
import a.AbstractC0904a;
import java.util.ArrayList;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class r extends C {
    public static final r c;

    static {
        int i7 = 1;
        c = new r(0, i7, i7);
    }

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        ((ArrayList) y10.c).add((InterfaceC0769u0) zVar.g(0));
    }

    @Override // U.C
    public final String c(int i7) {
        return AbstractC0904a.B(i7, 0) ? "value" : super.c(i7);
    }
}
