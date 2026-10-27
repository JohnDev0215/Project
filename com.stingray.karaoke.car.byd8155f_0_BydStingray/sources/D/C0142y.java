package D;

import androidx.compose.foundation.layout.LayoutWeightElement;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: D.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0142y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0142y f1119a = new C0142y();

    public static g0.o a(C0142y c0142y, g0.o oVar, float f10) {
        c0142y.getClass();
        if (f10 > 0.0d) {
            return oVar.k(new LayoutWeightElement(AbstractC2673a.s(f10, Float.MAX_VALUE), true));
        }
        throw new IllegalArgumentException(("invalid weight " + f10 + "; must be greater than zero").toString());
    }
}
