package V8;

import U8.EnumC0810a;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public final class N implements d0, InterfaceC0817f, W8.w {
    public final /* synthetic */ L l;

    public N(L l) {
        this.l = l;
    }

    @Override // W8.w
    public final InterfaceC0817f a(InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        return (((i7 < 0 || i7 >= 2) && i7 != -2) || enumC0810a != EnumC0810a.f9223m) ? AbstractC0828q.l(this, interfaceC2194h, i7, enumC0810a) : this;
    }

    @Override // V8.InterfaceC0817f
    public final Object b(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) throws Throwable {
        ((f0) this.l).b(interfaceC0818g, interfaceC2189c);
        return EnumC2303a.l;
    }

    @Override // V8.d0
    public final Object getValue() {
        return ((f0) this.l).getValue();
    }
}
