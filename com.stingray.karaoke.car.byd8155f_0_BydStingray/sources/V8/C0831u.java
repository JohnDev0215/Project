package V8;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: V8.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0831u extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9653m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0832v f9654n;
    public C0832v o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public InterfaceC0818g f9655p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public W8.z f9656q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0831u(C0832v c0832v, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9654n = c0832v;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f9653m |= Integer.MIN_VALUE;
        return this.f9654n.b(null, this);
    }
}
