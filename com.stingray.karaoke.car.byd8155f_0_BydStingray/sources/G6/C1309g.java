package g6;

import java.util.Iterator;
import java.util.List;
import s7.AbstractC2410c;

/* JADX INFO: renamed from: g6.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1309g extends AbstractC2410c {
    public C1310h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f14990m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Iterator f14991n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1310h f14992p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f14993q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1309g(C1310h c1310h, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f14992p = c1310h;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.o = obj;
        this.f14993q |= Integer.MIN_VALUE;
        return this.f14992p.b(this);
    }
}
