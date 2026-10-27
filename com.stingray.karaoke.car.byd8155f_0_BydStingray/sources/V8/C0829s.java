package V8;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: V8.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0829s extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9649m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0830t f9650n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public InterfaceC0818g f9651p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0829s(C0830t c0830t, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9650n = c0830t;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f9649m |= Integer.MIN_VALUE;
        return this.f9650n.b(null, this);
    }
}
