package androidx.compose.ui.focus;

import g0.o;
import l0.C1723n;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final o a(C1723n c1723n) {
        return new FocusRequesterElement(c1723n);
    }

    public static final o b(o oVar, InterfaceC2782k interfaceC2782k) {
        return oVar.k(new FocusChangedElement(interfaceC2782k));
    }
}
