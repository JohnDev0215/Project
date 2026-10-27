package Q5;

import T.C0761q;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class D implements InterfaceC2787p {
    public static final D l = new D();

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
        V5.o oVar = (V5.o) obj;
        float f10 = ((a1.e) obj2).l;
        C0761q c0761q = (C0761q) obj3;
        int iIntValue = ((Number) obj4).intValue();
        A7.m.f(oVar, "state");
        if ((iIntValue & 6) == 0) {
            i7 = (c0761q.f(oVar) ? 4 : 2) | iIntValue;
        } else {
            i7 = iIntValue;
        }
        if ((iIntValue & 48) == 0) {
            i7 |= c0761q.c(f10) ? 32 : 16;
        }
        if ((i7 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            com.bumptech.glide.c.f(oVar, f10, c0761q, i7 & 126);
        }
        return m7.q.f19267a;
    }
}
