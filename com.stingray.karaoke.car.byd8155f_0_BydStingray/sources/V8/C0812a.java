package V8;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: V8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0812a extends AbstractC2410c {
    public W8.z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f9604m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0819h f9605n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0812a(C0819h c0819h, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9605n = c0819h;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f9604m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f9605n.b(null, this);
    }
}
