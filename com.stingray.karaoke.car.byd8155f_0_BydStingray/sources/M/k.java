package M;

import N0.H;
import android.view.inputmethod.CursorAnchorInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {
    public static final CursorAnchorInfo.Builder a(CursorAnchorInfo.Builder builder, H h10, m0.d dVar) {
        int iC;
        N0.o oVar;
        int iC2;
        if (!dVar.e() && (iC = h10.f4349b.c(dVar.f18883b)) <= (iC2 = (oVar = h10.f4349b).c(dVar.d))) {
            while (true) {
                builder.addVisibleLineBounds(h10.g(iC), oVar.d(iC), h10.h(iC), oVar.b(iC));
                if (iC == iC2) {
                    break;
                }
                iC++;
            }
        }
        return builder;
    }
}
