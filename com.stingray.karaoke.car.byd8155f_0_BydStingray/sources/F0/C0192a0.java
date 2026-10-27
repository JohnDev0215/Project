package F0;

import r7.EnumC2303a;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: F0.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0192a0 extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0196c0 f2174m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2175n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0192a0(C0196c0 c0196c0, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f2174m = c0196c0;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.l = obj;
        this.f2175n |= Integer.MIN_VALUE;
        this.f2174m.a(null, this);
        return EnumC2303a.l;
    }
}
