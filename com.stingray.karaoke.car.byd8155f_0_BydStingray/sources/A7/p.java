package A7;

import H7.InterfaceC0317b;
import H7.InterfaceC0318c;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class p extends q implements H7.k {
    public p(InterfaceC0318c interfaceC0318c, String str, String str2) {
        super(C0055c.l, ((InterfaceC0057e) interfaceC0318c).d(), str, str2, !Objects.nonNull(interfaceC0318c) ? 1 : 0);
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return get(obj);
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0317b f() {
        return A.f443a.f(this);
    }

    @Override // H7.t
    public final Object get(Object obj) {
        return ((K7.r) c()).h(obj);
    }

    @Override // H7.w
    public final H7.s c() {
        return ((H7.k) l()).c();
    }

    @Override // H7.l
    public final H7.j d() {
        return ((H7.k) l()).d();
    }

    public p(Class cls, String str, String str2, int i7) {
        super(C0055c.l, cls, str, str2, i7);
    }
}
