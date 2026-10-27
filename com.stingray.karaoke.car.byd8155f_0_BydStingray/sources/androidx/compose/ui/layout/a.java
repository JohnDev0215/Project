package androidx.compose.ui.layout;

import C0.InterfaceC0112t;
import C0.J;
import g0.o;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final Object a(J j3) {
        Object objI = j3.i();
        InterfaceC0112t interfaceC0112t = objI instanceof InterfaceC0112t ? (InterfaceC0112t) objI : null;
        if (interfaceC0112t != null) {
            return interfaceC0112t.z();
        }
        return null;
    }

    public static final o b(o oVar, InterfaceC2786o interfaceC2786o) {
        return oVar.k(new LayoutElement(interfaceC2786o));
    }

    public static final o c(o oVar, Object obj) {
        return oVar.k(new LayoutIdElement(obj));
    }

    public static final o d(o oVar, InterfaceC2782k interfaceC2782k) {
        return oVar.k(new OnGloballyPositionedElement(interfaceC2782k));
    }

    public static final o e(o oVar, InterfaceC2782k interfaceC2782k) {
        return oVar.k(new OnSizeChangedModifier(interfaceC2782k));
    }
}
