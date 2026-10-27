package androidx.compose.foundation;

import C.k;
import E0.V;
import g0.l;
import g0.n;
import g0.o;
import y.C2688I;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    static {
        new V() { // from class: androidx.compose.foundation.FocusableKt$FocusableInNonTouchModeElement$1
            public final boolean equals(Object obj) {
                return this == obj;
            }

            public final int hashCode() {
                return System.identityHashCode(this);
            }

            @Override // E0.V
            public final n i() {
                return new C2688I();
            }

            @Override // E0.V
            public final /* bridge */ /* synthetic */ void l(n nVar) {
            }
        };
    }

    public static final o a(o oVar, boolean z6, k kVar) {
        return oVar.k(z6 ? new FocusableElement(kVar) : l.f14684a);
    }
}
