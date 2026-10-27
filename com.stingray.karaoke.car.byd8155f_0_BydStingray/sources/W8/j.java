package W8;

import U8.EnumC0810a;
import V8.InterfaceC0817f;
import V8.InterfaceC0818g;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public final class j extends i {
    @Override // W8.g
    public final g e(InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        return new j(i7, enumC0810a, this.o, interfaceC2194h);
    }

    @Override // W8.g
    public final InterfaceC0817f f() {
        return this.o;
    }

    @Override // W8.i
    public final Object i(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) {
        Object objB = this.o.b(interfaceC0818g, interfaceC2189c);
        return objB == EnumC2303a.l ? objB : m7.q.f19267a;
    }
}
