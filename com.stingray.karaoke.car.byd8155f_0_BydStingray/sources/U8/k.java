package U8;

import r7.EnumC2303a;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class k extends AbstractC2410c {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l f9238m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9239n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(l lVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f9238m = lVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.l = obj;
        this.f9239n |= Integer.MIN_VALUE;
        Object objH = this.f9238m.H(null, 0, 0L, this);
        return objH == EnumC2303a.l ? objH : new s(objH);
    }
}
