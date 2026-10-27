package U;

import E0.Y;
import T.B0;
import a.AbstractC0904a;
import java.util.ArrayList;
import l3.P;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class v extends C {
    public static final v c;

    static {
        int i7 = 1;
        c = new v(0, i7, i7);
    }

    @Override // U.C
    public final void a(E5.z zVar, P p10, B0 b02, Y y10) {
        ((ArrayList) y10.f1602e).add((InterfaceC2772a) zVar.g(0));
    }

    @Override // U.C
    public final String c(int i7) {
        return AbstractC0904a.B(i7, 0) ? "effect" : super.c(i7);
    }
}
