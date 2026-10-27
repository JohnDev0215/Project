package V8;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: V8.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0834x extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9662m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0832v f9663n;
    public C.f o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0834x(C0832v c0832v, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9663n = c0832v;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f9662m |= Integer.MIN_VALUE;
        return this.f9663n.b(null, this);
    }
}
