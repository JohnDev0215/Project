package N5;

import s7.AbstractC2410c;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N5.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0450f extends AbstractC2410c {
    public InterfaceC2782k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f4689m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0451g f4690n;
    public int o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0450f(C0451g c0451g, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f4690n = c0451g;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4689m = obj;
        this.o |= Integer.MIN_VALUE;
        return this.f4690n.a(this);
    }
}
