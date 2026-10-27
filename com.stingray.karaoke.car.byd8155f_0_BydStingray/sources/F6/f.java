package f6;

import com.stingray.kca.model.KaraokeItem;
import java.util.Iterator;
import java.util.List;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractC2410c {
    public g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f14607m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Iterator f14608n;
    public KaraokeItem o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f14609p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ g f14610q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f14611r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f14610q = gVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f14609p = obj;
        this.f14611r |= Integer.MIN_VALUE;
        return g.o(this.f14610q, this);
    }
}
