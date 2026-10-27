package V8;

import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends AbstractC2410c {
    public f0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC0818g f9615m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public g0 f9616n;
    public S8.W o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f9617p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f9618q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ f0 f9619r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f9620s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e0(f0 f0Var, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9619r = f0Var;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.f9618q = obj;
        this.f9620s |= Integer.MIN_VALUE;
        this.f9619r.b(null, this);
        return EnumC2303a.l;
    }
}
