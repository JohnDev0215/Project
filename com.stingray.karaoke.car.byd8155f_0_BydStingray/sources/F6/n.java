package f6;

import com.stingray.kca.model.KaraokeItem;
import java.util.Iterator;
import java.util.List;
import s7.AbstractC2410c;

/* JADX INFO: loaded from: classes.dex */
public final class n extends AbstractC2410c {
    public o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f14631m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Iterator f14632n;
    public KaraokeItem o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f14633p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ o f14634q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f14635r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(o oVar, AbstractC2410c abstractC2410c) {
        super(abstractC2410c);
        this.f14634q = oVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) {
        this.f14633p = obj;
        this.f14635r |= Integer.MIN_VALUE;
        return o.p(this.f14634q, this);
    }
}
