package V8;

import W8.AbstractC0843c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class J implements InterfaceC0817f {
    public final /* synthetic */ InterfaceC0817f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0817f f9576m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ s7.i f9577n;

    /* JADX WARN: Multi-variable type inference failed */
    public J(InterfaceC0817f interfaceC0817f, InterfaceC0817f interfaceC0817f2, InterfaceC2786o interfaceC2786o) {
        this.l = interfaceC0817f;
        this.f9576m = interfaceC0817f2;
        this.f9577n = (s7.i) interfaceC2786o;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [s7.i, z7.o] */
    @Override // V8.InterfaceC0817f
    public final Object b(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) {
        Object objA = AbstractC0843c.a(interfaceC0818g, interfaceC2189c, new I((InterfaceC2786o) this.f9577n, (InterfaceC2189c) null), new InterfaceC0817f[]{this.l, this.f9576m});
        return objA == EnumC2303a.l ? objA : m7.q.f19267a;
    }
}
