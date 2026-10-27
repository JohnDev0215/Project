package N5;

import java.util.Set;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class W extends AbstractC2410c {
    public Set l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Set f4617m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4618n;
    public final /* synthetic */ Z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4619p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W(Z z6, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.o = z6;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f4618n = obj;
        this.f4619p |= Integer.MIN_VALUE;
        return this.o.G(null, null, null, 0L, this);
    }
}
