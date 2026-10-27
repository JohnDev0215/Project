package D;

import androidx.compose.foundation.layout.LayoutWeightElement;
import androidx.compose.foundation.layout.VerticalAlignElement;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class T {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final T f1050a = new T();

    public static g0.o b(g0.o oVar, float f10) {
        if (f10 > 0.0d) {
            return oVar.k(new LayoutWeightElement(AbstractC2673a.s(f10, Float.MAX_VALUE), true));
        }
        throw new IllegalArgumentException(("invalid weight " + f10 + "; must be greater than zero").toString());
    }

    public final g0.o a(g0.o oVar, g0.f fVar) {
        return oVar.k(new VerticalAlignElement(fVar));
    }
}
