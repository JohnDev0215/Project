package K5;

import q7.InterfaceC2189c;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: K5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0370c extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3624m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A3.m f3625n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0370c(A3.m mVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f3625n = mVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.f3624m |= Integer.MIN_VALUE;
        return this.f3625n.f(null, this);
    }
}
