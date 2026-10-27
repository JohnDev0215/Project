package V8;

import R.C0684l;
import W8.AbstractC0843c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: V8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0816e implements InterfaceC0817f {
    public final InterfaceC0817f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2785n f9614m;

    public C0816e(InterfaceC0817f interfaceC0817f, InterfaceC2785n interfaceC2785n) {
        this.l = interfaceC0817f;
        this.f9614m = interfaceC2785n;
    }

    @Override // V8.InterfaceC0817f
    public final Object b(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) {
        A7.z zVar = new A7.z();
        zVar.l = AbstractC0843c.f9911b;
        Object objB = this.l.b(new C0684l(this, zVar, interfaceC0818g), interfaceC2189c);
        return objB == EnumC2303a.l ? objB : m7.q.f19267a;
    }
}
