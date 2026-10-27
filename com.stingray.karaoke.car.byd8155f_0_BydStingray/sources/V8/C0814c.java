package V8;

import N1.C0438t;
import U8.EnumC0810a;
import W8.C0846f;
import q7.InterfaceC2194h;
import r7.EnumC2303a;

/* JADX INFO: renamed from: V8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0814c extends W8.g {
    public final C0438t o;

    public C0814c(C0438t c0438t, InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        super(interfaceC2194h, i7, enumC0810a);
        this.o = c0438t;
    }

    @Override // W8.g
    public final Object d(U8.x xVar, C0846f c0846f) {
        Object objInvoke = this.o.invoke(xVar, c0846f);
        return objInvoke == EnumC2303a.l ? objInvoke : m7.q.f19267a;
    }

    @Override // W8.g
    public final W8.g e(InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        return new C0814c(this.o, interfaceC2194h, i7, enumC0810a);
    }

    @Override // W8.g
    public final String toString() {
        return "block[" + this.o + "] -> " + super.toString();
    }
}
