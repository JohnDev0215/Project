package A7;

import H7.InterfaceC0317b;
import H7.InterfaceC0318c;
import H7.InterfaceC0320e;

/* JADX INFO: loaded from: classes.dex */
public class s extends u implements H7.t {
    public s(InterfaceC0320e interfaceC0320e, String str, String str2) {
        super(C0055c.l, ((InterfaceC0057e) interfaceC0320e).d(), str, str2, !(interfaceC0320e instanceof InterfaceC0318c) ? 1 : 0);
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return get(obj);
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0317b f() {
        return A.f443a.h(this);
    }

    @Override // H7.t
    public Object get(Object obj) {
        return ((K7.r) c()).h(obj);
    }

    @Override // H7.w
    public final H7.s c() {
        return ((H7.t) l()).c();
    }
}
