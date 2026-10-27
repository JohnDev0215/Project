package W8;

import S8.AbstractC0727y;
import U8.EnumC0810a;
import V8.InterfaceC0817f;
import V8.InterfaceC0818g;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class n extends i {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final s7.i f9927p;

    /* JADX WARN: Multi-variable type inference failed */
    public n(InterfaceC2786o interfaceC2786o, InterfaceC0817f interfaceC0817f, InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        super(i7, enumC0810a, interfaceC0817f, interfaceC2194h);
        this.f9927p = (s7.i) interfaceC2786o;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [s7.i, z7.o] */
    @Override // W8.g
    public final g e(InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        return new n(this.f9927p, this.o, interfaceC2194h, i7, enumC0810a);
    }

    @Override // W8.i
    public final Object i(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) {
        Object objH = AbstractC0727y.h(new m(this, interfaceC0818g, null), interfaceC2189c);
        return objH == EnumC2303a.l ? objH : m7.q.f19267a;
    }
}
